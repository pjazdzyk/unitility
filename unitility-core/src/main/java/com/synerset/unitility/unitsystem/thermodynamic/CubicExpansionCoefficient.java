package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class CubicExpansionCoefficient implements CalculableQuantity<CubicExpansionCoefficientUnit, CubicExpansionCoefficient> {
    private final double value;
    private final double baseValue;
    private final CubicExpansionCoefficientUnit unitType;

    public CubicExpansionCoefficient(double value, CubicExpansionCoefficientUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = CubicExpansionCoefficientUnits.INVERSE_KELVIN;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static CubicExpansionCoefficient of(double value, CubicExpansionCoefficientUnit unit) {
        return new CubicExpansionCoefficient(value, unit);
    }

    public static CubicExpansionCoefficient of(double value, String unitSymbol) {
        return new CubicExpansionCoefficient(value, CubicExpansionCoefficientUnits.fromSymbol(unitSymbol));
    }

    public static CubicExpansionCoefficient ofInverseKelvin(double value) {
        return new CubicExpansionCoefficient(value, CubicExpansionCoefficientUnits.INVERSE_KELVIN);
    }

    public static CubicExpansionCoefficient ofInverseCelsius(double value) {
        return new CubicExpansionCoefficient(value, CubicExpansionCoefficientUnits.INVERSE_CELSIUS);
    }

    public static CubicExpansionCoefficient ofInverseMillikelvin(double value) {
        return new CubicExpansionCoefficient(value, CubicExpansionCoefficientUnits.INVERSE_MILLIKELVIN);
    }

    public static CubicExpansionCoefficient ofInverseRankine(double value) {
        return new CubicExpansionCoefficient(value, CubicExpansionCoefficientUnits.INVERSE_RANKINE);
    }

    public static CubicExpansionCoefficient ofInverseFahrenheit(double value) {
        return new CubicExpansionCoefficient(value, CubicExpansionCoefficientUnits.INVERSE_FAHRENHEIT);
    }

    // Implement CalculableQuantity interface methods
    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public CubicExpansionCoefficientUnit getUnit() {
        return unitType;
    }

    @Override
    public CubicExpansionCoefficient toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }

    @Override
    public CubicExpansionCoefficient toUnit(CubicExpansionCoefficientUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }

    @Override
    public CubicExpansionCoefficient toUnit(String targetUnit) {
        return toUnit(CubicExpansionCoefficientUnits.fromSymbol(targetUnit));
    }

    @Override
    public CubicExpansionCoefficient withValue(double value) {
        return of(value, unitType);
    }

    // Conversion methods
    public CubicExpansionCoefficient toInverseKelvin() {
        return toUnit(CubicExpansionCoefficientUnits.INVERSE_KELVIN);
    }

    public CubicExpansionCoefficient toInverseCelsius() {
        return toUnit(CubicExpansionCoefficientUnits.INVERSE_CELSIUS);
    }

    public CubicExpansionCoefficient toInverseMillikelvin() {
        return toUnit(CubicExpansionCoefficientUnits.INVERSE_MILLIKELVIN);
    }

    public CubicExpansionCoefficient toInverseRankine() {
        return toUnit(CubicExpansionCoefficientUnits.INVERSE_RANKINE);
    }

    public CubicExpansionCoefficient toInverseFahrenheit() {
        return toUnit(CubicExpansionCoefficientUnits.INVERSE_FAHRENHEIT);
    }

    // Value getter methods
    public double getInInverseKelvin() {
        return getInUnit(CubicExpansionCoefficientUnits.INVERSE_KELVIN);
    }

    public double getInInverseCelsius() {
        return getInUnit(CubicExpansionCoefficientUnits.INVERSE_CELSIUS);
    }

    public double getInInverseMillikelvin() {
        return getInUnit(CubicExpansionCoefficientUnits.INVERSE_MILLIKELVIN);
    }

    public double getInInverseRankine() {
        return getInUnit(CubicExpansionCoefficientUnits.INVERSE_RANKINE);
    }

    public double getInInverseFahrenheit() {
        return getInUnit(CubicExpansionCoefficientUnits.INVERSE_FAHRENHEIT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CubicExpansionCoefficient)) return false;
        CubicExpansionCoefficient other = (CubicExpansionCoefficient) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "CubicExpansionCoefficient{" + value + " " + unitType.getSymbol() + "}";
    }
}
