package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/**
 * Relative density (specific gravity) — the dimensionless ratio of a substance's density to that of a
 * reference (gas vs. dry air, liquid vs. water). A dedicated dimensionless quantity so APIs reporting
 * a relative density are self-describing rather than using a generic dimensionless number.
 */
public class RelativeDensity implements CalculableQuantity<RelativeDensityUnit, RelativeDensity> {

    private final double value;
    private final double baseValue;
    private final RelativeDensityUnit unitType;

    public RelativeDensity(double value, RelativeDensityUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = RelativeDensityUnits.DIMENSIONLESS;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static RelativeDensity of(double value, RelativeDensityUnit unit) {
        return new RelativeDensity(value, unit);
    }

    public static RelativeDensity of(double value, String unitSymbol) {
        RelativeDensityUnit resolvedUnit = RelativeDensityUnits.fromSymbol(unitSymbol);
        return new RelativeDensity(value, resolvedUnit);
    }

    public static RelativeDensity of(double value) {
        return new RelativeDensity(value, RelativeDensityUnits.DIMENSIONLESS);
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
    public RelativeDensityUnit getUnit() {
        return unitType;
    }

    @Override
    public RelativeDensity toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public RelativeDensity toUnit(RelativeDensityUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return RelativeDensity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public RelativeDensity toUnit(String targetUnit) {
        RelativeDensityUnit resolvedUnit = RelativeDensityUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public RelativeDensity withValue(double value) {
        return RelativeDensity.of(value, unitType);
    }

    public double getInDimensionless() {
        return getInUnit(RelativeDensityUnits.DIMENSIONLESS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelativeDensity inputQuantity = (RelativeDensity) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "RelativeDensity{" + value + " " + unitType.getSymbol() + '}';
    }
}
