package com.synerset.unitility.unitsystem.similaritynumber;

import com.synerset.unitility.unitsystem.thermodynamic.CompressibilityFactor;
import com.synerset.unitility.unitsystem.thermodynamic.CompressibilityFactorUnit;
import com.synerset.unitility.unitsystem.thermodynamic.CompressibilityFactorUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class CompressibilityFactorTest {

    @Test
    @DisplayName("should have DIMENSIONLESS as base unit")
    void shouldHaveDimensionlessAsBaseUnit() {
        // Given
        CompressibilityFactorUnit expectedBaseUnit = CompressibilityFactorUnits.DIMENSIONLESS;

        // When
        CompressibilityFactor z = CompressibilityFactor.of(0.95);
        CompressibilityFactorUnit actualBaseUnit = z.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should create ideal gas compressibility factor")
    void shouldCreateIdealGasCompressibilityFactor() {
        // Given/When
        CompressibilityFactor z = CompressibilityFactor.ofIdealGas();

        // Then
        assertThat(z.getValue()).isEqualTo(1.0);
        assertThat(z.isIdealGas()).isTrue();
    }

    @Test
    @DisplayName("should identify ideal gas behavior")
    void shouldIdentifyIdealGasBehavior() {
        // Given
        CompressibilityFactor idealZ = CompressibilityFactor.of(1.0);
        CompressibilityFactor realZ = CompressibilityFactor.of(0.85);

        // When/Then
        assertThat(idealZ.isIdealGas()).isTrue();
        assertThat(realZ.isIdealGas()).isFalse();
    }

    @Test
    @DisplayName("should calculate deviation from ideal gas behavior")
    void shouldCalculateDeviationFromIdeal() {
        // Given
        CompressibilityFactor zLessThanOne = CompressibilityFactor.of(0.85);
        CompressibilityFactor zGreaterThanOne = CompressibilityFactor.of(1.15);
        CompressibilityFactor idealZ = CompressibilityFactor.of(1.0);

        // When/Then
        assertThat(zLessThanOne.getDeviationFromIdealPercent()).isEqualTo(-15.0, withPrecision(1E-11));
        assertThat(zGreaterThanOne.getDeviationFromIdealPercent()).isEqualTo(15.0, withPrecision(1E-11));
        assertThat(idealZ.getDeviationFromIdealPercent()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("should handle typical real gas values")
    void shouldHandleTypicalRealGasValues() {
        // Given - Typical Z values for various conditions
        CompressibilityFactor nitrogenAtSTP = CompressibilityFactor.of(0.9996);  // Nearly ideal
        CompressibilityFactor waterVaporHighPressure = CompressibilityFactor.of(0.75);  // Significant deviation
        CompressibilityFactor heliumLowTemp = CompressibilityFactor.of(1.02);  // Slightly above ideal

        // When/Then
        assertThat(nitrogenAtSTP.getZ()).isEqualTo(0.9996);
        assertThat(waterVaporHighPressure.getDeviationFromIdealPercent()).isEqualTo(-25.0);
        assertThat(heliumLowTemp.isIdealGas()).isFalse();
    }

    @Test
    @DisplayName("should be immutable and return same instance for conversions")
    void shouldReturnSameInstanceForConversions() {
        // Given
        CompressibilityFactor z = CompressibilityFactor.of(0.92);

        // When
        CompressibilityFactor converted = z.toBaseUnit();

        // Then - For dimensionless, conversion returns same instance
        assertThat(converted).isSameAs(z);
    }

    @Test
    @DisplayName("should properly compare equal compressibility factors")
    void shouldProperlyCompareEqualCompressibilityFactors() {
        // Given
        CompressibilityFactor z1 = CompressibilityFactor.of(0.875);
        CompressibilityFactor z2 = CompressibilityFactor.of(0.875);
        CompressibilityFactor z3 = CompressibilityFactor.of(0.925);

        // When/Then
        assertThat(z1).isEqualTo(z2);
        assertThat(z1.hashCode()).isEqualTo(z2.hashCode());
        assertThat(z1).isNotEqualTo(z3);
    }
}