package com.synerset.unitility.unitsystem.oscillation;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

// NOTE: This implementation assumes the existence of 'FrequencyUnit', 'FrequencyUnits'
// and a 'Constants' class with KILO, MEGA, GIGA, and SECONDS_IN_MINUTE fields.
public class Frequency implements CalculableQuantity<FrequencyUnit, Frequency> {

    private final double value;
    private final double baseValue;
    private final FrequencyUnit unitType;

    public Frequency(double value, FrequencyUnit unitType) {
        this.value = value;
        // Assuming FrequencyUnits.HERTZ is the base unit
        if (unitType == null) {
            unitType = FrequencyUnits.HERTZ;
        }
        this.unitType = unitType;
        // Assuming FrequencyUnit has toValueInBaseUnit method
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Frequency of(double value, FrequencyUnit unit) {
        return new Frequency(value, unit);
    }

    public static Frequency of(double value, String unitSymbol) {
        // Assuming FrequencyUnits has fromSymbol method
        FrequencyUnit resolvedUnit = FrequencyUnits.fromSymbol(unitSymbol);
        return new Frequency(value, resolvedUnit);
    }

    // Missing factory methods
    public static Frequency ofHertz(double value) {
        return new Frequency(value, FrequencyUnits.HERTZ);
    }

    public static Frequency ofKiloHertz(double value) {
        return new Frequency(value, FrequencyUnits.KILOHERTZ);
    }

    public static Frequency ofMegaHertz(double value) {
        return new Frequency(value, FrequencyUnits.MEGAHERTZ);
    }

    public static Frequency ofGigaHertz(double value) {
        return new Frequency(value, FrequencyUnits.GIGAHERTZ);
    }

    public static Frequency ofCyclesPerMinute(double value) {
        return new Frequency(value, FrequencyUnits.CYCLES_PER_MINUTE);
    }
    // End of missing factory methods

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public FrequencyUnit getUnit() {
        return unitType;
    }

    @Override
    public Frequency toBaseUnit() {
        // Assuming FrequencyUnit has toValueInBaseUnit and getBaseUnit methods
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Frequency toUnit(FrequencyUnit targetUnit) {
        // Assuming FrequencyUnit has toValueInBaseUnit and fromValueInBaseUnit methods
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return Frequency.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Frequency toUnit(String targetUnit) {
        // Assuming FrequencyUnits has fromSymbol method
        FrequencyUnit resolvedUnit = FrequencyUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Frequency withValue(double value) {
        return Frequency.of(value, unitType);
    }

    // Missing specific toUnit methods (Conversion methods)
    public Frequency toHertz() {
        return toUnit(FrequencyUnits.HERTZ);
    }

    public Frequency toKiloHertz() {
        return toUnit(FrequencyUnits.KILOHERTZ);
    }

    public Frequency toMegaHertz() {
        return toUnit(FrequencyUnits.MEGAHERTZ);
    }

    public Frequency toGigaHertz() {
        return toUnit(FrequencyUnits.GIGAHERTZ);
    }

    public Frequency toCyclesPerMinute() {
        return toUnit(FrequencyUnits.CYCLES_PER_MINUTE);
    }
    // End of missing specific toUnit methods

    // Missing specific getInUnit methods (Getters)
    public double getInHertz() {
        return getInUnit(FrequencyUnits.HERTZ);
    }

    public double getInKiloHertz() {
        return getInUnit(FrequencyUnits.KILOHERTZ);
    }

    public double getInMegaHertz() {
        return getInUnit(FrequencyUnits.MEGAHERTZ);
    }

    public double getInGigaHertz() {
        return getInUnit(FrequencyUnits.GIGAHERTZ);
    }

    public double getInCyclesPerMinute() {
        return getInUnit(FrequencyUnits.CYCLES_PER_MINUTE);
    }
    // End of missing specific getInUnit methods

    // Standard overridden methods (equals, hashCode, toString)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frequency inputQuantity = (Frequency) o;
        // Comparison in base unit for value, base unit for unit type
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        // Hash based on base value and base unit type
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        // Using Angle's toString logic as a reference
        String separator = " ";
        // Assuming FrequencyUnit has getSymbol method
        return "Frequency{" + value + separator + unitType.getSymbol() + '}';
    }
}