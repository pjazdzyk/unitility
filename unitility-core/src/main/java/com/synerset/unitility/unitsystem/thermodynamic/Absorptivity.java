package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.common.RatioUnit;
import com.synerset.unitility.unitsystem.common.RatioUnits;

import java.util.Objects;

/**
 * Surface solar absorptivity {@code α}, a dimensionless ratio in {@code [0, 1]}: the fraction of
 * incident solar radiation a surface absorbs. Reuses {@link RatioUnit} (decimal / percent). It is a
 * named, distinct quantity (not a generic {@code Ratio}) so it cannot be cross-wired with emissivity.
 * The α/ε ratio governs the radiative equilibrium temperature of a sunlit surface.
 */
public class Absorptivity implements CalculableQuantity<RatioUnit, Absorptivity> {

    private final double value;
    private final double baseValue;
    private final RatioUnit unitType;

    public Absorptivity(double value, RatioUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = RatioUnits.DECIMAL;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Absorptivity of(double value, RatioUnit unit) {
        return new Absorptivity(value, unit);
    }

    public static Absorptivity of(double value, String unitSymbol) {
        RatioUnit resolvedUnit = RatioUnits.fromSymbol(unitSymbol);
        return new Absorptivity(value, resolvedUnit);
    }

    public static Absorptivity of(double value) {
        return new Absorptivity(value, RatioUnits.DECIMAL);
    }

    public static Absorptivity ofDecimal(double value) {
        return new Absorptivity(value, RatioUnits.DECIMAL);
    }

    public static Absorptivity ofPercentage(double value) {
        return new Absorptivity(value, RatioUnits.PERCENT);
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
    public Absorptivity toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Absorptivity toUnit(RatioUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return Absorptivity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Absorptivity toUnit(String targetUnit) {
        RatioUnit resolvedUnit = RatioUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Absorptivity withValue(double value) {
        return Absorptivity.of(value, unitType);
    }

    // Convert to target unit
    public Absorptivity toPercent() {
        return toUnit(RatioUnits.PERCENT);
    }

    public Absorptivity toDecimal() {
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
        Absorptivity other = (Absorptivity) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Absorptivity{" + value + " " + unitType.getSymbol() + '}';
    }
}
