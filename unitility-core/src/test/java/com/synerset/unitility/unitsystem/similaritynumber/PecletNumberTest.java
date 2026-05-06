package com.synerset.unitility.unitsystem.similaritynumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PecletNumberTest {

    @Test
    @DisplayName("should create Peclet number")
    void shouldCreatePecletNumber() {
        // Given
        double expectedValue = 10000.0;

        // When
        PecletNumber actual = PecletNumber.of(expectedValue);

        // Then
        assertThat(actual.getValue()).isEqualTo(expectedValue);
        assertThat(actual.getBaseValue()).isEqualTo(actual.getValue());
        assertThat(actual.toUnit(PecletNumberUnits.DIMENSIONLESS)).isEqualTo(actual.toBaseUnit());
    }
}
