package com.synerset.unitility.unitsystem.flow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class NormalVolumetricFlowTest {

    @Test
    @DisplayName("should have Nm³/s as base unit")
    void shouldHaveBaseUnit() {
        NormalVolumetricFlow q = NormalVolumetricFlow.ofNormalCubicMetersPerHour(3600);
        assertThat(q.getUnit().getBaseUnit()).isEqualTo(NormalVolumetricFlowUnits.NORMAL_CUBIC_METERS_PER_SECOND);
    }

    @Test
    @DisplayName("normal-referenced units convert by pure geometric/time factors")
    void normalUnitsExact() {
        assertThat(NormalVolumetricFlow.ofNormalCubicMetersPerHour(3600).getInNormalCubicMetersPerSecond())
                .isEqualTo(1.0, withPrecision(1e-12));
        assertThat(NormalVolumetricFlow.ofNormalCubicMetersPerSecond(1.0).getInNormalLitersPerMinute())
                .isEqualTo(60000.0, withPrecision(1e-9));
    }

    @Test
    @DisplayName("standard (15 °C) carries the ideal-gas temperature ratio to the normal base")
    void standardCubicMeters() {
        // 1 Sm³ (15 °C) = 273.15/288.15 Nm³ (0 °C)
        double oneStdInNormal = NormalVolumetricFlow.ofStandardCubicMetersPerHour(1.0).getInNormalCubicMetersPerHour();
        assertThat(oneStdInNormal).isEqualTo(273.15 / 288.15, withPrecision(1e-12));
    }

    @Test
    @DisplayName("scfm (60 °F) round-trips and matches the documented reference factor")
    void standardCubicFeet() {
        NormalVolumetricFlow oneScfm = NormalVolumetricFlow.ofStandardCubicFeetPerMinute(1.0);
        // round-trip
        assertThat(oneScfm.getInStandardCubicFeetPerMinute()).isEqualTo(1.0, withPrecision(1e-9));
        // 1 scf = 0.028316846592 m³ at 60 °F -> in Nm³ via 273.15/288.70556
        double expectedNm3PerMin = 0.028316846592 * (273.15 / 288.7055555555556);
        assertThat(oneScfm.getInNormalCubicMetersPerSecond())
                .isEqualTo(expectedNm3PerMin / 60.0, withPrecision(1e-15));
    }

    @Test
    @DisplayName("parses from a unit symbol")
    void parsesFromSymbol() {
        assertThat(NormalVolumetricFlow.of(3600, "Nm³/h").getInNormalCubicMetersPerSecond())
                .isEqualTo(1.0, withPrecision(1e-12));
    }
}
