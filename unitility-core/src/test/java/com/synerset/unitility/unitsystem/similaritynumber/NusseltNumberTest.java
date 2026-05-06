package com.synerset.unitility.unitsystem.similaritynumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NusseltNumberTest {

    @Test
    @DisplayName("should create Nusselt number")
    void shouldCreateNusseltNumber() {
        // Given
        double expectedValue = 45.0;

        // When
        NusseltNumber actual = NusseltNumber.of(expectedValue);

        // Then
        assertThat(actual.getValue()).isEqualTo(expectedValue);
        assertThat(actual.getBaseValue()).isEqualTo(actual.getValue());
        assertThat(actual.toUnit(NusseltNumberUnits.DIMENSIONLESS)).isEqualTo(actual.toBaseUnit());
    }
}
