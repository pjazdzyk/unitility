package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.TrigonometricQuantity;

import java.util.Objects;

public class Angle implements TrigonometricQuantity<Angle> {

    private final double value;
    private final double baseValue;
    private final AngleUnit unitType;

    public Angle(double value, AngleUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = AngleUnits.getDefaultUnit();
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Angle of(double value, AngleUnit unit) {
        return new Angle(value, unit);
    }

    public static Angle of(double value, String unitSymbol) {
        AngleUnit resolvedUnit = AngleUnits.fromSymbol(unitSymbol);
        return new Angle(value, resolvedUnit);
    }

    public static Angle ofRadians(double value) {
        return new Angle(value, AngleUnits.RADIANS);
    }

    public static Angle ofDegrees(double value) {
        return new Angle(value, AngleUnits.DEGREES);
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
    public AngleUnit getUnit() {
        return unitType;
    }

    @Override
    public Angle toBaseUnit() {
        double degrees = unitType.toValueInBaseUnit(value);
        return of(degrees, AngleUnits.DEGREES);
    }

    @Override
    public Angle toUnit(AngleUnit targetUnit) {
        double valueInDegrees = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInDegrees);
        return Angle.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Angle toUnit(String targetUnit) {
        AngleUnit resolvedUnit = AngleUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Angle withValue(double value) {
        return Angle.of(value, unitType);
    }

    // Convert to target unit
    public Angle toRadians() {
        return toUnit(AngleUnits.RADIANS);
    }

    public Angle toDegrees() {
        return toUnit(AngleUnits.DEGREES);
    }

    // Get value in target unit
    public double getInRadians() {
        return getInUnit(AngleUnits.RADIANS);
    }

    public double getInDegrees() {
        return getInUnit(AngleUnits.DEGREES);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Angle inputQuantity = (Angle) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        String separator = getUnit().getSymbol().contains("°") ? "" : " ";
        return "Angle{" + value + separator + unitType.getSymbol() + '}';
    }

}