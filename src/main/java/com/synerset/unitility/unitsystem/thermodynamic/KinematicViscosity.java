package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class KinematicViscosity implements PhysicalQuantity<KinematicViscosityUnits> {

    private final double value;
    private final double baseValue;
    private final KinematicViscosityUnits unitType;

    public KinematicViscosity(double value, KinematicViscosityUnits unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static KinematicViscosity of(double value, KinematicViscosityUnits unit) {
        return new KinematicViscosity(value, unit);
    }

    public static KinematicViscosity ofSquareMeterPerSecond(double value) {
        return new KinematicViscosity(value, KinematicViscosityUnits.SQUARE_METER_PER_SECOND);
    }

    public static KinematicViscosity ofSquareFootPerSecond(double value) {
        return new KinematicViscosity(value, KinematicViscosityUnits.SQUARE_FOOT_PER_SECOND);
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
    public KinematicViscosityUnits getUnitType() {
        return unitType;
    }

    @Override
    public KinematicViscosity toBaseUnit() {
        double valueInSquareMeterPerSecond = unitType.toValueInBaseUnit(value);
        return KinematicViscosity.of(valueInSquareMeterPerSecond, KinematicViscosityUnits.SQUARE_METER_PER_SECOND);
    }

    @Override
    public KinematicViscosity toUnit(KinematicViscosityUnits targetUnit) {
        double valueInSquareMeterPerSecond = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInSquareMeterPerSecond);
        return KinematicViscosity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public KinematicViscosity createNewWithValue(double value) {
        return KinematicViscosity.of(value, unitType);
    }

    // Convert to target unit
    public KinematicViscosity toSquareMeterPerSecond() {
        return toUnit(KinematicViscosityUnits.SQUARE_METER_PER_SECOND);
    }

    public KinematicViscosity toSquareFootPerSecond() {
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
        KinematicViscosity inputQuantity = (KinematicViscosity) o;
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unitType.getBaseUnit(),
                inputQuantity.getUnitType().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "KinematicViscosity{" + value + " " + unitType.getSymbol() + '}';
    }

}