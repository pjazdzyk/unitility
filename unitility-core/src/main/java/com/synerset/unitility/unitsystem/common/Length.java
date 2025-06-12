package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Length implements CalculableQuantity<DistanceUnit, Length> {

    public static final Length PHYSICAL_MIN_LIMIT = Length.ofMeters(0);
    private final double value;
    private final double baseValue;
    private final DistanceUnit unitType;

    public Length(double value, DistanceUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = DistanceUnits.METER;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Length of(double value, DistanceUnit unit) {
        return new Length(value, unit);
    }

    public static Length of(double value, String unitSymbol) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(unitSymbol);
        return new Length(value, resolvedUnit);
    }
    
    public static Length ofMeters(double value) {
        return new Length(value, DistanceUnits.METER);
    }

    public static Length ofCentimeters(double value) {
        return new Length(value, DistanceUnits.CENTIMETER);
    }

    public static Length ofMillimeters(double value) {
        return new Length(value, DistanceUnits.MILLIMETER);
    }

    public static Length ofKilometers(double value) {
        return new Length(value, DistanceUnits.KILOMETER);
    }

    public static Length ofMiles(double value) {
        return new Length(value, DistanceUnits.MILE);
    }

    public static Length ofNauticalMiles(double value) {
        return new Length(value, DistanceUnits.NAUTICAL_MILE);
    }

    public static Length ofFeet(double value) {
        return new Length(value, DistanceUnits.FEET);
    }

    public static Length ofInches(double value) {
        return new Length(value, DistanceUnits.INCH);
    }

    public static Length of(PhysicalQuantity<? extends DistanceUnit> distanceType){
        return Length.of(distanceType.getValue(), distanceType.getUnit());
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
    public Length toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Length toUnit(DistanceUnit targetUnit) {
        double valueInMeters = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInMeters);
        return Length.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Length toUnit(String targetUnit) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Length withValue(double value) {
        return Length.of(value, unitType);
    }

    // Convert to target unit
    public Length toMeter() {
        return toUnit(DistanceUnits.METER);
    }

    public Length toCentimeter() {
        return toUnit(DistanceUnits.CENTIMETER);
    }

    public Length toMillimeter() {
        return toUnit(DistanceUnits.MILLIMETER);
    }

    public Length toKilometer() {
        return toUnit(DistanceUnits.KILOMETER);
    }

    public Length toMile() {
        return toUnit(DistanceUnits.MILE);
    }

    public Length toNauticalMile() {
        return toUnit(DistanceUnits.NAUTICAL_MILE);
    }

    public Length toFeet() {
        return toUnit(DistanceUnits.FEET);
    }

    public Length toInch() {
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
        Length inputQuantity = (Length) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Length{" + value + " " + unitType.getSymbol() + '}';
    }

}