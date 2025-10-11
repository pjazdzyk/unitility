package com.synerset.unitility.unitsystem.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ValueFormatterTest {

    static Stream<Arguments> testDoubleSeed() {
        return Stream.of(
                Arguments.of(-1.0, "-1"),
                Arguments.of(0, "0"),
                Arguments.of(0.123456789, "0.123"),
                Arguments.of(0.0123456789, "0.0123"),
                Arguments.of(0.000000123456789, "0.000000123"),
                Arguments.of(0.001000123456789, "0.001"),
                Arguments.of(-0.001000123456789, "-0.001"),
                Arguments.of(10.123456789, "10.123"),
                Arguments.of(10.123556789, "10.124"),
                Arguments.of(0.000000123556789, "0.000000124")
        );
    }

    @ParameterizedTest
    @MethodSource("testDoubleSeed")
    @DisplayName("should properly format: truncate and round up double values with respect of relevant digits count")
    void shouldFormatAndTruncateDoubleInputWithRespectOfRelevantDigits(double inputDouble, String expectedFormattedDoubleAsString) {
        // Given
        int relevantDigits = 3;

        // When
        String actualFormattedDoubleAsString = ValueFormatter.toStringWithRelevantDigits(inputDouble, relevantDigits);

        // Then
        assertThat(actualFormattedDoubleAsString).isEqualTo(expectedFormattedDoubleAsString);
    }

    static Stream<Arguments> lowRelDigitsSeed() {
        return Stream.of(
                Arguments.of(10.6, 0, "11"),
                Arguments.of(10.1234, 1, "10.1"),
                Arguments.of(10.1234, 2, "10.12"),
                Arguments.of(10.1234, 3, "10.123"),
                Arguments.of(0.0011234, 1, "0.001"),
                Arguments.of(0.0011234, 0, "0"),
                Arguments.of(1E-12, 0, "0")
        );
    }

    @ParameterizedTest
    @MethodSource("lowRelDigitsSeed")
    @DisplayName("should properly format: truncate and round up double values for relevant digits < 3")
    void shouldFormatAndTruncateDoubleInput_whenRelevantDigitsAreLowerThan3(double inputDouble, int relDigits, String expectedFormattedDoubleAsString) {
        // Given
        // When
        String actualFormattedDoubleAsString = ValueFormatter.toStringWithRelevantDigits(inputDouble, relDigits);

        // Then
        assertThat(actualFormattedDoubleAsString).isEqualTo(expectedFormattedDoubleAsString);
    }

    static Stream<Arguments> precisionRoundingSeed() {
        return Stream.of(
                // < value , precision , expected >
                Arguments.of(0.01567, 0.01, "0.02"),
                Arguments.of(0.014, 0.01, "0.01"),
                Arguments.of(0.015, 0.01, "0.02"),
                Arguments.of(0.025, 0.01, "0.03"),
                Arguments.of(123.456, 0.1, "123.5"),
                Arguments.of(123.44, 0.1, "123.4"),
                Arguments.of(999.99, 1, "1000"),
                Arguments.of(999.99, 0.01, "999.99"),
                Arguments.of(0.00044, 0.0001, "0.0004"),
                Arguments.of(0.00046, 0.0001, "0.0005"),
                Arguments.of(-0.01567, 0.01, "-0.02"),
                Arguments.of(-123.456, 0.1, "-123.5"),
                Arguments.of(10, 0.1, "10"),
                Arguments.of(10, 1, "10"),
                Arguments.of(10, 0.01, "10")
        );
    }

    @ParameterizedTest
    @MethodSource("precisionRoundingSeed")
    @DisplayName("should properly round value with respect to given precision (epsilon)")
    void shouldProperlyRoundValueWithGivenPrecision(double inputValue, double precision, String expectedValue) {
        // When
        String actualValue = ValueFormatter.toStringWithPrecision(inputValue, precision);

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

}