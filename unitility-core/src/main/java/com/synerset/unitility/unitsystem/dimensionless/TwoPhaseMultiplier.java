package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class TwoPhaseMultiplier implements CalculableQuantity<TwoPhaseMultiplierUnit, TwoPhaseMultiplier> {
    private final double value;
    private final double baseValue;
    private final TwoPhaseMultiplierUnit unitType;

    public TwoPhaseMultiplier(double value, TwoPhaseMultiplierUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    public static TwoPhaseMultiplier of(double value) {
        return new TwoPhaseMultiplier(value, TwoPhaseMultiplierUnits.DIMENSIONLESS);
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
    public TwoPhaseMultiplierUnit getUnit() {
        return unitType;
    }

    @Override
    public TwoPhaseMultiplier toBaseUnit() {
        return this;
    }

    @Override
    public TwoPhaseMultiplier toUnit(TwoPhaseMultiplierUnit targetUnit) {
        return this;
    }

    @Override
    public TwoPhaseMultiplier toUnit(String targetUnit) {
        return this;
    }

    @Override
    public TwoPhaseMultiplier withValue(double value) {
        return TwoPhaseMultiplier.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoPhaseMultiplier that = (TwoPhaseMultiplier) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "TwoPhaseMultiplier{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
