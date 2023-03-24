package com.synerset.unitsystem.specificenthalpy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class SpecificEnthalpyTest {

    @Test
    @DisplayName("should convert J/kg to kJ/kg and vice versa")
    public void shouldProperlyConvertJoulesPerKilogramToKilojoulesPerKilogram() {
        // Given
        SpecificEnthalpy initialSpecificEnthalpy = SpecificEnthalpy.ofJoulePerKiloGram(1000);

        // When
        SpecificEnthalpy actual_KJ_PER_KG = initialSpecificEnthalpy.toKiloJoulePerKiloGram();
        SpecificEnthalpy actual_J_PER_KG = actual_KJ_PER_KG.toJoulePerKiloGram();

        // Then
        SpecificEnthalpy expected_KJ_PER_KG = SpecificEnthalpy.ofKiloJoulePerKiloGram(1);
        assertThat(actual_KJ_PER_KG.getValue()).isEqualTo(expected_KJ_PER_KG.getValue());
        assertThat(actual_J_PER_KG.getValue()).isEqualTo(1000);
    }

    @Test
    @DisplayName("should convert J/kg to BTU/lb and vice versa")
    public void shouldProperlyConvertJoulesPerKilogramToBTUPerPound() {
        // Given
        SpecificEnthalpy initialSpecificEnthalpy = SpecificEnthalpy.ofJoulePerKiloGram(1000);

        // When
        SpecificEnthalpy actual_BTU_PER_LB = initialSpecificEnthalpy.toBTUPerPound();
        SpecificEnthalpy actual_J_PER_KG = actual_BTU_PER_LB.toJoulePerKiloGram();

        // Then
        SpecificEnthalpy expected_BTU_PER_LB = SpecificEnthalpy.ofBTUPerPound(0.429922614);
        assertThat(actual_BTU_PER_LB.getValue()).isEqualTo(expected_BTU_PER_LB.getValue(), withPrecision(1E-10));
        assertThat(actual_J_PER_KG.getValue()).isEqualTo(1000, withPrecision(1E-10));
    }

}
