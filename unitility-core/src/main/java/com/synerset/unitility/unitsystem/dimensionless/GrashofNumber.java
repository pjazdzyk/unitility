package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class GrashofNumber implements CalculableQuantity<GrashofNumberUnit, GrashofNumber> {
    private final double value;
    private final double baseValue;
    private final GrashofNumberUnit unitType;

    public GrashofNumber(double value, GrashofNumberUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static GrashofNumber of(double value) {
        return new GrashofNumber(value, GrashofNumberUnits.DIMENSIONLESS);
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
    public GrashofNumberUnit getUnit() {
        return unitType;
    }

    @Override
    public GrashofNumber toBaseUnit() {
        return this;
    }

    @Override
    public GrashofNumber toUnit(GrashofNumberUnit targetUnit) {
        return this;
    }

    @Override
    public GrashofNumber toUnit(String targetUnit) {
        return this;
    }

    @Override
    public GrashofNumber withValue(double value) {
        return GrashofNumber.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrashofNumber that = (GrashofNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "GrashofNumber{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
