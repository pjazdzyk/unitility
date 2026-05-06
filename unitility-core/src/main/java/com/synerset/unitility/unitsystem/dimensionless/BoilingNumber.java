package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class BoilingNumber implements CalculableQuantity<BoilingNumberUnit, BoilingNumber> {
    private final double value;
    private final double baseValue;
    private final BoilingNumberUnit unitType;

    public BoilingNumber(double value, BoilingNumberUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    public static BoilingNumber of(double value) {
        return new BoilingNumber(value, BoilingNumberUnits.DIMENSIONLESS);
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
    public BoilingNumberUnit getUnit() {
        return unitType;
    }

    @Override
    public BoilingNumber toBaseUnit() {
        return this;
    }

    @Override
    public BoilingNumber toUnit(BoilingNumberUnit targetUnit) {
        return this;
    }

    @Override
    public BoilingNumber toUnit(String targetUnit) {
        return this;
    }

    @Override
    public BoilingNumber withValue(double value) {
        return BoilingNumber.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoilingNumber that = (BoilingNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "BoilingNumber{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
