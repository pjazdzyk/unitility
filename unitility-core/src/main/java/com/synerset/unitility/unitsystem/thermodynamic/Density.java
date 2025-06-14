package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Density implements CalculableQuantity<DensityUnit, Density> {

    public static final Density PHYSICAL_MIN_LIMIT = Density.ofKilogramPerCubicMeter(0);
    private final double value;
    private final double baseValue;
    private final DensityUnit unitType;

    public Density(double value, DensityUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = DensityUnits.KILOGRAM_PER_CUBIC_METER;
        }
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
    public DensityUnit getUnit() {
        return unitType;
    }

    @Override
    public Density toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Density toUnit(DensityUnit targetUnit) {
        double valueInKGpM3 = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKGpM3);
        return Density.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Density toUnit(String targetUnit) {
        DensityUnit resolvedUnit = DensityUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Density withValue(double value) {
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
        return getInUnit(DensityUnits.KILOGRAM_PER_CUBIC_METER);
    }

    public double getInPoundsPerCubicFoot() {
        return getInUnit(DensityUnits.POUND_PER_CUBIC_FOOT);
    }

    public double getInPoundsPerCubicInches() {
        return getInUnit(DensityUnits.POUND_PER_CUBIC_INCH);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Density inputQuantity = (Density) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
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
