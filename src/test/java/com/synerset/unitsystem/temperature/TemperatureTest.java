package com.synerset.unitsystem.temperature;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class TemperatureTest {

    // Given
    private static final double TEST_TEMP_VALUE = 293.15;
    private static final Temperature TEST_KELVIN_TEMP = Temperature.kelvin(TEST_TEMP_VALUE).getOrElseThrow(
            () -> new IllegalStateException("Unit tests: Invalid temperature"));

    private static final double MATH_ACCURACY = 1E-13;

    @Test
    @DisplayName("should convert Kelvin to Celsius and vice versa")
    void shouldConvertKelvinToCelsiusAndViceVersa() {
        // When
        Temperature actualUnitCelsius = TEST_KELVIN_TEMP.toCelsius();
        double actualValueInCelsius = actualUnitCelsius.getValue();

        Temperature actualUnitKelvin = actualUnitCelsius.toKelvin();
        double actualValueInKelvin = actualUnitKelvin.getValue();

        // Then
        assertThat(actualValueInKelvin).isEqualTo(TEST_TEMP_VALUE);
        double expectedValueInCelsius = 20.0;
        assertThat(actualValueInCelsius).isEqualTo(expectedValueInCelsius);
    }

    @Test
    @DisplayName("should convert Kelvin to Fahrenheit and vice versa")
    void shouldConvertKelvinsToFahrenheitAndViceVersa() {
        // When
        Temperature actualUnitFahrenheit = TEST_KELVIN_TEMP.toFahrenheit();
        double actualValueInFahrenheit = actualUnitFahrenheit.getValue();

        Temperature actualUnitKelvin = actualUnitFahrenheit.toKelvin();
        double actualValueInKelvin = actualUnitKelvin.getValue();

        // Then
        assertThat(actualValueInKelvin).isEqualTo(TEST_TEMP_VALUE);
        double expectedValueInFahrenheit = 68.0;
        assertThat(actualValueInFahrenheit).isEqualTo(expectedValueInFahrenheit, withPrecision(MATH_ACCURACY));
    }


}