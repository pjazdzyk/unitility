package com.synerset.unitsystem.temperature;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TemperatureTest {

    // Given
    private static final Temperature TEST_KELVIN_TEMP = Temperature.kelvin(293.15).getOrElseThrow(
            () -> new IllegalStateException("Unit tests: Invalid temperature"));
    private static final double MATH_ACCURACY = 1E-13;

    @Test
    @DisplayName("should convert Kelvin to Celsius and vice versa")
    void shouldConvertKelvinToCelsiusAndViceVersa() {
        // When
        Celsius actualUnitCelsius = TEST_KELVIN_TEMP.toCelsius();
        Kelvin actualUnitKelvin = actualUnitCelsius.toKelvin();

        // Then
        assertThat(actualUnitKelvin).isEqualTo(TEST_KELVIN_TEMP);
        Celsius expectedValueInCelsius = Temperature.celsius(20.0);
        assertThat(actualUnitCelsius).isEqualTo(expectedValueInCelsius);
    }

    @Test
    @DisplayName("should convert Kelvin to Fahrenheit and vice versa")
    void shouldConvertKelvinsToFahrenheitAndViceVersa() {
        // When
        Fahrenheit actualUnitFahrenheit = TEST_KELVIN_TEMP.toFahrenheit();
        double actualValueInFahrenheit = actualUnitFahrenheit.getValue();
        Kelvin actualUnitKelvin = actualUnitFahrenheit.toKelvin();

        // Then
        assertThat(actualUnitKelvin).isEqualTo(TEST_KELVIN_TEMP);
        double expectedValueInFahrenheit = 68.0;
        assertThat(actualValueInFahrenheit).isEqualTo(expectedValueInFahrenheit, withPrecision(MATH_ACCURACY));
    }

    @Test
    @DisplayName("should return InvalidTemperature when negative temperature in Kelvin is given")
    void shouldReturnInvalidTemperatureWhenNegativeTempInKelvinIsGiven(){
        // Then
        assertThat(Temperature.kelvin(-1).getLeft()).isInstanceOf(InvalidTemperature.class);
    }

    @Test
    @DisplayName("should return InvalidTemperature when unphysical temperature in Celsius is given")
    void shouldReturnInvalidTemperatureWhenUnphysicalTempInCelsiusIsGiven(){
        // Then
        assertThatThrownBy(()->Temperature.celsius(-1000)).isInstanceOf(IllegalStateException.class);
    }
}