package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class Power implements PhysicalQuantity<Power> {
    private final double value;
    private final Unit<Power> unit;

    private Power(double value, Unit<Power> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Power> getUnit() {
        return unit;
    }

    @Override
    public Power toBaseUnit() {
        double valueInWatts = unit.toBaseUnit(value);
        return Power.of(valueInWatts, PowerUnits.WATT);
    }

    @Override
    public Power toUnit(Unit<Power> targetUnit) {
        double valueInWatts = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInWatts);
        return Power.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public PhysicalQuantity<Power> createNewWithValue(double value) {
        return Power.of(value, unit);
    }

    // Custom value getters
    public double getValueOfWatts() {
        if (unit == PowerUnits.WATT) {
            return value;
        }
        return toUnit(PowerUnits.WATT).getValue();
    }

    public double getValueOfKiloWatts() {
        if (unit == PowerUnits.KILOWATT) {
            return value;
        }
        return toUnit(PowerUnits.KILOWATT).getValue();
    }

    // Custom converter methods, for most popular units
    public Power toWatts(){
        return toUnit(PowerUnits.WATT);
    }

    public Power toKiloWatts(){
        return toUnit(PowerUnits.KILOWATT);
    }

    public Power toBTUPerHour(){
        return toUnit(PowerUnits.BTU_PER_HOUR);
    }

    public Power toMegaWatts(){
        return toUnit(PowerUnits.MEGAWATT);
    }

    public Power toHorsePower(){
        return toUnit(PowerUnits.HORSE_POWER);
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
        return "Power{" +
                "value=" + value +
                ", unit=" + unit.getSymbol() +
                '}';
    }

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
