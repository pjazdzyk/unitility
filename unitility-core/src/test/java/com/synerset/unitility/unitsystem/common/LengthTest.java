package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LengthTest {

    @Test
    @DisplayName("Length: should convert to m from mm and vice versa")
    void shouldProperlyConvertToMetersFromMillimeters() {
        // Given
        Length initialLengthInMillimeters = Length.ofMillimeters(1000.0);

        // When
        Length actualInMeters = initialLengthInMillimeters.toUnit(DistanceUnits.METER);
        double actualInMetersVal = actualInMeters.getInMeters();
        Length actualInMillimeters = actualInMeters.toUnit(DistanceUnits.MILLIMETER);
        double actualInMillimetersVal = actualInMeters.getInMillimeters();

        // Then
        Length expectedInMeters = Length.ofMeters(1.0);
        assertThat(actualInMeters.getValue()).isEqualTo(actualInMetersVal);
        assertThat(actualInMillimeters.getValue()).isEqualTo(actualInMillimetersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMillimeters).isEqualTo(initialLengthInMillimeters);
    }

    @Test
    @DisplayName("Length: should convert to m from cm and vice versa")
    void shouldProperlyConvertToMetersFromCentimeters() {
        // Given
        Length initialLengthInCentimeters = Length.ofCentimeters(100.0);

        // When
        Length actualInMeters = initialLengthInCentimeters.toBaseUnit();
        Length actualInCentimeters = actualInMeters.toUnit(DistanceUnits.CENTIMETER);
        double actualInCentimetersVal = actualInMeters.getInCentimeters();

        // Then
        Length expectedInMeters = Length.ofMeters(1.0);
        assertThat(actualInCentimeters.getValue()).isEqualTo(actualInCentimetersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInCentimeters).isEqualTo(initialLengthInCentimeters);
    }

    @Test
    @DisplayName("Length: should convert to m from km and vice versa")
    void shouldProperlyConvertToMetersFromKilometers() {
        // Given
        Length initialLengthInKilometers = Length.ofKilometers(1.0);

        // When
        Length actualInMeters = initialLengthInKilometers.toBaseUnit();
        Length actualInKilometers = actualInMeters.toUnit(DistanceUnits.KILOMETER);
        double actualInKilometersVal = actualInMeters.getInKilometers();

        // Then
        Length expectedInMeters = Length.ofMeters(1000.0);
        assertThat(actualInKilometers.getValue()).isEqualTo(actualInKilometersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInKilometers).isEqualTo(initialLengthInKilometers);
    }

    @Test
    @DisplayName("Length: should convert to m from mi and vice versa")
    void shouldProperlyConvertToMetersFromMile() {
        // Given
        Length initialLengthInMiles = Length.ofMiles(1.0);

        // When
        Length actualInMeters = initialLengthInMiles.toBaseUnit();
        Length actualInMiles = actualInMeters.toUnit(DistanceUnits.MILE);
        double actualInMilesVal = actualInMeters.getInMiles();

        // Then
        Length expectedInMeters = Length.ofMeters(1609.344);
        assertThat(actualInMiles.getValue()).isEqualTo(actualInMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMiles).isEqualTo(initialLengthInMiles);
    }

    @Test
    @DisplayName("Length: should convert to m from ft and vice versa")
    void shouldProperlyConvertToMetersFromFeet() {
        // Given
        Length initialLengthInFeet = Length.ofFeet(10.0);

        // When
        Length actualInMeters = initialLengthInFeet.toBaseUnit();
        Length actualInFeet = actualInMeters.toUnit(DistanceUnits.FEET);
        double actualInFeetVal = actualInMeters.getInFeet();

        // Then
        Length expectedInMeters = Length.ofMeters(3.048);
        assertThat(actualInFeet.getValue()).isEqualTo(actualInFeetVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInFeet).isEqualTo(initialLengthInFeet);
    }

    @Test
    @DisplayName("Length: should convert to m from ft and vice versa")
    void shouldProperlyConvertToMetersFromInch() {
        // Given
        Length initialLengthInInch = Length.ofInches(10.0);

        // When
        Length actualInMeters = initialLengthInInch.toBaseUnit();
        Length actualInInch = actualInMeters.toUnit(DistanceUnits.INCH);
        double actualInInchVal = actualInMeters.getInInches();

        // Then
        Length expectedInMeters = Length.ofMeters(0.254);
        assertThat(actualInInch.getValue()).isEqualTo(actualInInchVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInInch).isEqualTo(initialLengthInInch);
    }

    @Test
    @DisplayName("Length: should have m as base unit")
    void shouldHaveMetersAsBaseUnit() {
        // Given
        DistanceUnit expectedBaseUnit = DistanceUnits.METER;

        // When
        Length lengthInMiles = Length.ofMiles(10);
        DistanceUnit actualBaseUnit = lengthInMiles.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("Length: should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Length expected = Length.ofMeters(10.1);

        // When
        Length actual = expected.toMeter()
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
    @DisplayName("Length: should convert to m from nmi and vice versa")
    void shouldProperlyConvertToMetersFromNauticalMile() {
        // Given
        Length initialLengthInMiles = Length.ofNauticalMiles(1.0);

        // When
        Length actualInMeters = initialLengthInMiles.toBaseUnit();
        Length actualInMiles = actualInMeters.toUnit(DistanceUnits.NAUTICAL_MILE);
        double actualInMilesVal = actualInMeters.getInNauticalMiles();

        // Then
        Length expectedInMeters = Length.ofMeters(1852);
        assertThat(actualInMiles.getValue()).isEqualTo(actualInMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMiles).isEqualTo(initialLengthInMiles);
    }

    @Test
    @DisplayName("Length: should convert to m from yd and vice versa")
    void shouldProperlyConvertToMetersFromYard() {
        // Given
        Length initialLengthInYards = Length.ofYards(10.0);

        // When
        Length actualInMeters = initialLengthInYards.toBaseUnit();
        Length actualInYards = actualInMeters.toUnit(DistanceUnits.YARD);
        double actualInYardsVal = actualInMeters.getInYards();

        // Then
        Length expectedInMeters = Length.ofMeters(9.144); // 10 * 0.9144
        assertThat(actualInYards.getValue()).isEqualTo(actualInYardsVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInYards).isEqualTo(initialLengthInYards);
    }

    @Test
    @DisplayName("Length: should convert to m from dam and vice versa")
    void shouldProperlyConvertToMetersFromDecameter() {
        // Given
        Length initialLengthInDecameters = Length.ofDecameters(5.0);

        // When
        Length actualInMeters = initialLengthInDecameters.toBaseUnit();
        Length actualInDecameters = actualInMeters.toUnit(DistanceUnits.DECAMETER);
        double actualInDecametersVal = actualInMeters.getInDecameters();

        // Then
        Length expectedInMeters = Length.ofMeters(50.0); // 5 * 10.0
        assertThat(actualInDecameters.getValue()).isEqualTo(actualInDecametersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInDecameters).isEqualTo(initialLengthInDecameters);
    }

    @Test
    @DisplayName("Length: should convert to m from hm and vice versa")
    void shouldProperlyConvertToMetersFromHectometer() {
        // Given
        Length initialLengthInHectometers = Length.ofHectometers(2.5);

        // When
        Length actualInMeters = initialLengthInHectometers.toBaseUnit();
        Length actualInHectometers = actualInMeters.toUnit(DistanceUnits.HECTOMETER);
        double actualInHectometersVal = actualInMeters.getInHectometers();

        // Then
        Length expectedInMeters = Length.ofMeters(250.0); // 2.5 * 100.0
        assertThat(actualInHectometers.getValue()).isEqualTo(actualInHectometersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInHectometers).isEqualTo(initialLengthInHectometers);
    }

    @Test
    @DisplayName("Length: should convert to m from datmi and vice versa")
    void shouldProperlyConvertToMetersFromDataMile() {
        // Given
        Length initialLengthInDataMiles = Length.ofDataMiles(1.0);

        // When
        Length actualInMeters = initialLengthInDataMiles.toBaseUnit();
        Length actualInDataMiles = actualInMeters.toUnit(DistanceUnits.DATAMILE);
        double actualInDataMilesVal = actualInMeters.getInDataMiles();

        // Then
        Length expectedInMeters = Length.ofMeters(1828.8); // 1 * 1828.8
        assertThat(actualInDataMiles.getValue()).isEqualTo(actualInDataMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInDataMiles).isEqualTo(initialLengthInDataMiles);
    }

    @Test
    @DisplayName("Length: should return valid result from to() and getIn() methods including new units")
    void shouldReturnValidResultFromToAndGetInMethodsIncludingNewUnits() {
        // Given
        Length expected = Length.ofMeters(1828.8);

        // When
        Length actual = expected.toMeter()
                .toYard()
                .toDecameter()
                .toHectometer()
                .toDataMile()
                .toMeter();

        double actualValue = expected.getInMeters();
        double actualValueInDataMiles = expected.getInDataMiles();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());

        assertThat(actualValueInDataMiles).isEqualTo(1.0);
    }

}
