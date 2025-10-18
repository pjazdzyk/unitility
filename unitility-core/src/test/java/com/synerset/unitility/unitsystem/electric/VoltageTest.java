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

class VoltageTest {

    private static final double DELTA = 1E-12;

    @Test
    @DisplayName("should have VOLT as base unit")
    void shouldHaveVoltAsBaseUnit() {
        // Given
        VoltageUnit expectedBaseUnit = VoltageUnits.VOLT;

        // When
        Voltage voltageInKilovolts = Voltage.ofKilovolts(10.0);
        VoltageUnit actualBaseUnit = voltageInKilovolts.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should correctly compare voltages in different units (equality check)")
    void shouldCorrectlyCompareVoltagesInDifferentUnits() {
        // Given
        Voltage oneKilovolt = Voltage.ofKilovolts(1.0);
        Voltage oneThousandVolts = Voltage.ofVolts(1000.0);

        // When & Then
        assertThat(oneKilovolt).isEqualTo(oneThousandVolts);
    }

    // --- TESTY KONWERSJI ---

    @Test
    @DisplayName("should properly convert Kilovolts (kV) to Volts (V) and Gigavolts (GV)")
    void shouldProperlyConvertFromKilovolts() {
        // Given
        Voltage initialVoltage = Voltage.ofKilovolts(25.0); // 25 kV

        // When
        double actualInVolts = initialVoltage.getInVolts();
        double actualInMillivolts = initialVoltage.getInMillivolts();
        double actualInGigavolts = initialVoltage.getInGigavolts();
        Voltage actualBackTokV = initialVoltage.toVolts().toKilovolts();

        // Then
        assertThat(actualInVolts).isEqualTo(25_000.0, withPrecision(DELTA));
        assertThat(actualInMillivolts).isEqualTo(25_000_000.0, withPrecision(DELTA));
        assertThat(actualInGigavolts).isEqualTo(0.000025, withPrecision(DELTA));
        assertThat(actualBackTokV).isEqualTo(initialVoltage);
        assertThat(actualBackTokV.getValue()).isEqualTo(25.0, withPrecision(DELTA));
    }

    @Test
    @DisplayName("should properly convert Microvolts (µV) to Millivolts (mV) and Volts (V)")
    void shouldProperlyConvertFromMicrovolts() {
        // Given
        Voltage initialVoltage = Voltage.ofMicrovolts(5_000.0); // 5000 µV

        // When
        double actualInMillivolts = initialVoltage.getInMillivolts();
        double actualInVolts = initialVoltage.getInVolts();
        Voltage actualBackToMicrovolts = initialVoltage.toVolts().toMicrovolts();

        // Then
        assertThat(actualInMillivolts).isEqualTo(5.0, withPrecision(DELTA));
        assertThat(actualInVolts).isEqualTo(0.005, withPrecision(DELTA));
        assertThat(actualBackToMicrovolts.getValue()).isEqualTo(5_000.0, withPrecision(DELTA));
    }

    @ParameterizedTest(name = "Convert {0} {1} to {2} should yield {3}")
    @MethodSource("conversionTestData")
    @DisplayName("should correctly perform conversion for all defined units")
    void shouldCorrectlyPerformConversionForAllUnits(double initialValue, VoltageUnit initialUnit, VoltageUnit targetUnit, double expectedValue) {
        // Given
        Voltage initialVoltage = Voltage.of(initialValue, initialUnit);

        // When
        double actualValue = initialVoltage.toUnit(targetUnit).getValue();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue, withPrecision(DELTA));
    }

    private static Stream<Arguments> conversionTestData() {
        return Stream.of(
                // MV -> V (x 10^6)
                arguments(5.0, VoltageUnits.MEGAVOLT, VoltageUnits.VOLT, 5_000_000.0),
                // GV -> V (x 10^9)
                arguments(0.001, VoltageUnits.GIGAVOLT, VoltageUnits.VOLT, 1_000_000.0),
                // V -> mV (x 10^3)
                arguments(1.23, VoltageUnits.VOLT, VoltageUnits.MILLIVOLT, 1230.0),
                // µV -> V (x 10^-6)
                arguments(400_000.0, VoltageUnits.MICROVOLT, VoltageUnits.VOLT, 0.4),
                // V -> µV (x 10^6)
                arguments(0.0001, VoltageUnits.VOLT, VoltageUnits.MICROVOLT, 100.0),
                // GV -> kV (x 10^6)
                arguments(0.5, VoltageUnits.GIGAVOLT, VoltageUnits.KILOVOLT, 500_000.0),
                // mV -> µV (x 10^3)
                arguments(2.0, VoltageUnits.MILLIVOLT, VoltageUnits.MICROVOLT, 2000.0)
        );
    }
}