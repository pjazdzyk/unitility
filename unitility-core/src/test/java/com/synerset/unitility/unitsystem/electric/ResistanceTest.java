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

class ResistanceTest {

    private static final double DELTA = 1E-12;

    @Test
    @DisplayName("should have OHM as base unit")
    void shouldHaveOhmAsBaseUnit() {
        // Given
        ResistanceUnit expectedBaseUnit = ResistanceUnits.OHM;

        // When
        Resistance resistanceInKiloohms = Resistance.ofKiloohms(10.0);
        ResistanceUnit actualBaseUnit = resistanceInKiloohms.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should correctly compare resistances in different units (equality check)")
    void shouldCorrectlyCompareResistancesInDifferentUnits() {
        // Given
        // 1 kΩ
        Resistance oneKiloohm = Resistance.ofKiloohms(1.0);
        // 1000 Ω
        Resistance oneThousandOhms = Resistance.ofOhms(1000.0);
        // 1 000 000 mΩ
        Resistance oneMillionMilliohms = Resistance.ofMilliohms(1_000_000.0);

        // When & Then
        assertThat(oneKiloohm.toBaseUnit().getValue()).isEqualTo(oneThousandOhms.toBaseUnit().getValue(), withPrecision(DELTA));
        assertThat(oneKiloohm.toBaseUnit().getValue()).isEqualTo(oneMillionMilliohms.toBaseUnit().getValue(), withPrecision(DELTA));
    }

    @Test
    @DisplayName("should properly convert Megaohms (MΩ) to Kiloohms (kΩ) and Ohms (Ω)")
    void shouldProperlyConvertFromMegaohms() {
        // Given
        Resistance initialResistance = Resistance.ofMegaohms(0.0025); // 0.0025 MΩ = 2500 Ω

        // When
        double actualInKiloohms = initialResistance.getInKiloohms(); // x 10^3
        double actualInOhms = initialResistance.getInOhms();         // x 10^6
        double actualInMilliohms = initialResistance.getInMilliohms();// x 10^9

        // Then
        assertThat(actualInKiloohms).isEqualTo(2.5, withPrecision(DELTA));
        assertThat(actualInOhms).isEqualTo(2500.0, withPrecision(DELTA));
        assertThat(actualInMilliohms).isEqualTo(2_500_000.0, withPrecision(DELTA));
    }

    @Test
    @DisplayName("should properly convert Ohms (Ω) to Milliohms (mΩ)")
    void shouldProperlyConvertFromOhms() {
        // Given
        Resistance initialResistance = Resistance.ofOhms(0.125); // 0.125 Ω

        // When
        double actualInMilliohms = initialResistance.getInMilliohms(); // x 10^3
        Resistance actualBackToOhm = initialResistance.toMilliohms().toOhms();

        // Then
        assertThat(actualInMilliohms).isEqualTo(125.0, withPrecision(DELTA));
        assertThat(actualBackToOhm.getValue()).isEqualTo(0.125, withPrecision(DELTA));
    }

    @ParameterizedTest(name = "Convert {0} {1} to {2} should yield {3}")
    @MethodSource("conversionTestData")
    @DisplayName("should correctly perform conversion for all defined units")
    void shouldCorrectlyPerformConversionForAllUnits(double initialValue, ResistanceUnit initialUnit, ResistanceUnit targetUnit, double expectedValue) {
        // Given
        Resistance initialResistance = Resistance.of(initialValue, initialUnit);

        // When
        double actualValue = initialResistance.toUnit(targetUnit).getValue();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue, withPrecision(DELTA));
    }

    private static Stream<Arguments> conversionTestData() {
        return Stream.of(
                // MΩ -> kΩ (x 10^3)
                arguments(0.005, ResistanceUnits.MEGAOHM, ResistanceUnits.KILOOHM, 5.0),
                // kΩ -> Ω (x 10^3)
                arguments(10.0, ResistanceUnits.KILOOHM, ResistanceUnits.OHM, 10_000.0),
                // Ω -> mΩ (x 10^3)
                arguments(0.2, ResistanceUnits.OHM, ResistanceUnits.MILLIOHM, 200.0),
                // mΩ -> Ω (x 10^-3)
                arguments(750.0, ResistanceUnits.MILLIOHM, ResistanceUnits.OHM, 0.75),
                // kΩ -> MΩ (x 10^-3)
                arguments(5000.0, ResistanceUnits.KILOOHM, ResistanceUnits.MEGAOHM, 5.0),
                // MΩ -> mΩ (x 10^9)
                arguments(0.000001, ResistanceUnits.MEGAOHM, ResistanceUnits.MILLIOHM, 1000.0)
        );
    }
}