package com.synerset.unitility.unitsystem.flows;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class MassFlow implements PhysicalQuantity<MassFlow> {

    private final double value;
    private final double baseValue;
    private final Unit<MassFlow> unit;

    public MassFlow(double value, Unit<MassFlow> unit) {
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
    public Unit<MassFlow> getUnit() {
        return unit;
    }

    @Override
    public MassFlow toBaseUnit() {
        double valueInKilogramsPerSecond = unit.toValueInBaseUnit(value);
        return MassFlow.of(valueInKilogramsPerSecond, MassFlowUnits.KILOGRAM_PER_SECOND);
    }

    @Override
    public MassFlow toUnit(Unit<MassFlow> targetUnit) {
        double valueInKilogramsPerSecond = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKilogramsPerSecond);
        return MassFlow.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public MassFlow createNewWithValue(double value) {
        return MassFlow.of(value, unit);
    }

    // Convert to target unit
    public MassFlow toKilogramsPerSecond(){
        return toUnit(MassFlowUnits.KILOGRAM_PER_SECOND);
    }

    public MassFlow toKiloGramPerHour(){
        return toUnit(MassFlowUnits.KILOGRAM_PER_HOUR);
    }

    public MassFlow toTonnesPerHour(){
        return toUnit(MassFlowUnits.TONNE_PER_HOUR);
    }

    public MassFlow toPoundsPerSecond(){
        return toUnit(MassFlowUnits.POUND_PER_SECOND);
    }

    // Get value in target unit
    public double getInKilogramsPerSecond() {
        return getIn(MassFlowUnits.KILOGRAM_PER_SECOND);
    }

    public double getInKiloGramsPerHour() {
        return getIn(MassFlowUnits.KILOGRAM_PER_HOUR);
    }

    public double getInTonnesPerHour() {
        return getIn(MassFlowUnits.TONNE_PER_HOUR);
    }

    public double getInPoundsPerSecond() {
        return getIn(MassFlowUnits.POUND_PER_SECOND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MassFlow massFlow = (MassFlow) o;
        return Double.compare(massFlow.value, value) == 0 && unit.equals(massFlow.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "MassFlow{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static MassFlow of(double value, Unit<MassFlow> unit) {
        return new MassFlow(value, unit);
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

}