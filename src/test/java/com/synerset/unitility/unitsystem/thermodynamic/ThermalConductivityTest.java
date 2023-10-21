package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class ThermalConductivityTest {

    @Test
    @DisplayName("should convert W/(m·K) to kW/(m·K) and vice versa")
    void shouldProperlyConvertWattsPerMeterKelvinToKilowattsPerMeterKelvin() {
        // Given
        ThermalConductivity initialUnits = ThermalConductivity.ofWattsPerMeterKelvin(1000);

        // When
        ThermalConductivity actual_KW_PER_M_K = initialUnits.toUnit(ThermalConductivityUnits.KILOWATTS_PER_METER_KELVIN);
        double actual_KW_PER_M_KVal = initialUnits.getInKilowattsPerMeterKelvin();
        ThermalConductivity actual_W_PER_M_K = actual_KW_PER_M_K.toBaseUnit();
        double actual_W_PER_M_KVal = actual_KW_PER_M_K.getInWattsPerMeterKelvin();

        // Then
        ThermalConductivity expected_KW_PER_M_K = ThermalConductivity.ofKilowattsPerMeterKelvin(1);
        assertThat(actual_KW_PER_M_K.getValue()).isEqualTo(actual_KW_PER_M_KVal);
        assertThat(actual_W_PER_M_K.getValue()).isEqualTo(actual_W_PER_M_KVal);
        assertThat(actual_KW_PER_M_K.getValue()).isEqualTo(expected_KW_PER_M_K.getValue(), withPrecision(1E-10));
        assertThat(actual_W_PER_M_K.getValue()).isEqualTo(1000, withPrecision(1E-10));
    }

    @Test
    @DisplayName("should convert W/(m·K) to BTU/(hr·ft·°F) and vice versa")
    void shouldProperlyConvertWattsPerMeterKelvinToBTUPerHourFootFahrenheit() {
        // Given
        ThermalConductivity initialUnits = ThermalConductivity.ofWattsPerMeterKelvin(1000);

        // When
        ThermalConductivity actual_BTU_PER_HR_FT_F = initialUnits.toUnit(ThermalConductivityUnits.BTU_PER_HOUR_FOOT_FAHRENHEIT);
        double actual_BTU_PER_HR_FT_FVal = initialUnits.getInBTUsPerHourFeetFahrenheit();
        ThermalConductivity actual_W_PER_M_K = actual_BTU_PER_HR_FT_F.toBaseUnit();

        // Then
        ThermalConductivity expected_BTU_PER_HR_FT_F = ThermalConductivity.ofBTUPerHourFeetFahrenheit(577.7891109505047);
        assertThat(actual_BTU_PER_HR_FT_F.getValue()).isEqualTo(actual_BTU_PER_HR_FT_FVal);
        assertThat(actual_BTU_PER_HR_FT_F.getValue()).isEqualTo(expected_BTU_PER_HR_FT_F.getValue(), withPrecision(1E-10));
        assertThat(actual_W_PER_M_K.getValue()).isEqualTo(1000, withPrecision(1E-10));
    }

    @Test
    @DisplayName("should have W/(m·K) as base unit")
    void shouldHaveWattPerMeterKelvinAsBaseUnit() {
        // Given
        ThermalConductivityUnits expectedBaseUnit = ThermalConductivityUnits.WATTS_PER_METER_KELVIN;

        // When
        ThermalConductivity thermalConductivityInBTUPerHrF = ThermalConductivity.ofBTUPerHourFeetFahrenheit(0.1);
        ThermalConductivityUnits actualBaseUnit = thermalConductivityInBTUPerHrF.getUnitType().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        ThermalConductivity expected = ThermalConductivity.ofWattsPerMeterKelvin(10.1);

        // When
        ThermalConductivity actual = expected.toKilowattsPerMeterKelvin()
                .toBTUPerHourFeetFahrenheit()
                .toWattsPerMeterKelvin();

        double actualValue = expected.getInWattsPerMeterKelvin();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}
