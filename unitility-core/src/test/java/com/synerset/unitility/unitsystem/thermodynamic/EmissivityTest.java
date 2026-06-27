package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.common.RatioUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class EmissivityTest {

    @Test
    @DisplayName("Emissivity: should convert decimal to percent")
    void shouldConvertDecimalToPercent() {
        // Given
        Emissivity emissivity = Emissivity.ofDecimal(0.85);

        // When
        double inPercent = emissivity.getInPercent();

        // Then
        assertThat(inPercent).isEqualTo(85.0, withPrecision(1E-9));
    }

    @Test
    @DisplayName("Emissivity: should be equal across units")
    void shouldBeEqualAcrossUnits() {
        // Given
        Emissivity asDecimal = Emissivity.ofDecimal(0.9);
        Emissivity asPercent = Emissivity.ofPercentage(90.0);

        // Then
        assertThat(asDecimal).isEqualTo(asPercent);
    }

    @Test
    @DisplayName("Emissivity: should resolve unit from symbol")
    void shouldResolveUnitFromSymbol() {
        // Given
        Emissivity emissivity = Emissivity.of(50.0, "%");

        // Then
        assertThat(emissivity.getUnit()).isEqualTo(RatioUnits.PERCENT);
        assertThat(emissivity.getInDecimal()).isEqualTo(0.5, withPrecision(1E-9));
    }
}
