package com.synerset.unitility.unitsystem.flow;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class MassFlow implements CalculableQuantity<MassFlowUnit, MassFlow> {

    public static final MassFlow MASS_FLOW_MIN_LIMIT = MassFlow.ofKilogramsPerSecond(0);
    private final double value;
    private final double baseValue;
    private final MassFlowUnit unitType;

    public MassFlow(double value, MassFlowUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = MassFlowUnits.getDefaultUnit();
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static MassFlow of(double value, MassFlowUnit unit) {
        return new MassFlow(value, unit);
    }

    public static MassFlow of(double value, String unitSymbol) {
        MassFlowUnit resolvedUnit = MassFlowUnits.fromSymbol(unitSymbol);
        return new MassFlow(value, resolvedUnit);
    }

    public static MassFlow ofKilogramsPerSecond(double value) {
        return new MassFlow(value, MassFlowUnits.KILOGRAM_PER_SECOND);
    }

    public static MassFlow ofKilogramsPerHour(double value) {
        return new MassFlow(value, MassFlowUnits.KILOGRAM_PER_HOUR);
    }

    public static MassFlow ofTonnesPerHour(double value) {
        return new MassFlow(value, MassFlowUnits.TONNE_PER_HOUR);
    }

    public static MassFlow ofPoundsPerSecond(double value) {
        return new MassFlow(value, MassFlowUnits.POUND_PER_SECOND);
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
    public MassFlowUnit getUnit() {
        return unitType;
    }

    @Override
    public MassFlow toBaseUnit() {
        double valueInKilogramsPerSecond = unitType.toValueInBaseUnit(value);
        return of(valueInKilogramsPerSecond, MassFlowUnits.KILOGRAM_PER_SECOND);
    }

    @Override
    public MassFlow toUnit(MassFlowUnit targetUnit) {
        double valueInKilogramsPerSecond = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKilogramsPerSecond);
        return MassFlow.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public MassFlow toUnit(String targetUnit) {
        MassFlowUnit resolvedUnit = MassFlowUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public MassFlow withValue(double value) {
        return MassFlow.of(value, unitType);
    }

    // Convert to target unit
    public MassFlow toKilogramsPerSecond() {
        return toUnit(MassFlowUnits.KILOGRAM_PER_SECOND);
    }

    public MassFlow toKiloGramPerHour() {
        return toUnit(MassFlowUnits.KILOGRAM_PER_HOUR);
    }

    public MassFlow toTonnesPerHour() {
        return toUnit(MassFlowUnits.TONNE_PER_HOUR);
    }

    public MassFlow toPoundsPerSecond() {
        return toUnit(MassFlowUnits.POUND_PER_SECOND);
    }

    // Get value in target unit
    public double getInKilogramsPerSecond() {
        return getInUnit(MassFlowUnits.KILOGRAM_PER_SECOND);
    }

    public double getInKiloGramsPerHour() {
        return getInUnit(MassFlowUnits.KILOGRAM_PER_HOUR);
    }

    public double getInTonnesPerHour() {
        return getInUnit(MassFlowUnits.TONNE_PER_HOUR);
    }

    public double getInPoundsPerSecond() {
        return getInUnit(MassFlowUnits.POUND_PER_SECOND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MassFlow inputQuantity = (MassFlow) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "MassFlow{" + value + " " + unitType.getSymbol() + '}';
    }

}