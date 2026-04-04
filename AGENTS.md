# AGENTS.md

This file provides guidance to agents when working with code in this repository.

## Build/Lint/Test Commands

- **Build**: `mvn clean install`
- **Test all**: `mvn test`
- **Single test class**: `mvn test -Dtest=TemperatureTest` (run from module directory or use full class name)
- **Skip tests**: `mvn clean install -DskipTests`
- **Coverage report**: Generated in `target/site/jacoco/` after running tests

## Architecture Overview

Multi-module Maven project with core physics units library:
- `unitility-core`: Core quantity classes implementing `PhysicalQuantity<U>` and `CalculableQuantity<U, Q>` interfaces
- `unitility-jackson`: Jackson serialization/deserialization module
- `unitility-spring`: Spring Boot autoconfiguration for web MVC converters
- `unitility-quarkus`: Quarkus support
- `unitility-validation`: Jakarta validation integration  
- `unitility-persistence`: Database persistence (JPA/Hibernate)
- `unitility-bom`: Bill of Materials for dependency management

## Code Style Guidelines

### Quantity Class Pattern
Each quantity follows this structure:
1. Static factory methods: `of(value, unit)`, `ofMeters(value)`
2. Conversion methods: `toMeter()`, `toKilometer()` 
3. Value getters: `getInMeters()`, `getInKilometers()`
4. Immutable with final fields storing both value and baseValue
5. Base unit conversion via `toBaseUnit()` using unit's converter functions

### Unit Enum Pattern
Units are enums implementing the Unit interface:
- Store symbol, toBaseConverter, fromBaseToUnitConverter as DoubleUnaryOperator
- Implement `fromSymbol(String)` with case-insensitive matching via `StringTransformer`
- Define base unit in `getBaseUnit()` method

### Test Style
- Use Given/When/Then structure
- Test naming: `should[Description]` pattern
- Assertions via AssertJ with precision for doubles: `withPrecision(1E-13)`
- Tests placed alongside source files (not separate test directory)

### Key Utilities
- [`ValueFormatter`](unitility-core/src/main/java/com/synerset/unitility/unitsystem/util/ValueFormatter.java): Number formatting with relevant digits
- [`StringTransformer`](unitility-core/src/main/java/com/synerset/unitility/unitsystem/util/StringTransformer.java): Symbol normalization (trim, lowercase, remove degree symbols)
- [`PhysicalQuantityParsingFactory`](unitility-core/src/main/java/com/synerset/unitility/unitsystem/util/PhysicalQuantityParsingFactory.java): Factory for parsing quantities from strings

## How to Add a New Physical Quantity

Based on commit 57f99fbe52dfd1cad1aaf657defba06bc62191ae (electrical quantities addition), follow these steps:

### Step 1: Create the Unit Interface
Create `YourQuantityUnit.java` in the appropriate package:
```java
package com.synerset.unitility.unitsystem.<category>;

import com.synerset.unitility.unitsystem.Unit;

public interface YourQuantityUnit extends Unit {
    @Override
    YourQuantityUnit getBaseUnit();
}
```

### Step 2: Create the Units Enum
Create `YourQuantityUnits.java` implementing the unit interface:
```java
package com.synerset.unitility.unitsystem.<category>;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum YourQuantityUnits implements YourQuantityUnit {
    BASE_UNIT("symbol", val -> val, val -> val),  // Base SI unit with identity converters
    SUB_UNIT("sub", val -> val * 1E-3, val -> val / 1E-3),  // Sub-multiple
    MULTI_UNIT("multi", val -> val * 1E3, val -> val / 1E3);   // Multiple

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    YourQuantityUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() { return symbol; }

    @Override
    public YourQuantityUnits getBaseUnit() { return BASE_UNIT; }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static YourQuantityUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return BASE_UNIT;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (YourQuantityUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equals(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + rawSymbol);
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString).trimAndClean().toString();
    }
}
```

### Step 3: Create the Quantity Class
Create `YourQuantity.java` implementing `CalculableQuantity<Unit, Quantity>`:
```java
package com.synerset.unitility.unitsystem.<category>;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import java.util.Objects;

public class YourQuantity implements CalculableQuantity<YourQuantityUnit, YourQuantity> {
    private final double value;
    private final double baseValue;
    private final YourQuantityUnit unitType;

    public YourQuantity(double value, YourQuantityUnit unitType) {
        this.value = value;
        if (unitType == null) { unitType = YourQuantityUnits.BASE_UNIT; }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static YourQuantity of(double value, YourQuantityUnit unit) {
        return new YourQuantity(value, unit);
    }

    public static YourQuantity of(double value, String unitSymbol) {
        return new YourQuantity(value, YourQuantityUnits.fromSymbol(unitSymbol));
    }

    // Unit-specific factory methods (e.g., ofMeters, ofKilometers)
    public static YourQuantity ofBaseUnitName(double value) {
        return new YourQuantity(value, YourQuantityUnits.BASE_UNIT);
    }

    // Implement CalculableQuantity interface methods
    @Override public double getValue() { return value; }
    @Override public double getBaseValue() { return baseValue; }
    @Override public YourQuantityUnit getUnit() { return unitType; }
    @Override public YourQuantity toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }
    @Override public YourQuantity toUnit(YourQuantityUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }
    @Override public YourQuantity toUnit(String targetUnit) {
        return toUnit(YourQuantityUnits.fromSymbol(targetUnit));
    }
    @Override public YourQuantity withValue(double value) {
        return of(value, unitType);
    }

    // Conversion methods (e.g., toMeters(), toKilometers())
    // Value getter methods (e.g., getInMeters(), getInKilometers())

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof YourQuantity)) return false;
        YourQuantity other = (YourQuantity) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override public int hashCode() { return Objects.hash(baseValue, unitType.getBaseUnit()); }

    @Override public String toString() {
        return "YourQuantity{" + value + unitType.getSymbol() + '}';
    }
}
```

### Step 4: Update Registry Files
Add entries to three registry files:

**a) [`PhysicalQuantityDefaultParsingFactory.java`](unitility-core/src/main/java/com/synerset/unitility/unitsystem/util/PhysicalQuantityDefaultParsingFactory.java)**:
- Add import for your quantity class
- Add entry in `immutableParsingRegistry`: `Map.entry(YourQuantity.class, YourQuantity::of)`
- Add entry in `immutableDefaultUnitRegistry`: `Map.entry(YourQuantity.class, YourQuantityUnits.BASE_UNIT)`

**b) [`SupportedQuantitiesRegistry.java`](unitility-core/src/main/java/com/synerset/unitility/unitsystem/util/SupportedQuantitiesRegistry.java)**:
- Add import for your quantity and units classes
- Add entry: `Map.entry(YourQuantity.class, () -> Arrays.asList(YourQuantityUnits.values()))`

### Step 5: Create JPA/Hibernate Converter
Create `<category>/YourQuantityPlainSIConverter.java` in [`unitility-persistence/src/main/java/com/synerset/unitility/persistence/converter/plainsivalue/`](unitility-persistence/src/main/java/com/synerset/unitility/persistence/converter/plainsivalue/):
```java
package com.synerset.unitility.persistence.converter.plainsivalue.<category>;

import com.synerset.unitility.unitsystem.<category>.YourQuantity;
import com.synerset.unitility.unitsystem.<category>.YourQuantityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class YourQuantityPlainSIConverter implements AttributeConverter<YourQuantity, Double> {
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

### Step 6: Create Tests
Create `YourQuantityTest.java` in [`unitility-core/src/test/java/com/synerset/unitility/unitsystem/<category>/`](unitility-core/src/test/java/com/synerset/unitility/unitsystem/common):
- Test base unit identification
- Test conversions between all units
- Test equality comparisons across different units
- Use Given/When/Then structure with AssertJ assertions

### Summary of Files to Create/Modify
| File Type | Location Pattern |
|-----------|------------------|
| Unit Interface | `unitility-core/src/main/java/com/synerset/unitility/unitsystem/<category>/YourQuantityUnit.java` |
| Units Enum | `unitility-core/src/main/java/com/synerset/unitility/unitsystem/<category>/YourQuantityUnits.java` |
| Quantity Class | `unitility-core/src/main/java/com/synerset/unitility/unitsystem/<category>/YourQuantity.java` |
| JPA Converter | `unitility-persistence/src/main/java/com/synerset/unitility/persistence/converter/plainsivalue/<category>/YourQuantityPlainSIConverter.java` |
| Tests | `unitility-core/src/test/java/com/synerset/unitility/unitsystem/<category>/YourQuantityTest.java` |
| Registry Update 1 | [`PhysicalQuantityDefaultParsingFactory.java`](unitility-core/src/main/java/com/synerset/unitility/unitsystem/util/PhysicalQuantityDefaultParsingFactory.java) |
| Registry Update 2 | [`SupportedQuantitiesRegistry.java`](unitility-core/src/main/java/com/synerset/unitility/unitsystem/util/SupportedQuantitiesRegistry.java) |
