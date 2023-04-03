package com.synerset.unitility.unitsystem.massflow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class MassFlowTest {

    @Test
    @DisplayName("should convert kg/s to kg/h and vice versa")
    void shouldProperlyConvertKilogramPerSecondToKilogramPerHour() {
        // Given
        MassFlow initialMassFlow = MassFlow.ofKilogramsPerSecond(1.5);

        // When
        MassFlow actual_KG_PER_H = initialMassFlow.toKiloGramPerHour();
        MassFlow actual_KG_PER_S = actual_KG_PER_H.toKilogramsPerSecond();

        // Then
        MassFlow expected_KG_PER_H = MassFlow.ofKilogramsPerHour(5400);
        assertThat(actual_KG_PER_H).isEqualTo(expected_KG_PER_H);
        assertThat(actual_KG_PER_S).isEqualTo(initialMassFlow);
    }

    @Test
    @DisplayName("should convert kg/s to t/h and vice versa")
    void shouldProperlyConvertKilogramPerSecondToTonnePerHour() {
        // Given
        MassFlow initialMassFlow = MassFlow.ofKilogramsPerSecond(1.5);

        // When
        MassFlow actual_TON_PER_H = initialMassFlow.toTonnesPerHour();
        MassFlow actual_KG_PER_S = actual_TON_PER_H.toKilogramsPerSecond();

        // Then
        MassFlow expected_TON_PER_H = MassFlow.ofTonnesPerHour(5.4);
        assertThat(actual_TON_PER_H.getValue()).isEqualTo(expected_TON_PER_H.getValue(), withPrecision(1E-15));
        assertThat(actual_KG_PER_S).isEqualTo(initialMassFlow);
    }

    @Test
    @DisplayName("should convert kg/s to lb/s and vice versa")
    void shouldProperlyConvertKilogramPerSecondToPoundPerSecond() {
        // Given
        MassFlow initialMassFlow = MassFlow.ofKilogramsPerSecond(1.5);

        // When
        MassFlow actual_LB_PER_S = initialMassFlow.toPoundsPerSecond();
        MassFlow actual_KG_PER_S = actual_LB_PER_S.toKilogramsPerSecond();

        // Then
        MassFlow expected_LB_PER_S = MassFlow.ofPoundsPerSecond(3.306933932773164);
        assertThat(actual_LB_PER_S.getValue()).isEqualTo(expected_LB_PER_S.getValue(), withPrecision(1E-15));
        assertThat(actual_KG_PER_S).isEqualTo(initialMassFlow);
    }

}