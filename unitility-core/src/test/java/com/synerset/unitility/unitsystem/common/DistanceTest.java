package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DistanceTest {

    @Test
    @DisplayName("Distance: should convert to m from mm and vice versa")
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
    @DisplayName("Distance: should convert to m from cm and vice versa")
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
    @DisplayName("Distance: should convert to m from km and vice versa")
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
    @DisplayName("Distance: should convert to m from mi and vice versa")
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
    @DisplayName("Distance: should convert to m from ft and vice versa")
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
    @DisplayName("Distance: should convert to m from ft and vice versa")
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
    @DisplayName("Distance: should have m as base unit")
    void shouldHaveMetersAsBaseUnit() {
        // Given
        DistanceUnit expectedBaseUnit = DistanceUnits.METER;

        // When
        Distance distanceInMiles = Distance.ofMiles(10);
        DistanceUnit actualBaseUnit = distanceInMiles.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("Distance: should return valid result from to() and getIn() methods")
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

    @Test
    @DisplayName("Distance: should convert to m from nmi and vice versa")
    void shouldProperlyConvertToMetersFromNauticalMile() {
        // Given
        Distance initialDistanceInMiles = Distance.ofNauticalMiles(1.0);

        // When
        Distance actualInMeters = initialDistanceInMiles.toBaseUnit();
        Distance actualInMiles = actualInMeters.toUnit(DistanceUnits.NAUTICAL_MILE);
        double actualInMilesVal = actualInMeters.getInNauticalMiles();

        // Then
        Distance expectedInMeters = Distance.ofMeters(1852);
        assertThat(actualInMiles.getValue()).isEqualTo(actualInMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMiles).isEqualTo(initialDistanceInMiles);
    }

    @Test
    @DisplayName("Distance: should convert to m from yd and vice versa")
    void shouldProperlyConvertToMetersFromYard() {
        // Given
        Distance initialDistanceInYards = Distance.ofYards(10.0);

        // When
        Distance actualInMeters = initialDistanceInYards.toBaseUnit();
        Distance actualInYards = actualInMeters.toUnit(DistanceUnits.YARD);
        double actualInYardsVal = actualInMeters.getInYards();

        // Then
        Distance expectedInMeters = Distance.ofMeters(9.144); // 10 * 0.9144
        assertThat(actualInYards.getValue()).isEqualTo(actualInYardsVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInYards).isEqualTo(initialDistanceInYards);
    }

    @Test
    @DisplayName("Distance: should convert to m from dam and vice versa")
    void shouldProperlyConvertToMetersFromDecameter() {
        // Given
        Distance initialDistanceInDecameters = Distance.ofDecameters(5.0);

        // When
        Distance actualInMeters = initialDistanceInDecameters.toBaseUnit();
        Distance actualInDecameters = actualInMeters.toUnit(DistanceUnits.DECAMETER);
        double actualInDecametersVal = actualInMeters.getInDecameters();

        // Then
        Distance expectedInMeters = Distance.ofMeters(50.0); // 5 * 10.0
        assertThat(actualInDecameters.getValue()).isEqualTo(actualInDecametersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInDecameters).isEqualTo(initialDistanceInDecameters);
    }

    @Test
    @DisplayName("Distance: should convert to m from hm and vice versa")
    void shouldProperlyConvertToMetersFromHectometer() {
        // Given
        Distance initialDistanceInHectometers = Distance.ofHectometers(2.5);

        // When
        Distance actualInMeters = initialDistanceInHectometers.toBaseUnit();
        Distance actualInHectometers = actualInMeters.toUnit(DistanceUnits.HECTOMETER);
        double actualInHectometersVal = actualInMeters.getInHectometers();

        // Then
        Distance expectedInMeters = Distance.ofMeters(250.0); // 2.5 * 100.0
        assertThat(actualInHectometers.getValue()).isEqualTo(actualInHectometersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInHectometers).isEqualTo(initialDistanceInHectometers);
    }

    @Test
    @DisplayName("Distance: should convert to m from datmi and vice versa")
    void shouldProperlyConvertToMetersFromDataMile() {
        // Given
        Distance initialDistanceInDataMiles = Distance.ofDataMiles(1.0);

        // When
        Distance actualInMeters = initialDistanceInDataMiles.toBaseUnit();
        Distance actualInDataMiles = actualInMeters.toUnit(DistanceUnits.DATAMILE);
        double actualInDataMilesVal = actualInMeters.getInDataMiles();

        // Then
        Distance expectedInMeters = Distance.ofMeters(1828.8); // 1 * 1828.8
        assertThat(actualInDataMiles.getValue()).isEqualTo(actualInDataMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInDataMiles).isEqualTo(initialDistanceInDataMiles);
    }

    @Test
    @DisplayName("Distance: should return valid result from to() and getIn() methods for new units")
    void shouldReturnValidResultFromToAndGetInMethodsForNewUnits() {
        // Given
        Distance expected = Distance.ofMeters(1828.8);

        // When
        Distance actual = expected.toMeter()
                .toYard()
                .toDecameter()
                .toHectometer()
                .toDataMile()
                .toMeter();

        double actualValue = expected.getInMeters();
        double actualValueInYards = expected.getInYards();
        double actualValueInDataMiles = expected.getInDataMiles();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());

        assertThat(actualValueInDataMiles).isEqualTo(1.0);
        // 1828.8 m / 0.9144 m/yd = 2000.0 yd
        assertThat(actualValueInYards).isEqualTo(2000.0);
    }

}
