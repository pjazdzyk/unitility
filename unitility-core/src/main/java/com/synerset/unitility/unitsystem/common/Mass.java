package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Mass implements PhysicalQuantity<MassUnit> {

    public static final Mass PHYSICAL_MIN_LIMIT = Mass.ofKilograms(0);
    private final double value;
    private final double baseValue;
    private final MassUnit unitType;

    public Mass(double value, MassUnit unitType) {
        this.value = value;
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
    public MassUnit getUnitType() {
        return unitType;
    }

    @Override
    public Mass toBaseUnit() {
        double valueInKilogram = unitType.toValueInBaseUnit(value);
        return Mass.of(valueInKilogram, MassUnits.KILOGRAM);
    }

    @Override
    public Mass toUnit(MassUnit targetUnit) {
        double valueInKilogram = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKilogram);
        return Mass.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Mass createNewWithValue(double value) {
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
        return getIn(MassUnits.KILOGRAM);
    }

    public double getIntoGrams() {
        return getIn(MassUnits.GRAM);
    }

    public double getInMilligrams() {
        return getIn(MassUnits.MILLIGRAM);
    }

    public double getIntoTonnesSI() {
        return getIn(MassUnits.TONNE_SI);
    }

    public double getIntoOunces() {
        return getIn(MassUnits.OUNCE);
    }

    public double getInPounds() {
        return getIn(MassUnits.POUND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mass inputQuantity = (Mass) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
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