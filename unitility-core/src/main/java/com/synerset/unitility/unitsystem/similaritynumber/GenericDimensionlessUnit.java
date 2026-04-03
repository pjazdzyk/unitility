package com.synerset.unitility.unitsystem.similaritynumber;

import com.synerset.unitility.unitsystem.Unit;

public interface GenericDimensionlessUnit extends Unit {
    @Override
    GenericDimensionlessUnit getBaseUnit();
}
