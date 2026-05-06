package com.synerset.unitility.unitsystem.similaritynumber;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class BiotNumber implements CalculableQuantity<BiotNumberUnit, BiotNumber> {
    private final double value;
    private final double baseValue;
    private final BiotNumberUnit unitType;

    public BiotNumber(double value, BiotNumberUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    public static BiotNumber of(double value) {
        return new BiotNumber(value, BiotNumberUnits.DIMENSIONLESS);
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
    public BiotNumberUnit getUnit() {
        return unitType;
    }

    @Override
    public BiotNumber toBaseUnit() {
        return this;
    }

    @Override
    public BiotNumber toUnit(BiotNumberUnit targetUnit) {
        return this;
    }

    @Override
    public BiotNumber toUnit(String targetUnit) {
        return this;
    }

    @Override
    public BiotNumber withValue(double value) {
        return BiotNumber.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiotNumber that = (BiotNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "BiotNumber{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
