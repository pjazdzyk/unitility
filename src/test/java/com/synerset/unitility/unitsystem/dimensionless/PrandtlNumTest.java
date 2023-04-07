package com.synerset.unitility.unitsystem.dimensionless;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PrandtlNumTest {

    @Test
    @DisplayName("should create Prandtl number")
    void shouldCreatePrandtlNumber(){
        // Given
        double expectedValue = 10.0;

        // When
        PrandtlNum actualPrandtlNum = PrandtlNum.of(expectedValue);

        // Then
        PrandtlNum expectedPrandtlNum = PrandtlNum.of(10.0);
        assertThat(actualPrandtlNum.getValue()).isEqualTo(expectedValue);
        assertThat(actualPrandtlNum).isEqualTo(expectedPrandtlNum);
    }

}
