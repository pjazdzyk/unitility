package com.synerset.unitsystem.pressure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PressureTest {

    // Given
    private static final Pressure TEST_PASCAL_PRESS = Pressure.pascal(100_000.0);

    @Test
    @DisplayName("should convert Pascals to Bars and vice versa")
    void shouldConvertPascalsToBarsAndViceVersa() {
        // When
        Pressure actualUnitBar = TEST_PASCAL_PRESS.toBar();
        Pressure actualUnitPa = actualUnitBar.toPascal();

        // Then
        assertThat(actualUnitPa).isEqualTo(TEST_PASCAL_PRESS);
        Bar expectedValueInBar = Pressure.bar(1.0);
        assertThat(actualUnitBar).isEqualTo(expectedValueInBar);
    }

    @Test
    @DisplayName("should convert Pascals to Hecto Pascals and vice versa")
    void shouldConvertPascalsToHectoPascalsAndViceVersa() {
        // When
        Pressure actualUnitHPa = TEST_PASCAL_PRESS.toHectoPascal();
        Pressure actualUnitPa = actualUnitHPa.toPascal();

        // Then
        assertThat(actualUnitPa).isEqualTo(TEST_PASCAL_PRESS);
        HectoPascal expectedValueInHPa = Pressure.hectoPascal(1000.0);
        assertThat(actualUnitHPa).isEqualTo(expectedValueInHPa);
    }

    @Test
    @DisplayName("should convert Pascals to Mega Pascals and vice versa")
    void shouldConvertPascalsToMegaPascalsAndViceVersa() {
        // When
        Pressure actualUnitMPa = TEST_PASCAL_PRESS.toMegaPascal();
        Pressure actualUnitPa = actualUnitMPa.toPascal();

        // Then
        assertThat(actualUnitPa).isEqualTo(TEST_PASCAL_PRESS);
        MegaPascal expectedValueInMPa = Pressure.megaPascal(0.1);
        assertThat(actualUnitMPa).isEqualTo(expectedValueInMPa);
    }

}