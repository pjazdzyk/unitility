package com.synerset.unitility.unitsystem.distance;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.math.BigDecimal;
import java.util.Objects;

public final class Distance implements PhysicalQuantity<Distance> {

    private final BigDecimal value;
    private final Unit<Distance> unit;

    private Distance(BigDecimal value, Unit<Distance> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public Unit<Distance> getUnit() {
        return unit;
    }

    @Override
    public Distance toBaseUnit() {
        BigDecimal valueInMeters = unit.toBaseUnit(value);
        return Distance.of(valueInMeters, DistanceUnits.METER);
    }

    @Override
    public Distance toUnit(Unit<Distance> targetUnit) {
        BigDecimal valueInMeters = unit.toBaseUnit(value);
        BigDecimal valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInMeters);
        return Distance.of(valueInTargetUnit, targetUnit);
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
        return Objects.equals(value, distance.value) && Objects.equals(unit, distance.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return ValueFormatter.formatDoubleToRelevantPrecision(value, TO_STRING_PRECISION) + " " + unit.getSymbol();
    }

    public static Distance of(BigDecimal value, Unit<Distance> unit) {
        return new Distance(value, unit);
    }

    public static Distance ofMeters(BigDecimal value) {
        return new Distance(value, DistanceUnits.METER);
    }

    public static Distance ofCentimeters(BigDecimal value) {
        return new Distance(value, DistanceUnits.CENTIMETER);
    }

    public static Distance ofMillimeters(BigDecimal value) {
        return new Distance(value, DistanceUnits.MILLIMETER);
    }

    public static Distance ofKilometers(BigDecimal value) {
        return new Distance(value, DistanceUnits.KILOMETER);
    }

    public static Distance ofMiles(BigDecimal value) {
        return new Distance(value, DistanceUnits.MILE);
    }

    public static Distance ofFeet(BigDecimal value) {
        return new Distance(value, DistanceUnits.FEET);
    }

    public static Distance ofInches(BigDecimal value) {
        return new Distance(value, DistanceUnits.INCH);
    }

}