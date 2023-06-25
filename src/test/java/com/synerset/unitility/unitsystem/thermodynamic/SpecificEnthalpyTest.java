package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class SpecificEnthalpyTest {

    @Test
    @DisplayName("should convert J/kg to kJ/kg and vice versa")
    void shouldProperlyConvertJoulesPerKilogramToKilojoulesPerKilogram() {
        // Given
        SpecificEnthalpy initialSpecificEnthalpy = SpecificEnthalpy.ofJoulePerKiloGram(1000);

        // When
        SpecificEnthalpy actual_KJ_PER_KG = initialSpecificEnthalpy.toKiloJoulePerKiloGram();
        SpecificEnthalpy actual_J_PER_KG = actual_KJ_PER_KG.toJoulePerKiloGram();

        // Then
        SpecificEnthalpy expected_KJ_PER_KG = SpecificEnthalpy.ofKiloJoulePerKiloGram(1);
        assertThat(actual_KJ_PER_KG.getValue()).isEqualTo(expected_KJ_PER_KG.getValue());
        assertThat(actual_J_PER_KG.getValue()).isEqualTo(1000);
    }

    @Test
    @DisplayName("should convert J/kg to BTU/lb and vice versa")
    void shouldProperlyConvertJoulesPerKilogramToBTUPerPound() {
        // Given
        SpecificEnthalpy initialSpecificEnthalpy = SpecificEnthalpy.ofJoulePerKiloGram(1000);

        // When
        SpecificEnthalpy actual_BTU_PER_LB = initialSpecificEnthalpy.toBTUPerPound();
        SpecificEnthalpy actual_J_PER_KG = actual_BTU_PER_LB.toJoulePerKiloGram();

        // Then
        SpecificEnthalpy expected_BTU_PER_LB = SpecificEnthalpy.ofBTUPerPound(0.429922614);
        assertThat(actual_BTU_PER_LB.getValue()).isEqualTo(expected_BTU_PER_LB.getValue(), withPrecision(1E-10));
        assertThat(actual_J_PER_KG.getValue()).isEqualTo(1000, withPrecision(1E-10));
    }

    @Test
    @DisplayName("should have J/kg as base unit")
    void shouldHaveJoulesPerKilogramAsBaseUnit() {
        // Given
        SpecificEnthalpyUnits expectedBaseUnit = SpecificEnthalpyUnits.JOULE_PER_KILOGRAM;

        // When
        SpecificEnthalpy specificEnthalpyInBTUPerLb = SpecificEnthalpy.ofBTUPerPound(0.1);
        Unit<SpecificEnthalpy> actualBaseUnit = specificEnthalpyInBTUPerLb.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

}
