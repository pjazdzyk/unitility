package com.synerset.unitility.unitsystem.distance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DistanceTest {

    @Test
    @DisplayName("should convert to m from mm and vice versa")
    void shouldProperlyConvertToMetersFromMillimeters() {
        // Given
        Distance initialDistanceInMillimeters = Distance.ofMillimeters(1000.0);

        // When
        Distance actualInMeters = initialDistanceInMillimeters.toBaseUnit();
        Distance actualInMillimeters = actualInMeters.toUnit(DistanceUnits.MILLIMETER);

        // Then
        Distance expectedInMeters = Distance.ofMeters(1.0);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMillimeters).isEqualTo(initialDistanceInMillimeters);
    }

    @Test
    @DisplayName("should convert to m from cm and vice versa")
    void shouldProperlyConvertToMetersFromCentimeters() {
        // Given
        Distance initialDistanceInCentimeters = Distance.ofCentimeters(100.0);

        // When
        Distance actualInMeters = initialDistanceInCentimeters.toBaseUnit();
        Distance actualInCentimeters = actualInMeters.toUnit(DistanceUnits.CENTIMETER);

        // Then
        Distance expectedInMeters = Distance.ofMeters(1.0);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInCentimeters).isEqualTo(initialDistanceInCentimeters);
    }

    @Test
    @DisplayName("should convert to m from km and vice versa")
    void shouldProperlyConvertToMetersFromKilometers() {
        // Given
        Distance initialDistanceInKilometers = Distance.ofKilometers(1.0);

        // When
        Distance actualInMeters = initialDistanceInKilometers.toBaseUnit();
        Distance actualInKilometers = actualInMeters.toUnit(DistanceUnits.KILOMETER);

        // Then
        Distance expectedInMeters = Distance.ofMeters(1000.0);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInKilometers).isEqualTo(initialDistanceInKilometers);
    }

    @Test
    @DisplayName("should convert to m from mi and vice versa")
    void shouldProperlyConvertToMetersFromMile() {
        // Given
        Distance initialDistanceInMiles = Distance.ofMiles(1.0);

        // When
        Distance actualInMeters = initialDistanceInMiles.toBaseUnit();
        Distance actualInMiles = actualInMeters.toUnit(DistanceUnits.MILE);

        // Then
        Distance expectedInMeters = Distance.ofMeters(1609.344);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMiles).isEqualTo(initialDistanceInMiles);
    }

    @Test
    @DisplayName("should convert to m from ft and vice versa")
    void shouldProperlyConvertToMetersFromFeet() {
        // Given
        Distance initialDistanceInFeet = Distance.ofFeet(10.0);

        // When
        Distance actualInMeters = initialDistanceInFeet.toBaseUnit();
        Distance actualInFeet = actualInMeters.toUnit(DistanceUnits.FEET);

        // Then
        Distance expectedInMeters = Distance.ofMeters(3.048);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInFeet).isEqualTo(initialDistanceInFeet);
    }

    @Test
    @DisplayName("should convert to m from ft and vice versa")
    void shouldProperlyConvertToMetersFromInch() {
        // Given
        Distance initialDistanceInInch = Distance.ofInches(10.0);

        // When
        Distance actualInMeters = initialDistanceInInch.toBaseUnit();
        Distance actualInInch = actualInMeters.toUnit(DistanceUnits.INCH);

        // Then
        Distance expectedInMeters = Distance.ofMeters(0.254);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInInch).isEqualTo(initialDistanceInInch);
    }

}
