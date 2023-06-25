package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class ThermalDiffusivityTest {

    @Test
    @DisplayName("should convert to square meter per second from square feet per second and vice versa")
    void shouldProperlyConvertToSquareMeterPerSecondFromSquareFeetPerSecond() {
        // Given
        ThermalDiffusivity initialDiffSquareFtPerS = ThermalDiffusivity.ofSquareFeetPerSecond(10.0);

        // When
        ThermalDiffusivity actualInSquareMPerS = initialDiffSquareFtPerS.toUnit(ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND);
        ThermalDiffusivity actualInSquareFtPerS = actualInSquareMPerS.toUnit(ThermalDiffusivityUnits.SQUARE_FEET_PER_SECOND);

        // Then
        ThermalDiffusivity expectedSquareMPerS = ThermalDiffusivity.ofSquareMeterPerSecond(0.9290304);
        assertThat(actualInSquareMPerS.getValue()).isEqualTo(expectedSquareMPerS.getValue(), withPrecision(1E-15));
        assertThat(actualInSquareFtPerS).isEqualTo(initialDiffSquareFtPerS);
    }

    @Test
    @DisplayName("should have square meter per second as base unit")
    void shouldHaveSquareMeterPerSecondAsBaseUnit() {
        // Given
        ThermalDiffusivityUnits expectedBaseUnit = ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND;

        // When
        ThermalDiffusivity diffusivityInSquareFeetPerSecond = ThermalDiffusivity.ofSquareFeetPerSecond(10);
        Unit<ThermalDiffusivity> actualBaseUnit = diffusivityInSquareFeetPerSecond.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

}
