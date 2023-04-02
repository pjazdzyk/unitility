package com.synerset.unitility.unitsystem.mass;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public final class Mass implements PhysicalQuantity<Mass> {

    public static final byte TO_STRING_PRECISION = 3;
    private final double value;
    private final Unit<Mass> unit;

    private Mass(double value, Unit<Mass> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Mass> getUnit() {
        return unit;
    }

    @Override
    public Mass toBaseUnit() {
        double valueInKilogram = unit.toBaseUnit(value);
        return Mass.of(valueInKilogram, MassUnits.KILOGRAM);
    }

    @Override
    public Mass toUnit(Unit<Mass> targetUnit) {
        double valueInKilogram = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInKilogram);
        return Mass.of(valueInTargetUnit, targetUnit);
    }

    // Custom converter methods, for most popular units
    public Mass toKilogram(){
        return toUnit(MassUnits.KILOGRAM);
    }

    public Mass toGram(){
        return toUnit(MassUnits.GRAM);
    }

    public Mass toMiligram(){
        return toUnit(MassUnits.MILLIGRAM);
    }

    public Mass toTonneSI(){
        return toUnit(MassUnits.TONNE_SI);
    }

    public Mass toOunce(){
        return toUnit(MassUnits.OUNCE);
    }

    public Mass toPound(){
        return toUnit(MassUnits.POUND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mass mass = (Mass) o;
        return Double.compare(mass.value, value) == 0 && Objects.equals(unit, mass.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return ValueFormatter.formatDoubleToRelevantPrecision(value, TO_STRING_PRECISION) + " " + unit.getSymbol();
    }

    public static Mass of(double value, Unit<Mass> unit) {
        return new Mass(value, unit);
    }

    public static Mass ofKilograms(double value) {
        return new Mass(value, MassUnits.KILOGRAM);
    }

    public static Mass ofGrams(double value) {
        return new Mass(value, MassUnits.GRAM);
    }

    public static Mass ofMiligrams(double value) {
        return new Mass(value, MassUnits.MILLIGRAM);
    }

    public static Mass ofTonneSI(double value) {
        return new Mass(value, MassUnits.TONNE_SI);
    }

    public static Mass ofOunces(double value) {
        return new Mass(value, MassUnits.OUNCE);
    }

    public static Mass ofPounds(double value) {
        return new Mass(value, MassUnits.POUND);
    }
}