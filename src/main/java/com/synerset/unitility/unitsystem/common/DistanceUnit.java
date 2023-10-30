package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.Unit;

public interface DistanceUnit extends Unit {
    @Override
    DistanceUnit getBaseUnit();
}
