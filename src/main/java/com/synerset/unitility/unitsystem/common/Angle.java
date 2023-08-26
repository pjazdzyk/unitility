package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public class Angle implements PhysicalQuantity<Angle> {

    private final double value;
    private final double baseValue;
    private final Unit<Angle> unit;

    public Angle(double value, Unit<Angle> unit) {
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
    public Unit<Angle> getUnit() {
        return unit;
    }

    @Override
    public Angle toBaseUnit() {
        double degrees = unit.toValueInBaseUnit(value);
        return Angle.of(degrees, AngleUnits.DEGREES);
    }

    @Override
    public Angle toUnit(Unit<Angle> targetUnit) {
        double valueInDegrees = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInDegrees);
        return Angle.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Angle createNewWithValue(double value) {
        return Angle.of(value, unit);
    }

    // Convert to target unit
    public double getInRadians() {
        return getIn(AngleUnits.RADIANS);
    }

    public double getInDegrees() {
        return getIn(AngleUnits.DEGREES);
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
        return "Angle{" + value + " " + unit.getSymbol() + '}';
    }

    @Override
    public String toStringWithRelevantDigits(int relevantDigits) {
        String separator = unit == AngleUnits.DEGREES ? "" : " ";
        return ValueFormatter.formatDoubleToRelevantDigits(value, relevantDigits) + separator + unit.getSymbol();
    }

    // Static factory methods
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