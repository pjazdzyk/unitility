package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class Energy implements PhysicalQuantity<Energy> {

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

    @Override
    public PhysicalQuantity<Energy> createNewWithValue(double value) {
        return Energy.of(value, unit);
    }

    // Custom value getters
    public double getValueOfJoules() {
        if (unit == EnergyUnits.JOULE) {
            return value;
        }
        return toUnit(EnergyUnits.JOULE).getValue();
    }

    public double getValueOfKiloJoules() {
        if (unit == EnergyUnits.KILOJOULE) {
            return value;
        }
        return toUnit(EnergyUnits.KILOJOULE).getValue();
    }

    // Custom converter methods, for most popular units
    public Energy toJoules() {
        return toUnit(EnergyUnits.JOULE);
    }

    public Energy toMilliJoules() {
        return toUnit(EnergyUnits.MILLIJOULE);
    }

    public Energy toKiloJoules() {
        return toUnit(EnergyUnits.KILOJOULE);
    }

    public Energy toMegaJoules() {
        return toUnit(EnergyUnits.MEGAJOULE);
    }

    public Energy toBTU() {
        return toUnit(EnergyUnits.BTU);
    }

    public Energy toCalories() {
        return toUnit(EnergyUnits.CALORIE);
    }

    public Energy toKiloCalories() {
        return toUnit(EnergyUnits.KILOCALORIE);
    }

    public Energy toWattHour() {
        return toUnit(EnergyUnits.WATT_HOUR);
    }

    public Energy toKilowattHour() {
        return toUnit(EnergyUnits.KILOWATT_HOUR);
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
        return "Energy{" +
                "value=" + value +
                ", unit=" + unit.getSymbol() +
                '}';
    }

    public static Energy of(double value, Unit<Energy> unit) {
        return new Energy(value, unit);
    }

    public static Energy ofJoules(double value) {
        return new Energy(value, EnergyUnits.JOULE);
    }

    public static Energy ofMilliJoules(double value) {
        return new Energy(value, EnergyUnits.MILLIJOULE);
    }

    public static Energy ofKiloJoules(double value) {
        return new Energy(value, EnergyUnits.KILOJOULE);
    }

    public static Energy ofMegaJoules(double value) {
        return new Energy(value, EnergyUnits.MEGAJOULE);
    }

    public static Energy ofBTU(double value) {
        return new Energy(value, EnergyUnits.BTU);
    }

    public static Energy ofCalorie(double value) {
        return new Energy(value, EnergyUnits.CALORIE);
    }

    public static Energy ofKiloCalorie(double value) {
        return new Energy(value, EnergyUnits.KILOCALORIE);
    }

    public static Energy ofWattHour(double value) {
        return new Energy(value, EnergyUnits.WATT_HOUR);
    }

    public static Energy ofKilowattHour(double value) {
        return new Energy(value, EnergyUnits.KILOWATT_HOUR);
    }

}