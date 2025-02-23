package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Width implements CalculableQuantity<DistanceUnit, Width> {

    public static final Width PHYSICAL_MIN_LIMIT = Width.ofMeters(0);
    private final double value;
    private final double baseValue;
    private final DistanceUnit unitType;

    public Width(double value, DistanceUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = DistanceUnits.getDefaultUnit();
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Width of(double value, DistanceUnit unit) {
        return new Width(value, unit);
    }

    public static Width of(double value, String unitSymbol) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(unitSymbol);
        return new Width(value, resolvedUnit);
    }
    
    public static Width ofMeters(double value) {
        return new Width(value, DistanceUnits.METER);
    }

    public static Width ofCentimeters(double value) {
        return new Width(value, DistanceUnits.CENTIMETER);
    }

    public static Width ofMillimeters(double value) {
        return new Width(value, DistanceUnits.MILLIMETER);
    }

    public static Width ofKilometers(double value) {
        return new Width(value, DistanceUnits.KILOMETER);
    }

    public static Width ofMiles(double value) {
        return new Width(value, DistanceUnits.MILE);
    }

    public static Width ofNauticalMiles(double value) {
        return new Width(value, DistanceUnits.NAUTICAL_MILE);
    }

    public static Width ofFeet(double value) {
        return new Width(value, DistanceUnits.FEET);
    }

    public static Width ofInches(double value) {
        return new Width(value, DistanceUnits.INCH);
    }

    public static Width of(PhysicalQuantity<? extends DistanceUnit> distanceType){
        return Width.of(distanceType.getValue(), distanceType.getUnit());
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
    public Width toBaseUnit() {
        double valueInMeters = unitType.toValueInBaseUnit(value);
        return Width.of(valueInMeters, DistanceUnits.METER);
    }

    @Override
    public Width toUnit(DistanceUnit targetUnit) {
        double valueInMeters = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInMeters);
        return Width.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Width toUnit(String targetUnit) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Width withValue(double value) {
        return Width.of(value, unitType);
    }

    // Convert to target unit
    public Width toMeter() {
        return toUnit(DistanceUnits.METER);
    }

    public Width toCentimeter() {
        return toUnit(DistanceUnits.CENTIMETER);
    }

    public Width toMillimeter() {
        return toUnit(DistanceUnits.MILLIMETER);
    }

    public Width toKilometer() {
        return toUnit(DistanceUnits.KILOMETER);
    }

    public Width toMile() {
        return toUnit(DistanceUnits.MILE);
    }

    public Width toNauticalMile() {
        return toUnit(DistanceUnits.NAUTICAL_MILE);
    }

    public Width toFeet() {
        return toUnit(DistanceUnits.FEET);
    }

    public Width toInch() {
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
        Width inputQuantity = (Width) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Width{" + value + " " + unitType.getSymbol() + '}';
    }

}