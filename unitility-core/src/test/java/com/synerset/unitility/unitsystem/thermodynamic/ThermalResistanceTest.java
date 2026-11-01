package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

class ThermalResistanceTest {

    @Test
    @DisplayName("should identify m²·K/W as base unit")
    void shouldIdentifyBaseUnit() {
        // Given
        ThermalResistance resistance = ThermalResistance.ofSquareFootFahrenheitHourPerBTU(10);

        // Then
        assertThat(resistance.getUnit().getBaseUnit()).isEqualTo(ThermalResistanceUnits.SQUARE_METER_KELVIN_PER_WATT);
    }

    @Test
    @DisplayName("should convert R-1 in US units to the hand value 0.1761102 m²·K/W")
    void shouldConvertUsRValueToSi() {
        // Given: 0.3048² m² × (1/1.8) K × 3600 s / 1055.05585262 J
        ThermalResistance rValue = ThermalResistance.ofSquareFootFahrenheitHourPerBTU(1.0);

        // When
        double inSi = rValue.getInSquareMeterKelvinPerWatt();

        // Then
        assertThat(inSi).isEqualTo(0.09290304 * 3600.0 / 1.8 / 1055.05585262, within(1E-13));
        assertThat(inSi).isEqualTo(0.1761102, within(1E-7));
    }

    @Test
    @DisplayName("should treat m²·°C/W as m²·K/W and kW-based unit as thousandth")
    void shouldConvertMetricUnits() {
        // Given
        ThermalResistance inKelvin = ThermalResistance.ofSquareMeterKelvinPerWatt(2.5);

        // Then
        assertThat(inKelvin.getInSquareMeterCelsiusPerWatt()).isEqualTo(2.5, within(1E-13));
        assertThat(inKelvin.getInSquareMeterKelvinPerKilowatt()).isEqualTo(2500.0, within(1E-9));
        assertThat(inKelvin).isEqualTo(ThermalResistance.ofSquareMeterCelsiusPerWatt(2.5));
        assertThat(inKelvin).isEqualTo(ThermalResistance.ofSquareMeterKelvinPerKilowatt(2500.0));
    }

    @Test
    @DisplayName("should round trip through every unit")
    void shouldRoundTripThroughEveryUnit() {
        // Given
        ThermalResistance initial = ThermalResistance.ofSquareMeterKelvinPerWatt(3.7);

        for (ThermalResistanceUnits unit : ThermalResistanceUnits.values()) {
            // When
            ThermalResistance back = initial.toUnit(unit).toBaseUnit();

            // Then
            assertThat(back.getValue()).isEqualTo(3.7, within(1E-12));
        }
    }

    @Test
    @DisplayName("should parse every unit symbol and a spelling without special characters")
    void shouldParseEverySymbol() {
        for (ThermalResistanceUnits unit : ThermalResistanceUnits.values()) {
            assertThat(ThermalResistanceUnits.fromSymbol(unit.getSymbol())).isEqualTo(unit);
            assertThat(ThermalResistance.of(1.0, unit.getSymbol()).getUnit()).isEqualTo(unit);
        }
        assertThat(ThermalResistanceUnits.fromSymbol("m2K/W")).isEqualTo(ThermalResistanceUnits.SQUARE_METER_KELVIN_PER_WATT);
        assertThat(ThermalResistanceUnits.fromSymbol("m2*C/W")).isEqualTo(ThermalResistanceUnits.SQUARE_METER_CELSIUS_PER_WATT);
        assertThat(ThermalResistanceUnits.fromSymbol("ft2·F·h/btu"))
                .isEqualTo(ThermalResistanceUnits.SQUARE_FOOT_FAHRENHEIT_HOUR_PER_BTU);
        assertThat(ThermalResistanceUnits.fromSymbol(null)).isEqualTo(ThermalResistanceUnits.SQUARE_METER_KELVIN_PER_WATT);
        assertThatThrownBy(() -> ThermalResistanceUnits.fromSymbol("bogus")).isInstanceOf(UnitSystemParseException.class);
    }

    @Test
    @DisplayName("should be the reciprocal of the heat transfer coefficient")
    void shouldBeReciprocalOfHeatTransferCoefficient() {
        // Given
        HeatTransferCoefficient u = HeatTransferCoefficient.ofWattsPerSquareMeterKelvin(0.25);

        // When
        ThermalResistance r = u.toThermalResistance();

        // Then
        assertThat(r.getInSquareMeterKelvinPerWatt()).isEqualTo(4.0, within(1E-13));
        assertThat(r.toHeatTransferCoefficient()).isEqualTo(u);
        assertThat(ThermalResistance.ofSquareFootFahrenheitHourPerBTU(1.0).toHeatTransferCoefficient()
                .getInBTUPerHourSquareFootFahrenheit()).isEqualTo(1.0, within(1E-12));
    }

    @Test
    @DisplayName("should be available from the default parsing factory")
    void shouldBeAvailableFromParsingFactory() {
        // When
        ThermalResistance parsed = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
                .parseValueAndSymbol(ThermalResistance.class, 5.0, "ft²·°F·h/BTU");
        ThermalResistance fromText = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
                .parse(ThermalResistance.class, "5.0[ft²·°F·h/BTU]");

        // Then
        assertThat(parsed).isEqualTo(ThermalResistance.ofSquareFootFahrenheitHourPerBTU(5.0));
        assertThat(fromText).isEqualTo(parsed);
    }
}
