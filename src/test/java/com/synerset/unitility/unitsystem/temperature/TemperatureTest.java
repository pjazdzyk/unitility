package com.synerset.unitility.unitsystem.temperature;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TemperatureTest {

    @Test
    @DisplayName("should convert to Kelvin from Celsius and vice versa")
    void shouldProperlyConvertToKelvinFromCelsius() {
        // Given
        Temperature initialTempInCelsius = Temperature.ofCelsius(20.5);

        // When
        Temperature actualInKelvins = initialTempInCelsius.toBaseUnit();
        Temperature actualInCelsius = actualInKelvins.toUnit(TemperatureUnits.CELSIUS);

        // Then
        Temperature expectedInKelvins = Temperature.ofKelvins(20.5 + 273.15);
        assertThat(actualInKelvins).isEqualTo(expectedInKelvins);
        assertThat(actualInCelsius).isEqualTo(initialTempInCelsius);
    }

    @Test
    @DisplayName("should convert to Kelvin from Fahrenheit and vice versa")
    void shouldProperlyConvertToKelvinFromFahrenheit() {
        // Given
        Temperature initialTempInKelvin = Temperature.ofKelvins(273.15 + 20.5);

        // When
        Temperature actualInFahrenheit = initialTempInKelvin.toFahrenheit();
        Temperature actualInKelvin = actualInFahrenheit.toBaseUnit();

        // Then
        Temperature expectedInFahrenheit = Temperature.ofFahrenheit(68.9);
        assertThat(actualInFahrenheit).isEqualTo(expectedInFahrenheit);
        assertThat(actualInKelvin).isEqualTo(initialTempInKelvin);
    }

}