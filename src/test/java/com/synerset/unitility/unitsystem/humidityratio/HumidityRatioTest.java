package com.synerset.unitility.unitsystem.humidityratio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class HumidityRatioTest {

    @Test
    @DisplayName("should properly convert from kg/kg to lb/lb")
    public void shouldProperlyConvertFromKgKgToLbLb() {
        // Given
        double initialValue = 0.015;
        HumidityRatio initialHumidityRatio = HumidityRatio.of(initialValue, HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);

        // When
        HumidityRatio actualInLbPerLb = initialHumidityRatio.toUnit(HumidityRatioUnits.POUND_PER_POUND);
        HumidityRatio actualInKgPerKg = actualInLbPerLb.toUnit(HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);

        // Then
        HumidityRatio expectedInLbPerLb = HumidityRatio.of(0.033069339826536, HumidityRatioUnits.POUND_PER_POUND);
        assertThat(actualInKgPerKg.getValue()).isEqualTo(initialValue);
        assertThat(actualInKgPerKg.getUnit()).isEqualTo(HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);
    }
}