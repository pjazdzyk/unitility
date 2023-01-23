package com.synerset.unitsystem.pressure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PressureTest {

    // Given
    private static final double TEST_PRESS_VALUE = 100_000.0;
    private static final Pressure TEST_PASCAL_PRESS = Pressure.pascal(TEST_PRESS_VALUE);

    @Test
    @DisplayName("should convert Pascals to Bars and vice versa")
    void shouldConvertPascalsToBarsAndViceVersa() {
        // When
        Pressure actualUnitBar = TEST_PASCAL_PRESS.toBar();
        double actualValueInBar = actualUnitBar.getValue();

        Pressure actualUnitPa = actualUnitBar.toPascal();
        double actualValueInPa = actualUnitPa.getValue();

        // Then
        assertThat(actualValueInPa).isEqualTo(TEST_PRESS_VALUE);
        double expectedValueInBar = 1.0;
        assertThat(actualValueInBar).isEqualTo(expectedValueInBar);
    }

    @Test
    @DisplayName("should convert Pascals to Hecto Pascals and vice versa")
    void shouldConvertPascalsToHectoPascalsAndViceVersa() {
        // When
        Pressure actualUnitHPa = TEST_PASCAL_PRESS.toHectoPascal();
        double actualValueInHPa = actualUnitHPa.getValue();

        Pressure actualUnitPa = actualUnitHPa.toPascal();
        double actualValueInPa = actualUnitPa.getValue();

        // Then
        assertThat(actualValueInPa).isEqualTo(TEST_PRESS_VALUE);
        double expectedValueInHPa = 1000;
        assertThat(actualValueInHPa).isEqualTo(expectedValueInHPa);
    }

    @Test
    @DisplayName("should convert Pascals to Mega Pascals and vice versa")
    void shouldConvertPascalsToMegaPascalsAndViceVersa() {
        // When
        Pressure actualUnitMPa = TEST_PASCAL_PRESS.toMegaPascal();
        double actualValueInMPa = actualUnitMPa.getValue();

        Pressure actualUnitPa = actualUnitMPa.toPascal();
        double actualValueInPa = actualUnitPa.getValue();

        // Then
        assertThat(actualValueInPa).isEqualTo(TEST_PRESS_VALUE);
        double expectedValueInMPa = 0.1;
        assertThat(actualValueInMPa).isEqualTo(expectedValueInMPa);
    }

}