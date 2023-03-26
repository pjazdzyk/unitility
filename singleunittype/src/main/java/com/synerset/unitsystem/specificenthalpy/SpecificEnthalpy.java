package com.synerset.unitsystem.specificenthalpy;

import com.synerset.unitsystem.PhysicalQuantity;
import com.synerset.unitsystem.Unit;

import java.util.Objects;

public final class SpecificEnthalpy implements PhysicalQuantity<SpecificEnthalpy> {

    public static final byte TO_STRING_PRECISION = 3;
    private final double value;
    private final Unit<SpecificEnthalpy> unit;

    private SpecificEnthalpy(double value, Unit<SpecificEnthalpy> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<SpecificEnthalpy> getUnit() {
        return unit;
    }

    @Override
    public SpecificEnthalpy toBaseUnit() {
        double valueInJoulePerKilogram = unit.toBaseUnit(value);
        return SpecificEnthalpy.of(valueInJoulePerKilogram, SpecificEnthalpyUnits.JOULE_PER_KILOGRAM);
    }

    @Override
    public SpecificEnthalpy toUnit(Unit<SpecificEnthalpy> targetUnit) {
        double valueInJoulePerKilogram = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInJoulePerKilogram);
        return SpecificEnthalpy.of(valueInTargetUnit, targetUnit);
    }

    // Custom converter methods, for most popular units
    public SpecificEnthalpy toJoulePerKiloGram() {
        return toUnit(SpecificEnthalpyUnits.JOULE_PER_KILOGRAM);
    }

    public SpecificEnthalpy toKiloJoulePerKiloGram() {
        return toUnit(SpecificEnthalpyUnits.KILO_JOULE_PER_KILOGRAM);
    }

    public SpecificEnthalpy toBTUPerPound() {
        return toUnit(SpecificEnthalpyUnits.BTU_PER_POUND);
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
        return String.format("%." + TO_STRING_PRECISION + "f %s", value, unit.getSymbol());
    }

    public static SpecificEnthalpy of(double value, Unit<SpecificEnthalpy> unit) {
        return new SpecificEnthalpy(value, unit);
    }

    public static SpecificEnthalpy ofJoulePerKiloGram(double value) {
        return new SpecificEnthalpy(value, SpecificEnthalpyUnits.JOULE_PER_KILOGRAM);
    }

    public static SpecificEnthalpy ofKiloJoulePerKiloGram(double value) {
        return new SpecificEnthalpy(value, SpecificEnthalpyUnits.KILO_JOULE_PER_KILOGRAM);
    }

    public static SpecificEnthalpy ofBTUPerPound(double value) {
        return new SpecificEnthalpy(value, SpecificEnthalpyUnits.BTU_PER_POUND);
    }
}