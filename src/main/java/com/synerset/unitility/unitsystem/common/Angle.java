package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Angle implements PhysicalQuantity<AngleUnits> {

    private final double value;
    private final double baseValue;
    private final AngleUnits unitType;

    public Angle(double value, AngleUnits unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Angle of(double value, AngleUnits unit) {
        return new Angle(value, unit);
    }
    public static Angle of(double value, String unitSymbol) {
        AngleUnits resolvedUnit = AngleUnits.fromSymbol(unitSymbol);
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
    public AngleUnits getUnitType() {
        return unitType;
    }

    @Override
    public Angle toBaseUnit() {
        double degrees = unitType.toValueInBaseUnit(value);
        return Angle.of(degrees, AngleUnits.DEGREES);
    }

    @Override
    public Angle toUnit(AngleUnits targetUnit) {
        double valueInDegrees = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInDegrees);
        return Angle.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Angle createNewWithValue(double value) {
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
        return getIn(AngleUnits.RADIANS);
    }

    public double getInDegrees() {
        return getIn(AngleUnits.DEGREES);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Angle inputQuantity = (Angle) o;
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        String separator = getUnitType().getSymbol().contains("°") ? "" : " ";
        return "Angle{" + value + separator + unitType.getSymbol() + '}';
    }

}