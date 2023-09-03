package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class DynamicViscosity implements PhysicalQuantity<DynamicViscosityUnits> {

    private final double value;
    private final double baseValue;
    private final DynamicViscosityUnits unit;

    public DynamicViscosity(double value, DynamicViscosityUnits unit) {
        this.value = value;
        this.unit = unit;
        this.baseValue = unit.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static DynamicViscosity of(double value, DynamicViscosityUnits unit) {
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

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public DynamicViscosityUnits getUnit() {
        return unit;
    }

    @Override
    public DynamicViscosity toBaseUnit() {
        double valueInPascalSecond = unit.toValueInBaseUnit(value);
        return DynamicViscosity.of(valueInPascalSecond, DynamicViscosityUnits.PASCAL_SECOND);
    }

    @Override
    public DynamicViscosity toUnit(DynamicViscosityUnits targetUnit) {
        double valueInPascalSecond = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInPascalSecond);
        return DynamicViscosity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public DynamicViscosity createNewWithValue(double value) {
        return DynamicViscosity.of(value, unit);
    }

    // Convert to target unit
    public DynamicViscosity toKiloGramPerMeterSecond() {
        return toUnit(DynamicViscosityUnits.KILOGRAM_PER_METER_SECOND);
    }

    public DynamicViscosity toPascalSecond() {
        return toUnit(DynamicViscosityUnits.PASCAL_SECOND);
    }

    public DynamicViscosity toPoise() {
        return toUnit(DynamicViscosityUnits.POISE);
    }

    // Get value in target unit
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
        DynamicViscosity inputQuantity = (DynamicViscosity) o;
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unit.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unit.getBaseUnit());
    }

    @Override
    public String toString() {
        return "DynamicViscosity{" + value + " " + unit.getSymbol() + '}';
    }

}
