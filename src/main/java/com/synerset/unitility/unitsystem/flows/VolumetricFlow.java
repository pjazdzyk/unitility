package com.synerset.unitility.unitsystem.flows;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class VolumetricFlow implements PhysicalQuantity<VolumetricFlow> {

    private final double value;
    private final Unit<VolumetricFlow> unit;

    private VolumetricFlow(double value, Unit<VolumetricFlow> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<VolumetricFlow> getUnit() {
        return unit;
    }

    @Override
    public VolumetricFlow toBaseUnit() {
        double valueInCubicMetersPerSecond = unit.toBaseUnit(value);
        return VolumetricFlow.of(valueInCubicMetersPerSecond, VolumetricFlowUnits.CUBIC_METERS_PER_SECOND);
    }

    @Override
    public VolumetricFlow toUnit(Unit<VolumetricFlow> targetUnit) {
        double valueInCubicMetersPerSecond = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInCubicMetersPerSecond);
        return VolumetricFlow.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public PhysicalQuantity<VolumetricFlow> createNewWithValue(double value) {
        return VolumetricFlow.of(value, unit);
    }

    // Custom value getters
    public double getValueOfCubicMetersPerSecond() {
        if (unit == VolumetricFlowUnits.CUBIC_METERS_PER_SECOND) {
            return value;
        }
        return toUnit(VolumetricFlowUnits.CUBIC_METERS_PER_SECOND).getValue();
    }

    public double getValueOfCubicMetersPerHour() {
        if (unit == VolumetricFlowUnits.CUBIC_METERS_PER_HOUR) {
            return value;
        }
        return toUnit(VolumetricFlowUnits.CUBIC_METERS_PER_HOUR).getValue();
    }

    // Custom converter methods, for most popular units
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
        return "VolumetricFlow{" +
                "value=" + value +
                ", unit=" + unit.getSymbol() +
                '}';
    }

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