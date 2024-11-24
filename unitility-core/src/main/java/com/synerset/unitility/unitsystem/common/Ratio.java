package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Ratio implements CalculableQuantity<RatioUnit, Ratio> {

    public static final Ratio RH_MIN_LIMIT = Ratio.ofPercentage(0);
    public static final Ratio RH_MAX_LIMIT = Ratio.ofPercentage(100);
    private final double value;
    private final double baseValue;
    private final RatioUnit unitType;

    public Ratio(double value, RatioUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = RatioUnits.getDefaultUnit();
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Ratio of(double value, RatioUnit unit) {
        return new Ratio(value, unit);
    }

    public static Ratio of(double value, String unitSymbol) {
        RatioUnit resolvedUnit = RatioUnits.fromSymbol(unitSymbol);
        return new Ratio(value, resolvedUnit);
    }

    public static Ratio ofPercentage(double value) {
        return new Ratio(value, RatioUnits.PERCENT);
    }

    public static Ratio ofDecimal(double value) {
        return new Ratio(value, RatioUnits.DECIMAL);
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
    public RatioUnit getUnit() {
        return unitType;
    }

    @Override
    public Ratio toBaseUnit() {
        double valueInPascal = unitType.toValueInBaseUnit(value);
        return of(valueInPascal, RatioUnits.PERCENT);
    }

    @Override
    public Ratio toUnit(RatioUnit targetUnit) {
        double valueInPascal = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInPascal);
        return Ratio.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Ratio toUnit(String targetUnit) {
        RatioUnit resolvedUnit = RatioUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Ratio withValue(double value) {
        return Ratio.of(value, unitType);
    }

    // Convert to target unit
    public Ratio toPercent() {
        return toUnit(RatioUnits.PERCENT);
    }

    public Ratio toDecimal() {
        return toUnit(RatioUnits.DECIMAL);
    }

    // Get value in target unit
    public double getInPercent() {
        return getInUnit(RatioUnits.PERCENT);
    }

    public double getInDecimal() {
        return getInUnit(RatioUnits.DECIMAL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ratio inputQuantity = (Ratio) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Ratio{" + value + " " + unitType.getSymbol() + '}';
    }

}
