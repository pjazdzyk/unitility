package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class DynamicViscosity implements CalculableQuantity<DynamicViscosityUnit, DynamicViscosity> {

    private final double value;
    private final double baseValue;
    private final DynamicViscosityUnit unitType;

    public DynamicViscosity(double value, DynamicViscosityUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = DynamicViscosityUnits.KILOGRAM_PER_METER_SECOND;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static DynamicViscosity of(double value, DynamicViscosityUnit unit) {
        return new DynamicViscosity(value, unit);
    }

    public static DynamicViscosity of(double value, String unitSymbol) {
        DynamicViscosityUnit resolvedUnit = DynamicViscosityUnits.fromSymbol(unitSymbol);
        return new DynamicViscosity(value, resolvedUnit);
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
    public DynamicViscosityUnit getUnit() {
        return unitType;
    }

    @Override
    public DynamicViscosity toBaseUnit() {
        double valueInPascalSecond = unitType.toValueInBaseUnit(value);
        return of(valueInPascalSecond, DynamicViscosityUnits.PASCAL_SECOND);
    }

    @Override
    public DynamicViscosity toUnit(DynamicViscosityUnit targetUnit) {
        double valueInPascalSecond = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInPascalSecond);
        return DynamicViscosity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public DynamicViscosity toUnit(String targetUnit) {
        DynamicViscosityUnit resolvedUnit = DynamicViscosityUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public DynamicViscosity withValue(double value) {
        return DynamicViscosity.of(value, unitType);
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
        return getInUnit(DynamicViscosityUnits.KILOGRAM_PER_METER_SECOND);
    }

    public double getInPascalsSecond() {
        return getInUnit(DynamicViscosityUnits.PASCAL_SECOND);
    }

    public double getInPoise() {
        return getInUnit(DynamicViscosityUnits.POISE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicViscosity inputQuantity = (DynamicViscosity) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "DynamicViscosity{" + value + " " + unitType.getSymbol() + '}';
    }

}
