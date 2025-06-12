package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Power implements CalculableQuantity<PowerUnit, Power> {
    private final double value;
    private final double baseValue;
    private final PowerUnit unitType;

    public Power(double value, PowerUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = PowerUnits.WATT;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Power of(double value, PowerUnit unit) {
        return new Power(value, unit);
    }

    public static Power of(double value, String unitSymbol) {
        PowerUnit resolvedUnit = PowerUnits.fromSymbol(unitSymbol);
        return new Power(value, resolvedUnit);
    }

    public static Power ofWatts(double value) {
        return new Power(value, PowerUnits.WATT);
    }

    public static Power ofKiloWatts(double value) {
        return new Power(value, PowerUnits.KILOWATT);
    }

    public static Power ofMegaWatts(double value) {
        return new Power(value, PowerUnits.MEGAWATT);
    }

    public static Power ofBTUPerHour(double value) {
        return new Power(value, PowerUnits.BTU_PER_HOUR);
    }

    public static Power ofHorsePower(double value) {
        return new Power(value, PowerUnits.HORSE_POWER);
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
    public Power toBaseUnit() {
        double valueInWatts = unitType.toValueInBaseUnit(value);
        return Power.of(valueInWatts, PowerUnits.WATT);
    }

    @Override
    public Power toUnit(PowerUnit targetUnit) {
        double valueInWatts = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInWatts);
        return Power.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Power toUnit(String targetUnit) {
        PowerUnit resolvedUnit = PowerUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Power withValue(double value) {
        return Power.of(value, unitType);
    }

    // Convert to target unit
    public Power toWatts() {
        return toUnit(PowerUnits.WATT);
    }

    public Power toKiloWatts() {
        return toUnit(PowerUnits.KILOWATT);
    }

    public Power toBTUPerHour() {
        return toUnit(PowerUnits.BTU_PER_HOUR);
    }

    public Power toMegaWatts() {
        return toUnit(PowerUnits.MEGAWATT);
    }

    public Power toHorsePower() {
        return toUnit(PowerUnits.HORSE_POWER);
    }

    // Get value in target unit
    public double getInWatts() {
        return getInUnit(PowerUnits.WATT);
    }

    public double getInKiloWatts() {
        return getInUnit(PowerUnits.KILOWATT);
    }

    public double getInBTUPerHour() {
        return getInUnit(PowerUnits.BTU_PER_HOUR);
    }

    public double getInMegaWatts() {
        return getInUnit(PowerUnits.MEGAWATT);
    }

    public double getInHorsePower() {
        return getInUnit(PowerUnits.HORSE_POWER);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Power inputQuantity = (Power) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Power{" + value + " " + unitType.getSymbol() + '}';
    }
}
