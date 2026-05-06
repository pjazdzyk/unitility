package com.synerset.unitility.unitsystem.flow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

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

    @Test
    @DisplayName("should convert between kg/(m²·s) and lb/(ft²·s)")
    void shouldConvertToImperialPoundsPerSquareFootSecond() {
        // Given
        MassFlux massFlux = MassFlux.ofKilogramsPerSquareMeterSecond(4.8824276364);

        // When
        MassFlux inImperial = massFlux.toPoundsPerSquareFootSecond();

        // Then
        assertThat(inImperial.getInPoundsPerSquareFootSecond()).isEqualByComparingTo(1.0);
        assertThat(inImperial.toKilogramsPerSquareMeterSecond().getInKilogramsPerSquareMeterSecond())
                .isEqualByComparingTo(4.8824276364);
    }

    @Test
    @DisplayName("should convert between kg/(m²·s) and lb/(ft²·h)")
    void shouldConvertToImperialPoundsPerSquareFootHour() {
        // Given
        MassFlux massFlux = MassFlux.ofKilogramsPerSquareMeterSecond(4.8824276364);

        // When
        MassFlux inImperial = massFlux.toPoundsPerSquareFootHour();

        // Then
        assertThat(inImperial.getInPoundsPerSquareFootHour()).isCloseTo(3600.0, within(1E-10));
        assertThat(inImperial.toKilogramsPerSquareMeterSecond().getInKilogramsPerSquareMeterSecond())
                .isCloseTo(4.8824276364, within(1E-10));
    }

    @Test
    @DisplayName("should consider equal mass fluxes across metric and imperial units")
    void shouldBeEqualAcrossMetricAndImperial() {
        // Given
        MassFlux inKg = MassFlux.ofKilogramsPerSquareMeterSecond(4.8824276364);
        MassFlux inLb = MassFlux.ofPoundsPerSquareFootSecond(1.0);

        // Then
        assertThat(inKg).isEqualTo(inLb);
    }

    @Test
    @DisplayName("should consider equal mass fluxes across metric and imperial hour units")
    void shouldBeEqualAcrossMetricAndImperialHour() {
        // Given
        MassFlux inKg = MassFlux.ofKilogramsPerSquareMeterSecond(4.8824276364);
        MassFlux inLb = MassFlux.ofPoundsPerSquareFootHour(3600.0);

        // Then
        assertThat(inKg).isEqualTo(inLb);
    }
}
