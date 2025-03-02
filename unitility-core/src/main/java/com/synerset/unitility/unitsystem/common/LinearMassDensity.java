package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class LinearMassDensity implements CalculableQuantity<LinearMassDensityUnit, LinearMassDensity> {

    public static final LinearMassDensity PHYSICAL_MIN_LIMIT = LinearMassDensity.ofKilogramsPerMeter(0);
    private final double value;
    private final double baseValue;
    private final LinearMassDensityUnit unitType;

    public LinearMassDensity(double value, LinearMassDensityUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = LinearMassDensityUnits.getDefaultUnit();
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static LinearMassDensity of(double value, LinearMassDensityUnit unit) {
        return new LinearMassDensity(value, unit);
    }

    public static LinearMassDensity of(double value, String unitSymbol) {
        LinearMassDensityUnit resolvedUnit = LinearMassDensityUnits.fromSymbol(unitSymbol);
        return new LinearMassDensity(value, resolvedUnit);
    }

    public static LinearMassDensity ofKilogramsPerMeter(double value) {
        return new LinearMassDensity(value, LinearMassDensityUnits.KILOGRAM_PER_METER);
    }

    public static LinearMassDensity ofTonnesPerMeter(double value) {
        return new LinearMassDensity(value, LinearMassDensityUnits.TONNE_PER_METER);
    }

    public static LinearMassDensity ofOuncesPerFoot(double value) {
        return new LinearMassDensity(value, LinearMassDensityUnits.OUNCE_PER_FOOT);
    }

    public static LinearMassDensity ofPoundsPerFoot(double value) {
        return new LinearMassDensity(value, LinearMassDensityUnits.POUND_PER_FOOT);
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
    public LinearMassDensityUnit getUnit() {
        return unitType;
    }

    @Override
    public LinearMassDensity toBaseUnit() {
        double valueInKgPerM = unitType.toValueInBaseUnit(value);
        return of(valueInKgPerM, LinearMassDensityUnits.KILOGRAM_PER_METER);
    }

    @Override
    public LinearMassDensity toUnit(LinearMassDensityUnit targetUnit) {
        double valueInKgPerM = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKgPerM);
        return LinearMassDensity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public LinearMassDensity toUnit(String targetUnit) {
        LinearMassDensityUnit resolvedUnit = LinearMassDensityUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public LinearMassDensity withValue(double value) {
        return LinearMassDensity.of(value, unitType);
    }

    // Convert to target unit
    public LinearMassDensity toKilogramPerMeter() {
        return toUnit(LinearMassDensityUnits.KILOGRAM_PER_METER);
    }

    public LinearMassDensity toTonnePerMeter() {
        return toUnit(LinearMassDensityUnits.TONNE_PER_METER);
    }

    public LinearMassDensity toOuncePerFoot() {
        return toUnit(LinearMassDensityUnits.OUNCE_PER_FOOT);
    }

    public LinearMassDensity toPoundPerFoot() {
        return toUnit(LinearMassDensityUnits.POUND_PER_FOOT);
    }

    // Get value in target unit
    public double getInKilogramsPerMeter() {
        return getInUnit(LinearMassDensityUnits.KILOGRAM_PER_METER);
    }

    public double getInTonnesPerMeter() {
        return getInUnit(LinearMassDensityUnits.TONNE_PER_METER);
    }

    public double getInOuncesPerFoot() {
        return getInUnit(LinearMassDensityUnits.OUNCE_PER_FOOT);
    }

    public double getInPoundsPerFoot() {
        return getInUnit(LinearMassDensityUnits.POUND_PER_FOOT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinearMassDensity inputQuantity = (LinearMassDensity) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 &&
               Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "LinearMassDensity{" + value + " " + unitType.getSymbol() + '}';
    }

}
