package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class SpecificHeat implements PhysicalQuantity<SpecificHeatUnit> {
    private final double value;
    private final double baseValue;
    private final SpecificHeatUnit unitType;

    public SpecificHeat(double value, SpecificHeatUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static SpecificHeat of(double value, SpecificHeatUnit unit) {
        return new SpecificHeat(value, unit);
    }

    public static SpecificHeat of(double value, String unitSymbol) {
        SpecificHeatUnit resolvedUnit = SpecificHeatUnits.fromSymbol(unitSymbol);
        return new SpecificHeat(value, resolvedUnit);
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
    public SpecificHeatUnit getUnitType() {
        return unitType;
    }

    @Override
    public SpecificHeat toBaseUnit() {
        double valueInJoulePerKiloGramKelvin = unitType.toValueInBaseUnit(value);
        return SpecificHeat.of(valueInJoulePerKiloGramKelvin, SpecificHeatUnits.JOULES_PER_KILOGRAM_KELVIN);
    }

    @Override
    public SpecificHeat toUnit(SpecificHeatUnit targetUnit) {
        double valueInJoulePerKiloGramKelvin = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInJoulePerKiloGramKelvin);
        return SpecificHeat.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SpecificHeat withValue(double value) {
        return SpecificHeat.of(value, unitType);
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
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "SpecificHeat{" + value + " " + unitType.getSymbol() + '}';
    }

}
