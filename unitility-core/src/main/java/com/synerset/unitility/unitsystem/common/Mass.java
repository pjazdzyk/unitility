package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Mass implements CalculableQuantity<MassUnit, Mass> {

    public static final Mass PHYSICAL_MIN_LIMIT = Mass.ofKilograms(0);
    private final double value;
    private final double baseValue;
    private final MassUnit unitType;

    public Mass(double value, MassUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = MassUnits.getDefaultUnit();
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Mass of(double value, MassUnit unit) {
        return new Mass(value, unit);
    }

    public static Mass of(double value, String unitSymbol) {
        MassUnit resolvedUnit = MassUnits.fromSymbol(unitSymbol);
        return new Mass(value, resolvedUnit);
    }

    public static Mass ofKilograms(double value) {
        return new Mass(value, MassUnits.KILOGRAM);
    }

    public static Mass ofGrams(double value) {
        return new Mass(value, MassUnits.GRAM);
    }

    public static Mass ofMilligrams(double value) {
        return new Mass(value, MassUnits.MILLIGRAM);
    }

    public static Mass ofTonneSI(double value) {
        return new Mass(value, MassUnits.TONNE_SI);
    }

    public static Mass ofOunces(double value) {
        return new Mass(value, MassUnits.OUNCE);
    }

    public static Mass ofPounds(double value) {
        return new Mass(value, MassUnits.POUND);
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
    public MassUnit getUnit() {
        return unitType;
    }

    @Override
    public Mass toBaseUnit() {
        double valueInKilogram = unitType.toValueInBaseUnit(value);
        return of(valueInKilogram, MassUnits.KILOGRAM);
    }

    @Override
    public Mass toUnit(MassUnit targetUnit) {
        double valueInKilogram = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKilogram);
        return Mass.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Mass toUnit(String targetUnit) {
        MassUnit resolvedUnit = MassUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Mass withValue(double value) {
        return Mass.of(value, unitType);
    }

    // Convert to target unit
    public Mass toKilogram() {
        return toUnit(MassUnits.KILOGRAM);
    }

    public Mass toGram() {
        return toUnit(MassUnits.GRAM);
    }

    public Mass toMilligram() {
        return toUnit(MassUnits.MILLIGRAM);
    }

    public Mass toTonneSI() {
        return toUnit(MassUnits.TONNE_SI);
    }

    public Mass toOunce() {
        return toUnit(MassUnits.OUNCE);
    }

    public Mass toPound() {
        return toUnit(MassUnits.POUND);
    }

    // Get value in target unit
    public double getInKilograms() {
        return getInUnit(MassUnits.KILOGRAM);
    }

    public double getIntoGrams() {
        return getInUnit(MassUnits.GRAM);
    }

    public double getInMilligrams() {
        return getInUnit(MassUnits.MILLIGRAM);
    }

    public double getIntoTonnesSI() {
        return getInUnit(MassUnits.TONNE_SI);
    }

    public double getIntoOunces() {
        return getInUnit(MassUnits.OUNCE);
    }

    public double getInPounds() {
        return getInUnit(MassUnits.POUND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mass inputQuantity = (Mass) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Mass{" + value + " " + unitType.getSymbol() + '}';
    }

}