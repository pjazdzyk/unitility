package com.synerset.unitsystem.length;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LengthTest {

    @Test
    @DisplayName("should convert to m from mm and vice versa")
    public void shouldProperlyConvertToMetersFromMillimeters() {
        // Given
        Length initialLengthInMillimeters = Length.ofMillimeters(1000.0);

        // When
        Length actualInMeters = initialLengthInMillimeters.toBaseUnit();
        Length actualInMillimeters = actualInMeters.toUnit(LengthUnits.MILLIMETER);

        // Then
        Length expectedInMeters = Length.ofMeters(1.0);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMillimeters).isEqualTo(initialLengthInMillimeters);
    }

    @Test
    @DisplayName("should convert to m from cm and vice versa")
    public void shouldProperlyConvertToMetersFromCentimeters() {
        // Given
        Length initialLengthInCentimeters = Length.ofCentimeters(100.0);

        // When
        Length actualInMeters = initialLengthInCentimeters.toBaseUnit();
        Length actualInCentimeters = actualInMeters.toUnit(LengthUnits.CENTIMETER);

        // Then
        Length expectedInMeters = Length.ofMeters(1.0);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInCentimeters).isEqualTo(initialLengthInCentimeters);
    }

    @Test
    @DisplayName("should convert to m from km and vice versa")
    public void shouldProperlyConvertToMetersFromKilometers() {
        // Given
        Length initialLengthInKilometers = Length.ofKilometers(1.0);

        // When
        Length actualInMeters = initialLengthInKilometers.toBaseUnit();
        Length actualInKilometers = actualInMeters.toUnit(LengthUnits.KILOMETER);

        // Then
        Length expectedInMeters = Length.ofMeters(1000.0);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInKilometers).isEqualTo(initialLengthInKilometers);
    }

    @Test
    @DisplayName("should convert to m from mi and vice versa")
    public void shouldProperlyConvertToMetersFromMile() {
        // Given
        Length initialLengthInMiles = Length.ofMiles(1.0);

        // When
        Length actualInMeters = initialLengthInMiles.toBaseUnit();
        Length actualInMiles = actualInMeters.toUnit(LengthUnits.MILE);

        // Then
        Length expectedInMeters = Length.ofMeters(1609.344);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMiles).isEqualTo(initialLengthInMiles);
    }

    @Test
    @DisplayName("should convert to m from ft and vice versa")
    public void shouldProperlyConvertToMetersFromFeet() {
        // Given
        Length initialLengthInFeet = Length.ofFeet(10.0);

        // When
        Length actualInMeters = initialLengthInFeet.toBaseUnit();
        Length actualInFeet = actualInMeters.toUnit(LengthUnits.FEET);

        // Then
        Length expectedInMeters = Length.ofMeters(3.048);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInFeet).isEqualTo(initialLengthInFeet);
    }

    @Test
    @DisplayName("should convert to m from ft and vice versa")
    public void shouldProperlyConvertToMetersFromInch() {
        // Given
        Length initialLengthInInch = Length.ofInches(10.0);

        // When
        Length actualInMeters = initialLengthInInch.toBaseUnit();
        Length actualInInch = actualInMeters.toUnit(LengthUnits.INCH);

        // Then
        Length expectedInMeters = Length.ofMeters(0.254);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInInch).isEqualTo(initialLengthInInch);
    }



}
