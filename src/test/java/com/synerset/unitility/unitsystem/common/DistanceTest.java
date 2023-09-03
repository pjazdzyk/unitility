package com.synerset.unitility.unitsystem.common;

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
        Distance actualInMeters = initialDistanceInMillimeters.toUnit(DistanceUnits.METER);
        double actualInMetersVal = actualInMeters.getInMeters();
        Distance actualInMillimeters = actualInMeters.toUnit(DistanceUnits.MILLIMETER);
        double actualInMillimetersVal = actualInMeters.getInMillimeters();

        // Then
        Distance expectedInMeters = Distance.ofMeters(1.0);
        assertThat(actualInMeters.getValue()).isEqualTo(actualInMetersVal);
        assertThat(actualInMillimeters.getValue()).isEqualTo(actualInMillimetersVal);
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
        double actualInCentimetersVal = actualInMeters.getInCentimeters();

        // Then
        Distance expectedInMeters = Distance.ofMeters(1.0);
        assertThat(actualInCentimeters.getValue()).isEqualTo(actualInCentimetersVal);
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
        double actualInKilometersVal = actualInMeters.getInKilometers();

        // Then
        Distance expectedInMeters = Distance.ofMeters(1000.0);
        assertThat(actualInKilometers.getValue()).isEqualTo(actualInKilometersVal);
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
        double actualInMilesVal = actualInMeters.getInMiles();

        // Then
        Distance expectedInMeters = Distance.ofMeters(1609.344);
        assertThat(actualInMiles.getValue()).isEqualTo(actualInMilesVal);
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
        double actualInFeetVal = actualInMeters.getInFeet();

        // Then
        Distance expectedInMeters = Distance.ofMeters(3.048);
        assertThat(actualInFeet.getValue()).isEqualTo(actualInFeetVal);
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
        double actualInInchVal = actualInMeters.getInInches();

        // Then
        Distance expectedInMeters = Distance.ofMeters(0.254);
        assertThat(actualInInch.getValue()).isEqualTo(actualInInchVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInInch).isEqualTo(initialDistanceInInch);
    }

    @Test
    @DisplayName("should have m as base unit")
    void shouldHaveMetersAsBaseUnit() {
        // Given
        DistanceUnits expectedBaseUnit = DistanceUnits.METER;

        // When
        Distance DistanceInMiles = Distance.ofMiles(10);
        DistanceUnits actualBaseUnit = DistanceInMiles.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Distance expected = Distance.ofMeters(10.1);

        // When
        Distance actual = expected.toMeter()
                .toCentimeter()
                .toMillimeter()
                .toKilometer()
                .toMile()
                .toFeet()
                .toInch()
                .toMeter();

        double actualValue = expected.getInMeters();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}
