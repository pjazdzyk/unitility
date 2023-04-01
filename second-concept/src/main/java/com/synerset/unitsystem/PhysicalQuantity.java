package com.synerset.unitsystem;

public interface PhysicalQuantity<Q> extends BareQuantity{
    Unit<Q> getUnit();
    Q toBaseUnit();
    Q toUnit(Unit<Q> targetUnit);
}
