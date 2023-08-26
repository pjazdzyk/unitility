package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class GrashofNumber implements PhysicalQuantity<GrashofNumber> {
    private final double value;
    private final double baseValue;
    private final Unit<GrashofNumber> unit;

    public GrashofNumber(double value) {
        this.value = value;
        this.unit = GrashofNumberUnits.DIMENSIONLESS;
        this.baseValue = unit.toValueInBaseUnit(value);
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
    public Unit<GrashofNumber> getUnit() {
        return unit;
    }

    @Override
    public GrashofNumber toBaseUnit() {
        return this;
    }

    @Override
    public PhysicalQuantity<GrashofNumber> toUnit(Unit<GrashofNumber> targetUnit) {
        return this;
    }

    @Override
    public GrashofNumber createNewWithValue(double value) {
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
        return "GrashofNumber{" + value + " " + unit.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    // Static factory methods
    public static GrashofNumber of(double value) {
        return new GrashofNumber(value);
    }

}
