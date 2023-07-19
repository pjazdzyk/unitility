package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.utils.ValueFormatter;

public interface BareQuantity {
    double getValue();

    default String toStringWithRelevantDigits(int relevantDigits) {
        return ValueFormatter.formatDoubleToRelevantDigits(getValue(), relevantDigits);
    }
}
