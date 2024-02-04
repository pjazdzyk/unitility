package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Energy implements CalculableQuantity<EnergyUnit, Energy> {

    private final double value;
    private final double baseValue;
    private final EnergyUnit unitType;

    public Energy(double value, EnergyUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = EnergyUnits.getDefaultUnit();
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Energy of(double value, EnergyUnit unit) {
        return new Energy(value, unit);
    }

    public static Energy of(double value, String unitSymbol) {
        EnergyUnit resolvedUnit = EnergyUnits.fromSymbol(unitSymbol);
        return new Energy(value, resolvedUnit);
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

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public EnergyUnit getUnitType() {
        return unitType;
    }

    @Override
    public Energy toBaseUnit() {
        double valueInJoules = unitType.toValueInBaseUnit(value);
        return Energy.of(valueInJoules, EnergyUnits.JOULE);
    }

    @Override
    public Energy toUnit(EnergyUnit targetUnit) {
        double valueInJoules = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInJoules);
        return Energy.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Energy withValue(double value) {
        return Energy.of(value, unitType);
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
        return getInUnit(EnergyUnits.JOULE);
    }

    public double getInMilliJoules() {
        return getInUnit(EnergyUnits.MILLIJOULE);
    }

    public double getInKiloJoules() {
        return getInUnit(EnergyUnits.KILOJOULE);
    }

    public double getInMegaJoules() {
        return getInUnit(EnergyUnits.MEGAJOULE);
    }

    public double getInBTUs() {
        return getInUnit(EnergyUnits.BTU);
    }

    public double getInCalories() {
        return getInUnit(EnergyUnits.CALORIE);
    }

    public double getInKiloCalories() {
        return getInUnit(EnergyUnits.KILOCALORIE);
    }

    public double getInWattHours() {
        return getInUnit(EnergyUnits.WATT_HOUR);
    }

    public double getInKilowattHours() {
        return getInUnit(EnergyUnits.KILOWATT_HOUR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Energy inputQuantity = (Energy) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Energy{" + value + " " + unitType.getSymbol() + '}';
    }

}