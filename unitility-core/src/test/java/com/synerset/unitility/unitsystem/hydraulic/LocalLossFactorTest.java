package com.synerset.unitility.unitsystem.hydraulic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LocalLossFactorTest {

    @Test
    @DisplayName("should create By-pass factor")
    void shouldCreateLocalLossFactor() {
        // Given
        double expectedValue = 0.2;

        // When
        LocalLossFactor actulaLocalLossFactor = LocalLossFactor.of(expectedValue);

        // Then
        LocalLossFactor expectedBypassFactor = LocalLossFactor.of(0.2);
        assertThat(actulaLocalLossFactor.getValue()).isEqualTo(expectedValue);
        assertThat(actulaLocalLossFactor.toUnit(LocalLossFactorUnits.DIMENSIONLESS)).isEqualTo(actulaLocalLossFactor.toBaseUnit());
        assertThat(actulaLocalLossFactor.getBaseValue()).isEqualTo(actulaLocalLossFactor.getValue());
        assertThat(actulaLocalLossFactor).isEqualTo(expectedBypassFactor);
        assertThat(LocalLossFactor.of(0)).isEqualTo(LocalLossFactor.LLF_PHYSICAL_MIN);
    }

}
