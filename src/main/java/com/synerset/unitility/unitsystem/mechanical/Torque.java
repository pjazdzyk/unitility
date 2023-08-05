package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class Torque implements PhysicalQuantity<Torque> {

    public static final Torque PHYSICAL_MIN_LIMIT = Torque.ofNewtonMeters(0);
    private final double value;
    private final Unit<Torque> unit;

    private Torque(double value, Unit<Torque> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Torque> getUnit() {
        return unit;
    }

    @Override
    public Torque toBaseUnit() {
        double valueInNewtonMeters = unit.toBaseUnit(value);
        return Torque.of(valueInNewtonMeters, TorqueUnits.NEWTON_METER);
    }

    @Override
    public Torque toUnit(Unit<Torque> targetUnit) {
        double valueInNewtonMeters = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInNewtonMeters);
        return Torque.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public PhysicalQuantity<Torque> createNewWithValue(double value) {
        return Torque.of(value, unit);
    }

    // Custom value getters
    public double getValueOfKilogramMetersPerSecond() {
        if (unit == TorqueUnits.NEWTON_METER) {
            return value;
        }
        return toUnit(TorqueUnits.NEWTON_METER).getValue();
    }

    // Custom converter methods for the most popular units
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
