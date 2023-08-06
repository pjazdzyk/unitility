package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class ThermalDiffusivity implements PhysicalQuantity<ThermalDiffusivity> {

    private final double value;
    private final Unit<ThermalDiffusivity> unit;

    private ThermalDiffusivity(double value, Unit<ThermalDiffusivity> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<ThermalDiffusivity> getUnit() {
        return unit;
    }

    @Override
    public ThermalDiffusivity toBaseUnit() {
        double valueInSquareMeterPerSecond = unit.toBaseUnit(value);
        return ThermalDiffusivity.of(valueInSquareMeterPerSecond, ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND);
    }

    @Override
    public ThermalDiffusivity toUnit(Unit<ThermalDiffusivity> targetUnit) {
        double valueInSquareMeterPerSecond = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInSquareMeterPerSecond);
        return ThermalDiffusivity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public ThermalDiffusivity createNewWithValue(double value) {
        return ThermalDiffusivity.of(value, unit);
    }

    // Custom value getters
    public double getValueOfSquareMetersPerSecond() {
        if (unit == ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND) {
            return value;
        }
        return toUnit(ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND).getValue();
    }

    // Custom converter methods, for most popular units
    public ThermalDiffusivity toSquareMeterPerSecond() {
        return toUnit(ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND);
    }

    public ThermalDiffusivity toSquareFeetPerSecond() {
        return toUnit(ThermalDiffusivityUnits.SQUARE_FEET_PER_SECOND);
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
