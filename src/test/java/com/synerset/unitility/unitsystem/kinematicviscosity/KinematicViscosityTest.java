package com.synerset.unitility.unitsystem.kinematicviscosity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class KinematicViscosityTest {
    @Test
    @DisplayName("should convert m²/s to ft²/s and vice versa")
    void shouldProperlyConvertSquareMeterPerSecondToSquareFootPerSecond() {
        // Given
        KinematicViscosity initialKinematicViscosity = KinematicViscosity.ofSquareMeterPerSecond(1000);

        // When
        KinematicViscosity actual_FT2_PER_SEC = initialKinematicViscosity.toSquareFootPerSecond();
        KinematicViscosity actual_M2_PER_SEC = actual_FT2_PER_SEC.toSquareMeterPerSecond();

        // Then
        KinematicViscosity expected_FT2_PER_SEC = KinematicViscosity.ofSquareFootPerSecond(10763.91041670972);
        assertThat(actual_FT2_PER_SEC.getValue()).isEqualTo(expected_FT2_PER_SEC.getValue(), withPrecision(1E-10));
        assertThat(actual_M2_PER_SEC.getValue()).isEqualTo(initialKinematicViscosity.getValue(), withPrecision(1E-10));
    }
}