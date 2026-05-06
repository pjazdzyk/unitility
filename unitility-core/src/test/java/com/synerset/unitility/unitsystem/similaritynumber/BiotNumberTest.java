package com.synerset.unitility.unitsystem.similaritynumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BiotNumberTest {

    @Test
    @DisplayName("should create Biot number")
    void shouldCreateBiotNumber() {
        // Given
        double expectedValue = 0.1;

        // When
        BiotNumber actual = BiotNumber.of(expectedValue);

        // Then
        assertThat(actual.getValue()).isEqualTo(expectedValue);
        assertThat(actual.getBaseValue()).isEqualTo(actual.getValue());
        assertThat(actual.toUnit(BiotNumberUnits.DIMENSIONLESS)).isEqualTo(actual.toBaseUnit());
    }
}
