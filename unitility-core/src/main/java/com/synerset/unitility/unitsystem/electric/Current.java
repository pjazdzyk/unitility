package com.synerset.unitility.unitsystem.electric;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Current implements CalculableQuantity<CurrentUnit, Current> {

    private final double value;
    private final double baseValue;
    private final CurrentUnit unitType;

    public Current(double value, CurrentUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = CurrentUnits.AMPERE;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Current of(double value, CurrentUnit unit) {
        return new Current(value, unit);
    }

    public static Current of(double value, String unitSymbol) {
        CurrentUnit resolvedUnit = CurrentUnits.fromSymbol(unitSymbol);
        return new Current(value, resolvedUnit);
    }

    public static Current ofMicroamperes(double value) {
        return new Current(value, CurrentUnits.MICROAMPERE);
    }

    public static Current ofMilliamperes(double value) {
        return new Current(value, CurrentUnits.MILLIAMPERE);
    }

    public static Current ofAmperes(double value) {
        return new Current(value, CurrentUnits.AMPERE);
    }

    public static Current ofKiloamperes(double value) {
        return new Current(value, CurrentUnits.KILOAMPERE);
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
    public CurrentUnit getUnit() {
        return unitType;
    }

    @Override
    public Current toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Current toUnit(CurrentUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return Current.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Current toUnit(String targetUnit) {
        CurrentUnit resolvedUnit = CurrentUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Current withValue(double value) {
        return Current.of(value, unitType);
    }

    // Convert to target unit
    public Current toMicroamperes() {
        return toUnit(CurrentUnits.MICROAMPERE);
    }

    public Current toMilliamperes() {
        return toUnit(CurrentUnits.MILLIAMPERE);
    }

    public Current toAmperes() {
        return toUnit(CurrentUnits.AMPERE);
    }

    public Current toKiloamperes() {
        return toUnit(CurrentUnits.KILOAMPERE);
    }


    // Get value in target unit
    public double getInMicroamperes() {
        return getInUnit(CurrentUnits.MICROAMPERE);
    }

    public double getInMilliamperes() {
        return getInUnit(CurrentUnits.MILLIAMPERE);
    }

    public double getInAmperes() {
        return getInUnit(CurrentUnits.AMPERE);
    }

    public double getInKiloamperes() {
        return getInUnit(CurrentUnits.KILOAMPERE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Current inputQuantity = (Current) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Current{" + value + unitType.getSymbol() + '}';
    }
}