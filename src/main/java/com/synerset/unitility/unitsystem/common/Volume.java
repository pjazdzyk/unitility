package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class Volume implements PhysicalQuantity<Volume> {
    private final double value;
    private final double baseValue;
    private final Unit<Volume> unit;

    public Volume(double value, Unit<Volume> unit) {
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
    public Unit<Volume> getUnit() {
        return unit;
    }

    @Override
    public Volume toBaseUnit() {
        double valueInCubicMeter = unit.toValueInBaseUnit(value);
        return Volume.of(valueInCubicMeter, VolumeUnits.CUBIC_METER);
    }

    @Override
    public Volume toUnit(Unit<Volume> targetUnit) {
        double valueInCubicMeter = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInCubicMeter);
        return Volume.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Volume createNewWithValue(double value) {
        return Volume.of(value, unit);
    }

    // Convert to target unit
    public double getInCubicMeters() {
        return getIn(VolumeUnits.CUBIC_METER);
    }

    public double getInLiters() {
        return getIn(VolumeUnits.LITER);
    }

    public double getInCubicCentimeters() {
        return getIn(VolumeUnits.CUBIC_CENTIMETER);
    }

    public double getInHectoLiters() {
        return getIn(VolumeUnits.HECTOLITRE);
    }

    public double getInMilliLiters() {
        return getIn(VolumeUnits.MILLILITRE);
    }

    public double getInOunces() {
        return getIn(VolumeUnits.OUNCE);
    }

    public double getInPints() {
        return getIn(VolumeUnits.PINT);
    }

    public double getInGallons() {
        return getIn(VolumeUnits.GALLON);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volume that = (Volume) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "Volume{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static Volume of(double value, Unit<Volume> unit) {
        return new Volume(value, unit);
    }

    public static Volume ofCubicMeters(double value) {
        return new Volume(value, VolumeUnits.CUBIC_METER);
    }

    public static Volume ofLiters(double value) {
        return new Volume(value, VolumeUnits.LITER);
    }

    public static Volume ofCubicCentimeters(double value) {
        return new Volume(value, VolumeUnits.CUBIC_CENTIMETER);
    }

    public static Volume ofHectoLiters(double value) {
        return new Volume(value, VolumeUnits.HECTOLITRE);
    }

    public static Volume ofMilliLiters(double value) {
        return new Volume(value, VolumeUnits.MILLILITRE);
    }

    public static Volume ofOunces(double value) {
        return new Volume(value, VolumeUnits.OUNCE);
    }

    public static Volume ofPints(double value) {
        return new Volume(value, VolumeUnits.PINT);
    }

    public static Volume ofGallons(double value) {
        return new Volume(value, VolumeUnits.GALLON);
    }
}

