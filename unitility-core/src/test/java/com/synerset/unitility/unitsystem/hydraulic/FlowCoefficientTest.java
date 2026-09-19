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
        // 1 Kv = 1 / 0.864 977 655 442 3... Cv = 1.156 099 228 353 656... Cv (Cv: US gpm at 1 psi; Kv: m³/h at 1 bar;
        // NIST SP 811 gallon, psi and bar). 4.1.0 used 0.85667 and this test pinned 1.167.
        assertThat(cv.getInCv()).isCloseTo(1.1560992283536565, within(1E-15));
        assertThat(cv.toKv().getInKv()).isCloseTo(1.0, within(0.001));
    }

    @Test
    @DisplayName("should consider equal flow coefficients in different units")
    void shouldBeEqualAcrossUnits() {
        // Given
        // 1 Cv = 0.864 977 655 442 301 8 Kv (was 0.85667 in 4.1.0, 0.96 % low).
        FlowCoefficient inKv = FlowCoefficient.ofKv(0.8649776554423018);
        FlowCoefficient inCv = FlowCoefficient.ofCv(1.0);

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
        // 10 Cv = 8.649 776 554 423 0... Kv; 4.1.0 pinned 8.567 from its factor 0.85667.
        assertThat(base.getValue()).isCloseTo(8.649776554423018, within(1E-14));
    }
}
