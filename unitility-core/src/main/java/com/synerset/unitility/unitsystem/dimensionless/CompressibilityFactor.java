package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class CompressibilityFactor implements CalculableQuantity<CompressibilityFactorUnit, CompressibilityFactor> {
    private final double value;
    private final double baseValue;
    private final CompressibilityFactorUnit unitType;

    public CompressibilityFactor(double value, CompressibilityFactorUnit unitType) {
        this.value = value;
        if (unitType == null) { unitType = CompressibilityFactorUnits.DIMENSIONLESS; }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static CompressibilityFactor of(double value) {
        return new CompressibilityFactor(value, CompressibilityFactorUnits.DIMENSIONLESS);
    }

    public static CompressibilityFactor ofIdealGas() {
        return new CompressibilityFactor(1.0, CompressibilityFactorUnits.DIMENSIONLESS);
    }

    // Implement CalculableQuantity interface methods
    @Override public double getValue() { return value; }
    @Override public double getBaseValue() { return baseValue; }
    @Override public CompressibilityFactorUnit getUnit() { return unitType; }
    
    @Override public CompressibilityFactor toBaseUnit() {
        return this;
    }
    
    @Override public CompressibilityFactor toUnit(CompressibilityFactorUnit targetUnit) {
        return this;
    }
    
    @Override public CompressibilityFactor toUnit(String targetUnit) {
        return this;
    }
    
    @Override public CompressibilityFactor withValue(double value) {
        return of(value);
    }

    // Convenience method for dimensionless quantity
    public double getZ() {
        return value;
    }

    /**
     * Checks if this compressibility factor represents ideal gas behavior.
     * @return true if Z equals 1.0 (ideal gas)
     */
    public boolean isIdealGas() {
        return Double.compare(value, 1.0) == 0;
    }

    /**
     * Calculates the deviation from ideal gas behavior as a percentage.
     * @return percentage deviation from Z = 1.0 (positive means Z > 1)
     */
    public double getDeviationFromIdealPercent() {
        return (value - 1.0) * 100.0;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompressibilityFactor)) return false;
        CompressibilityFactor that = (CompressibilityFactor) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override public int hashCode() { return Objects.hash(value); }

    @Override public String toString() {
        return "CompressibilityFactor{Z=" + value + "}";
    }
}