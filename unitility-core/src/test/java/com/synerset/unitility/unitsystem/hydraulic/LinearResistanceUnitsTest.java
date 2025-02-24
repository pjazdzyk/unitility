package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.withPrecision;

class LinearResistanceUnitsTest {

    @Test
    @DisplayName("should convert Pa/m to inH₂O/100ft and vice versa")
    void shouldProperlyConvertPascalPerMeterToInchOfWaterPer100Feet() {
        // Given
        LinearResistance initialResistance = LinearResistance.ofPascalPerMeter(100.0);

        // When
        LinearResistance inInchOfWater = initialResistance.toInchOfWaterPer100Feet();
        double inInchOfWaterValue = initialResistance.getInInchOfWaterPer100Feet();
        LinearResistance backToPaPerMeter = inInchOfWater.toPascalPerMeter();

        // Then
        double expectedInInchOfWater = 12.244068361082473;
        assertThat(inInchOfWater.getValue()).isEqualTo(expectedInInchOfWater, withPrecision(1E-3));
        assertThat(inInchOfWaterValue).isEqualTo(expectedInInchOfWater, withPrecision(1E-3));
        // Converting back to Pa/m should yield the original value
        assertThat(backToPaPerMeter.getValue()).isEqualTo(100.0, withPrecision(1E-11));
    }

    @Test
    @DisplayName("should convert Pa/m to inHg/100ft and vice versa")
    void shouldProperlyConvertPascalPerMeterToInchOfMercuryPer100Feet() {
        // Given
        LinearResistance initialResistance = LinearResistance.ofPascalPerMeter(100.0);

        // When
        LinearResistance inInHgPer100Feet = initialResistance.toInchOfMercuryPer100Feet();
        double inInHgPer100FeetValue = initialResistance.getInInchOfMercuryPer100Feet();
        LinearResistance backToPaPerMeter = inInHgPer100Feet.toPascalPerMeter();

        // Then
        double expectedInInHgPer100Feet = 0.9000765336;
        assertThat(inInHgPer100Feet.getValue()).isEqualTo(expectedInInHgPer100Feet, withPrecision(1E-4));
        assertThat(inInHgPer100FeetValue).isEqualTo(expectedInInHgPer100Feet, withPrecision(1E-4));
        // Converting back to Pa/m should yield the original value
        assertThat(backToPaPerMeter.getValue()).isEqualTo(100.0, withPrecision(1E-11));
    }

    @Test
    @DisplayName("should return valid result from chained to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        LinearResistance initial = LinearResistance.ofPascalPerMeter(100.0);

        // When
        // Chain conversions: Pa/m -> inH₂O/100ft -> inHg/100ft -> Pa/m
        LinearResistance converted = initial
                .toInchOfWaterPer100Feet()
                .toInchOfMercuryPer100Feet()
                .toPascalPerMeter();
        double actualValue = converted.getValue();

        // Then
        // The final result should be equal to the original 100 Pa/m
        assertThat(actualValue).isEqualTo(100.0, withPrecision(1E-11));
    }

    @Test
    @DisplayName("should correctly parse valid unit symbols and throw exception for invalid ones")
    void shouldCorrectlyParseValidAndInvalidUnitSymbols() {
        // Given
        double value = 50.0;

        // Valid cases
        assertThat(LinearResistance.of(value, "Pa/m").getUnit())
                .isEqualTo(LinearResistanceUnits.PASCAL_PER_METER);
        assertThat(LinearResistance.of(value, "Pa p   m").getUnit())
                .isEqualTo(LinearResistanceUnits.PASCAL_PER_METER);

        assertThat(LinearResistance.of(value, "inH₂O/100ft").getUnit())
                .isEqualTo(LinearResistanceUnits.INCH_OF_WATER_PER_100_FEET);
        assertThat(LinearResistance.of(value, "inH2o p   100 ft").getUnit())
                .isEqualTo(LinearResistanceUnits.INCH_OF_WATER_PER_100_FEET);

        assertThat(LinearResistance.of(value, "inHg/100ft").getUnit())
                .isEqualTo(LinearResistanceUnits.INCH_OF_MERCURY_PER_100_FEET);
        assertThat(LinearResistance.of(value, "  i  n hg p 1 0 0 f   t").getUnit())
                .isEqualTo(LinearResistanceUnits.INCH_OF_MERCURY_PER_100_FEET);

        // Invalid case
        assertThatThrownBy(() -> LinearResistance.of(value, "invalid_unit"))
                .isInstanceOf(UnitSystemParseException.class)
                .hasMessageContaining("Unsupported unit symbol: {invalid_unit}");
    }

}
