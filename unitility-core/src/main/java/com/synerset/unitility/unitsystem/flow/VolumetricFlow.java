package com.synerset.unitility.unitsystem.flow;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class VolumetricFlow implements CalculableQuantity<VolumetricFlowUnit, VolumetricFlow> {

    public static final VolumetricFlow VOL_FLOW_MIN_LIMIT = VolumetricFlow.ofCubicMetersPerSecond(0);
    private final double value;
    private final double baseValue;
    private final VolumetricFlowUnit unitType;

    public VolumetricFlow(double value, VolumetricFlowUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = VolumetricFlowUnits.CUBIC_METERS_PER_SECOND;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static VolumetricFlow of(double value, VolumetricFlowUnit unit) {
        return new VolumetricFlow(value, unit);
    }

    public static VolumetricFlow of(double value, String unitSymbol) {
        VolumetricFlowUnit resolvedUnit = VolumetricFlowUnits.fromSymbol(unitSymbol);
        return new VolumetricFlow(value, resolvedUnit);
    }

    public static VolumetricFlow ofCubicMetersPerSecond(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.CUBIC_METERS_PER_SECOND);
    }

    public static VolumetricFlow ofCubicMetersPerMinute(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.CUBIC_METERS_PER_MINUTE);
    }

    public static VolumetricFlow ofCubicMetersPerHour(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.CUBIC_METERS_PER_HOUR);
    }

    public static VolumetricFlow ofCubicFeetPerMinute(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.CUBIC_FEET_PER_MINUTE);
    }

    public static VolumetricFlow ofLitresPerSecond(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.LITRE_PER_SECOND);
    }

    public static VolumetricFlow ofLitresPerMinute(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.LITRE_PER_MINUTE);
    }

    public static VolumetricFlow ofLitresPerHour(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.LITRE_PER_HOUR);
    }

    public static VolumetricFlow ofGallonsPerSecondUS(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.GALLONS_PER_SECOND_US);
    }

    public static VolumetricFlow ofGallonsPerMinuteUS(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.GALLONS_PER_MINUTE_US);
    }

    public static VolumetricFlow ofGallonsPerHourUS(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.GALLONS_PER_HOUR_US);
    }

    public static VolumetricFlow ofGallonsPerSecondUK(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.GALLONS_PER_SECOND_UK);
    }

    public static VolumetricFlow ofGallonsPerMinuteUK(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.GALLONS_PER_MINUTE_UK);
    }

    public static VolumetricFlow ofGallonsPerHourUK(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.GALLONS_PER_HOUR_UK);
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
    public VolumetricFlowUnit getUnit() {
        return unitType;
    }

    @Override
    public VolumetricFlow toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public VolumetricFlow toUnit(VolumetricFlowUnit targetUnit) {
        double valueInCubicMetersPerSecond = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInCubicMetersPerSecond);
        return VolumetricFlow.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public VolumetricFlow toUnit(String targetUnit) {
        VolumetricFlowUnit resolvedUnit = VolumetricFlowUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public VolumetricFlow withValue(double value) {
        return VolumetricFlow.of(value, unitType);
    }

    // Convert to target unit
    public VolumetricFlow toCubicMetersPerSecond() {
        return toUnit(VolumetricFlowUnits.CUBIC_METERS_PER_SECOND);
    }

    public VolumetricFlow toCubicMetersPerMinute() {
        return toUnit(VolumetricFlowUnits.CUBIC_METERS_PER_MINUTE);
    }

    public VolumetricFlow toCubicMetersPerHour() {
        return toUnit(VolumetricFlowUnits.CUBIC_METERS_PER_HOUR);
    }

    public VolumetricFlow toCubicFeetPerMinute() {
        return toUnit(VolumetricFlowUnits.CUBIC_FEET_PER_MINUTE);
    }

    public VolumetricFlow toLitresPerSecond() {
        return toUnit(VolumetricFlowUnits.LITRE_PER_SECOND);
    }

    public VolumetricFlow toLitresPerMinute() {
        return toUnit(VolumetricFlowUnits.LITRE_PER_MINUTE);
    }

    public VolumetricFlow toLitresPerHour() {
        return toUnit(VolumetricFlowUnits.LITRE_PER_HOUR);
    }

    public VolumetricFlow toGallonsPerSecondUS() {
        return toUnit(VolumetricFlowUnits.GALLONS_PER_SECOND_US);
    }

    public VolumetricFlow toGallonsPerMinuteUS() {
        return toUnit(VolumetricFlowUnits.GALLONS_PER_MINUTE_US);
    }

    public VolumetricFlow toGallonsPerHourUS() {
        return toUnit(VolumetricFlowUnits.GALLONS_PER_HOUR_US);
    }

    public VolumetricFlow toGallonsPerSecondUK() {
        return toUnit(VolumetricFlowUnits.GALLONS_PER_SECOND_UK);
    }

    public VolumetricFlow toGallonsPerMinuteUK() {
        return toUnit(VolumetricFlowUnits.GALLONS_PER_MINUTE_UK);
    }

    public VolumetricFlow toGallonsPerHourUK() {
        return toUnit(VolumetricFlowUnits.GALLONS_PER_HOUR_UK);
    }

    // Get value in target unit
    public double getInCubicMetersPerSecond() {
        return getInUnit(VolumetricFlowUnits.CUBIC_METERS_PER_SECOND);
    }

    public double getInCubicMetersPerMinute() {
        return getInUnit(VolumetricFlowUnits.CUBIC_METERS_PER_MINUTE);
    }

    public double getInCubicMetersPerHour() {
        return getInUnit(VolumetricFlowUnits.CUBIC_METERS_PER_HOUR);
    }

    public double getInCubicFeetPerMinute() {
        return getInUnit(VolumetricFlowUnits.CUBIC_FEET_PER_MINUTE);
    }

    public double getInLitresPerSecond() {
        return getInUnit(VolumetricFlowUnits.LITRE_PER_SECOND);
    }

    public double getInLitresPerMinute() {
        return getInUnit(VolumetricFlowUnits.LITRE_PER_MINUTE);
    }

    public double getInLitresPerHour() {
        return getInUnit(VolumetricFlowUnits.LITRE_PER_HOUR);
    }

    public double getInGallonsPerSecondUS() {
        return getInUnit(VolumetricFlowUnits.GALLONS_PER_SECOND_US);
    }

    public double getInGallonsPerMinuteUS() {
        return getInUnit(VolumetricFlowUnits.GALLONS_PER_MINUTE_US);
    }

    public double getInGallonsPerHourUS() {
        return getInUnit(VolumetricFlowUnits.GALLONS_PER_HOUR_US);
    }

    public double getInGallonsPerSecondUK() {
        return getInUnit(VolumetricFlowUnits.GALLONS_PER_SECOND_UK);
    }

    public double getInGallonsPerMinuteUK() {
        return getInUnit(VolumetricFlowUnits.GALLONS_PER_MINUTE_UK);
    }

    public double getInGallonsPerHourUK() {
        return getInUnit(VolumetricFlowUnits.GALLONS_PER_HOUR_UK);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolumetricFlow inputQuantity = (VolumetricFlow) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "VolumetricFlow{" + value + " " + unitType.getSymbol() + '}';
    }

}