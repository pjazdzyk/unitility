package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class HeatTransferCoefficient implements CalculableQuantity<HeatTransferCoefficientUnit, HeatTransferCoefficient> {
    private final double value;
    private final double baseValue;
    private final HeatTransferCoefficientUnit unitType;

    public HeatTransferCoefficient(double value, HeatTransferCoefficientUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = HeatTransferCoefficientUnits.WATTS_PER_SQUARE_METER_KELVIN;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static HeatTransferCoefficient of(double value, HeatTransferCoefficientUnit unit) {
        return new HeatTransferCoefficient(value, unit);
    }

    public static HeatTransferCoefficient of(double value, String unitSymbol) {
        HeatTransferCoefficientUnit resolvedUnit = HeatTransferCoefficientUnits.fromSymbol(unitSymbol);
        return new HeatTransferCoefficient(value, resolvedUnit);
    }

    public static HeatTransferCoefficient ofWattsPerSquareMeterKelvin(double value) {
        return new HeatTransferCoefficient(value, HeatTransferCoefficientUnits.WATTS_PER_SQUARE_METER_KELVIN);
    }

    public static HeatTransferCoefficient ofKilowattsPerSquareMeterKelvin(double value) {
        return new HeatTransferCoefficient(value, HeatTransferCoefficientUnits.KILOWATTS_PER_SQUARE_METER_KELVIN);
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
    public HeatTransferCoefficientUnit getUnit() {
        return unitType;
    }

    @Override
    public HeatTransferCoefficient toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public HeatTransferCoefficient toUnit(HeatTransferCoefficientUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return HeatTransferCoefficient.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public HeatTransferCoefficient toUnit(String targetUnit) {
        HeatTransferCoefficientUnit resolvedUnit = HeatTransferCoefficientUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public HeatTransferCoefficient withValue(double value) {
        return HeatTransferCoefficient.of(value, unitType);
    }

    // Convert to target unit
    public HeatTransferCoefficient toWattsPerSquareMeterKelvin() {
        return toUnit(HeatTransferCoefficientUnits.WATTS_PER_SQUARE_METER_KELVIN);
    }

    public HeatTransferCoefficient toKilowattsPerSquareMeterKelvin() {
        return toUnit(HeatTransferCoefficientUnits.KILOWATTS_PER_SQUARE_METER_KELVIN);
    }

    // Get value in target unit
    public double getInWattsPerSquareMeterKelvin() {
        return getInUnit(HeatTransferCoefficientUnits.WATTS_PER_SQUARE_METER_KELVIN);
    }

    public double getInKilowattsPerSquareMeterKelvin() {
        return getInUnit(HeatTransferCoefficientUnits.KILOWATTS_PER_SQUARE_METER_KELVIN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeatTransferCoefficient other = (HeatTransferCoefficient) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "HeatTransferCoefficient{" + value + " " + unitType.getSymbol() + '}';
    }
}
