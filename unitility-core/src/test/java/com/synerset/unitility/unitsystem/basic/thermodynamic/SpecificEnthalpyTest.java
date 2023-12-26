package com.synerset.unitility.unitsystem.basic.thermodynamic;

import com.synerset.unitility.unitsystem.basic.thermodynamic.SpecificEnthalpy;
import com.synerset.unitility.unitsystem.basic.thermodynamic.SpecificEnthalpyUnit;
import com.synerset.unitility.unitsystem.basic.thermodynamic.SpecificEnthalpyUnits;
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
        SpecificEnthalpy actual_KJ_PER_KG = initialSpecificEnthalpy.toUnit(SpecificEnthalpyUnits.KILOJOULE_PER_KILOGRAM);
        double actual_KJ_PER_KGVal = initialSpecificEnthalpy.getInKiloJoulesPerKiloGram();
        SpecificEnthalpy actual_J_PER_KG = actual_KJ_PER_KG.toBaseUnit();
        double actual_J_PER_KGVal = actual_KJ_PER_KG.getInJoulesPerKiloGram();

        // Then
        SpecificEnthalpy expected_KJ_PER_KG = SpecificEnthalpy.ofKiloJoulePerKiloGram(1);
        assertThat(actual_KJ_PER_KG.getValue()).isEqualTo(actual_KJ_PER_KGVal);
        assertThat(actual_J_PER_KG.getValue()).isEqualTo(actual_J_PER_KGVal);
        assertThat(actual_KJ_PER_KG.getValue()).isEqualTo(expected_KJ_PER_KG.getValue());
        assertThat(actual_J_PER_KG.getValue()).isEqualTo(1000);
    }

    @Test
    @DisplayName("should convert J/kg to BTU/lb and vice versa")
    void shouldProperlyConvertJoulesPerKilogramToBTUPerPound() {
        // Given
        SpecificEnthalpy initialSpecificEnthalpy = SpecificEnthalpy.ofJoulePerKiloGram(1000);

        // When
        SpecificEnthalpy actual_BTU_PER_LB = initialSpecificEnthalpy.toUnit(SpecificEnthalpyUnits.BTU_PER_POUND);
        double actual_BTU_PER_LBVal = initialSpecificEnthalpy.getInBTUsPerPound();
        SpecificEnthalpy actual_J_PER_KG = actual_BTU_PER_LB.toBaseUnit();

        // Then
        SpecificEnthalpy expected_BTU_PER_LB = SpecificEnthalpy.ofBTUPerPound(0.429922614);
        assertThat(actual_BTU_PER_LB.getValue()).isEqualTo(actual_BTU_PER_LBVal);
        assertThat(actual_BTU_PER_LB.getValue()).isEqualTo(expected_BTU_PER_LB.getValue(), withPrecision(1E-10));
        assertThat(actual_J_PER_KG.getValue()).isEqualTo(1000, withPrecision(1E-10));
    }

    @Test
    @DisplayName("should have J/kg as base unit")
    void shouldHaveJoulesPerKilogramAsBaseUnit() {
        // Given
        SpecificEnthalpyUnit expectedBaseUnit = SpecificEnthalpyUnits.JOULE_PER_KILOGRAM;

        // When
        SpecificEnthalpy specificEnthalpyInBTUPerLb = SpecificEnthalpy.ofBTUPerPound(0.1);
        SpecificEnthalpyUnit actualBaseUnit = specificEnthalpyInBTUPerLb.getUnitType().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        SpecificEnthalpy expected = SpecificEnthalpy.ofJoulePerKiloGram(10.1);

        // When
        SpecificEnthalpy actual = expected.toKiloJoulePerKiloGram()
                .toBTUPerPound()
                .toJoulePerKiloGram();

        double actualValue = expected.getInJoulesPerKiloGram();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}
