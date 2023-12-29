package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class TemperatureTest {

    @Test
    @DisplayName("should convert to K from °C and vice versa")
    void shouldProperlyConvertToKelvinFromCelsius() {
        // Given
        Temperature initialTempInCelsius = Temperature.ofCelsius(20.5);

        // When
        Temperature actualInKelvins = initialTempInCelsius.toBaseUnit();
        Temperature actualInCelsius = actualInKelvins.toUnit(TemperatureUnits.CELSIUS);
        double actualInKelvinsVal = initialTempInCelsius.getInKelvins();
        double actualInCelsiusVal = actualInKelvins.getInCelsius();

        // Then
        Temperature expectedInKelvins = Temperature.ofKelvins(20.5 + 273.15);
        assertThat(actualInKelvins.getValue()).isEqualTo(actualInKelvinsVal);
        assertThat(actualInCelsius.getValue()).isEqualTo(actualInCelsiusVal);
        assertThat(actualInKelvins).isEqualTo(expectedInKelvins);
        assertThat(actualInCelsius).isEqualTo(initialTempInCelsius);
    }

    @Test
    @DisplayName("should convert to K from F and vice versa")
    void shouldProperlyConvertToKelvinFromFahrenheit() {
        // Given
        Temperature initialTempInKelvin = Temperature.ofKelvins(273.15 + 20.5);

        // When
        Temperature actualInFahrenheit = initialTempInKelvin.toUnit(TemperatureUnits.FAHRENHEIT);
        Temperature actualInKelvin = actualInFahrenheit.toBaseUnit();
        double actualInFahrenheitVal = actualInFahrenheit.getInFahrenheits();

        // Then
        Temperature expectedInFahrenheit = Temperature.ofFahrenheit(68.9);
        assertThat(actualInFahrenheit.getValue()).isEqualTo(actualInFahrenheitVal);
        assertThat(actualInFahrenheit).isEqualTo(expectedInFahrenheit);
        assertThat(actualInKelvin).isEqualTo(initialTempInKelvin);
    }

    @Test
    @DisplayName("should have K as base unit")
    void shouldHaveKelvinAsBaseUnit() {
        // Given
        TemperatureUnit expectedBaseUnit = TemperatureUnits.KELVIN;

        // When
        Temperature specificHeatInFahrenheit = Temperature.ofFahrenheit(10);
        TemperatureUnit actualBaseUnit = specificHeatInFahrenheit.getUnitType().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Temperature expected = Temperature.ofKelvins(10.1);

        // When
        Temperature actual = expected.toCelsius()
                .toFahrenheit()
                .toKelvin();

        double actualValue = expected.getInKelvins();

        // Then
        assertThat(actual.getValue()).isEqualTo(expected.getValue(), withPrecision(1E-13));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}