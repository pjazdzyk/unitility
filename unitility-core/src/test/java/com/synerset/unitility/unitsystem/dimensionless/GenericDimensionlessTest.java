package com.synerset.unitility.unitsystem.dimensionless;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GenericDimensionlessTest {

    @Test
    @DisplayName("should create Generic dimensionless quantity")
    void shouldCreatBypassFactor() {
        // Given
        double expectedValue = 0.2;

        // When
        BypassFactor actualBypassFactor = BypassFactor.of(expectedValue);

        // Then
        BypassFactor genericDimensionless = BypassFactor.of(0.2);
        assertThat(actualBypassFactor.getValue()).isEqualTo(expectedValue);
        assertThat(actualBypassFactor.toUnit(BypassFactorUnits.DIMENSIONLESS)).isEqualTo(actualBypassFactor.toBaseUnit());
        assertThat(actualBypassFactor.getBaseValue()).isEqualTo(actualBypassFactor.getValue());
        assertThat(actualBypassFactor).isEqualTo(genericDimensionless);
    }

}
