package com.synerset.unitility.unitsystem.electric;

import com.synerset.unitility.unitsystem.Unit;

public interface VoltageUnit extends Unit {
    @Override
    VoltageUnit getBaseUnit();
}