package com.synerset.unitility.unitsystem.humidity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class HumidityRatioTest {

    @Test
    @DisplayName("should properly convert from kg/kg to lb/lb")
    void shouldProperlyConvertFromKgKgToLbLb() {
        // Given
        double initialValue = 0.015;
        HumidityRatio initialHumidityRatio = HumidityRatio.of(initialValue, HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);

        // When
        HumidityRatio actualInLbPerLb = initialHumidityRatio.toUnit(HumidityRatioUnits.POUND_PER_POUND);
        double actualInLbPerLbVal = initialHumidityRatio.getInPoundPerPound();
        HumidityRatio actualInKgPerKg = actualInLbPerLb.toBaseUnit();
        double actualInKgPerKgVal = actualInLbPerLb.getInKilogramPerKilogram();

        // Then
        HumidityRatio expectedInLbPerLb = HumidityRatio.of(0.033069339826536, HumidityRatioUnits.POUND_PER_POUND);
        assertThat(actualInKgPerKg.getValue()).isEqualTo(initialValue);
        assertThat(actualInLbPerLb.getValue()).isEqualTo(actualInLbPerLbVal);
        assertThat(actualInKgPerKg.getValue()).isEqualTo(actualInKgPerKgVal);
        assertThat(actualInLbPerLb.getValue()).isEqualTo(expectedInLbPerLb.getValue(), withPrecision(1E-9));
        assertThat(actualInKgPerKg.getUnit()).isEqualTo(HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);
        assertThat(initialHumidityRatio).isEqualTo(HumidityRatio.ofKilogramPerKilogram(initialValue));
    }

    @Test
    @DisplayName("should convert from g/kg to kg/kg and vice versa")
    void shouldProperlyConvertFromGkgToKgKgAndViceVersa() {
        // When
        double initialValue = 0.015;
        HumidityRatio initialHumidityRatio = HumidityRatio.of(initialValue, HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);

        // Then
        HumidityRatio actualInGPerKg = initialHumidityRatio.toUnit(HumidityRatioUnits.GRAM_PER_KILOGRAM);
        // Going back again to base value
        HumidityRatio actualInKgPerKg = actualInGPerKg.toBaseUnit();
        // Checking values
        double gramPerKilogram1 = actualInGPerKg.getGramPerKilogram();
        double gramPerKilogram2 = initialHumidityRatio.toGramPerKilogram().getValue();

        HumidityRatio expectedInGramPerKilogram = HumidityRatio.of(15, HumidityRatioUnits.GRAM_PER_KILOGRAM);
        assertThat(actualInKgPerKg.getValue()).isEqualTo(initialValue);
        assertThat(actualInGPerKg).isEqualTo(expectedInGramPerKilogram);
        assertThat(actualInKgPerKg.getValue()).isEqualTo(initialHumidityRatio.getValue());
        assertThat(gramPerKilogram1).isEqualTo(gramPerKilogram2);
    }

    @Test
    @DisplayName("should have kg/kg as base unit")
    void shouldHaveKilogramPerKilogramBaseUnit() {
        // Given
        HumidityRatioUnit expectedBaseUnit = HumidityRatioUnits.KILOGRAM_PER_KILOGRAM;

        // When
        HumidityRatio humidityKgPerKg = HumidityRatio.ofPoundPerPound(10);
        HumidityRatioUnit actualBaseUnit = humidityKgPerKg.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        HumidityRatio expected = HumidityRatio.ofKilogramPerKilogram(10.1);

        // When
        HumidityRatio actual = expected.toPoundPerPound()
                .toKilogramPerKilogram();

        double actualValue = expected.getInKilogramPerKilogram();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}