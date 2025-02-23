package com.synerset.unitility.unitsystem.dimensionless;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrictionFactorTest {

    @Test
    @DisplayName("should create friction factor")
    void shouldCreateFrictionFactor() {
        // Given
        double expectedValue = 10.0;

        // When
        FrictionFactor actualFrictionFactor = FrictionFactor.of(expectedValue);

        // Then
        FrictionFactor expectedFrictionFactor = FrictionFactor.of(10.0);
        assertThat(actualFrictionFactor.getValue()).isEqualTo(expectedValue);
        assertThat(actualFrictionFactor.getBaseValue()).isEqualTo(actualFrictionFactor.getValue());
        assertThat(actualFrictionFactor.toUnit(FrictionFactorUnits.DIMENSIONLESS)).isEqualTo(actualFrictionFactor.toBaseUnit());
        assertThat(actualFrictionFactor).isEqualTo(expectedFrictionFactor);
    }

}
