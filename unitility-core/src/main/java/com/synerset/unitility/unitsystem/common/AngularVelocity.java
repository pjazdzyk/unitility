package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class AngularVelocity implements CalculableQuantity<AngularVelocityUnit, AngularVelocity> {

    private final double value;
    private final double baseValue;
    private final AngularVelocityUnit unitType;

    public AngularVelocity(double value, AngularVelocityUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = AngularVelocityUnits.RADIANS_PER_SECOND;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static AngularVelocity of(double value, AngularVelocityUnit unit) {
        return new AngularVelocity(value, unit);
    }

    public static AngularVelocity of(double value, String unitSymbol) {
        AngularVelocityUnit resolvedUnit = AngularVelocityUnits.fromSymbol(unitSymbol);
        return new AngularVelocity(value, resolvedUnit);
    }

    public static AngularVelocity ofRadiansPerSecond(double value) {
        return new AngularVelocity(value, AngularVelocityUnits.RADIANS_PER_SECOND);
    }

    public static AngularVelocity ofRevolutionsPerMinute(double value) {
        return new AngularVelocity(value, AngularVelocityUnits.REVOLUTIONS_PER_MINUTE);
    }

    public static AngularVelocity ofRevolutionsPerSecond(double value) {
        return new AngularVelocity(value, AngularVelocityUnits.REVOLUTIONS_PER_SECOND);
    }

    public static AngularVelocity ofDegreesPerSecond(double value) {
        return new AngularVelocity(value, AngularVelocityUnits.DEGREES_PER_SECOND);
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
    public AngularVelocityUnit getUnit() {
        return unitType;
    }

    @Override
    public AngularVelocity toBaseUnit() {
        double valueInMetersPerSecond = unitType.toValueInBaseUnit(value);
        return of(valueInMetersPerSecond, AngularVelocityUnits.RADIANS_PER_SECOND);
    }

    @Override
    public AngularVelocity toUnit(AngularVelocityUnit targetUnit) {
        double valueInMetersPerSecond = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInMetersPerSecond);
        return AngularVelocity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public AngularVelocity toUnit(String targetUnit) {
        AngularVelocityUnit resolvedUnit = AngularVelocityUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public AngularVelocity withValue(double value) {
        return AngularVelocity.of(value, unitType);
    }

    // Convert to target unit
    public AngularVelocity toRadiansPerSecond() {
        return toUnit(AngularVelocityUnits.RADIANS_PER_SECOND);
    }

    public AngularVelocity toRevolutionsPerMinute() {
        return toUnit(AngularVelocityUnits.REVOLUTIONS_PER_MINUTE);
    }

    public AngularVelocity toRevolutionsPerSecond() {
        return toUnit(AngularVelocityUnits.REVOLUTIONS_PER_SECOND);
    }

    public AngularVelocity toDegreesPerSecond() {
        return toUnit(AngularVelocityUnits.DEGREES_PER_SECOND);
    }

    // Get value in target unit
    public double getInRadiansPerSecond() {
        return getInUnit(AngularVelocityUnits.RADIANS_PER_SECOND);
    }

    public double getInRevolutionsPerMinute() {
        return getInUnit(AngularVelocityUnits.REVOLUTIONS_PER_MINUTE);
    }

    public double getInRevolutionsPerSecond() {
        return getInUnit(AngularVelocityUnits.REVOLUTIONS_PER_SECOND);
    }

    public double getInDegreesPerSecond() {
        return getInUnit(AngularVelocityUnits.DEGREES_PER_SECOND);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AngularVelocity inputQuantity = (AngularVelocity) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "AngularVelocity{" + value + " " + unitType.getSymbol() + '}';
    }

}
