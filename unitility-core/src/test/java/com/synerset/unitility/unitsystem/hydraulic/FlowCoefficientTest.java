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

    @Test
    @DisplayName("should convert between Av in m² and Kv, one Av being 36 000 Kv")
    void shouldConvertBetweenAvAndKv() {
        // Given
        // q = Av·√(Δp/ρ). At Δp = 1 bar and ρ = 1000 kg/m³ the root is 10, so 1 m² passes 10 m³/s = 36 000 m³/h = 36 000 Kv.
        FlowCoefficient av = FlowCoefficient.ofAv(1.0);

        // When
        FlowCoefficient kv = av.toKv();

        // Then
        assertThat(kv.getInKv()).isEqualTo(36000.0);
        assertThat(FlowCoefficient.ofKv(2.5).getInAv()).isCloseTo(2.5 / 36000, within(1E-20));
        assertThat(FlowCoefficient.ofKv(2.5).toAv().toKv().getInKv()).isCloseTo(2.5, within(1E-14));
    }

    @Test
    @DisplayName("should convert between Av and Cv through Kv")
    void shouldConvertBetweenAvAndCv() {
        // Given a Cv of 1: 0.8649776554423018 Kv, which is 2.40271570956e-5 m² of Av
        FlowCoefficient cv = FlowCoefficient.ofCv(1.0);

        // Then
        assertThat(cv.getInAv()).isCloseTo(0.8649776554423018 / 36000, within(1E-19));
        assertThat(FlowCoefficient.ofAv(cv.getInAv()).getInCv()).isCloseTo(1.0, within(1E-13));
    }

    @Test
    @DisplayName("should parse Av from the symbols Av and m²")
    void shouldParseAv() {
        assertThat(FlowCoefficient.of(3, "Av")).isEqualTo(FlowCoefficient.ofAv(3));
        assertThat(FlowCoefficient.of(3, "av")).isEqualTo(FlowCoefficient.ofAv(3));
        assertThat(FlowCoefficient.of(3, "m²")).isEqualTo(FlowCoefficient.ofAv(3));
        assertThat(FlowCoefficient.of(3, "m2")).isEqualTo(FlowCoefficient.ofAv(3));
        assertThat(FlowCoefficient.of(3, "Kv")).isEqualTo(FlowCoefficient.ofKv(3));
    }

}
