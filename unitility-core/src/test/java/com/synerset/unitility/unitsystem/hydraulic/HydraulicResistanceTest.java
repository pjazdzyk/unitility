package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.withPrecision;

class HydraulicResistanceTest {

    @Test
    @DisplayName("should have Pa·s²/kg² as base unit")
    void shouldHaveBaseUnit() {
        // Given
        HydraulicResistance resistance = HydraulicResistance.ofBarHourSquaredPerTonneSquared(10);

        // When
        HydraulicResistanceUnit baseUnit = resistance.getUnit().getBaseUnit();

        // Then
        assertThat(baseUnit).isEqualTo(HydraulicResistanceUnits.PASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED);
        assertThat(baseUnit.getSymbol()).isEqualTo("Pa·s²/kg²");
    }

    @Test
    @DisplayName("should convert with exact factors: kPa·s²/kg² is 1E3 and bar·h²/t² is 1.296E6 Pa·s²/kg²")
    void shouldConvertWithExactFactors() {
        // Given
        HydraulicResistance base = HydraulicResistance.ofPascalSecondSquaredPerKilogramSquared(1.296E6);

        // When
        HydraulicResistance inKilopascal = base.toKilopascalSecondSquaredPerKilogramSquared();
        HydraulicResistance inBarHour = base.toBarHourSquaredPerTonneSquared();

        // Then
        assertThat(inKilopascal.getValue()).isEqualTo(1296.0, withPrecision(1E-10));
        assertThat(inBarHour.getValue()).isEqualTo(1.0, withPrecision(1E-13));
        assertThat(HydraulicResistance.ofBarHourSquaredPerTonneSquared(1.0).getBaseValue())
                .isEqualTo(1.296E6, withPrecision(1E-6));
        assertThat(HydraulicResistance.ofKilopascalSecondSquaredPerKilogramSquared(1.0).getBaseValue())
                .isEqualTo(1000.0, withPrecision(1E-13));
    }

    @Test
    @DisplayName("should round-trip through every unit and compare equal across units")
    void shouldRoundTripAndCompareAcrossUnits() {
        // Given
        HydraulicResistance initial = HydraulicResistance.ofPascalSecondSquaredPerKilogramSquared(123.456);

        // When
        HydraulicResistance back = initial.toBarHourSquaredPerTonneSquared()
                .toKilopascalSecondSquaredPerKilogramSquared()
                .toBaseUnit();

        // Then
        assertThat(back.getValue()).isEqualTo(initial.getValue(), withPrecision(1E-11));
        assertThat(HydraulicResistance.ofKilopascalSecondSquaredPerKilogramSquared(1.0))
                .isEqualTo(HydraulicResistance.ofPascalSecondSquaredPerKilogramSquared(1000.0));
        assertThat(initial.getInKilopascalSecondSquaredPerKilogramSquared()).isEqualTo(0.123456, withPrecision(1E-14));
    }

    @Test
    @DisplayName("should resolve unit symbols in common spellings and reject unknown ones")
    void shouldParseSymbols() {
        // Then
        assertThat(HydraulicResistanceUnits.fromSymbol("Pa·s²/kg²"))
                .isEqualTo(HydraulicResistanceUnits.PASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED);
        assertThat(HydraulicResistanceUnits.fromSymbol("Pa*s2/kg2"))
                .isEqualTo(HydraulicResistanceUnits.PASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED);
        assertThat(HydraulicResistanceUnits.fromSymbol("kPa s2/kg2"))
                .isEqualTo(HydraulicResistanceUnits.KILOPASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED);
        assertThat(HydraulicResistanceUnits.fromSymbol("bar·h²/t²"))
                .isEqualTo(HydraulicResistanceUnits.BAR_HOUR_SQUARED_PER_TONNE_SQUARED);
        assertThat(HydraulicResistanceUnits.fromSymbol(null))
                .isEqualTo(HydraulicResistanceUnits.PASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED);
        assertThat(HydraulicResistance.of(2.0, "bar·h²/t²"))
                .isEqualTo(HydraulicResistance.ofBarHourSquaredPerTonneSquared(2.0));
        assertThatThrownBy(() -> HydraulicResistanceUnits.fromSymbol("Pa/m"))
                .isInstanceOf(UnitSystemParseException.class);
    }

    @Test
    @DisplayName("should keep dp = K·mdot² consistent: K of 1 bar·h²/t² gives 1 bar at 1 t/h")
    void shouldGiveOneBarAtOneTonnePerHour() {
        // Given
        double kInBase = HydraulicResistance.ofBarHourSquaredPerTonneSquared(1.0).getBaseValue();
        double massFlowKgPerSecond = 1000.0 / 3600.0;

        // When
        double pressureDropPa = kInBase * massFlowKgPerSecond * massFlowKgPerSecond;

        // Then
        assertThat(pressureDropPa).isEqualTo(1.0E5, withPrecision(1E-8));
    }
}
