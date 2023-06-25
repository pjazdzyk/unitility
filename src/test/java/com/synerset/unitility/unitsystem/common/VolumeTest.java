package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.Unit;
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

        // Then
        Volume expectedInCubicMeter = Volume.ofCubicMeters(1.0);
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
        Volume actualInLiter = actualInCubicMeter.toCubicCentimeter();

        // Then
        Volume expectedInCubicMeter = Volume.ofCubicMeters(1.0);
        assertThat(actualInCubicMeter).isEqualTo(expectedInCubicMeter);
        assertThat(actualInLiter).isEqualTo(initialVolumeInCubicCentimeters);
    }

    @Test
    @DisplayName("should convert to m³ from hectoLiter and vice versa")
    void shouldProperlyConvertToCubicMetersFromHectoLiters() {
        // Given
        Volume initialVolumeInHectoLiters = Volume.ofHectoLiters(10.0);

        // When
        Volume actualInCubicMeters = initialVolumeInHectoLiters.toBaseUnit();
        Volume actualInHectoLiters = actualInCubicMeters.toHectoLiter();

        // Then
        Volume expectedInCubicMeters = Volume.ofCubicMeters(1.0);
        assertThat(actualInCubicMeters).isEqualTo(expectedInCubicMeters);
        assertThat(actualInHectoLiters).isEqualTo(initialVolumeInHectoLiters);
    }

    @Test
    @DisplayName("should convert to m³ from mL and vice versa")
    void shouldProperlyConvertToCubicMeterFromMilliliter() {
        // Given
        Volume initialVolumeInMilliliter = Volume.ofMilliLiters(1000_000.0);

        // When
        Volume actualInCubicMeter = initialVolumeInMilliliter.toBaseUnit();
        Volume actualInMilliliter = actualInCubicMeter.toMilliLiter();

        // Then
        Volume expectedInCubicMeter = Volume.ofCubicMeters(100);
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
        Volume actualInPint = actualInCubicMeter.toPint();

        // Then
        Volume expectedInCubicMeter = Volume.ofCubicMeters(0.473176473);
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
        Volume actualInGallon = actualInCubicMeter.toGallon();

        // Then
        Volume expectedInCubicMeter = Volume.ofCubicMeters(3.785411784);
        assertThat(actualInCubicMeter.getValue()).isEqualTo(expectedInCubicMeter.getValue(), withPrecision(1E-15));
        assertThat(actualInGallon).isEqualTo(initialVolumeInGallon);
    }

    @Test
    @DisplayName("should convert to m³ from fl.oz and vice versa")
    void shouldProperlyConvertToCubicMetersFromOunce() {
        // Given
        Volume initialVolumeInOunces = Volume.ofOunces(10.0);

        // When
        Volume actualInCubicMeters = initialVolumeInOunces.toBaseUnit();
        Volume actualInOunces = actualInCubicMeters.toOunce();

        // Then
        Volume expectedInCubicMeters = Volume.ofCubicMeters(0.000295735295625);
        assertThat(actualInCubicMeters.getValue()).isEqualTo(expectedInCubicMeters.getValue(), withPrecision(1E-15));
        assertThat(actualInOunces).isEqualTo(initialVolumeInOunces);
    }

    @Test
    @DisplayName("should have m³ as base unit")
    void shouldHaveCubicMeterAsBaseUnit() {
        // Given
        VolumeUnits expectedBaseUnit = VolumeUnits.CUBIC_METER;

        // When
        Volume volumeInGallons = Volume.ofGallons(100);
        Unit<Volume> actualBaseUnit = volumeInGallons.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

}

