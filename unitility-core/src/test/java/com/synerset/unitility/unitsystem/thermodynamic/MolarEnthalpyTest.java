package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class MolarEnthalpyTest {

    @Test
    @DisplayName("should have J/mol as base unit")
    void shouldHaveJoulePerMoleAsBaseUnit() {
        // Given
        MolarEnthalpyUnit expectedBaseUnit = MolarEnthalpyUnits.JOULE_PER_MOLE;

        // When
        MolarEnthalpy enthalpyInKilojoule = MolarEnthalpy.ofKilojoulesPerMole(100);
        MolarEnthalpyUnit actualBaseUnit = enthalpyInKilojoule.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should convert to J/mol from kJ/mol and vice versa")
    void shouldProperlyConvertToJoulePerMoleFromKilojoule() {
        // Given - Standard enthalpy of formation of H₂O(l) = -285.8 kJ/mol
        MolarEnthalpy initialEnthalpyInKilojoule = MolarEnthalpy.ofKilojoulesPerMole(-285.8);

        // When
        MolarEnthalpy actualInJoule = initialEnthalpyInKilojoule.toBaseUnit();
        MolarEnthalpy actualInKilojoule = actualInJoule.toUnit(MolarEnthalpyUnits.KILOJOULE_PER_MOLE);

        // Then
        MolarEnthalpy expectedInJoule = MolarEnthalpy.ofJoulesPerMole(-285800.0);
        assertThat(actualInJoule.getValue()).isCloseTo(expectedInJoule.getValue(), withPrecision(1E-9));
        assertThat(actualInKilojoule.getValue()).isCloseTo(initialEnthalpyInKilojoule.getValue(), withPrecision(1E-9));
    }

    @Test
    @DisplayName("should convert to J/mol from BTU/lbmol and vice versa")
    void shouldProperlyConvertToJoulePerMoleFromBTUPerPoundMole() {
        // Given
        double initialValue = 100.0;
        MolarEnthalpy initialEnthalpyInBTU = MolarEnthalpy.ofBTUPerPoundMole(initialValue);

        // When
        MolarEnthalpy actualInJoule = initialEnthalpyInBTU.toBaseUnit();
        MolarEnthalpy actualInBTU = actualInJoule.toUnit(MolarEnthalpyUnits.BTU_PER_POUND_MOLE);

        // Then
        double expectedJouleValue = 232.6;
        assertThat(actualInJoule.getValue()).isCloseTo(expectedJouleValue, withPrecision(1E-9));
        assertThat(actualInBTU.getValue()).isCloseTo(initialValue, withPrecision(1E-9));
    }

    @Test
    @DisplayName("should handle standard enthalpy of combustion of methane")
    void shouldHandleStandardEnthalpyOfCombustion() {
        // Given - ΔH_c° for CH₄ = -890.3 kJ/mol
        MolarEnthalpy methaneCombustion = MolarEnthalpy.ofKilojoulesPerMole(-890.3);

        // When
        double inJoules = methaneCombustion.getInJoulesPerMole();
        double inBTUperLbmol = methaneCombustion.getInBTUsPerPoundMole();

        // Then
        assertThat(inJoules).isEqualTo(-890300.0);
        assertThat(inBTUperLbmol).isCloseTo(-382760.1, withPrecision(0.1));
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        MolarEnthalpy expected = MolarEnthalpy.ofKilojoulesPerMole(413.0);  // C-H bond energy

        // When
        MolarEnthalpy actual = expected.toJoulePerMole()
                .toBTUPerPoundMole()
                .toMegajoulePerKilomole()
                .toKilojoulePerMole();

        double actualValue = expected.getInKilojoulesPerMole();

        // Then
        assertThat(actual.getValue()).isCloseTo(expected.getValue(), withPrecision(1E-9));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }
}