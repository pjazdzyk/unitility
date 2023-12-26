package com.synerset.unitility.unitsystem.basic.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;

public interface TemperatureUnit extends Unit {
    @Override
    TemperatureUnit getBaseUnit();
}