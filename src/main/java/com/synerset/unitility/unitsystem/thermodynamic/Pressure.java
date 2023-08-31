package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class Pressure implements PhysicalQuantity<Pressure> {

    public static final Pressure STANDARD_ATMOSPHERE = Pressure.ofPascal(101_325);
    public static final Pressure TECHNICAL_ATMOSPHERE = Pressure.ofPascal(98_067);
    private final double value;
    private final double baseValue;
    private final Unit<Pressure> unit;

    public Pressure(double value, Unit<Pressure> unit) {
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
    public Unit<Pressure> getUnit() {
        return unit;
    }

    @Override
    public Pressure toBaseUnit() {
        double valueInPascal = unit.toValueInBaseUnit(value);
        return Pressure.of(valueInPascal, PressureUnits.PASCAL);
    }

    @Override
    public Pressure toUnit(Unit<Pressure> targetUnit) {
        double valueInPascal = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInPascal);
        return Pressure.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Pressure createNewWithValue(double value) {
        return Pressure.of(value, unit);
    }

    // Convert to target unit
    public Pressure toPascal() {
        return toUnit(PressureUnits.PASCAL);
    }

    public Pressure toHectoPascal() {
        return toUnit(PressureUnits.HECTOPASCAL);
    }

    public Pressure toMegaPascal() {
        return toUnit(PressureUnits.MEGAPASCAL);
    }

    public Pressure toBar() {
        return toUnit(PressureUnits.BAR);
    }

    public Pressure toMilliBar() {
        return toUnit(PressureUnits.MILLIBAR);
    }

    public Pressure toPsi() {
        return toUnit(PressureUnits.PSI);
    }

    public Pressure toTorr() {
        return toUnit(PressureUnits.TORR);
    }

    // Get value in target unit
    public double getInPascals() {
        return getIn(PressureUnits.PASCAL);
    }

    public double getInHectoPascals() {
        return getIn(PressureUnits.HECTOPASCAL);
    }

    public double getInMegaPascals() {
        return getIn(PressureUnits.MEGAPASCAL);
    }

    public double getInBar() {
        return getIn(PressureUnits.BAR);
    }

    public double getInMilliBar() {
        return getIn(PressureUnits.MILLIBAR);
    }

    public double getInPsi() {
        return getIn(PressureUnits.PSI);
    }

    public double getInTorr() {
        return getIn(PressureUnits.TORR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pressure pressure = (Pressure) o;
        return Double.compare(pressure.value, value) == 0 && unit.equals(pressure.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "Pressure{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static Pressure of(double value, Unit<Pressure> unit) {
        return new Pressure(value, unit);
    }

    public static Pressure ofPascal(double value) {
        return new Pressure(value, PressureUnits.PASCAL);
    }

    public static Pressure ofHectoPascal(double value) {
        return new Pressure(value, PressureUnits.HECTOPASCAL);
    }

    public static Pressure ofMegaPascal(double value) {
        return new Pressure(value, PressureUnits.MEGAPASCAL);
    }

    public static Pressure ofBar(double value) {
        return new Pressure(value, PressureUnits.BAR);
    }

    public static Pressure ofMilliBar(double value) {
        return new Pressure(value, PressureUnits.MILLIBAR);
    }

    public static Pressure ofPsi(double value) {
        return new Pressure(value, PressureUnits.PSI);
    }

    public static Pressure ofTorr(double value) {
        return new Pressure(value, PressureUnits.TORR);
    }

}

