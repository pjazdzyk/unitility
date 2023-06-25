package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.Unit;
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
        Angle actualInDegrees = actualInRadians.toBaseUnit();

        // Then
        Angle expectedRadian = Angle.of(Math.PI/4, AngleUnits.RADIANS);
        assertThat(actualInRadians).isEqualTo(expectedRadian);
        assertThat(actualInDegrees).isEqualTo(initialAngleInDegrees);
    }

    @Test
    @DisplayName("should have DEGREES as base unit")
    void shouldHaveDegreesAsBaseUnit() {
        // Given
        AngleUnits expectedBaseUnit = AngleUnits.DEGREES;

        // When
        Angle angleInRadians = Angle.ofRadians(10);
        Unit<Angle> actualBaseUnit = angleInRadians.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

}
