package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DynamicViscosityTest {

    @Test
    @DisplayName("should convert kg/(m·s) to P and vice versa")
    void shouldProperlyConvertKilogramsPerMeterSecondToPoise() {
        // Given
        DynamicViscosity initialViscosity = DynamicViscosity.ofKiloGramPerMeterSecond(100);

        // When
        DynamicViscosity actual_Poise = initialViscosity.toUnit(DynamicViscosityUnits.POISE);
        double actual_PoiseVal = initialViscosity.getInPoise();
        DynamicViscosity actual_KG_PER_M_S = actual_Poise.toBaseUnit();
        double actual_KG_PER_M_SVal = actual_Poise.getInKiloGramPerMeterSecond();

        // Then
        DynamicViscosity expected_Poise = DynamicViscosity.ofPoise(1000);
        assertThat(actual_Poise.getValue()).isEqualTo(actual_PoiseVal);
        assertThat(actual_KG_PER_M_S.getValue()).isEqualTo(actual_KG_PER_M_SVal);
        assertThat(actual_Poise.getValue()).isEqualTo(expected_Poise.getValue());
        assertThat(actual_KG_PER_M_S.getValue()).isEqualTo(initialViscosity.getValue());
    }

    @Test
    @DisplayName("should convert kg/(m·s) to Pa·s and vice versa")
    void shouldProperlyConvertKilogramsPerMeterSecondToPascalSecond() {
        // Given
        DynamicViscosity initialViscosity = DynamicViscosity.ofKiloGramPerMeterSecond(100);

        // When
        DynamicViscosity actual_PaS = initialViscosity.toUnit(DynamicViscosityUnits.PASCAL_SECOND);
        double actual_PaSVal = initialViscosity.getInPascalsSecond();
        DynamicViscosity actual_KG_PER_M_S = actual_PaS.toBaseUnit();

        // Then
        DynamicViscosity expected_PaS = DynamicViscosity.ofPascalSecond(100);
        assertThat(actual_PaS.getValue()).isEqualTo(actual_PaSVal);
        assertThat(actual_PaS.getValue()).isEqualTo(expected_PaS.getValue());
        assertThat(actual_KG_PER_M_S.getValue()).isEqualTo(initialViscosity.getValue());
    }

    @Test
    @DisplayName("should have kg/(m·s) as base unit")
    void shouldHaveKilogramPerMeterSecondAsBaseUnit() {
        // Given
        DynamicViscosityUnits expectedBaseUnit = DynamicViscosityUnits.KILOGRAM_PER_METER_SECOND;

        // When
        DynamicViscosity dynVisInPoise = DynamicViscosity.of(10, DynamicViscosityUnits.POISE);
        DynamicViscosityUnits actualBaseUnit = dynVisInPoise.getUnitType().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        DynamicViscosity expected = DynamicViscosity.ofKiloGramPerMeterSecond(10.1);

        // When
        DynamicViscosity actual = expected.toPascalSecond()
                .toPoise()
                .toKiloGramPerMeterSecond();

        double actualValue = expected.getInKiloGramPerMeterSecond();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}
