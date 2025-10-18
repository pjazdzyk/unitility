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

    @Test
    @DisplayName("Diameter: should convert to m from yd and vice versa")
    void shouldProperlyConvertToMetersFromYard() {
        // Given
        Diameter initialLengthInYards = Diameter.ofYards(10.0);

        // When
        Diameter actualInMeters = initialLengthInYards.toBaseUnit();
        Diameter actualInYards = actualInMeters.toUnit(DistanceUnits.YARD);
        double actualInYardsVal = actualInMeters.getInYards();

        // Then
        Diameter expectedInMeters = Diameter.ofMeters(9.144); // 10 * 0.9144
        assertThat(actualInYards.getValue()).isEqualTo(actualInYardsVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInYards).isEqualTo(initialLengthInYards);
    }

    @Test
    @DisplayName("Diameter: should convert to m from dam and vice versa")
    void shouldProperlyConvertToMetersFromDecameter() {
        // Given
        Diameter initialLengthInDecameters = Diameter.ofDecameters(5.0);

        // When
        Diameter actualInMeters = initialLengthInDecameters.toBaseUnit();
        Diameter actualInDecameters = actualInMeters.toUnit(DistanceUnits.DECAMETER);
        double actualInDecametersVal = actualInMeters.getInDecameters();

        // Then
        Diameter expectedInMeters = Diameter.ofMeters(50.0); // 5 * 10.0
        assertThat(actualInDecameters.getValue()).isEqualTo(actualInDecametersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInDecameters).isEqualTo(initialLengthInDecameters);
    }

    @Test
    @DisplayName("Diameter: should convert to m from hm and vice versa")
    void shouldProperlyConvertToMetersFromHectometer() {
        // Given
        Diameter initialLengthInHectometers = Diameter.ofHectometers(2.5);

        // When
        Diameter actualInMeters = initialLengthInHectometers.toBaseUnit();
        Diameter actualInHectometers = actualInMeters.toUnit(DistanceUnits.HECTOMETER);
        double actualInHectometersVal = actualInMeters.getInHectometers();

        // Then
        Diameter expectedInMeters = Diameter.ofMeters(250.0); // 2.5 * 100.0
        assertThat(actualInHectometers.getValue()).isEqualTo(actualInHectometersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInHectometers).isEqualTo(initialLengthInHectometers);
    }

    @Test
    @DisplayName("Diameter: should convert to m from datmi and vice versa")
    void shouldProperlyConvertToMetersFromDataMile() {
        // Given
        Diameter initialLengthInDataMiles = Diameter.ofDataMiles(1.0);

        // When
        Diameter actualInMeters = initialLengthInDataMiles.toBaseUnit();
        Diameter actualInDataMiles = actualInMeters.toUnit(DistanceUnits.DATAMILE);
        double actualInDataMilesVal = actualInMeters.getInDataMiles();

        // Then
        Diameter expectedInMeters = Diameter.ofMeters(1828.8); // 1 * 1828.8
        assertThat(actualInDataMiles.getValue()).isEqualTo(actualInDataMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInDataMiles).isEqualTo(initialLengthInDataMiles);
    }

    @Test
    @DisplayName("Diameter: should return valid result from to() and getIn() methods including new units")
    void shouldReturnValidResultFromToAndGetInMethodsIncludingNewUnits() {
        // Given
        Diameter expected = Diameter.ofMeters(1828.8);

        // When
        Diameter actual = expected.toMeter()
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
