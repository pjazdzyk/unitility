package com.synerset.unitility.unitsystem.humidity;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class RelativeHumidity implements CalculableQuantity<RelativeHumidityUnit, RelativeHumidity> {

    public static final RelativeHumidity RH_MIN_LIMIT = RelativeHumidity.ofPercentage(0);
    public static final RelativeHumidity RH_MAX_LIMIT = RelativeHumidity.ofPercentage(100);
    private final double value;
    private final double baseValue;
    private final RelativeHumidityUnit unitType;

    public RelativeHumidity(double value, RelativeHumidityUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = RelativeHumidityUnits.DECIMAL;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static RelativeHumidity of(double value, RelativeHumidityUnit unit) {
        return new RelativeHumidity(value, unit);
    }

    public static RelativeHumidity of(double value, String unitSymbol) {
        RelativeHumidityUnit resolvedUnit = RelativeHumidityUnits.fromSymbol(unitSymbol);
        return new RelativeHumidity(value, resolvedUnit);
    }

    public static RelativeHumidity ofPercentage(double value) {
        return new RelativeHumidity(value, RelativeHumidityUnits.PERCENT);
    }

    public static RelativeHumidity ofDecimal(double value) {
        return new RelativeHumidity(value, RelativeHumidityUnits.DECIMAL);
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
    public RelativeHumidityUnit getUnit() {
        return unitType;
    }

    @Override
    public RelativeHumidity toBaseUnit() {
        double valueInPascal = unitType.toValueInBaseUnit(value);
        return of(valueInPascal, RelativeHumidityUnits.PERCENT);
    }

    @Override
    public RelativeHumidity toUnit(RelativeHumidityUnit targetUnit) {
        double valueInPascal = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInPascal);
        return RelativeHumidity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public RelativeHumidity toUnit(String targetUnit) {
        RelativeHumidityUnit resolvedUnit = RelativeHumidityUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public RelativeHumidity withValue(double value) {
        return RelativeHumidity.of(value, unitType);
    }

    // Convert to target unit
    public RelativeHumidity toPercent() {
        return toUnit(RelativeHumidityUnits.PERCENT);
    }

    public RelativeHumidity toDecimal() {
        return toUnit(RelativeHumidityUnits.DECIMAL);
    }

    // Get value in target unit
    public double getInPercent() {
        return getInUnit(RelativeHumidityUnits.PERCENT);
    }

    public double getInDecimal() {
        return getInUnit(RelativeHumidityUnits.DECIMAL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelativeHumidity inputQuantity = (RelativeHumidity) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "RelativeHumidity{" + value + " " + unitType.getSymbol() + '}';
    }

}
