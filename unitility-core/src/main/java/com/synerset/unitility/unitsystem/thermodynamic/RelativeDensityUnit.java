package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;

/**
 * Marker unit interface for {@link RelativeDensity} — the dimensionless ratio of a substance's
 * density to that of a reference substance (e.g. gas relative to dry air, liquid relative to water).
 */
public interface RelativeDensityUnit extends Unit {
    @Override
    RelativeDensityUnit getBaseUnit();
}
