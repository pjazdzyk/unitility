package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class JakobNumber implements CalculableQuantity<JakobNumberUnit, JakobNumber> {
    private final double value;
    private final double baseValue;
    private final JakobNumberUnit unitType;

    public JakobNumber(double value, JakobNumberUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    public static JakobNumber of(double value) {
        return new JakobNumber(value, JakobNumberUnits.DIMENSIONLESS);
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
    public JakobNumberUnit getUnit() {
        return unitType;
    }

    @Override
    public JakobNumber toBaseUnit() {
        return this;
    }

    @Override
    public JakobNumber toUnit(JakobNumberUnit targetUnit) {
        return this;
    }

    @Override
    public JakobNumber toUnit(String targetUnit) {
        return this;
    }

    @Override
    public JakobNumber withValue(double value) {
        return JakobNumber.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JakobNumber that = (JakobNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "JakobNumber{" + value + " " + unitType.getSymbol() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
