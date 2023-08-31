package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class KinematicViscosity implements PhysicalQuantity<KinematicViscosity> {

    private final double value;
    private final double baseValue;
    private final Unit<KinematicViscosity> unit;

    public KinematicViscosity(double value, Unit<KinematicViscosity> unit) {
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
    public Unit<KinematicViscosity> getUnit() {
        return unit;
    }

    @Override
    public KinematicViscosity toBaseUnit() {
        double valueInSquareMeterPerSecond = unit.toValueInBaseUnit(value);
        return KinematicViscosity.of(valueInSquareMeterPerSecond, KinematicViscosityUnits.SQUARE_METER_PER_SECOND);
    }

    @Override
    public KinematicViscosity toUnit(Unit<KinematicViscosity> targetUnit) {
        double valueInSquareMeterPerSecond = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInSquareMeterPerSecond);
        return KinematicViscosity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public KinematicViscosity createNewWithValue(double value) {
        return KinematicViscosity.of(value, unit);
    }

    // Convert to target unit
    public KinematicViscosity toSquareMeterPerSecond(){
        return toUnit(KinematicViscosityUnits.SQUARE_METER_PER_SECOND);
    }

    public KinematicViscosity toSquareFootPerSecond(){
        return toUnit(KinematicViscosityUnits.SQUARE_FOOT_PER_SECOND);
    }

    // Get value in target unit
    public double getInSquareMetersPerSecond() {
        return getIn(KinematicViscosityUnits.SQUARE_METER_PER_SECOND);
    }

    public double getInSquareFeetPerSecond() {
        return getIn(KinematicViscosityUnits.SQUARE_FOOT_PER_SECOND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KinematicViscosity that = (KinematicViscosity) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "KinematicViscosity{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static KinematicViscosity of(double value, Unit<KinematicViscosity> unit) {
        return new KinematicViscosity(value, unit);
    }

    public static KinematicViscosity ofSquareMeterPerSecond(double value) {
        return new KinematicViscosity(value, KinematicViscosityUnits.SQUARE_METER_PER_SECOND);
    }

    public static KinematicViscosity ofSquareFootPerSecond(double value) {
        return new KinematicViscosity(value, KinematicViscosityUnits.SQUARE_FOOT_PER_SECOND);
    }

}