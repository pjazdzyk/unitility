package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class ThermalDiffusivity implements PhysicalQuantity<ThermalDiffusivity> {

    private final double value;
    private final double baseValue;
    private final Unit<ThermalDiffusivity> unit;

    public ThermalDiffusivity(double value, Unit<ThermalDiffusivity> unit) {
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
    public Unit<ThermalDiffusivity> getUnit() {
        return unit;
    }

    @Override
    public ThermalDiffusivity toBaseUnit() {
        double valueInSquareMeterPerSecond = unit.toValueInBaseUnit(value);
        return ThermalDiffusivity.of(valueInSquareMeterPerSecond, ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND);
    }

    @Override
    public ThermalDiffusivity toUnit(Unit<ThermalDiffusivity> targetUnit) {
        double valueInSquareMeterPerSecond = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInSquareMeterPerSecond);
        return ThermalDiffusivity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public ThermalDiffusivity createNewWithValue(double value) {
        return ThermalDiffusivity.of(value, unit);
    }

    // Convert to target unit
    public double getInSquareMetersPerSecond() {
        return getIn(ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND);
    }

    public double getInSquareFeetPerSecond() {
        return getIn(ThermalDiffusivityUnits.SQUARE_FEET_PER_SECOND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThermalDiffusivity thermalDiffusivity = (ThermalDiffusivity) o;
        return Double.compare(thermalDiffusivity.value, value) == 0 && Objects.equals(unit, thermalDiffusivity.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "ThermalDiffusivity{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static ThermalDiffusivity of(double value, Unit<ThermalDiffusivity> unit) {
        return new ThermalDiffusivity(value, unit);
    }

    public static ThermalDiffusivity ofSquareMeterPerSecond(double value) {
        return new ThermalDiffusivity(value, ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND);
    }

    public static ThermalDiffusivity ofSquareFeetPerSecond(double value) {
        return new ThermalDiffusivity(value, ThermalDiffusivityUnits.SQUARE_FEET_PER_SECOND);
    }
}
