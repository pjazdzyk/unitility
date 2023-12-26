package com.synerset.unitility.unitsystem.basic.dimensionless;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PrandtlNumberTest {

    @Test
    @DisplayName("should create Prandtl number")
    void shouldCreatePrandtlNumber() {
        // Given
        double expectedValue = 10.0;

        // When
        PrandtlNumber actualPrandtlNum = PrandtlNumber.of(expectedValue);

        // Then
        PrandtlNumber expectedPrandtlNum = PrandtlNumber.of(10.0);
        assertThat(actualPrandtlNum.getValue()).isEqualTo(expectedValue);
        assertThat(actualPrandtlNum.getBaseValue()).isEqualTo(actualPrandtlNum.getValue());
        assertThat(actualPrandtlNum.toUnit(PrandtlNumberUnits.DIMENSIONLESS)).isEqualTo(actualPrandtlNum.toBaseUnit());
        assertThat(actualPrandtlNum).isEqualTo(expectedPrandtlNum);
    }

}
