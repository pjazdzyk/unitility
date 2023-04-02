package com.synerset.unitility.unitsystem.power;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class PowerTest {

    @Test
    @DisplayName("should convert Watts to BTU/hour and vice versa")
    public void shouldProperlyConvertWattsToBTUPerHour() {
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
    @DisplayName("should convert Kilowatts to Watts and vice versa")
    public void shouldProperlyConvertKilowattsToWatts() {
        // Given
        Power initialPower = Power.ofKiloWatts(10);

        // When
        Power actualInWatts = initialPower.toWatts();
        Power actualInKilowatts = actualInWatts.toKiloWatts();

        // Then
        Power expectedInWatts = Power.ofWatts(10000);
        assertThat(actualInWatts.getValue()).isEqualTo(expectedInWatts.getValue());
        assertThat(actualInKilowatts.getValue()).isEqualTo(10);
    }
}