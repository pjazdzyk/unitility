package com.synerset.unitility.unitsystem.mechanical;

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
        Momentum actualInPoundFeetPerSec = actualInKgMPerS.toUnit(MomentumUnits.POUND_FEET_PER_SECOND);
        double actualInPoundFeetPerSecVal = actualInKgMPerS.getInPoundFeetPerSecond();
        double actualInKgMPerSVal = actualInPoundFeetPerSec.getInKilogramMetersPerSecond();

        // Then
        Momentum expectedInKgMPerS = Momentum.ofKilogramMeterPerSecond(1.38254954376);
        assertThat(actualInPoundFeetPerSec.getValue()).isEqualTo(actualInPoundFeetPerSecVal);
        assertThat(actualInKgMPerS.getValue()).isEqualTo(actualInKgMPerSVal);
        assertThat(actualInKgMPerS.getValue()).isEqualTo(expectedInKgMPerS.getValue(), withPrecision(1E-15));
        assertThat(actualInPoundFeetPerSec).isEqualTo(initialMomentumInLbFtPerS);
    }

    @Test
    @DisplayName("should convert to kg·m/s from g·cm/s and vice versa")
    void shouldProperlyConvertToKilogramMeterPerSecondFromGramCentimetrePerSecond() {
        // Given
        Momentum initialMomentumInGCmPerS = Momentum.ofGramCentimetrePerSecond(1000.0);

        // When
        Momentum actualInKgMPerS = initialMomentumInGCmPerS.toUnit(MomentumUnits.KILOGRAM_METER_PER_SECOND);
        Momentum actualInGCmPerS = actualInKgMPerS.toUnit(MomentumUnits.GRAM_CENTIMETRE_PER_SECOND);
        double actualInGCmPerSVal = actualInKgMPerS.getInGramCentimetersPerSecond();

        // Then
        Momentum expectedInKgMPerS = Momentum.ofKilogramMeterPerSecond(0.01);
        assertThat(actualInGCmPerS.getValue()).isEqualTo(actualInGCmPerSVal);
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
        MomentumUnits actualBaseUnit = momentumInPoundFeetPerSecond.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Momentum expected = Momentum.ofKilogramMeterPerSecond(10.1);

        // When
        Momentum actual = expected.toPoundFeetPerSecond()
                .toGramCentimetrePerSecond()
                .toKilogramMeterPerSecond();

        double actualValue = expected.getInKilogramMetersPerSecond();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}
