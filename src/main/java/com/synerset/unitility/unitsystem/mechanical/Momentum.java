package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class Momentum implements PhysicalQuantity<Momentum> {

    private final double value;
    private final double baseValue;
    private final Unit<Momentum> unit;

    public Momentum(double value, Unit<Momentum> unit) {
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
    public Unit<Momentum> getUnit() {
        return unit;
    }

    @Override
    public Momentum toBaseUnit() {
        double valueInKilogramMeterPerSecond = unit.toValueInBaseUnit(value);
        return Momentum.of(valueInKilogramMeterPerSecond, MomentumUnits.KILOGRAM_METER_PER_SECOND);
    }

    @Override
    public Momentum toUnit(Unit<Momentum> targetUnit) {
        double valueInKilogramMeterPerSecond = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKilogramMeterPerSecond);
        return Momentum.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Momentum createNewWithValue(double value) {
        return Momentum.of(value, unit);
    }

    // Convert to target unit
    public double getInKilogramMetersPerSecond() {
        return getIn(MomentumUnits.KILOGRAM_METER_PER_SECOND);
    }

    public double getInPoundFeetPerSecond() {
        return getIn(MomentumUnits.POUND_FEET_PER_SECOND);
    }

    public double getInGramCentimetersPerSecond() {
        return getIn(MomentumUnits.GRAM_CENTIMETRE_PER_SECOND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Momentum momentum = (Momentum) o;
        return Double.compare(momentum.value, value) == 0 && Objects.equals(unit, momentum.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "Momentum{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static Momentum of(double value, Unit<Momentum> unit) {
        return new Momentum(value, unit);
    }

    public static Momentum ofKilogramMeterPerSecond(double value) {
        return new Momentum(value, MomentumUnits.KILOGRAM_METER_PER_SECOND);
    }

    public static Momentum ofPoundFeetPerSecond(double value) {
        return new Momentum(value, MomentumUnits.POUND_FEET_PER_SECOND);
    }

    public static Momentum ofGramCentimetrePerSecond(double value) {
        return new Momentum(value, MomentumUnits.GRAM_CENTIMETRE_PER_SECOND);
    }
}
