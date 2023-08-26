package com.synerset.unitility.unitsystem.flows;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class VolumetricFlow implements PhysicalQuantity<VolumetricFlow> {

    private final double value;
    private final double baseValue;
    private final Unit<VolumetricFlow> unit;

    private VolumetricFlow(double value, Unit<VolumetricFlow> unit) {
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
    public Unit<VolumetricFlow> getUnit() {
        return unit;
    }

    @Override
    public VolumetricFlow toBaseUnit() {
        double valueInCubicMetersPerSecond = unit.toValueInBaseUnit(value);
        return VolumetricFlow.of(valueInCubicMetersPerSecond, VolumetricFlowUnits.CUBIC_METERS_PER_SECOND);
    }

    @Override
    public VolumetricFlow toUnit(Unit<VolumetricFlow> targetUnit) {
        double valueInCubicMetersPerSecond = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInCubicMetersPerSecond);
        return VolumetricFlow.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public VolumetricFlow createNewWithValue(double value) {
        return VolumetricFlow.of(value, unit);
    }

    // Convert to target unit
    public double getInCubicMetersPerSecond() {
        return getIn(VolumetricFlowUnits.CUBIC_METERS_PER_SECOND);
    }

    public double getInCubicMetersPerMinute() {
        return getIn(VolumetricFlowUnits.CUBIC_METERS_PER_MINUTE);
    }

    public double getInCubicMetersPerHour() {
        return getIn(VolumetricFlowUnits.CUBIC_METERS_PER_HOUR);
    }

    public double getInLitresPerSecond() {
        return getIn(VolumetricFlowUnits.LITRE_PER_SECOND);
    }

    public double getInLitresPerMinute() {
        return getIn(VolumetricFlowUnits.LITRE_PER_MINUTE);
    }

    public double getInLitresPerHour() {
        return getIn(VolumetricFlowUnits.LITRE_PER_HOUR);
    }

    public double getInGallonsPerSecond() {
        return getIn(VolumetricFlowUnits.GALLONS_PER_SECOND);
    }

    public double getInGallonsPerMinute() {
        return getIn(VolumetricFlowUnits.GALLONS_PER_MINUTE);
    }

    public double getInGallonsPerHour() {
        return getIn(VolumetricFlowUnits.GALLONS_PER_HOUR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolumetricFlow that = (VolumetricFlow) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "VolumetricFlow{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static VolumetricFlow of(double value, Unit<VolumetricFlow> unit) {
        return new VolumetricFlow(value, unit);
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

}