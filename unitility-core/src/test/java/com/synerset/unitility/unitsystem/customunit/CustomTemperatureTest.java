package com.synerset.unitility.unitsystem.customunit;

import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomTemperatureTest {

    @Test
    @DisplayName("should create and convert custom temperature")
    void customTemperature_shouldCreateAndConvertCustomTemperature(){
        // Given
        Temperature temperatureInC = Temperature.ofKelvins(100);                    // 100.00 K
        Temperature temperatureInR = Temperature.of(200, CustomTempUnits.RANKINE);  // 111.11 K

        // When
        Temperature totalTemperature = temperatureInC.plus(temperatureInR);

        // Then
        assertThat(totalTemperature).isEqualTo(Temperature.ofKelvins(100 + 111.11111111111111));

    }
}