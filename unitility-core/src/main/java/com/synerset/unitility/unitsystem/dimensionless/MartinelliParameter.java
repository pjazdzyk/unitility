package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class MartinelliParameter implements CalculableQuantity<MartinelliParameterUnit, MartinelliParameter> {
    private final double value;
    private final double baseValue;
    private final MartinelliParameterUnit unitType;

    public MartinelliParameter(double value, MartinelliParameterUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    public static MartinelliParameter of(double value) {
        return new MartinelliParameter(value, MartinelliParameterUnits.DIMENSIONLESS);
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
    public MartinelliParameterUnit getUnit() {
        return unitType;
    }

    @Override
    public MartinelliParameter toBaseUnit() {
        return this;
    }

    @Override
    public MartinelliParameter toUnit(MartinelliParameterUnit targetUnit) {
        return this;
    }

    @Override
    public MartinelliParameter toUnit(String targetUnit) {
        return this;
    }

    @Override
    public MartinelliParameter withValue(double value) {
        return MartinelliParameter.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MartinelliParameter that = (MartinelliParameter) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "MartinelliParameter{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
