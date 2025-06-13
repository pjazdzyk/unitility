package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Diameter implements CalculableQuantity<DistanceUnit, Diameter> {

    public static final Diameter PHYSICAL_MIN_LIMIT = Diameter.ofMeters(0);
    private final double value;
    private final double baseValue;
    private final DistanceUnit unitType;

    public Diameter(double value, DistanceUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = DistanceUnits.METER;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Diameter of(double value, DistanceUnit unit) {
        return new Diameter(value, unit);
    }

    public static Diameter of(double value, String unitSymbol) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(unitSymbol);
        return new Diameter(value, resolvedUnit);
    }
    
    public static Diameter ofMeters(double value) {
        return new Diameter(value, DistanceUnits.METER);
    }

    public static Diameter ofCentimeters(double value) {
        return new Diameter(value, DistanceUnits.CENTIMETER);
    }

    public static Diameter ofMillimeters(double value) {
        return new Diameter(value, DistanceUnits.MILLIMETER);
    }

    public static Diameter ofKilometers(double value) {
        return new Diameter(value, DistanceUnits.KILOMETER);
    }

    public static Diameter ofMiles(double value) {
        return new Diameter(value, DistanceUnits.MILE);
    }

    public static Diameter ofNauticalMiles(double value) {
        return new Diameter(value, DistanceUnits.NAUTICAL_MILE);
    }

    public static Diameter ofFeet(double value) {
        return new Diameter(value, DistanceUnits.FEET);
    }

    public static Diameter ofInches(double value) {
        return new Diameter(value, DistanceUnits.INCH);
    }

    public static Diameter of(PhysicalQuantity<? extends DistanceUnit> distanceType){
        return Diameter.of(distanceType.getValue(), distanceType.getUnit());
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
    public Diameter toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Diameter toUnit(DistanceUnit targetUnit) {
        double valueInMeters = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInMeters);
        return Diameter.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Diameter toUnit(String targetUnit) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Diameter withValue(double value) {
        return Diameter.of(value, unitType);
    }

    // Convert to target unit
    public Diameter toMeter() {
        return toUnit(DistanceUnits.METER);
    }

    public Diameter toCentimeter() {
        return toUnit(DistanceUnits.CENTIMETER);
    }

    public Diameter toMillimeter() {
        return toUnit(DistanceUnits.MILLIMETER);
    }

    public Diameter toKilometer() {
        return toUnit(DistanceUnits.KILOMETER);
    }

    public Diameter toMile() {
        return toUnit(DistanceUnits.MILE);
    }

    public Diameter toNauticalMile() {
        return toUnit(DistanceUnits.NAUTICAL_MILE);
    }

    public Diameter toFeet() {
        return toUnit(DistanceUnits.FEET);
    }

    public Diameter toInch() {
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
        Diameter inputQuantity = (Diameter) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Diameter{" + value + " " + unitType.getSymbol() + '}';
    }

}