package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class SpecificVolumeTest {

    @Test
    @DisplayName("should have m³/kg as base unit")
    void shouldHaveCubicMeterPerKilogramAsBaseUnit() {
        // Given
        SpecificVolumeUnit expectedBaseUnit = SpecificVolumeUnits.CUBIC_METER_PER_KILOGRAM;

        // When
        SpecificVolume specificVol = SpecificVolume.ofLiterPerKilogram(1000);
        SpecificVolumeUnit actualBaseUnit = specificVol.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should convert to m³/kg from L/kg and vice versa")
    void shouldProperlyConvertToCubicMeterPerKilogramFromLiter() {
        // Given - Water specific volume = 1 L/kg at 4°C
        SpecificVolume initialSpecificVolInLiters = SpecificVolume.ofLiterPerKilogram(1.0);

        // When
        SpecificVolume actualInCubicMeter = initialSpecificVolInLiters.toBaseUnit();
        SpecificVolume actualInLiter = actualInCubicMeter.toUnit(SpecificVolumeUnits.LITER_PER_KILOGRAM);

        // Then
        SpecificVolume expectedInCubicMeter = SpecificVolume.ofCubicMeterPerKilogram(0.001);
        assertThat(actualInCubicMeter).isEqualTo(expectedInCubicMeter);
        assertThat(actualInLiter).isEqualTo(initialSpecificVolInLiters);
    }

    @Test
    @DisplayName("should convert to m³/kg from cm³/kg and vice versa")
    void shouldProperlyConvertToCubicMeterPerKilogramFromCubicCentimeter() {
        // Given - Water specific volume = 1000 cm³/kg at 4°C
        SpecificVolume initialSpecificVolInCM = SpecificVolume.ofCubicCentimeterPerKilogram(1000.0);

        // When
        SpecificVolume actualInCubicMeter = initialSpecificVolInCM.toBaseUnit();
        SpecificVolume actualInCM = actualInCubicMeter.toUnit(SpecificVolumeUnits.CUBIC_CENTIMETER_PER_KILOGRAM);

        // Then
        SpecificVolume expectedInCubicMeter = SpecificVolume.ofCubicMeterPerKilogram(0.001);
        assertThat(actualInCubicMeter).isEqualTo(expectedInCubicMeter);
        assertThat(actualInCM).isEqualTo(initialSpecificVolInCM);
    }

    @Test
    @DisplayName("should convert to m³/kg from ft³/lb and vice versa")
    void shouldProperlyConvertToCubicMeterPerKilogramFromCubicFoot() {
        // Given
        SpecificVolume initialSpecificVolInFT = SpecificVolume.ofCubicFootPerPound(1.0);

        // When
        SpecificVolume actualInCubicMeter = initialSpecificVolInFT.toBaseUnit();
        SpecificVolume actualInFT = actualInCubicMeter.toUnit(SpecificVolumeUnits.CUBIC_FOOT_PER_POUND);

        // Then
        SpecificVolume expectedInCubicMeter = SpecificVolume.ofCubicMeterPerKilogram(0.0624279606);
        assertThat(actualInCubicMeter.getValue()).isEqualTo(expectedInCubicMeter.getValue(), withPrecision(1E-8));
        assertThat(actualInFT).isEqualTo(initialSpecificVolInFT);
    }

    @Test
    @DisplayName("should convert to m³/kg from gal_US/lb and vice versa")
    void shouldProperlyConvertToCubicMeterPerKilogramFromGallonUS() {
        // Given
        SpecificVolume initialSpecificVolInGal = SpecificVolume.ofGallonUSPerPound(1.0);

        // When
        SpecificVolume actualInCubicMeter = initialSpecificVolInGal.toBaseUnit();
        SpecificVolume actualInGal = actualInCubicMeter.toUnit(SpecificVolumeUnits.GALLON_US_PER_POUND);

        // Then
        SpecificVolume expectedInCubicMeter = SpecificVolume.ofCubicMeterPerKilogram(0.0083454045);
        assertThat(actualInCubicMeter.getValue()).isEqualTo(expectedInCubicMeter.getValue(), withPrecision(1E-8));
        assertThat(actualInGal).isEqualTo(initialSpecificVolInGal);
    }

    @Test
    @DisplayName("should convert to m³/kg from gal_UK/lb and vice versa")
    void shouldProperlyConvertToCubicMeterPerKilogramFromGallonUK() {
        // Given
        SpecificVolume initialSpecificVolInGal = SpecificVolume.ofGallonUKPerPound(1.0);

        // When
        SpecificVolume actualInCubicMeter = initialSpecificVolInGal.toBaseUnit();
        SpecificVolume actualInGal = actualInCubicMeter.toUnit(SpecificVolumeUnits.GALLON_UK_PER_POUND);

        // Then
        SpecificVolume expectedInCubicMeter = SpecificVolume.ofCubicMeterPerKilogram(0.0100224129);
        assertThat(actualInCubicMeter.getValue()).isEqualTo(expectedInCubicMeter.getValue(), withPrecision(1E-8));
        assertThat(actualInGal).isEqualTo(initialSpecificVolInGal);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        SpecificVolume expected = SpecificVolume.ofLiterPerKilogram(1.0);

        // When
        SpecificVolume actual = expected.toCubicMeterPerKilogram()
                .toCubicCentimeterPerKilogram()
                .toCubicFootPerPound()
                .toLiterPerKilogram();
        double actualValue = expected.getInLiterPerKilogram();

        // Then
        assertThat(actual.getValue()).isEqualTo(expected.getValue(), withPrecision(1E-10));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

    @Test
    @DisplayName("should handle all unit conversions correctly")
    void shouldHandleAllUnitConversionsCorrectly() {
        // Given
        SpecificVolume oneCubicMeter = SpecificVolume.ofCubicMeterPerKilogram(1.0);

        // When/Then
        assertThat(oneCubicMeter.getInCubicMeterPerKilogram()).isEqualTo(1.0);
        assertThat(oneCubicMeter.getInCubicDecimeterPerKilogram()).isCloseTo(1000.0, withPrecision(1E-10));
        assertThat(oneCubicMeter.getInLiterPerKilogram()).isCloseTo(1000.0, withPrecision(1E-10));
        assertThat(oneCubicMeter.getInCubicCentimeterPerKilogram()).isCloseTo(1000000.0, withPrecision(1E-10));
        assertThat(oneCubicMeter.getInMilliliterPerKilogram()).isCloseTo(1000000.0, withPrecision(1E-10));
        assertThat(oneCubicMeter.getInHectoliterPerKilogram()).isCloseTo(10.0, withPrecision(1E-10));
        assertThat(oneCubicMeter.getInCubicFootPerPound()).isCloseTo(16.01846337, withPrecision(1E-5));
        assertThat(oneCubicMeter.getInGallonUSPerPound()).isCloseTo(119.826427, withPrecision(1E-5));
        assertThat(oneCubicMeter.getInGallonUKPerPound()).isCloseTo(99.77637221, withPrecision(1E-5));
    }

    @Test
    @DisplayName("should parse from symbol correctly")
    void shouldParseFromSymbolCorrectly() {
        // Given/When
        SpecificVolume vol1 = SpecificVolume.of(1.0, "m³/kg");
        SpecificVolume vol2 = SpecificVolume.of(1.0, "L/kg");
        SpecificVolume vol3 = SpecificVolume.of(1.0, "ft³/lb");

        // Then
        assertThat(vol1.getUnit()).isEqualTo(SpecificVolumeUnits.CUBIC_METER_PER_KILOGRAM);
        assertThat(vol2.getUnit()).isEqualTo(SpecificVolumeUnits.LITER_PER_KILOGRAM);
        assertThat(vol3.getUnit()).isEqualTo(SpecificVolumeUnits.CUBIC_FOOT_PER_POUND);
    }

    @Test
    @DisplayName("should handle equality comparisons across different units")
    void shouldHandleEqualityComparisonsAcrossDifferentUnits() {
        // Given
        SpecificVolume vol1 = SpecificVolume.ofCubicMeterPerKilogram(0.001);
        SpecificVolume vol2 = SpecificVolume.ofLiterPerKilogram(1.0);
        SpecificVolume vol3 = SpecificVolume.ofCubicCentimeterPerKilogram(1000.0);

        // When/Then
        assertThat(vol1).isEqualTo(vol2);
        assertThat(vol1).isEqualTo(vol3);
        assertThat(vol2).isEqualTo(vol3);
    }

    @Test
    @DisplayName("should return correct toString representation")
    void shouldReturnCorrectToStringRepresentation() {
        // Given
        SpecificVolume vol = SpecificVolume.ofCubicMeterPerKilogram(1.5);

        // When
        String result = vol.toString();

        // Then
        assertThat(result).contains("1.5");
        assertThat(result).contains("m³/kg");
    }
}
