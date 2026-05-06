package com.synerset.unitility.unitsystem.similaritynumber;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class BondNumber implements CalculableQuantity<BondNumberUnit, BondNumber> {
    private final double value;
    private final double baseValue;
    private final BondNumberUnit unitType;

    public BondNumber(double value, BondNumberUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    public static BondNumber of(double value) {
        return new BondNumber(value, BondNumberUnits.DIMENSIONLESS);
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
    public BondNumberUnit getUnit() {
        return unitType;
    }

    @Override
    public BondNumber toBaseUnit() {
        return this;
    }

    @Override
    public BondNumber toUnit(BondNumberUnit targetUnit) {
        return this;
    }

    @Override
    public BondNumber toUnit(String targetUnit) {
        return this;
    }

    @Override
    public BondNumber withValue(double value) {
        return BondNumber.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BondNumber that = (BondNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "BondNumber{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
