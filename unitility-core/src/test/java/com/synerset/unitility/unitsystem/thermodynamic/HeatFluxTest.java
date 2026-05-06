package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class HeatFluxTest {

    @Test
    @DisplayName("should identify W/m² as base unit")
    void shouldIdentifyBaseUnit() {
        // Given & When
        HeatFluxUnit baseUnit = HeatFluxUnits.WATTS_PER_SQUARE_METER;

        // Then
        assertThat(baseUnit.getBaseUnit()).isEqualTo(baseUnit);
    }

    @Test
    @DisplayName("should convert between W/m² and kW/m²")
    void shouldConvertBetweenUnits() {
        // Given
        HeatFlux q = HeatFlux.ofWattsPerSquareMeter(1000.0);

        // When
        HeatFlux inKw = q.toKilowattsPerSquareMeter();

        // Then
        assertThat(inKw.getInKilowattsPerSquareMeter()).isCloseTo(1.0, within(1E-10));
        assertThat(inKw.toWattsPerSquareMeter().getInWattsPerSquareMeter())
                .isCloseTo(1000.0, within(1E-10));
    }

    @Test
    @DisplayName("should consider equal heat fluxes in different units")
    void shouldBeEqualAcrossUnits() {
        // Given
        HeatFlux inW = HeatFlux.ofWattsPerSquareMeter(500.0);
        HeatFlux inKW = HeatFlux.ofKilowattsPerSquareMeter(0.5);

        // Then
        assertThat(inW).isEqualTo(inKW);
    }

    @Test
    @DisplayName("should convert to base unit correctly")
    void shouldConvertToBaseUnit() {
        // Given
        HeatFlux q = HeatFlux.ofKilowattsPerSquareMeter(2.5);

        // When
        HeatFlux base = q.toBaseUnit();

        // Then
        assertThat(base.getUnit()).isEqualTo(HeatFluxUnits.WATTS_PER_SQUARE_METER);
        assertThat(base.getValue()).isCloseTo(2500.0, within(1E-10));
    }

    @Test
    @DisplayName("should convert between W/m² and BTU/(h·ft²)")
    void shouldConvertToImperialBTUPerHourSquareFoot() {
        // Given
        HeatFlux inBTU = HeatFlux.ofBTUPerHourSquareFoot(1.0);
        double expectedW = inBTU.toWattsPerSquareMeter().getInWattsPerSquareMeter();

        // When
        HeatFlux q = HeatFlux.ofWattsPerSquareMeter(expectedW);
        HeatFlux roundtrip = q.toBTUPerHourSquareFoot();

        // Then
        assertThat(roundtrip.getInBTUPerHourSquareFoot()).isCloseTo(1.0, within(1E-6));
    }

    @Test
    @DisplayName("should convert between W/m² and BTU/(min·ft²)")
    void shouldConvertToImperialBTUPerMinuteSquareFoot() {
        // Given
        HeatFlux inBTU = HeatFlux.ofBTUPerMinuteSquareFoot(1.0);
        double expectedW = inBTU.toWattsPerSquareMeter().getInWattsPerSquareMeter();

        // When
        HeatFlux q = HeatFlux.ofWattsPerSquareMeter(expectedW);
        HeatFlux roundtrip = q.toBTUPerMinuteSquareFoot();

        // Then
        assertThat(roundtrip.getInBTUPerMinuteSquareFoot()).isCloseTo(1.0, within(1E-6));
    }

    @Test
    @DisplayName("should consider equal heat fluxes across metric and imperial units")
    void shouldBeEqualAcrossMetricAndImperial() {
        // Given
        HeatFlux inBTU = HeatFlux.ofBTUPerHourSquareFoot(1.0);
        double expectedW = inBTU.toWattsPerSquareMeter().getInWattsPerSquareMeter();
        HeatFlux inW = HeatFlux.ofWattsPerSquareMeter(expectedW);

        // Then
        assertThat(inW).isEqualTo(inBTU);
    }

    @Test
    @DisplayName("should consider equal heat fluxes across metric and imperial minute units")
    void shouldBeEqualAcrossMetricAndImperialMinute() {
        // Given
        HeatFlux inBTU = HeatFlux.ofBTUPerMinuteSquareFoot(1.0);
        double expectedW = inBTU.toWattsPerSquareMeter().getInWattsPerSquareMeter();
        HeatFlux inW = HeatFlux.ofWattsPerSquareMeter(expectedW);

        // Then
        assertThat(inW).isEqualTo(inBTU);
    }
}
