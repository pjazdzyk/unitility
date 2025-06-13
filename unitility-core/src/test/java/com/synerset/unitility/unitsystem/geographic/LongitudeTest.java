package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LongitudeTest {

    @Test
    @DisplayName("should properly convert degrees to radians and vice versa")
    void shouldProperlyConvertFromDegreeToRadian() {
        // Given
        Longitude initialAngleInDegrees = Longitude.ofDegrees(45);

        // When
        Longitude actualInRadians = initialAngleInDegrees.toUnit(AngleUnits.RADIANS);
        double actualRadiansVal = initialAngleInDegrees.getInRadians();
        Longitude actualInDegrees = actualInRadians.toUnit(AngleUnits.DEGREES);
        double actualInDegreesVal = actualInRadians.getInDegrees();

        // Then
        Longitude expectedRadian = Longitude.of(Math.PI / 4, AngleUnits.RADIANS);
        assertThat(actualInRadians).isEqualTo(expectedRadian);
        assertThat(actualRadiansVal).isEqualTo(actualInRadians.getValue());
        assertThat(actualInDegrees.getValue()).isEqualTo(actualInDegreesVal);
    }

    @Test
    @DisplayName("should have DEGREES as base unit")
    void shouldHaveDegreesAsBaseUnit() {
        // Given
        AngleUnit expectedBaseUnit = AngleUnits.RADIANS;

        // When
        Longitude angleInRadians = Longitude.ofRadians(10);
        AngleUnit actualBaseUnit = angleInRadians.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Longitude expected = Longitude.ofDegrees(10.1);

        // When
        Longitude actual = expected.toRadians().toDegrees();
        double actualValue = expected.getInDegrees();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

    @Test
    @DisplayName("should output longitude in DMS format")
    void toDmsFormat_shouldOutputValidDMSFormat() {
        // Given
        Longitude longitude = Longitude.ofDegrees(-21.06777388888889);

        // When
        String lonInDms = longitude.toDMSFormat();
        String lonInDmsVar = longitude.toDMSFormat("lat");
        String lonInDmsVarDigits = longitude.toDMSFormat("lat", 1);

        // Then
        assertThat(lonInDms).isEqualTo("21°4'3.986000000018066\"W");
        assertThat(lonInDmsVar).isEqualTo("lat = 21°4'3.986000000018066\"W");
        assertThat(lonInDmsVarDigits).isEqualTo("lat = 21°4'4\"W");
    }

}
