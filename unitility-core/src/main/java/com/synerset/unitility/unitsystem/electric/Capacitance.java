package com.synerset.unitility.unitsystem.electric;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Capacitance implements CalculableQuantity<CapacitanceUnit, Capacitance> {

    private final double value;
    private final double baseValue;
    private final CapacitanceUnit unitType;

    public Capacitance(double value, CapacitanceUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = CapacitanceUnits.FARAD;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Capacitance of(double value, CapacitanceUnit unit) {
        return new Capacitance(value, unit);
    }

    public static Capacitance of(double value, String unitSymbol) {
        CapacitanceUnit resolvedUnit = CapacitanceUnits.fromSymbol(unitSymbol);
        return new Capacitance(value, resolvedUnit);
    }

    public static Capacitance ofPicofarads(double value) {
        return new Capacitance(value, CapacitanceUnits.PICOFARAD);
    }

    public static Capacitance ofNanofarads(double value) {
        return new Capacitance(value, CapacitanceUnits.NANOFARAD);
    }

    public static Capacitance ofMicrofarads(double value) {
        return new Capacitance(value, CapacitanceUnits.MICROFARAD);
    }

    public static Capacitance ofMillifarads(double value) {
        return new Capacitance(value, CapacitanceUnits.MILLIFARAD);
    }

    public static Capacitance ofFarads(double value) {
        return new Capacitance(value, CapacitanceUnits.FARAD);
    }

    public static Capacitance ofKilofarads(double value) {
        return new Capacitance(value, CapacitanceUnits.KILOFARAD);
    }

    public static Capacitance ofMegafarads(double value) {
        return new Capacitance(value, CapacitanceUnits.MEGAFARAD);
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
    public CapacitanceUnit getUnit() {
        return unitType;
    }

    @Override
    public Capacitance toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Capacitance toUnit(CapacitanceUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return Capacitance.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Capacitance toUnit(String targetUnit) {
        CapacitanceUnit resolvedUnit = CapacitanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Capacitance withValue(double value) {
        return Capacitance.of(value, unitType);
    }

    // Convert to target unit
    public Capacitance toPicofarads() {
        return toUnit(CapacitanceUnits.PICOFARAD);
    }

    public Capacitance toNanofarads() {
        return toUnit(CapacitanceUnits.NANOFARAD);
    }

    public Capacitance toMicrofarads() {
        return toUnit(CapacitanceUnits.MICROFARAD);
    }

    public Capacitance toMillifarads() {
        return toUnit(CapacitanceUnits.MILLIFARAD);
    }

    public Capacitance toFarads() {
        return toUnit(CapacitanceUnits.FARAD);
    }

    public Capacitance toKilofarads() {
        return toUnit(CapacitanceUnits.KILOFARAD);
    }

    public Capacitance toMegafarads() {
        return toUnit(CapacitanceUnits.MEGAFARAD);
    }


    // Get value in target unit
    public double getInPicofarads() {
        return getInUnit(CapacitanceUnits.PICOFARAD);
    }

    public double getInNanofarads() {
        return getInUnit(CapacitanceUnits.NANOFARAD);
    }

    public double getInMicrofarads() {
        return getInUnit(CapacitanceUnits.MICROFARAD);
    }

    public double getInMillifarads() {
        return getInUnit(CapacitanceUnits.MILLIFARAD);
    }

    public double getInFarads() {
        return getInUnit(CapacitanceUnits.FARAD);
    }

    public double getInKilofarads() {
        return getInUnit(CapacitanceUnits.KILOFARAD);
    }

    public double getInMegafarads() {
        return getInUnit(CapacitanceUnits.MEGAFARAD);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capacitance inputQuantity = (Capacitance) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Capacitance{" + value + unitType.getSymbol() + '}';
    }
}