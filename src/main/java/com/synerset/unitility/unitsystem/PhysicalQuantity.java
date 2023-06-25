package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.utils.ValueFormatter;

public interface PhysicalQuantity<Q> extends BareQuantity {
    Unit<Q> getUnit();

    PhysicalQuantity<Q> toBaseUnit();

    PhysicalQuantity<Q> toUnit(Unit<Q> targetUnit);

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

    @Override
    default String toStringWithRelevantDigits(int relevantDigits) {
        return ValueFormatter.formatDoubleToRelevantDigits(getValue(), relevantDigits) + " " + getUnit().getSymbol();
    }

}
