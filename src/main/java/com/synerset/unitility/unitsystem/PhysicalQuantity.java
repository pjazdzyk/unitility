package com.synerset.unitility.unitsystem;

public interface PhysicalQuantity<Q> extends BareQuantity{
    Unit<Q> getUnit();
    Q toBaseUnit();
    Q toUnit(Unit<Q> targetUnit);
}
