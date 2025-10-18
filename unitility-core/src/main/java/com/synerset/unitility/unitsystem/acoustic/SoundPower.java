package com.synerset.unitility.unitsystem.acoustic;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.thermodynamic.PowerUnit;

import java.util.Objects;

public class SoundPower implements CalculableQuantity<PowerUnit, SoundPower> {

    private final double value;
    private final double baseValue;
    private final PowerUnit unitType;

    public SoundPower(double value, PowerUnit unitType) {
        this.unitType = unitType == null ? SoundPowerUnits.WATT : unitType;
        this.value = value;
        this.baseValue = this.unitType.toValueInBaseUnit(value);
    }

    // Static factories
    public static SoundPower of(double value, PowerUnit unit) {
        return new SoundPower(value, unit);
    }

    public static SoundPower of(double value, String unitSymbol) {
        PowerUnit resolvedUnit = SoundPowerUnits.fromSymbol(unitSymbol);
        return new SoundPower(value, resolvedUnit);
    }

    public static SoundPower ofWatts(double value) {
        return new SoundPower(value, SoundPowerUnits.WATT);
    }

    public static SoundPower ofDecibels(double value) {
        return new SoundPower(value, SoundPowerUnits.DECIBEL);
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
    public PowerUnit getUnit() {
        return unitType;
    }

    @Override
    public SoundPower toBaseUnit() {
        return of(baseValue, SoundPowerUnits.WATT);
    }

    @Override
    public SoundPower toUnit(PowerUnit targetUnit) {
        double targetVal = targetUnit.fromValueInBaseUnit(baseValue);
        return of(targetVal, targetUnit);
    }

    @Override
    public SoundPower toUnit(String targetSymbol) {
        return toUnit(SoundPowerUnits.fromSymbol(targetSymbol));
    }

    @Override
    public SoundPower withValue(double value) {
        return of(value, unitType);
    }

    // Convenience converters
    public SoundPower toWatts() {
        return toUnit(SoundPowerUnits.WATT);
    }

    public SoundPower toDecibels() {
        return toUnit(SoundPowerUnits.DECIBEL);
    }

    public double getInWatts() {
        return getInUnit(SoundPowerUnits.WATT);
    }

    public double getInDecibels() {
        return getInUnit(SoundPowerUnits.DECIBEL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SoundPower that)) return false;
        return Double.compare(that.baseValue, baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), that.unitType.getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unitType.getSymbol();
    }
}
