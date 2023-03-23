package com.synerset.unitsystem;

public interface Unit<Q> {
    String getSymbol();
    Unit<Q> getBaseUnit();
    double toBaseUnit(double valueInThisUnit);
    double fromBaseToThisUnit(double valueInBaseUnit);
}
