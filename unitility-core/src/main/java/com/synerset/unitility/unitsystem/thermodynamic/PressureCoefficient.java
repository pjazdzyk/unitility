package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class PressureCoefficient implements CalculableQuantity<PressureCoefficientUnit, PressureCoefficient> {
    private final double value;
    private final double baseValue;
    private final PressureCoefficientUnit unitType;

    public PressureCoefficient(double value, PressureCoefficientUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = PressureCoefficientUnits.PASCAL_PER_KELVIN;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static PressureCoefficient of(double value, PressureCoefficientUnit unit) {
        return new PressureCoefficient(value, unit);
    }

    public static PressureCoefficient of(double value, String unitSymbol) {
        return new PressureCoefficient(value, PressureCoefficientUnits.fromSymbol(unitSymbol));
    }

    public static PressureCoefficient ofPascalPerKelvin(double value) {
        return new PressureCoefficient(value, PressureCoefficientUnits.PASCAL_PER_KELVIN);
    }

    public static PressureCoefficient ofKilopascalPerKelvin(double value) {
        return new PressureCoefficient(value, PressureCoefficientUnits.KILOPASCAL_PER_KELVIN);
    }

    public static PressureCoefficient ofMegapascalPerKelvin(double value) {
        return new PressureCoefficient(value, PressureCoefficientUnits.MEGAPASCAL_PER_KELVIN);
    }

    public static PressureCoefficient ofBarPerKelvin(double value) {
        return new PressureCoefficient(value, PressureCoefficientUnits.BAR_PER_KELVIN);
    }

    public static PressureCoefficient ofAtmospherePerKelvin(double value) {
        return new PressureCoefficient(value, PressureCoefficientUnits.ATMOSPHERE_PER_KELVIN);
    }

    public static PressureCoefficient ofPsiPerRankine(double value) {
        return new PressureCoefficient(value, PressureCoefficientUnits.PSI_PER_RANKINE);
    }

    public static PressureCoefficient ofPsiPerFahrenheit(double value) {
        return new PressureCoefficient(value, PressureCoefficientUnits.PSI_PER_FAHRENHEIT);
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
    public PressureCoefficientUnit getUnit() {
        return unitType;
    }

    @Override
    public PressureCoefficient toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }

    @Override
    public PressureCoefficient toUnit(PressureCoefficientUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }

    @Override
    public PressureCoefficient toUnit(String targetUnit) {
        return toUnit(PressureCoefficientUnits.fromSymbol(targetUnit));
    }

    @Override
    public PressureCoefficient withValue(double value) {
        return of(value, unitType);
    }

    // Conversion methods
    public PressureCoefficient toPascalPerKelvin() {
        return toUnit(PressureCoefficientUnits.PASCAL_PER_KELVIN);
    }

    public PressureCoefficient toKilopascalPerKelvin() {
        return toUnit(PressureCoefficientUnits.KILOPASCAL_PER_KELVIN);
    }

    public PressureCoefficient toMegapascalPerKelvin() {
        return toUnit(PressureCoefficientUnits.MEGAPASCAL_PER_KELVIN);
    }

    public PressureCoefficient toBarPerKelvin() {
        return toUnit(PressureCoefficientUnits.BAR_PER_KELVIN);
    }

    public PressureCoefficient toAtmospherePerKelvin() {
        return toUnit(PressureCoefficientUnits.ATMOSPHERE_PER_KELVIN);
    }

    public PressureCoefficient toPsiPerRankine() {
        return toUnit(PressureCoefficientUnits.PSI_PER_RANKINE);
    }

    public PressureCoefficient toPsiPerFahrenheit() {
        return toUnit(PressureCoefficientUnits.PSI_PER_FAHRENHEIT);
    }

    // Value getter methods
    public double getInPascalPerKelvin() {
        return getInUnit(PressureCoefficientUnits.PASCAL_PER_KELVIN);
    }

    public double getInKilopascalPerKelvin() {
        return getInUnit(PressureCoefficientUnits.KILOPASCAL_PER_KELVIN);
    }

    public double getInMegapascalPerKelvin() {
        return getInUnit(PressureCoefficientUnits.MEGAPASCAL_PER_KELVIN);
    }

    public double getInBarPerKelvin() {
        return getInUnit(PressureCoefficientUnits.BAR_PER_KELVIN);
    }

    public double getInAtmospherePerKelvin() {
        return getInUnit(PressureCoefficientUnits.ATMOSPHERE_PER_KELVIN);
    }

    public double getInPsiPerRankine() {
        return getInUnit(PressureCoefficientUnits.PSI_PER_RANKINE);
    }

    public double getInPsiPerFahrenheit() {
        return getInUnit(PressureCoefficientUnits.PSI_PER_FAHRENHEIT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PressureCoefficient)) return false;
        PressureCoefficient other = (PressureCoefficient) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "PressureCoefficient{" + value + " " + unitType.getSymbol() + "}";
    }
}
