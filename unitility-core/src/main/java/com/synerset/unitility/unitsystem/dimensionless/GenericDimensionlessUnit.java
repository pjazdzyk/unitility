package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.Unit;

public interface GenericDimensionlessUnit extends Unit {
    @Override
    GenericDimensionlessUnit getBaseUnit();
}
