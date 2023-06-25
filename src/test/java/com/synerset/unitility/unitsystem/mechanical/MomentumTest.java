package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class MomentumTest {

    @Test
    @DisplayName("should convert to kg·m/s from lb·ft/s and vice versa")
    void shouldProperlyConvertToKilogramMeterPerSecondFromPoundFeetPerSecond() {
        // Given
        Momentum initialMomentumInLbFtPerS = Momentum.ofPoundFeetPerSecond(10.0);

        // When
        Momentum actualInKgMPerS = initialMomentumInLbFtPerS.toUnit(MomentumUnits.KILOGRAM_METER_PER_SECOND);
        Momentum actualInPoundFeetPerSecond = actualInKgMPerS.toUnit(MomentumUnits.POUND_FEET_PER_SECOND);

        // Then
        Momentum expectedInKgMPerS = Momentum.ofKilogramMeterPerSecond(1.38254954376);
        assertThat(actualInKgMPerS.getValue()).isEqualTo(expectedInKgMPerS.getValue(), withPrecision(1E-15));
        assertThat(actualInPoundFeetPerSecond).isEqualTo(initialMomentumInLbFtPerS);
    }

    @Test
    @DisplayName("should convert to kg·m/s from g·cm/s and vice versa")
    void shouldProperlyConvertToKilogramMeterPerSecondFromGramCentimetrePerSecond() {
        // Given
        Momentum initialMomentumInGCmPerS = Momentum.ofGramCentimetrePerSecond(1000.0);

        // When
        Momentum actualInKgMPerS = initialMomentumInGCmPerS.toUnit(MomentumUnits.KILOGRAM_METER_PER_SECOND);
        Momentum actualInGCmPerS = actualInKgMPerS.toUnit(MomentumUnits.GRAM_CENTIMETRE_PER_SECOND);

        // Then
        Momentum expectedInKgMPerS = Momentum.ofKilogramMeterPerSecond(0.01);
        assertThat(actualInKgMPerS).isEqualTo(expectedInKgMPerS);
        assertThat(actualInGCmPerS.getValue()).isEqualTo(initialMomentumInGCmPerS.getValue(), withPrecision(1E-13));
    }

    @Test
    @DisplayName("should have kg·m/s as base unit")
    void shouldHaveKilogramMeterPerSecondAsBaseUnit() {
        // Given
        MomentumUnits expectedBaseUnit = MomentumUnits.KILOGRAM_METER_PER_SECOND;

        // When
        Momentum momentumInPoundFeetPerSecond = Momentum.ofPoundFeetPerSecond(10);
        Unit<Momentum> actualBaseUnit = momentumInPoundFeetPerSecond.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

}
