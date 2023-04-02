package com.synerset.unitility.unitsystem.energy;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public final class Energy implements PhysicalQuantity<Energy> {

    public static final byte TO_STRING_PRECISION = 3;
    private final double value;
    private final Unit<Energy> unit;

    private Energy(double value, Unit<Energy> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Energy> getUnit() {
        return unit;
    }

    @Override
    public Energy toBaseUnit() {
        double valueInJoules = unit.toBaseUnit(value);
        return Energy.of(valueInJoules, EnergyUnits.JOULE);
    }

    @Override
    public Energy toUnit(Unit<Energy> targetUnit) {
        double valueInJoules = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInJoules);
        return Energy.of(valueInTargetUnit, targetUnit);
    }

    // Custom converter methods, for most popular units
    public Energy toJoules() {
        return toUnit(EnergyUnits.JOULE);
    }

    public Energy toMilliJoules() {
        return toUnit(EnergyUnits.MILLI_JOULE);
    }

    public Energy toKiloJoules() {
        return toUnit(EnergyUnits.KILO_JOULE);
    }

    public Energy toMegaJoules() {
        return toUnit(EnergyUnits.MEGA_JOULE);
    }

    public Energy toBTU() {
        return toUnit(EnergyUnits.BTU);
    }

    public Energy toCalories() {
        return toUnit(EnergyUnits.CALORIE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Energy that = (Energy) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return ValueFormatter.formatDoubleToRelevantPrecision(value, TO_STRING_PRECISION) + " " + unit.getSymbol();
    }

    public static Energy of(double value, Unit<Energy> unit) {
        return new Energy(value, unit);
    }

    public static Energy ofJoules(double value) {
        return new Energy(value, EnergyUnits.JOULE);
    }

    public static Energy ofMilliJoules(double value) {
        return new Energy(value, EnergyUnits.MILLI_JOULE);
    }

    public static Energy ofKiloJoules(double value) {
        return new Energy(value, EnergyUnits.KILO_JOULE);
    }

    public static Energy ofMegaJoules(double value) {
        return new Energy(value, EnergyUnits.MEGA_JOULE);
    }

    public static Energy ofBTU(double value) {
        return new Energy(value, EnergyUnits.BTU);
    }

    public static Energy ofCalorie(double value) {
        return new Energy(value, EnergyUnits.CALORIE);
    }
}