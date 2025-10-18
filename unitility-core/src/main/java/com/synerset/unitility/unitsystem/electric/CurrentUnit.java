package com.synerset.unitility.unitsystem.electric;

import com.synerset.unitility.unitsystem.Unit;

public interface CurrentUnit extends Unit {
    @Override
    CurrentUnit getBaseUnit();
}