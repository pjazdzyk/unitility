package com.synerset.unitility.unitsystem.humidity;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class HumidityRatio implements PhysicalQuantity<HumidityRatioUnit> {

    public static final HumidityRatio HUM_RATIO_MIN_LIMIT = HumidityRatio.ofKilogramPerKilogram(0);
    private final double value;
    private final double baseValue;
    private final HumidityRatioUnit unitType;

    public HumidityRatio(double value, HumidityRatioUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static HumidityRatio of(double value, HumidityRatioUnit unit) {
        return new HumidityRatio(value, unit);
    }

    public static HumidityRatio of(double value, String unitSymbol) {
        HumidityRatioUnit resolvedUnit = HumidityRatioUnits.fromSymbol(unitSymbol);
        return new HumidityRatio(value, resolvedUnit);
    }
    
    public static HumidityRatio ofKilogramPerKilogram(double value) {
        return new HumidityRatio(value, HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);
    }

    public static HumidityRatio ofPoundPerPound(double value) {
        return new HumidityRatio(value, HumidityRatioUnits.POUND_PER_POUND);
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
    public HumidityRatioUnit getUnitType() {
        return unitType;
    }

    @Override
    public HumidityRatio toBaseUnit() {
        double valueInKgKg = unitType.toValueInBaseUnit(value);
        return HumidityRatio.of(valueInKgKg, HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);
    }

    @Override
    public HumidityRatio toUnit(HumidityRatioUnit targetUnit) {
        double valueInKgKg = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKgKg);
        return HumidityRatio.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public HumidityRatio createNewWithValue(double value) {
        return HumidityRatio.of(value, unitType);
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
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "HumidityRatio{" + value + " " + unitType.getSymbol() + '}';
    }
}