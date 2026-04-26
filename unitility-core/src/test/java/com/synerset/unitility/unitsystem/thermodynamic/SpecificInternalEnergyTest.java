package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class SpecificInternalEnergyTest {

    @Test
    @DisplayName("Should identify base unit correctly")
    void shouldIdentifyBaseUnit() {
        // Given
        SpecificInternalEnergy energy = SpecificInternalEnergy.ofJoulesPerKilogram(100);

        // When/Then
        assertThat(energy.getUnit()).isEqualTo(SpecificInternalEnergyUnits.JOULE_PER_KILOGRAM);
    }

    @Test
    @DisplayName("Should convert between SI units correctly")
    void shouldConvertBetweenSIUnits() {
        // Given
        SpecificInternalEnergy energy = SpecificInternalEnergy.ofKiloJoulesPerKilogram(1.5);

        // When/Then
        assertThat(energy.getInJoulesPerKilogram()).isCloseTo(1500.0, within(1E-13));
        assertThat(energy.getInMegaJoulesPerKilogram()).isCloseTo(0.0015, within(1E-13));
    }

    @Test
    @DisplayName("Should convert between SI and Imperial units correctly")
    void shouldConvertBetweenSIAndImperial() {
        // Given: 1 BTU/lb approx 2326.0 J/kg
        SpecificInternalEnergy energy = SpecificInternalEnergy.ofBtuPerPound(1.0);

        // When/Then
        assertThat(energy.getInJoulesPerKilogram()).isCloseTo(2326.0, within(1.0)); // Rough check for BTU conversion
    }

    @Test
    @DisplayName("Should handle equality across different units")
    void shouldHandleEquality() {
        // Given
        SpecificInternalEnergy e1 = SpecificInternalEnergy.ofKiloJoulesPerKilogram(1.0);
        SpecificInternalEnergy e2 = SpecificInternalEnergy.ofJoulesPerKilogram(1000.0);

        // When/Then
        assertThat(e1).isEqualTo(e2);
    }

    @Test
    @DisplayName("Should parse from symbol correctly")
    void shouldParseFromSymbol() {
        // Given
        SpecificInternalEnergy energy = SpecificInternalEnergy.of(100, "kJ/kg");

        // When/Then
        assertThat(energy.getUnit()).isEqualTo(SpecificInternalEnergyUnits.KILOJOULE_PER_KILOGRAM);
    }
}
