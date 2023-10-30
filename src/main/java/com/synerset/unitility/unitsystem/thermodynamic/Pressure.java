package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Pressure implements PhysicalQuantity<PressureUnit> {

    public static final Pressure STANDARD_ATMOSPHERE = Pressure.ofPascal(101_325);
    public static final Pressure TECHNICAL_ATMOSPHERE = Pressure.ofPascal(98_067);
    private final double value;
    private final double baseValue;
    private final PressureUnit unitType;

    public Pressure(double value, PressureUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Pressure of(double value, PressureUnit unit) {
        return new Pressure(value, unit);
    }

    public static Pressure of(double value, String unitSymbol) {
        PressureUnit resolvedUnit = PressureUnits.fromSymbol(unitSymbol);
        return new Pressure(value, resolvedUnit);
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

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public PressureUnit getUnitType() {
        return unitType;
    }

    @Override
    public Pressure toBaseUnit() {
        double valueInPascal = unitType.toValueInBaseUnit(value);
        return Pressure.of(valueInPascal, PressureUnits.PASCAL);
    }

    @Override
    public Pressure toUnit(PressureUnit targetUnit) {
        double valueInPascal = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInPascal);
        return Pressure.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pressure createNewWithValue(double value) {
        return Pressure.of(value, unitType);
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
        Pressure inputQuantity = (Pressure) o;
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Pressure{" + value + " " + unitType.getSymbol() + '}';
    }

}

