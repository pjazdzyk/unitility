---
name: unitility
description: >
  Use this skill whenever working with the UNITILITY Java library (com.synerset:unitility-core) for type-safe physical quantities in Java 21+.
  Triggers include: creating quantities (Temperature, Pressure, Distance, Mass, Velocity, Flow, Humidity, etc.),
  unit conversions between SI and Imperial units, arithmetic on physical quantities, comparing or sorting quantities,
  engineering-format output, or integrating Unitility with Spring Boot / Quarkus. Also trigger when the user mentions
  PhysicalQuantity, unit-safe calculations in Java, HVAC/thermodynamic calculations with units, or any quantity class name.
---

# Unitility Skill — Type-Safe Physical Quantities for Java

Unitility is a Java 21 library for type-safe physical quantities. Every quantity encapsulates **value + unit** and is immutable (thread-safe).

## Instructions

Follow these rules when generating code with Unitility:

### 1. Dependency Setup
Always include the Maven dependency first if the project does not already have it:

```xml
<dependency>
    <groupId>com.synerset</groupId>
    <artifactId>unitility-core</artifactId>
    <version>4.0.1</version>
</dependency>
```

### 2. Creating Quantities — Always Use Static Factory Methods
Never parse strings in business logic. Always use typed factory methods:

```java
Temperature t = Temperature.ofCelsius(20.5);
Pressure p = Pressure.ofPascal(101325);
Distance d = Distance.ofKilometers(100);
```

**Pattern:** `QuantityClass.of<UnitName>(double value)` — e.g., `ofCelsius()`, `ofKelvins()`, `ofPascal()`, `ofMeters()`.

### 3. Converting Units
Each quantity type has a **base SI unit**. Use `toUnit()` for target conversion, `toBaseUnit()` for SI:

```java
Temperature temp = Temperature.ofCelsius(20.5);
Temperature inF  = temp.toUnit(TemperatureUnits.FAHRENHEIT);   // {68.90 °F}
Temperature inK  = temp.toBaseUnit();                           // {293.15 K}

// Extract raw values
double c = temp.getInCelsius();    // 20.5
```

### 4. Arithmetic — Same-Type Only
- `add()` / `subtract()`: result unit = **augend** (right operand) unit
- `multiply(double)` / `divide(double)`: scalar operations, same type preserved
- Cross-type multiply/divide returns `double`

```java
Temperature t1 = Temperature.ofCelsius(20);
Temperature t2 = Temperature.ofKelvins(293.15);  // = 20 °C
Temperature sum = t1.add(t2);                     // {40.00 °C} — augend unit
```

### 5. Comparison & Logic
All comparisons convert to base units first. Only **same-type** comparison is supported:

```java
cold.isLowerThan(warm);
warm.isGreaterThan(cold);
c1.equalsWithPrecision(k1, 0.001);   // epsilon-based equality
t.isPositive(); t.isNegativeOrZero(); t.isCloseToZero();
```

### 6. Sorting
Quantities implement `Comparable` — sorting uses base-unit values:

```java
Arrays.sort(temps);  // ascending by SI base value
```

### 7. Rounding & Formatting
- `roundHalfEven(n)` keeps **n significant digits**, NOT n decimal places
- `toEngineeringFormat(precision)` outputs with engineering prefixes: `"10 [m]"`

```java
ThermalConductivity tc = ThermalConductivity.ofWattsPerMeterKelvin(0.00366);
tc.roundHalfEven(2);  // 0.0037 (significant digits)
big.toEngineeringFormat(3);  // "10 [m]"
```

### 8. Naming Conventions
- **Quantity Class:** `Temperature`, `Pressure`, `Distance` …
- **Unit Enum:** `<Quantity>Units` — e.g., `TemperatureUnits.FAHRENHEIT`
- **Unit Interface:** `<Quantity>Unit` — e.g., `TemperatureUnit`

Full class list is in [`reference.md`](reference.md).

## Common Mistakes to Avoid

| ❌ Wrong | ✅ Correct |
|---------|-----------|
| `new Temperature(25, ...)` or string parsing | `Temperature.ofCelsius(25)` |
| Assuming `add()` preserves the addend's unit | Result unit = augend (right operand) unit |
| `roundHalfEven(3)` → 3 decimal places | It keeps 3 **significant** digits |
| Comparing different quantity types | Only same-type comparison supported |

## Examples

### HVAC: Coil Air Temperature Rise
```java
Temperature supply = Temperature.ofCelsius(22.0);
Temperature returnTemp = Temperature.ofCelsius(18.5);
double delta = supply.getInCelsius() - returnTemp.getInCelsius();  // 3.5 °C

Temperature setpoint = Temperature.ofFahrenheit(75.0);
setpoint = setpoint.toUnit(TemperatureUnits.CELSIUS);  // {23.89 °C}
```

### Flow + Pressure → Power Estimate
```java
VolumetricFlow flow = VolumetricFlow.ofLitersPerSecond(5.0);
Pressure deltaP = Pressure.ofKilopascal(120.0);

// Hydraulic power ≈ Q × ΔP (convert to SI first)
double flowSI = flow.getInCubicMetersPerSecond();  // m³/s
double pSI = deltaP.getInPascals();                // Pa
double powerWatts = flowSI * pSI;                  // Watts
```

### Engineering Output Formatting
```java
Distance d = Distance.ofMeters(0.000123678);
System.out.println(d.toEngineeringFormat(3));  // "0.000124 [m]"
```

## Reference
For the complete list of quantity classes, base units, and supported symbols, see [`reference.md`](reference.md).
