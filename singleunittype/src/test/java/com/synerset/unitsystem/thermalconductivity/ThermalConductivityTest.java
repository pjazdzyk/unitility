package com.synerset.unitsystem.thermalconductivity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class ThermalConductivityTest {

    @Test
    @DisplayName("should convert W/(m·K) to kW/(m·K) and vice versa")
    public void shouldProperlyConvertWattsPerMeterKelvinToKilowattsPerMeterKelvin() {
        // Given
        ThermalConductivity initialUnits = ThermalConductivity.ofWattsPerMeterKelvin(1000);

        // When
        ThermalConductivity actual_KW_PER_M_K = initialUnits.toKilowattsPerMeterKelvin();
        ThermalConductivity actual_W_PER_M_K = actual_KW_PER_M_K.toWattsPerMeterKelvin();

        // Then
        ThermalConductivity expected_KW_PER_M_K = ThermalConductivity.ofKilowattsPerMeterKelvin(1);
        assertThat(actual_KW_PER_M_K.getValue()).isEqualTo(expected_KW_PER_M_K.getValue(), withPrecision(1E-10));
        assertThat(actual_W_PER_M_K.getValue()).isEqualTo(1000, withPrecision(1E-10));
    }

    @Test
    @DisplayName("should convert W/(m·K) to BTU/(hr·ft·°F) and vice versa")
    public void shouldProperlyConvertWattsPerMeterKelvinToBTUPerHourFootFahrenheit() {
        // Given
        ThermalConductivity initialUnits = ThermalConductivity.ofWattsPerMeterKelvin(1000);

        // When
        ThermalConductivity actual_BTU_PER_HR_FT_F = initialUnits.toBTUPerHourFeetFahrenheit();
        ThermalConductivity actual_W_PER_M_K = actual_BTU_PER_HR_FT_F.toWattsPerMeterKelvin();

        // Then
        ThermalConductivity expected_BTU_PER_HR_FT_F = ThermalConductivity.ofBTUPerHourFeetFahrenheit(577.7891109505047);
        assertThat(actual_BTU_PER_HR_FT_F.getValue()).isEqualTo(expected_BTU_PER_HR_FT_F.getValue(), withPrecision(1E-10));
        assertThat(actual_W_PER_M_K.getValue()).isEqualTo(1000, withPrecision(1E-10));
    }

}
