package com.synerset.unitility.unitsystem.dimensionless;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReynoldsNumTest {

    @Test
    @DisplayName("should create Reynolds number")
    void shouldCreateReynoldsNumber(){
        // Given
        double expectedValue = 10.0;

        // When
        ReynoldsNum actualReynoldsNum = ReynoldsNum.of(expectedValue);

        // Then
        ReynoldsNum expectedReynoldsNum = ReynoldsNum.of(10.0);
        assertThat(actualReynoldsNum.getValue()).isEqualTo(expectedValue);
        assertThat(actualReynoldsNum).isEqualTo(expectedReynoldsNum);
    }

}
