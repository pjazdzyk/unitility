package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LatitudeTest {

    @Test
    @DisplayName("should properly convert degrees to radians and vice versa")
    void shouldProperlyConvertFromDegreeToRadian() {
        // Given
        Latitude initialAngleInDegrees = Latitude.ofDegrees(45);

        // When
        Latitude actualInRadians = initialAngleInDegrees.toUnit(AngleUnits.RADIANS);
        double actualRadiansVal = initialAngleInDegrees.getInRadians();
        Latitude actualInDegrees = actualInRadians.toUnit(AngleUnits.DEGREES);
        double actualInDegreesVal = actualInRadians.getInDegrees();

        // Then
        Latitude expectedRadian = Latitude.of(Math.PI / 4, AngleUnits.RADIANS);
        assertThat(actualInRadians).isEqualTo(expectedRadian);
        assertThat(actualRadiansVal).isEqualTo(actualInRadians.getValue());
        assertThat(actualInDegrees.getValue()).isEqualTo(actualInDegreesVal);
    }

    @Test
    @DisplayName("should have DEGREES as base unit")
    void shouldHaveDegreesAsBaseUnit() {
        // Given
        AngleUnit expectedBaseUnit = AngleUnits.DEGREES;

        // When
        Latitude angleInRadians = Latitude.ofRadians(10);
        AngleUnit actualBaseUnit = angleInRadians.getUnitType().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Latitude expected = Latitude.ofDegrees(10.1);

        // When
        Latitude actual = expected.toRadians().toDegrees();
        double actualValue = expected.getInDegrees();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

    @Test
    @DisplayName("should output latitude in DMS format")
    void toDmsFormat_shouldOutputValidDMSFormat() {
        // Given
        Latitude latitude = Latitude.ofDegrees(52.23475638888889);

        // When
        String latInDms = latitude.toDMSFormat();
        String latInDmsVar = latitude.toDMSFormat("lat");
        String latInDmsVarDigits = latitude.toDMSFormat("lat", 1);

        // Then
        assertThat(latInDms).isEqualTo("52°14'5.123000000003799\"N");
        assertThat(latInDmsVar).isEqualTo("lat = 52°14'5.123000000003799\"N");
        assertThat(latInDmsVarDigits).isEqualTo("lat = 52°14'5.1\"N");
    }

}
