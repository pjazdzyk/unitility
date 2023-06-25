package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class Volume implements PhysicalQuantity<Volume> {
    private final double value;
    private final Unit<Volume> unit;

    private Volume(double value, Unit<Volume> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Volume> getUnit() {
        return unit;
    }

    @Override
    public Volume toBaseUnit() {
        double valueInCubicMeter = unit.toBaseUnit(value);
        return Volume.of(valueInCubicMeter, VolumeUnits.CUBIC_METER);
    }

    @Override
    public Volume toUnit(Unit<Volume> targetUnit) {
        double valueInCubicMeter = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInCubicMeter);
        return Volume.of(valueInTargetUnit, targetUnit);
    }

    // Custom converter methods, for most popular units
    public Volume toCubicMeter() {
        return toUnit(VolumeUnits.CUBIC_METER);
    }

    public Volume toLiter() {
        return toUnit(VolumeUnits.LITER);
    }

    public Volume toCubicCentimeter() {
        return toUnit(VolumeUnits.CUBIC_CENTIMETER);
    }

    public Volume toHectoLiter() {
        return toUnit(VolumeUnits.HECTOLITRE);
    }

    public Volume toMilliLiter() {
        return toUnit(VolumeUnits.MILLILITRE);
    }

    public Volume toOunce() {
        return toUnit(VolumeUnits.OUNCE);
    }

    public Volume toPint() {
        return toUnit(VolumeUnits.PINT);
    }

    public Volume toGallon() {
        return toUnit(VolumeUnits.GALLON);
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
        return "Volume{" +
                "value=" + value +
                ", unit=" + unit.getSymbol() +
                '}';
    }

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

