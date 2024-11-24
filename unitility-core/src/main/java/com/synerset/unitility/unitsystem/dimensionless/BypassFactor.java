package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class BypassFactor implements CalculableQuantity<BypassFactorUnit, BypassFactor> {

    public static final BypassFactor BF_PHYSICAL_MIN = BypassFactor.of(0);
    public static final BypassFactor BF_PHYSICAL_MAX = BypassFactor.of(1);
    public static final BypassFactor BF_HVAC_MIN = BypassFactor.of(0.01);

    private final double value;
    private final double baseValue;
    private final BypassFactorUnit unitType;

    public BypassFactor(double value) {
        this.value = value;
        this.unitType = BypassFactorUnits.DIMENSIONLESS;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static BypassFactor of(double value) {
        return new BypassFactor(value);
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
    public BypassFactorUnit getUnit() {
        return unitType;
    }

    @Override
    public BypassFactor toBaseUnit() {
        return this;
    }

    @Override
    public BypassFactor toUnit(BypassFactorUnit targetUnit) {
        return this;
    }

    @Override
    public BypassFactor toUnit(String targetUnit) {
        return this;
    }

    @Override
    public BypassFactor withValue(double value) {
        return BypassFactor.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BypassFactor that = (BypassFactor) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "BypassFactor{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
