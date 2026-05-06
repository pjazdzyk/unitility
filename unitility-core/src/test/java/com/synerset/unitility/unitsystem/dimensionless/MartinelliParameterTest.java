package com.synerset.unitility.unitsystem.dimensionless;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MartinelliParameterTest {

    @Test
    @DisplayName("should create Martinelli parameter")
    void shouldCreateMartinelliParameter() {
        // Given
        double expectedValue = 10.0;

        // When
        MartinelliParameter actual = MartinelliParameter.of(expectedValue);

        // Then
        assertThat(actual.getValue()).isEqualTo(expectedValue);
        assertThat(actual.getBaseValue()).isEqualTo(actual.getValue());
        assertThat(actual.toUnit(MartinelliParameterUnits.DIMENSIONLESS)).isEqualTo(actual.toBaseUnit());
    }
}
