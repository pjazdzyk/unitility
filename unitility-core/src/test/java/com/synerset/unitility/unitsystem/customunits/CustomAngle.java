package com.synerset.unitility.unitsystem.customunits;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;

import java.util.Objects;

public class CustomAngle implements CalculableQuantity<AngleUnit, CustomAngle> {

    private final double value;
    private final double baseValue;
    private final AngleUnit unitType;

    public CustomAngle(double value, AngleUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static CustomAngle of(double value, AngleUnit unit) {
        return new CustomAngle(value, unit);
    }

    public static CustomAngle of(double value, String unitSymbol) {
        AngleUnit angleUnit = CustomAngleUnits.fromSymbol(unitSymbol);
        return new CustomAngle(value, angleUnit);
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
    public AngleUnit getUnitType() {
        return unitType;
    }

    @Override
    public CustomAngle toBaseUnit() {
        double degrees = unitType.toValueInBaseUnit(value);
        return CustomAngle.of(degrees, AngleUnits.DEGREES);
    }

    @Override
    public CustomAngle toUnit(AngleUnit targetUnit) {
        double valueInDegrees = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInDegrees);
        return CustomAngle.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public CustomAngle withValue(double value) {
        return CustomAngle.of(value, unitType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomAngle inputQuantity = (CustomAngle) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        String separator = getUnitType().getSymbol().contains("°") ? "" : " ";
        return "Angle{" + value + separator + unitType.getSymbol() + '}';
    }

}