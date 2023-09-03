package com.synerset.unitility.unitsystem.dimensionless;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReynoldsNumberTest {

    @Test
    @DisplayName("should create Reynolds number")
    void shouldCreateReynoldsNumber() {
        // Given
        double expectedValue = 10.0;

        // When
        ReynoldsNumber actualReynoldsNumber = ReynoldsNumber.of(expectedValue);

        // Then
        ReynoldsNumber expectedReynoldsNumber = ReynoldsNumber.of(10.0);
        assertThat(actualReynoldsNumber.getValue()).isEqualTo(expectedValue);
        assertThat(actualReynoldsNumber.getBaseValue()).isEqualTo(actualReynoldsNumber.getValue());
        assertThat(actualReynoldsNumber.toUnit(ReynoldsNumberUnits.DIMENSIONLESS)).isEqualTo(actualReynoldsNumber.toBaseUnit());
        assertThat(actualReynoldsNumber).isEqualTo(expectedReynoldsNumber);
    }

}
