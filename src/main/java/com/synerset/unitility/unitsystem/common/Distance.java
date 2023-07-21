package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class Distance implements PhysicalQuantity<Distance> {

    private final double value;
    private final Unit<Distance> unit;

    private Distance(double value, Unit<Distance> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Distance> getUnit() {
        return unit;
    }

    @Override
    public Distance toBaseUnit() {
        double valueInMeters = unit.toBaseUnit(value);
        return Distance.of(valueInMeters, DistanceUnits.METER);
    }

    @Override
    public Distance toUnit(Unit<Distance> targetUnit) {
        double valueInMeters = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInMeters);
        return Distance.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public PhysicalQuantity<Distance> createNewWithValue(double value) {
        return Distance.of(value, unit);
    }

    // Custom value getters
    public double getValueOfMeters(){
        if(unit == DistanceUnits.METER){
            return value;
        }
        return toUnit(DistanceUnits.METER).getValue();
    }

    // Custom converter methods for most popular units
    public Distance toMeter(){
        return toUnit(DistanceUnits.METER);
    }

    public Distance toCentimeter(){
        return toUnit(DistanceUnits.CENTIMETER);
    }

    public Distance toMillimeter(){
        return toUnit(DistanceUnits.MILLIMETER);
    }

    public Distance toKilometer(){
        return toUnit(DistanceUnits.KILOMETER);
    }

    public Distance toMile(){
        return toUnit(DistanceUnits.MILE);
    }

    public Distance toFeet(){
        return toUnit(DistanceUnits.FEET);
    }

    public Distance toInch(){
        return toUnit(DistanceUnits.INCH);
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
        return "Distance{" +
                "value=" + value +
                ", unit=" + unit.getSymbol() +
                '}';
    }

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