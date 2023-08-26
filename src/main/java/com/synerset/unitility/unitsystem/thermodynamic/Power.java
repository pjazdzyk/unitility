package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class Power implements PhysicalQuantity<Power> {
    private final double value;
    private final double baseValue;
    private final Unit<Power> unit;

    private Power(double value, Unit<Power> unit) {
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
    public Unit<Power> getUnit() {
        return unit;
    }

    @Override
    public Power toBaseUnit() {
        double valueInWatts = unit.toValueInBaseUnit(value);
        return Power.of(valueInWatts, PowerUnits.WATT);
    }

    @Override
    public Power toUnit(Unit<Power> targetUnit) {
        double valueInWatts = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInWatts);
        return Power.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Power createNewWithValue(double value) {
        return Power.of(value, unit);
    }

    // Convert to target unit
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
        Power that = (Power) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "Power{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static Power of(double value, Unit<Power> unit) {
        return new Power(value, unit);
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
}
