package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class IsentropicCompressibilityTest {

    @Test
    @DisplayName("should have 1/Pa as base unit")
    void shouldHaveInversePascalAsBaseUnit() {
        // Given
        IsentropicCompressibilityUnit expectedBaseUnit = IsentropicCompressibilityUnits.INVERSE_PASCAL;

        // When
        IsentropicCompressibility compressibilityInPSI = IsentropicCompressibility.ofInversePSI(100);
        IsentropicCompressibilityUnit actualBaseUnit = compressibilityInPSI.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should convert to 1/Pa from 1/MPa and vice versa")
    void shouldProperlyConvertToInversePascalFromInverseMegapascal() {
        // Given
        IsentropicCompressibility initialCompressibilityInMPa = IsentropicCompressibility.ofInverseMegapascals(1.0);

        // When
        IsentropicCompressibility actualInPa = initialCompressibilityInMPa.toBaseUnit();
        IsentropicCompressibility actualInMPa = actualInPa.toUnit(IsentropicCompressibilityUnits.INVERSE_MEGAPASCAL);

        // Then
        IsentropicCompressibility expectedInPa = IsentropicCompressibility.ofInversePascals(0.000001);
        assertThat(actualInPa).isEqualTo(expectedInPa);
        assertThat(actualInMPa).isEqualTo(initialCompressibilityInMPa);
    }

    @Test
    @DisplayName("should convert to 1/Pa from 1/psi and vice versa")
    void shouldProperlyConvertToInversePascalFromInversePSI() {
        // Given
        IsentropicCompressibility initialCompressibilityInPSI = IsentropicCompressibility.ofInversePSI(1.0);

        // When
        IsentropicCompressibility actualInPa = initialCompressibilityInPSI.toBaseUnit();
        IsentropicCompressibility actualInPSI = actualInPa.toUnit(IsentropicCompressibilityUnits.INVERSE_PSI);

        // Then
        IsentropicCompressibility expectedInPa = IsentropicCompressibility.ofInversePascals(0.000145037738);
        assertThat(actualInPa.getValue()).isEqualTo(expectedInPa.getValue(), withPrecision(1E-12));
        assertThat(actualInPSI).isEqualTo(initialCompressibilityInPSI);
    }

    @Test
    @DisplayName("should convert to 1/Pa from 1/bar and vice versa")
    void shouldProperlyConvertToInversePascalFromInverseBar() {
        // Given
        IsentropicCompressibility initialCompressibilityInBar = IsentropicCompressibility.ofInverseBars(1.0);

        // When
        IsentropicCompressibility actualInPa = initialCompressibilityInBar.toBaseUnit();
        IsentropicCompressibility actualInBar = actualInPa.toUnit(IsentropicCompressibilityUnits.INVERSE_BAR);

        // Then
        IsentropicCompressibility expectedInPa = IsentropicCompressibility.ofInversePascals(0.00001);
        assertThat(actualInPa).isEqualTo(expectedInPa);
        assertThat(actualInBar).isEqualTo(initialCompressibilityInBar);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        IsentropicCompressibility expected = IsentropicCompressibility.ofInversePascals(1.5e-9);

        // When
        IsentropicCompressibility actual = expected.toInverseMegapascal()
                .toInversePSI()
                .toInverseBar()
                .toInversePascal();
        double actualValue = expected.getInInversePascals();

        // Then
        assertThat(actual.getValue()).isEqualTo(expected.getValue(), withPrecision(1E-15));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

    @Test
    @DisplayName("should compare equal across different units")
    void shouldCompareEqualAcrossDifferentUnits() {
        // Given - 1e-6 Pa⁻¹ = 1.0 MPa⁻¹
        IsentropicCompressibility inBase = IsentropicCompressibility.ofInversePascals(1e-6);
        IsentropicCompressibility inMPa = IsentropicCompressibility.ofInverseMegapascals(1.0);

        // Then
        assertThat(inBase).isEqualTo(inMPa);
    }
}
