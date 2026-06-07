package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class VapourQualityTest {

    @Test
    @DisplayName("should have FRACTION as base unit")
    void shouldHaveFractionAsBaseUnit() {
        VapourQuality q = VapourQuality.ofPercent(50);
        assertThat(q.getUnit().getBaseUnit()).isEqualTo(VapourQualityUnits.FRACTION);
    }

    @Test
    @DisplayName("should convert fraction <-> percent exactly")
    void shouldConvertFractionPercent() {
        assertThat(VapourQuality.ofFraction(0.5).getInPercent()).isEqualTo(50.0, withPrecision(1e-13));
        assertThat(VapourQuality.ofPercent(25.0).getInFraction()).isEqualTo(0.25, withPrecision(1e-13));
    }

    @Test
    @DisplayName("should compare equal across fraction and percent")
    void shouldCompareEqualAcrossUnits() {
        assertThat(VapourQuality.ofFraction(0.3)).isEqualTo(VapourQuality.ofPercent(30.0));
    }

    @Test
    @DisplayName("physical limits are 0 and 1")
    void physicalLimits() {
        assertThat(VapourQuality.PHYSICAL_MIN_LIMIT.getInFraction()).isEqualTo(0.0);
        assertThat(VapourQuality.PHYSICAL_MAX_LIMIT.getInFraction()).isEqualTo(1.0);
    }

    @Test
    @DisplayName("parses from a unit symbol")
    void parsesFromSymbol() {
        assertThat(VapourQuality.of(40.0, "%").getInFraction()).isEqualTo(0.40, withPrecision(1e-13));
        assertThat(VapourQuality.of(0.4, "").getInPercent()).isEqualTo(40.0, withPrecision(1e-13));
    }
}
