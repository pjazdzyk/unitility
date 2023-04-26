package com.synerset.unitility.unitsystem.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ValueFormatterTest {

    @ParameterizedTest
    @MethodSource("testDoubleSeed")
    @DisplayName("should properly format: truncate and round up double values with respect of relevant digits count")
    void shouldFormatAndTruncateDoubleInputWithRespectOfRelevantDigits(String inputString, String expectedFormattedDoubleAsString) {
        // Given
        int relevantDigits = 6;
        BigDecimal inputBigDecimal = new BigDecimal(inputString);

        // When
        String actualFormattedDoubleAsString = ValueFormatter.formatDoubleToRelevantPrecision(inputBigDecimal, relevantDigits);



        // Then
        assertThat(actualFormattedDoubleAsString).isEqualTo(expectedFormattedDoubleAsString);

    }

    static Stream<Arguments> testDoubleSeed() {
        return Stream.of(
                Arguments.of("-1.0", "-1"),
                Arguments.of("0", "0"),
                Arguments.of("0.123456789", "0.123457"),
                Arguments.of("0.0123456789", "0.0123457"),
                Arguments.of("0.000000123456789", "0.000000123457"),
                Arguments.of("0.001000123456789", "0.00100012"),
                Arguments.of("-0.001000123456789", "-0.00100012"),
                Arguments.of("10.123456789", "10.123457"),
                Arguments.of("10.123556789", "10.123557"),
                Arguments.of("0.000000123556789", "0.000000123557")
        );
    }

}