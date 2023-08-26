package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class SpecificEnthalpy implements PhysicalQuantity<SpecificEnthalpy> {
    private final double value;
    private final double baseValue;
    private final Unit<SpecificEnthalpy> unit;

    public SpecificEnthalpy(double value, Unit<SpecificEnthalpy> unit) {
        this.value = value;
        this.unit = unit;
        this.baseValue = unit.toValueInBaseUnit(value);
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
    public Unit<SpecificEnthalpy> getUnit() {
        return unit;
    }

    @Override
    public SpecificEnthalpy toBaseUnit() {
        double valueInJoulePerKilogram = unit.toValueInBaseUnit(value);
        return SpecificEnthalpy.of(valueInJoulePerKilogram, SpecificEnthalpyUnits.JOULE_PER_KILOGRAM);
    }

    @Override
    public SpecificEnthalpy toUnit(Unit<SpecificEnthalpy> targetUnit) {
        double valueInJoulePerKilogram = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInJoulePerKilogram);
        return SpecificEnthalpy.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public SpecificEnthalpy createNewWithValue(double value) {
        return SpecificEnthalpy.of(value, unit);
    }

    // Convert to target unit
    public double getInJoulesPerKiloGram() {
        return getIn(SpecificEnthalpyUnits.JOULE_PER_KILOGRAM);
    }

    public double getInKiloJoulesPerKiloGram() {
        return getIn(SpecificEnthalpyUnits.KILOJOULE_PER_KILOGRAM);
    }

    public double getInBTUsPerPound() {
        return getIn(SpecificEnthalpyUnits.BTU_PER_POUND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificEnthalpy that = (SpecificEnthalpy) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "SpecificEnthalpy{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static SpecificEnthalpy of(double value, Unit<SpecificEnthalpy> unit) {
        return new SpecificEnthalpy(value, unit);
    }

    public static SpecificEnthalpy ofJoulePerKiloGram(double value) {
        return new SpecificEnthalpy(value, SpecificEnthalpyUnits.JOULE_PER_KILOGRAM);
    }

    public static SpecificEnthalpy ofKiloJoulePerKiloGram(double value) {
        return new SpecificEnthalpy(value, SpecificEnthalpyUnits.KILOJOULE_PER_KILOGRAM);
    }

    public static SpecificEnthalpy ofBTUPerPound(double value) {
        return new SpecificEnthalpy(value, SpecificEnthalpyUnits.BTU_PER_POUND);
    }
}