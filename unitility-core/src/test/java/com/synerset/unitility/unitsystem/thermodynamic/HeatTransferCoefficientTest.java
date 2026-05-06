package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class HeatTransferCoefficientTest {

    @Test
    @DisplayName("should identify W/(m²·K) as base unit")
    void shouldIdentifyBaseUnit() {
        // Given & When
        HeatTransferCoefficientUnit baseUnit = HeatTransferCoefficientUnits.WATTS_PER_SQUARE_METER_KELVIN;

        // Then
        assertThat(baseUnit.getBaseUnit()).isEqualTo(baseUnit);
    }

    @Test
    @DisplayName("should convert between W/(m²·K) and kW/(m²·K)")
    void shouldConvertBetweenUnits() {
        // Given
        HeatTransferCoefficient h = HeatTransferCoefficient.ofWattsPerSquareMeterKelvin(100.0);

        // When
        HeatTransferCoefficient inKw = h.toKilowattsPerSquareMeterKelvin();

        // Then
        assertThat(inKw.getInKilowattsPerSquareMeterKelvin()).isCloseTo(0.1, within(1E-10));
        assertThat(inKw.toWattsPerSquareMeterKelvin().getInWattsPerSquareMeterKelvin())
                .isCloseTo(100.0, within(1E-10));
    }

    @Test
    @DisplayName("should consider equal coefficients in different units")
    void shouldBeEqualAcrossUnits() {
        // Given
        HeatTransferCoefficient inW = HeatTransferCoefficient.ofWattsPerSquareMeterKelvin(100.0);
        HeatTransferCoefficient inKW = HeatTransferCoefficient.ofKilowattsPerSquareMeterKelvin(0.1);

        // Then
        assertThat(inW).isEqualTo(inKW);
    }

    @Test
    @DisplayName("should convert to base unit correctly")
    void shouldConvertToBaseUnit() {
        // Given
        HeatTransferCoefficient h = HeatTransferCoefficient.ofKilowattsPerSquareMeterKelvin(0.5);

        // When
        HeatTransferCoefficient base = h.toBaseUnit();

        // Then
        assertThat(base.getUnit()).isEqualTo(HeatTransferCoefficientUnits.WATTS_PER_SQUARE_METER_KELVIN);
        assertThat(base.getValue()).isCloseTo(500.0, within(1E-10));
    }

    @Test
    @DisplayName("should convert between W/(m²·K) and BTU/(h·ft²·°F)")
    void shouldConvertToImperialBTUPerHourSquareFootFahrenheit() {
        // Given
        HeatTransferCoefficient inBTU = HeatTransferCoefficient.ofBTUPerHourSquareFootFahrenheit(1.0);
        double expectedW = inBTU.toWattsPerSquareMeterKelvin().getInWattsPerSquareMeterKelvin();

        // When
        HeatTransferCoefficient h = HeatTransferCoefficient.ofWattsPerSquareMeterKelvin(expectedW);
        HeatTransferCoefficient roundtrip = h.toBTUPerHourSquareFootFahrenheit();

        // Then
        assertThat(roundtrip.getInBTUPerHourSquareFootFahrenheit()).isCloseTo(1.0, within(1E-5));
    }

    @Test
    @DisplayName("should convert between W/(m²·K) and BTU/(min·ft²·°F)")
    void shouldConvertToImperialBTUPerMinuteSquareFootFahrenheit() {
        // Given
        HeatTransferCoefficient inBTU = HeatTransferCoefficient.ofBTUPerMinuteSquareFootFahrenheit(1.0);
        double expectedW = inBTU.toWattsPerSquareMeterKelvin().getInWattsPerSquareMeterKelvin();

        // When
        HeatTransferCoefficient h = HeatTransferCoefficient.ofWattsPerSquareMeterKelvin(expectedW);
        HeatTransferCoefficient roundtrip = h.toBTUPerMinuteSquareFootFahrenheit();

        // Then
        assertThat(roundtrip.getInBTUPerMinuteSquareFootFahrenheit()).isCloseTo(1.0, within(1E-5));
    }

    @Test
    @DisplayName("should consider equal coefficients across metric and imperial units")
    void shouldBeEqualAcrossMetricAndImperial() {
        // Given
        HeatTransferCoefficient inBTU = HeatTransferCoefficient.ofBTUPerHourSquareFootFahrenheit(1.0);
        double expectedW = inBTU.toWattsPerSquareMeterKelvin().getInWattsPerSquareMeterKelvin();
        HeatTransferCoefficient inW = HeatTransferCoefficient.ofWattsPerSquareMeterKelvin(expectedW);

        // Then
        assertThat(inW).isEqualTo(inBTU);
    }

    @Test
    @DisplayName("should consider equal coefficients across metric and imperial minute units")
    void shouldBeEqualAcrossMetricAndImperialMinute() {
        // Given
        HeatTransferCoefficient inBTU = HeatTransferCoefficient.ofBTUPerMinuteSquareFootFahrenheit(1.0);
        double expectedW = inBTU.toWattsPerSquareMeterKelvin().getInWattsPerSquareMeterKelvin();
        HeatTransferCoefficient inW = HeatTransferCoefficient.ofWattsPerSquareMeterKelvin(expectedW);

        // Then
        assertThat(inW).isEqualTo(inBTU);
    }
}
