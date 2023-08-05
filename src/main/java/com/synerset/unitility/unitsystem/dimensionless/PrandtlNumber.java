package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class PrandtlNumber implements PhysicalQuantity<PrandtlNumber> {
    private final double value;
    private final Unit<PrandtlNumber> unit;

    private PrandtlNumber(double value) {
        this.value = value;
        this.unit = PrandtlNumberUnits.DIMENSIONLESS;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<PrandtlNumber> getUnit() {
        return unit;
    }

    @Override
    public PrandtlNumber toBaseUnit() {
        return this;
    }

    @Override
    public PhysicalQuantity<PrandtlNumber> toUnit(Unit<PrandtlNumber> targetUnit) {
        return this;
    }

    @Override
    public PhysicalQuantity<PrandtlNumber> createNewWithValue(double value) {
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
        return "PrandtlNumber{" + value + " " + unit.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static PrandtlNumber of(double value) {
        return new PrandtlNumber(value);
    }

}
