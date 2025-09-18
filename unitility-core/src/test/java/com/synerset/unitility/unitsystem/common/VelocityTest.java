package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class VelocityTest {

    @Test
    @DisplayName("should convert m/s to cm/s and vice versa")
    void shouldProperlyConvertMetersPerSecondToCentimetersPerSecond() {
        // Given
        Velocity initialVelocity = Velocity.ofMetersPerSecond(10.0);

        // When
        Velocity actualInCentimetersPerSec = initialVelocity.toUnit(VelocityUnits.CENTIMETER_PER_SECOND);
        double actualInCentimetersPerSecVal = initialVelocity.getInCentimetersPerSeconds();
        Velocity actualInMetersPerSecond = actualInCentimetersPerSec.toBaseUnit();
        double actualInMetersPerSecondVal = actualInCentimetersPerSec.getInMetersPerSecond();

        // Then
        Velocity expectedInKilometersPerHour = Velocity.ofCentimetersPerSecond(1000.0);
        assertThat(actualInCentimetersPerSec.getValue()).isEqualTo(actualInCentimetersPerSecVal);
        assertThat(actualInMetersPerSecond.getValue()).isEqualTo(actualInMetersPerSecondVal);
        assertThat(actualInCentimetersPerSec).isEqualTo(expectedInKilometersPerHour);
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }

    @Test
    @DisplayName("should convert m/s to km/h and vice versa")
    void shouldProperlyConvertMetersPerSecondToKilometersPerHour() {
        // Given
        Velocity initialVelocity = Velocity.ofMetersPerSecond(10.0);

        // When
        Velocity actualInKilometersPerHour = initialVelocity.toUnit(VelocityUnits.KILOMETER_PER_HOUR);
        double actualInKilometersPerHourVal = initialVelocity.getInKilometersPerHours();
        Velocity actualInMetersPerSecond = actualInKilometersPerHour.toBaseUnit();

        // Then
        Velocity expectedInKilometersPerHour = Velocity.ofKilometersPerHour(36.0);
        assertThat(actualInKilometersPerHour.getValue()).isEqualTo(actualInKilometersPerHourVal);
        assertThat(actualInKilometersPerHour).isEqualTo(expectedInKilometersPerHour);
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }

    @Test
    @DisplayName("should convert m/s to in/s and vice versa")
    void shouldProperlyConvertMetersPerSecondToInchesPerSecond() {
        // Given
        Velocity initialVelocity = Velocity.ofMetersPerSecond(10.0);

        // When
        Velocity actualInInchesPerSec = initialVelocity.toUnit(VelocityUnits.INCH_PER_SECOND);
        double actualInInchesPerSecVal = initialVelocity.getInInchesPerSeconds();
        Velocity actualInMetersPerSecond = actualInInchesPerSec.toBaseUnit();

        // Then
        Velocity expectedInInchesPerSec = Velocity.ofInchesPerSecond(393.7007874015748);
        assertThat(actualInInchesPerSec.getValue()).isEqualTo(actualInInchesPerSecVal);
        assertThat(actualInInchesPerSec.getValue()).isEqualTo(expectedInInchesPerSec.getValue(), withPrecision(1e-15));
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }

    @Test
    @DisplayName("should convert m/s to ft/s and vice versa")
    void shouldProperlyConvertMetersPerSecondToFeetPerSecond() {
        // Given
        Velocity initialVelocity = Velocity.ofMetersPerSecond(10.0);

        // When
        Velocity actualInFeetPerSecond = initialVelocity.toUnit(VelocityUnits.FEET_PER_SECOND);
        double actualInFeetPerSecondVal = initialVelocity.getInFeetPerSeconds();
        Velocity actualInMetersPerSecond = actualInFeetPerSecond.toBaseUnit();

        // Then
        Velocity expectedInFeetPerSecond = Velocity.ofFeetPerSecond(32.80839895013123);
        assertThat(actualInFeetPerSecond.getValue()).isEqualTo(actualInFeetPerSecondVal);
        assertThat(actualInFeetPerSecond.getValue()).isEqualTo(expectedInFeetPerSecond.getValue(), withPrecision(1e-14));
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }

    @Test
    @DisplayName("should convert m/s to ft/min and vice versa")
    void shouldProperlyConvertMetersPerSecondToFeetPerMinute() {
        // Given
        Velocity initialVelocity = Velocity.ofMetersPerSecond(10.0);

        // When
        Velocity actualInFeetPerMinute = initialVelocity.toUnit(VelocityUnits.FEET_PER_MINUTE);
        double actualInFeetPerMinuteVal = initialVelocity.getInFeetPerMinutes();
        Velocity actualInMetersPerSecond = actualInFeetPerMinute.toBaseUnit();

        // Then
        // 10 m/s = 10 * 196.8503937 ≈ 1968.503937 ft/min
        Velocity expectedInFeetPerMinute = Velocity.ofFeetPerMinute(1968.503937007874);

        assertThat(actualInFeetPerMinute.getValue()).isEqualTo(actualInFeetPerMinuteVal);
        assertThat(actualInFeetPerMinute.getValue())
                .isEqualTo(expectedInFeetPerMinute.getValue(), withPrecision(1e-12));
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }


    @Test
    @DisplayName("should convert m/s to mph and vice versa")
    void shouldProperlyConvertMetersPerSecondToMilesPerHour() {
        // Given
        Velocity initialVelocity = Velocity.ofMetersPerSecond(10.0);

        // When
        Velocity actualInMilesPerHour = initialVelocity.toUnit(VelocityUnits.MILES_PER_HOUR);
        double actualInMilesPerHourVal = initialVelocity.getInMilesPerHours();
        Velocity actualInMetersPerSecond = actualInMilesPerHour.toBaseUnit();

        // Then
        Velocity expectedInMilesPerHour = Velocity.ofMilesPerHour(22.36936292054402);
        assertThat(actualInMilesPerHour.getValue()).isEqualTo(actualInMilesPerHourVal);
        assertThat(actualInMilesPerHour.getValue()).isEqualTo(expectedInMilesPerHour.getValue(), withPrecision(1e-14));
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }

    @Test
    @DisplayName("should convert m/s to kn and vice versa")
    void shouldProperlyConvertMetersPerSecondToKnots() {
        // Given
        Velocity initialVelocity = Velocity.ofMetersPerSecond(10.0);

        // When
        Velocity actualInKnots = initialVelocity.toUnit(VelocityUnits.KNOT);
        double actualInKnotsVal = initialVelocity.getInKnots();
        Velocity actualInMetersPerSecond = actualInKnots.toBaseUnit();

        // Then
        Velocity expectedInKnots = Velocity.ofKnots(19.43844492440606);
        assertThat(actualInKnots.getValue()).isEqualTo(actualInKnotsVal);
        assertThat(actualInKnots.getValue()).isEqualTo(expectedInKnots.getValue(), withPrecision(1e-14));
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }

    @Test
    @DisplayName("should convert m/s to Mach and vice versa")
    void shouldProperlyConvertMetersPerSecondToMach() {
        // Given
        Velocity initialVelocity = Velocity.ofMetersPerSecond(100.0);

        // When
        Velocity actualInMach = initialVelocity.toUnit(VelocityUnits.MACH);
        double actualInMachVal = initialVelocity.getInMach();
        Velocity actualInMetersPerSecond = actualInMach.toBaseUnit();

        // Then
        Velocity expectedInMachs = Velocity.ofMach(0.293866995797702);
        assertThat(actualInMach.getValue()).isEqualTo(actualInMachVal);
        assertThat(actualInMach.getValue()).isEqualTo(expectedInMachs.getValue(), withPrecision(1e-14));
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }

    @Test
    @DisplayName("should have m/s as base unit")
    void shouldHaveMeterPerSecondAsBaseUnit() {
        // Given
        VelocityUnit expectedBaseUnit = VelocityUnits.METER_PER_SECOND;

        // When
        Velocity velocityInMilesPerHour = Velocity.ofMilesPerHour(2);
        VelocityUnit actualBaseUnit = velocityInMilesPerHour.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Velocity expected = Velocity.ofMetersPerSecond(10.1);

        // When
        Velocity actual = expected.toCentimetersPerSecond()
                .toKilometersPerHour()
                .toInchesPerSecond()
                .toFeetPerSecond()
                .toMilesPerHour()
                .toKnots()
                .toMach()
                .toMetersPerSecond();

        double actualValue = expected.getInMetersPerSecond();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}