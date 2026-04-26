package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class PressureCoefficientTest {

    @Test
    @DisplayName("should have Pa/K as base unit")
    void shouldHavePascalPerKelvinAsBaseUnit() {
        // Given
        PressureCoefficientUnit expectedBaseUnit = PressureCoefficientUnits.PASCAL_PER_KELVIN;

        // When
        PressureCoefficient coefficientInPsiR = PressureCoefficient.ofPsiPerRankine(100);
        PressureCoefficientUnit actualBaseUnit = coefficientInPsiR.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should convert to Pa/K from kPa/K and vice versa")
    void shouldProperlyConvertToPascalPerKelvinFromKilopascalPerKelvin() {
        // Given
        PressureCoefficient initialInKPa = PressureCoefficient.ofKilopascalPerKelvin(1.0);

        // When
        PressureCoefficient actualInPa = initialInKPa.toBaseUnit();
        PressureCoefficient actualInKPa = actualInPa.toUnit(PressureCoefficientUnits.KILOPASCAL_PER_KELVIN);

        // Then
        assertThat(actualInPa.getValue()).isEqualTo(1000.0);
        assertThat(actualInKPa).isEqualTo(initialInKPa);
    }

    @Test
    @DisplayName("should convert to Pa/K from bar/K and vice versa")
    void shouldProperlyConvertToPascalPerKelvinFromBarPerKelvin() {
        // Given
        PressureCoefficient initialInBar = PressureCoefficient.ofBarPerKelvin(1.0);

        // When
        PressureCoefficient actualInPa = initialInBar.toBaseUnit();
        PressureCoefficient actualInBar = actualInPa.toUnit(PressureCoefficientUnits.BAR_PER_KELVIN);

        // Then
        assertThat(actualInPa.getValue()).isEqualTo(100_000.0);
        assertThat(actualInBar).isEqualTo(initialInBar);
    }

    @Test
    @DisplayName("should convert to Pa/K from psi/°R and vice versa")
    void shouldProperlyConvertToPascalPerKelvinFromPsiPerRankine() {
        // Given
        PressureCoefficient initialInPsiR = PressureCoefficient.ofPsiPerRankine(1.0);

        // When
        PressureCoefficient actualInPa = initialInPsiR.toBaseUnit();
        PressureCoefficient actualInPsiR = actualInPa.toUnit(PressureCoefficientUnits.PSI_PER_RANKINE);

        // Then
        assertThat(actualInPa.getValue()).isEqualTo(12410.56312770305, withPrecision(1E-8));
        assertThat(actualInPsiR).isEqualTo(initialInPsiR);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        PressureCoefficient expected = PressureCoefficient.ofPascalPerKelvin(100.0);

        // When
        PressureCoefficient actual = expected.toKilopascalPerKelvin()
                .toBarPerKelvin()
                .toPsiPerRankine()
                .toPascalPerKelvin();
        double actualValue = expected.getInPascalPerKelvin();

        // Then
        assertThat(actual.getValue()).isEqualTo(expected.getValue(), withPrecision(1E-10));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

    @Test
    @DisplayName("should compare equal across different units")
    void shouldCompareEqualAcrossDifferentUnits() {
        // Given - 1 kPa/K = 1000 Pa/K
        PressureCoefficient inPa = PressureCoefficient.ofPascalPerKelvin(1000.0);
        PressureCoefficient inKPa = PressureCoefficient.ofKilopascalPerKelvin(1.0);

        // Then
        assertThat(inPa).isEqualTo(inKPa);
    }
}
