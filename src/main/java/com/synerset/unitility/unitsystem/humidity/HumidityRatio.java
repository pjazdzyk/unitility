package com.synerset.unitility.unitsystem.humidity;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public class HumidityRatio implements PhysicalQuantity<HumidityRatio> {

    public static final HumidityRatio HUM_RATIO_MIN_LIMIT = HumidityRatio.ofKilogramPerKilogram(0);
    private final double value;
    private final double baseValue;
    private final Unit<HumidityRatio> unit;

    public HumidityRatio(double value, Unit<HumidityRatio> unit) {
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
    public Unit<HumidityRatio> getUnit() {
        return unit;
    }

    @Override
    public HumidityRatio toBaseUnit() {
        double valueInKgKg = unit.toValueInBaseUnit(value);
        return HumidityRatio.of(valueInKgKg, HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);
    }

    @Override
    public HumidityRatio toUnit(Unit<HumidityRatio> targetUnit) {
        double valueInKgKg = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKgKg);
        return HumidityRatio.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public HumidityRatio createNewWithValue(double value) {
        return HumidityRatio.of(value, unit);
    }

    // Convert to target unit
    public HumidityRatio toKilogramPerKilogram() {
        return toUnit(HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);
    }

    public HumidityRatio toPoundPerPound() {
        return toUnit(HumidityRatioUnits.POUND_PER_POUND);
    }

    // Get value in target unit
    public double getInKilogramPerKilogram() {
        return getIn(HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);
    }

    public double getInPoundPerPound() {
        return getIn(HumidityRatioUnits.POUND_PER_POUND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumidityRatio inputQuantity = (HumidityRatio) o;
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unit.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unit.getBaseUnit());
    }

    @Override
    public String toString() {
        return "HumidityRatio{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
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