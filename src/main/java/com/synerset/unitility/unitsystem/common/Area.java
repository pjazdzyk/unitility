package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class Area implements PhysicalQuantity<Area> {

    public static final Area PHYSICAL_MIN_LIMIT = Area.ofSquareMeters(0);

    private final double value;
    private final Unit<Area> unit;

    private Area(double value, Unit<Area> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Area> getUnit() {
        return unit;
    }

    @Override
    public Area toBaseUnit() {
        double valueInSquareMeters = unit.toBaseUnit(value);
        return Area.of(valueInSquareMeters, AreaUnits.SQUARE_METER);
    }

    @Override
    public Area toUnit(Unit<Area> targetUnit) {
        double valueInSquareMeters = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInSquareMeters);
        return Area.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public PhysicalQuantity<Area> createNewWithValue(double value) {
        return Area.of(value, unit);
    }

    // Custom value getters
    public double getValueOfSquareMeters(){
        if(unit == AreaUnits.SQUARE_METER){
            return value;
        }
        return toUnit(AreaUnits.SQUARE_METER).getValue();
    }

    // Custom converter methods, for most popular units
    public Area toSquareMeters() {
        return toUnit(AreaUnits.SQUARE_METER);
    }

    public Area toSquareKilometers() {
        return toUnit(AreaUnits.SQUARE_KILOMETER);
    }

    public Area toSquareCentimeters() {
        return toUnit(AreaUnits.SQUARE_CENTIMETER);
    }

    public Area toSquareMillimeters() {
        return toUnit(AreaUnits.SQUARE_MILLIMETER);
    }

    public Area toAres() {
        return toUnit(AreaUnits.ARE);
    }

    public Area toHectares() {
        return toUnit(AreaUnits.HECTARE);
    }

    public Area toSquareInches() {
        return toUnit(AreaUnits.SQUARE_INCH);
    }

    public Area toSquareFeet() {
        return toUnit(AreaUnits.SQUARE_FOOT);
    }

    public Area toSquareYards() {
        return toUnit(AreaUnits.SQUARE_YARD);
    }

    public Area toAcres() {
        return toUnit(AreaUnits.ACRE);
    }

    public Area toSquareMiles() {
        return toUnit(AreaUnits.SQUARE_MILE);
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