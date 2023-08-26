package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class Distance implements PhysicalQuantity<Distance> {

    private final double value;
    private final double baseValue;
    private final Unit<Distance> unit;

    public Distance(double value, Unit<Distance> unit) {
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
    public Unit<Distance> getUnit() {
        return unit;
    }

    @Override
    public Distance toBaseUnit() {
        double valueInMeters = unit.toValueInBaseUnit(value);
        return Distance.of(valueInMeters, DistanceUnits.METER);
    }

    @Override
    public Distance toUnit(Unit<Distance> targetUnit) {
        double valueInMeters = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInMeters);
        return Distance.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Distance createNewWithValue(double value) {
        return Distance.of(value, unit);
    }

    // Convert to target unit
    public double getInMeters() {
        return getIn(DistanceUnits.METER);
    }

    public double getInCentimeters() {
        return getIn(DistanceUnits.CENTIMETER);
    }

    public double getInMillimeters() {
        return getIn(DistanceUnits.MILLIMETER);
    }

    public double getInKilometers() {
        return getIn(DistanceUnits.KILOMETER);
    }

    public double getInMiles() {
        return getIn(DistanceUnits.MILE);
    }

    public double getInFeet() {
        return getIn(DistanceUnits.FEET);
    }

    public double getInInches() {
        return getIn(DistanceUnits.INCH);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return Double.compare(distance.value, value) == 0 && unit.equals(distance.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "Distance{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static Distance of(double value, Unit<Distance> unit) {
        return new Distance(value, unit);
    }

    public static Distance ofMeters(double value) {
        return new Distance(value, DistanceUnits.METER);
    }

    public static Distance ofCentimeters(double value) {
        return new Distance(value, DistanceUnits.CENTIMETER);
    }

    public static Distance ofMillimeters(double value) {
        return new Distance(value, DistanceUnits.MILLIMETER);
    }

    public static Distance ofKilometers(double value) {
        return new Distance(value, DistanceUnits.KILOMETER);
    }

    public static Distance ofMiles(double value) {
        return new Distance(value, DistanceUnits.MILE);
    }

    public static Distance ofFeet(double value) {
        return new Distance(value, DistanceUnits.FEET);
    }

    public static Distance ofInches(double value) {
        return new Distance(value, DistanceUnits.INCH);
    }

}