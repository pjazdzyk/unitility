package com.synerset.unitility.unitsystem.acoustic;

import com.synerset.unitility.unitsystem.thermodynamic.PowerUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class SoundPowerTest {

    @Test
    @DisplayName("SoundPower: should convert to W from dB and vice versa")
    void shouldProperlyConvertToWattFromDecibel() {
        // Given
        SoundPower initialInDb = SoundPower.ofDecibels(120.0);

        // When
        SoundPower actualInWatt = initialInDb.toBaseUnit();
        SoundPower actualInDb = actualInWatt.toUnit(SoundPowerUnits.DECIBEL);
        double actualInWattVal = actualInWatt.getInWatts();
        double actualInDbVal = actualInWatt.getInDecibels();

        // Then
        SoundPower expectedInWatt = SoundPower.ofWatts(1.0);
        assertThat(actualInWatt.getValue()).isEqualTo(actualInWattVal);
        assertThat(actualInDb.getValue()).isEqualTo(actualInDbVal);
        assertThat(actualInWatt).isEqualTo(expectedInWatt);
        assertThat(actualInDb).isEqualTo(initialInDb);
    }

    @Test
    @DisplayName("SoundPower: should return 0 dB for reference power 1e-12 W")
    void shouldReturnZeroDbForReferencePower() {
        // Given
        SoundPower referencePower = SoundPower.ofWatts(1e-12);

        // When
        double actualInDb = referencePower.getInDecibels();

        // Then
        assertThat(actualInDb).isEqualTo(0.0, withPrecision(1E-12));
    }

    @Test
    @DisplayName("SoundPower: should have W as base unit")
    void shouldHaveWattAsBaseUnit() {
        // Given
        SoundPowerUnits expectedBaseUnit = SoundPowerUnits.WATT;

        // When
        SoundPower soundPower = SoundPower.ofDecibels(90.0);
        PowerUnit actualBaseUnit = soundPower.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("SoundPower: should be able to resolve unit from string when PowerUnit in kW is provided")
    void shouldResolveUnitFromStringWhenPowerUnitInKWIsProvided() {
        // Given
        SoundPower soundPower = SoundPower.of(0.001, "kW");

        // When
        double actualInDb = soundPower.getInDecibels();

        // Then
        assertThat(actualInDb).isEqualTo(120, withPrecision(1E-12));
    }

}
