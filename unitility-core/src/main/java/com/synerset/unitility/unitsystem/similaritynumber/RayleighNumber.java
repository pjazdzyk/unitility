package com.synerset.unitility.unitsystem.similaritynumber;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class RayleighNumber implements CalculableQuantity<RayleighNumberUnit, RayleighNumber> {
    private final double value;
    private final double baseValue;
    private final RayleighNumberUnit unitType;

    public RayleighNumber(double value, RayleighNumberUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    public static RayleighNumber of(double value) {
        return new RayleighNumber(value, RayleighNumberUnits.DIMENSIONLESS);
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
    public RayleighNumberUnit getUnit() {
        return unitType;
    }

    @Override
    public RayleighNumber toBaseUnit() {
        return this;
    }

    @Override
    public RayleighNumber toUnit(RayleighNumberUnit targetUnit) {
        return this;
    }

    @Override
    public RayleighNumber toUnit(String targetUnit) {
        return this;
    }

    @Override
    public RayleighNumber withValue(double value) {
        return RayleighNumber.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RayleighNumber that = (RayleighNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "RayleighNumber{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
