package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DataSizeTest {

    private static final double DELTA = 1E-12;

    @Test
    @DisplayName("DataSize: should have BYTE as base unit")
    void shouldHaveByteAsBaseUnit() {
        // Given
        DataSizeUnit expectedBaseUnit = DataSizeUnits.BYTE;

        // When
        DataSize dataSize = DataSize.ofGigabytes(10);
        DataSizeUnit actualBaseUnit = dataSize.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("DataSize: should properly convert between all defined units, including base unit (Byte)")
    void shouldProperlyConvertBetweenAllUnits() {
        // Given
        // Using 1 Petabyte, which is the largest safe unit (2^53 bits)
        DataSize initialSize = DataSize.ofPetabytes(1.0);

        // When
        double bytes = initialSize.getInBytes();
        double bits = initialSize.getInBits();
        double kilobytes = initialSize.toKilobytes().getValue();
        double megabytes = initialSize.toMegabytes().getValue();
        double gigabytes = initialSize.toGigabytes().getValue();
        double terabytes = initialSize.toTerabytes().getValue();
        double petabytes = initialSize.toPetabytes().getValue();

        // Convert back to PB to test conversion stability
        DataSize actualFromBytes = DataSize.ofBytes(bytes).toPetabytes();

        // Then
        // 1 PB = 1024^5 B
        double factor5 = Math.pow(1024.0, 5);
        double factor4 = Math.pow(1024.0, 4);
        double factor3 = Math.pow(1024.0, 3);
        double factor2 = Math.pow(1024.0, 2);
        double factor1 = 1024.0;

        assertThat(bytes).isEqualTo(factor5, withPrecision(DELTA));
        // 1 PB in Bits = (1024^5) * 8
        assertThat(bits).isEqualTo(factor5 * 8.0, withPrecision(DELTA));

        assertThat(kilobytes).isEqualTo(factor4, withPrecision(DELTA));
        assertThat(megabytes).isEqualTo(factor3, withPrecision(DELTA));
        assertThat(gigabytes).isEqualTo(factor2, withPrecision(DELTA));
        assertThat(terabytes).isEqualTo(factor1, withPrecision(DELTA));
        assertThat(petabytes).isEqualTo(1.0, withPrecision(DELTA));

        // Test conversion stability
        assertThat(actualFromBytes.getValue()).isEqualTo(1.0, withPrecision(DELTA));
    }

    @Test
    @DisplayName("DataSize: should properly convert from Bit to Byte and vice versa")
    void shouldProperlyConvertBitToByte() {
        // Given
        // 48 bits, which is exactly 6 Bytes and a valid value
        DataSize initialSizeInBits = DataSize.ofBits(48);

        // When
        DataSize actualInBytes = initialSizeInBits.toBytes();
        double actualBytesValue = initialSizeInBits.getInBytes();

        // Convert back to Bits
        DataSize actualInBits = actualInBytes.toBits();
        double actualBitsValue = actualInBytes.getInBits();

        // Then
        DataSize expectedByte = DataSize.of(6, DataSizeUnits.BYTE);

        assertThat(actualInBytes).isEqualTo(expectedByte);
        assertThat(actualBytesValue).isEqualTo(6.0, withPrecision(DELTA));
        assertThat(actualInBits.getValue()).isEqualTo(48.0, withPrecision(DELTA));
        assertThat(actualBitsValue).isEqualTo(48.0, withPrecision(DELTA));
    }

    @ParameterizedTest(name = "Value {0} {1} should be valid")
    @MethodSource("validDataSizeValues")
    @DisplayName("DataSize: should PASS validation for valid data sizes")
    void shouldPassValidationForValidDataSizes(double value, DataSizeUnit unit) {
        // When & Then
        // Should pass without throwing an exception
        DataSize dataSize = DataSize.of(value, unit);
        assertThat(dataSize.getValue()).isEqualTo(value);
    }

    private static Stream<Arguments> validDataSizeValues() {
        return Stream.of(
                // Valid bit count (multiple of 8 and non-fractional)
                arguments(8.0, DataSizeUnits.BIT),
                arguments(48.0, DataSizeUnits.BIT),

                // Valid Byte-based units (must be a whole number of Bytes)
                arguments(1.0, DataSizeUnits.BYTE),
                arguments(10.0, DataSizeUnits.BYTE),
                arguments(1.0, DataSizeUnits.KILOBYTE),
                arguments(1.0, DataSizeUnits.PETABYTE),
                arguments(0.0, DataSizeUnits.BIT),
                arguments(0.0, DataSizeUnits.BYTE)
        );
    }

    @ParameterizedTest(name = "Validation: Value {0} {1} should throw IllegalArgumentException")
    @MethodSource("invalidDataSizeValues")
    @DisplayName("DataSize: should FAIL validation for invalid data sizes")
    void shouldFailValidationForInvalidDataSizes(double value, DataSizeUnit unit, String expectedError) {
        // When & Then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .as("Expected error: " + expectedError)
                .isThrownBy(() -> DataSize.of(value, unit));
    }

    private static Stream<Arguments> invalidDataSizeValues() {
        return Stream.of(
                // BIT: Fractional bit count (Rule 1a)
                arguments(7.5, DataSizeUnits.BIT, "Fractional number of bits"),

                // BIT: Not a multiple of 8 (Rule 1b)
                arguments(7.0, DataSizeUnits.BIT, "Not a multiple of 8 bits"),
                arguments(9.0, DataSizeUnits.BIT, "Not a multiple of 8 bits"),

                // BYTE-BASED: Fractional number of Bytes (Rule 2)
                arguments(1.5, DataSizeUnits.BYTE, "Fractional number of Bytes"),
                // 1.001 KB = 1025.024 Bytes (Fractional)
                arguments(1.001, DataSizeUnits.KILOBYTE, "Fractional number of Bytes"),

                // Exceeds safe integer limit (Rule 3)
                // 2.1 PB > 2^53 Bytes
                arguments(2.1, DataSizeUnits.PETABYTE, "Exceeds safe integer limit"),
                // 2^53 + 1 Byte
                arguments(Math.pow(2, 53) + 8, DataSizeUnits.BYTE, "Exceeds safe integer limit")
        );
    }
}