package com.synerset.unitsystem.length;

import com.synerset.unitsystem.PhysicalQuantity;
import com.synerset.unitsystem.Unit;
import com.synerset.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public final class Length implements PhysicalQuantity<Length> {

    public static final byte TO_STRING_PRECISION = 3;
    private final double value;
    private final Unit<Length> unit;

    private Length(double value, Unit<Length> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Length> getUnit() {
        return unit;
    }

    @Override
    public Length toBaseUnit() {
        double valueInMeters = unit.toBaseUnit(value);
        return Length.of(valueInMeters, LengthUnits.METER);
    }

    @Override
    public Length toUnit(Unit<Length> targetUnit) {
        double valueInMeters = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInMeters);
        return Length.of(valueInTargetUnit, targetUnit);
    }

    // Custom converter methods for most popular units
    public Length toMeter(){
        return toUnit(LengthUnits.METER);
    }

    public Length toCentimeter(){
        return toUnit(LengthUnits.CENTIMETER);
    }

    public Length toMillimeter(){
        return toUnit(LengthUnits.MILLIMETER);
    }

    public Length toKilometer(){
        return toUnit(LengthUnits.KILOMETER);
    }

    public Length toMile(){
        return toUnit(LengthUnits.MILE);
    }

    public Length toFeet(){
        return toUnit(LengthUnits.FEET);
    }

    public Length toInch(){
        return toUnit(LengthUnits.INCH);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Length length = (Length) o;
        return Double.compare(length.value, value) == 0 && unit.equals(length.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return ValueFormatter.formatDoubleToRelevantPrecision(value, TO_STRING_PRECISION) + " " + unit.getSymbol();
    }

    public static Length of(double value, Unit<Length> unit) {
        return new Length(value, unit);
    }

    public static Length ofMeters(double value) {
        return new Length(value, LengthUnits.METER);
    }

    public static Length ofCentimeters(double value) {
        return new Length(value, LengthUnits.CENTIMETER);
    }

    public static Length ofMillimeters(double value) {
        return new Length(value, LengthUnits.MILLIMETER);
    }

    public static Length ofKilometers(double value) {
        return new Length(value, LengthUnits.KILOMETER);
    }

    public static Length ofMiles(double value) {
        return new Length(value, LengthUnits.MILE);
    }

    public static Length ofFeet(double value) {
        return new Length(value, LengthUnits.FEET);
    }

    public static Length ofInches(double value) {
        return new Length(value, LengthUnits.INCH);
    }

}