package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("EnergyDensity (energy per volume) tests")
class EnergyDensityTest {

    @Test
    @DisplayName("should have J/m³ as the base unit")
    void shouldHaveJoulePerCubicMeterAsBaseUnit() {
        // Given
        EnergyDensity ed = EnergyDensity.ofMegajoulePerCubicMeter(38.547291);

        // When
        EnergyDensityUnit baseUnit = ed.getUnit().getBaseUnit();

        // Then
        assertThat(baseUnit).isEqualTo(EnergyDensityUnits.JOULE_PER_CUBIC_METER);
        assertThat(ed.getBaseValue()).isEqualTo(38_547_291.0);
    }

    @Test
    @DisplayName("should convert MJ/m³ to other metric units exactly")
    void shouldConvertMetricUnits() {
        // Given
        EnergyDensity ed = EnergyDensity.ofMegajoulePerCubicMeter(36.0);

        // When / Then
        assertThat(ed.getInJoulePerCubicMeter()).isEqualTo(36_000_000.0);
        assertThat(ed.getInKilojoulePerCubicMeter()).isEqualTo(36_000.0);
        // 36 MJ/m³ = 10 kWh/m³ (1 kWh = 3.6 MJ)
        assertThat(ed.getInKilowattHourPerCubicMeter()).isCloseTo(10.0, org.assertj.core.api.Assertions.within(1E-9));
    }

    @Test
    @DisplayName("should convert to BTU/ft³ with the exact IT factor")
    void shouldConvertToBtuPerCubicFoot() {
        // Given — exactly one BTU_IT per cubic foot.
        EnergyDensity ed = EnergyDensity.ofBTUPerCubicFoot(1.0);

        // When / Then — 1 BTU_IT / ft³ = 37258.945802… J/m³
        assertThat(ed.getInJoulePerCubicMeter()).isCloseTo(37258.9458020, org.assertj.core.api.Assertions.within(1E-3));
        assertThat(ed.getInBTUPerCubicFoot()).isEqualTo(1.0);
    }

    @Test
    @DisplayName("should be equal across units representing the same base value")
    void shouldBeEqualAcrossUnits() {
        // Given
        EnergyDensity inMj = EnergyDensity.ofMegajoulePerCubicMeter(3.6);
        EnergyDensity inKwh = EnergyDensity.ofKilowattHourPerCubicMeter(1.0);

        // Then — 3.6 MJ/m³ == 1 kWh/m³
        assertThat(inMj).isEqualTo(inKwh);
    }

    @Test
    @DisplayName("should resolve units from symbols (case/spacing-insensitive)")
    void shouldResolveFromSymbol() {
        assertThat(EnergyDensity.of(50.0, "MJ/m³").getUnit()).isEqualTo(EnergyDensityUnits.MEGAJOULE_PER_CUBIC_METER);
        assertThat(EnergyDensity.of(1000.0, "BTU/ft³").getUnit()).isEqualTo(EnergyDensityUnits.BTU_PER_CUBIC_FOOT);
    }
}
