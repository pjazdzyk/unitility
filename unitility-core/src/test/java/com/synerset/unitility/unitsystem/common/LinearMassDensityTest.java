package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class LinearMassDensityTest {

    @Test
    @DisplayName("should convert to kg/m from lb/ft and vice versa")
    void shouldProperlyConvertToKilogramsPerMeterFromPoundsPerFoot() {
        // Given
        LinearMassDensity initialValue = LinearMassDensity.ofPoundsPerFoot(10.0);

        // When
        LinearMassDensity actualInKgPerM = initialValue.toUnit(LinearMassDensityUnits.KILOGRAM_PER_METER);
        LinearMassDensity actualInPoundsPerFoot = actualInKgPerM.toUnit(LinearMassDensityUnits.POUND_PER_FOOT);
        double actualInPoundsPerFootVal = actualInKgPerM.getInPoundsPerFoot();

        // Then
        LinearMassDensity expectedInKgPerM = LinearMassDensity.ofKilogramsPerMeter(14.88163943569554);
        assertThat(actualInPoundsPerFoot.getValue()).isEqualTo(actualInPoundsPerFootVal);
        assertThat(actualInKgPerM.getValue()).isEqualTo(expectedInKgPerM.getValue(), withPrecision(1E-3));
        assertThat(actualInPoundsPerFoot.getInPoundsPerFoot()).isEqualTo(initialValue.getInPoundsPerFoot(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("should convert to kg/m from oz/ft and vice versa")
    void shouldProperlyConvertToKilogramsPerMeterFromOuncesPerFoot() {
        // Given
        LinearMassDensity initialValue = LinearMassDensity.ofOuncesPerFoot(1);

        // When
        LinearMassDensity actualInKgPerM = initialValue.toUnit(LinearMassDensityUnits.KILOGRAM_PER_METER);
        LinearMassDensity actualInOuncesPerFoot = actualInKgPerM.toUnit(LinearMassDensityUnits.OUNCE_PER_FOOT);
        double actualInOuncesPerFootVal = actualInKgPerM.getInOuncesPerFoot();

        // Then
        LinearMassDensity expectedInKgPerM = LinearMassDensity.ofKilogramsPerMeter(0.093010246);
        assertThat(actualInOuncesPerFoot.getValue()).isEqualTo(actualInOuncesPerFootVal);
        assertThat(actualInKgPerM.getValue()).isEqualTo(expectedInKgPerM.getValue(), withPrecision(1E-3));
        assertThat(actualInOuncesPerFoot).isEqualTo(initialValue);
    }

    @Test
    @DisplayName("should convert to kg/m from t/m and vice versa")
    void shouldProperlyConvertToKilogramsPerMeterFromTonnesPerMeter() {
        // Given
        LinearMassDensity initialValue = LinearMassDensity.ofTonnesPerMeter(1);

        // When
        LinearMassDensity actualInKgPerM = initialValue.toUnit(LinearMassDensityUnits.KILOGRAM_PER_METER);
        LinearMassDensity actualInTonnesPerMeter = actualInKgPerM.toUnit(LinearMassDensityUnits.TONNE_PER_METER);
        double actualInTonnesPerMeterVal = actualInKgPerM.getInTonnesPerMeter();

        // Then
        LinearMassDensity expectedInKgPerM = LinearMassDensity.ofKilogramsPerMeter(1000.0);
        assertThat(actualInTonnesPerMeter.getValue()).isEqualTo(actualInTonnesPerMeterVal);
        assertThat(actualInKgPerM.getValue()).isEqualTo(expectedInKgPerM.getValue(), withPrecision(1E-3));
        assertThat(actualInTonnesPerMeter).isEqualTo(initialValue);
    }

    @Test
    @DisplayName("should have kg/m as base unit")
    void shouldHaveKilogramPerMeterAsBaseUnit() {
        // Given
        LinearMassDensityUnit expectedBaseUnit = LinearMassDensityUnits.KILOGRAM_PER_METER;

        // When
        LinearMassDensity linearMassDensity = LinearMassDensity.ofPoundsPerFoot(10);
        LinearMassDensityUnit actualBaseUnit = linearMassDensity.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        LinearMassDensity expected = LinearMassDensity.ofKilogramsPerMeter(10.1);

        // When
        LinearMassDensity actual = expected.toPoundPerFoot()
                .toOuncePerFoot()
                .toTonnePerMeter()
                .toKilogramPerMeter();

        double actualValue = expected.getInKilogramsPerMeter();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }
}
