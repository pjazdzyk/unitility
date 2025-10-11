package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PerimeterTest {

    @Test
    @DisplayName("Perimeter: should convert to m from mm and vice versa")
    void shouldProperlyConvertToMetersFromMillimeters() {
        // Given
        Perimeter initialPerimeterInMillimeters = Perimeter.ofMillimeters(1000.0);

        // When
        Perimeter actualInMeters = initialPerimeterInMillimeters.toUnit(DistanceUnits.METER);
        double actualInMetersVal = actualInMeters.getInMeters();
        Perimeter actualInMillimeters = actualInMeters.toUnit(DistanceUnits.MILLIMETER);
        double actualInMillimetersVal = actualInMeters.getInMillimeters();

        // Then
        Perimeter expectedInMeters = Perimeter.ofMeters(1.0);
        assertThat(actualInMeters.getValue()).isEqualTo(actualInMetersVal);
        assertThat(actualInMillimeters.getValue()).isEqualTo(actualInMillimetersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMillimeters).isEqualTo(initialPerimeterInMillimeters);
    }

    @Test
    @DisplayName("Perimeter: should convert to m from cm and vice versa")
    void shouldProperlyConvertToMetersFromCentimeters() {
        // Given
        Perimeter initialPerimeterInCentimeters = Perimeter.ofCentimeters(100.0);

        // When
        Perimeter actualInMeters = initialPerimeterInCentimeters.toBaseUnit();
        Perimeter actualInCentimeters = actualInMeters.toUnit(DistanceUnits.CENTIMETER);
        double actualInCentimetersVal = actualInMeters.getInCentimeters();

        // Then
        Perimeter expectedInMeters = Perimeter.ofMeters(1.0);
        assertThat(actualInCentimeters.getValue()).isEqualTo(actualInCentimetersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInCentimeters).isEqualTo(initialPerimeterInCentimeters);
    }

    @Test
    @DisplayName("Perimeter: should convert to m from km and vice versa")
    void shouldProperlyConvertToMetersFromKilometers() {
        // Given
        Perimeter initialPerimeterInKilometers = Perimeter.ofKilometers(1.0);

        // When
        Perimeter actualInMeters = initialPerimeterInKilometers.toBaseUnit();
        Perimeter actualInKilometers = actualInMeters.toUnit(DistanceUnits.KILOMETER);
        double actualInKilometersVal = actualInMeters.getInKilometers();

        // Then
        Perimeter expectedInMeters = Perimeter.ofMeters(1000.0);
        assertThat(actualInKilometers.getValue()).isEqualTo(actualInKilometersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInKilometers).isEqualTo(initialPerimeterInKilometers);
    }

    @Test
    @DisplayName("Perimeter: should convert to m from mi and vice versa")
    void shouldProperlyConvertToMetersFromMile() {
        // Given
        Perimeter initialPerimeterInMiles = Perimeter.ofMiles(1.0);

        // When
        Perimeter actualInMeters = initialPerimeterInMiles.toBaseUnit();
        Perimeter actualInMiles = actualInMeters.toUnit(DistanceUnits.MILE);
        double actualInMilesVal = actualInMeters.getInMiles();

        // Then
        Perimeter expectedInMeters = Perimeter.ofMeters(1609.344);
        assertThat(actualInMiles.getValue()).isEqualTo(actualInMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMiles).isEqualTo(initialPerimeterInMiles);
    }

    @Test
    @DisplayName("Perimeter: should convert to m from ft and vice versa")
    void shouldProperlyConvertToMetersFromFeet() {
        // Given
        Perimeter initialPerimeterInFeet = Perimeter.ofFeet(10.0);

        // When
        Perimeter actualInMeters = initialPerimeterInFeet.toBaseUnit();
        Perimeter actualInFeet = actualInMeters.toUnit(DistanceUnits.FEET);
        double actualInFeetVal = actualInMeters.getInFeet();

        // Then
        Perimeter expectedInMeters = Perimeter.ofMeters(3.048);
        assertThat(actualInFeet.getValue()).isEqualTo(actualInFeetVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInFeet).isEqualTo(initialPerimeterInFeet);
    }

    @Test
    @DisplayName("Perimeter: should convert to m from ft and vice versa")
    void shouldProperlyConvertToMetersFromInch() {
        // Given
        Perimeter initialPerimeterInInch = Perimeter.ofInches(10.0);

        // When
        Perimeter actualInMeters = initialPerimeterInInch.toBaseUnit();
        Perimeter actualInInch = actualInMeters.toUnit(DistanceUnits.INCH);
        double actualInInchVal = actualInMeters.getInInches();

        // Then
        Perimeter expectedInMeters = Perimeter.ofMeters(0.254);
        assertThat(actualInInch.getValue()).isEqualTo(actualInInchVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInInch).isEqualTo(initialPerimeterInInch);
    }

    @Test
    @DisplayName("Perimeter: should have m as base unit")
    void shouldHaveMetersAsBaseUnit() {
        // Given
        DistanceUnit expectedBaseUnit = DistanceUnits.METER;

        // When
        Perimeter perimeterInMiles = Perimeter.ofMiles(10);
        DistanceUnit actualBaseUnit = perimeterInMiles.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("Perimeter: should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Perimeter expected = Perimeter.ofMeters(10.1);

        // When
        Perimeter actual = expected.toMeter()
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
    @DisplayName("Perimeter: should convert to m from nmi and vice versa")
    void shouldProperlyConvertToMetersFromNauticalMile() {
        // Given
        Perimeter initialPerimeterInMiles = Perimeter.ofNauticalMiles(1.0);

        // When
        Perimeter actualInMeters = initialPerimeterInMiles.toBaseUnit();
        Perimeter actualInMiles = actualInMeters.toUnit(DistanceUnits.NAUTICAL_MILE);
        double actualInMilesVal = actualInMeters.getInNauticalMiles();

        // Then
        Perimeter expectedInMeters = Perimeter.ofMeters(1852);
        assertThat(actualInMiles.getValue()).isEqualTo(actualInMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMiles).isEqualTo(initialPerimeterInMiles);
    }

    @Test
    @DisplayName("Perimeter: should convert to m from yd and vice versa")
    void shouldProperlyConvertToMetersFromYard() {
        // Given
        Perimeter initialPerimeterInYards = Perimeter.ofYards(10.0);

        // When
        Perimeter actualInMeters = initialPerimeterInYards.toBaseUnit();
        Perimeter actualInYards = actualInMeters.toUnit(DistanceUnits.YARD);
        double actualInYardsVal = actualInMeters.getInYards();

        // Then
        Perimeter expectedInMeters = Perimeter.ofMeters(9.144); // 10 * 0.9144
        assertThat(actualInYards.getValue()).isEqualTo(actualInYardsVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInYards).isEqualTo(initialPerimeterInYards);
    }

    @Test
    @DisplayName("Perimeter: should convert to m from dam and vice versa")
    void shouldProperlyConvertToMetersFromDecameter() {
        // Given
        Perimeter initialPerimeterInDecameters = Perimeter.ofDecameters(5.0);

        // When
        Perimeter actualInMeters = initialPerimeterInDecameters.toBaseUnit();
        Perimeter actualInDecameters = actualInMeters.toUnit(DistanceUnits.DECAMETER);
        double actualInDecametersVal = actualInMeters.getInDecameters();

        // Then
        Perimeter expectedInMeters = Perimeter.ofMeters(50.0); // 5 * 10.0
        assertThat(actualInDecameters.getValue()).isEqualTo(actualInDecametersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInDecameters).isEqualTo(initialPerimeterInDecameters);
    }

    @Test
    @DisplayName("Perimeter: should convert to m from hm and vice versa")
    void shouldProperlyConvertToMetersFromHectometer() {
        // Given
        Perimeter initialPerimeterInHectometers = Perimeter.ofHectometers(2.5);

        // When
        Perimeter actualInMeters = initialPerimeterInHectometers.toBaseUnit();
        Perimeter actualInHectometers = actualInMeters.toUnit(DistanceUnits.HECTOMETER);
        double actualInHectometersVal = actualInMeters.getInHectometers();

        // Then
        Perimeter expectedInMeters = Perimeter.ofMeters(250.0); // 2.5 * 100.0
        assertThat(actualInHectometers.getValue()).isEqualTo(actualInHectometersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInHectometers).isEqualTo(initialPerimeterInHectometers);
    }

    @Test
    @DisplayName("Perimeter: should convert to m from datmi and vice versa")
    void shouldProperlyConvertToMetersFromDataMile() {
        // Given
        Perimeter initialPerimeterInDataMiles = Perimeter.ofDataMiles(1.0);

        // When
        Perimeter actualInMeters = initialPerimeterInDataMiles.toBaseUnit();
        Perimeter actualInDataMiles = actualInMeters.toUnit(DistanceUnits.DATAMILE);
        double actualInDataMilesVal = actualInMeters.getInDataMiles();

        // Then
        Perimeter expectedInMeters = Perimeter.ofMeters(1828.8); // 1 * 1828.8
        assertThat(actualInDataMiles.getValue()).isEqualTo(actualInDataMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInDataMiles).isEqualTo(initialPerimeterInDataMiles);
    }

    @Test
    @DisplayName("Perimeter: should return valid result from to() and getIn() methods including new units")
    void shouldReturnValidResultFromToAndGetInMethodsIncludingNewUnits() {
        // Given
        Perimeter expected = Perimeter.ofMeters(1828.8);

        // When
        Perimeter actual = expected.toMeter()
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
