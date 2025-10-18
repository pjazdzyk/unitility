package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LongitudeTest {

    @Test
    @DisplayName("Longitude: should properly convert degrees to radians and vice versa")
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
    @DisplayName("Longitude: should have DEGREES as base unit")
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
    @DisplayName("Longitude: should return valid result from to() and getIn() methods")
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
    @DisplayName("Longitude: should output longitude in DMS format")
    void toDmsFormat_shouldOutputValidDMSSFormat() {
        // Given
        Longitude longitude = Longitude.ofDegrees(-21.06777388888889);

        // When
        String lonInDms = longitude.toDMSsFormat();
        String lonInDmsVar = longitude.toDMSsFormat("lat");
        String lonInDmsVarDigits = longitude.toDMSsFormat("lat", 1);

        // Then
        assertThat(lonInDms).isEqualTo("021°04'03.99\"W");
        assertThat(lonInDmsVar).isEqualTo("lat = 021°04'03.99\"W");
        assertThat(lonInDmsVarDigits).isEqualTo("lat = 021°04'04\"W");
    }

    @Test
    @DisplayName("Longitude: should create instance from DMS input")
    void shouldCreateNewInstanceFromDMSFormat(){
        // Given
        String longitudeAsStringE = "002°14'5.1\"E";
        String longitudeAsStringW = "002°14'5.10000000000000001\"W";

        // When
        Longitude longitudeE = Longitude.ofDMSFormat(longitudeAsStringE);
        Longitude longitudeW = Longitude.ofDMSFormat(longitudeAsStringW);

        // Then
        assertThat(longitudeE.getInDegrees()).isEqualTo(2.23475);
        assertThat(longitudeW.getInDegrees()).isEqualTo(-2.23475);

    }

    @Test
    @DisplayName("Longitude: should create instance from minutes degrees seconds")
    void shouldCreateNewInstanceFromDegreesMinutesSeconds(){
        // When
        Longitude longitudeE = Longitude.ofDegMinSec(2,14,5.1);
        Longitude longitudeW = Longitude.ofDegMinSec(-52,14,5.10000000000);

        // Then
        assertThat(longitudeE.getInDegrees()).isEqualTo(2.23475);
        assertThat(longitudeW.getInDegrees()).isEqualTo(-52.23475);

        assertThatThrownBy(() -> Longitude.ofDMSFormat("90NN")).isInstanceOf(UnitSystemArgumentException.class);
        assertThatThrownBy(() -> Longitude.ofDMSFormat(null)).isInstanceOf(UnitSystemArgumentException.class);


    }

}
