package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Height implements CalculableQuantity<DistanceUnit, Height> {

    public static final Height PHYSICAL_MIN_LIMIT = Height.ofMeters(0);
    private final double value;
    private final double baseValue;
    private final DistanceUnit unitType;

    public Height(double value, DistanceUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = DistanceUnits.METER;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Height of(double value, DistanceUnit unit) {
        return new Height(value, unit);
    }

    public static Height of(double value, String unitSymbol) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(unitSymbol);
        return new Height(value, resolvedUnit);
    }
    
    public static Height ofMeters(double value) {
        return new Height(value, DistanceUnits.METER);
    }

    public static Height ofCentimeters(double value) {
        return new Height(value, DistanceUnits.CENTIMETER);
    }

    public static Height ofMillimeters(double value) {
        return new Height(value, DistanceUnits.MILLIMETER);
    }

    public static Height ofKilometers(double value) {
        return new Height(value, DistanceUnits.KILOMETER);
    }

    public static Height ofMiles(double value) {
        return new Height(value, DistanceUnits.MILE);
    }

    public static Height ofNauticalMiles(double value) {
        return new Height(value, DistanceUnits.NAUTICAL_MILE);
    }

    public static Height ofFeet(double value) {
        return new Height(value, DistanceUnits.FEET);
    }

    public static Height ofInches(double value) {
        return new Height(value, DistanceUnits.INCH);
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
    public Height toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Height toUnit(DistanceUnit targetUnit) {
        double valueInMeters = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInMeters);
        return Height.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Height toUnit(String targetUnit) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Height withValue(double value) {
        return Height.of(value, unitType);
    }

    // Convert to target unit
    public Height toMeter() {
        return toUnit(DistanceUnits.METER);
    }

    public Height toCentimeter() {
        return toUnit(DistanceUnits.CENTIMETER);
    }

    public Height toMillimeter() {
        return toUnit(DistanceUnits.MILLIMETER);
    }

    public Height toKilometer() {
        return toUnit(DistanceUnits.KILOMETER);
    }

    public Height toMile() {
        return toUnit(DistanceUnits.MILE);
    }

    public Height toNauticalMile() {
        return toUnit(DistanceUnits.NAUTICAL_MILE);
    }

    public Height toFeet() {
        return toUnit(DistanceUnits.FEET);
    }

    public Height toInch() {
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

    public static Height of(PhysicalQuantity<? extends DistanceUnit> distanceType){
        return Height.of(distanceType.getValue(), distanceType.getUnit());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Height inputQuantity = (Height) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Height{" + value + " " + unitType.getSymbol() + '}';
    }

}