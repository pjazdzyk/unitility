package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class HeatCapacityTest {

    @Test
    @DisplayName("HeatCapacity: should have J/K as base unit")
    void shouldHaveJoulesPerKelvinAsBaseUnit() {
        // Given
        HeatCapacityUnit expectedBaseUnit = HeatCapacityUnits.JOULES_PER_KELVIN;

        // When
        HeatCapacity quantity = HeatCapacity.ofKilojoulesPerKelvin(10);
        HeatCapacityUnit actualBaseUnit = quantity.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("HeatCapacity: should convert kJ/K to J/K and back")
    void shouldConvertKilojoulesToJoules() {
        // Given
        HeatCapacity initial = HeatCapacity.ofKilojoulesPerKelvin(1.5);

        // When
        HeatCapacity inJoules = initial.toJoulesPerKelvin();
        HeatCapacity backToKj = inJoules.toKilojoulesPerKelvin();

        // Then
        assertThat(inJoules.getValue()).isEqualTo(1500.0, withPrecision(1E-9));
        assertThat(backToKj).isEqualTo(initial);
    }

    @Test
    @DisplayName("HeatCapacity: should convert BTU/°F to J/K")
    void shouldConvertImperialToBase() {
        // Given
        HeatCapacity oneBtu = HeatCapacity.ofBTUPerFahrenheit(1.0);

        // When
        double inJoulesPerKelvin = oneBtu.getInJoulesPerKelvin();

        // Then  1 BTU/°F = 1055.05585262 * 9/5 J/K ≈ 1899.1005
        assertThat(inJoulesPerKelvin).isEqualTo(1899.1005347, withPrecision(1E-3));
    }
}
