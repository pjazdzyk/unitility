package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class ThermalDiffusivity implements PhysicalQuantity<ThermalDiffusivityUnits> {

    private final double value;
    private final double baseValue;
    private final ThermalDiffusivityUnits unit;

    public ThermalDiffusivity(double value, ThermalDiffusivityUnits unit) {
        this.value = value;
        this.unit = unit;
        this.baseValue = unit.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static ThermalDiffusivity of(double value, ThermalDiffusivityUnits unit) {
        return new ThermalDiffusivity(value, unit);
    }

    public static ThermalDiffusivity ofSquareMeterPerSecond(double value) {
        return new ThermalDiffusivity(value, ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND);
    }

    public static ThermalDiffusivity ofSquareFeetPerSecond(double value) {
        return new ThermalDiffusivity(value, ThermalDiffusivityUnits.SQUARE_FEET_PER_SECOND);
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
    public ThermalDiffusivityUnits getUnit() {
        return unit;
    }

    @Override
    public ThermalDiffusivity toBaseUnit() {
        double valueInSquareMeterPerSecond = unit.toValueInBaseUnit(value);
        return ThermalDiffusivity.of(valueInSquareMeterPerSecond, ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND);
    }

    @Override
    public ThermalDiffusivity toUnit(ThermalDiffusivityUnits targetUnit) {
        double valueInSquareMeterPerSecond = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInSquareMeterPerSecond);
        return ThermalDiffusivity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ThermalDiffusivity createNewWithValue(double value) {
        return ThermalDiffusivity.of(value, unit);
    }

    // Convert to target unit
    public ThermalDiffusivity toSquareMeterPerSecond() {
        return toUnit(ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND);
    }

    public ThermalDiffusivity toSquareFeetPerSecond() {
        return toUnit(ThermalDiffusivityUnits.SQUARE_FEET_PER_SECOND);
    }

    // Get value in target unit
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
        ThermalDiffusivity inputQuantity = (ThermalDiffusivity) o;
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unit.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unit.getBaseUnit());
    }

    @Override
    public String toString() {
        return "ThermalDiffusivity{" + value + " " + unit.getSymbol() + '}';
    }

}
