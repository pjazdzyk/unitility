package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ThicknessTest {

    @Test
    @DisplayName("Thickness: should convert to m from mm and vice versa")
    void shouldProperlyConvertToMetersFromMillimeters() {
        // Given
        Thickness initialThicknessInMillimeters = Thickness.ofMillimeters(1000.0);

        // When
        Thickness actualInMeters = initialThicknessInMillimeters.toUnit(DistanceUnits.METER);
        double actualInMetersVal = actualInMeters.getInMeters();
        Thickness actualInMillimeters = actualInMeters.toUnit(DistanceUnits.MILLIMETER);
        double actualInMillimetersVal = actualInMeters.getInMillimeters();

        // Then
        Thickness expectedInMeters = Thickness.ofMeters(1.0);
        assertThat(actualInMeters.getValue()).isEqualTo(actualInMetersVal);
        assertThat(actualInMillimeters.getValue()).isEqualTo(actualInMillimetersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMillimeters).isEqualTo(initialThicknessInMillimeters);
    }

    @Test
    @DisplayName("Thickness: should convert to m from cm and vice versa")
    void shouldProperlyConvertToMetersFromCentimeters() {
        // Given
        Thickness initialThicknessInCentimeters = Thickness.ofCentimeters(100.0);

        // When
        Thickness actualInMeters = initialThicknessInCentimeters.toBaseUnit();
        Thickness actualInCentimeters = actualInMeters.toUnit(DistanceUnits.CENTIMETER);
        double actualInCentimetersVal = actualInMeters.getInCentimeters();

        // Then
        Thickness expectedInMeters = Thickness.ofMeters(1.0);
        assertThat(actualInCentimeters.getValue()).isEqualTo(actualInCentimetersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInCentimeters).isEqualTo(initialThicknessInCentimeters);
    }

    @Test
    @DisplayName("Thickness: should convert to m from km and vice versa")
    void shouldProperlyConvertToMetersFromKilometers() {
        // Given
        Thickness initialThicknessInKilometers = Thickness.ofKilometers(1.0);

        // When
        Thickness actualInMeters = initialThicknessInKilometers.toBaseUnit();
        Thickness actualInKilometers = actualInMeters.toUnit(DistanceUnits.KILOMETER);
        double actualInKilometersVal = actualInMeters.getInKilometers();

        // Then
        Thickness expectedInMeters = Thickness.ofMeters(1000.0);
        assertThat(actualInKilometers.getValue()).isEqualTo(actualInKilometersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInKilometers).isEqualTo(initialThicknessInKilometers);
    }

    @Test
    @DisplayName("Thickness: should convert to m from mi and vice versa")
    void shouldProperlyConvertToMetersFromMile() {
        // Given
        Thickness initialThicknessInMiles = Thickness.ofMiles(1.0);

        // When
        Thickness actualInMeters = initialThicknessInMiles.toBaseUnit();
        Thickness actualInMiles = actualInMeters.toUnit(DistanceUnits.MILE);
        double actualInMilesVal = actualInMeters.getInMiles();

        // Then
        Thickness expectedInMeters = Thickness.ofMeters(1609.344);
        assertThat(actualInMiles.getValue()).isEqualTo(actualInMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMiles).isEqualTo(initialThicknessInMiles);
    }

    @Test
    @DisplayName("Thickness: should convert to m from ft and vice versa")
    void shouldProperlyConvertToMetersFromFeet() {
        // Given
        Thickness initialThicknessInFeet = Thickness.ofFeet(10.0);

        // When
        Thickness actualInMeters = initialThicknessInFeet.toBaseUnit();
        Thickness actualInFeet = actualInMeters.toUnit(DistanceUnits.FEET);
        double actualInFeetVal = actualInMeters.getInFeet();

        // Then
        Thickness expectedInMeters = Thickness.ofMeters(3.048);
        assertThat(actualInFeet.getValue()).isEqualTo(actualInFeetVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInFeet).isEqualTo(initialThicknessInFeet);
    }

    @Test
    @DisplayName("Thickness: should convert to m from ft and vice versa")
    void shouldProperlyConvertToMetersFromInch() {
        // Given
        Thickness initialThicknessInInch = Thickness.ofInches(10.0);

        // When
        Thickness actualInMeters = initialThicknessInInch.toBaseUnit();
        Thickness actualInInch = actualInMeters.toUnit(DistanceUnits.INCH);
        double actualInInchVal = actualInMeters.getInInches();

        // Then
        Thickness expectedInMeters = Thickness.ofMeters(0.254);
        assertThat(actualInInch.getValue()).isEqualTo(actualInInchVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInInch).isEqualTo(initialThicknessInInch);
    }

    @Test
    @DisplayName("Thickness: should have m as base unit")
    void shouldHaveMetersAsBaseUnit() {
        // Given
        DistanceUnit expectedBaseUnit = DistanceUnits.METER;

        // When
        Thickness ThicknessInMiles = Thickness.ofMiles(10);
        DistanceUnit actualBaseUnit = ThicknessInMiles.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("Thickness: should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Thickness expected = Thickness.ofMeters(10.1);

        // When
        Thickness actual = expected.toMeter()
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
    @DisplayName("Thickness: should convert to m from nmi and vice versa")
    void shouldProperlyConvertToMetersFromNauticalMile() {
        // Given
        Thickness initialThicknessInMiles = Thickness.ofNauticalMiles(1.0);

        // When
        Thickness actualInMeters = initialThicknessInMiles.toBaseUnit();
        Thickness actualInMiles = actualInMeters.toUnit(DistanceUnits.NAUTICAL_MILE);
        double actualInMilesVal = actualInMeters.getInNauticalMiles();

        // Then
        Thickness expectedInMeters = Thickness.ofMeters(1852);
        assertThat(actualInMiles.getValue()).isEqualTo(actualInMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMiles).isEqualTo(initialThicknessInMiles);
    }

    @Test
    @DisplayName("Thickness: should convert to m from yd and vice versa")
    void shouldProperlyConvertToMetersFromYard() {
        // Given
        Thickness initialThicknessInYards = Thickness.ofYards(10.0);

        // When
        Thickness actualInMeters = initialThicknessInYards.toBaseUnit();
        Thickness actualInYards = actualInMeters.toUnit(DistanceUnits.YARD);
        double actualInYardsVal = actualInMeters.getInYards();

        // Then
        Thickness expectedInMeters = Thickness.ofMeters(9.144); // 10 * 0.9144
        assertThat(actualInYards.getValue()).isEqualTo(actualInYardsVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInYards).isEqualTo(initialThicknessInYards);
    }

    @Test
    @DisplayName("Thickness: should convert to m from dam and vice versa")
    void shouldProperlyConvertToMetersFromDecameter() {
        // Given
        Thickness initialThicknessInDecameters = Thickness.ofDecameters(5.0);

        // When
        Thickness actualInMeters = initialThicknessInDecameters.toBaseUnit();
        Thickness actualInDecameters = actualInMeters.toUnit(DistanceUnits.DECAMETER);
        double actualInDecametersVal = actualInMeters.getInDecameters();

        // Then
        Thickness expectedInMeters = Thickness.ofMeters(50.0); // 5 * 10.0
        assertThat(actualInDecameters.getValue()).isEqualTo(actualInDecametersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInDecameters).isEqualTo(initialThicknessInDecameters);
    }

    @Test
    @DisplayName("Thickness: should convert to m from hm and vice versa")
    void shouldProperlyConvertToMetersFromHectometer() {
        // Given
        Thickness initialThicknessInHectometers = Thickness.ofHectometers(2.5);

        // When
        Thickness actualInMeters = initialThicknessInHectometers.toBaseUnit();
        Thickness actualInHectometers = actualInMeters.toUnit(DistanceUnits.HECTOMETER);
        double actualInHectometersVal = actualInMeters.getInHectometers();

        // Then
        Thickness expectedInMeters = Thickness.ofMeters(250.0); // 2.5 * 100.0
        assertThat(actualInHectometers.getValue()).isEqualTo(actualInHectometersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInHectometers).isEqualTo(initialThicknessInHectometers);
    }

    @Test
    @DisplayName("Thickness: should convert to m from datmi and vice versa")
    void shouldProperlyConvertToMetersFromDataMile() {
        // Given
        Thickness initialThicknessInDataMiles = Thickness.ofDataMiles(1.0);

        // When
        Thickness actualInMeters = initialThicknessInDataMiles.toBaseUnit();
        Thickness actualInDataMiles = actualInMeters.toUnit(DistanceUnits.DATAMILE);
        double actualInDataMilesVal = actualInMeters.getInDataMiles();

        // Then
        Thickness expectedInMeters = Thickness.ofMeters(1828.8); // 1 * 1828.8
        assertThat(actualInDataMiles.getValue()).isEqualTo(actualInDataMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInDataMiles).isEqualTo(initialThicknessInDataMiles);
    }

    @Test
    @DisplayName("Thickness: should return valid result from to() and getIn() methods including new units")
    void shouldReturnValidResultFromToAndGetInMethodsIncludingNewUnits() {
        // Given
        Thickness expected = Thickness.ofMeters(1828.8);

        // When
        Thickness actual = expected.toMeter()
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
