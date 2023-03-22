package com.synerset;

public interface PhysicalQuantity<Q> {
    double getValue();
    Unit<Q> getUnit();
    Q toBaseUnit();
    Q toUnit(Unit<Q> targetUnit);
}
