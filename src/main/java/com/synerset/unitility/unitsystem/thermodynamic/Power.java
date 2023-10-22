package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Power implements PhysicalQuantity<PowerUnits> {
    private final double value;
    private final double baseValue;
    private final PowerUnits unitType;

    public Power(double value, PowerUnits unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Power of(double value, PowerUnits unit) {
        return new Power(value, unit);
    }

    public static Power of(double value, String unitSymbol) {
        PowerUnits resolvedUnit = PowerUnits.fromSymbol(unitSymbol);
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
    public PowerUnits getUnitType() {
        return unitType;
    }

    @Override
    public Power toBaseUnit() {
        double valueInWatts = unitType.toValueInBaseUnit(value);
        return Power.of(valueInWatts, PowerUnits.WATT);
    }

    @Override
    public Power toUnit(PowerUnits targetUnit) {
        double valueInWatts = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInWatts);
        return Power.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Power createNewWithValue(double value) {
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
        return getIn(PowerUnits.WATT);
    }

    public double getInKiloWatts() {
        return getIn(PowerUnits.KILOWATT);
    }

    public double getInBTUPerHour() {
        return getIn(PowerUnits.BTU_PER_HOUR);
    }

    public double getInMegaWatts() {
        return getIn(PowerUnits.MEGAWATT);
    }

    public double getInHorsePower() {
        return getIn(PowerUnits.HORSE_POWER);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Power inputQuantity = (Power) o;
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
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
