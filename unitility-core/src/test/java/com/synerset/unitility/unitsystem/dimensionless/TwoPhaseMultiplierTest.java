package com.synerset.unitility.unitsystem.dimensionless;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TwoPhaseMultiplierTest {

    @Test
    @DisplayName("should create two-phase multiplier")
    void shouldCreateTwoPhaseMultiplier() {
        // Given
        double expectedValue = 2.5;

        // When
        TwoPhaseMultiplier actual = TwoPhaseMultiplier.of(expectedValue);

        // Then
        assertThat(actual.getValue()).isEqualTo(expectedValue);
        assertThat(actual.getBaseValue()).isEqualTo(actual.getValue());
        assertThat(actual.toUnit(TwoPhaseMultiplierUnits.DIMENSIONLESS)).isEqualTo(actual.toBaseUnit());
    }
}
