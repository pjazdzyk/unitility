package com.synerset.unitility.unitsystem.geographic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GeoCoordinateTest {

    @Test
    @DisplayName("should output coordinates in DMS format")
    void toDMSFormat_shouldOutputInDegreeMinutesSecondsFormat() {
        // Given
        Latitude latitude = Latitude.ofDegrees(-52.23411);
        Longitude longitude = Longitude.ofDegrees(-21.56711);

        // When
        GeoCoordinate geoCoordinate = GeoCoordinate.of(latitude, longitude, "name");
        String actualDmsOutput = geoCoordinate.toDMSFormat();
        String actualDmsOutputVar = geoCoordinate.toDMSFormat("sea_quest");
        String actualDmsOutputVarTruncated = geoCoordinate.toDMSFormat("sea_quest", 3);

        // Then
        assertThat(actualDmsOutput).isEqualTo("52°14'2.796000000004142\"S, 21°34'1.595999999998412\"W");
        assertThat(actualDmsOutputVar).isEqualTo("sea_quest = 52°14'2.796000000004142\"S, 21°34'1.595999999998412\"W");
        assertThat(actualDmsOutputVarTruncated).isEqualTo("sea_quest = 52°14'2.796\"S, 21°34'1.596\"W");

        assertThat(geoCoordinate.name()).isEqualTo("name");
        assertThat(geoCoordinate.latitude()).isEqualTo(latitude);
        assertThat(geoCoordinate.longitude()).isEqualTo(longitude);
    }

    @Test
    @DisplayName("should output coordinates in decimal degrees format")
    void toDecimalDegrees_shouldOutputInDecimalDegreesFormat() {
        // Given
        Latitude latitude = Latitude.ofDegrees(-52.2341122);
        Longitude longitude = Longitude.ofDegrees(-21.5671122);

        // When
        GeoCoordinate geoCoordinate = GeoCoordinate.of(latitude, longitude, "name");
        String actualDegreesOutput = geoCoordinate.toDecimalDegrees();
        String actualDegreesOutputVar = geoCoordinate.toDecimalDegrees("sea_quest");
        String actualDegreesOutputVarTruncated = geoCoordinate.toDecimalDegrees("sea_quest", 3);

        // Then
        assertThat(actualDegreesOutput).isEqualTo("-52.2341122, -21.5671122");
        assertThat(actualDegreesOutputVar).isEqualTo("sea_quest = -52.2341122, -21.5671122");
        assertThat(actualDegreesOutputVarTruncated).isEqualTo("sea_quest = -52.234, -21.567");
    }

    @Test
    @DisplayName("should output cin engineering format")
    void toEngineeringFormat_shouldOutputInEngineeringFormat() {
        // Given
        Latitude latitude = Latitude.ofDegrees(-52.2341122);
        Longitude longitude = Longitude.ofDegrees(-21.5671122);

        // When
        GeoCoordinate geoCoordinate = GeoCoordinate.of(latitude, longitude);
        String actualEngOutput = geoCoordinate.toEngineeringFormat();
        String actualEngOutputVar = geoCoordinate.toEngineeringFormat("sea_quest");
        String actualEngOutputVarTruncated = geoCoordinate.toEngineeringFormat("sea_quest", 3);

        // Then
        assertThat(actualEngOutput).isEqualTo("-52.2341122 [°], -21.5671122 [°]");
        assertThat(actualEngOutputVar).isEqualTo("sea_quest = -52.2341122 [°], -21.5671122 [°]");
        assertThat(actualEngOutputVarTruncated).isEqualTo("sea_quest = -52.234 [°], -21.567 [°]");
    }

    @Test
    @DisplayName("should be equals for defined precision")
    void equalsWithPrecision_shouldBeEqualForPrecision() {
        // Given
        Latitude latitude1 = Latitude.ofDegrees(-52.236);
        Longitude longitude1 = Longitude.ofDegrees(-21.569);
        GeoCoordinate geoCoordinate1 = GeoCoordinate.of(latitude1, longitude1);

        Latitude latitude2 = Latitude.ofDegrees(-52.234);
        Longitude longitude2 = Longitude.ofDegrees(-21.567);
        GeoCoordinate geoCoordinate2 = GeoCoordinate.of(latitude2, longitude2);

        // When
        boolean actualResult = geoCoordinate1.equalsWithPrecision(geoCoordinate2, 0.002);

        // Then
        assertThat(actualResult).isTrue();

    }
}