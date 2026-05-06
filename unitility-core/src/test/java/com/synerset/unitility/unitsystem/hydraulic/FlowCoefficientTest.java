package com.synerset.unitility.unitsystem.hydraulic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class FlowCoefficientTest {

    @Test
    @DisplayName("should identify Kv as base unit")
    void shouldIdentifyBaseUnit() {
        // Given & When
        FlowCoefficientUnit baseUnit = FlowCoefficientUnits.KV;

        // Then
        assertThat(baseUnit.getBaseUnit()).isEqualTo(baseUnit);
    }

    @Test
    @DisplayName("should convert between Kv and Cv")
    void shouldConvertBetweenKvAndCv() {
        // Given
        FlowCoefficient kv = FlowCoefficient.ofKv(1.0);

        // When
        FlowCoefficient cv = kv.toCv();

        // Then
        assertThat(cv.getInCv()).isCloseTo(1.167, within(0.001));
        assertThat(cv.toKv().getInKv()).isCloseTo(1.0, within(0.001));
    }

    @Test
    @DisplayName("should consider equal flow coefficients in different units")
    void shouldBeEqualAcrossUnits() {
        // Given
        FlowCoefficient inKv = FlowCoefficient.ofKv(1.0);
        // 1 Kv = 1/0.85667 CV ≈ 1.16737 CV
        FlowCoefficient inCv = FlowCoefficient.ofCv(1.0 / 0.85667);

        // Then
        assertThat(inKv).isEqualTo(inCv);
    }

    @Test
    @DisplayName("should convert to base unit correctly")
    void shouldConvertToBaseUnit() {
        // Given
        FlowCoefficient cv = FlowCoefficient.ofCv(10.0);

        // When
        FlowCoefficient base = cv.toBaseUnit();

        // Then
        assertThat(base.getUnit()).isEqualTo(FlowCoefficientUnits.KV);
        assertThat(base.getValue()).isCloseTo(8.567, within(0.001));
    }
}
