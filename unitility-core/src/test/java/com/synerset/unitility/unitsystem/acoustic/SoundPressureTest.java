package com.synerset.unitility.unitsystem.acoustic;

import com.synerset.unitility.unitsystem.thermodynamic.PressureUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class SoundPressureTest {

    @Test
    @DisplayName("should convert to Pa from dB and vice versa")
    void shouldProperlyConvertToPascalFromDecibel() {
        // Given
        SoundPressure initialInDb = SoundPressure.ofDecibels(94.0);

        // When
        SoundPressure actualInPa = initialInDb.toBaseUnit();
        SoundPressure actualInDb = actualInPa.toUnit(SoundPressureUnits.DECIBEL);
        double actualInPaVal = actualInPa.getInPascals();
        double actualInDbVal = actualInPa.getInDecibels();

        // Then
        SoundPressure expectedInPa = SoundPressure.ofPascals(1.0023744672545452);
        assertThat(actualInPa.getValue()).isEqualTo(actualInPaVal);
        assertThat(actualInDb.getValue()).isEqualTo(actualInDbVal);
        assertThat(actualInPa).isEqualTo(expectedInPa);
        assertThat(actualInDb).isEqualTo(initialInDb);
    }

    @Test
    @DisplayName("should return 0 dB for reference pressure 2e-5 Pa")
    void shouldReturnZeroDbForReferencePressure() {
        // Given
        SoundPressure referencePressure = SoundPressure.ofPascals(2e-5);

        // When
        double actualInDb = referencePressure.getInDecibels();

        // Then
        assertThat(actualInDb).isEqualTo(0.0, withPrecision(1E-9));
    }

    @Test
    @DisplayName("should have Pa as base unit")
    void shouldHavePascalAsBaseUnit() {
        // Given
        SoundPressureUnits expectedBaseUnit = SoundPressureUnits.PASCAL;

        // When
        SoundPressure soundPressure = SoundPressure.ofDecibels(60.0);
        PressureUnit actualBaseUnit = soundPressure.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("SoundPressure: should be able to resolve unit from string when Pressure in kPa is provided")
    void shouldResolveUnitFromStringWhenPressureUnitInkPaIsProvided() {
        // Given
        SoundPressure soundPressure = SoundPressure.of(0.001, "kPa");

        // When
        double actualInDb = soundPressure.getInDecibels();

        // Then
        assertThat(actualInDb).isEqualTo(93.9794, withPrecision(1E-5));
    }

}