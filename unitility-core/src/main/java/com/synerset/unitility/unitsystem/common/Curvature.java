package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Curvature implements CalculableQuantity<CurvatureUnit, Curvature> {

    private final double value;
    private final double baseValue;
    private final CurvatureUnit unitType;

    public Curvature(double value, CurvatureUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = CurvatureUnits.getDefaultUnit();
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Curvature of(double value, CurvatureUnit unit) {
        return new Curvature(value, unit);
    }

    public static Curvature of(double value, String unitSymbol) {
        CurvatureUnit resolvedUnit = CurvatureUnits.fromSymbol(unitSymbol);
        return new Curvature(value, resolvedUnit);
    }

    public static Curvature ofRadiansPerMeter(double value) {
        return new Curvature(value, CurvatureUnits.RADIANS_PER_METER);
    }

    public static Curvature ofRadiansPerFoot(double value) {
        return new Curvature(value, CurvatureUnits.RADIANS_PER_FOOT);
    }

    public static Curvature ofDegreesPerMeter(double value) {
        return new Curvature(value, CurvatureUnits.DEGREES_PER_METER);
    }

    public static Curvature ofDegreesPerFoot(double value) {
        return new Curvature(value, CurvatureUnits.DEGREES_PER_FOOT);
    }

    public static Curvature ofDegreesPerHundredFeet(double value) {
        return new Curvature(value, CurvatureUnits.DEGREES_PER_HUNDRED_FEET);
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
    public CurvatureUnit getUnit() {
        return unitType;
    }

    @Override
    public Curvature toBaseUnit() {
        double degrees = unitType.toValueInBaseUnit(value);
        return of(degrees, CurvatureUnits.RADIANS_PER_METER);
    }

    @Override
    public Curvature toUnit(CurvatureUnit targetUnit) {
        double valueInDegrees = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInDegrees);
        return Curvature.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Curvature toUnit(String targetUnit) {
        CurvatureUnit resolvedUnit = CurvatureUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Curvature withValue(double value) {
        return Curvature.of(value, unitType);
    }

    // Convert to target unit
    public Curvature toRadiansPerMeter() {
        return toUnit(CurvatureUnits.RADIANS_PER_METER);
    }

    public Curvature toRadiansPerFoot() {
        return toUnit(CurvatureUnits.RADIANS_PER_FOOT);
    }

    public Curvature toDegreesPerMeter() {
        return toUnit(CurvatureUnits.DEGREES_PER_METER);
    }

    public Curvature toDegreesPerFoot() {
        return toUnit(CurvatureUnits.DEGREES_PER_FOOT);
    }

    public Curvature toDegreesPeHundredFeet() {
        return toUnit(CurvatureUnits.DEGREES_PER_HUNDRED_FEET);
    }

    // Get value in target unit
    public double getInRadiansPerMeter() {
        return getInUnit(CurvatureUnits.RADIANS_PER_METER);
    }

    public double getInRadiansPerFoot() {
        return getInUnit(CurvatureUnits.RADIANS_PER_FOOT);
    }

    public double getInDegreesPerMeter() {
        return getInUnit(CurvatureUnits.DEGREES_PER_METER);
    }

    public double getInDegreesPerFoot() {
        return getInUnit(CurvatureUnits.DEGREES_PER_FOOT);
    }

    public double getInDegreesPerHundredFeet() {
        return getInUnit(CurvatureUnits.DEGREES_PER_HUNDRED_FEET);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curvature inputQuantity = (Curvature) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        String separator = getUnit().getSymbol().contains("°") ? "" : " ";
        return "Curvature{" + value + separator + unitType.getSymbol() + '}';
    }

}