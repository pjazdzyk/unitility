package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class DynamicViscosity implements PhysicalQuantity<DynamicViscosity> {

    private final double value;
    private final double baseValue;
    private final Unit<DynamicViscosity> unit;

    public DynamicViscosity(double value, Unit<DynamicViscosity> unit) {
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
    public Unit<DynamicViscosity> getUnit() {
        return unit;
    }

    @Override
    public DynamicViscosity toBaseUnit() {
        double valueInPascalSecond = unit.toValueInBaseUnit(value);
        return DynamicViscosity.of(valueInPascalSecond, DynamicViscosityUnits.PASCAL_SECOND);
    }

    @Override
    public DynamicViscosity toUnit(Unit<DynamicViscosity> targetUnit) {
        double valueInPascalSecond = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInPascalSecond);
        return DynamicViscosity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public DynamicViscosity createNewWithValue(double value) {
        return DynamicViscosity.of(value, unit);
    }

    // Convert to target unit
    public double getInKiloGramPerMeterSecond() {
        return getIn(DynamicViscosityUnits.KILOGRAM_PER_METER_SECOND);
    }

    public double getInPascalsSecond() {
        return getIn(DynamicViscosityUnits.PASCAL_SECOND);
    }

    public double getInPoise() {
        return getIn(DynamicViscosityUnits.POISE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicViscosity that = (DynamicViscosity) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "DynamicViscosity{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static DynamicViscosity of(double value, Unit<DynamicViscosity> unit) {
        return new DynamicViscosity(value, unit);
    }

    public static DynamicViscosity ofKiloGramPerMeterSecond(double value) {
        return new DynamicViscosity(value, DynamicViscosityUnits.KILOGRAM_PER_METER_SECOND);
    }

    public static DynamicViscosity ofPascalSecond(double value) {
        return new DynamicViscosity(value, DynamicViscosityUnits.PASCAL_SECOND);
    }

    public static DynamicViscosity ofPoise(double value) {
        return new DynamicViscosity(value, DynamicViscosityUnits.POISE);
    }

}
