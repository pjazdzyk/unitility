package com.synerset.unitsystem.specificheat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class SpecificHeatTest {

    @Test
    @DisplayName("should convert J/(kg·K) to kJ/(kg·K) and vice versa")
    public void shouldProperlyConvertJoulesPerKilogramKelvinToKilojoulesPerKilogramKelvin() {
        // Given
        SpecificHeat initialSpecificHeat = SpecificHeat.ofJoulePerKiloGramKelvin(1000);

        // When
        SpecificHeat actual_KJ_PER_KG_K = initialSpecificHeat.toKiloJoulePerKiloGramKelvin();
        SpecificHeat actual_J_PER_KG_K = actual_KJ_PER_KG_K.toJoulePerKiloGramKelvin();

        // Then
        SpecificHeat expected_KJ_PER_KG_K = SpecificHeat.ofKiloJoulePerKiloGramKelvin(1);
        assertThat(actual_KJ_PER_KG_K.getValue()).isEqualTo(expected_KJ_PER_KG_K.getValue(), withPrecision(1E-10));
        assertThat(actual_J_PER_KG_K.getValue()).isEqualTo(1000, withPrecision(1E-10));
    }

    @Test
    @DisplayName("should convert J/(kg·K) to BTU/(lb·°F) and vice versa")
    public void shouldProperlyConvertJoulesPerKilogramKelvinToBTUPerPoundFahrenheit() {
        // Given
        SpecificHeat initialSpecificHeat = SpecificHeat.ofJoulePerKiloGramKelvin(1000);

        // When
        SpecificHeat actual_BTU_PER_KG_F = initialSpecificHeat.toBTUPerPoundFahrenheit();
        SpecificHeat actual_J_PER_KG_KG = actual_BTU_PER_KG_F.toJoulePerKiloGramKelvin();

        // Then
        SpecificHeat expected_BTU_PER_KG_F = SpecificHeat.ofBTUPerPoundFahrenheit(0.2388458969999981);
        assertThat(actual_BTU_PER_KG_F.getValue()).isEqualTo(expected_BTU_PER_KG_F.getValue(), withPrecision(1E-10));
        assertThat(actual_J_PER_KG_KG.getValue()).isEqualTo(1000, withPrecision(1E-10));
    }

}
