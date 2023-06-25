package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class PressureTest {
    @Test
    @DisplayName("should convert Pa to PSI and vice versa")
    void shouldProperlyConvertPascalToPsi() {
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
    void shouldProperlyConvertPascalToBar() {
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
    void shouldProperlyConvertPascalToHectopascal() {
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
    void shouldProperlyConvertPascalToMegapascal() {
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

    @Test
    @DisplayName("should convert Pa to mbar and vice versa")
    void shouldProperlyConvertPascalToMillibar() {
        // Given
        Pressure initialPressure = Pressure.ofPascal(101325.0);

        // When
        Pressure actualInMillibar = initialPressure.toMilliBar();
        Pressure actualInPascal = actualInMillibar.toPascal();

        // Then
        Pressure expectedInMillibar = Pressure.ofMilliBar(1013.25);
        assertThat(actualInMillibar).isEqualTo(expectedInMillibar);
        assertThat(actualInPascal).isEqualTo(initialPressure);
    }

    @Test
    @DisplayName("should convert Pa to Torr and vice versa")
    void shouldProperlyConvertPascalToTorr() {
        // Given
        Pressure initialPressure = Pressure.ofPascal(101325.0);

        // When
        Pressure actualInTorr = initialPressure.toTorr();
        Pressure actualInPascal = actualInTorr.toPascal();

        // Then
        Pressure expectedInTorr = Pressure.ofTorr(759.9999999999979);
        assertThat(actualInTorr.getValue()).isEqualTo(expectedInTorr.getValue(), withPrecision(1E-15));
        assertThat(actualInPascal).isEqualTo(initialPressure);
    }

    @Test
    @DisplayName("should have Pa as base unit")
    void shouldHavePascalAsBaseUnit() {
        // Given
        PressureUnits expectedBaseUnit = PressureUnits.PASCAL;

        // When
        Pressure pressureInPsi = Pressure.ofPsi(10);
        Unit<Pressure> actualBaseUnit = pressureInPsi.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

}
