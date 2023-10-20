package com.synerset.unitility.unitsystem.flows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class MassFlowTest {

    @Test
    @DisplayName("should convert kg/s to kg/h and vice versa")
    void shouldProperlyConvertKilogramPerSecondToKilogramPerHour() {
        // Given
        MassFlow initialMassFlow = MassFlow.ofKilogramsPerSecond(1.5);

        // When
        MassFlow actual_KG_PER_Hr = initialMassFlow.toUnit(MassFlowUnits.KILOGRAM_PER_HOUR);
        double actual_KG_PER_HrVal = initialMassFlow.getInKiloGramsPerHour();
        MassFlow actual_KG_PER_Sec = actual_KG_PER_Hr.toBaseUnit();
        double actual_KG_PER_SecVal = actual_KG_PER_Hr.getInKilogramsPerSecond();

        // Then
        MassFlow expected_KG_PER_H = MassFlow.ofKilogramsPerHour(5400);
        assertThat(actual_KG_PER_Hr.getValue()).isEqualTo(actual_KG_PER_HrVal);
        assertThat(actual_KG_PER_Sec.getValue()).isEqualTo(actual_KG_PER_SecVal);
        assertThat(actual_KG_PER_Hr).isEqualTo(expected_KG_PER_H);
        assertThat(actual_KG_PER_Sec).isEqualTo(initialMassFlow);
    }

    @Test
    @DisplayName("should convert kg/s to t/h and vice versa")
    void shouldProperlyConvertKilogramPerSecondToTonnePerHour() {
        // Given
        MassFlow initialMassFlow = MassFlow.ofKilogramsPerSecond(1.5);

        // When
        MassFlow actual_TON_PER_H = initialMassFlow.toUnit(MassFlowUnits.TONNE_PER_HOUR);
        double actual_TON_PER_HVal = initialMassFlow.getInTonnesPerHour();
        MassFlow actual_KG_PER_S = actual_TON_PER_H.toBaseUnit();

        // Then
        MassFlow expected_TON_PER_H = MassFlow.ofTonnesPerHour(5.4);
        assertThat(actual_TON_PER_H.getValue()).isEqualTo(actual_TON_PER_HVal);
        assertThat(actual_TON_PER_H.getValue()).isEqualTo(expected_TON_PER_H.getValue(), withPrecision(1E-15));
        assertThat(actual_KG_PER_S).isEqualTo(initialMassFlow);
    }

    @Test
    @DisplayName("should convert kg/s to lb/s and vice versa")
    void shouldProperlyConvertKilogramPerSecondToPoundPerSecond() {
        // Given
        MassFlow initialMassFlow = MassFlow.ofKilogramsPerSecond(1.5);

        // When
        MassFlow actual_LB_PER_S = initialMassFlow.toUnit(MassFlowUnits.POUND_PER_SECOND);
        double actual_LB_PER_SVal = initialMassFlow.getInPoundsPerSecond();
        MassFlow actual_KG_PER_S = actual_LB_PER_S.toBaseUnit();

        // Then
        MassFlow expected_LB_PER_S = MassFlow.ofPoundsPerSecond(3.306933932773164);
        assertThat(actual_LB_PER_S.getValue()).isEqualTo(actual_LB_PER_SVal);
        assertThat(actual_LB_PER_S.getValue()).isEqualTo(expected_LB_PER_S.getValue(), withPrecision(1E-15));
        assertThat(actual_KG_PER_S).isEqualTo(initialMassFlow);
    }

    @Test
    @DisplayName("should have kg/s as base unit")
    void shouldHaveSquareMeterPerSecondAsBaseUnit() {
        // Given
        MassFlowUnits expectedBaseUnit = MassFlowUnits.KILOGRAM_PER_SECOND;

        // When
        MassFlow massFlowInLbPerSec = MassFlow.ofPoundsPerSecond(10);
        MassFlowUnits actualBaseUnit = massFlowInLbPerSec.getUnitType().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        MassFlow expected = MassFlow.ofKilogramsPerSecond(10.1);

        // When
        MassFlow actual = expected.toKiloGramPerHour()
                .toTonnesPerHour()
                .toPoundsPerSecond()
                .toKilogramsPerSecond();

        double actualValue = expected.getInKilogramsPerSecond();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}