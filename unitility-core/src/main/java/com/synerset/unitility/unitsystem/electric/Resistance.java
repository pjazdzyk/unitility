package com.synerset.unitility.unitsystem.electric;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Resistance implements CalculableQuantity<ResistanceUnit, Resistance> {

    private final double value;
    private final double baseValue;
    private final ResistanceUnit unitType;

    public Resistance(double value, ResistanceUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = ResistanceUnits.OHM;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Resistance of(double value, ResistanceUnit unit) {
        return new Resistance(value, unit);
    }

    public static Resistance of(double value, String unitSymbol) {
        ResistanceUnit resolvedUnit = ResistanceUnits.fromSymbol(unitSymbol);
        return new Resistance(value, resolvedUnit);
    }

    public static Resistance ofMilliohms(double value) {
        return new Resistance(value, ResistanceUnits.MILLIOHM);
    }

    public static Resistance ofOhms(double value) {
        return new Resistance(value, ResistanceUnits.OHM);
    }

    public static Resistance ofKiloohms(double value) {
        return new Resistance(value, ResistanceUnits.KILOOHM);
    }

    public static Resistance ofMegaohms(double value) {
        return new Resistance(value, ResistanceUnits.MEGAOHM);
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
    public ResistanceUnit getUnit() {
        return unitType;
    }

    @Override
    public Resistance toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Resistance toUnit(ResistanceUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return Resistance.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Resistance toUnit(String targetUnit) {
        ResistanceUnit resolvedUnit = ResistanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Resistance withValue(double value) {
        return Resistance.of(value, unitType);
    }

    // Convert to target unit
    public Resistance toMilliohms() {
        return toUnit(ResistanceUnits.MILLIOHM);
    }

    public Resistance toOhms() {
        return toUnit(ResistanceUnits.OHM);
    }

    public Resistance toKiloohms() {
        return toUnit(ResistanceUnits.KILOOHM);
    }

    public Resistance toMegaohms() {
        return toUnit(ResistanceUnits.MEGAOHM);
    }

    // Get value in target unit
    public double getInMilliohms() {
        return getInUnit(ResistanceUnits.MILLIOHM);
    }

    public double getInOhms() {
        return getInUnit(ResistanceUnits.OHM);
    }

    public double getInKiloohms() {
        return getInUnit(ResistanceUnits.KILOOHM);
    }

    public double getInMegaohms() {
        return getInUnit(ResistanceUnits.MEGAOHM);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resistance inputQuantity = (Resistance) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Resistance{" + value + unitType.getSymbol() + '}';
    }
}