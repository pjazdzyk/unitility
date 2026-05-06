package com.synerset.unitility.unitsystem.flow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MassFluxTest {

    @Test
    @DisplayName("should identify base unit as kg/(m²·s)")
    void shouldIdentifyBaseUnit() {
        // Given & When
        MassFluxUnit baseUnit = MassFluxUnits.KILOGRAM_PER_SQUARE_METER_SECOND;

        // Then
        assertThat(baseUnit.getBaseUnit()).isEqualTo(baseUnit);
    }

    @Test
    @DisplayName("should convert between kg/(m²·s) and g/(m²·s)")
    void shouldConvertBetweenUnits() {
        // Given
        MassFlux massFlux = MassFlux.ofKilogramsPerSquareMeterSecond(1.0);

        // When
        MassFlux inGrams = massFlux.toGramsPerSquareMeterSecond();

        // Then
        assertThat(inGrams.getInGramsPerSquareMeterSecond()).isEqualTo(1000.0);
        assertThat(inGrams.toKilogramsPerSquareMeterSecond().getInKilogramsPerSquareMeterSecond())
                .isEqualByComparingTo(1.0);
    }

    @Test
    @DisplayName("should consider equal mass fluxes in different units")
    void shouldBeEqualAcrossUnits() {
        // Given
        MassFlux inKg = MassFlux.ofKilogramsPerSquareMeterSecond(1.0);
        MassFlux inG = MassFlux.ofGramsPerSquareMeterSecond(1000.0);

        // Then
        assertThat(inKg).isEqualTo(inG);
    }

    @Test
    @DisplayName("should convert to base unit correctly")
    void shouldConvertToBaseUnit() {
        // Given
        MassFlux massFlux = MassFlux.ofGramsPerSquareMeterSecond(500.0);

        // When
        MassFlux baseFlux = massFlux.toBaseUnit();

        // Then
        assertThat(baseFlux.getUnit()).isEqualTo(MassFluxUnits.KILOGRAM_PER_SQUARE_METER_SECOND);
        assertThat(baseFlux.getValue()).isEqualByComparingTo(0.5);
    }
}
