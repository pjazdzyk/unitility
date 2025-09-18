package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.common.DistanceUnit;
import com.synerset.unitility.unitsystem.common.DistanceUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AbsoluteRoughnessTest {

    @Test
    @DisplayName("AbsoluteRoughness: should convert to m from mm and vice versa")
    void shouldProperlyConvertToMetersFromMillimeters() {
        // Given
        AbsoluteRoughness initialAbsoluteRoughnessInMillimeters = AbsoluteRoughness.ofMillimeters(1000.0);

        // When
        AbsoluteRoughness actualInMeters = initialAbsoluteRoughnessInMillimeters.toUnit(DistanceUnits.METER);
        double actualInMetersVal = actualInMeters.getInMeters();
        AbsoluteRoughness actualInMillimeters = actualInMeters.toUnit(DistanceUnits.MILLIMETER);
        double actualInMillimetersVal = actualInMeters.getInMillimeters();

        // Then
        AbsoluteRoughness expectedInMeters = AbsoluteRoughness.ofMeters(1.0);
        assertThat(actualInMeters.getValue()).isEqualTo(actualInMetersVal);
        assertThat(actualInMillimeters.getValue()).isEqualTo(actualInMillimetersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMillimeters).isEqualTo(initialAbsoluteRoughnessInMillimeters);
    }

    @Test
    @DisplayName("AbsoluteRoughness: should convert to m from cm and vice versa")
    void shouldProperlyConvertToMetersFromCentimeters() {
        // Given
        AbsoluteRoughness initialAbsoluteRoughnessInCentimeters = AbsoluteRoughness.ofCentimeters(100.0);

        // When
        AbsoluteRoughness actualInMeters = initialAbsoluteRoughnessInCentimeters.toBaseUnit();
        AbsoluteRoughness actualInCentimeters = actualInMeters.toUnit(DistanceUnits.CENTIMETER);
        double actualInCentimetersVal = actualInMeters.getInCentimeters();

        // Then
        AbsoluteRoughness expectedInMeters = AbsoluteRoughness.ofMeters(1.0);
        assertThat(actualInCentimeters.getValue()).isEqualTo(actualInCentimetersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInCentimeters).isEqualTo(initialAbsoluteRoughnessInCentimeters);
    }

    @Test
    @DisplayName("AbsoluteRoughness: should convert to m from km and vice versa")
    void shouldProperlyConvertToMetersFromKilometers() {
        // Given
        AbsoluteRoughness initialAbsoluteRoughnessInKilometers = AbsoluteRoughness.ofKilometers(1.0);

        // When
        AbsoluteRoughness actualInMeters = initialAbsoluteRoughnessInKilometers.toBaseUnit();
        AbsoluteRoughness actualInKilometers = actualInMeters.toUnit(DistanceUnits.KILOMETER);
        double actualInKilometersVal = actualInMeters.getInKilometers();

        // Then
        AbsoluteRoughness expectedInMeters = AbsoluteRoughness.ofMeters(1000.0);
        assertThat(actualInKilometers.getValue()).isEqualTo(actualInKilometersVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInKilometers).isEqualTo(initialAbsoluteRoughnessInKilometers);
    }

    @Test
    @DisplayName("AbsoluteRoughness: should convert to m from mi and vice versa")
    void shouldProperlyConvertToMetersFromMile() {
        // Given
        AbsoluteRoughness initialAbsoluteRoughnessInMiles = AbsoluteRoughness.ofMiles(1.0);

        // When
        AbsoluteRoughness actualInMeters = initialAbsoluteRoughnessInMiles.toBaseUnit();
        AbsoluteRoughness actualInMiles = actualInMeters.toUnit(DistanceUnits.MILE);
        double actualInMilesVal = actualInMeters.getInMiles();

        // Then
        AbsoluteRoughness expectedInMeters = AbsoluteRoughness.ofMeters(1609.344);
        assertThat(actualInMiles.getValue()).isEqualTo(actualInMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMiles).isEqualTo(initialAbsoluteRoughnessInMiles);
    }

    @Test
    @DisplayName("AbsoluteRoughness: should convert to m from ft and vice versa")
    void shouldProperlyConvertToMetersFromFeet() {
        // Given
        AbsoluteRoughness initialAbsoluteRoughnessInFeet = AbsoluteRoughness.ofFeet(10.0);

        // When
        AbsoluteRoughness actualInMeters = initialAbsoluteRoughnessInFeet.toBaseUnit();
        AbsoluteRoughness actualInFeet = actualInMeters.toUnit(DistanceUnits.FEET);
        double actualInFeetVal = actualInMeters.getInFeet();

        // Then
        AbsoluteRoughness expectedInMeters = AbsoluteRoughness.ofMeters(3.048);
        assertThat(actualInFeet.getValue()).isEqualTo(actualInFeetVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInFeet).isEqualTo(initialAbsoluteRoughnessInFeet);
    }

    @Test
    @DisplayName("AbsoluteRoughness: should convert to m from ft and vice versa")
    void shouldProperlyConvertToMetersFromInch() {
        // Given
        AbsoluteRoughness initialAbsoluteRoughnessInInch = AbsoluteRoughness.ofInches(10.0);

        // When
        AbsoluteRoughness actualInMeters = initialAbsoluteRoughnessInInch.toBaseUnit();
        AbsoluteRoughness actualInInch = actualInMeters.toUnit(DistanceUnits.INCH);
        double actualInInchVal = actualInMeters.getInInches();

        // Then
        AbsoluteRoughness expectedInMeters = AbsoluteRoughness.ofMeters(0.254);
        assertThat(actualInInch.getValue()).isEqualTo(actualInInchVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInInch).isEqualTo(initialAbsoluteRoughnessInInch);
    }

    @Test
    @DisplayName("AbsoluteRoughness: should have m as base unit")
    void shouldHaveMetersAsBaseUnit() {
        // Given
        DistanceUnit expectedBaseUnit = DistanceUnits.METER;

        // When
        AbsoluteRoughness AbsoluteRoughnessInMiles = AbsoluteRoughness.ofMiles(10);
        DistanceUnit actualBaseUnit = AbsoluteRoughnessInMiles.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("AbsoluteRoughness: should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        AbsoluteRoughness expected = AbsoluteRoughness.ofMeters(10.1);

        // When
        AbsoluteRoughness actual = expected.toMeter()
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
    @DisplayName("AbsoluteRoughness: should convert to m from nmi and vice versa")
    void shouldProperlyConvertToMetersFromNauticalMile() {
        // Given
        AbsoluteRoughness initialAbsoluteRoughnessInMiles = AbsoluteRoughness.ofNauticalMiles(1.0);

        // When
        AbsoluteRoughness actualInMeters = initialAbsoluteRoughnessInMiles.toBaseUnit();
        AbsoluteRoughness actualInMiles = actualInMeters.toUnit(DistanceUnits.NAUTICAL_MILE);
        double actualInMilesVal = actualInMeters.getInNauticalMiles();

        // Then
        AbsoluteRoughness expectedInMeters = AbsoluteRoughness.ofMeters(1852);
        assertThat(actualInMiles.getValue()).isEqualTo(actualInMilesVal);
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMiles).isEqualTo(initialAbsoluteRoughnessInMiles);
    }

}
