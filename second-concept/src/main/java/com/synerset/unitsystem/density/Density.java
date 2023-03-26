package com.synerset.unitsystem.density;

import com.synerset.unitsystem.PhysicalQuantity;
import com.synerset.unitsystem.Unit;

import java.util.Objects;

public final class Density implements PhysicalQuantity<Density> {

    public static final byte TO_STRING_PRECISION = 3;
    private final double value;
    private final Unit<Density> unit;

    private Density(double value, Unit<Density> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Density> getUnit() {
        return unit;
    }

    @Override
    public Density toBaseUnit() {
        double valueInKGpM3 = unit.toBaseUnit(value);
        return Density.of(valueInKGpM3, DensityUnits.KILOGRAM_PER_CUBIC_METER);
    }

    @Override
    public Density toUnit(Unit<Density> targetUnit) {
        double valueInKGpM3 = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInKGpM3);
        return Density.of(valueInTargetUnit, targetUnit);
    }

    // Custom converter methods, for most popular units
    public Density toKilogramPerCubicMeter() {
        return toUnit(DensityUnits.KILOGRAM_PER_CUBIC_METER);
    }

    public Density toPoundPerCubicFoot() {
        return toUnit(DensityUnits.POUND_PER_CUBIC_FOOT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Density that = (Density) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return String.format("%." + TO_STRING_PRECISION + "f %s", value, unit.getSymbol());
    }

    public static Density of(double value, Unit<Density> unit) {
        return new Density(value, unit);
    }

    public static Density ofKilogramPerCubicMeter(double value) {
        return new Density(value, DensityUnits.KILOGRAM_PER_CUBIC_METER);
    }

    public static Density ofPoundPerCubicFoot(double value) {
        return new Density(value, DensityUnits.POUND_PER_CUBIC_FOOT);
    }
}
