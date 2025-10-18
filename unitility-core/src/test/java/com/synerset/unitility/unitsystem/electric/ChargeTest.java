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

class ChargeTest {

    private static final double DELTA = 1E-12;

    @Test
    @DisplayName("should have COULOMB as base unit")
    void shouldHaveCoulombAsBaseUnit() {
        // Given
        ChargeUnit expectedBaseUnit = ChargeUnits.COULOMB;

        // When
        Charge chargeInMicrocoulombs = Charge.ofMicrocoulombs(10.0);
        ChargeUnit actualBaseUnit = chargeInMicrocoulombs.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should correctly compare charges in different units (equality check using base value comparison)")
    void shouldCorrectlyCompareChargesInDifferentUnits() {
        // Given
        // 1 C
        Charge oneCoulomb = Charge.ofCoulombs(1.0);
        // 1000 mC
        Charge oneThousandMillicoulombs = Charge.ofMillicoulombs(1000.0);
        // 1 000 000 µC
        Charge oneMillionMicrocoulombs = Charge.ofMicrocoulombs(1_000_000.0);

        // When & Then
        double baseValue1 = oneCoulomb.toBaseUnit().getValue();
        double baseValue2 = oneThousandMillicoulombs.toBaseUnit().getValue();
        double baseValue3 = oneMillionMicrocoulombs.toBaseUnit().getValue();

        assertThat(baseValue1).isEqualTo(baseValue2, withPrecision(DELTA));
        assertThat(baseValue1).isEqualTo(baseValue3, withPrecision(DELTA));
    }

    @Test
    @DisplayName("should properly convert Kilocoulombs (kC) to Coulombs (C) and lower units")
    void shouldProperlyConvertFromKilocoulombs() {
        // Given
        Charge initialCharge = Charge.ofKilocoulombs(0.005); // 0.005 kC = 5 C

        // When
        double actualInCoulombs = initialCharge.getInCoulombs();         // x 10^3
        double actualInMillicoulombs = initialCharge.getInMillicoulombs();// x 10^6
        double actualInMicrocoulombs = initialCharge.getInMicrocoulombs();// x 10^9

        // Then
        assertThat(actualInCoulombs).isEqualTo(5.0, withPrecision(DELTA));
        assertThat(actualInMillicoulombs).isEqualTo(5_000.0, withPrecision(DELTA));
        assertThat(actualInMicrocoulombs).isEqualTo(5_000_000.0, withPrecision(DELTA));
    }

    @Test
    @DisplayName("should properly convert Picocoulombs (pC) to Nanocoulombs (nC)")
    void shouldProperlyConvertBetweenLowerUnits() {
        // Given
        Charge initialCharge = Charge.ofPicocoulombs(750.0); // 750 pC

        // When
        double actualInNanocoulombs = initialCharge.getInNanocoulombs(); // x 10^-3
        Charge actualBackToPicocoulombs = initialCharge.toNanocoulombs().toPicocoulombs();

        // Then
        assertThat(actualInNanocoulombs).isEqualTo(0.75, withPrecision(DELTA));
        assertThat(actualBackToPicocoulombs.getValue()).isEqualTo(750.0, withPrecision(DELTA));
    }

    @ParameterizedTest(name = "Convert {0} {1} to {2} should yield {3}")
    @MethodSource("conversionTestData")
    @DisplayName("should correctly perform conversion for all defined units")
    void shouldCorrectlyPerformConversionForAllUnits(double initialValue, ChargeUnit initialUnit, ChargeUnit targetUnit, double expectedValue) {
        // Given
        Charge initialCharge = Charge.of(initialValue, initialUnit);

        // When
        double actualValue = initialCharge.toUnit(targetUnit).getValue();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue, withPrecision(DELTA));
    }

    private static Stream<Arguments> conversionTestData() {
        return Stream.of(
                // MC -> kC (x 10^3)
                arguments(0.001, ChargeUnits.MEGACOULOMB, ChargeUnits.KILOCOULOMB, 1.0),
                // kC -> C (x 10^3)
                arguments(2.5, ChargeUnits.KILOCOULOMB, ChargeUnits.COULOMB, 2500.0),
                // C -> mC (x 10^3)
                arguments(0.005, ChargeUnits.COULOMB, ChargeUnits.MILLICOULOMB, 5.0),
                // mC -> µC (x 10^3)
                arguments(0.12, ChargeUnits.MILLICOULOMB, ChargeUnits.MICROCOULOMB, 120.0),
                arguments(0.003, ChargeUnits.MICROCOULOMB, ChargeUnits.NANOCOULOMB, 3.0),
                // nC -> pC (x 10^3)
                arguments(1.0, ChargeUnits.NANOCOULOMB, ChargeUnits.PICOCOULOMB, 1000.0),
                // C -> µC (x 10^6)
                arguments(0.000004, ChargeUnits.COULOMB, ChargeUnits.MICROCOULOMB, 4.0),
                // pC -> nC (x 10^-3)
                arguments(5000.0, ChargeUnits.PICOCOULOMB, ChargeUnits.NANOCOULOMB, 5.0)
        );
    }
}