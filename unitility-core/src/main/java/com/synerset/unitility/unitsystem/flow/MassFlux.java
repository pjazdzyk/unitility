package com.synerset.unitility.unitsystem.flow;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class MassFlux implements CalculableQuantity<MassFluxUnit, MassFlux> {
    private final double value;
    private final double baseValue;
    private final MassFluxUnit unitType;

    public MassFlux(double value, MassFluxUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = MassFluxUnits.KILOGRAM_PER_SQUARE_METER_SECOND;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static MassFlux of(double value, MassFluxUnit unit) {
        return new MassFlux(value, unit);
    }

    public static MassFlux of(double value, String unitSymbol) {
        MassFluxUnit resolvedUnit = MassFluxUnits.fromSymbol(unitSymbol);
        return new MassFlux(value, resolvedUnit);
    }

    public static MassFlux ofKilogramsPerSquareMeterSecond(double value) {
        return new MassFlux(value, MassFluxUnits.KILOGRAM_PER_SQUARE_METER_SECOND);
    }

    public static MassFlux ofGramsPerSquareMeterSecond(double value) {
        return new MassFlux(value, MassFluxUnits.GRAM_PER_SQUARE_METER_SECOND);
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
    public MassFluxUnit getUnit() {
        return unitType;
    }

    @Override
    public MassFlux toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public MassFlux toUnit(MassFluxUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return MassFlux.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public MassFlux toUnit(String targetUnit) {
        MassFluxUnit resolvedUnit = MassFluxUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public MassFlux withValue(double value) {
        return MassFlux.of(value, unitType);
    }

    // Convert to target unit
    public MassFlux toKilogramsPerSquareMeterSecond() {
        return toUnit(MassFluxUnits.KILOGRAM_PER_SQUARE_METER_SECOND);
    }

    public MassFlux toGramsPerSquareMeterSecond() {
        return toUnit(MassFluxUnits.GRAM_PER_SQUARE_METER_SECOND);
    }

    // Get value in target unit
    public double getInKilogramsPerSquareMeterSecond() {
        return getInUnit(MassFluxUnits.KILOGRAM_PER_SQUARE_METER_SECOND);
    }

    public double getInGramsPerSquareMeterSecond() {
        return getInUnit(MassFluxUnits.GRAM_PER_SQUARE_METER_SECOND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MassFlux other = (MassFlux) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "MassFlux{" + value + " " + unitType.getSymbol() + '}';
    }
}
