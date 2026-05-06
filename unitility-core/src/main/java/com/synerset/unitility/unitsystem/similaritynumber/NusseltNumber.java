package com.synerset.unitility.unitsystem.similaritynumber;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class NusseltNumber implements CalculableQuantity<NusseltNumberUnit, NusseltNumber> {
    private final double value;
    private final double baseValue;
    private final NusseltNumberUnit unitType;

    public NusseltNumber(double value, NusseltNumberUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static NusseltNumber of(double value) {
        return new NusseltNumber(value, NusseltNumberUnits.DIMENSIONLESS);
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
    public NusseltNumberUnit getUnit() {
        return unitType;
    }

    @Override
    public NusseltNumber toBaseUnit() {
        return this;
    }

    @Override
    public NusseltNumber toUnit(NusseltNumberUnit targetUnit) {
        return this;
    }

    @Override
    public NusseltNumber toUnit(String targetUnit) {
        return this;
    }

    @Override
    public NusseltNumber withValue(double value) {
        return NusseltNumber.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NusseltNumber that = (NusseltNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "NusseltNumber{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
