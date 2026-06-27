package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/**
 * Time / duration. SI base unit is the second. Used for transient simulation time axes (orbital
 * periods, time steps), among others.
 */
public class Time implements CalculableQuantity<TimeUnit, Time> {

    public static final Time PHYSICAL_MIN_LIMIT = Time.ofSeconds(0);

    private final double value;
    private final double baseValue;
    private final TimeUnit unitType;

    public Time(double value, TimeUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = TimeUnits.SECOND;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Time of(double value, TimeUnit unit) {
        return new Time(value, unit);
    }

    public static Time of(double value, String unitSymbol) {
        TimeUnit resolvedUnit = TimeUnits.fromSymbol(unitSymbol);
        return new Time(value, resolvedUnit);
    }

    public static Time ofSeconds(double value) {
        return new Time(value, TimeUnits.SECOND);
    }

    public static Time ofMilliseconds(double value) {
        return new Time(value, TimeUnits.MILLISECOND);
    }

    public static Time ofMinutes(double value) {
        return new Time(value, TimeUnits.MINUTE);
    }

    public static Time ofHours(double value) {
        return new Time(value, TimeUnits.HOUR);
    }

    public static Time ofDays(double value) {
        return new Time(value, TimeUnits.DAY);
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
    public TimeUnit getUnit() {
        return unitType;
    }

    @Override
    public Time toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Time toUnit(TimeUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return Time.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Time toUnit(String targetUnit) {
        TimeUnit resolvedUnit = TimeUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Time withValue(double value) {
        return Time.of(value, unitType);
    }

    // Convert to target unit
    public Time toSeconds() {
        return toUnit(TimeUnits.SECOND);
    }

    public Time toMinutes() {
        return toUnit(TimeUnits.MINUTE);
    }

    public Time toHours() {
        return toUnit(TimeUnits.HOUR);
    }

    public Time toDays() {
        return toUnit(TimeUnits.DAY);
    }

    // Get value in target unit
    public double getInSeconds() {
        return getInUnit(TimeUnits.SECOND);
    }

    public double getInMilliseconds() {
        return getInUnit(TimeUnits.MILLISECOND);
    }

    public double getInMinutes() {
        return getInUnit(TimeUnits.MINUTE);
    }

    public double getInHours() {
        return getInUnit(TimeUnits.HOUR);
    }

    public double getInDays() {
        return getInUnit(TimeUnits.DAY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time other = (Time) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Time{" + value + " " + unitType.getSymbol() + '}';
    }
}
