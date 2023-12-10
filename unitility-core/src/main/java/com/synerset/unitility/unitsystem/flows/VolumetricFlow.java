package com.synerset.unitility.unitsystem.flows;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class VolumetricFlow implements PhysicalQuantity<VolumetricFlowUnit> {

    private final double value;
    private final double baseValue;
    private final VolumetricFlowUnit unitType;

    public VolumetricFlow(double value, VolumetricFlowUnit unitType) {
        this.value = value;
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

    public static VolumetricFlow ofLitresPerSecond(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.LITRE_PER_SECOND);
    }

    public static VolumetricFlow ofLitresPerMinute(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.LITRE_PER_MINUTE);
    }

    public static VolumetricFlow ofLitresPerHour(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.LITRE_PER_HOUR);
    }

    public static VolumetricFlow ofGallonsPerSecond(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.GALLONS_PER_SECOND);
    }

    public static VolumetricFlow ofGallonsPerMinute(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.GALLONS_PER_MINUTE);
    }

    public static VolumetricFlow ofGallonsPerHour(double value) {
        return new VolumetricFlow(value, VolumetricFlowUnits.GALLONS_PER_HOUR);
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
    public VolumetricFlowUnit getUnitType() {
        return unitType;
    }

    @Override
    public VolumetricFlow toBaseUnit() {
        double valueInCubicMetersPerSecond = unitType.toValueInBaseUnit(value);
        return VolumetricFlow.of(valueInCubicMetersPerSecond, VolumetricFlowUnits.CUBIC_METERS_PER_SECOND);
    }

    @Override
    public VolumetricFlow toUnit(VolumetricFlowUnit targetUnit) {
        double valueInCubicMetersPerSecond = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInCubicMetersPerSecond);
        return VolumetricFlow.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
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

    public VolumetricFlow toLitresPerSecond() {
        return toUnit(VolumetricFlowUnits.LITRE_PER_SECOND);
    }

    public VolumetricFlow toLitresPerMinute() {
        return toUnit(VolumetricFlowUnits.LITRE_PER_MINUTE);
    }

    public VolumetricFlow toLitresPerHour() {
        return toUnit(VolumetricFlowUnits.LITRE_PER_HOUR);
    }

    public VolumetricFlow toGallonsPerSecond() {
        return toUnit(VolumetricFlowUnits.GALLONS_PER_SECOND);
    }

    public VolumetricFlow toGallonsPerMinute() {
        return toUnit(VolumetricFlowUnits.GALLONS_PER_MINUTE);
    }

    public VolumetricFlow toGallonsPerHour() {
        return toUnit(VolumetricFlowUnits.GALLONS_PER_HOUR);
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

    public double getInLitresPerSecond() {
        return getInUnit(VolumetricFlowUnits.LITRE_PER_SECOND);
    }

    public double getInLitresPerMinute() {
        return getInUnit(VolumetricFlowUnits.LITRE_PER_MINUTE);
    }

    public double getInLitresPerHour() {
        return getInUnit(VolumetricFlowUnits.LITRE_PER_HOUR);
    }

    public double getInGallonsPerSecond() {
        return getInUnit(VolumetricFlowUnits.GALLONS_PER_SECOND);
    }

    public double getInGallonsPerMinute() {
        return getInUnit(VolumetricFlowUnits.GALLONS_PER_MINUTE);
    }

    public double getInGallonsPerHour() {
        return getInUnit(VolumetricFlowUnits.GALLONS_PER_HOUR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolumetricFlow inputQuantity = (VolumetricFlow) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
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