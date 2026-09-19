package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.Assertions.withPrecision;

class SpecificHeatTest {

    @Test
    @DisplayName("should convert J/(kg·K) to kJ/(kg·K) and vice versa")
    void shouldProperlyConvertJoulesPerKilogramKelvinToKilojoulesPerKilogramKelvin() {
        // Given
        SpecificHeat initialSpecificHeat = SpecificHeat.ofJoulePerKiloGramKelvin(1000);

        // When
        SpecificHeat actual_KJ_PER_KG_K = initialSpecificHeat.toUnit(SpecificHeatUnits.KILOJOULES_PER_KILOGRAM_KELVIN);
        double actual_KJ_PER_KG_KVal = initialSpecificHeat.getInKiloJoulesPerKiloGramKelvin();
        SpecificHeat actual_J_PER_KG_K = actual_KJ_PER_KG_K.toBaseUnit();
        double actual_J_PER_KG_KVal = actual_KJ_PER_KG_K.getInJoulePerKiloGramKelvin();

        // Then
        SpecificHeat expected_KJ_PER_KG_K = SpecificHeat.ofKiloJoulePerKiloGramKelvin(1);
        assertThat(actual_KJ_PER_KG_K.getValue()).isEqualTo(actual_KJ_PER_KG_KVal);
        assertThat(actual_J_PER_KG_K.getValue()).isEqualTo(actual_J_PER_KG_KVal);
        assertThat(actual_KJ_PER_KG_K.getValue()).isEqualTo(expected_KJ_PER_KG_K.getValue(), withPrecision(1E-10));
        assertThat(actual_J_PER_KG_K.getValue()).isEqualTo(1000, withPrecision(1E-10));
    }

    @Test
    @DisplayName("should convert J/(kg·K) to BTU/(lb·°F) and vice versa")
    void shouldProperlyConvertJoulesPerKilogramKelvinToBTUPerPoundFahrenheit() {
        // Given
        SpecificHeat initialSpecificHeat = SpecificHeat.ofJoulePerKiloGramKelvin(1000);

        // When
        SpecificHeat actual_BTU_PER_KG_F = initialSpecificHeat.toUnit(SpecificHeatUnits.BTU_PER_POUND_FAHRENHEIT);
        double actual_BTU_PER_KG_FVal = initialSpecificHeat.getInBTUsPerPoundFahrenheit();
        SpecificHeat actual_J_PER_KG_KG = actual_BTU_PER_KG_F.toBaseUnit();

        // Then
        // 1000 / 4186.8 = 0.238 845 896 627 495 9... (Btu_IT/(lb·°F) = 4186.8 J/(kg·K) exactly, NIST SP 811 App. B.8).
        // 4.1.0 used 4186.7999934703 and pinned 0.2388458969999981.
        SpecificHeat expected_BTU_PER_KG_F = SpecificHeat.ofBTUPerPoundFahrenheit(0.23884589662749594);
        assertThat(actual_BTU_PER_KG_F.getValue()).isEqualTo(actual_BTU_PER_KG_FVal);
        assertThat(actual_BTU_PER_KG_F.getValue()).isEqualTo(expected_BTU_PER_KG_F.getValue(), withPrecision(1E-10));
        assertThat(actual_J_PER_KG_KG.getValue()).isEqualTo(1000, withPrecision(1E-10));
    }

    @Test
    @DisplayName("should have J/(kg·K) as base unit")
    void shouldHaveJoulesPerKilogramKelvinAsBaseUnit() {
        // Given
        SpecificHeatUnit expectedBaseUnit = SpecificHeatUnits.JOULES_PER_KILOGRAM_KELVIN;

        // When
        SpecificHeat specificHeatInBTUPerLbF = SpecificHeat.ofBTUPerPoundFahrenheit(0.1);
        SpecificHeatUnit actualBaseUnit = specificHeatInBTUPerLbF.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        SpecificHeat expected = SpecificHeat.ofJoulePerKiloGramKelvin(10.1);

        // When
        SpecificHeat actual = expected.toKiloJoulePerKiloGramKelvin()
                .toBTUPerPoundFahrenheit()
                .toJoulePerKiloGramKelvin();

        double actualValue = expected.getInJoulePerKiloGramKelvin();

        // Then
        // 4.2.0: a chain of conversions may now end one ulp from where it started (double arithmetic); the factors
        // themselves are pinned by UnitGoldenTableTest.
        assertThat(actual.getValue()).isCloseTo(expected.getValue(), within(Math.ulp(expected.getValue())));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}
