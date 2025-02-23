package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiameterTest {

    @Test
    @DisplayName("Diameter: should convert to m from mm and vice versa")
    void shouldProperlyConvertToMetersFromMillimeters() {
        // Given
        Diameter initialLengthInMillimeters = Diameter.ofMillimeters(1000.0);

        // When
        Diameter actualInMeters = initialLengthInMillimeters.toUnit(DistanceUnits.METER);
        double actualInMetersVal = actualInMeters.getInMeters();
        Diameter actualInMillimeters = actualInMeters.toUnit(DistanceUnits.MILLIMETER);
        double actualInMillimetersVal = actualInMeters.getInMillimeters();

        // Then
        Diameter expectedInMeters = Diameter.ofMeters(1.0);
        assertThat(actualInMeters.getValue()).isEqualTo(actualInMetersVal);
        assertThat(actualInMillimeters.getValue()).isEqualTo(actualInMillimetersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMillimeters).isEqualTo(initialLengthInMillimeters);
    }

    @Test
    @DisplayName("Diameter: should convert to m from cm and vice versa")
    void shouldProperlyConvertToMetersFromCentimeters() {
        // Given
        Diameter initialLengthInCentimeters = Diameter.ofCentimeters(100.0);

        // When
        Diameter actualInMeters = initialLengthInCentimeters.toBaseUnit();
        Diameter actualInCentimeters = actualInMeters.toUnit(DistanceUnits.CENTIMETER);
        double actualInCentimetersVal = actualInMeters.getInCentimeters();

        // Then
        Diameter expectedInMeters = Diameter.ofMeters(1.0);
        assertThat(actualInCentimeters.getValue()).isEqualTo(actualInCentimetersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInCentimeters).isEqualTo(initialLengthInCentimeters);
    }

    @Test
    @DisplayName("Diameter: should convert to m from km and vice versa")
    void shouldProperlyConvertToMetersFromKilometers() {
        // Given
        Diameter initialLengthInKilometers = Diameter.ofKilometers(1.0);

        // When
        Diameter actualInMeters = initialLengthInKilometers.toBaseUnit();
        Diameter actualInKilometers = actualInMeters.toUnit(DistanceUnits.KILOMETER);
        double actualInKilometersVal = actualInMeters.getInKilometers();

        // Then
        Diameter expectedInMeters = Diameter.ofMeters(1000.0);
        assertThat(actualInKilometers.getValue()).isEqualTo(actualInKilometersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInKilometers).isEqualTo(initialLengthInKilometers);
    }

    @Test
    @DisplayName("Diameter: should convert to m from mi and vice versa")
    void shouldProperlyConvertToMetersFromMile() {
        // Given
        Diameter initialLengthInMiles = Diameter.ofMiles(1.0);

        // When
        Diameter actualInMeters = initialLengthInMiles.toBaseUnit();
        Diameter actualInMiles = actualInMeters.toUnit(DistanceUnits.MILE);
        double actualInMilesVal = actualInMeters.getInMiles();

        // Then
        Diameter expectedInMeters = Diameter.ofMeters(1609.344);
        assertThat(actualInMiles.getValue()).isEqualTo(actualInMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMiles).isEqualTo(initialLengthInMiles);
    }

    @Test
    @DisplayName("Diameter: should convert to m from ft and vice versa")
    void shouldProperlyConvertToMetersFromFeet() {
        // Given
        Diameter initialLengthInFeet = Diameter.ofFeet(10.0);

        // When
        Diameter actualInMeters = initialLengthInFeet.toBaseUnit();
        Diameter actualInFeet = actualInMeters.toUnit(DistanceUnits.FEET);
        double actualInFeetVal = actualInMeters.getInFeet();

        // Then
        Diameter expectedInMeters = Diameter.ofMeters(3.048);
        assertThat(actualInFeet.getValue()).isEqualTo(actualInFeetVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInFeet).isEqualTo(initialLengthInFeet);
    }

    @Test
    @DisplayName("Diameter: should convert to m from ft and vice versa")
    void shouldProperlyConvertToMetersFromInch() {
        // Given
        Diameter initialLengthInInch = Diameter.ofInches(10.0);

        // When
        Diameter actualInMeters = initialLengthInInch.toBaseUnit();
        Diameter actualInInch = actualInMeters.toUnit(DistanceUnits.INCH);
        double actualInInchVal = actualInMeters.getInInches();

        // Then
        Diameter expectedInMeters = Diameter.ofMeters(0.254);
        assertThat(actualInInch.getValue()).isEqualTo(actualInInchVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInInch).isEqualTo(initialLengthInInch);
    }

    @Test
    @DisplayName("Diameter: should have m as base unit")
    void shouldHaveMetersAsBaseUnit() {
        // Given
        DistanceUnit expectedBaseUnit = DistanceUnits.METER;

        // When
        Diameter inMiles = Diameter.ofMiles(10);
        DistanceUnit actualBaseUnit = inMiles.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("Diameter: should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Diameter expected = Diameter.ofMeters(10.1);

        // When
        Diameter actual = expected.toMeter()
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

    @Test
    @DisplayName("Diameter: should convert to m from nmi and vice versa")
    void shouldProperlyConvertToMetersFromNauticalMile() {
        // Given
        Diameter initialLengthInMiles = Diameter.ofNauticalMiles(1.0);

        // When
        Diameter actualInMeters = initialLengthInMiles.toBaseUnit();
        Diameter actualInMiles = actualInMeters.toUnit(DistanceUnits.NAUTICAL_MILE);
        double actualInMilesVal = actualInMeters.getInNauticalMiles();

        // Then
        Diameter expectedInMeters = Diameter.ofMeters(1852);
        assertThat(actualInMiles.getValue()).isEqualTo(actualInMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMiles).isEqualTo(initialLengthInMiles);
    }

}
