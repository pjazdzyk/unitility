package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.common.RatioUnit;
import com.synerset.unitility.unitsystem.common.RatioUnits;

import java.util.Objects;

/**
 * Surface infrared emissivity {@code ε}, a dimensionless ratio in {@code [0, 1]}: the fraction of
 * blackbody radiation a surface emits at its temperature. Reuses {@link RatioUnit} (decimal / percent).
 * It is a named, distinct quantity (not a generic {@code Ratio}) so it cannot be cross-wired with
 * absorptivity, reflectivity, or any other ratio.
 */
public class Emissivity implements CalculableQuantity<RatioUnit, Emissivity> {

    private final double value;
    private final double baseValue;
    private final RatioUnit unitType;

    public Emissivity(double value, RatioUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = RatioUnits.DECIMAL;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Emissivity of(double value, RatioUnit unit) {
        return new Emissivity(value, unit);
    }

    public static Emissivity of(double value, String unitSymbol) {
        RatioUnit resolvedUnit = RatioUnits.fromSymbol(unitSymbol);
        return new Emissivity(value, resolvedUnit);
    }

    public static Emissivity of(double value) {
        return new Emissivity(value, RatioUnits.DECIMAL);
    }

    public static Emissivity ofDecimal(double value) {
        return new Emissivity(value, RatioUnits.DECIMAL);
    }

    public static Emissivity ofPercentage(double value) {
        return new Emissivity(value, RatioUnits.PERCENT);
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
    public RatioUnit getUnit() {
        return unitType;
    }

    @Override
    public Emissivity toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Emissivity toUnit(RatioUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return Emissivity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Emissivity toUnit(String targetUnit) {
        RatioUnit resolvedUnit = RatioUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Emissivity withValue(double value) {
        return Emissivity.of(value, unitType);
    }

    // Convert to target unit
    public Emissivity toPercent() {
        return toUnit(RatioUnits.PERCENT);
    }

    public Emissivity toDecimal() {
        return toUnit(RatioUnits.DECIMAL);
    }

    // Get value in target unit
    public double getInPercent() {
        return getInUnit(RatioUnits.PERCENT);
    }

    public double getInDecimal() {
        return getInUnit(RatioUnits.DECIMAL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emissivity other = (Emissivity) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Emissivity{" + value + " " + unitType.getSymbol() + '}';
    }
}
