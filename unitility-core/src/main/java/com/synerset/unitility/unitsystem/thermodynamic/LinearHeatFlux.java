package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class LinearHeatFlux implements CalculableQuantity<LinearHeatFluxUnit, LinearHeatFlux> {
    private final double value;
    private final double baseValue;
    private final LinearHeatFluxUnit unitType;

    public LinearHeatFlux(double value, LinearHeatFluxUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = LinearHeatFluxUnits.WATTS_PER_METER;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static LinearHeatFlux of(double value, LinearHeatFluxUnit unit) {
        return new LinearHeatFlux(value, unit);
    }

    public static LinearHeatFlux of(double value, String unitSymbol) {
        LinearHeatFluxUnit resolvedUnit = LinearHeatFluxUnits.fromSymbol(unitSymbol);
        return new LinearHeatFlux(value, resolvedUnit);
    }

    public static LinearHeatFlux ofWattsPerMeter(double value) {
        return new LinearHeatFlux(value, LinearHeatFluxUnits.WATTS_PER_METER);
    }

    public static LinearHeatFlux ofKilowattsPerMeter(double value) {
        return new LinearHeatFlux(value, LinearHeatFluxUnits.KILOWATTS_PER_METER);
    }

    public static LinearHeatFlux ofBTUPerHourFoot(double value) {
        return new LinearHeatFlux(value, LinearHeatFluxUnits.BTU_PER_HOUR_FOOT);
    }

    public static LinearHeatFlux ofBTUPerMinuteFoot(double value) {
        return new LinearHeatFlux(value, LinearHeatFluxUnits.BTU_PER_MINUTE_FOOT);
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
    public LinearHeatFluxUnit getUnit() {
        return unitType;
    }

    @Override
    public LinearHeatFlux toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public LinearHeatFlux toUnit(LinearHeatFluxUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return LinearHeatFlux.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public LinearHeatFlux toUnit(String targetUnit) {
        LinearHeatFluxUnit resolvedUnit = LinearHeatFluxUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public LinearHeatFlux withValue(double value) {
        return LinearHeatFlux.of(value, unitType);
    }

    // Convert to target unit
    public LinearHeatFlux toWattsPerMeter() {
        return toUnit(LinearHeatFluxUnits.WATTS_PER_METER);
    }

    public LinearHeatFlux toKilowattsPerMeter() {
        return toUnit(LinearHeatFluxUnits.KILOWATTS_PER_METER);
    }

    public LinearHeatFlux toBTUPerHourFoot() {
        return toUnit(LinearHeatFluxUnits.BTU_PER_HOUR_FOOT);
    }

    public LinearHeatFlux toBTUPerMinuteFoot() {
        return toUnit(LinearHeatFluxUnits.BTU_PER_MINUTE_FOOT);
    }

    // Get value in target unit
    public double getInWattsPerMeter() {
        return getInUnit(LinearHeatFluxUnits.WATTS_PER_METER);
    }

    public double getInKilowattsPerMeter() {
        return getInUnit(LinearHeatFluxUnits.KILOWATTS_PER_METER);
    }

    public double getInBTUPerHourFoot() {
        return getInUnit(LinearHeatFluxUnits.BTU_PER_HOUR_FOOT);
    }

    public double getInBTUPerMinuteFoot() {
        return getInUnit(LinearHeatFluxUnits.BTU_PER_MINUTE_FOOT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinearHeatFlux other = (LinearHeatFlux) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "LinearHeatFlux{" + value + " " + unitType.getSymbol() + '}';
    }
}
