package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;

/**
 * Marker unit interface for {@link VapourQuality} — a bounded mass-fraction of vapour in a
 * two-phase mixture (0 = saturated liquid, 1 = saturated vapour).
 */
public interface VapourQualityUnit extends Unit {
    @Override
    VapourQualityUnit getBaseUnit();
}
