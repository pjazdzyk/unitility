package com.synerset.unitility.unitsystem.similaritynumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RayleighNumberTest {

    @Test
    @DisplayName("should create Rayleigh number")
    void shouldCreateRayleighNumber() {
        // Given
        double expectedValue = 1E7;

        // When
        RayleighNumber actual = RayleighNumber.of(expectedValue);

        // Then
        assertThat(actual.getValue()).isEqualTo(expectedValue);
        assertThat(actual.getBaseValue()).isEqualTo(actual.getValue());
        assertThat(actual.toUnit(RayleighNumberUnits.DIMENSIONLESS)).isEqualTo(actual.toBaseUnit());
    }
}
