package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public final class Force implements PhysicalQuantity<Force> {

    private final double value;
    private final Unit<Force> unit;

    private Force(double value, Unit<Force> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Force> getUnit() {
        return unit;
    }

    @Override
    public Force toBaseUnit() {
        double valueInNewtons = unit.toBaseUnit(value);
        return Force.of(valueInNewtons, ForceUnits.NEWTON);
    }

    @Override
    public Force toUnit(Unit<Force> targetUnit) {
        double valueInNewtons = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInNewtons);
        return Force.of(valueInTargetUnit, targetUnit);
    }

    // Custom converter methods, for most popular units
    public Force toNewtons() {
        return toUnit(ForceUnits.NEWTON);
    }

    public Force toKiloNewtons() {
        return toUnit(ForceUnits.KILONEWTON);
    }

    public Force toKiloponds() {
        return toUnit(ForceUnits.KILOPOND);
    }

    public Force toDynes() {
        return toUnit(ForceUnits.DYNE);
    }

    public Force toPoundForce() {
        return toUnit(ForceUnits.POUND_FORCE);
    }

    public Force toPoundal() {
        return toUnit(ForceUnits.POUNDAL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Force that = (Force) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return ValueFormatter.formatDoubleToRelevantPrecision(value, TO_STRING_PRECISION) + " " + unit.getSymbol();
    }

    public static Force of(double value, Unit<Force> unit) {
        return new Force(value, unit);
    }

    public static Force ofNewtons(double value) {
        return new Force(value, ForceUnits.NEWTON);
    }

    public static Force ofKiloNewtons(double value) {
        return new Force(value, ForceUnits.KILONEWTON);
    }

    public static Force ofKiloponds(double value) {
        return new Force(value, ForceUnits.KILOPOND);
    }

    public static Force ofDynes(double value) {
        return new Force(value, ForceUnits.DYNE);
    }

    public static Force ofPoundForce(double value) {
        return new Force(value, ForceUnits.POUND_FORCE);
    }

    public static Force ofPoundal(double value) {
        return new Force(value, ForceUnits.POUNDAL);
    }
}