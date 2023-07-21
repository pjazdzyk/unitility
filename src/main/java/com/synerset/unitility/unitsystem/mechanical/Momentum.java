package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class Momentum implements PhysicalQuantity<Momentum> {

    private final double value;
    private final Unit<Momentum> unit;

    private Momentum(double value, Unit<Momentum> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Momentum> getUnit() {
        return unit;
    }

    @Override
    public Momentum toBaseUnit() {
        double valueInKilogramMeterPerSecond = unit.toBaseUnit(value);
        return Momentum.of(valueInKilogramMeterPerSecond, MomentumUnits.KILOGRAM_METER_PER_SECOND);
    }

    @Override
    public Momentum toUnit(Unit<Momentum> targetUnit) {
        double valueInKilogramMeterPerSecond = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInKilogramMeterPerSecond);
        return Momentum.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public PhysicalQuantity<Momentum> createNewWithValue(double value) {
        return Momentum.of(value, unit);
    }

    // Custom value getters
    public double getValueOfKilogramMetersPerSecond() {
        if (unit == MomentumUnits.KILOGRAM_METER_PER_SECOND) {
            return value;
        }
        return toUnit(MomentumUnits.KILOGRAM_METER_PER_SECOND).getValue();
    }

    // Custom converter methods, for most popular units
    public Momentum toKilogramMeterPerSecond(){
        return toUnit(MomentumUnits.KILOGRAM_METER_PER_SECOND);
    }

    public Momentum toPoundFeetPerSecond(){
        return toUnit(MomentumUnits.POUND_FEET_PER_SECOND);
    }

    public Momentum toGramCentimetrePerSecond(){
        return toUnit(MomentumUnits.GRAM_CENTIMETRE_PER_SECOND);
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
        return "Momentum{" +
                "value=" + value +
                ", unit=" + unit.getSymbol() +
                '}';
    }

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
