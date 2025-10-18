package com.synerset.unitility.unitsystem.electric;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CurrentTest {

    private static final double DELTA = 1E-12; // Precision for double comparisons

    @Test
    @DisplayName("should have AMPERE as base unit")
    void shouldHaveAmpereAsBaseUnit() {
        // Given
        CurrentUnit expectedBaseUnit = CurrentUnits.AMPERE;

        // When
        Current currentInMilliamperes = Current.ofMilliamperes(500.0);
        CurrentUnit actualBaseUnit = currentInMilliamperes.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should correctly compare currents in different units (equality check)")
    void shouldCorrectlyCompareCurrentsInDifferentUnits() {
        // Given
        Current oneAmpere = Current.ofAmperes(1.0);
        Current oneThousandMilliamperes = Current.ofMilliamperes(1000.0);

        // When & Then
        assertThat(oneAmpere).isEqualTo(oneThousandMilliamperes);
    }

    // --- TESTY KONWERSJI ---

    @Test
    @DisplayName("should properly convert Amperes (A) to Milliamperes (mA) and Microamperes (µA)")
    void shouldProperlyConvertFromAmperes() {
        // Given
        Current initialCurrent = Current.ofAmperes(0.5); // 0.5 A

        // When
        double actualInMilliamperes = initialCurrent.getInMilliamperes();
        double actualInMicroamperes = initialCurrent.getInMicroamperes();
        double actualInKiloamperes = initialCurrent.getInKiloamperes();
        Current actualBackToA = initialCurrent.toMilliamperes().toAmperes();

        // Then
        assertThat(actualInMilliamperes).isEqualTo(500.0, withPrecision(DELTA));
        assertThat(actualInMicroamperes).isEqualTo(500_000.0, withPrecision(DELTA));
        assertThat(actualInKiloamperes).isEqualTo(0.0005, withPrecision(DELTA));
        assertThat(actualBackToA).isEqualTo(initialCurrent);
        assertThat(actualBackToA.getValue()).isEqualTo(0.5, withPrecision(DELTA));
    }

    @Test
    @DisplayName("should properly convert Kiloamperes (kA) to Amperes (A)")
    void shouldProperlyConvertFromKiloamperes() {
        // Given
        Current initialCurrent = Current.ofKiloamperes(15.0); // 15 kA

        // When
        double actualInAmperes = initialCurrent.getInAmperes();
        Current actualBackToKA = initialCurrent.toAmperes().toKiloamperes();

        // Then
        assertThat(actualInAmperes).isEqualTo(15_000.0, withPrecision(DELTA));
        assertThat(actualBackToKA.getValue()).isEqualTo(15.0, withPrecision(DELTA));
    }

    @ParameterizedTest(name = "Convert {0} {1} to {2} should yield {3}")
    @MethodSource("conversionTestData")
    @DisplayName("should correctly perform conversion for all defined units")
    void shouldCorrectlyPerformConversionForAllUnits(double initialValue, CurrentUnit initialUnit, CurrentUnit targetUnit, double expectedValue) {
        // Given
        Current initialCurrent = Current.of(initialValue, initialUnit);

        // When
        double actualValue = initialCurrent.toUnit(targetUnit).getValue();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue, withPrecision(DELTA));
    }

    private static Stream<Arguments> conversionTestData() {
        return Stream.of(
                // kA -> A (x 10^3)
                arguments(5.0, CurrentUnits.KILOAMPERE, CurrentUnits.AMPERE, 5000.0),
                // A -> mA (x 10^3)
                arguments(1.23, CurrentUnits.AMPERE, CurrentUnits.MILLIAMPERE, 1230.0),
                // mA -> µA (x 10^3)
                arguments(150.0, CurrentUnits.MILLIAMPERE, CurrentUnits.MICROAMPERE, 150_000.0),
                // µA -> A (x 10^-6)
                arguments(250_000.0, CurrentUnits.MICROAMPERE, CurrentUnits.AMPERE, 0.25),
                // µA -> mA (x 10^-3)
                arguments(75.0, CurrentUnits.MICROAMPERE, CurrentUnits.MILLIAMPERE, 0.075),
                // kA -> µA (x 10^9)
                arguments(0.001, CurrentUnits.KILOAMPERE, CurrentUnits.MICROAMPERE, 1_000_000.0)
        );
    }
}