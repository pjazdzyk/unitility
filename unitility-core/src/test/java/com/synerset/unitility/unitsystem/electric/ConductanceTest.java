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

class ConductanceTest {

    private static final double DELTA = 1E-11;

    @Test
    @DisplayName("should have SIEMENS as base unit")
    void shouldHaveSiemensAsBaseUnit() {
        // Given
        ConductanceUnit expectedBaseUnit = ConductanceUnits.SIEMENS;

        // When
        Conductance conductanceInMillisiemens = Conductance.ofMilliseimens(10.0);
        ConductanceUnit actualBaseUnit = conductanceInMillisiemens.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should correctly compare conductances in different units (equality check using base value comparison)")
    void shouldCorrectlyCompareConductancesInDifferentUnits() {
        // Given
        // 1 mS
        Conductance oneMillisiemens = Conductance.ofMilliseimens(1.0);
        // 1000 µS
        Conductance oneThousandMicrosiemens = Conductance.ofMicroseimens(1000.0);
        // 0.001 S
        Conductance oneThousandthSiemens = Conductance.ofSeimens(0.001);

        // When & Then
        double baseValue1 = oneMillisiemens.toBaseUnit().getValue();
        double baseValue2 = oneThousandMicrosiemens.toBaseUnit().getValue();
        double baseValue3 = oneThousandthSiemens.toBaseUnit().getValue();

        assertThat(baseValue1).isEqualTo(baseValue2, withPrecision(DELTA));
        assertThat(baseValue1).isEqualTo(baseValue3, withPrecision(DELTA));
    }

    @Test
    @DisplayName("should properly convert Siemens (S) to lower units (mS, µS, nS, pS)")
    void shouldProperlyConvertFromSiemensToLowerUnits() {
        // Given
        Conductance initialConductance = Conductance.ofSeimens(0.000005); // 5 µS

        // When
        double actualInMillisiemens = initialConductance.getInMilliseimens(); // x 10^3
        double actualInMicrosiemens = initialConductance.getInMicroseimens(); // x 10^6
        double actualInNanosiemsns = initialConductance.getInNanoseimens();   // x 10^9
        double actualInPicoseimens = initialConductance.getInPicoseimens();   // x 10^12

        // Then
        assertThat(actualInMillisiemens).isEqualTo(0.005, withPrecision(DELTA));
        assertThat(actualInMicrosiemens).isEqualTo(5.0, withPrecision(DELTA));
        assertThat(actualInNanosiemsns).isEqualTo(5_000.0, withPrecision(DELTA));
        assertThat(actualInPicoseimens).isEqualTo(5_000_000.0, withPrecision(1E-9));
    }

    @Test
    @DisplayName("should properly convert Microseimens (µS) to Siemens (S) and Kiloseimens (kS)")
    void shouldProperlyConvertToUpperUnits() {
        // Given
        Conductance initialConductance = Conductance.ofMicroseimens(2000.0); // 2000 µS = 2 mS = 0.002 S

        // When
        double actualInMillisiemens = initialConductance.getInMilliseimens(); // x 10^-3
        double actualInSeimens = initialConductance.getInSeimens();         // x 10^-6
        double actualInKiloseimens = initialConductance.getInKiloseimens(); // x 10^-9

        // Then
        assertThat(actualInMillisiemens).isEqualTo(2.0, withPrecision(DELTA));
        assertThat(actualInSeimens).isEqualTo(0.002, withPrecision(DELTA));
        assertThat(actualInKiloseimens).isEqualTo(0.000002, withPrecision(DELTA)); // 2E-6 kS
    }


    @ParameterizedTest(name = "Convert {0} {1} to {2} should yield {3}")
    @MethodSource("conversionTestData")
    @DisplayName("should correctly perform conversion for all defined units across the full range")
    void shouldCorrectlyPerformConversionForAllUnits(double initialValue, ConductanceUnit initialUnit, ConductanceUnit targetUnit, double expectedValue) {
        // Given
        Conductance initialConductance = Conductance.of(initialValue, initialUnit);

        // When
        double actualValue = initialConductance.toUnit(targetUnit).getValue();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue, withPrecision(DELTA));
    }

    private static Stream<Arguments> conversionTestData() {
        return Stream.of(
                // kS -> S (x 10^3)
                arguments(0.05, ConductanceUnits.KILOSIEMENS, ConductanceUnits.SIEMENS, 50.0),
                // S -> mS (x 10^3)
                arguments(0.012, ConductanceUnits.SIEMENS, ConductanceUnits.MILLISIEMENS, 12.0),
                // mS -> µS (x 10^3)
                arguments(4.5, ConductanceUnits.MILLISIEMENS, ConductanceUnits.MICROSIEMENS, 4500.0),
                // µS -> nS (x 10^3)
                arguments(0.9, ConductanceUnits.MICROSIEMENS, ConductanceUnits.NANOSIEMENS, 900.0),
                // nS -> pS (x 10^3)
                arguments(10.0, ConductanceUnits.NANOSIEMENS, ConductanceUnits.PICOSIEMENS, 10_000.0),

                // pS -> µS (x 10^-6)
                arguments(3_000_000.0, ConductanceUnits.PICOSIEMENS, ConductanceUnits.MICROSIEMENS, 3.0),
                // mS -> S (x 10^-3)
                arguments(500.0, ConductanceUnits.MILLISIEMENS, ConductanceUnits.SIEMENS, 0.5),
                // S -> kS (x 10^-3)
                arguments(1500.0, ConductanceUnits.SIEMENS, ConductanceUnits.KILOSIEMENS, 1.5)
        );
    }
}