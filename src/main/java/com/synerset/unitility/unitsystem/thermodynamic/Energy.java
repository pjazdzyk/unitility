package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class Energy implements PhysicalQuantity<Energy> {

    private final double value;
    private final double baseValue;
    private final Unit<Energy> unit;

    public Energy(double value, Unit<Energy> unit) {
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
    public Unit<Energy> getUnit() {
        return unit;
    }

    @Override
    public Energy toBaseUnit() {
        double valueInJoules = unit.toValueInBaseUnit(value);
        return Energy.of(valueInJoules, EnergyUnits.JOULE);
    }

    @Override
    public Energy toUnit(Unit<Energy> targetUnit) {
        double valueInJoules = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInJoules);
        return Energy.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Energy createNewWithValue(double value) {
        return Energy.of(value, unit);
    }

    // Convert to target unit
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

    // Get value in target unit
    public double getInJoules() {
        return getIn(EnergyUnits.JOULE);
    }

    public double getInMilliJoules() {
        return getIn(EnergyUnits.MILLIJOULE);
    }

    public double getInKiloJoules() {
        return getIn(EnergyUnits.KILOJOULE);
    }

    public double getInMegaJoules() {
        return getIn(EnergyUnits.MEGAJOULE);
    }

    public double getInBTUs() {
        return getIn(EnergyUnits.BTU);
    }

    public double getInCalories() {
        return getIn(EnergyUnits.CALORIE);
    }

    public double getInKiloCalories() {
        return getIn(EnergyUnits.KILOCALORIE);
    }

    public double getInWattHours() {
        return getIn(EnergyUnits.WATT_HOUR);
    }

    public double getInKilowattHours() {
        return getIn(EnergyUnits.KILOWATT_HOUR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Energy inputQuantity = (Energy) o;
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unit.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unit.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Energy{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
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