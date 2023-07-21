package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class DynamicViscosity implements PhysicalQuantity<DynamicViscosity> {

    private final double value;
    private final Unit<DynamicViscosity> unit;

    private DynamicViscosity(double value, Unit<DynamicViscosity> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<DynamicViscosity> getUnit() {
        return unit;
    }

    @Override
    public DynamicViscosity toBaseUnit() {
        double valueInPascalSecond = unit.toBaseUnit(value);
        return DynamicViscosity.of(valueInPascalSecond, DynamicViscosityUnits.PASCAL_SECOND);
    }

    @Override
    public DynamicViscosity toUnit(Unit<DynamicViscosity> targetUnit) {
        double valueInPascalSecond = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInPascalSecond);
        return DynamicViscosity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public PhysicalQuantity<DynamicViscosity> createNewWithValue(double value) {
        return DynamicViscosity.of(value, unit);
    }

    // Custom value getters
    public double getValueOfPascalSecond() {
        if (unit == DynamicViscosityUnits.PASCAL_SECOND) {
            return value;
        }
        return toUnit(DynamicViscosityUnits.PASCAL_SECOND).getValue();
    }

    // Custom converter methods, for most popular units
    public DynamicViscosity toKiloGramPerMeterSecond(){
        return toUnit(DynamicViscosityUnits.KILOGRAM_PER_METER_SECOND);
    }

    public DynamicViscosity toPascalSecond(){
        return toUnit(DynamicViscosityUnits.PASCAL_SECOND);
    }

    public DynamicViscosity toPoise(){
        return toUnit(DynamicViscosityUnits.POISE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicViscosity that = (DynamicViscosity) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "DynamicViscosity{" +
                "value=" + value +
                ", unit=" + unit.getSymbol() +
                '}';
    }

    public static DynamicViscosity of(double value, Unit<DynamicViscosity> unit) {
        return new DynamicViscosity(value, unit);
    }

    public static DynamicViscosity ofKiloGramPerMeterSecond(double value) {
        return new DynamicViscosity(value, DynamicViscosityUnits.KILOGRAM_PER_METER_SECOND);
    }

    public static DynamicViscosity ofPascalSecond(double value) {
        return new DynamicViscosity(value, DynamicViscosityUnits.PASCAL_SECOND);
    }

    public static DynamicViscosity ofPoise(double value) {
        return new DynamicViscosity(value, DynamicViscosityUnits.POISE);
    }

}
