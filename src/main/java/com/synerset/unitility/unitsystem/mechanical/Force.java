package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class Force implements PhysicalQuantity<Force> {

    private final double value;
    private final double baseValue;
    private final Unit<Force> unit;

    private Force(double value, Unit<Force> unit) {
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
    public Unit<Force> getUnit() {
        return unit;
    }

    @Override
    public Force toBaseUnit() {
        double valueInNewtons = unit.toValueInBaseUnit(value);
        return Force.of(valueInNewtons, ForceUnits.NEWTON);
    }

    @Override
    public Force toUnit(Unit<Force> targetUnit) {
        double valueInNewtons = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInNewtons);
        return Force.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Force createNewWithValue(double value) {
        return Force.of(value, unit);
    }

    // Convert to target unit
    public double getInNewtons() {
        return getIn(ForceUnits.NEWTON);
    }

    public double getInKiloNewtons() {
        return getIn(ForceUnits.KILONEWTON);
    }

    public double getInKiloponds() {
        return getIn(ForceUnits.KILOPOND);
    }

    public double getInDynes() {
        return getIn(ForceUnits.DYNE);
    }

    public double getInPoundsForce() {
        return getIn(ForceUnits.POUND_FORCE);
    }

    public double getInPoundals() {
        return getIn(ForceUnits.POUNDAL);
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
        return "Force{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
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