package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class Torque implements PhysicalQuantity<Torque> {

    public static final Torque PHYSICAL_MIN_LIMIT = Torque.ofNewtonMeters(0);
    private final double value;
    private final double baseValue;
    private final Unit<Torque> unit;

    public Torque(double value, Unit<Torque> unit) {
        this.value = value;
        this.unit = unit;
        this.baseValue = unit.toValueInBaseUnit(value);
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
    public Unit<Torque> getUnit() {
        return unit;
    }

    @Override
    public Torque toBaseUnit() {
        double valueInNewtonMeters = unit.toValueInBaseUnit(value);
        return Torque.of(valueInNewtonMeters, TorqueUnits.NEWTON_METER);
    }

    @Override
    public Torque toUnit(Unit<Torque> targetUnit) {
        double valueInNewtonMeters = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInNewtonMeters);
        return Torque.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Torque createNewWithValue(double value) {
        return Torque.of(value, unit);
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
        Torque torque = (Torque) o;
        return Double.compare(torque.value, value) == 0 && unit.equals(torque.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "Torque{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static Torque of(double value, Unit<Torque> unit) {
        return new Torque(value, unit);
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
}
