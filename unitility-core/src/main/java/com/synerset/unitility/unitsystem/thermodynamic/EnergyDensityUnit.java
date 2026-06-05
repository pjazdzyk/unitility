package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;

/**
 * Energy per unit volume (J/m³). Used for volumetric calorific (heating) value and the Wobbe index
 * of fuel gases, and any other volumetric energy-content quantity.
 */
public interface EnergyDensityUnit extends Unit {
    @Override
    EnergyDensityUnit getBaseUnit();
}
