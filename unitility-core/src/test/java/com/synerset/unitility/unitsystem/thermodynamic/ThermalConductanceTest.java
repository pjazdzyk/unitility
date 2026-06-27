package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class ThermalConductanceTest {

    @Test
    @DisplayName("ThermalConductance: should have W/K as base unit")
    void shouldHaveWattsPerKelvinAsBaseUnit() {
        // Given
        ThermalConductanceUnit expectedBaseUnit = ThermalConductanceUnits.WATTS_PER_KELVIN;

        // When
        ThermalConductance quantity = ThermalConductance.ofKilowattsPerKelvin(10);
        ThermalConductanceUnit actualBaseUnit = quantity.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("ThermalConductance: should convert kW/K to W/K")
    void shouldConvertKilowattsToWatts() {
        // Given
        ThermalConductance initial = ThermalConductance.ofKilowattsPerKelvin(2.0);

        // When
        ThermalConductance inWatts = initial.toWattsPerKelvin();

        // Then
        assertThat(inWatts.getValue()).isEqualTo(2000.0, withPrecision(1E-9));
        assertThat(inWatts).isEqualTo(initial);
    }

    @Test
    @DisplayName("ThermalConductance: should convert BTU/(h·°F) to W/K")
    void shouldConvertImperialToBase() {
        // Given
        ThermalConductance oneBtu = ThermalConductance.ofBTUPerHourFahrenheit(1.0);

        // When
        double inWattsPerKelvin = oneBtu.getInWattsPerKelvin();

        // Then  1 BTU/(h·°F) = 0.29307107017 * 9/5 W/K ≈ 0.5275279
        assertThat(inWattsPerKelvin).isEqualTo(0.5275279263, withPrecision(1E-6));
    }
}
