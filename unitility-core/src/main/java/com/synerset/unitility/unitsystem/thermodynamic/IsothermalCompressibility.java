package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class IsothermalCompressibility implements CalculableQuantity<IsothermalCompressibilityUnit, IsothermalCompressibility> {
    private final double value;
    private final double baseValue;
    private final IsothermalCompressibilityUnit unitType;

    public IsothermalCompressibility(double value, IsothermalCompressibilityUnit unitType) {
        this.value = value;
        if (unitType == null) { unitType = IsothermalCompressibilityUnits.INVERSE_PASCAL; }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static IsothermalCompressibility of(double value, IsothermalCompressibilityUnit unit) {
        return new IsothermalCompressibility(value, unit);
    }

    public static IsothermalCompressibility of(double value, String unitSymbol) {
        return new IsothermalCompressibility(value, IsothermalCompressibilityUnits.fromSymbol(unitSymbol));
    }

    public static IsothermalCompressibility ofInversePascals(double value) {
        return new IsothermalCompressibility(value, IsothermalCompressibilityUnits.INVERSE_PASCAL);
    }

    public static IsothermalCompressibility ofInverseMegapascals(double value) {
        return new IsothermalCompressibility(value, IsothermalCompressibilityUnits.INVERSE_MEGAPASCAL);
    }

    public static IsothermalCompressibility ofInversePSI(double value) {
        return new IsothermalCompressibility(value, IsothermalCompressibilityUnits.INVERSE_PSI);
    }

    // Implement CalculableQuantity interface methods
    @Override public double getValue() { return value; }
    @Override public double getBaseValue() { return baseValue; }
    @Override public IsothermalCompressibilityUnit getUnit() { return unitType; }
    
    @Override public IsothermalCompressibility toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }
    
    @Override public IsothermalCompressibility toUnit(IsothermalCompressibilityUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }
    
    @Override public IsothermalCompressibility toUnit(String targetUnit) {
        return toUnit(IsothermalCompressibilityUnits.fromSymbol(targetUnit));
    }
    
    @Override public IsothermalCompressibility withValue(double value) {
        return of(value, unitType);
    }

    // Conversion methods
    public IsothermalCompressibility toInversePascal() { return toUnit(IsothermalCompressibilityUnits.INVERSE_PASCAL); }
    public IsothermalCompressibility toInverseKilopascal() { return toUnit(IsothermalCompressibilityUnits.INVERSE_KILOPASCAL); }
    public IsothermalCompressibility toInverseMegapascal() { return toUnit(IsothermalCompressibilityUnits.INVERSE_MEGAPASCAL); }
    public IsothermalCompressibility toInverseBar() { return toUnit(IsothermalCompressibilityUnits.INVERSE_BAR); }
    public IsothermalCompressibility toInversePSI() { return toUnit(IsothermalCompressibilityUnits.INVERSE_PSI); }

    // Value getter methods
    public double getInInversePascals() { return getInUnit(IsothermalCompressibilityUnits.INVERSE_PASCAL); }
    public double getInInverseKilopascals() { return getInUnit(IsothermalCompressibilityUnits.INVERSE_KILOPASCAL); }
    public double getInInverseMegapascals() { return getInUnit(IsothermalCompressibilityUnits.INVERSE_MEGAPASCAL); }
    public double getInInverseBars() { return getInUnit(IsothermalCompressibilityUnits.INVERSE_BAR); }
    public double getInInversePSI() { return getInUnit(IsothermalCompressibilityUnits.INVERSE_PSI); }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IsothermalCompressibility)) return false;
        IsothermalCompressibility other = (IsothermalCompressibility) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override public int hashCode() { return Objects.hash(baseValue, unitType.getBaseUnit()); }

    @Override public String toString() {
        return "IsothermalCompressibility{" + value + " " + unitType.getSymbol() + "}";
    }
}