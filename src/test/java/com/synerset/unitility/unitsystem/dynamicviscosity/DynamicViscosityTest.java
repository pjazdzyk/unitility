package com.synerset.unitility.unitsystem.dynamicviscosity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DynamicViscosityTest {

    @Test
    @DisplayName("should convert kg/(m·s) to P and vice versa")
    void shouldProperlyConvertKilogramsPerMeterSecondToPoise() {
        // Given
        DynamicViscosity initialViscosity = DynamicViscosity.ofKiloGramPerMeterSecond(100);

        // When
        DynamicViscosity actual_Poise = initialViscosity.toPoise();
        DynamicViscosity actual_KG_PER_M_S = actual_Poise.toKiloGramPerMeterSecond();

        // Then
        DynamicViscosity expected_Poise = DynamicViscosity.ofPoise(1000);
        assertThat(actual_Poise.getValue()).isEqualTo(expected_Poise.getValue());
        assertThat(actual_KG_PER_M_S.getValue()).isEqualTo(initialViscosity.getValue());
    }

    @Test
    @DisplayName("should convert kg/(m·s) to Pa·s and vice versa")
    void shouldProperlyConvertKilogramsPerMeterSecondToPascalSecond() {
        // Given
        DynamicViscosity initialViscosity = DynamicViscosity.ofKiloGramPerMeterSecond(100);

        // When
        DynamicViscosity actual_PaS = initialViscosity.toPascalSecond();
        DynamicViscosity actual_KG_PER_M_S = actual_PaS.toKiloGramPerMeterSecond();

        // Then
        DynamicViscosity expected_PaS = DynamicViscosity.ofPascalSecond(100);
        assertThat(actual_PaS.getValue()).isEqualTo(expected_PaS.getValue());
        assertThat(actual_KG_PER_M_S.getValue()).isEqualTo(initialViscosity.getValue());
    }

}
