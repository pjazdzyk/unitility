package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Momentum implements PhysicalQuantity<MomentumUnits> {

    private final double value;
    private final double baseValue;
    private final MomentumUnits unitType;

    public Momentum(double value, MomentumUnits unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Momentum of(double value, MomentumUnits unit) {
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

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public MomentumUnits getUnitType() {
        return unitType;
    }

    @Override
    public Momentum toBaseUnit() {
        double valueInKilogramMeterPerSecond = unitType.toValueInBaseUnit(value);
        return Momentum.of(valueInKilogramMeterPerSecond, MomentumUnits.KILOGRAM_METER_PER_SECOND);
    }

    @Override
    public Momentum toUnit(MomentumUnits targetUnit) {
        double valueInKilogramMeterPerSecond = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKilogramMeterPerSecond);
        return Momentum.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Momentum createNewWithValue(double value) {
        return Momentum.of(value, unitType);
    }

    // Convert to target unit
    public Momentum toKilogramMeterPerSecond() {
        return toUnit(MomentumUnits.KILOGRAM_METER_PER_SECOND);
    }

    public Momentum toPoundFeetPerSecond() {
        return toUnit(MomentumUnits.POUND_FEET_PER_SECOND);
    }

    public Momentum toGramCentimetrePerSecond() {
        return toUnit(MomentumUnits.GRAM_CENTIMETRE_PER_SECOND);
    }

    // Get value in target unit
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
        Momentum inputQuantity = (Momentum) o;
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Momentum{" + value + " " + unitType.getSymbol() + '}';
    }

}
