package com.synerset.unitility.unitsystem.basic.common;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Volume implements PhysicalQuantity<VolumeUnit> {
    public static final Volume PHYSICAL_MIN_LIMIT = Volume.ofCubicMeters(0);
    private final double value;
    private final double baseValue;
    private final VolumeUnit unitType;

    public Volume(double value, VolumeUnit unit) {
        this.value = value;
        this.unitType = unit;
        this.baseValue = unit.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Volume of(double value, VolumeUnit unit) {
        return new Volume(value, unit);
    }

    public static Volume of(double value, String unitSymbol) {
        VolumeUnit resolvedUnit = VolumeUnits.fromSymbol(unitSymbol);
        return new Volume(value, resolvedUnit);
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

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public VolumeUnit getUnitType() {
        return unitType;
    }

    @Override
    public Volume toBaseUnit() {
        double valueInCubicMeter = unitType.toValueInBaseUnit(value);
        return of(valueInCubicMeter, VolumeUnits.CUBIC_METER);
    }

    @Override
    public Volume toUnit(VolumeUnit targetUnit) {
        double valueInCubicMeter = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInCubicMeter);
        return Volume.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Volume withValue(double value) {
        return Volume.of(value, unitType);
    }

    // Convert to target unit
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

    // Get value in target unit
    public double getInCubicMeters() {
        return getInUnit(VolumeUnits.CUBIC_METER);
    }

    public double getInLiters() {
        return getInUnit(VolumeUnits.LITER);
    }

    public double getInCubicCentimeters() {
        return getInUnit(VolumeUnits.CUBIC_CENTIMETER);
    }

    public double getInHectoLiters() {
        return getInUnit(VolumeUnits.HECTOLITRE);
    }

    public double getInMilliLiters() {
        return getInUnit(VolumeUnits.MILLILITRE);
    }

    public double getInOunces() {
        return getInUnit(VolumeUnits.OUNCE);
    }

    public double getInPints() {
        return getInUnit(VolumeUnits.PINT);
    }

    public double getInGallons() {
        return getInUnit(VolumeUnits.GALLON);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volume inputQuantity = (Volume) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Volume{" + value + " " + unitType.getSymbol() + '}';
    }
}

