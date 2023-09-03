package com.synerset.unitility.unitsystem.common;

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
        Angle actualInRadians = initialAngleInDegrees.toUnit(AngleUnits.RADIANS);
        double actualRadiansVal = initialAngleInDegrees.getInRadians();
        Angle actualInDegrees = actualInRadians.toUnit(AngleUnits.DEGREES);
        double actualInDegreesVal = actualInRadians.getInDegrees();

        // Then
        Angle expectedRadian = Angle.of(Math.PI / 4, AngleUnits.RADIANS);
        assertThat(actualInRadians).isEqualTo(expectedRadian);
        assertThat(actualRadiansVal).isEqualTo(actualInRadians.getValue());
        assertThat(actualInDegrees.getValue()).isEqualTo(actualInDegreesVal);
    }

    @Test
    @DisplayName("should have DEGREES as base unit")
    void shouldHaveDegreesAsBaseUnit() {
        // Given
        AngleUnits expectedBaseUnit = AngleUnits.DEGREES;

        // When
        Angle angleInRadians = Angle.ofRadians(10);
        AngleUnits actualBaseUnit = angleInRadians.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Angle expected = Angle.ofDegrees(10.1);

        // When
        Angle actual = expected.toRadians().toDegrees();
        double actualValue = expected.getInDegrees();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}
