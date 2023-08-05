package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class BypassFactor implements PhysicalQuantity<BypassFactor> {

    public static final BypassFactor BYPASS_MIN_VALUE = BypassFactor.of(0);
    public static final BypassFactor BYPASS_MAX_VALUE = BypassFactor.of(1);

    private final double value;
    private final Unit<BypassFactor> unit;

    private BypassFactor(double value) {
        this.value = value;
        this.unit = BypassFactorUnits.DIMENSIONLESS;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<BypassFactor> getUnit() {
        return unit;
    }

    @Override
    public BypassFactor toBaseUnit() {
        return this;
    }

    @Override
    public PhysicalQuantity<BypassFactor> toUnit(Unit<BypassFactor> targetUnit) {
        return this;
    }

    @Override
    public PhysicalQuantity<BypassFactor> createNewWithValue(double value) {
        return BypassFactor.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BypassFactor that = (BypassFactor) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "BypassFactor{" + value + " " + unit.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static BypassFactor of(double value) {
        return new BypassFactor(value);
    }

}
