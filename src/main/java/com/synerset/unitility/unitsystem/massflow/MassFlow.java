package com.synerset.unitility.unitsystem.massflow;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public final class MassFlow implements PhysicalQuantity<MassFlow> {

    private final double value;
    private final Unit<MassFlow> unit;

    private MassFlow(double value, Unit<MassFlow> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<MassFlow> getUnit() {
        return unit;
    }

    @Override
    public MassFlow toBaseUnit() {
        double valueInKilogramsPerSecond = unit.toBaseUnit(value);
        return MassFlow.of(valueInKilogramsPerSecond, MassFlowUnits.KILOGRAM_PER_SECOND);
    }

    @Override
    public MassFlow toUnit(Unit<MassFlow> targetUnit) {
        double valueInKilogramsPerSecond = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInKilogramsPerSecond);
        return MassFlow.of(valueInTargetUnit, targetUnit);
    }

    // Custom converter methods, for most popular units
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
        return ValueFormatter.formatDoubleToRelevantPrecision(value, TO_STRING_PRECISION) + " " + unit.getSymbol();
    }

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