package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Distance implements CalculableQuantity<DistanceUnit, Distance> {

    public static final Distance PHYSICAL_MIN_LIMIT = Distance.ofMeters(0);
    private final double value;
    private final double baseValue;
    private final DistanceUnit unitType;

    public Distance(double value, DistanceUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = DistanceUnits.METER;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Distance of(double value, DistanceUnit unit) {
        return new Distance(value, unit);
    }

    public static Distance of(double value, String unitSymbol) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(unitSymbol);
        return new Distance(value, resolvedUnit);
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

    public static Distance ofNauticalMiles(double value) {
        return new Distance(value, DistanceUnits.NAUTICAL_MILE);
    }

    public static Distance ofFeet(double value) {
        return new Distance(value, DistanceUnits.FEET);
    }

    public static Distance ofInches(double value) {
        return new Distance(value, DistanceUnits.INCH);
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
    public DistanceUnit getUnit() {
        return unitType;
    }

    @Override
    public Distance toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Distance toUnit(DistanceUnit targetUnit) {
        double valueInMeters = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInMeters);
        return Distance.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Distance toUnit(String targetUnit) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Distance withValue(double value) {
        return Distance.of(value, unitType);
    }

    // Convert to target unit
    public Distance toMeter() {
        return toUnit(DistanceUnits.METER);
    }

    public Distance toCentimeter() {
        return toUnit(DistanceUnits.CENTIMETER);
    }

    public Distance toMillimeter() {
        return toUnit(DistanceUnits.MILLIMETER);
    }

    public Distance toKilometer() {
        return toUnit(DistanceUnits.KILOMETER);
    }

    public Distance toMile() {
        return toUnit(DistanceUnits.MILE);
    }

    public Distance toNauticalMile() {
        return toUnit(DistanceUnits.NAUTICAL_MILE);
    }

    public Distance toFeet() {
        return toUnit(DistanceUnits.FEET);
    }

    public Distance toInch() {
        return toUnit(DistanceUnits.INCH);
    }

    // Get value in target unit
    public double getInMeters() {
        return getInUnit(DistanceUnits.METER);
    }

    public double getInCentimeters() {
        return getInUnit(DistanceUnits.CENTIMETER);
    }

    public double getInMillimeters() {
        return getInUnit(DistanceUnits.MILLIMETER);
    }

    public double getInKilometers() {
        return getInUnit(DistanceUnits.KILOMETER);
    }

    public double getInMiles() {
        return getInUnit(DistanceUnits.MILE);
    }

    public double getInNauticalMiles() {
        return getInUnit(DistanceUnits.NAUTICAL_MILE);
    }

    public double getInFeet() {
        return getInUnit(DistanceUnits.FEET);
    }

    public double getInInches() {
        return getInUnit(DistanceUnits.INCH);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance inputQuantity = (Distance) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Distance{" + value + " " + unitType.getSymbol() + '}';
    }

}