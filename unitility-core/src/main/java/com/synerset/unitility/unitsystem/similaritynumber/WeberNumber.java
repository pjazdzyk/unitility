package com.synerset.unitility.unitsystem.similaritynumber;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class WeberNumber implements CalculableQuantity<WeberNumberUnit, WeberNumber> {
    private final double value;
    private final double baseValue;
    private final WeberNumberUnit unitType;

    public WeberNumber(double value, WeberNumberUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    public static WeberNumber of(double value) {
        return new WeberNumber(value, WeberNumberUnits.DIMENSIONLESS);
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
    public WeberNumberUnit getUnit() {
        return unitType;
    }

    @Override
    public WeberNumber toBaseUnit() {
        return this;
    }

    @Override
    public WeberNumber toUnit(WeberNumberUnit targetUnit) {
        return this;
    }

    @Override
    public WeberNumber toUnit(String targetUnit) {
        return this;
    }

    @Override
    public WeberNumber withValue(double value) {
        return WeberNumber.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeberNumber that = (WeberNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "WeberNumber{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
