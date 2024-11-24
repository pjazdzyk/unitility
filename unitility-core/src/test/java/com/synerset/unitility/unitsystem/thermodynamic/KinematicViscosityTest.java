package com.synerset.unitility.unitsystem.thermodynamic;

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
        KinematicViscosity actual_FT2_PER_SEC = initialKinematicViscosity.toUnit(KinematicViscosityUnits.SQUARE_FOOT_PER_SECOND);
        double actual_FT2_PER_SECVal = initialKinematicViscosity.getInSquareFeetPerSecond();
        KinematicViscosity actual_M2_PER_SEC = actual_FT2_PER_SEC.toBaseUnit();
        double actual_M2_PER_SECVal = actual_FT2_PER_SEC.getInSquareMetersPerSecond();

        // Then
        KinematicViscosity expected_FT2_PER_SEC = KinematicViscosity.ofSquareFootPerSecond(10763.91041670972);
        assertThat(actual_FT2_PER_SEC.getValue()).isEqualTo(actual_FT2_PER_SECVal);
        assertThat(actual_M2_PER_SEC.getValue()).isEqualTo(actual_M2_PER_SECVal);
        assertThat(actual_FT2_PER_SEC.getValue()).isEqualTo(expected_FT2_PER_SEC.getValue(), withPrecision(1E-10));
        assertThat(actual_M2_PER_SEC.getValue()).isEqualTo(initialKinematicViscosity.getValue(), withPrecision(1E-10));
    }

    @Test
    @DisplayName("should have m²/s as base unit")
    void shouldHaveSquareMeterPerSecondAsBaseUnit() {
        // Given
        KinematicViscosityUnit expectedBaseUnit = KinematicViscosityUnits.SQUARE_METER_PER_SECOND;

        // When
        KinematicViscosity kinematicViscosityInFt2PerSec = KinematicViscosity.ofSquareFootPerSecond(10);
        KinematicViscosityUnit actualBaseUnit = kinematicViscosityInFt2PerSec.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        KinematicViscosity expected = KinematicViscosity.ofSquareMeterPerSecond(10.1);

        // When
        KinematicViscosity actual = expected.toSquareFootPerSecond()
                .toSquareMeterPerSecond();

        double actualValue = expected.getInSquareMetersPerSecond();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}