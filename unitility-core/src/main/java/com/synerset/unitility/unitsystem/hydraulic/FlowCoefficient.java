package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class FlowCoefficient implements CalculableQuantity<FlowCoefficientUnit, FlowCoefficient> {
    private final double value;
    private final double baseValue;
    private final FlowCoefficientUnit unitType;

    public FlowCoefficient(double value, FlowCoefficientUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = FlowCoefficientUnits.KV;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static FlowCoefficient of(double value, FlowCoefficientUnit unit) {
        return new FlowCoefficient(value, unit);
    }

    public static FlowCoefficient of(double value, String unitSymbol) {
        FlowCoefficientUnit resolvedUnit = FlowCoefficientUnits.fromSymbol(unitSymbol);
        return new FlowCoefficient(value, resolvedUnit);
    }

    public static FlowCoefficient ofKv(double value) {
        return new FlowCoefficient(value, FlowCoefficientUnits.KV);
    }

    public static FlowCoefficient ofCv(double value) {
        return new FlowCoefficient(value, FlowCoefficientUnits.CV);
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
    public FlowCoefficientUnit getUnit() {
        return unitType;
    }

    @Override
    public FlowCoefficient toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public FlowCoefficient toUnit(FlowCoefficientUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return FlowCoefficient.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public FlowCoefficient toUnit(String targetUnit) {
        FlowCoefficientUnit resolvedUnit = FlowCoefficientUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public FlowCoefficient withValue(double value) {
        return FlowCoefficient.of(value, unitType);
    }

    // Convert to target unit
    public FlowCoefficient toKv() {
        return toUnit(FlowCoefficientUnits.KV);
    }

    public FlowCoefficient toCv() {
        return toUnit(FlowCoefficientUnits.CV);
    }

    // Get value in target unit
    public double getInKv() {
        return getInUnit(FlowCoefficientUnits.KV);
    }

    public double getInCv() {
        return getInUnit(FlowCoefficientUnits.CV);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowCoefficient other = (FlowCoefficient) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "FlowCoefficient{" + value + " " + unitType.getSymbol() + '}';
    }
}
