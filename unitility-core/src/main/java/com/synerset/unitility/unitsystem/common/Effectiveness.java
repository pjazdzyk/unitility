package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.Objects;

public class Effectiveness implements CalculableQuantity<EffectivenessUnit, Effectiveness> {

    private final double value;
    private final double baseValue;
    private final EffectivenessUnit unitType;

    public Effectiveness(double value, EffectivenessUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = EffectivenessUnits.DECIMAL;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Effectiveness of(double value, EffectivenessUnit unit) {
        return new Effectiveness(value, unit);
    }

    public static Effectiveness of(double value, String unitSymbol) {
        EffectivenessUnit resolvedUnit = EffectivenessUnits.fromSymbol(unitSymbol);
        return new Effectiveness(value, resolvedUnit);
    }

    public static Effectiveness ofPercentage(double value) {
        return new Effectiveness(value, EffectivenessUnits.PERCENT);
    }

    public static Effectiveness ofDecimal(double value) {
        return new Effectiveness(value, EffectivenessUnits.DECIMAL);
    }

    public static <U extends Unit, Q extends PhysicalQuantity<U>> Effectiveness from(Q firstQuantity, Q secondQuantity) {
        Objects.requireNonNull(firstQuantity);
        Objects.requireNonNull(secondQuantity);
        double firstValue = firstQuantity.toBaseUnit().getValue();
        double secondValue = secondQuantity.toBaseUnit().getValue();
        if(secondValue == 0.0){
            throw new UnitSystemArgumentException("Second quantity at base unit cannot be 0 to calculate actual Effectiveness. Second quantity: " + secondQuantity);
        }
        return Effectiveness.ofDecimal(firstValue/secondValue);
    }

    public static Effectiveness from(double firstValue, double secondValue) {
        if(secondValue == 0.0){
            throw new UnitSystemArgumentException("Second quantity at base unit cannot be 0 to calculate actual Effectiveness. Second value: " + secondValue);
        }
        return Effectiveness.ofDecimal(firstValue/secondValue);
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
    public EffectivenessUnit getUnit() {
        return unitType;
    }

    @Override
    public Effectiveness toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Effectiveness toUnit(EffectivenessUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return Effectiveness.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Effectiveness toUnit(String targetUnit) {
        EffectivenessUnit resolvedUnit = EffectivenessUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Effectiveness withValue(double value) {
        return Effectiveness.of(value, unitType);
    }

    // Convert to target unit
    public Effectiveness toPercent() {
        return toUnit(EffectivenessUnits.PERCENT);
    }

    public Effectiveness toDecimal() {
        return toUnit(EffectivenessUnits.DECIMAL);
    }

    // Get value in the target unit
    public double getInPercent() {
        return getInUnit(EffectivenessUnits.PERCENT);
    }

    public double getInDecimal() {
        return getInUnit(EffectivenessUnits.DECIMAL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Effectiveness inputQuantity = (Effectiveness) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Effectiveness{" + value + " " + unitType.getSymbol() + '}';
    }

}
