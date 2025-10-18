package com.synerset.unitility.unitsystem.electric;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Voltage implements CalculableQuantity<VoltageUnit, Voltage> {

    private final double value;
    private final double baseValue;
    private final VoltageUnit unitType;

    public Voltage(double value, VoltageUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = VoltageUnits.VOLT;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Voltage of(double value, VoltageUnit unit) {
        return new Voltage(value, unit);
    }

    public static Voltage of(double value, String unitSymbol) {
        VoltageUnit resolvedUnit = VoltageUnits.fromSymbol(unitSymbol);
        return new Voltage(value, resolvedUnit);
    }

    public static Voltage ofMicrovolts(double value) {
        return new Voltage(value, VoltageUnits.MICROVOLT);
    }

    public static Voltage ofMillivolts(double value) {
        return new Voltage(value, VoltageUnits.MILLIVOLT);
    }

    public static Voltage ofVolts(double value) {
        return new Voltage(value, VoltageUnits.VOLT);
    }

    public static Voltage ofKilovolts(double value) {
        return new Voltage(value, VoltageUnits.KILOVOLT);
    }

    public static Voltage ofMegavolts(double value) {
        return new Voltage(value, VoltageUnits.MEGAVOLT);
    }

    public static Voltage ofGigavolts(double value) { // Added
        return new Voltage(value, VoltageUnits.GIGAVOLT);
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
    public VoltageUnit getUnit() {
        return unitType;
    }

    @Override
    public Voltage toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Voltage toUnit(VoltageUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return Voltage.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Voltage toUnit(String targetUnit) {
        VoltageUnit resolvedUnit = VoltageUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Voltage withValue(double value) {
        return Voltage.of(value, unitType);
    }

    // Convert to target unit
    public Voltage toMicrovolts() {
        return toUnit(VoltageUnits.MICROVOLT);
    }

    public Voltage toMillivolts() {
        return toUnit(VoltageUnits.MILLIVOLT);
    }

    public Voltage toVolts() {
        return toUnit(VoltageUnits.VOLT);
    }

    public Voltage toKilovolts() {
        return toUnit(VoltageUnits.KILOVOLT);
    }

    public Voltage toMegavolts() {
        return toUnit(VoltageUnits.MEGAVOLT);
    }

    public Voltage toGigavolts() { // Added
        return toUnit(VoltageUnits.GIGAVOLT);
    }


    // Get value in target unit
    public double getInMicrovolts() {
        return getInUnit(VoltageUnits.MICROVOLT);
    }

    public double getInMillivolts() {
        return getInUnit(VoltageUnits.MILLIVOLT);
    }

    public double getInVolts() {
        return getInUnit(VoltageUnits.VOLT);
    }

    public double getInKilovolts() {
        return getInUnit(VoltageUnits.KILOVOLT);
    }

    public double getInMegavolts() {
        return getInUnit(VoltageUnits.MEGAVOLT);
    }

    public double getInGigavolts() { // Added
        return getInUnit(VoltageUnits.GIGAVOLT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voltage inputQuantity = (Voltage) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Voltage{" + value + unitType.getSymbol() + '}';
    }
}