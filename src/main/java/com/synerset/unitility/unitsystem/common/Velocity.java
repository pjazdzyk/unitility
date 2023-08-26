package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class Velocity implements PhysicalQuantity<Velocity> {

    private final double value;
    private final double baseValue;
    private final Unit<Velocity> unit;

    private Velocity(double value, Unit<Velocity> unit) {
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
    public Unit<Velocity> getUnit() {
        return unit;
    }

    @Override
    public Velocity toBaseUnit() {
        double valueInMetersPerSecond = unit.toValueInBaseUnit(value);
        return Velocity.of(valueInMetersPerSecond, VelocityUnits.METER_PER_SECOND);
    }

    @Override
    public Velocity toUnit(Unit<Velocity> targetUnit) {
        double valueInMetersPerSecond = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInMetersPerSecond);
        return Velocity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Velocity createNewWithValue(double value) {
        return Velocity.of(value, unit);
    }

    // Convert to target unit
    public double toMetersPerSecond() {
        return getIn(VelocityUnits.METER_PER_SECOND);
    }

    public double toCentimetersPerSeconds() {
        return getIn(VelocityUnits.CENTIMETER_PER_SECOND);
    }

    public double toKilometersPerHours() {
        return getIn(VelocityUnits.KILOMETER_PER_HOUR);
    }

    public double toInchesPerSeconds() {
        return getIn(VelocityUnits.INCH_PER_SECOND);
    }

    public double toFeetPerSeconds() {
        return getIn(VelocityUnits.FEET_PER_SECOND);
    }

    public double toMilesPerHours() {
        return getIn(VelocityUnits.MILES_PER_HOUR);
    }

    public double toKnots() {
        return getIn(VelocityUnits.KNOTS);
    }

    public double toMach() {
        return getIn(VelocityUnits.MACH);
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
        return "Velocity{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
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
