package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class HeatFlux implements CalculableQuantity<HeatFluxUnit, HeatFlux> {
    private final double value;
    private final double baseValue;
    private final HeatFluxUnit unitType;

    public HeatFlux(double value, HeatFluxUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = HeatFluxUnits.WATTS_PER_SQUARE_METER;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static HeatFlux of(double value, HeatFluxUnit unit) {
        return new HeatFlux(value, unit);
    }

    public static HeatFlux of(double value, String unitSymbol) {
        HeatFluxUnit resolvedUnit = HeatFluxUnits.fromSymbol(unitSymbol);
        return new HeatFlux(value, resolvedUnit);
    }

    public static HeatFlux ofWattsPerSquareMeter(double value) {
        return new HeatFlux(value, HeatFluxUnits.WATTS_PER_SQUARE_METER);
    }

    public static HeatFlux ofKilowattsPerSquareMeter(double value) {
        return new HeatFlux(value, HeatFluxUnits.KILOWATTS_PER_SQUARE_METER);
    }

    public static HeatFlux ofBTUPerHourSquareFoot(double value) {
        return new HeatFlux(value, HeatFluxUnits.BTU_PER_HOUR_SQUARE_FOOT);
    }

    public static HeatFlux ofBTUPerMinuteSquareFoot(double value) {
        return new HeatFlux(value, HeatFluxUnits.BTU_PER_MINUTE_SQUARE_FOOT);
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
    public HeatFluxUnit getUnit() {
        return unitType;
    }

    @Override
    public HeatFlux toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public HeatFlux toUnit(HeatFluxUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return HeatFlux.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public HeatFlux toUnit(String targetUnit) {
        HeatFluxUnit resolvedUnit = HeatFluxUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public HeatFlux withValue(double value) {
        return HeatFlux.of(value, unitType);
    }

    // Convert to target unit
    public HeatFlux toWattsPerSquareMeter() {
        return toUnit(HeatFluxUnits.WATTS_PER_SQUARE_METER);
    }

    public HeatFlux toKilowattsPerSquareMeter() {
        return toUnit(HeatFluxUnits.KILOWATTS_PER_SQUARE_METER);
    }

    public HeatFlux toBTUPerHourSquareFoot() {
        return toUnit(HeatFluxUnits.BTU_PER_HOUR_SQUARE_FOOT);
    }

    public HeatFlux toBTUPerMinuteSquareFoot() {
        return toUnit(HeatFluxUnits.BTU_PER_MINUTE_SQUARE_FOOT);
    }

    // Get value in target unit
    public double getInWattsPerSquareMeter() {
        return getInUnit(HeatFluxUnits.WATTS_PER_SQUARE_METER);
    }

    public double getInKilowattsPerSquareMeter() {
        return getInUnit(HeatFluxUnits.KILOWATTS_PER_SQUARE_METER);
    }

    public double getInBTUPerHourSquareFoot() {
        return getInUnit(HeatFluxUnits.BTU_PER_HOUR_SQUARE_FOOT);
    }

    public double getInBTUPerMinuteSquareFoot() {
        return getInUnit(HeatFluxUnits.BTU_PER_MINUTE_SQUARE_FOOT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeatFlux other = (HeatFlux) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "HeatFlux{" + value + " " + unitType.getSymbol() + '}';
    }
}
