package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Torque implements PhysicalQuantity<TorqueUnit> {

    public static final Torque PHYSICAL_MIN_LIMIT = Torque.ofNewtonMeters(0);
    private final double value;
    private final double baseValue;
    private final TorqueUnit unitType;

    public Torque(double value, TorqueUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Torque of(double value, TorqueUnit unit) {
        return new Torque(value, unit);
    }

    public static Torque of(double value, String unitSymbol) {
        TorqueUnit resolvedUnit = TorqueUnits.fromSymbol(unitSymbol);
        return new Torque(value, resolvedUnit);
    }
    
    public static Torque ofNewtonMeters(double value) {
        return new Torque(value, TorqueUnits.NEWTON_METER);
    }

    public static Torque ofMillinewtonMeters(double value) {
        return new Torque(value, TorqueUnits.MILLINEWTON_METER);
    }

    public static Torque ofKilopondMeters(double value) {
        return new Torque(value, TorqueUnits.KILOPOND_METER);
    }

    public static Torque ofFootPounds(double value) {
        return new Torque(value, TorqueUnits.FOOT_POUND);
    }

    public static Torque ofInchPounds(double value) {
        return new Torque(value, TorqueUnits.INCH_POUND);
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
    public TorqueUnit getUnitType() {
        return unitType;
    }

    @Override
    public Torque toBaseUnit() {
        double valueInNewtonMeters = unitType.toValueInBaseUnit(value);
        return Torque.of(valueInNewtonMeters, TorqueUnits.NEWTON_METER);
    }

    @Override
    public Torque toUnit(TorqueUnit targetUnit) {
        double valueInNewtonMeters = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInNewtonMeters);
        return Torque.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Torque createNewWithValue(double value) {
        return Torque.of(value, unitType);
    }

    // Convert to target unit
    public Torque toNewtonMeters() {
        return toUnit(TorqueUnits.NEWTON_METER);
    }

    public Torque toMillinewtonMeters() {
        return toUnit(TorqueUnits.MILLINEWTON_METER);
    }

    public Torque toKilopondMeters() {
        return toUnit(TorqueUnits.KILOPOND_METER);
    }

    public Torque toPoundFeet() {
        return toUnit(TorqueUnits.FOOT_POUND);
    }

    public Torque toInchPounds() {
        return toUnit(TorqueUnits.INCH_POUND);
    }

    // Get value in target unit
    public double getInNewtonMeters() {
        return getIn(TorqueUnits.NEWTON_METER);
    }

    public double getInMillinewtonMeters() {
        return getIn(TorqueUnits.MILLINEWTON_METER);
    }

    public double getInKilopondMeters() {
        return getIn(TorqueUnits.KILOPOND_METER);
    }

    public double getInPoundFeet() {
        return getIn(TorqueUnits.FOOT_POUND);
    }

    public double getInInchPounds() {
        return getIn(TorqueUnits.INCH_POUND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Torque inputQuantity = (Torque) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Torque{" + value + " " + unitType.getSymbol() + '}';
    }

}
