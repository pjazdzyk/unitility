package com.synerset.unitility.unitsystem.dimensionless;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoilingNumberTest {

    @Test
    @DisplayName("should create Boiling number")
    void shouldCreateBoilingNumber() {
        // Given
        double expectedValue = 1E-4;

        // When
        BoilingNumber actual = BoilingNumber.of(expectedValue);

        // Then
        assertThat(actual.getValue()).isEqualTo(expectedValue);
        assertThat(actual.getBaseValue()).isEqualTo(actual.getValue());
        assertThat(actual.toUnit(BoilingNumberUnits.DIMENSIONLESS)).isEqualTo(actual.toBaseUnit());
    }
}
