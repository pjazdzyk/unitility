package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.flow.MassFlow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EffectivenessTest {
    @Test
    @DisplayName("should convert % to decimal and vice versa")
    void shouldProperlyConvertPercentToDecimalAndViceVersa() {
        // Given
        Effectiveness initialEffectiveness = Effectiveness.ofPercentage(50.5);

        // When
        Effectiveness actualInDecimal = initialEffectiveness.toUnit(EffectivenessUnits.DECIMAL);
        double actualInDecimalVal = initialEffectiveness.getInDecimal();
        Effectiveness actualInPercent = actualInDecimal.toBaseUnit();
        double actualInPercentVal = actualInDecimal.getInPercent();

        // Then
        Effectiveness expectedInDecimal = Effectiveness.ofDecimal(0.505);
        assertThat(actualInDecimal.getValue()).isEqualTo(actualInDecimalVal);
        assertThat(actualInPercent.getValue()).isEqualTo(actualInPercentVal);
        assertThat(actualInDecimal).isEqualTo(expectedInDecimal);
        assertThat(actualInPercent).isEqualTo(initialEffectiveness);
    }

    @Test
    @DisplayName("should have % as base unit")
    void shouldHavePercentAsBaseUnit() {
        // Given
        EffectivenessUnit expectedBaseUnit = EffectivenessUnits.PERCENT;

        // When
        Effectiveness effectivenessInDecimal = Effectiveness.ofDecimal(0.1);
        EffectivenessUnit actualBaseUnit = effectivenessInDecimal.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Effectiveness expected = Effectiveness.ofPercentage(10.1);

        // When
        Effectiveness actual = expected.toDecimal()
                .toPercent();

        double actualValue = expected.getInPercent();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

    @Test
    @DisplayName("should return valid result from two PhysicalQuantity passed as arguments")
    void shouldReturnValidResultFromTwoPhysicalQuantityPassedAsArguments() {
        // Given
        MassFlow inletMassFlow = MassFlow.ofKilogramsPerSecond(1);
        MassFlow outletMassFlow = MassFlow.ofKilogramsPerSecond(2);

        // When
        Effectiveness flowEffectiveness = Effectiveness.from(inletMassFlow, outletMassFlow);
        Effectiveness flowEffectivenessFromValues = Effectiveness.from(1, 2);

        // Then
        assertThat(flowEffectiveness).isNotNull();
        assertThat(flowEffectivenessFromValues).isEqualTo(flowEffectiveness);
        assertThat(flowEffectiveness.getInPercent()).isEqualTo(50);
        assertThat(flowEffectiveness.getInDecimal()).isEqualTo(0.5);
    }

}
