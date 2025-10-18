package com.synerset.unitility.unitsystem.electric;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Conductance implements CalculableQuantity<ConductanceUnit, Conductance> {

    private final double value;
    private final double baseValue;
    private final ConductanceUnit unitType;

    public Conductance(double value, ConductanceUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = ConductanceUnits.SIEMENS;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Conductance of(double value, ConductanceUnit unit) {
        return new Conductance(value, unit);
    }

    public static Conductance of(double value, String unitSymbol) {
        ConductanceUnit resolvedUnit = ConductanceUnits.fromSymbol(unitSymbol);
        return new Conductance(value, resolvedUnit);
    }

    public static Conductance ofPicoseimens(double value) { // Name kept for compatibility with unit symbol
        return new Conductance(value, ConductanceUnits.PICOSIEMENS);
    }

    public static Conductance ofNanoseimens(double value) { // Name kept for compatibility with unit symbol
        return new Conductance(value, ConductanceUnits.NANOSIEMENS);
    }

    public static Conductance ofMicroseimens(double value) { // Name kept for compatibility with unit symbol
        return new Conductance(value, ConductanceUnits.MICROSIEMENS);
    }

    public static Conductance ofMilliseimens(double value) { // Name kept for compatibility with unit symbol
        return new Conductance(value, ConductanceUnits.MILLISIEMENS);
    }

    public static Conductance ofSeimens(double value) {
        return new Conductance(value, ConductanceUnits.SIEMENS);
    }

    public static Conductance ofKiloseimens(double value) { // Name kept for compatibility with unit symbol
        return new Conductance(value, ConductanceUnits.KILOSIEMENS);
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
    public ConductanceUnit getUnit() {
        return unitType;
    }

    @Override
    public Conductance toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Conductance toUnit(ConductanceUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return Conductance.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Conductance toUnit(String targetUnit) {
        ConductanceUnit resolvedUnit = ConductanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Conductance withValue(double value) {
        return Conductance.of(value, unitType);
    }

    // Convert to target unit
    public Conductance toPicoseimens() {
        return toUnit(ConductanceUnits.PICOSIEMENS);
    }

    public Conductance toNanoseimens() {
        return toUnit(ConductanceUnits.NANOSIEMENS);
    }

    public Conductance toMicroseimens() {
        return toUnit(ConductanceUnits.MICROSIEMENS);
    }

    public Conductance toMilliseimens() {
        return toUnit(ConductanceUnits.MILLISIEMENS);
    }

    public Conductance toSeimens() {
        return toUnit(ConductanceUnits.SIEMENS);
    }

    public Conductance toKiloseimens() {
        return toUnit(ConductanceUnits.KILOSIEMENS);
    }

    // Get value in target unit
    public double getInPicoseimens() {
        return getInUnit(ConductanceUnits.PICOSIEMENS);
    }

    public double getInNanoseimens() {
        return getInUnit(ConductanceUnits.NANOSIEMENS);
    }

    public double getInMicroseimens() {
        return getInUnit(ConductanceUnits.MICROSIEMENS);
    }

    public double getInMilliseimens() {
        return getInUnit(ConductanceUnits.MILLISIEMENS);
    }

    public double getInSeimens() {
        return getInUnit(ConductanceUnits.SIEMENS);
    }

    public double getInKiloseimens() {
        return getInUnit(ConductanceUnits.KILOSIEMENS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conductance inputQuantity = (Conductance) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Conductance{" + value + unitType.getSymbol() + '}';
    }
}