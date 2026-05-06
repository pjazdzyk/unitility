package com.synerset.unitility.unitsystem.similaritynumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WeberNumberTest {

    @Test
    @DisplayName("should create Weber number")
    void shouldCreateWeberNumber() {
        // Given
        double expectedValue = 50.0;

        // When
        WeberNumber actual = WeberNumber.of(expectedValue);

        // Then
        assertThat(actual.getValue()).isEqualTo(expectedValue);
        assertThat(actual.getBaseValue()).isEqualTo(actual.getValue());
        assertThat(actual.toUnit(WeberNumberUnits.DIMENSIONLESS)).isEqualTo(actual.toBaseUnit());
    }
}
