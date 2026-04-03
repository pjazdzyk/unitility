package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class MolarFraction implements CalculableQuantity<MolarFractionUnit, MolarFraction> {
    private final double value;
    private final double baseValue;
    private final MolarFractionUnit unitType;

    public MolarFraction(double value, MolarFractionUnit unitType) {
        this.value = value;
        if (unitType == null) { unitType = MolarFractionUnits.DIMENSIONLESS; }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static MolarFraction of(double value, MolarFractionUnit unit) {
        return new MolarFraction(value, unit);
    }

    public static MolarFraction of(double value, String unitSymbol) {
        return new MolarFraction(value, MolarFractionUnits.fromSymbol(unitSymbol));
    }

    // Unit-specific factory methods
    public static MolarFraction ofDimensionless(double value) {
        return new MolarFraction(value, MolarFractionUnits.DIMENSIONLESS);
    }

    // Implement CalculableQuantity interface methods
    @Override
    public double getValue() { return value; }

    @Override
    public double getBaseValue() { return baseValue; }

    @Override
    public MolarFractionUnit getUnit() { return unitType; }

    @Override
    public MolarFraction toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }

    @Override
    public MolarFraction toUnit(MolarFractionUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }

    @Override
    public MolarFraction toUnit(String targetUnit) {
        return toUnit(MolarFractionUnits.fromSymbol(targetUnit));
    }

    @Override
    public MolarFraction withValue(double value) {
        return of(value, unitType);
    }

    // Conversion methods
    public double getInDimensionless() {
        return getInUnit(MolarFractionUnits.DIMENSIONLESS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MolarFraction)) return false;
        MolarFraction other = (MolarFraction) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override
    public int hashCode() { return Objects.hash(baseValue, unitType.getBaseUnit()); }

    @Override
    public String toString() {
        return "MolarFraction{" + value + unitType.getSymbol() + '}';
    }
}