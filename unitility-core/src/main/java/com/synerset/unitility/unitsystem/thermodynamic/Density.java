package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Density implements PhysicalQuantity<DensityUnit> {

    public static final Density PHYSICAL_MIN_LIMIT = Density.ofKilogramPerCubicMeter(0);
    private final double value;
    private final double baseValue;
    private final DensityUnit unitType;

    public Density(double value, DensityUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Density of(double value, DensityUnit unit) {
        return new Density(value, unit);
    }

    public static Density of(double value, String unitSymbol) {
        DensityUnit resolvedUnit = DensityUnits.fromSymbol(unitSymbol);
        return new Density(value, resolvedUnit);
    }
    
    public static Density ofKilogramPerCubicMeter(double value) {
        return new Density(value, DensityUnits.KILOGRAM_PER_CUBIC_METER);
    }

    public static Density ofPoundPerCubicFoot(double value) {
        return new Density(value, DensityUnits.POUND_PER_CUBIC_FOOT);
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
    public DensityUnit getUnitType() {
        return unitType;
    }

    @Override
    public Density toBaseUnit() {
        double valueInKGpM3 = unitType.toValueInBaseUnit(value);
        return Density.of(valueInKGpM3, DensityUnits.KILOGRAM_PER_CUBIC_METER);
    }

    @Override
    public Density toUnit(DensityUnit targetUnit) {
        double valueInKGpM3 = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKGpM3);
        return Density.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Density createNewWithValue(double value) {
        return Density.of(value, unitType);
    }

    // Convert to target unit
    public Density toKilogramPerCubicMeter() {
        return toUnit(DensityUnits.KILOGRAM_PER_CUBIC_METER);
    }

    public Density toPoundPerCubicFoot() {
        return toUnit(DensityUnits.POUND_PER_CUBIC_FOOT);
    }

    // Get value in target unit
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
        Density inputQuantity = (Density) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Density{" + value + " " + unitType.getSymbol() + '}';
    }
}
