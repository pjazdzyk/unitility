package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class CubicExpansionCoefficientTest {

    @Test
    @DisplayName("should have 1/K as base unit")
    void shouldHaveInverseKelvinAsBaseUnit() {
        // Given
        CubicExpansionCoefficientUnit expectedBaseUnit = CubicExpansionCoefficientUnits.INVERSE_KELVIN;

        // When
        CubicExpansionCoefficient coefficientInF = CubicExpansionCoefficient.ofInverseFahrenheit(100);
        CubicExpansionCoefficientUnit actualBaseUnit = coefficientInF.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should convert to 1/K from 1/°C and vice versa")
    void shouldProperlyConvertToInverseKelvinFromInverseCelsius() {
        // Given - temperature intervals are identical: Δ1K = Δ1°C
        CubicExpansionCoefficient initialInC = CubicExpansionCoefficient.ofInverseCelsius(1.0);

        // When
        CubicExpansionCoefficient actualInK = initialInC.toBaseUnit();
        CubicExpansionCoefficient actualInC = actualInK.toUnit(CubicExpansionCoefficientUnits.INVERSE_CELSIUS);

        // Then
        assertThat(actualInK.getValue()).isEqualTo(1.0);
        assertThat(actualInC).isEqualTo(initialInC);
    }

    @Test
    @DisplayName("should convert to 1/K from 1/°R and vice versa")
    void shouldProperlyConvertToInverseKelvinFromInverseRankine() {
        // Given - Δ1K = Δ1.8°R, so 1 °R⁻¹ = 1.8 K⁻¹
        CubicExpansionCoefficient initialInR = CubicExpansionCoefficient.ofInverseRankine(1.0);

        // When
        CubicExpansionCoefficient actualInK = initialInR.toBaseUnit();
        CubicExpansionCoefficient actualInR = actualInK.toUnit(CubicExpansionCoefficientUnits.INVERSE_RANKINE);

        // Then
        assertThat(actualInK.getValue()).isEqualTo(1.8);
        assertThat(actualInR).isEqualTo(initialInR);
    }

    @Test
    @DisplayName("should convert to 1/K from 1/mK and vice versa")
    void shouldProperlyConvertToInverseKelvinFromInverseMillikelvin() {
        // Given - 1 mK⁻¹ = 1000 K⁻¹
        CubicExpansionCoefficient initialInmK = CubicExpansionCoefficient.ofInverseMillikelvin(1.0);

        // When
        CubicExpansionCoefficient actualInK = initialInmK.toBaseUnit();
        CubicExpansionCoefficient actualInmK = actualInK.toUnit(CubicExpansionCoefficientUnits.INVERSE_MILLIKELVIN);

        // Then
        assertThat(actualInK.getValue()).isEqualTo(1000.0);
        assertThat(actualInmK).isEqualTo(initialInmK);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        CubicExpansionCoefficient expected = CubicExpansionCoefficient.ofInverseKelvin(0.000207);

        // When
        CubicExpansionCoefficient actual = expected.toInverseCelsius()
                .toInverseRankine()
                .toInverseFahrenheit()
                .toInverseKelvin();
        double actualValue = expected.getInInverseKelvin();

        // Then
        assertThat(actual.getValue()).isEqualTo(expected.getValue(), withPrecision(1E-15));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

    @Test
    @DisplayName("should compare equal across different units")
    void shouldCompareEqualAcrossDifferentUnits() {
        // Given - 1 K⁻¹ = 1.8 °R⁻¹
        CubicExpansionCoefficient inK = CubicExpansionCoefficient.ofInverseKelvin(1.8);
        CubicExpansionCoefficient inR = CubicExpansionCoefficient.ofInverseRankine(1.0);

        // Then
        assertThat(inK).isEqualTo(inR);
    }
}
