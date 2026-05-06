package com.synerset.unitility.unitsystem.dimensionless;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JakobNumberTest {

    @Test
    @DisplayName("should create Jakob number")
    void shouldCreateJakobNumber() {
        // Given
        double expectedValue = 0.05;

        // When
        JakobNumber actual = JakobNumber.of(expectedValue);

        // Then
        assertThat(actual.getValue()).isEqualTo(expectedValue);
        assertThat(actual.getBaseValue()).isEqualTo(actual.getValue());
        assertThat(actual.toUnit(JakobNumberUnits.DIMENSIONLESS)).isEqualTo(actual.toBaseUnit());
    }
}
