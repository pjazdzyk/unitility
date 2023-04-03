package com.synerset.unitility.unitsystem.velocity;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public final class Velocity implements PhysicalQuantity<Velocity> {

    private final double value;
    private final Unit<Velocity> unit;

    private Velocity(double value, Unit<Velocity> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Velocity> getUnit() {
        return unit;
    }

    @Override
    public Velocity toBaseUnit() {
        double valueInMetersPerSecond = unit.toBaseUnit(value);
        return Velocity.of(valueInMetersPerSecond, VelocityUnits.METER_PER_SECOND);
    }

    @Override
    public Velocity toUnit(Unit<Velocity> targetUnit) {
        double valueInMetersPerSecond = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInMetersPerSecond);
        return Velocity.of(valueInTargetUnit, targetUnit);
    }

    // Custom converter methods, for most popular units
    public Velocity toMetersPerSecond() {
        return toUnit(VelocityUnits.METER_PER_SECOND);
    }

    public Velocity toCentimetersPerSecond() {
        return toUnit(VelocityUnits.CENTIMETER_PER_SECOND);
    }

    public Velocity toKilometersPerHour() {
        return toUnit(VelocityUnits.KILOMETER_PER_HOUR);
    }

    public Velocity toInchesPerSecond() {
        return toUnit(VelocityUnits.INCH_PER_SECOND);
    }

    public Velocity toFeetPerSecond() {
        return toUnit(VelocityUnits.FEET_PER_SECOND);
    }

    public Velocity toMilesPerHour() {
        return toUnit(VelocityUnits.MILES_PER_HOUR);
    }

    public Velocity toKnots() {
        return toUnit(VelocityUnits.KNOTS);
    }

    public Velocity toMach() {
        return toUnit(VelocityUnits.MACH);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Velocity velocity = (Velocity) o;
        return Double.compare(velocity.value, value) == 0 && unit.equals(velocity.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return ValueFormatter.formatDoubleToRelevantPrecision(value, TO_STRING_PRECISION) + " " + unit.getSymbol();
    }

    public static Velocity of(double value, Unit<Velocity> unit) {
        return new Velocity(value, unit);
    }

    public static Velocity ofMetersPerSecond(double value) {
        return new Velocity(value, VelocityUnits.METER_PER_SECOND);
    }

    public static Velocity ofCentimetersPerSecond(double value) {
        return new Velocity(value, VelocityUnits.CENTIMETER_PER_SECOND);
    }

    public static Velocity ofKilometersPerHour(double value) {
        return new Velocity(value, VelocityUnits.KILOMETER_PER_HOUR);
    }

    public static Velocity ofInchesPerSecond(double value) {
        return new Velocity(value, VelocityUnits.INCH_PER_SECOND);
    }

    public static Velocity ofFeetPerSecond(double value) {
        return new Velocity(value, VelocityUnits.FEET_PER_SECOND);
    }

    public static Velocity ofMilesPerHour(double value) {
        return new Velocity(value, VelocityUnits.MILES_PER_HOUR);
    }

    public static Velocity ofKnots(double value) {
        return new Velocity(value, VelocityUnits.KNOTS);
    }

    public static Velocity ofMach(double value) {
        return new Velocity(value, VelocityUnits.MACH);
    }

}
