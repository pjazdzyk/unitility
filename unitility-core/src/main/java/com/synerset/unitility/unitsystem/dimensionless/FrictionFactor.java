package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class FrictionFactor implements CalculableQuantity<FrictionFactorUnit, FrictionFactor> {

    private final double value;
    private final double baseValue;
    private final FrictionFactorUnit unitType;

    public FrictionFactor(double value) {
        this.value = value;
        this.unitType = FrictionFactorUnits.DIMENSIONLESS;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static FrictionFactor of(double value) {
        return new FrictionFactor(value);
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
    public FrictionFactorUnit getUnit() {
        return unitType;
    }

    @Override
    public FrictionFactor toBaseUnit() {
        return this;
    }

    @Override
    public FrictionFactor toUnit(FrictionFactorUnit targetUnit) {
        return this;
    }

    @Override
    public FrictionFactor toUnit(String targetUnit) {
        return this;
    }

    @Override
    public FrictionFactor withValue(double value) {
        return FrictionFactor.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrictionFactor that = (FrictionFactor) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "FrictionFactor{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
