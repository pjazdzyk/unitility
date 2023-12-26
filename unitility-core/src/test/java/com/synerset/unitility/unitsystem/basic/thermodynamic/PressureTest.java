package com.synerset.unitility.unitsystem.basic.thermodynamic;

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
        Pressure actualInPsi = initialPressure.toUnit(PressureUnits.PSI);
        double actualInPsiVal = initialPressure.getInPsi();
        Pressure actualInPascal = actualInPsi.toBaseUnit();
        double actualInPascalVal = actualInPsi.getInPascals();

        // Then
        Pressure expectedInPsi = Pressure.ofPsi(14.6959487755131);
        assertThat(actualInPsi.getValue()).isEqualTo(actualInPsiVal);
        assertThat(actualInPascal.getValue()).isEqualTo(actualInPascalVal);
        assertThat(actualInPsi.getValue()).isEqualTo(expectedInPsi.getValue(), withPrecision(1E-11));
        assertThat(actualInPascal.getValue()).isEqualTo(101325, withPrecision(1E-11));
    }

    @Test
    @DisplayName("should convert Pa to BAR and vice versa")
    void shouldProperlyConvertPascalToBar() {
        // Given
        Pressure initialPressure = Pressure.ofPascal(101325);

        // When
        Pressure actualInBar = initialPressure.toUnit(PressureUnits.BAR);
        double actualInBarVal = initialPressure.getInBar();
        Pressure actualInPascal = actualInBar.toBaseUnit();

        // Then
        Pressure expectedInBar = Pressure.ofBar(1.01325);
        assertThat(actualInBar.getValue()).isEqualTo(actualInBarVal);
        assertThat(actualInBar.getValue()).isEqualTo(expectedInBar.getValue(), withPrecision(1E-11));
        assertThat(actualInPascal.getValue()).isEqualTo(101325, withPrecision(1E-11));
    }

    @Test
    @DisplayName("should convert Pa to hPa and vice versa")
    void shouldProperlyConvertPascalToHectopascal() {
        // Given
        Pressure initialPressure = Pressure.ofPascal(101325);

        // When
        Pressure actualInHectopascal = initialPressure.toUnit(PressureUnits.HECTOPASCAL);
        double actualInHectopascalVal = initialPressure.getInHectoPascals();
        Pressure actualInPascal = actualInHectopascal.toBaseUnit();

        // Then
        Pressure expectedInHectopascal = Pressure.ofHectoPascal(1013.25);
        assertThat(actualInHectopascal.getValue()).isEqualTo(actualInHectopascalVal);
        assertThat(actualInHectopascal.getValue()).isEqualTo(expectedInHectopascal.getValue(), withPrecision(1E-10));
        assertThat(actualInPascal.getValue()).isEqualTo(101325, withPrecision(1E-11));
    }

    @Test
    @DisplayName("should convert Pa to MPa and vice versa")
    void shouldProperlyConvertPascalToMegapascal() {
        // Given
        Pressure initialPressure = Pressure.ofPascal(101325);

        // When
        Pressure actualInMegapascal = initialPressure.toUnit(PressureUnits.MEGAPASCAL);
        double actualInMegapascalVal = initialPressure.getInMegaPascals();
        Pressure actualInPascal = actualInMegapascal.toBaseUnit();

        // Then
        Pressure expectedInMegapascal = Pressure.ofMegaPascal(0.101325);
        assertThat(actualInMegapascal.getValue()).isEqualTo(actualInMegapascalVal);
        assertThat(actualInMegapascal.getValue()).isEqualTo(expectedInMegapascal.getValue(), withPrecision(1E-10));
        assertThat(actualInPascal.getValue()).isEqualTo(101325, withPrecision(1E-11));
    }

    @Test
    @DisplayName("should convert Pa to mbar and vice versa")
    void shouldProperlyConvertPascalToMillibar() {
        // Given
        Pressure initialPressure = Pressure.ofPascal(101325.0);

        // When
        Pressure actualInMillibar = initialPressure.toUnit(PressureUnits.MILLIBAR);
        double actualInMillibarVal = initialPressure.getInMilliBar();
        Pressure actualInPascal = actualInMillibar.toBaseUnit();

        // Then
        Pressure expectedInMillibar = Pressure.ofMilliBar(1013.25);
        assertThat(actualInMillibar.getValue()).isEqualTo(actualInMillibarVal);
        assertThat(actualInMillibar).isEqualTo(expectedInMillibar);
        assertThat(actualInPascal).isEqualTo(initialPressure);
    }

    @Test
    @DisplayName("should convert Pa to Torr and vice versa")
    void shouldProperlyConvertPascalToTorr() {
        // Given
        Pressure initialPressure = Pressure.ofPascal(101325.0);

        // When
        Pressure actualInTorr = initialPressure.toUnit(PressureUnits.TORR);
        double actualInTorrValue = initialPressure.getInTorr();
        Pressure actualInPascal = actualInTorr.toBaseUnit();

        // Then
        Pressure expectedInTorr = Pressure.ofTorr(759.9999999999979);
        assertThat(actualInTorr.getValue()).isEqualTo(actualInTorrValue);
        assertThat(actualInTorr.getValue()).isEqualTo(expectedInTorr.getValue(), withPrecision(1E-15));
        assertThat(actualInPascal).isEqualTo(initialPressure);
    }

    @Test
    @DisplayName("should have Pa as base unit")
    void shouldHavePascalAsBaseUnit() {
        // Given
        PressureUnit expectedBaseUnit = PressureUnits.PASCAL;

        // When
        Pressure pressureInPsi = Pressure.ofPsi(10);
        PressureUnit actualBaseUnit = pressureInPsi.getUnitType().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Pressure expected = Pressure.ofPascal(10.1);

        // When
        Pressure actual = expected.toHectoPascal()
                .toMegaPascal()
                .toBar()
                .toMilliBar()
                .toPsi()
                .toTorr()
                .toPascal();

        double actualValue = expected.getInPascals();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}
