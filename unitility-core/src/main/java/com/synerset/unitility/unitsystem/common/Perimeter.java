package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Perimeter implements CalculableQuantity<DistanceUnit, Perimeter> {

    public static final Perimeter PHYSICAL_MIN_LIMIT = Perimeter.ofMeters(0);
    private final double value;
    private final double baseValue;
    private final DistanceUnit unitType;

    public Perimeter(double value, DistanceUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = DistanceUnits.METER;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Perimeter of(double value, DistanceUnit unit) {
        return new Perimeter(value, unit);
    }

    public static Perimeter of(double value, String unitSymbol) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(unitSymbol);
        return new Perimeter(value, resolvedUnit);
    }

    public static Perimeter ofMeters(double value) {
        return new Perimeter(value, DistanceUnits.METER);
    }

    public static Perimeter ofCentimeters(double value) {
        return new Perimeter(value, DistanceUnits.CENTIMETER);
    }

    public static Perimeter ofMillimeters(double value) {
        return new Perimeter(value, DistanceUnits.MILLIMETER);
    }

    public static Perimeter ofKilometers(double value) {
        return new Perimeter(value, DistanceUnits.KILOMETER);
    }

    public static Perimeter ofMiles(double value) {
        return new Perimeter(value, DistanceUnits.MILE);
    }

    public static Perimeter ofNauticalMiles(double value) {
        return new Perimeter(value, DistanceUnits.NAUTICAL_MILE);
    }

    public static Perimeter ofFeet(double value) {
        return new Perimeter(value, DistanceUnits.FEET);
    }

    public static Perimeter ofInches(double value) {
        return new Perimeter(value, DistanceUnits.INCH);
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
    public Perimeter toBaseUnit() {
        double valueInMeters = unitType.toValueInBaseUnit(value);
        return Perimeter.of(valueInMeters, DistanceUnits.METER);
    }

    @Override
    public Perimeter toUnit(DistanceUnit targetUnit) {
        double valueInMeters = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInMeters);
        return Perimeter.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Perimeter toUnit(String targetUnit) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Perimeter withValue(double value) {
        return Perimeter.of(value, unitType);
    }

    // Convert to target unit
    public Perimeter toMeter() {
        return toUnit(DistanceUnits.METER);
    }

    public Perimeter toCentimeter() {
        return toUnit(DistanceUnits.CENTIMETER);
    }

    public Perimeter toMillimeter() {
        return toUnit(DistanceUnits.MILLIMETER);
    }

    public Perimeter toKilometer() {
        return toUnit(DistanceUnits.KILOMETER);
    }

    public Perimeter toMile() {
        return toUnit(DistanceUnits.MILE);
    }

    public Perimeter toNauticalMile() {
        return toUnit(DistanceUnits.NAUTICAL_MILE);
    }

    public Perimeter toFeet() {
        return toUnit(DistanceUnits.FEET);
    }

    public Perimeter toInch() {
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

    public static Perimeter of(PhysicalQuantity<? extends DistanceUnit> distanceType) {
        return Perimeter.of(distanceType.getValue(), distanceType.getUnit());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perimeter inputQuantity = (Perimeter) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Perimeter{" + value + " " + unitType.getSymbol() + '}';
    }

}