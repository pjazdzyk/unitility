package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class HeatFluxTest {

    @Test
    @DisplayName("should identify W/m² as base unit")
    void shouldIdentifyBaseUnit() {
        // Given & When
        HeatFluxUnit baseUnit = HeatFluxUnits.WATTS_PER_SQUARE_METER;

        // Then
        assertThat(baseUnit.getBaseUnit()).isEqualTo(baseUnit);
    }

    @Test
    @DisplayName("should convert between W/m² and kW/m²")
    void shouldConvertBetweenUnits() {
        // Given
        HeatFlux q = HeatFlux.ofWattsPerSquareMeter(1000.0);

        // When
        HeatFlux inKw = q.toKilowattsPerSquareMeter();

        // Then
        assertThat(inKw.getInKilowattsPerSquareMeter()).isCloseTo(1.0, within(1E-10));
        assertThat(inKw.toWattsPerSquareMeter().getInWattsPerSquareMeter())
                .isCloseTo(1000.0, within(1E-10));
    }

    @Test
    @DisplayName("should consider equal heat fluxes in different units")
    void shouldBeEqualAcrossUnits() {
        // Given
        HeatFlux inW = HeatFlux.ofWattsPerSquareMeter(500.0);
        HeatFlux inKW = HeatFlux.ofKilowattsPerSquareMeter(0.5);

        // Then
        assertThat(inW).isEqualTo(inKW);
    }

    @Test
    @DisplayName("should convert to base unit correctly")
    void shouldConvertToBaseUnit() {
        // Given
        HeatFlux q = HeatFlux.ofKilowattsPerSquareMeter(2.5);

        // When
        HeatFlux base = q.toBaseUnit();

        // Then
        assertThat(base.getUnit()).isEqualTo(HeatFluxUnits.WATTS_PER_SQUARE_METER);
        assertThat(base.getValue()).isCloseTo(2500.0, within(1E-10));
    }
}
