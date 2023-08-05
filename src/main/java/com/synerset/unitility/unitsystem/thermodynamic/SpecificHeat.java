package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class SpecificHeat implements PhysicalQuantity<SpecificHeat> {
    private final double value;
    private final Unit<SpecificHeat> unit;

    private SpecificHeat(double value, Unit<SpecificHeat> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<SpecificHeat> getUnit() {
        return unit;
    }

    @Override
    public SpecificHeat toBaseUnit() {
        double valueInJoulePerKiloGramKelvin = unit.toBaseUnit(value);
        return SpecificHeat.of(valueInJoulePerKiloGramKelvin, SpecificHeatUnits.JOULES_PER_KILOGRAM_KELVIN);
    }

    @Override
    public SpecificHeat toUnit(Unit<SpecificHeat> targetUnit) {
        double valueInJoulePerKiloGramKelvin = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInJoulePerKiloGramKelvin);
        return SpecificHeat.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public PhysicalQuantity<SpecificHeat> createNewWithValue(double value) {
        return SpecificHeat.of(value, unit);
    }

    // Custom value getters
    public double getValueOfKiloJoulesPerKilogramKelvin() {
        if (unit == SpecificHeatUnits.KILOJOULES_PER_KILOGRAM_KELVIN) {
            return value;
        }
        return toUnit(SpecificHeatUnits.KILOJOULES_PER_KILOGRAM_KELVIN).getValue();
    }

    // Custom converter methods, for most popular units
    public SpecificHeat toJoulePerKiloGramKelvin() {
        return toUnit(SpecificHeatUnits.JOULES_PER_KILOGRAM_KELVIN);
    }

    public SpecificHeat toKiloJoulePerKiloGramKelvin() {
        return toUnit(SpecificHeatUnits.KILOJOULES_PER_KILOGRAM_KELVIN);
    }

    public SpecificHeat toBTUPerPoundFahrenheit() {
        return toUnit(SpecificHeatUnits.BTU_PER_POUND_FAHRENHEIT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificHeat that = (SpecificHeat) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "SpecificHeat{" + value + " " + unit.getSymbol() + '}';
    }

    public static SpecificHeat of(double value, Unit<SpecificHeat> unit) {
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

}
