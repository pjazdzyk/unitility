package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RelativeDensityTest {

    @Test
    @DisplayName("should be a dimensionless quantity carrying its value")
    void shouldCarryValue() {
        RelativeDensity rd = RelativeDensity.of(0.62);
        assertThat(rd.getValue()).isEqualTo(0.62);
        assertThat(rd.getInDimensionless()).isEqualTo(0.62);
        assertThat(rd.getUnit().getBaseUnit()).isEqualTo(RelativeDensityUnits.DIMENSIONLESS);
    }

    @Test
    @DisplayName("two equal values are equal")
    void equality() {
        assertThat(RelativeDensity.of(1.05)).isEqualTo(RelativeDensity.of(1.05));
    }

    @Test
    @DisplayName("arithmetic via CalculableQuantity works")
    void arithmetic() {
        RelativeDensity sum = RelativeDensity.of(0.6).plus(0.4);
        assertThat(sum.getValue()).isEqualTo(1.0);
    }

    @Test
    @DisplayName("parses from a (blank) symbol")
    void parsesFromSymbol() {
        assertThat(RelativeDensity.of(0.7, "").getValue()).isEqualTo(0.7);
    }
}
