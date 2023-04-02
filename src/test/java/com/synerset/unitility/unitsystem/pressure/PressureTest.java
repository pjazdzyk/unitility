package com.synerset.unitility.unitsystem.pressure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class PressureTest {
    @Test
    @DisplayName("should convert Pa to PSI and vice versa")
    public void shouldProperlyConvertPascalToPsi() {
        // Given
        Pressure initialPressure = Pressure.ofPascal(101325);

        // When
        Pressure actualInPsi = initialPressure.toPsi();
        Pressure actualInPascal = actualInPsi.toPascal();

        // Then
        Pressure expectedInPsi = Pressure.ofPsi(14.6959487755131);
        assertThat(actualInPsi.getValue()).isEqualTo(expectedInPsi.getValue(), withPrecision(1E-11));
        assertThat(actualInPascal.getValue()).isEqualTo(101325, withPrecision(1E-11));
    }

    @Test
    @DisplayName("should convert Pa to BAR and vice versa")
    public void shouldProperlyConvertPascalToBar() {
        // Given
        Pressure initialPressure = Pressure.ofPascal(101325);

        // When
        Pressure actualInBar = initialPressure.toBar();
        Pressure actualInPascal = actualInBar.toPascal();

        // Then
        Pressure expectedInBar = Pressure.ofBar(1.01325);
        assertThat(actualInBar.getValue()).isEqualTo(expectedInBar.getValue(), withPrecision(1E-11));
        assertThat(actualInPascal.getValue()).isEqualTo(101325, withPrecision(1E-11));
    }

    @Test
    @DisplayName("should convert Pa to hPa and vice versa")
    public void shouldProperlyConvertPascalToHectopascal() {
        // Given
        Pressure initialPressure = Pressure.ofPascal(101325);

        // When
        Pressure actualInHectopascal = initialPressure.toHectoPascal();
        Pressure actualInPascal = actualInHectopascal.toPascal();

        // Then
        Pressure expectedInHectopascal = Pressure.ofHectoPascal(1013.25);
        assertThat(actualInHectopascal.getValue()).isEqualTo(expectedInHectopascal.getValue(), withPrecision(1E-10));
        assertThat(actualInPascal.getValue()).isEqualTo(101325, withPrecision(1E-11));
    }

    @Test
    @DisplayName("should convert Pa to MPa and vice versa")
    public void shouldProperlyConvertPascalToMegapascal() {
        // Given
        Pressure initialPressure = Pressure.ofPascal(101325);

        // When
        Pressure actualInMegapascal = initialPressure.toMegaPascal();
        Pressure actualInPascal = actualInMegapascal.toPascal();

        // Then
        Pressure expectedInMegapascal = Pressure.ofMegaPascal(0.101325);
        assertThat(actualInMegapascal.getValue()).isEqualTo(expectedInMegapascal.getValue(), withPrecision(1E-10));
        assertThat(actualInPascal.getValue()).isEqualTo(101325, withPrecision(1E-11));
    }



}
