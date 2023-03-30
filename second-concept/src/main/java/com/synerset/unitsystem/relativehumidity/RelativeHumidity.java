package com.synerset.unitsystem.relativehumidity;

import com.synerset.unitsystem.PhysicalQuantity;
import com.synerset.unitsystem.Unit;

import java.util.Objects;

public class RelativeHumidity implements PhysicalQuantity<RelativeHumidity> {

    public static final RelativeHumidity RH_MIN_LIMIT = RelativeHumidity.ofPercentage(0);
    public static final RelativeHumidity RH_MAX_LIMIT = RelativeHumidity.ofPercentage(100);

    public static final byte TO_STRING_PRECISION = 3;
    private final double value;
    private final Unit<RelativeHumidity> unit;

    private RelativeHumidity(double value, Unit<RelativeHumidity> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<RelativeHumidity> getUnit() {
        return unit;
    }

    @Override
    public RelativeHumidity toBaseUnit() {
        double valueInPascal = unit.toBaseUnit(value);
        return RelativeHumidity.of(valueInPascal, RelativeHumidityUnits.PERCENT);
    }

    @Override
    public RelativeHumidity toUnit(Unit<RelativeHumidity> targetUnit) {
        double valueInPascal = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInPascal);
        return RelativeHumidity.of(valueInTargetUnit, targetUnit);
    }

    // Custom converter methods, for most popular units
    public RelativeHumidity toPercent() {
        return toUnit(RelativeHumidityUnits.PERCENT);
    }

    public RelativeHumidity toDecimal() {
        return toUnit(RelativeHumidityUnits.DECIMAL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelativeHumidity that = (RelativeHumidity) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    public String toString() {
        return String.format("%." + TO_STRING_PRECISION + "f %s", value, unit.getSymbol());
    }

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
