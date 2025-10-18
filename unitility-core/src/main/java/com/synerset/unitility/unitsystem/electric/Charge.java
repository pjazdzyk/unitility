package com.synerset.unitility.unitsystem.electric;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Charge implements CalculableQuantity<ChargeUnit, Charge> {

    private final double value;
    private final double baseValue;
    private final ChargeUnit unitType;

    public Charge(double value, ChargeUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = ChargeUnits.COULOMB;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Charge of(double value, ChargeUnit unit) {
        return new Charge(value, unit);
    }

    public static Charge of(double value, String unitSymbol) {
        ChargeUnit resolvedUnit = ChargeUnits.fromSymbol(unitSymbol);
        return new Charge(value, resolvedUnit);
    }

    public static Charge ofPicocoulombs(double value) {
        return new Charge(value, ChargeUnits.PICOCOULOMB);
    }

    public static Charge ofNanocoulombs(double value) {
        return new Charge(value, ChargeUnits.NANOCOULOMB);
    }

    public static Charge ofMicrocoulombs(double value) {
        return new Charge(value, ChargeUnits.MICROCOULOMB);
    }

    public static Charge ofMillicoulombs(double value) {
        return new Charge(value, ChargeUnits.MILLICOULOMB);
    }

    public static Charge ofCoulombs(double value) {
        return new Charge(value, ChargeUnits.COULOMB);
    }

    public static Charge ofKilocoulombs(double value) {
        return new Charge(value, ChargeUnits.KILOCOULOMB);
    }

    public static Charge ofMegacoulombs(double value) {
        return new Charge(value, ChargeUnits.MEGACOULOMB);
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
    public ChargeUnit getUnit() {
        return unitType;
    }

    @Override
    public Charge toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Charge toUnit(ChargeUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return Charge.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Charge toUnit(String targetUnit) {
        ChargeUnit resolvedUnit = ChargeUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Charge withValue(double value) {
        return Charge.of(value, unitType);
    }

    // Convert to target unit
    public Charge toPicocoulombs() {
        return toUnit(ChargeUnits.PICOCOULOMB);
    }

    public Charge toNanocoulombs() {
        return toUnit(ChargeUnits.NANOCOULOMB);
    }

    public Charge toMicrocoulombs() {
        return toUnit(ChargeUnits.MICROCOULOMB);
    }

    public Charge toMillicoulombs() {
        return toUnit(ChargeUnits.MILLICOULOMB);
    }

    public Charge toCoulombs() {
        return toUnit(ChargeUnits.COULOMB);
    }

    public Charge toKilocoulombs() {
        return toUnit(ChargeUnits.KILOCOULOMB);
    }

    public Charge toMegacoulombs() {
        return toUnit(ChargeUnits.MEGACOULOMB);
    }

    // Get value in target unit
    public double getInPicocoulombs() {
        return getInUnit(ChargeUnits.PICOCOULOMB);
    }

    public double getInNanocoulombs() {
        return getInUnit(ChargeUnits.NANOCOULOMB);
    }

    public double getInMicrocoulombs() {
        return getInUnit(ChargeUnits.MICROCOULOMB);
    }

    public double getInMillicoulombs() {
        return getInUnit(ChargeUnits.MILLICOULOMB);
    }

    public double getInCoulombs() {
        return getInUnit(ChargeUnits.COULOMB);
    }

    public double getInKilocoulombs() {
        return getInUnit(ChargeUnits.KILOCOULOMB);
    }

    public double getInMegacoulombs() {
        return getInUnit(ChargeUnits.MEGACOULOMB);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Charge inputQuantity = (Charge) o;
        // Strict double comparison as requested
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Charge{" + value + unitType.getSymbol() + '}';
    }
}