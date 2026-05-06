package com.synerset.unitility.unitsystem.dimensionless;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConfinementNumberTest {

    @Test
    @DisplayName("should create Confinement number")
    void shouldCreateConfinementNumber() {
        // Given
        double expectedValue = 0.5;

        // When
        ConfinementNumber actual = ConfinementNumber.of(expectedValue);

        // Then
        assertThat(actual.getValue()).isEqualTo(expectedValue);
        assertThat(actual.getBaseValue()).isEqualTo(actual.getValue());
        assertThat(actual.toUnit(ConfinementNumberUnits.DIMENSIONLESS)).isEqualTo(actual.toBaseUnit());
    }
}
