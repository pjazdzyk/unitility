package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class IsentropicCompressibility implements CalculableQuantity<IsentropicCompressibilityUnit, IsentropicCompressibility> {
    private final double value;
    private final double baseValue;
    private final IsentropicCompressibilityUnit unitType;

    public IsentropicCompressibility(double value, IsentropicCompressibilityUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = IsentropicCompressibilityUnits.INVERSE_PASCAL;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static IsentropicCompressibility of(double value, IsentropicCompressibilityUnit unit) {
        return new IsentropicCompressibility(value, unit);
    }

    public static IsentropicCompressibility of(double value, String unitSymbol) {
        return new IsentropicCompressibility(value, IsentropicCompressibilityUnits.fromSymbol(unitSymbol));
    }

    public static IsentropicCompressibility ofInversePascals(double value) {
        return new IsentropicCompressibility(value, IsentropicCompressibilityUnits.INVERSE_PASCAL);
    }

    public static IsentropicCompressibility ofInverseKilopascals(double value) {
        return new IsentropicCompressibility(value, IsentropicCompressibilityUnits.INVERSE_KILOPASCAL);
    }

    public static IsentropicCompressibility ofInverseMegapascals(double value) {
        return new IsentropicCompressibility(value, IsentropicCompressibilityUnits.INVERSE_MEGAPASCAL);
    }

    public static IsentropicCompressibility ofInverseBars(double value) {
        return new IsentropicCompressibility(value, IsentropicCompressibilityUnits.INVERSE_BAR);
    }

    public static IsentropicCompressibility ofInverseAtmospheres(double value) {
        return new IsentropicCompressibility(value, IsentropicCompressibilityUnits.INVERSE_ATMOSPHERE);
    }

    public static IsentropicCompressibility ofInversePSI(double value) {
        return new IsentropicCompressibility(value, IsentropicCompressibilityUnits.INVERSE_PSI);
    }

    public static IsentropicCompressibility ofInversePSF(double value) {
        return new IsentropicCompressibility(value, IsentropicCompressibilityUnits.INVERSE_PSF);
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
    public IsentropicCompressibilityUnit getUnit() {
        return unitType;
    }

    @Override
    public IsentropicCompressibility toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }

    @Override
    public IsentropicCompressibility toUnit(IsentropicCompressibilityUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }

    @Override
    public IsentropicCompressibility toUnit(String targetUnit) {
        return toUnit(IsentropicCompressibilityUnits.fromSymbol(targetUnit));
    }

    @Override
    public IsentropicCompressibility withValue(double value) {
        return of(value, unitType);
    }

    // Conversion methods
    public IsentropicCompressibility toInversePascal() {
        return toUnit(IsentropicCompressibilityUnits.INVERSE_PASCAL);
    }

    public IsentropicCompressibility toInverseKilopascal() {
        return toUnit(IsentropicCompressibilityUnits.INVERSE_KILOPASCAL);
    }

    public IsentropicCompressibility toInverseMegapascal() {
        return toUnit(IsentropicCompressibilityUnits.INVERSE_MEGAPASCAL);
    }

    public IsentropicCompressibility toInverseBar() {
        return toUnit(IsentropicCompressibilityUnits.INVERSE_BAR);
    }

    public IsentropicCompressibility toInverseAtmosphere() {
        return toUnit(IsentropicCompressibilityUnits.INVERSE_ATMOSPHERE);
    }

    public IsentropicCompressibility toInversePSI() {
        return toUnit(IsentropicCompressibilityUnits.INVERSE_PSI);
    }

    public IsentropicCompressibility toInversePSF() {
        return toUnit(IsentropicCompressibilityUnits.INVERSE_PSF);
    }

    // Value getter methods
    public double getInInversePascals() {
        return getInUnit(IsentropicCompressibilityUnits.INVERSE_PASCAL);
    }

    public double getInInverseKilopascals() {
        return getInUnit(IsentropicCompressibilityUnits.INVERSE_KILOPASCAL);
    }

    public double getInInverseMegapascals() {
        return getInUnit(IsentropicCompressibilityUnits.INVERSE_MEGAPASCAL);
    }

    public double getInInverseBars() {
        return getInUnit(IsentropicCompressibilityUnits.INVERSE_BAR);
    }

    public double getInInverseAtmospheres() {
        return getInUnit(IsentropicCompressibilityUnits.INVERSE_ATMOSPHERE);
    }

    public double getInInversePSI() {
        return getInUnit(IsentropicCompressibilityUnits.INVERSE_PSI);
    }

    public double getInInversePSF() {
        return getInUnit(IsentropicCompressibilityUnits.INVERSE_PSF);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IsentropicCompressibility)) return false;
        IsentropicCompressibility other = (IsentropicCompressibility) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "IsentropicCompressibility{" + value + " " + unitType.getSymbol() + "}";
    }
}
