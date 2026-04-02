package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class SpecificEntropy implements CalculableQuantity<SpecificEntropyUnit, SpecificEntropy> {
    private final double value;
    private final double baseValue;
    private final SpecificEntropyUnit unitType;

    public SpecificEntropy(double value, SpecificEntropyUnit unitType) {
        this.value = value;
        if (unitType == null) { unitType = SpecificEntropyUnits.JOULE_PER_KILOGRAM_KELVIN; }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static SpecificEntropy of(double value, SpecificEntropyUnit unit) {
        return new SpecificEntropy(value, unit);
    }

    public static SpecificEntropy of(double value, String unitSymbol) {
        return new SpecificEntropy(value, SpecificEntropyUnits.fromSymbol(unitSymbol));
    }

    public static SpecificEntropy ofJoulePerKilogramKelvin(double value) {
        return new SpecificEntropy(value, SpecificEntropyUnits.JOULE_PER_KILOGRAM_KELVIN);
    }

    public static SpecificEntropy ofKiloJoulePerKilogramKelvin(double value) {
        return new SpecificEntropy(value, SpecificEntropyUnits.KILOJOULE_PER_KILOGRAM_KELVIN);
    }

    public static SpecificEntropy ofBTUPerPoundRankine(double value) {
        return new SpecificEntropy(value, SpecificEntropyUnits.BTU_PER_POUND_RANKINE);
    }

    public static SpecificEntropy ofMilliJoulePerGramKelvin(double value) {
        return new SpecificEntropy(value, SpecificEntropyUnits.MILLIJOULE_PER_GRAM_KELVIN);
    }

    public static SpecificEntropy ofMegaJoulePerTonneKelvin(double value) {
        return new SpecificEntropy(value, SpecificEntropyUnits.MEGAJOULE_PER_TONNE_KELVIN);
    }

    public static SpecificEntropy ofBTUPerPoundFahrenheit(double value) {
        return new SpecificEntropy(value, SpecificEntropyUnits.BTU_PER_POUND_FAHRENHEIT);
    }

    // Implement CalculableQuantity interface methods
    @Override public double getValue() { return value; }
    @Override public double getBaseValue() { return baseValue; }
    @Override public SpecificEntropyUnit getUnit() { return unitType; }
    
    @Override public SpecificEntropy toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }
    
    @Override public SpecificEntropy toUnit(SpecificEntropyUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }
    
    @Override public SpecificEntropy toUnit(String targetUnit) {
        return toUnit(SpecificEntropyUnits.fromSymbol(targetUnit));
    }
    
    @Override public SpecificEntropy withValue(double value) {
        return of(value, unitType);
    }

    // Conversion methods
    public SpecificEntropy toJoulePerKilogramKelvin() { return toUnit(SpecificEntropyUnits.JOULE_PER_KILOGRAM_KELVIN); }
    public SpecificEntropy toKiloJoulePerKilogramKelvin() { return toUnit(SpecificEntropyUnits.KILOJOULE_PER_KILOGRAM_KELVIN); }
    public SpecificEntropy toMilliJoulePerGramKelvin() { return toUnit(SpecificEntropyUnits.MILLIJOULE_PER_GRAM_KELVIN); }
    public SpecificEntropy toMegaJoulePerTonneKelvin() { return toUnit(SpecificEntropyUnits.MEGAJOULE_PER_TONNE_KELVIN); }
    public SpecificEntropy toBTUPerPoundRankine() { return toUnit(SpecificEntropyUnits.BTU_PER_POUND_RANKINE); }
    public SpecificEntropy toBTUPerPoundFahrenheit() { return toUnit(SpecificEntropyUnits.BTU_PER_POUND_FAHRENHEIT); }

    // Value getter methods
    public double getInJoulesPerKilogramKelvin() { return getInUnit(SpecificEntropyUnits.JOULE_PER_KILOGRAM_KELVIN); }
    public double getInKiloJoulesPerKilogramKelvin() { return getInUnit(SpecificEntropyUnits.KILOJOULE_PER_KILOGRAM_KELVIN); }
    public double getInMilliJoulesPerGramKelvin() { return getInUnit(SpecificEntropyUnits.MILLIJOULE_PER_GRAM_KELVIN); }
    public double getInMegaJoulesPerTonneKelvin() { return getInUnit(SpecificEntropyUnits.MEGAJOULE_PER_TONNE_KELVIN); }
    public double getInBTUsPerPoundRankine() { return getInUnit(SpecificEntropyUnits.BTU_PER_POUND_RANKINE); }
    public double getInBTUsPerPoundFahrenheit() { return getInUnit(SpecificEntropyUnits.BTU_PER_POUND_FAHRENHEIT); }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecificEntropy)) return false;
        SpecificEntropy other = (SpecificEntropy) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override public int hashCode() { return Objects.hash(baseValue, unitType.getBaseUnit()); }

    @Override public String toString() {
        return "SpecificEntropy{" + value + " " + unitType.getSymbol() + "}";
    }
}