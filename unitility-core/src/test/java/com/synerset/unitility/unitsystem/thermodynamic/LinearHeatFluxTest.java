package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class LinearHeatFluxTest {

    @Test
    @DisplayName("should identify W/m as base unit")
    void shouldIdentifyBaseUnit() {
        // Given & When
        LinearHeatFluxUnit baseUnit = LinearHeatFluxUnits.WATTS_PER_METER;

        // Then
        assertThat(baseUnit.getBaseUnit()).isEqualTo(baseUnit);
    }

    @Test
    @DisplayName("should convert between W/m and kW/m")
    void shouldConvertBetweenUnits() {
        // Given
        LinearHeatFlux q = LinearHeatFlux.ofWattsPerMeter(500.0);

        // When
        LinearHeatFlux inKw = q.toKilowattsPerMeter();

        // Then
        assertThat(inKw.getInKilowattsPerMeter()).isCloseTo(0.5, within(1E-10));
        assertThat(inKw.toWattsPerMeter().getInWattsPerMeter())
                .isCloseTo(500.0, within(1E-10));
    }

    @Test
    @DisplayName("should consider equal linear heat fluxes in different units")
    void shouldBeEqualAcrossUnits() {
        // Given
        LinearHeatFlux inW = LinearHeatFlux.ofWattsPerMeter(1000.0);
        LinearHeatFlux inKW = LinearHeatFlux.ofKilowattsPerMeter(1.0);

        // Then
        assertThat(inW).isEqualTo(inKW);
    }

    @Test
    @DisplayName("should convert to base unit correctly")
    void shouldConvertToBaseUnit() {
        // Given
        LinearHeatFlux q = LinearHeatFlux.ofKilowattsPerMeter(0.25);

        // When
        LinearHeatFlux base = q.toBaseUnit();

        // Then
        assertThat(base.getUnit()).isEqualTo(LinearHeatFluxUnits.WATTS_PER_METER);
        assertThat(base.getValue()).isCloseTo(250.0, within(1E-10));
    }
}
