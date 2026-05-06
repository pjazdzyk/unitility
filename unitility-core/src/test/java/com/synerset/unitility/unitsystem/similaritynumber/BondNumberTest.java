package com.synerset.unitility.unitsystem.similaritynumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BondNumberTest {

    @Test
    @DisplayName("should create Bond number")
    void shouldCreateBondNumber() {
        // Given
        double expectedValue = 100.0;

        // When
        BondNumber actual = BondNumber.of(expectedValue);

        // Then
        assertThat(actual.getValue()).isEqualTo(expectedValue);
        assertThat(actual.getBaseValue()).isEqualTo(actual.getValue());
        assertThat(actual.toUnit(BondNumberUnits.DIMENSIONLESS)).isEqualTo(actual.toBaseUnit());
    }
}
