package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class Area implements PhysicalQuantity<Area> {

    public static final Area PHYSICAL_MIN_LIMIT = Area.ofSquareMeters(0);
    private final double value;
    private final double baseValue;
    private final Unit<Area> unit;

    private Area(double value, Unit<Area> unit) {
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
    public Unit<Area> getUnit() {
        return unit;
    }

    @Override
    public Area toBaseUnit() {
        double valueInSquareMeters = unit.toValueInBaseUnit(value);
        return Area.of(valueInSquareMeters, AreaUnits.SQUARE_METER);
    }

    @Override
    public Area toUnit(Unit<Area> targetUnit) {
        double valueInSquareMeters = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInSquareMeters);
        return Area.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Area createNewWithValue(double value) {
        return Area.of(value, unit);
    }

    // Convert to target unit
    public double getInSquareMeters() {
        return getIn(AreaUnits.SQUARE_METER);
    }

    public double getInSquareKilometers() {
        return getIn(AreaUnits.SQUARE_KILOMETER);
    }

    public double getInSquareCentimeters() {
        return getIn(AreaUnits.SQUARE_CENTIMETER);
    }

    public double getInSquareMillimeters() {
        return getIn(AreaUnits.SQUARE_MILLIMETER);
    }

    public double getInAres() {
        return getIn(AreaUnits.ARE);
    }

    public double getInHectares() {
        return getIn(AreaUnits.HECTARE);
    }

    public double getInSquareInches() {
        return getIn(AreaUnits.SQUARE_INCH);
    }

    public double getInSquareFeet() {
        return getIn(AreaUnits.SQUARE_FOOT);
    }

    public double getInSquareYards() {
        return getIn(AreaUnits.SQUARE_YARD);
    }

    public double getInAcres() {
        return getIn(AreaUnits.ACRE);
    }

    public double getInSquareMiles() {
        return getIn(AreaUnits.SQUARE_MILE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return Double.compare(area.value, value) == 0 && unit.equals(area.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "Area{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static Area of(double value, Unit<Area> unit) {
        return new Area(value, unit);
    }

    public static Area ofSquareMeters(double value) {
        return new Area(value, AreaUnits.SQUARE_METER);
    }

    public static Area ofSquareKilometers(double value) {
        return new Area(value, AreaUnits.SQUARE_KILOMETER);
    }

    public static Area ofSquareCentimeters(double value) {
        return new Area(value, AreaUnits.SQUARE_CENTIMETER);
    }

    public static Area ofSquareMillimeters(double value) {
        return new Area(value, AreaUnits.SQUARE_MILLIMETER);
    }

    public static Area ofAres(double value) {
        return new Area(value, AreaUnits.ARE);
    }

    public static Area ofHectares(double value) {
        return new Area(value, AreaUnits.HECTARE);
    }

    public static Area ofSquareInches(double value) {
        return new Area(value, AreaUnits.SQUARE_INCH);
    }

    public static Area ofSquareFeet(double value) {
        return new Area(value, AreaUnits.SQUARE_FOOT);
    }

    public static Area ofSquareYards(double value) {
        return new Area(value, AreaUnits.SQUARE_YARD);
    }

    public static Area ofAcres(double value) {
        return new Area(value, AreaUnits.ACRE);
    }

    public static Area ofSquareMiles(double value) {
        return new Area(value, AreaUnits.SQUARE_MILE);
    }
}