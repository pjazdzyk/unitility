package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class SpecificHeat implements PhysicalQuantity<SpecificHeatUnits> {
    private final double value;
    private final double baseValue;
    private final SpecificHeatUnits unit;

    public SpecificHeat(double value, SpecificHeatUnits unit) {
        this.value = value;
        this.unit = unit;
        this.baseValue = unit.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static SpecificHeat of(double value, SpecificHeatUnits unit) {
        return new SpecificHeat(value, unit);
    }

    public static SpecificHeat ofJoulePerKiloGramKelvin(double value) {
        return new SpecificHeat(value, SpecificHeatUnits.JOULES_PER_KILOGRAM_KELVIN);
    }

    public static SpecificHeat ofKiloJoulePerKiloGramKelvin(double value) {
        return new SpecificHeat(value, SpecificHeatUnits.KILOJOULES_PER_KILOGRAM_KELVIN);
    }

    public static SpecificHeat ofBTUPerPoundFahrenheit(double value) {
        return new SpecificHeat(value, SpecificHeatUnits.BTU_PER_POUND_FAHRENHEIT);
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public SpecificHeatUnits getUnit() {
        return unit;
    }

    @Override
    public SpecificHeat toBaseUnit() {
        double valueInJoulePerKiloGramKelvin = unit.toValueInBaseUnit(value);
        return SpecificHeat.of(valueInJoulePerKiloGramKelvin, SpecificHeatUnits.JOULES_PER_KILOGRAM_KELVIN);
    }

    @Override
    public SpecificHeat toUnit(SpecificHeatUnits targetUnit) {
        double valueInJoulePerKiloGramKelvin = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInJoulePerKiloGramKelvin);
        return SpecificHeat.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SpecificHeat createNewWithValue(double value) {
        return SpecificHeat.of(value, unit);
    }

    // Convert to target unit
    public SpecificHeat toJoulePerKiloGramKelvin() {
        return toUnit(SpecificHeatUnits.JOULES_PER_KILOGRAM_KELVIN);
    }

    public SpecificHeat toKiloJoulePerKiloGramKelvin() {
        return toUnit(SpecificHeatUnits.KILOJOULES_PER_KILOGRAM_KELVIN);
    }

    public SpecificHeat toBTUPerPoundFahrenheit() {
        return toUnit(SpecificHeatUnits.BTU_PER_POUND_FAHRENHEIT);
    }

    // Get value in target unit
    public double getInJoulePerKiloGramKelvin() {
        return getIn(SpecificHeatUnits.JOULES_PER_KILOGRAM_KELVIN);
    }

    public double getInKiloJoulesPerKiloGramKelvin() {
        return getIn(SpecificHeatUnits.KILOJOULES_PER_KILOGRAM_KELVIN);
    }

    public double getInBTUsPerPoundFahrenheit() {
        return getIn(SpecificHeatUnits.BTU_PER_POUND_FAHRENHEIT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificHeat inputQuantity = (SpecificHeat) o;
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unit.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unit.getBaseUnit());
    }

    @Override
    public String toString() {
        return "SpecificHeat{" + value + " " + unit.getSymbol() + '}';
    }

}
