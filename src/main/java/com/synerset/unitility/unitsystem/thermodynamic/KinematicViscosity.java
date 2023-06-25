package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public final class KinematicViscosity implements PhysicalQuantity<KinematicViscosity> {

    private final double value;
    private final Unit<KinematicViscosity> unit;

    private KinematicViscosity(double value, Unit<KinematicViscosity> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<KinematicViscosity> getUnit() {
        return unit;
    }

    @Override
    public KinematicViscosity toBaseUnit() {
        double valueInSquareMeterPerSecond = unit.toBaseUnit(value);
        return KinematicViscosity.of(valueInSquareMeterPerSecond, KinematicViscosityUnits.SQUARE_METER_PER_SECOND);
    }

    @Override
    public KinematicViscosity toUnit(Unit<KinematicViscosity> targetUnit) {
        double valueInSquareMeterPerSecond = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInSquareMeterPerSecond);
        return KinematicViscosity.of(valueInTargetUnit, targetUnit);
    }

    // Custom converter methods, for most popular units
    public KinematicViscosity toSquareMeterPerSecond(){
        return toUnit(KinematicViscosityUnits.SQUARE_METER_PER_SECOND);
    }

    public KinematicViscosity toSquareFootPerSecond(){
        return toUnit(KinematicViscosityUnits.SQUARE_FOOT_PER_SECOND);
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
        return ValueFormatter.formatDoubleToRelevantPrecision(value, TO_STRING_PRECISION) + " " + unit.getSymbol();
    }

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