package com.synerset.unitility.unitsystem.angle;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public final class Angle implements PhysicalQuantity<Angle> {

    public static final byte TO_STRING_PRECISION = 3;
    private final double value;
    private final Unit<Angle> unit;

    private Angle(double value, Unit<Angle> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Angle> getUnit() {
        return unit;
    }

    @Override
    public Angle toBaseUnit() {
        double valueInRadians = unit.toBaseUnit(value);
        return Angle.of(valueInRadians, AngleUnits.RADIANS);
    }

    @Override
    public Angle toUnit(Unit<Angle> targetUnit) {
        double valueInRadians = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInRadians);
        return Angle.of(valueInTargetUnit, targetUnit);
    }

    // Custom converter methods for most popular units
    public Angle toRadians() {
        return toUnit(AngleUnits.RADIANS);
    }

    public Angle toDegrees() {
        return toUnit(AngleUnits.DEGREES);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Angle angle = (Angle) o;
        return Double.compare(angle.value, value) == 0 && Objects.equals(unit, angle.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return ValueFormatter.formatDoubleToRelevantPrecision(value, TO_STRING_PRECISION) + " " + unit.getSymbol();
    }

    public static Angle of(double value, Unit<Angle> unit) {
        return new Angle(value, unit);
    }

    public static Angle ofRadians(double value) {
        return new Angle(value, AngleUnits.RADIANS);
    }

    public static Angle ofDegrees(double value) {
        return new Angle(value, AngleUnits.DEGREES);
    }

}