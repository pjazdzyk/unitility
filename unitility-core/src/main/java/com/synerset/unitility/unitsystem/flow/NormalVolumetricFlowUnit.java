package com.synerset.unitility.unitsystem.flow;

import com.synerset.unitility.unitsystem.Unit;

/**
 * Marker unit interface for {@link NormalVolumetricFlow} — a volumetric gas flow referenced to a
 * fixed thermodynamic state (normal or standard conditions), as opposed to actual-state
 * {@link VolumetricFlow}.
 */
public interface NormalVolumetricFlowUnit extends Unit {
    @Override
    NormalVolumetricFlowUnit getBaseUnit();
}
