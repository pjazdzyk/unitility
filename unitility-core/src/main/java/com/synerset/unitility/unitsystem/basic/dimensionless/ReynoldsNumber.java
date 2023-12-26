package com.synerset.unitility.unitsystem.basic.dimensionless;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class ReynoldsNumber implements PhysicalQuantity<ReynoldsNumberUnit> {

    public static final ReynoldsNumber PIPE_TURBULENT_THRESHOLD = ReynoldsNumber.of(2300);
    public static final ReynoldsNumber PLATE_TURBULENT_THRESHOLD = ReynoldsNumber.of(5 * 10E5);
    public static final ReynoldsNumber AERO_TURBULENT_THRESHOLD = ReynoldsNumber.of(2 * 10E6);
    private final double value;
    private final double baseValue;
    private final ReynoldsNumberUnit unitType;

    public ReynoldsNumber(double value) {
        this.value = value;
        this.unitType = ReynoldsNumberUnits.DIMENSIONLESS;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static ReynoldsNumber of(double value) {
        return new ReynoldsNumber(value);
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
    public ReynoldsNumberUnit getUnitType() {
        return unitType;
    }

    @Override
    public ReynoldsNumber toBaseUnit() {
        return this;
    }

    @Override
    public ReynoldsNumber toUnit(ReynoldsNumberUnit targetUnit) {
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ReynoldsNumber withValue(double value) {
        return ReynoldsNumber.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReynoldsNumber that = (ReynoldsNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "ReynoldsNumber{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
