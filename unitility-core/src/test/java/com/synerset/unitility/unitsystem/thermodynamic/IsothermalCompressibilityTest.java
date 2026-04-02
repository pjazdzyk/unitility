package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class IsothermalCompressibilityTest {

    @Test
    @DisplayName("should have 1/Pa as base unit")
    void shouldHaveInversePascalAsBaseUnit() {
        // Given
        IsothermalCompressibilityUnit expectedBaseUnit = IsothermalCompressibilityUnits.INVERSE_PASCAL;

        // When
        IsothermalCompressibility compressibilityInPSI = IsothermalCompressibility.ofInversePSI(100);
        IsothermalCompressibilityUnit actualBaseUnit = compressibilityInPSI.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should convert to 1/Pa from 1/MPa and vice versa")
    void shouldProperlyConvertToInversePascalFromInverseMegapascal() {
        // Given
        IsothermalCompressibility initialCompressibilityInMPa = IsothermalCompressibility.ofInverseMegapascals(1.0);

        // When
        IsothermalCompressibility actualInPa = initialCompressibilityInMPa.toBaseUnit();
        IsothermalCompressibility actualInMPa = actualInPa.toUnit(IsothermalCompressibilityUnits.INVERSE_MEGAPASCAL);

        // Then
        IsothermalCompressibility expectedInPa = IsothermalCompressibility.ofInversePascals(0.000001);
        assertThat(actualInPa).isEqualTo(expectedInPa);
        assertThat(actualInMPa).isEqualTo(initialCompressibilityInMPa);
    }

    @Test
    @DisplayName("should convert to 1/Pa from 1/psi and vice versa")
    void shouldProperlyConvertToInversePascalFromInversePSI() {
        // Given
        IsothermalCompressibility initialCompressibilityInPSI = IsothermalCompressibility.ofInversePSI(1.0);

        // When
        IsothermalCompressibility actualInPa = initialCompressibilityInPSI.toBaseUnit();
        IsothermalCompressibility actualInPSI = actualInPa.toUnit(IsothermalCompressibilityUnits.INVERSE_PSI);

        // Then
        IsothermalCompressibility expectedInPa = IsothermalCompressibility.ofInversePascals(0.000145037738);
        assertThat(actualInPa.getValue()).isEqualTo(expectedInPa.getValue(), withPrecision(1E-12));
        assertThat(actualInPSI).isEqualTo(initialCompressibilityInPSI);
    }

    @Test
    @DisplayName("should convert water compressibility correctly")
    void shouldConvertWaterCompressibility() {
        // Given - Water at 20°C has κ ≈ 4.6 × 10⁻¹⁰ Pa⁻¹ or 0.46 GPa⁻¹
        IsothermalCompressibility waterCompressibility = IsothermalCompressibility.ofInversePascals(4.6e-10);

        // When
        double inInverseMPa = waterCompressibility.getInInverseMegapascals();

        // Then - 4.6 × 10⁻¹⁰ Pa⁻¹ = 0.00046 MPa⁻¹ (not 0.46 MPa⁻¹ as previously stated in test)
        assertThat(inInverseMPa).isEqualTo(0.00046, withPrecision(0.00001));
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        IsothermalCompressibility expected = IsothermalCompressibility.ofInversePascals(1.5e-9);

        // When
        IsothermalCompressibility actual = expected.toInverseMegapascal()
                .toInversePSI()
                .toInverseBar()
                .toInversePascal();
        double actualValue = expected.getInInversePascals();

        // Then
        assertThat(actual.getValue()).isEqualTo(expected.getValue(), withPrecision(1E-15));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }
}