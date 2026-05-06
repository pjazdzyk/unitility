package com.synerset.unitility.unitsystem.similaritynumber;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class PecletNumber implements CalculableQuantity<PecletNumberUnit, PecletNumber> {
    private final double value;
    private final double baseValue;
    private final PecletNumberUnit unitType;

    public PecletNumber(double value, PecletNumberUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    public static PecletNumber of(double value) {
        return new PecletNumber(value, PecletNumberUnits.DIMENSIONLESS);
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
    public PecletNumberUnit getUnit() {
        return unitType;
    }

    @Override
    public PecletNumber toBaseUnit() {
        return this;
    }

    @Override
    public PecletNumber toUnit(PecletNumberUnit targetUnit) {
        return this;
    }

    @Override
    public PecletNumber toUnit(String targetUnit) {
        return this;
    }

    @Override
    public PecletNumber withValue(double value) {
        return PecletNumber.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PecletNumber that = (PecletNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "PecletNumber{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
