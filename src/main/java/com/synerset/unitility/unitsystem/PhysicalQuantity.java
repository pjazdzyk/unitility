package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public interface PhysicalQuantity<Q> {
    double getValue();

    Unit<Q> getUnit();

    PhysicalQuantity<Q> toBaseUnit();

    PhysicalQuantity<Q> toUnit(Unit<Q> targetUnit);

    PhysicalQuantity<Q> createNewWithValue(double value);

    default String toStringWithRelevantDigits(int relevantDigits) {
        return ValueFormatter.formatDoubleToRelevantDigits(getValue(), relevantDigits) + " " + getUnit().getSymbol();
    }

    // Comparing methods
    default boolean isGreaterThan(PhysicalQuantity<Q> quantity) {
        if (Objects.isNull(quantity)) {
            return false;
        }
        double condition = this.toBaseUnit().getValue() - quantity.toBaseUnit().getValue();
        return condition > 0.0;
    }

    default boolean isEqualOrGreaterThan(PhysicalQuantity<Q> quantity) {
        if (Objects.isNull(quantity)) {
            return false;
        }
        double condition = this.toBaseUnit().getValue() - quantity.toBaseUnit().getValue();
        return condition >= 0.0;
    }

    default boolean isLowerThan(PhysicalQuantity<Q> quantity) {
        if (Objects.isNull(quantity)) {
            return false;
        }
        double condition = this.toBaseUnit().getValue() - quantity.toBaseUnit().getValue();
        return condition < 0.0;
    }

    default boolean isEqualOrLowerThan(PhysicalQuantity<Q> quantity) {
        if (Objects.isNull(quantity)) {
            return false;
        }
        double condition = this.toBaseUnit().getValue() - quantity.toBaseUnit().getValue();
        return condition <= 0.0;
    }

    default boolean isEqualsWithPrecision(PhysicalQuantity<Q> quantity, double epsilon) {
        if (this == quantity) return true;
        if (quantity == null || getClass() != quantity.getClass()) return false;
        PhysicalQuantity<Q> thisInBaseUnit = this.toBaseUnit();
        PhysicalQuantity<Q> inputInBaseUnit = quantity.toBaseUnit();
        if (thisInBaseUnit.getUnit() != inputInBaseUnit.getUnit()) return false;
        double thisValue = thisInBaseUnit.getValue();
        double inputValue = inputInBaseUnit.getValue();
        return Math.abs(thisValue - inputValue) < epsilon;
    }

    // Transformation methods
    default PhysicalQuantity<Q> add(double value) {
        double newValue = getValue() + value;
        return createNewWithValue(newValue);
    }

    default PhysicalQuantity<Q> add(PhysicalQuantity<Q> inputQuantity) {
        Unit<Q> sourceUnit = this.getUnit();
        PhysicalQuantity<Q> inputInSourceUnits = inputQuantity.toUnit(sourceUnit);
        double newValue = this.getValue() + inputInSourceUnits.getValue();
        return createNewWithValue(newValue);
    }

    default PhysicalQuantity<Q> subtract(double value) {
        double newValue = getValue() - value;
        return createNewWithValue(newValue);
    }

    default PhysicalQuantity<Q> subtract(PhysicalQuantity<Q> inputQuantity) {
        Unit<Q> sourceUnit = this.getUnit();
        PhysicalQuantity<Q> inputInSourceUnits = inputQuantity.toUnit(sourceUnit);
        double newValue = this.getValue() - inputInSourceUnits.getValue();
        return createNewWithValue(newValue);
    }

    default PhysicalQuantity<Q> multiply(double value) {
        double newValue = getValue() * value;
        return createNewWithValue(newValue);
    }

    default double multiply(PhysicalQuantity<?> inputQuantity) {
        return this.getValue() * inputQuantity.getValue();
    }

    default PhysicalQuantity<Q> divide(double value) {
        if (value == 0) {
            throw new UnitSystemArgumentException("Divider value cannot be zero.");
        }
        double newValue = getValue() / value;
        return createNewWithValue(newValue);
    }

    default double divide(PhysicalQuantity<?> inputQuantity) {
        double thisValue = getValue();
        if (thisValue == 0) {
            throw new UnitSystemArgumentException("Divider quantity value cannot be zero.");
        }
        return thisValue / inputQuantity.getValue();
    }

}
