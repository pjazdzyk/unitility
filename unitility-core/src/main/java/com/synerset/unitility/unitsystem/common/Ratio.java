package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.Objects;

public class Ratio implements CalculableQuantity<RatioUnit, Ratio> {

    private final double value;
    private final double baseValue;
    private final RatioUnit unitType;

    public Ratio(double value, RatioUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = RatioUnits.DECIMAL;
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

    public static <U extends Unit, Q extends PhysicalQuantity<U>> Ratio from(Q firstQuantity, Q secondQuantity) {
        Objects.requireNonNull(firstQuantity);
        Objects.requireNonNull(secondQuantity);
        double firstValue = firstQuantity.toBaseUnit().getValue();
        double secondValue = secondQuantity.toBaseUnit().getValue();
        if(secondValue == 0.0){
            throw new UnitSystemArgumentException("Second quantity at base unit cannot be 0 to calculate actual Ratio. Second quantity: " + secondQuantity);
        }
        return Ratio.ofDecimal(firstValue/secondValue);
    }

    public static Ratio from(double firstValue, double secondValue) {
        if(secondValue == 0.0){
            throw new UnitSystemArgumentException("Second quantity at base unit cannot be 0 to calculate actual Ratio. Second value: " + secondValue);
        }
        return Ratio.ofDecimal(firstValue/secondValue);
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
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
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
