package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class SpecificGasConstantTest {

    @Test
    @DisplayName("should identify J/(kg·K) as base unit")
    void shouldIdentifyBaseUnit() {
        // Given & When
        SpecificGasConstantUnit baseUnit = SpecificGasConstantUnits.JOULE_PER_KILOGRAM_KELVIN;

        // Then
        assertThat(baseUnit.getBaseUnit()).isEqualTo(baseUnit);
    }

    @Test
    @DisplayName("should convert between J/(kg·K) and kJ/(kg·K)")
    void shouldConvertBetweenUnits() {
        // Given
        SpecificGasConstant r = SpecificGasConstant.ofJoulesPerKilogramKelvin(287.0);

        // When
        SpecificGasConstant inKilojoules = r.toKilojoulesPerKilogramKelvin();

        // Then
        assertThat(inKilojoules.getInKilojoulesPerKilogramKelvin()).isCloseTo(0.287, within(1E-10));
        assertThat(inKilojoules.toJoulesPerKilogramKelvin().getInJoulesPerKilogramKelvin())
                .isCloseTo(287.0, within(1E-10));
    }

    @Test
    @DisplayName("should consider equal gas constants in different units")
    void shouldBeEqualAcrossUnits() {
        // Given
        SpecificGasConstant inJ = SpecificGasConstant.ofJoulesPerKilogramKelvin(287.0);
        SpecificGasConstant inKJ = SpecificGasConstant.ofKilojoulesPerKilogramKelvin(0.287);

        // Then
        assertThat(inJ).isEqualTo(inKJ);
    }

    @Test
    @DisplayName("should convert to base unit correctly")
    void shouldConvertToBaseUnit() {
        // Given
        SpecificGasConstant r = SpecificGasConstant.ofKilojoulesPerKilogramKelvin(0.5);

        // When
        SpecificGasConstant base = r.toBaseUnit();

        // Then
        assertThat(base.getUnit()).isEqualTo(SpecificGasConstantUnits.JOULE_PER_KILOGRAM_KELVIN);
        assertThat(base.getValue()).isCloseTo(500.0, within(1E-10));
    }
}
