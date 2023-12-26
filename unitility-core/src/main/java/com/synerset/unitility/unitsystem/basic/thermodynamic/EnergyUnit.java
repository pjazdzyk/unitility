package com.synerset.unitility.unitsystem.basic.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;

public interface EnergyUnit extends Unit {
    @Override
    EnergyUnit getBaseUnit();
}