package com.synerset.unitility.unitsystem.distance;

import com.synerset.unitility.unitsystem.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class DistanceTest {

    @Test
    @DisplayName("should convert to m from mm and vice versa")
    void shouldProperlyConvertToMetersFromMillimeters() {
        // Given
        Distance initialDistanceInMillimeters = Distance.ofMillimeters(new BigDecimal("1000.0"));

        // When
        Distance actualInMeters = initialDistanceInMillimeters.toBaseUnit();
        Distance actualInMillimeters = actualInMeters.toUnit(DistanceUnits.MILLIMETER);

        // Then
        Distance expectedInMeters = Distance.ofMeters(new BigDecimal("1.0"));
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInMillimeters).isEqualTo(initialDistanceInMillimeters);
    }

    @Test
    @DisplayName("should convert to m from cm and vice versa")
    void shouldProperlyConvertToMetersFromCentimeters() {
        // Given
        Distance initialDistanceInCentimeters = Distance.ofCentimeters(new BigDecimal("100.0"));

        // When
        Distance actualInMeters = initialDistanceInCentimeters.toBaseUnit();
        Distance actualInCentimeters = actualInMeters.toUnit(DistanceUnits.CENTIMETER);

        // Then
        Distance expectedInMeters = Distance.ofMeters(new BigDecimal("1.0"));
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInCentimeters).isEqualTo(initialDistanceInCentimeters);
    }

    @Test
    @DisplayName("should convert to m from km and vice versa")
    void shouldProperlyConvertToMetersFromKilometers() {
        // Given
        Distance initialDistanceInKilometers = Distance.ofKilometers(new BigDecimal("1.0"));

        // When
        Distance actualInMeters = initialDistanceInKilometers.toBaseUnit();
        Distance actualInKilometers = actualInMeters.toUnit(DistanceUnits.KILOMETER);

        // Then
        Distance expectedInMeters = Distance.ofMeters(new BigDecimal("1000.0"));
        assertThat(actualInMeters).isEqualTo(expectedInMeters);
        assertThat(actualInKilometers).isEqualTo(initialDistanceInKilometers);
    }

    @Test
    @DisplayName("should convert to m from mi and vice versa")
    void shouldProperlyConvertToMetersFromMile() {
        // Given
        Distance initialDistanceInMiles = Distance.ofMiles(new BigDecimal("1.0"));

        // When
        Distance actualInMeters = initialDistanceInMiles.toBaseUnit();
        Distance actualInMiles = actualInMeters.toUnit(DistanceUnits.MILE);

        // Then
        Distance expectedInMeters = Distance.ofMeters(new BigDecimal("1609.344"));
        assertThat(actualInMeters.equals(expectedInMeters));
        assertThat(actualInMiles.equals(initialDistanceInMiles));
    }

    @Test
    @DisplayName("should convert to m from ft and vice versa")
    void shouldProperlyConvertToMetersFromFeet() {
        // Given
        Distance initialDistanceInFeet = Distance.ofFeet(new BigDecimal("10.0"));

        // When
        Distance actualInMeters = initialDistanceInFeet.toBaseUnit();
        Distance actualInFeet = actualInMeters.toUnit(DistanceUnits.FEET);

        // Then
        Distance expectedInMeters = Distance.ofMeters(new BigDecimal("3.048"));
        assertThat(actualInMeters.equals(expectedInMeters));
        assertThat(actualInFeet.equals(initialDistanceInFeet));
    }

    @Test
    @DisplayName("should convert to m from ft and vice versa")
    void shouldProperlyConvertToMetersFromInch() {
        // Given
        Distance initialDistanceInInch = Distance.ofInches(new BigDecimal("10.0"));

        // When
        Distance actualInMeters = initialDistanceInInch.toBaseUnit();
        Distance actualInInch = actualInMeters.toUnit(DistanceUnits.INCH);

        // Then
        Distance expectedInMeters = Distance.ofMeters(new BigDecimal("0.0254"));
        assertThat(actualInMeters.equals(expectedInMeters));
        assertThat(actualInInch.equals(initialDistanceInInch));
    }

    @Test
    @DisplayName("should have m as base unit")
    void shouldHaveMetersAsBaseUnit() {
        // Given
        DistanceUnits expectedBaseUnit = DistanceUnits.METER;

        // When
        Distance DistanceInMiles = Distance.ofMiles(new BigDecimal("10"));
        Unit<Distance> actualBaseUnit = DistanceInMiles.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

}
