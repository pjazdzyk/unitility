package com.synerset.unitility.unitsystem.acoustic;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.thermodynamic.PowerUnit;
import com.synerset.unitility.unitsystem.thermodynamic.PressureUnit;

import java.util.Objects;

public class SoundPressure implements CalculableQuantity<PressureUnit, SoundPressure> {

    private final double value;
    private final double baseValue;
    private final PressureUnit unit;

    public SoundPressure(double value, PressureUnit unit) {
        this.unit = unit == null ? SoundPressureUnits.PASCAL : unit;
        this.value = value;
        this.baseValue = this.unit.toValueInBaseUnit(value);
    }

    public static SoundPressure of(double value, PressureUnit unit) {
        return new SoundPressure(value, unit);
    }

    public static SoundPressure of(double value, String unitSymbol) {
        PressureUnit resolvedUnit = SoundPressureUnits.fromSymbol(unitSymbol);
        return new SoundPressure(value, resolvedUnit);
    }

    public static SoundPressure ofPascals(double value) {
        return new SoundPressure(value, SoundPressureUnits.PASCAL);
    }

    public static SoundPressure ofDecibels(double value) {
        return new SoundPressure(value, SoundPressureUnits.DECIBEL);
    }

    @Override
    public SoundPressure toBaseUnit() {
        return of(baseValue, SoundPressureUnits.PASCAL);
    }

    @Override
    public SoundPressure toUnit(PressureUnit targetUnit) {
        double targetVal = targetUnit.fromValueInBaseUnit(baseValue);
        return of(targetVal, targetUnit);
    }

    @Override
    public SoundPressure toUnit(String targetSymbol) {
        return toUnit(SoundPressureUnits.fromSymbol(targetSymbol));
    }

    @Override
    public SoundPressure withValue(double value) {
        return of(value, unit);
    }

    public SoundPressure toPascals() {
        return toUnit(SoundPressureUnits.PASCAL);
    }

    public SoundPressure toDecibels() {
        return toUnit(SoundPressureUnits.DECIBEL);
    }

    public double getInPascals() {
        return getInUnit(SoundPressureUnits.PASCAL);
    }

    public double getInDecibels() {
        return getInUnit(SoundPressureUnits.DECIBEL);
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
    public PressureUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SoundPressure that)) return false;
        return Double.compare(that.baseValue, baseValue) == 0
                && Objects.equals(unit.getBaseUnit(), that.unit.getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unit.getBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit.getSymbol();
    }
}
