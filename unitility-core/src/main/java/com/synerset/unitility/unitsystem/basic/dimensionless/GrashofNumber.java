package com.synerset.unitility.unitsystem.basic.dimensionless;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class GrashofNumber implements PhysicalQuantity<GrashofNumberUnit> {
    private final double value;
    private final double baseValue;
    private final GrashofNumberUnit unitType;

    public GrashofNumber(double value) {
        this.value = value;
        this.unitType = GrashofNumberUnits.DIMENSIONLESS;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static GrashofNumber of(double value) {
        return new GrashofNumber(value);
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
    public GrashofNumberUnit getUnitType() {
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
    @SuppressWarnings("unchecked")
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
