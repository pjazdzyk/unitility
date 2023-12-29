package com.synerset.unitility.unitsystem.dimensionless;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BypassFactorTest {

    @Test
    @DisplayName("should create By-pass factor")
    void shouldCreatBypassFactor() {
        // Given
        double expectedValue = 0.2;

        // When
        BypassFactor actualBypassFactor = BypassFactor.of(expectedValue);

        // Then
        BypassFactor expectedBypassFactor = BypassFactor.of(0.2);
        assertThat(actualBypassFactor.getValue()).isEqualTo(expectedValue);
        assertThat(actualBypassFactor.toUnit(BypassFactorUnits.DIMENSIONLESS)).isEqualTo(actualBypassFactor.toBaseUnit());
        assertThat(actualBypassFactor.getBaseValue()).isEqualTo(actualBypassFactor.getValue());
        assertThat(actualBypassFactor).isEqualTo(expectedBypassFactor);
    }

}
