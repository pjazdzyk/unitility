package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class ThermalDiffusivity implements CalculableQuantity<ThermalDiffusivityUnit, ThermalDiffusivity> {

    private final double value;
    private final double baseValue;
    private final ThermalDiffusivityUnit unitType;

    public ThermalDiffusivity(double value, ThermalDiffusivityUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = ThermalDiffusivityUnits.getDefaultUnit();
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static ThermalDiffusivity of(double value, ThermalDiffusivityUnit unit) {
        return new ThermalDiffusivity(value, unit);
    }

    public static ThermalDiffusivity of(double value, String unitSymbol) {
        ThermalDiffusivityUnit resolvedUnit = ThermalDiffusivityUnits.fromSymbol(unitSymbol);
        return new ThermalDiffusivity(value, resolvedUnit);
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
    public ThermalDiffusivityUnit getUnit() {
        return unitType;
    }

    @Override
    public ThermalDiffusivity toBaseUnit() {
        double valueInSquareMeterPerSecond = unitType.toValueInBaseUnit(value);
        return of(valueInSquareMeterPerSecond, ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND);
    }

    @Override
    public ThermalDiffusivity toUnit(ThermalDiffusivityUnit targetUnit) {
        double valueInSquareMeterPerSecond = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInSquareMeterPerSecond);
        return ThermalDiffusivity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public ThermalDiffusivity toUnit(String targetUnit) {
        ThermalDiffusivityUnit resolvedUnit = ThermalDiffusivityUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public ThermalDiffusivity withValue(double value) {
        return ThermalDiffusivity.of(value, unitType);
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
        return getInUnit(ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND);
    }

    public double getInSquareFeetPerSecond() {
        return getInUnit(ThermalDiffusivityUnits.SQUARE_FEET_PER_SECOND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThermalDiffusivity inputQuantity = (ThermalDiffusivity) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "ThermalDiffusivity{" + value + " " + unitType.getSymbol() + '}';
    }

}
