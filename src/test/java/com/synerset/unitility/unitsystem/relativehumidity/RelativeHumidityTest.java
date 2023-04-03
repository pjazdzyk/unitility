package com.synerset.unitility.unitsystem.relativehumidity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RelativeHumidityTest {
    @Test
    @DisplayName("should convert % to decimal and vice versa")
    void shouldProperlyConvertPascalToPsi() {
        // Given
        RelativeHumidity initialHumidity = RelativeHumidity.ofPercentage(50.5);

        // When
        RelativeHumidity actualInDecimal = initialHumidity.toDecimal();
        RelativeHumidity actualInPercent = actualInDecimal.toPercent();

        // Then
        RelativeHumidity expectedInDecimal = RelativeHumidity.ofDecimal(0.505);
        assertThat(actualInDecimal).isEqualTo(expectedInDecimal);
        assertThat(actualInPercent).isEqualTo(initialHumidity);
    }
}