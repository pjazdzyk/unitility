package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class PrandtlNumber implements CalculableQuantity<PrandtlNumberUnit, PrandtlNumber> {
    private final double value;
    private final double baseValue;
    private final PrandtlNumberUnits unitType;

    public PrandtlNumber(double value) {
        this.value = value;
        this.unitType = PrandtlNumberUnits.DIMENSIONLESS;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static PrandtlNumber of(double value) {
        return new PrandtlNumber(value);
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
    public PrandtlNumberUnit getUnitType() {
        return unitType;
    }

    @Override
    public PrandtlNumber toBaseUnit() {
        return this;
    }

    @Override
    public PrandtlNumber toUnit(PrandtlNumberUnit targetUnit) {
        return this;
    }

    @Override
    public PrandtlNumber toUnit(String targetUnit) {
        return this;
    }

    @Override
    public PrandtlNumber withValue(double value) {
        return PrandtlNumber.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrandtlNumber that = (PrandtlNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "PrandtlNumber{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
