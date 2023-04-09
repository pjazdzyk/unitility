package com.synerset.unitility.unitsystem.humidityratio;

import com.synerset.unitility.unitsystem.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class HumidityRatioTest {

    @Test
    @DisplayName("should properly convert from kg/kg to lb/lb")
    void shouldProperlyConvertFromKgKgToLbLb() {
        // Given
        double initialValue = 0.015;
        HumidityRatio initialHumidityRatio = HumidityRatio.of(initialValue, HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);

        // When
        HumidityRatio actualInLbPerLb = initialHumidityRatio.toUnit(HumidityRatioUnits.POUND_PER_POUND);
        HumidityRatio actualInKgPerKg = actualInLbPerLb.toBaseUnit();

        // Then
        HumidityRatio expectedInLbPerLb = HumidityRatio.of(0.033069339826536, HumidityRatioUnits.POUND_PER_POUND);
        assertThat(actualInKgPerKg.getValue()).isEqualTo(initialValue);
        assertThat(actualInLbPerLb.getValue()).isEqualTo(expectedInLbPerLb.getValue(), withPrecision(1E-9));
        assertThat(actualInKgPerKg.getUnit()).isEqualTo(HumidityRatioUnits.KILOGRAM_PER_KILOGRAM);
    }

    @Test
    @DisplayName("should have kg/kg as base unit")
    void shouldHaveKilogramPerKilogramBaseUnit() {
        // Given
        HumidityRatioUnits expectedBaseUnit = HumidityRatioUnits.KILOGRAM_PER_KILOGRAM;

        // When
        HumidityRatio humidityKgPerKg = HumidityRatio.ofPoundPerPound(10);
        Unit<HumidityRatio> actualBaseUnit = humidityKgPerKg.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

}