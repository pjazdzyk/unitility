package com.synerset.unitility.unitsystem.humidity;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class RelativeHumidity implements PhysicalQuantity<RelativeHumidity> {

    public static final RelativeHumidity RH_MIN_LIMIT = RelativeHumidity.ofPercentage(0);
    public static final RelativeHumidity RH_MAX_LIMIT = RelativeHumidity.ofPercentage(100);
    private final double value;
    private final double baseValue;
    private final Unit<RelativeHumidity> unit;

    public RelativeHumidity(double value, Unit<RelativeHumidity> unit) {
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
    public Unit<RelativeHumidity> getUnit() {
        return unit;
    }

    @Override
    public RelativeHumidity toBaseUnit() {
        double valueInPascal = unit.toValueInBaseUnit(value);
        return RelativeHumidity.of(valueInPascal, RelativeHumidityUnits.PERCENT);
    }

    @Override
    public RelativeHumidity toUnit(Unit<RelativeHumidity> targetUnit) {
        double valueInPascal = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInPascal);
        return RelativeHumidity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public RelativeHumidity createNewWithValue(double value) {
        return RelativeHumidity.of(value, unit);
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
        return getIn(RelativeHumidityUnits.PERCENT);
    }

    public double getInDecimal() {
        return getIn(RelativeHumidityUnits.DECIMAL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelativeHumidity inputQuantity = (RelativeHumidity) o;
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unit.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unit.getBaseUnit());
    }

    @Override
    public String toString() {
        return "RelativeHumidity{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static RelativeHumidity of(double value, Unit<RelativeHumidity> unit) {
        return new RelativeHumidity(value, unit);
    }

    public static RelativeHumidity ofPercentage(double value) {
        return new RelativeHumidity(value, RelativeHumidityUnits.PERCENT);
    }

    public static RelativeHumidity ofDecimal(double value) {
        return new RelativeHumidity(value, RelativeHumidityUnits.DECIMAL);
    }

}
