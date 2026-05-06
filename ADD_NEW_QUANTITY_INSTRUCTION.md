# How to Add a New Physical Quantity

This document describes the complete process for adding a new physical quantity to the Unitility library, based on analysis of all previous commits that introduced quantities (Temperature, Length, Mass, Energy, Force, Electrical quantities, etc.).

---

## Table of Contents

- [Overview](#overview)
- [Step 1: Choose the Category Package](#step-1-choose-the-category-package)
- [Step 2: Create the Unit Interface](#step-2-create-the-unit-interface)
- [Step 3: Create the Units Enum](#step-3-create-the-units-enum)
- [Step 4: Create the Quantity Class](#step-4-create-the-quantity-class)
- [Step 5: Register in PhysicalQuantityDefaultParsingFactory](#step-5-register-in-physicalquantitydefaultparsingfactory)
- [Step 6: Register in SupportedQuantitiesRegistry](#step-6-register-in-supportedquantitiesregistry)
- [Step 7: Create JPA/Hibernate Converter (unitility-persistence)](#step-7-create-jpahibernate-converter-unitility-persistence)
- [Step 8: Write Tests](#step-8-write-tests)
- [Step 9: Update Supporting Tests](#step-9-update-supporting-tests)
- [Step 10: Verify the Build](#step-10-verify-the-build)
- [Alternative: Reusing Existing Units](#alternative-reusing-existing-units)
- [Module Impact Summary](#module-impact-summary)

---

## Overview

Adding a new physical quantity requires changes across **multiple modules** in this multi-module Maven project:

| Module | Action | Files |
|--------|--------|-------|
| `unitility-core` | Create 3 new files + modify 2 registries | Unit interface, Units enum, Quantity class + 2 registry entries |
| `unitility-persistence` | Create 1 converter | JPA `AttributeConverter` |
| `unitility-jackson` | **None** | Auto-discovers from registry |
| `unitility-spring` | **None** | Uses Jackson module |
| `unitility-quarkus` | **None** | Generic CDI beans |
| `unitility-validation` | **None** | Generic annotations |

The core pattern is:

```
Unit Interface  →  Units Enum  →  Quantity Class  →  Registries  →  Converter  →  Tests
```

---

## Step 1: Choose the Category Package

Quantities are organized into category packages under `unitility-core/src/main/java/com/synerset/unitility/unitsystem/`:

| Package | Examples |
|---------|----------|
| `common` | Angle, Area, Distance, Mass, Velocity, Volume |
| `thermodynamic` | Temperature, Pressure, Energy, Power, Density |
| `electric` | Voltage, Current, Resistance, Capacitance |
| `mechanical` | Force, Momentum, Torque |
| `flow` | MassFlow, VolumetricFlow |
| `acoustic` | SoundPower, SoundPressure |
| `oscillation` | Frequency |
| `humidity` | HumidityRatio, RelativeHumidity |
| `hydraulic` | LinearResistance, FrictionFactor |
| `geographic` | Latitude, Longitude, Bearing |
| `similaritynumber` | ReynoldsNumber, PrandtlNumber (dimensionless) |

**Decision:** Use an existing package if your quantity fits, or create a new subpackage for a new category.

---

## Step 2: Create the Unit Interface

**Location:** `unitility-core/src/main/java/com/synerset/unitility/unitsystem/<category>/<Quantity>Unit.java`

Create a minimal interface extending `Unit` that narrows the `getBaseUnit()` return type:

```java
package com.synerset.unitility.unitsystem.<category>;

import com.synerset.unitility.unitsystem.Unit;

public interface YourQuantityUnit extends Unit {
    @Override
    YourQuantityUnit getBaseUnit();
}
```

---

## Step 3: Create the Units Enum

**Location:** `unitility-core/src/main/java/com/synerset/unitility/unitsystem/<category>/<Quantity>Units.java`

```java
package com.synerset.unitility.unitsystem.<category>;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum YourQuantityUnits implements YourQuantityUnit {

    // Base SI unit — identity converters
    BASE_UNIT_SYMBOL("symbol", val -> val, val -> val),

    // Sub-multiples and multiples — multiplication/division
    SUB_UNIT("sub", val -> val * 1E-3, val -> val / 1E-3),
    MULTI_UNIT("multi", val -> val * 1E3, val -> val / 1E3),

    // Offset units (like Celsius/Fahrenheit) — affine transforms
    OFFSET_UNIT("°O", val -> val * scale + offset, val -> (val - offset) / scale);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    YourQuantityUnits(String symbol, DoubleUnaryOperator toBaseConverter, 
                      DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public YourQuantityUnit getBaseUnit() {
        return BASE_UNIT_SYMBOL;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    /** Resolve a unit from its string symbol. Returns base unit for null/blank input. */
    public static YourQuantityUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return BASE_UNIT_SYMBOL;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (YourQuantityUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException(
            "Unsupported unit symbol: {" + rawSymbol + "}. Target class: "
            + YourQuantityUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .dropDegreeSymbols()
                .toString();
    }
}
```

**Key points:**
- The **base unit** always has identity converters (`val -> val`)
- Non-base units define `toBaseConverter` (this unit → base) and `fromBaseToUnitConverter` (base → this unit)
- The `fromSymbol()` method uses `StringTransformer` for case-insensitive, cleaned symbol matching
- Return the base unit for `null` or blank input

---

## Step 4: Create the Quantity Class

**Location:** `unitility-core/src/main/java/com/synerset/unitility/unitsystem/<category>/<Quantity>.java`

```java
package com.synerset.unitility.unitsystem.<category>;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class YourQuantity implements CalculableQuantity<YourQuantityUnit, YourQuantity> {

    // Optional: physical limits/constants
    public static final YourQuantity PHYSICAL_MIN_LIMIT = YourQuantity.ofBaseUnit(0);

    private final double value;
    private final double baseValue;
    private final YourQuantityUnit unitType;

    public YourQuantity(double value, YourQuantityUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = YourQuantityUnits.BASE_UNIT_SYMBOL;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // --- Static factory methods ---

    public static YourQuantity of(double value, YourQuantityUnit unit) {
        return new YourQuantity(value, unit);
    }

    public static YourQuantity of(double value, String unitSymbol) {
        YourQuantityUnit resolvedUnit = YourQuantityUnits.fromSymbol(unitSymbol);
        return new YourQuantity(value, resolvedUnit);
    }

    // Unit-specific factory methods (one per enum constant)
    public static YourQuantity ofBaseUnitName(double value) {
        return new YourQuantity(value, YourQuantityUnits.BASE_UNIT_SYMBOL);
    }

    public static YourQuantity ofSubUnit(double value) {
        return new YourQuantity(value, YourQuantityUnits.SUB_UNIT);
    }

    // --- CalculableQuantity interface implementations ---

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public YourQuantityUnit getUnit() {
        return unitType;
    }

    @Override
    public YourQuantity toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public YourQuantity toUnit(YourQuantityUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return YourQuantity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public YourQuantity toUnit(String targetUnit) {
        YourQuantityUnit resolvedUnit = YourQuantityUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public YourQuantity withValue(double value) {
        return YourQuantity.of(value, unitType);
    }

    // --- Convenience conversion methods (one per unit) ---

    public YourQuantity toBaseUnitName() {
        return toUnit(YourQuantityUnits.BASE_UNIT_SYMBOL);
    }

    public YourQuantity toSubUnit() {
        return toUnit(YourQuantityUnits.SUB_UNIT);
    }

    // --- Convenience getter methods (one per unit) ---

    public double getInBaseUnitNames() {
        return getInUnit(YourQuantityUnits.BASE_UNIT_SYMBOL);
    }

    public double getInSubUnit() {
        return getInUnit(YourQuantityUnits.SUB_UNIT);
    }

    // --- Standard Java methods ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YourQuantity other = (YourQuantity) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
            && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "YourQuantity{" + value + " " + unitType.getSymbol() + '}';
    }
}
```

**Key points:**
- Implements `CalculableQuantity<U, Q>` (not just `PhysicalQuantity<U>`) to get arithmetic operations (`plus`, `minus`, `multiply`, `divide`, etc.) for free via default methods
- Stores both `value` (in current unit) and `baseValue` (in base unit) for efficient equality comparison
- `equals()` compares `baseValue` — two quantities are equal if they represent the same physical amount, regardless of unit
- `getInUnit()` and `toUnit()` are inherited default methods from `PhysicalQuantity` / `CalculableQuantity`

---

## Step 5: Register in PhysicalQuantityDefaultParsingFactory

**File:** `unitility-core/src/main/java/com/synerset/unitility/unitsystem/util/PhysicalQuantityDefaultParsingFactory.java`

Add **two** entries:

### a) Add import statement

```java
import com.synerset.unitility.unitsystem.<category>.YourQuantity;
import com.synerset.unitility.unitsystem.<category>.YourQuantityUnits;
```

### b) Add to `immutableParsingRegistry` (inside the `Map.ofEntries(...)`)

```java
// For quantities with unit symbol support:
Map.entry(YourQuantity.class, YourQuantity::of),

// For dimensionless quantities (no symbol parameter):
Map.entry(YourQuantity.class, (value, symbol) -> YourQuantity.of(value)),
```

### c) Add to `immutableDefaultUnitRegistry` (inside the `Map.ofEntries(...)`)

```java
Map.entry(YourQuantity.class, YourQuantityUnits.BASE_UNIT_SYMBOL),
```

**Important:** This class uses `Map.ofEntries()` which has a limit on the number of entries. If you hit the limit, you may need to switch to an unmodifiable map built from a builder or similar pattern.

---

## Step 6: Register in SupportedQuantitiesRegistry

**File:** `unitility-core/src/main/java/com/synerset/unitility/unitsystem/util/SupportedQuantitiesRegistry.java`

### a) Add import statement

```java
import com.synerset.unitility.unitsystem.<category>.YourQuantity;
import com.synerset.unitility.unitsystem.<category>.YourQuantityUnits;
```

### b) Add entry to `immutableRegistry`

```java
// For quantities with their own units enum:
Map.entry(YourQuantity.class, () -> Arrays.asList(YourQuantityUnits.values())),

// For dimensionless quantities with no units:
Map.entry(YourQuantity.class, Collections::emptyList),

// For quantities reusing another category's units (e.g., Length reusing DistanceUnits):
Map.entry(YourQuantity.class, () -> Arrays.asList(DistanceUnits.values())),
```

---

## Step 7: Create JPA/Hibernate Converter (unitility-persistence)

**Location:** `unitility-persistence/src/main/java/com/synerset/unitility/persistence/converter/plainsivalue/<category>/<Quantity>PlainSiConverter.java`

```java
package com.synerset.unitility.persistence.converter.plainsivalue.<category>;

import com.synerset.unitility.unitsystem.<category>.YourQuantity;
import com.synerset.unitility.unitsystem.<category>.YourQuantityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class YourQuantityPlainSiConverter implements AttributeConverter<YourQuantity, Double> {

    public static final YourQuantityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(YourQuantity.class);

    @Override
    public Double convertToDatabaseColumn(YourQuantity attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public YourQuantity convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : YourQuantity.of(dbData, DEFAULT_SI_UNIT);
    }
}
```

This converter stores the quantity's value in the **base SI unit** as a plain `Double` in the database, and reconstructs the full quantity object on read.

---

## Step 8: Write Tests

**Location:** `unitility-core/src/test/java/com/synerset/unitility/unitsystem/<category>/<Quantity>Test.java`

```java
package com.synerset.unitility.unitsystem.<category>;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class YourQuantityTest {

    @Test
    @DisplayName("should have BASE_UNIT as base unit")
    void shouldHaveBaseUnitAsBaseUnit() {
        // Given
        YourQuantityUnit expectedBaseUnit = YourQuantityUnits.BASE_UNIT_SYMBOL;

        // When
        YourQuantity quantity = YourQuantity.ofSubUnit(10);
        YourQuantityUnit actualBaseUnit = quantity.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should convert between units correctly")
    void shouldConvertBetweenUnitsCorrectly() {
        // Given
        YourQuantity initialInBase = YourQuantity.ofBaseUnitName(1000.0);

        // When
        YourQuantity inSubUnit = initialInBase.toUnit(YourQuantityUnits.SUB_UNIT);
        YourQuantity backToBase = inSubUnit.toBaseUnit();

        // Then
        assertThat(inSubUnit.getValue()).isEqualTo(1000000.0); // 1000 * 1E3
        assertThat(backToBase).isEqualTo(initialInBase);
        assertThat(backToBase.getBaseValue()).isEqualTo(initialInBase.getBaseValue(), withPrecision(1E-13));
    }

    @Test
    @DisplayName("should compare equal across different units")
    void shouldCompareEqualAcrossDifferentUnits() {
        // Given
        YourQuantity inBase = YourQuantity.ofBaseUnitName(1.0);
        YourQuantity inSub = YourQuantity.ofSubUnit(1000.0);

        // Then
        assertThat(inBase).isEqualTo(inSub);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() chain")
    void shouldReturnValidResultFromConversionChain() {
        // Given
        YourQuantity expected = YourQuantity.ofBaseUnitName(10.1);

        // When
        YourQuantity actual = expected.toSubUnit()
                .toBaseUnitName();

        double actualValue = expected.getInBaseUnitNames();

        // Then
        assertThat(actual.getValue()).isEqualTo(expected.getValue(), withPrecision(1E-13));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }
}
```

**Test coverage checklist:**
- [ ] Base unit identification
- [ ] Conversion between all defined units (round-trip)
- [ ] Equality comparison across different units
- [ ] `toX()` and `getInX()` convenience methods
- [ ] Edge cases (zero, negative values, physical limits if applicable)

---

## Step 9: Update Supporting Tests

### SupportedQuantitiesRegistryTest

**File:** `unitility-core/src/test/java/com/synerset/unitility/unitsystem/util/SupportedQuantitiesRegistryTest.java`

Update the expected count in `findAllSupportedQuantities_shouldFindAllSupportedQuantitiesAndAssociatedUnits()`:

```java
assertThat(allSupportedQuantities).isNotNull().isNotEmpty().hasSize(70); // increment by 1
```

---

## Step 10: Verify the Build

Run the full build to ensure everything compiles and tests pass:

```bash
mvn clean install
```

Or run just the relevant test:

```bash
mvn test -Dtest=YourQuantityTest
```

---

## Alternative: Reusing Existing Units

Some quantities share the same physical dimension and can reuse an existing unit enum. Examples:

| Quantity | Reuses | Has own enum? |
|----------|--------|---------------|
| `Length`, `Width`, `Height`, `Diameter`, `Perimeter`, `Thickness` | `DistanceUnit` / `DistanceUnits` | No |
| `SoundPower` | `PowerUnit` | Yes (`SoundPowerUnits` implements `PowerUnit`) |
| `SoundPressure` | `PressureUnit` | Yes (`SoundPressureUnits` implements `PressureUnit`) |
| `SDR` | `RatioUnits` | No |
| `Latitude`, `Longitude`, `Bearing` | `AngleUnits` | No |

When reusing units:
1. **Skip** creating a new unit interface
2. **Optionally** create a dedicated units enum that implements the existing unit interface (for domain-specific units)
3. In the quantity class, use the existing unit type in the generic parameters
4. In registries, reference the existing units enum
5. In the JPA converter, use the existing unit type

---

## Module Impact Summary

### Files to CREATE (per new quantity):

| # | File | Module | Path Pattern |
|---|------|--------|-------------|
| 1 | `<Quantity>Unit.java` | unitility-core | `unitility-core/.../unitsystem/<category>/` |
| 2 | `<Quantity>Units.java` | unitility-core | `unitility-core/.../unitsystem/<category>/` |
| 3 | `<Quantity>.java` | unitility-core | `unitility-core/.../unitsystem/<category>/` |
| 4 | `<Quantity>PlainSiConverter.java` | unitility-persistence | `unitility-persistence/.../converter/plainsivalue/<category>/` |
| 5 | `<Quantity>Test.java` | unitility-core (test) | `unitility-core/.../test/.../<category>/` |

### Files to MODIFY:

| # | File | Module | Change |
|---|------|--------|--------|
| 6 | `PhysicalQuantityDefaultParsingFactory.java` | unitility-core | Add 2 `Map.entry` calls (parsing + default unit) |
| 7 | `SupportedQuantitiesRegistry.java` | unitility-core | Add 1 `Map.entry` call |
| 8 | `SupportedQuantitiesRegistryTest.java` | unitility-core (test) | Increment expected quantity count |

### Modules requiring NO changes:

| Module | Reason |
|--------|--------|
| `unitility-jackson` | Auto-discovers quantities from `PhysicalQuantityParsingFactory` registry |
| `unitility-spring` | Uses Jackson module's generic converters |
| `unitility-quarkus` | Generic CDI beans, uses parsing factory |
| `unitility-validation` | Generic `@PhysicalMin`, `@PhysicalMax`, `@PhysicalRange` annotations |
| `unitility-bom` | Maven BOM, no code changes needed |
