package com.synerset.unitility.unitsystem.humidity;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class HumidityRatio implements PhysicalQuantity<HumidityRatio> {

    public static final RelativeHumidity HUM_RATIO_MIN_LIMIT = RelativeHumidity.ofPercentage(0);
    private final double value;
    private final Unit<HumidityRatio> unit;

    private HumidityRatio(double value, Unit<HumidityRatio> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<HumidityRatio> getUnit() {
        return unit;
    }

    @Override
    public HumidityRatio toBaseUnit() {
        double valueInKgKg = unit.toBaseUnit(value);
        return HumidityRatio.of(valueInKgKg, HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);
    }

    @Override
    public HumidityRatio toUnit(Unit<HumidityRatio> targetUnit) {
        double valueInKgKg = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInKgKg);
        return HumidityRatio.of(valueInTargetUnit, targetUnit);
    }

    // Custom converter methods, for most popular units
    public HumidityRatio toKilogramPerKilogram() {
        return toUnit(HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);
    }

    public HumidityRatio toPoundPerPound() {
        return toUnit(HumidityRatioUnits.POUND_PER_POUND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumidityRatio that = (HumidityRatio) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "HumidityRatio{" +
                "value=" + value +
                ", unit=" + unit.getSymbol() +
                '}';
    }

    public static HumidityRatio of(double value, Unit<HumidityRatio> unit) {
        return new HumidityRatio(value, unit);
    }

    public static HumidityRatio ofKilogramPerKilogram(double value) {
        return new HumidityRatio(value, HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);
    }

    public static HumidityRatio ofPoundPerPound(double value) {
        return new HumidityRatio(value, HumidityRatioUnits.POUND_PER_POUND);
    }
}