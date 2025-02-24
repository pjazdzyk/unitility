package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class LinearResistance implements CalculableQuantity<LinearResistanceUnit, LinearResistance> {

    private final double value;
    private final double baseValue;
    private final LinearResistanceUnit unitType;

    public LinearResistance(double value, LinearResistanceUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = LinearResistanceUnits.getDefaultUnit();
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static LinearResistance of(double value, LinearResistanceUnit unit) {
        return new LinearResistance(value, unit);
    }

    public static LinearResistance of(double value, String unitSymbol) {
        LinearResistanceUnit resolvedUnit = LinearResistanceUnits.fromSymbol(unitSymbol);
        return new LinearResistance(value, resolvedUnit);
    }

    // Factory methods for each unit
    public static LinearResistance ofPascalPerMeter(double value) {
        return new LinearResistance(value, LinearResistanceUnits.PASCAL_PER_METER);
    }

    public static LinearResistance ofInchOfWaterPer100Feet(double value) {
        return new LinearResistance(value, LinearResistanceUnits.INCH_OF_WATER_PER_100_FEET);
    }

    public static LinearResistance ofInchOfMercuryPer100Feet(double value) {
        return new LinearResistance(value, LinearResistanceUnits.INCH_OF_MERCURY_PER_100_FEET);
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
    public LinearResistanceUnit getUnit() {
        return unitType;
    }

    @Override
    public LinearResistance toBaseUnit() {
        double valueInPascal = unitType.toValueInBaseUnit(value);
        return of(valueInPascal, LinearResistanceUnits.PASCAL_PER_METER);
    }

    @Override
    public LinearResistance toUnit(LinearResistanceUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return LinearResistance.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public LinearResistance toUnit(String targetUnit) {
        LinearResistanceUnit resolvedUnit = LinearResistanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public LinearResistance withValue(double value) {
        return LinearResistance.of(value, unitType);
    }

    // Convert to target unit
    public LinearResistance toPascalPerMeter() {
        return toUnit(LinearResistanceUnits.PASCAL_PER_METER);
    }

    public LinearResistance toInchOfWaterPer100Feet() {
        return toUnit(LinearResistanceUnits.INCH_OF_WATER_PER_100_FEET);
    }

    public LinearResistance toInchOfMercuryPer100Feet() {
        return toUnit(LinearResistanceUnits.INCH_OF_MERCURY_PER_100_FEET);
    }

    // Get value in target unit
    public double getInPascalPerMeter() {
        return getInUnit(LinearResistanceUnits.PASCAL_PER_METER);
    }

    public double getInInchOfWaterPer100Feet() {
        return getInUnit(LinearResistanceUnits.INCH_OF_WATER_PER_100_FEET);
    }

    public double getInInchOfMercuryPer100Feet() {
        return getInUnit(LinearResistanceUnits.INCH_OF_MERCURY_PER_100_FEET);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinearResistance that = (LinearResistance) o;
        return Double.compare(that.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), that.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "LinearResistance{" + value + " " + unitType.getSymbol() + '}';
    }
}
