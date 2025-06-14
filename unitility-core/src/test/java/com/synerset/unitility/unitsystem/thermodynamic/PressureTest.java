package com.synerset.unitility.unitsystem.thermodynamic;

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
    @DisplayName("should convert Pa to kPa and vice versa")
    void shouldProperlyConvertPascalToKiloPascal() {
        // Given
        Pressure initialPressure = Pressure.ofPascal(100000.0);

        // When
        Pressure actualInKpa = initialPressure.toUnit(PressureUnits.KILOPASCAL);
        double actualInKpaValue = initialPressure.getInKiloPascals();
        Pressure actualInPascal = actualInKpa.toBaseUnit();

        // Then
        Pressure expectedInTorr = Pressure.ofKiloPascal(100);
        assertThat(actualInKpa.getValue()).isEqualTo(actualInKpaValue);
        assertThat(actualInKpa.getValue()).isEqualTo(expectedInTorr.getValue(), withPrecision(1E-15));
        assertThat(actualInPascal).isEqualTo(initialPressure);
    }

    @Test
    @DisplayName("should have Pa as base unit")
    void shouldHavePascalAsBaseUnit() {
        // Given
        PressureUnit expectedBaseUnit = PressureUnits.PASCAL;

        // When
        Pressure pressureInPsi = Pressure.ofPsi(10);
        PressureUnit actualBaseUnit = pressureInPsi.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should convert mH₂O_10 to Pa and vice versa")
    void shouldProperlyConvertMetreOfWater10ToPa() {
        // Given
        Pressure initialPressure = Pressure.ofMetreOfWater10(1.0);

        // When
        Pressure actualInPa = initialPressure.toUnit(PressureUnits.PASCAL);
        double actualInPaVal = initialPressure.getInPascals();
        Pressure actualInMetreOfWater10 = actualInPa.toUnit(PressureUnits.METRE_OF_WATER_10);

        // Then
        Pressure expectedInPa = Pressure.ofPascal(9802.19483921956398939);
        assertThat(actualInPa.getValue()).isEqualTo(actualInPaVal);
        assertThat(actualInMetreOfWater10.getValue()).isEqualTo(initialPressure.getValue());
        assertThat(actualInPa.getValue()).isEqualTo(expectedInPa.getValue(), withPrecision(1E-9));
    }

    @Test
    @DisplayName("should convert mH₂O_60 to Pa and vice versa")
    void shouldProperlyConvertMetreOfWater60ToPa() {
        // Given
        Pressure initialPressure = Pressure.ofMetreOfWater60(1.0);

        // When
        Pressure actualInPa = initialPressure.toUnit(PressureUnits.PASCAL);
        double actualInPaVal = initialPressure.getInPascals();
        Pressure actualInMetreOfWater60 = actualInPa.toUnit(PressureUnits.METRE_OF_WATER_60);

        // Then
        Pressure expectedInPa = Pressure.ofPascal(9636.71656869821414767);
        assertThat(actualInPa.getValue()).isEqualTo(actualInPaVal);
        assertThat(actualInMetreOfWater60.getValue()).isEqualTo(initialPressure.getValue());
        assertThat(actualInPa.getValue()).isEqualTo(expectedInPa.getValue(), withPrecision(1E-9));
    }

    @Test
    @DisplayName("should convert mH₂O_95 to Pa and vice versa")
    void shouldProperlyConvertMetreOfWater95ToPa() {
        // Given
        Pressure initialPressure = Pressure.ofMetreOfWater95(1.0);

        // When
        Pressure actualInPa = initialPressure.toUnit(PressureUnits.PASCAL);
        double actualInPaVal = initialPressure.getInPascals();
        Pressure actualInMetreOfWater95 = actualInPa.toUnit(PressureUnits.METRE_OF_WATER_95);

        // Then
        Pressure expectedInPa = Pressure.ofPascal(9426.82921524257130525);
        assertThat(actualInPa.getValue()).isEqualTo(actualInPaVal);
        assertThat(actualInMetreOfWater95.getValue()).isEqualTo(initialPressure.getValue());
        assertThat(actualInPa.getValue()).isEqualTo(expectedInPa.getValue(), withPrecision(1E-9));
    }

    @Test
    @DisplayName("should convert mmHg_10 to Pa and vice versa")
    void shouldProperlyConvertMillimetreOfMercury10ToPa() {
        // Given
        Pressure initialPressure = Pressure.ofMillimetreOfMercury10(1.0);

        // When
        Pressure actualInPa = initialPressure.toUnit(PressureUnits.PASCAL);
        double actualInPaVal = initialPressure.getInPascals();
        Pressure actualInMillimetreOfMercury10 = actualInPa.toUnit(PressureUnits.MILLIMETRE_OF_MERCURY_10);

        // Then
        Pressure expectedInPa = Pressure.ofPascal(133.0762405);
        assertThat(actualInPa.getValue()).isEqualTo(actualInPaVal);
        assertThat(actualInMillimetreOfMercury10.getValue()).isEqualTo(initialPressure.getValue());
        assertThat(actualInPa.getValue()).isEqualTo(expectedInPa.getValue(), withPrecision(1E-9));
    }

    @Test
    @DisplayName("should convert mmHg_60 to Pa and vice versa")
    void shouldProperlyConvertMillimetreOfMercury60ToPa() {
        // Given
        Pressure initialPressure = Pressure.ofMillimetreOfMercury60(1.0);

        // When
        Pressure actualInPa = initialPressure.toUnit(PressureUnits.PASCAL);
        double actualInPaVal = initialPressure.getInPascals();
        Pressure actualInMillimetreOfMercury60 = actualInPa.toUnit(PressureUnits.MILLIMETRE_OF_MERCURY_60);

        // Then
        Pressure expectedInPa = Pressure.ofPascal(131.8798292);
        assertThat(actualInPa.getValue()).isEqualTo(actualInPaVal);
        assertThat(actualInMillimetreOfMercury60.getValue()).isEqualTo(initialPressure.getValue());
        assertThat(actualInPa.getValue()).isEqualTo(expectedInPa.getValue(), withPrecision(1E-9));
    }

    @Test
    @DisplayName("should convert mmHg_95 to Pa and vice versa")
    void shouldProperlyConvertMillimetreOfMercury95ToPa() {
        // Given
        Pressure initialPressure = Pressure.ofMillimetreOfMercury95(1.0);

        // When
        Pressure actualInPa = initialPressure.toUnit(PressureUnits.PASCAL);
        double actualInPaVal = initialPressure.getInPascals();
        Pressure actualInMillimetreOfMercury95 = actualInPa.toUnit(PressureUnits.MILLIMETRE_OF_MERCURY_95);

        // Then
        Pressure expectedInPa = Pressure.ofPascal(131.0560706);
        assertThat(actualInPa.getValue()).isEqualTo(actualInPaVal);
        assertThat(actualInMillimetreOfMercury95.getValue()).isEqualTo(initialPressure.getValue());
        assertThat(actualInPa.getValue()).isEqualTo(expectedInPa.getValue(), withPrecision(1E-9));
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
                .toKiloPascal()
                .toPsi()
                .toTorr()
                .toMetreOfWater10()
                .toMetreOfWater60()
                .toMetreOfWater95()
                .toMillimetreOfMercury10()
                .toMillimetreOfMercury60()
                .toMillimetreOfMercury95()
                .toPascal();

        // Then
        assertThat(actual.getInPascals()).isEqualTo(expected.getValue(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("should properly parse to Pressure from string")
    void shouldProperlyParseToPressureFromString() {
        String pEmpty = "mH2O";
        String p10 = "mH2O_10";
        String p60bis = "mH2O 60";
        String pHgEmpty = "mmHg";
        String pHg10 = "mmHg_10";

        assertThat(Pressure.of(1, pEmpty)).isEqualTo(Pressure.ofMetreOfWater10(1));
        assertThat(Pressure.of(1, p10)).isEqualTo(Pressure.ofMetreOfWater10(1));
        assertThat(Pressure.of(1, p60bis)).isEqualTo(Pressure.ofMetreOfWater60(1));
        assertThat(Pressure.of(1, pHgEmpty)).isEqualTo(Pressure.ofMillimetreOfMercury10(1));
        assertThat(Pressure.of(1, pHg10)).isEqualTo(Pressure.ofMillimetreOfMercury10(1));

    }

}
