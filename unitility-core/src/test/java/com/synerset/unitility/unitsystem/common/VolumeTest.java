package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class VolumeTest {

    @Test
    @DisplayName("should convert to m³ from L and vice versa")
    void shouldProperlyConvertToCubicMeterFromLiter() {
        // Given
        Volume initialVolumeInLiter = Volume.ofLiters(1000.0);

        // When
        Volume actualInCubicMeter = initialVolumeInLiter.toBaseUnit();
        Volume actualInLiter = actualInCubicMeter.toUnit(VolumeUnits.LITER);
        double actualInLiterVal = actualInCubicMeter.getInLiters();
        double actualInCubicMeterVal = initialVolumeInLiter.getInCubicMeters();

        // Then
        Volume expectedInCubicMeter = Volume.ofCubicMeters(1.0);
        assertThat(actualInLiter.getValue()).isEqualTo(actualInLiterVal);
        assertThat(actualInCubicMeter.getValue()).isEqualTo(actualInCubicMeterVal);
        assertThat(actualInCubicMeter).isEqualTo(expectedInCubicMeter);
        assertThat(actualInLiter).isEqualTo(initialVolumeInLiter);
    }

    @Test
    @DisplayName("should convert to m³ from cm3 and vice versa")
    void shouldProperlyConvertToCubicMeterFromCubicCentimeter() {
        // Given
        Volume initialVolumeInCubicCentimeters = Volume.ofCubicCentimeters(1000.0);

        // When
        Volume actualInCubicMeter = initialVolumeInCubicCentimeters.toBaseUnit();
        Volume actualInCubicCM = actualInCubicMeter.toUnit(VolumeUnits.CUBIC_CENTIMETER);
        double actualInCubicCMVal = actualInCubicMeter.getInCubicCentimeters();

        // Then
        Volume expectedInCubicMeter = Volume.ofCubicMeters(1.0);
        assertThat(actualInCubicCM.getValue()).isEqualTo(actualInCubicCMVal);
        assertThat(actualInCubicMeter).isEqualTo(expectedInCubicMeter);
        assertThat(actualInCubicCM).isEqualTo(initialVolumeInCubicCentimeters);
    }

    @Test
    @DisplayName("should convert to m³ from hectoLiter and vice versa")
    void shouldProperlyConvertToCubicMetersFromHectoLiters() {
        // Given
        Volume initialVolumeInHectoLiters = Volume.ofHectoLiters(10.0);

        // When
        Volume actualInCubicMeter = initialVolumeInHectoLiters.toBaseUnit();
        Volume actualInHectoLiters = actualInCubicMeter.toUnit(VolumeUnits.HECTOLITRE);
        double actualInHectoLitersVal = actualInCubicMeter.getInHectoLiters();

        // Then
        Volume expectedInCubicMeters = Volume.ofCubicMeters(1.0);
        assertThat(actualInHectoLiters.getValue()).isEqualTo(actualInHectoLitersVal);
        assertThat(actualInCubicMeter).isEqualTo(expectedInCubicMeters);
        assertThat(actualInHectoLiters).isEqualTo(initialVolumeInHectoLiters);
    }

    @Test
    @DisplayName("should convert to m³ from mL and vice versa")
    void shouldProperlyConvertToCubicMeterFromMilliliter() {
        // Given
        Volume initialVolumeInMilliliter = Volume.ofMilliLiters(1000_000.0);

        // When
        Volume actualInCubicMeter = initialVolumeInMilliliter.toBaseUnit();
        Volume actualInMilliliter = actualInCubicMeter.toUnit(VolumeUnits.MILLILITRE);
        double actualInMilliliterVal = actualInCubicMeter.getInMilliLiters();

        // Then
        Volume expectedInCubicMeter = Volume.ofCubicMeters(100);
        assertThat(actualInMilliliter.getValue()).isEqualTo(actualInMilliliterVal);
        assertThat(actualInCubicMeter.getValue()).isEqualTo(expectedInCubicMeter.getValue(), withPrecision(1E-15));
        assertThat(actualInMilliliter).isEqualTo(initialVolumeInMilliliter);
    }

    @Test
    @DisplayName("should convert to m³ from pt and vice versa")
    void shouldProperlyConvertToCubicMeterFromPint() {
        // Given
        Volume initialVolumeInPint = Volume.ofPints(1000.0);

        // When
        Volume actualInCubicMeter = initialVolumeInPint.toBaseUnit();
        Volume actualInPint = actualInCubicMeter.toUnit(VolumeUnits.PINT);
        double actualInPintVal = actualInCubicMeter.getInPints();

        // Then
        Volume expectedInCubicMeter = Volume.ofCubicMeters(0.473176473);
        assertThat(actualInPint.getValue()).isEqualTo(actualInPintVal);
        assertThat(actualInCubicMeter.getValue()).isEqualTo(expectedInCubicMeter.getValue(), withPrecision(1E-15));
        assertThat(actualInPint).isEqualTo(initialVolumeInPint);
    }

    @Test
    @DisplayName("should convert to m³ from gal and vice versa")
    void shouldProperlyConvertToCubicMeterFromGallon() {
        // Given
        Volume initialVolumeInGallon = Volume.ofGallons(1000.0);

        // When
        Volume actualInCubicMeter = initialVolumeInGallon.toBaseUnit();
        Volume actualInGallon = actualInCubicMeter.toUnit(VolumeUnits.GALLON);
        double actualInGallonVal = actualInCubicMeter.getInGallons();

        // Then
        Volume expectedInCubicMeter = Volume.ofCubicMeters(3.785411784);
        assertThat(actualInGallon.getValue()).isEqualTo(actualInGallonVal);
        assertThat(actualInCubicMeter.getValue()).isEqualTo(expectedInCubicMeter.getValue(), withPrecision(1E-15));
        assertThat(actualInGallon).isEqualTo(initialVolumeInGallon);
    }

    @Test
    @DisplayName("should convert to m³ from fl.oz and vice versa")
    void shouldProperlyConvertToCubicMetersFromOunce() {
        // Given
        Volume initialVolumeInOunces = Volume.ofOunces(10.0);

        // When
        Volume actualInCubicMeter = initialVolumeInOunces.toBaseUnit();
        Volume actualInOunces = actualInCubicMeter.toUnit(VolumeUnits.OUNCE);
        double actualInOuncesVal = actualInCubicMeter.getInOunces();

        // Then
        Volume expectedInCubicMeters = Volume.ofCubicMeters(0.000295735295625);
        assertThat(actualInOunces.getValue()).isEqualTo(actualInOuncesVal);
        assertThat(actualInCubicMeter.getValue()).isEqualTo(expectedInCubicMeters.getValue(), withPrecision(1E-15));
        assertThat(actualInOunces).isEqualTo(initialVolumeInOunces);
    }

    @Test
    @DisplayName("should have m³ as base unit")
    void shouldHaveCubicMeterAsBaseUnit() {
        // Given
        VolumeUnit expectedBaseUnit = VolumeUnits.CUBIC_METER;

        // When
        Volume volumeInGallons = Volume.ofGallons(100);
        VolumeUnit actualBaseUnit = volumeInGallons.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Volume expected = Volume.ofCubicMeters(10.1);

        // When
        Volume actual = expected.toLiter()
                .toCubicCentimeter()
                .toHectoLiter()
                .toMilliLiter()
                .toOunce()
                .toPint()
                .toGallon()
                .toCubicMeter();
        double actualValue = expected.getInCubicMeters();

        // Then
        assertThat(actual.getValue()).isEqualTo(expected.getValue(), withPrecision(1E-13));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}

