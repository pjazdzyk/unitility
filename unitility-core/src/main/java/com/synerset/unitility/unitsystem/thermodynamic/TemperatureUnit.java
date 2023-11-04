package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;

public interface TemperatureUnit extends Unit {
    @Override
    TemperatureUnit getBaseUnit();
}