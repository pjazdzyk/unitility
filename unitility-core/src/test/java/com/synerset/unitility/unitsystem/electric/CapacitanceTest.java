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

class CapacitanceTest {

    private static final double DELTA = 1E-11;

    @Test
    @DisplayName("should have FARAD as base unit")
    void shouldHaveFaradAsBaseUnit() {
        // Given
        CapacitanceUnit expectedBaseUnit = CapacitanceUnits.FARAD;

        // When
        Capacitance capacitanceInMicrofarads = Capacitance.ofMicrofarads(100.0);
        CapacitanceUnit actualBaseUnit = capacitanceInMicrofarads.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should correctly compare capacitances in different units (equality check)")
    void shouldCorrectlyCompareCapacitancesInDifferentUnits() {
        // Given
        // 1 µF
        Capacitance oneMicrofarad = Capacitance.ofMicrofarads(1.0);
        // 1000 nF (1000 * 10^-9 = 10^-6 F)
        Capacitance oneThousandNanofarads = Capacitance.ofNanofarads(1000.0);

        // When & Then
        assertThat(oneMicrofarad.getInMicrofarads()).isEqualTo(oneThousandNanofarads.getInMicrofarads(), withPrecision(DELTA));
    }

    @Test
    @DisplayName("should properly convert Farads (F) to Millifarads (mF), Microfarads (µF), Nanofarads (nF), and Picofarads (pF)")
    void shouldProperlyConvertFromFarads() {
        // Given
        Capacitance initialCapacitance = Capacitance.ofFarads(0.005); // 5 mF

        // When
        double actualInMillifarads = initialCapacitance.getInMillifarads(); // x 10^3
        double actualInMicrofarads = initialCapacitance.getInMicrofarads(); // x 10^6
        double actualInNanofarads = initialCapacitance.getInNanofarads();   // x 10^9
        double actualInPicofarads = initialCapacitance.getInPicofarads();   // x 10^12

        // Then
        assertThat(actualInMillifarads).isEqualTo(5.0, withPrecision(DELTA));
        assertThat(actualInMicrofarads).isEqualTo(5_000.0, withPrecision(DELTA));
        assertThat(actualInNanofarads).isEqualTo(5_000_000.0, withPrecision(DELTA));
        assertThat(actualInPicofarads).isEqualTo(5_000_000_000.0, withPrecision(DELTA));
    }

    @Test
    @DisplayName("should properly convert Microfarads (µF) to Kilofarads (kF) and Megafarads (MF)")
    void shouldProperlyConvertToUpperUnits() {
        // Given
        Capacitance initialCapacitance = Capacitance.ofMicrofarads(1000.0); // 1000 µF = 1 mF = 0.001 F

        // When
        double actualInFarads = initialCapacitance.getInFarads();       // 0.001 F
        double actualInKilofarads = initialCapacitance.getInKilofarads(); // / 10^3
        double actualInMegafarads = initialCapacitance.getInMegafarads(); // / 10^6

        // Then
        assertThat(actualInFarads).isEqualTo(0.001, withPrecision(DELTA));
        assertThat(actualInKilofarads).isEqualTo(0.000001, withPrecision(DELTA)); // 1E-6 kF
        assertThat(actualInMegafarads).isEqualTo(0.000000001, withPrecision(DELTA)); // 1E-9 MF
    }


    @ParameterizedTest(name = "Convert {0} {1} to {2} should yield {3}")
    @MethodSource("conversionTestData")
    @DisplayName("should correctly perform conversion for all defined units across the full range")
    void shouldCorrectlyPerformConversionForAllUnits(double initialValue, CapacitanceUnit initialUnit, CapacitanceUnit targetUnit, double expectedValue) {
        // Given
        Capacitance initialCapacitance = Capacitance.of(initialValue, initialUnit);

        // When
        double actualValue = initialCapacitance.toUnit(targetUnit).getValue();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue, withPrecision(DELTA));
    }

    private static Stream<Arguments> conversionTestData() {
        return Stream.of(
                // MF -> F (x 10^6)
                arguments(0.001, CapacitanceUnits.MEGAFARAD, CapacitanceUnits.FARAD, 1000.0),
                // kF -> mF (x 10^6)
                arguments(1.0, CapacitanceUnits.KILOFARAD, CapacitanceUnits.MILLIFARAD, 1_000_000.0),
                // F -> µF (x 10^6)
                arguments(0.0000025, CapacitanceUnits.FARAD, CapacitanceUnits.MICROFARAD, 2.5),
                // µF -> nF (x 10^3)
                arguments(0.8, CapacitanceUnits.MICROFARAD, CapacitanceUnits.NANOFARAD, 800.0),
                // nF -> pF (x 10^3)
                arguments(15.0, CapacitanceUnits.NANOFARAD, CapacitanceUnits.PICOFARAD, 15_000.0),
                // pF -> nF (x 10^-3)
                arguments(5000.0, CapacitanceUnits.PICOFARAD, CapacitanceUnits.NANOFARAD, 5.0),
                // µF -> F (x 10^-6)
                arguments(120.0, CapacitanceUnits.MICROFARAD, CapacitanceUnits.FARAD, 0.000120),
                // mF -> kF (x 10^-6)
                arguments(500.0, CapacitanceUnits.MILLIFARAD, CapacitanceUnits.KILOFARAD, 0.0005)
        );
    }
}