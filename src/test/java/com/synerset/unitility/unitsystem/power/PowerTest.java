package com.synerset.unitility.unitsystem.power;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class PowerTest {

    @Test
    @DisplayName("should convert W from BTU/hour and vice versa")
    public void shouldProperlyConvertWattsFromBTUPerHour() {
        // Given
        Power initialPower = Power.ofWatts(1000);

        // When
        Power actualInBTUPerHour = initialPower.toBTUPerHour();
        Power actualInWatts = actualInBTUPerHour.toWatts();

        // Then
        Power expectedInBTUPerHour = Power.ofBTUPerHour(3412.141633127942);
        assertThat(actualInBTUPerHour.getValue()).isEqualTo(expectedInBTUPerHour.getValue(), withPrecision(1E-14));
        assertThat(actualInWatts.getValue()).isEqualTo(1000, withPrecision(1E-9));
    }

    @Test
    @DisplayName("should convert W from kW and vice versa")
    public void shouldProperlyConvertWattsFromKiloWatts() {
        // Given
        Power initialPowerInWatts = Power.ofWatts(10_000);

        // When
        Power actualInKilowatts = initialPowerInWatts.toKiloWatts();
        Power actualInWatts = actualInKilowatts.toBaseUnit();

        // Then
        Power expectedInKiloWatts = Power.ofKiloWatts(10);
        assertThat(actualInKilowatts).isEqualTo(expectedInKiloWatts);
        assertThat(actualInWatts).isEqualTo(initialPowerInWatts);
    }

    @Test
    @DisplayName("should convert W from MW and vice versa")
    public void shouldProperlyConvertWattsFromMegaWatts() {
        // Given
        Power initialPowerInWatts = Power.ofWatts(2_000_000);

        // When
        Power actualInMegaWatts = initialPowerInWatts.toMegaWatts();
        Power actualInWatts = actualInMegaWatts.toBaseUnit();

        // Then
        Power expectedInMegaWatts = Power.ofMegaWatts(2);
        assertThat(actualInMegaWatts).isEqualTo(expectedInMegaWatts);
        assertThat(actualInWatts).isEqualTo(initialPowerInWatts);
    }

    @Test
    @DisplayName("should convert W from HP and vice versa")
    public void shouldProperlyConvertWattsFromHorsePower() {
        // Given
        Power initialPowerInWatts = Power.ofWatts(2000);

        // When
        Power actualInHorsePower = initialPowerInWatts.toHorsePower();
        Power actualInWatts = actualInHorsePower.toBaseUnit();

        // Then
        Power expectedInHorsePower = Power.ofHorsePower(2.68204417919006);
        assertThat(actualInHorsePower.getValue()).isEqualTo(expectedInHorsePower.getValue(), withPrecision(1E-13));
        assertThat(actualInWatts.getValue()).isEqualTo(initialPowerInWatts.getValue(), withPrecision(1E-12));
    }

}