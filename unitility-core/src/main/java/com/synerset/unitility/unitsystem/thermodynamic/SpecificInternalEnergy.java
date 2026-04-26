package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class SpecificInternalEnergy implements CalculableQuantity<SpecificInternalEnergyUnit, SpecificInternalEnergy> {

    private final double value;
    private final double baseValue;
    private final SpecificInternalEnergyUnit unitType;

    public SpecificInternalEnergy(double value, SpecificInternalEnergyUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = SpecificInternalEnergyUnits.JOULE_PER_KILOGRAM;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static SpecificInternalEnergy of(double value, SpecificInternalEnergyUnit unit) {
        return new SpecificInternalEnergy(value, unit);
    }

    public static SpecificInternalEnergy of(double value, String unitSymbol) {
        return new SpecificInternalEnergy(value, SpecificInternalEnergyUnits.fromSymbol(unitSymbol));
    }

    public static SpecificInternalEnergy ofJoulesPerKilogram(double value) {
        return new SpecificInternalEnergy(value, SpecificInternalEnergyUnits.JOULE_PER_KILOGRAM);
    }

    public static SpecificInternalEnergy ofKiloJoulesPerKilogram(double value) {
        return new SpecificInternalEnergy(value, SpecificInternalEnergyUnits.KILOJOULE_PER_KILOGRAM);
    }

    public static SpecificInternalEnergy ofMegaJoulesPerKilogram(double value) {
        return new SpecificInternalEnergy(value, SpecificInternalEnergyUnits.MEGAJOULE_PER_KILOGRAM);
    }

    public static SpecificInternalEnergy ofBtuPerPound(double value) {
        return new SpecificInternalEnergy(value, SpecificInternalEnergyUnits.BTU_PER_POUND);
    }

    public static SpecificInternalEnergy ofCaloriePerGram(double value) {
        return new SpecificInternalEnergy(value, SpecificInternalEnergyUnits.CALORIE_PER_GRAM);
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
    public SpecificInternalEnergyUnit getUnit() {
        return unitType;
    }

    @Override
    public SpecificInternalEnergy toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }

    @Override
    public SpecificInternalEnergy toUnit(SpecificInternalEnergyUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }

    @Override
    public SpecificInternalEnergy toUnit(String targetUnit) {
        return toUnit(SpecificInternalEnergyUnits.fromSymbol(targetUnit));
    }

    @Override
    public SpecificInternalEnergy withValue(double value) {
        return of(value, unitType);
    }

    // Conversion methods
    public SpecificInternalEnergy toJoulesPerKilogram() {
        return toUnit(SpecificInternalEnergyUnits.JOULE_PER_KILOGRAM);
    }

    public SpecificInternalEnergy toKiloJoulesPerKilogram() {
        return toUnit(SpecificInternalEnergyUnits.KILOJOULE_PER_KILOGRAM);
    }

    public SpecificInternalEnergy toMegaJoulesPerKilogram() {
        return toUnit(SpecificInternalEnergyUnits.MEGAJOULE_PER_KILOGRAM);
    }

    public SpecificInternalEnergy toBtuPerPound() {
        return toUnit(SpecificInternalEnergyUnits.BTU_PER_POUND);
    }

    public SpecificInternalEnergy toCaloriePerGram() {
        return toUnit(SpecificInternalEnergyUnits.CALORIE_PER_GRAM);
    }

    // Value getters
    public double getInJoulesPerKilogram() {
        return getInUnit(SpecificInternalEnergyUnits.JOULE_PER_KILOGRAM);
    }

    public double getInKiloJoulesPerKilogram() {
        return getInUnit(SpecificInternalEnergyUnits.KILOJOULE_PER_KILOGRAM);
    }

    public double getInMegaJoulesPerKilogram() {
        return getInUnit(SpecificInternalEnergyUnits.MEGAJOULE_PER_KILOGRAM);
    }

    public double getInBtuPerPound() {
        return getInUnit(SpecificInternalEnergyUnits.BTU_PER_POUND);
    }

    public double getInCaloriePerGram() {
        return getInUnit(SpecificInternalEnergyUnits.CALORIE_PER_GRAM);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificInternalEnergy that = (SpecificInternalEnergy) o;
        return Double.compare(that.toBaseUnit().getValue(), baseValue) == 0 && 
               Objects.equals(unitType.getBaseUnit(), that.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "SpecificInternalEnergy{" + value + " " + unitType.getSymbol() + '}';
    }
}
