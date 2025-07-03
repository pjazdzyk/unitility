package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class GenericDimensionless implements CalculableQuantity<GenericDimensionlessUnit, GenericDimensionless> {

    private final double value;
    private final double baseValue;
    private final GenericDimensionlessUnit unitType;

    public GenericDimensionless(double value, GenericDimensionlessUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static GenericDimensionless of(double value) {
        return new GenericDimensionless(value, GenericDimensionlessUnits.DIMENSIONLESS);
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
    public GenericDimensionlessUnit getUnit() {
        return unitType;
    }

    @Override
    public GenericDimensionless toBaseUnit() {
        return this;
    }

    @Override
    public GenericDimensionless toUnit(GenericDimensionlessUnit targetUnit) {
        return this;
    }

    @Override
    public GenericDimensionless toUnit(String targetUnit) {
        return this;
    }

    @Override
    public GenericDimensionless withValue(double value) {
        return GenericDimensionless.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericDimensionless that = (GenericDimensionless) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "GenericDimensionless{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
