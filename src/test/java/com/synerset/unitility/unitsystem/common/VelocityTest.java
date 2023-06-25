package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.Unit;
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
        Velocity actualInCentimetersPerSec = initialVelocity.toCentimetersPerSecond();
        Velocity actualInMetersPerSecond = actualInCentimetersPerSec.toBaseUnit();

        // Then
        Velocity expectedInKilometersPerHour = Velocity.ofCentimetersPerSecond(1000.0);
        assertThat(actualInCentimetersPerSec).isEqualTo(expectedInKilometersPerHour);
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }

    @Test
    @DisplayName("should convert m/s to km/h and vice versa")
    void shouldProperlyConvertMetersPerSecondToKilometersPerHour() {
        // Given
        Velocity initialVelocity = Velocity.ofMetersPerSecond(10.0);

        // When
        Velocity actualInKilometersPerHour = initialVelocity.toKilometersPerHour();
        Velocity actualInMetersPerSecond = actualInKilometersPerHour.toBaseUnit();

        // Then
        Velocity expectedInKilometersPerHour = Velocity.ofKilometersPerHour(36.0);
        assertThat(actualInKilometersPerHour).isEqualTo(expectedInKilometersPerHour);
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }

    @Test
    @DisplayName("should convert m/s to in/s and vice versa")
    void shouldProperlyConvertMetersPerSecondToInchesPerSecond() {
        // Given
        Velocity initialVelocity = Velocity.ofMetersPerSecond(10.0);

        // When
        Velocity actualInInchesPerSec = initialVelocity.toInchesPerSecond();
        Velocity actualInMetersPerSecond = actualInInchesPerSec.toBaseUnit();

        // Then
        Velocity expectedInInchesPerSec = Velocity.ofInchesPerSecond(393.7007874015748);
        assertThat(actualInInchesPerSec.getValue()).isEqualTo(expectedInInchesPerSec.getValue(), withPrecision(1e-15));
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }

    @Test
    @DisplayName("should convert m/s to ft/s and vice versa")
    void shouldProperlyConvertMetersPerSecondToFeetPerSecond() {
        // Given
        Velocity initialVelocity = Velocity.ofMetersPerSecond(10.0);

        // When
        Velocity actualInFeetPerSecond = initialVelocity.toFeetPerSecond();
        Velocity actualInMetersPerSecond = actualInFeetPerSecond.toBaseUnit();

        // Then
        Velocity expectedInFeetPerSecond = Velocity.ofFeetPerSecond(32.80839895013123);
        assertThat(actualInFeetPerSecond.getValue()).isEqualTo(expectedInFeetPerSecond.getValue(), withPrecision(1e-14));
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }

    @Test
    @DisplayName("should convert m/s to mph and vice versa")
    void shouldProperlyConvertMetersPerSecondToMilesPerHour() {
        // Given
        Velocity initialVelocity = Velocity.ofMetersPerSecond(10.0);

        // When
        Velocity actualInMilesPerHour = initialVelocity.toMilesPerHour();
        Velocity actualInMetersPerSecond = actualInMilesPerHour.toBaseUnit();

        // Then
        Velocity expectedInMilesPerHour = Velocity.ofMilesPerHour(22.36936292054402);
        assertThat(actualInMilesPerHour.getValue()).isEqualTo(expectedInMilesPerHour.getValue(), withPrecision(1e-14));
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }

    @Test
    @DisplayName("should convert m/s to kn and vice versa")
    void shouldProperlyConvertMetersPerSecondToKnots() {
        // Given
        Velocity initialVelocity = Velocity.ofMetersPerSecond(10.0);

        // When
        Velocity actualInKnots = initialVelocity.toKnots();
        Velocity actualInMetersPerSecond = actualInKnots.toBaseUnit();

        // Then
        Velocity expectedInKnots = Velocity.ofKnots(19.43844492440606);
        assertThat(actualInKnots.getValue()).isEqualTo(expectedInKnots.getValue(), withPrecision(1e-14));
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }

    @Test
    @DisplayName("should convert m/s to Mach and vice versa")
    void shouldProperlyConvertMetersPerSecondToMach() {
        // Given
        Velocity initialVelocity = Velocity.ofMetersPerSecond(100.0);

        // When
        Velocity actualInMach = initialVelocity.toMach();
        Velocity actualInMetersPerSecond = actualInMach.toBaseUnit();

        // Then
        Velocity expectedInMachs = Velocity.ofMach(0.293866995797702);
        assertThat(actualInMach.getValue()).isEqualTo(expectedInMachs.getValue(), withPrecision(1e-14));
        assertThat(actualInMetersPerSecond).isEqualTo(initialVelocity);
    }

    @Test
    @DisplayName("should have m/s as base unit")
    void shouldHaveMeterPerSecondAsBaseUnit() {
        // Given
        VelocityUnits expectedBaseUnit = VelocityUnits.METER_PER_SECOND;

        // When
        Velocity velocityInMilesPerHour = Velocity.ofMilesPerHour(2);
        Unit<Velocity> actualBaseUnit = velocityInMilesPerHour.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

}