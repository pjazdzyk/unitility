package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class TimeTest {

    @Test
    @DisplayName("Time: should have second as base unit")
    void shouldHaveSecondAsBaseUnit() {
        Time time = Time.ofHours(2);
        assertThat(time.getUnit().getBaseUnit()).isEqualTo(TimeUnits.SECOND);
    }

    @Test
    @DisplayName("Time: should convert hours and minutes to seconds")
    void shouldConvertToSeconds() {
        assertThat(Time.ofHours(1).getInSeconds()).isEqualTo(3600.0, withPrecision(1E-9));
        assertThat(Time.ofMinutes(1).getInSeconds()).isEqualTo(60.0, withPrecision(1E-9));
        assertThat(Time.ofMilliseconds(500).getInSeconds()).isEqualTo(0.5, withPrecision(1E-9));
        assertThat(Time.ofDays(1).getInSeconds()).isEqualTo(86400.0, withPrecision(1E-9));
    }

    @Test
    @DisplayName("Time: should compare equal across units")
    void shouldCompareEqualAcrossUnits() {
        assertThat(Time.ofMinutes(60)).isEqualTo(Time.ofHours(1));
        assertThat(Time.ofSeconds(3600)).isEqualTo(Time.ofHours(1));
    }

    @Test
    @DisplayName("Time: should resolve unit from symbol")
    void shouldResolveUnitFromSymbol() {
        assertThat(Time.of(90, "min").getInHours()).isEqualTo(1.5, withPrecision(1E-9));
    }
}
