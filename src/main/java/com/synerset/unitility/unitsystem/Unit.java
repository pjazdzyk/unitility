package com.synerset.unitility.unitsystem;

import java.math.BigDecimal;

public interface Unit<Q> {
    String getSymbol();
    Unit<Q> getBaseUnit();
    BigDecimal toBaseUnit(BigDecimal valueInThisUnit);
    BigDecimal fromBaseToThisUnit(BigDecimal valueInBaseUnit);
}
