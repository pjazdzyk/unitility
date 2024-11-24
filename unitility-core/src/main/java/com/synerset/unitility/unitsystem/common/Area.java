package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Area implements CalculableQuantity<AreaUnit, Area> {

    public static final Area PHYSICAL_MIN_LIMIT = Area.ofSquareMeters(0);
    private final double value;
    private final double baseValue;
    private final AreaUnit unitType;

    public Area(double value, AreaUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = AreaUnits.getDefaultUnit();
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Area of(double value, AreaUnit unit) {
        return new Area(value, unit);
    }

    public static Area of(double value, String unitSymbol) {
        AreaUnit resolvedUnit = AreaUnits.fromSymbol(unitSymbol);
        return new Area(value, resolvedUnit);
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

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public AreaUnit getUnit() {
        return unitType;
    }

    @Override
    public Area toBaseUnit() {
        double valueInSquareMeters = unitType.toValueInBaseUnit(value);
        return of(valueInSquareMeters, AreaUnits.SQUARE_METER);
    }

    @Override
    public Area toUnit(AreaUnit targetUnit) {
        double valueInSquareMeters = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInSquareMeters);
        return Area.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Area toUnit(String targetUnit) {
        AreaUnit resolvedUnit = AreaUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Area withValue(double value) {
        return Area.of(value, unitType);
    }

    // Convert to target unit
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

    // Get value in target unit
    public double getInSquareMeters() {
        return getInUnit(AreaUnits.SQUARE_METER);
    }

    public double getInSquareKilometers() {
        return getInUnit(AreaUnits.SQUARE_KILOMETER);
    }

    public double getInSquareCentimeters() {
        return getInUnit(AreaUnits.SQUARE_CENTIMETER);
    }

    public double getInSquareMillimeters() {
        return getInUnit(AreaUnits.SQUARE_MILLIMETER);
    }

    public double getInAres() {
        return getInUnit(AreaUnits.ARE);
    }

    public double getInHectares() {
        return getInUnit(AreaUnits.HECTARE);
    }

    public double getInSquareInches() {
        return getInUnit(AreaUnits.SQUARE_INCH);
    }

    public double getInSquareFeet() {
        return getInUnit(AreaUnits.SQUARE_FOOT);
    }

    public double getInSquareYards() {
        return getInUnit(AreaUnits.SQUARE_YARD);
    }

    public double getInAcres() {
        return getInUnit(AreaUnits.ACRE);
    }

    public double getInSquareMiles() {
        return getInUnit(AreaUnits.SQUARE_MILE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area inputQuantity = (Area) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Area{" + value + " " + unitType.getSymbol() + '}';
    }

}