package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class ConfinementNumber implements CalculableQuantity<ConfinementNumberUnit, ConfinementNumber> {
    private final double value;
    private final double baseValue;
    private final ConfinementNumberUnit unitType;

    public ConfinementNumber(double value, ConfinementNumberUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    public static ConfinementNumber of(double value) {
        return new ConfinementNumber(value, ConfinementNumberUnits.DIMENSIONLESS);
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
    public ConfinementNumberUnit getUnit() {
        return unitType;
    }

    @Override
    public ConfinementNumber toBaseUnit() {
        return this;
    }

    @Override
    public ConfinementNumber toUnit(ConfinementNumberUnit targetUnit) {
        return this;
    }

    @Override
    public ConfinementNumber toUnit(String targetUnit) {
        return this;
    }

    @Override
    public ConfinementNumber withValue(double value) {
        return ConfinementNumber.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfinementNumber that = (ConfinementNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "ConfinementNumber{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
