package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

class LinearThermalResistanceTest {

    @Test
    @DisplayName("should identify m·K/W as base unit")
    void shouldIdentifyBaseUnit() {
        // Given
        LinearThermalResistance resistance = LinearThermalResistance.ofFootFahrenheitHourPerBTU(10);

        // Then
        assertThat(resistance.getUnit().getBaseUnit()).isEqualTo(LinearThermalResistanceUnits.METER_KELVIN_PER_WATT);
    }

    @Test
    @DisplayName("should convert 1 ft·°F·h/BTU to the hand value 0.5777893 m·K/W")
    void shouldConvertUsUnitToSi() {
        // Given: 0.3048 m × (1/1.8) K × 3600 s / 1055.05585262 J
        LinearThermalResistance resistance = LinearThermalResistance.ofFootFahrenheitHourPerBTU(1.0);

        // When
        double inSi = resistance.getInMeterKelvinPerWatt();

        // Then
        assertThat(inSi).isEqualTo(0.3048 * 3600.0 / 1.8 / 1055.05585262, within(1E-13));
        assertThat(inSi).isEqualTo(0.5777893, within(1E-7));
    }

    @Test
    @DisplayName("should treat m·°C/W as m·K/W")
    void shouldTreatCelsiusAsKelvin() {
        // Given
        LinearThermalResistance inKelvin = LinearThermalResistance.ofMeterKelvinPerWatt(2.5);

        // Then
        assertThat(inKelvin.getInMeterCelsiusPerWatt()).isEqualTo(2.5, within(1E-13));
        assertThat(inKelvin).isEqualTo(LinearThermalResistance.ofMeterCelsiusPerWatt(2.5));
    }

    @Test
    @DisplayName("should round trip through every unit")
    void shouldRoundTripThroughEveryUnit() {
        // Given
        LinearThermalResistance initial = LinearThermalResistance.ofMeterKelvinPerWatt(3.7);

        for (LinearThermalResistanceUnits unit : LinearThermalResistanceUnits.values()) {
            // When
            LinearThermalResistance back = initial.toUnit(unit).toBaseUnit();

            // Then
            assertThat(back.getValue()).isEqualTo(3.7, within(1E-12));
        }
    }

    @Test
    @DisplayName("should parse every unit symbol and a spelling without special characters")
    void shouldParseEverySymbol() {
        for (LinearThermalResistanceUnits unit : LinearThermalResistanceUnits.values()) {
            assertThat(LinearThermalResistanceUnits.fromSymbol(unit.getSymbol())).isEqualTo(unit);
            assertThat(LinearThermalResistance.of(1.0, unit.getSymbol()).getUnit()).isEqualTo(unit);
        }
        assertThat(LinearThermalResistanceUnits.fromSymbol("mK/W")).isEqualTo(LinearThermalResistanceUnits.METER_KELVIN_PER_WATT);
        assertThat(LinearThermalResistanceUnits.fromSymbol("m*C/W")).isEqualTo(LinearThermalResistanceUnits.METER_CELSIUS_PER_WATT);
        assertThat(LinearThermalResistanceUnits.fromSymbol("ft·F·h/btu"))
                .isEqualTo(LinearThermalResistanceUnits.FOOT_FAHRENHEIT_HOUR_PER_BTU);
        assertThat(LinearThermalResistanceUnits.fromSymbol(" ")).isEqualTo(LinearThermalResistanceUnits.METER_KELVIN_PER_WATT);
        assertThatThrownBy(() -> LinearThermalResistanceUnits.fromSymbol("bogus")).isInstanceOf(UnitSystemParseException.class);
    }

    @Test
    @DisplayName("should be available from the default parsing factory")
    void shouldBeAvailableFromParsingFactory() {
        // When
        LinearThermalResistance parsed = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
                .parse(LinearThermalResistance.class, "5.0[ft·°F·h/BTU]");

        // Then
        assertThat(parsed).isEqualTo(LinearThermalResistance.ofFootFahrenheitHourPerBTU(5.0));
        assertThat(PhysicalQuantityParsingFactory.getDefaultParsingFactory().getDefaultUnit(LinearThermalResistance.class))
                .isEqualTo(LinearThermalResistanceUnits.METER_KELVIN_PER_WATT);
    }
}
