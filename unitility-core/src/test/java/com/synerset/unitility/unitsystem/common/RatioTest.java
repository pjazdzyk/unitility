package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RatioTest {
    @Test
    @DisplayName("should convert % to decimal and vice versa")
    void shouldProperlyConvertPascalToPsi() {
        // Given
        Ratio initialRatio = Ratio.ofPercentage(50.5);

        // When
        Ratio actualInDecimal = initialRatio.toUnit(RatioUnits.DECIMAL);
        double actualInDecimalVal = initialRatio.getInDecimal();
        Ratio actualInPercent = actualInDecimal.toBaseUnit();
        double actualInPercentVal = actualInDecimal.getInPercent();

        // Then
        Ratio expectedInDecimal = Ratio.ofDecimal(0.505);
        assertThat(actualInDecimal.getValue()).isEqualTo(actualInDecimalVal);
        assertThat(actualInPercent.getValue()).isEqualTo(actualInPercentVal);
        assertThat(actualInDecimal).isEqualTo(expectedInDecimal);
        assertThat(actualInPercent).isEqualTo(initialRatio);
    }

    @Test
    @DisplayName("should have % as base unit")
    void shouldHavePercentAsBaseUnit() {
        // Given
        RatioUnit expectedBaseUnit = RatioUnits.PERCENT;

        // When
        Ratio ratioInDecimal = Ratio.ofDecimal(0.1);
        RatioUnit actualBaseUnit = ratioInDecimal.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Ratio expected = Ratio.ofPercentage(10.1);

        // When
        Ratio actual = expected.toDecimal()
                .toPercent();

        double actualValue = expected.getInPercent();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}