package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.Unit;

public interface TimeUnit extends Unit {
    @Override
    TimeUnit getBaseUnit();
}
