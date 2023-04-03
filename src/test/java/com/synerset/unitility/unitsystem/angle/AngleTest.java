package com.synerset.unitility.unitsystem.angle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AngleTest {

    @Test
    @DisplayName("should properly convert degrees to radians and vice versa")
    void shouldProperlyConvertFromDegreeToRadian() {
        // Given
        Angle initialAngleInDegrees = Angle.ofDegrees(45);

        // When
        Angle actualInRadians = initialAngleInDegrees.toRadians();
        Angle actualInDegrees = actualInRadians.toDegrees();

        // Then
        Angle expectedRadian = Angle.of(Math.PI/4, AngleUnits.RADIANS);
        assertThat(actualInRadians).isEqualTo(expectedRadian);
        assertThat(actualInDegrees).isEqualTo(initialAngleInDegrees);
    }
}
