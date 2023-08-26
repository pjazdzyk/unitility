package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class Density implements PhysicalQuantity<Density> {

    private final double value;
    private final double baseValue;
    private final Unit<Density> unit;

    public Density(double value, Unit<Density> unit) {
        this.value = value;
        this.unit = unit;
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
    public Unit<Density> getUnit() {
        return unit;
    }

    @Override
    public Density toBaseUnit() {
        double valueInKGpM3 = unit.toValueInBaseUnit(value);
        return Density.of(valueInKGpM3, DensityUnits.KILOGRAM_PER_CUBIC_METER);
    }

    @Override
    public Density toUnit(Unit<Density> targetUnit) {
        double valueInKGpM3 = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKGpM3);
        return Density.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Density createNewWithValue(double value) {
        return Density.of(value, unit);
    }

    // Convert to target unit
    public double getInKilogramsPerCubicMeters() {
        return getIn(DensityUnits.KILOGRAM_PER_CUBIC_METER);
    }

    public double getInPoundsPerCubicFoot() {
        return getIn(DensityUnits.POUND_PER_CUBIC_FOOT);
    }

    public double getInPoundsPerCubicInches() {
        return getIn(DensityUnits.POUND_PER_CUBIC_INCH);
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
        return "Density{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
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
