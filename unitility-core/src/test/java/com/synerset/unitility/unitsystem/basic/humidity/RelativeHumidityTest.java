package com.synerset.unitility.unitsystem.basic.humidity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RelativeHumidityTest {
    @Test
    @DisplayName("should convert % to decimal and vice versa")
    void shouldProperlyConvertPascalToPsi() {
        // Given
        RelativeHumidity initialHumidity = RelativeHumidity.ofPercentage(50.5);

        // When
        RelativeHumidity actualInDecimal = initialHumidity.toUnit(RelativeHumidityUnits.DECIMAL);
        double actualInDecimalVal = initialHumidity.getInDecimal();
        RelativeHumidity actualInPercent = actualInDecimal.toBaseUnit();
        double actualInPercentVal = actualInDecimal.getInPercent();

        // Then
        RelativeHumidity expectedInDecimal = RelativeHumidity.ofDecimal(0.505);
        assertThat(actualInDecimal.getValue()).isEqualTo(actualInDecimalVal);
        assertThat(actualInPercent.getValue()).isEqualTo(actualInPercentVal);
        assertThat(actualInDecimal).isEqualTo(expectedInDecimal);
        assertThat(actualInPercent).isEqualTo(initialHumidity);
    }

    @Test
    @DisplayName("should have % as base unit")
    void shouldHavePercentAsBaseUnit() {
        // Given
        RelativeHumidityUnit expectedBaseUnit = RelativeHumidityUnits.PERCENT;

        // When
        RelativeHumidity relativeHumidityInDecimal = RelativeHumidity.ofDecimal(0.1);
        RelativeHumidityUnit actualBaseUnit = relativeHumidityInDecimal.getUnitType().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        RelativeHumidity expected = RelativeHumidity.ofPercentage(10.1);

        // When
        RelativeHumidity actual = expected.toDecimal()
                .toPercent();

        double actualValue = expected.getInPercent();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}