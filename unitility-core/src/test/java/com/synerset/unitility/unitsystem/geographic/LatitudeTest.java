package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LatitudeTest {

    @Test
    @DisplayName("Latitude: should properly convert degrees to radians and vice versa")
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
    @DisplayName("Latitude: should have DEGREES as base unit")
    void shouldHaveDegreesAsBaseUnit() {
        // Given
        AngleUnit expectedBaseUnit = AngleUnits.RADIANS;

        // When
        Latitude angleInRadians = Latitude.ofRadians(10);
        AngleUnit actualBaseUnit = angleInRadians.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("Latitude: should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Latitude expected = Latitude.ofDegrees(10.1);

        // When
        Latitude actual = expected.toRadians().toDegrees();
        double actualValue = expected.getInDegrees();

        // Then
        assertThat(actual.getValue()).isEqualTo(expected.getValue(), withPrecision(1E-11));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

    @Test
    @DisplayName("Latitude: should output latitude in DMS format")
    void toDmsFormat_shouldOutputValidDMSSFormat() {
        // Given
        Latitude latitude = Latitude.ofDegrees(52.23475638888889);

        // When
        String latInDms = latitude.toDMSsFormat();
        String latInDmsVar = latitude.toDMSsFormat("lat");
        String latInDmsVarDigits = latitude.toDMSsFormat("lat", 0.001);

        // Then
        assertThat(latInDms).isEqualTo("52°14'05.12\"N");
        assertThat(latInDmsVar).isEqualTo("lat = 52°14'05.12\"N");
        assertThat(latInDmsVarDigits).isEqualTo("lat = 52°14'05.123\"N");
    }

    @Test
    @DisplayName("Latitude: should create instance from DMS input")
    void shouldCreateNewInstanceFromDMSFormat() {
        // Given
        String latitudeAsStringN = "02°14'5.1\"N";
        String latitudeAsStringS = "52°14'5.1\"S";
        // When
        Latitude latitudeN = Latitude.ofDMSFormat(latitudeAsStringN);
        Latitude latitudeS = Latitude.ofDMSFormat(latitudeAsStringS);

        // Then
        assertThat(latitudeN.getInDegrees()).isEqualTo(2.23475);
        assertThat(latitudeS.getInDegrees()).isEqualTo(-52.23475);

        assertThatThrownBy(() -> Latitude.ofDMSFormat("90NN")).isInstanceOf(UnitSystemArgumentException.class);
        assertThatThrownBy(() -> Latitude.ofDMSFormat(null)).isInstanceOf(UnitSystemArgumentException.class);

    }

    @Test
    @DisplayName("Latitude: should create instance from minutes degrees seconds")
    void shouldCreateNewInstanceFromDegreesMinutesSeconds() {
        // When
        Latitude latitudeN = Latitude.ofDegMinSec(2, 14, 5.1);
        Latitude latitudeS = Latitude.ofDegMinSec(-52, 14, 5.1);

        // Then
        assertThat(latitudeN.getInDegrees()).isEqualTo(2.23475);
        assertThat(latitudeS.getInDegrees()).isEqualTo(-52.23475);

    }

}