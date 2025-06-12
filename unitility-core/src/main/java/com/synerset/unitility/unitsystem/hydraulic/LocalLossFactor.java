package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class LocalLossFactor implements CalculableQuantity<LocalLossFactorUnit, LocalLossFactor> {

    public static final LocalLossFactor LLF_PHYSICAL_MIN = LocalLossFactor.of(0);

    private final double value;
    private final double baseValue;
    private final LocalLossFactorUnit unitType;

    public LocalLossFactor(double value, LocalLossFactorUnits unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static LocalLossFactor of(double value) {
        return new LocalLossFactor(value, LocalLossFactorUnits.DIMENSIONLESS);
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
    public LocalLossFactorUnit getUnit() {
        return unitType;
    }

    @Override
    public LocalLossFactor toBaseUnit() {
        return this;
    }

    @Override
    public LocalLossFactor toUnit(LocalLossFactorUnit targetUnit) {
        return this;
    }

    @Override
    public LocalLossFactor toUnit(String targetUnit) {
        return this;
    }

    @Override
    public LocalLossFactor withValue(double value) {
        return LocalLossFactor.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalLossFactor that = (LocalLossFactor) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "LocalLossFactor{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
