package com.synerset.unitility.unitsystem;

import java.math.BigDecimal;

public interface BareQuantity {
    byte TO_STRING_PRECISION = 6;
    BigDecimal getValue();
}
