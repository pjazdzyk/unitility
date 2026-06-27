package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.common.RatioUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class AbsorptivityTest {

    @Test
    @DisplayName("Absorptivity: should convert decimal to percent")
    void shouldConvertDecimalToPercent() {
        // Given
        Absorptivity absorptivity = Absorptivity.ofDecimal(0.3);

        // When
        double inPercent = absorptivity.getInPercent();

        // Then
        assertThat(inPercent).isEqualTo(30.0, withPrecision(1E-9));
    }

    @Test
    @DisplayName("Absorptivity: should be equal across units")
    void shouldBeEqualAcrossUnits() {
        // Given
        Absorptivity asDecimal = Absorptivity.ofDecimal(0.25);
        Absorptivity asPercent = Absorptivity.ofPercentage(25.0);

        // Then
        assertThat(asDecimal).isEqualTo(asPercent);
    }

    @Test
    @DisplayName("Absorptivity: should resolve unit from symbol")
    void shouldResolveUnitFromSymbol() {
        // Given
        Absorptivity absorptivity = Absorptivity.of(40.0, "%");

        // Then
        assertThat(absorptivity.getUnit()).isEqualTo(RatioUnits.PERCENT);
        assertThat(absorptivity.getInDecimal()).isEqualTo(0.4, withPrecision(1E-9));
    }
}
