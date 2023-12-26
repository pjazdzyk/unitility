package com.synerset.unitility.unitsystem.basic.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class DensityTest {

    @Test
    @DisplayName("should convert kg/m³ to lb/ft³ and vice versa")
    void shouldProperlyConvertKilogramsPerCubicMeterToPoundPerCubicFoot() {
        // Given
        Density initialDensity = Density.ofKilogramPerCubicMeter(1.2);

        // When
        Density actualInPoundsPerCubicFoot = initialDensity.toUnit(DensityUnits.POUND_PER_CUBIC_FOOT);
        double actualInPoundsPerCubicFootVal = initialDensity.getInPoundsPerCubicFoot();
        Density actualInKilogramPerCubicMeter = actualInPoundsPerCubicFoot.toBaseUnit();
        double actualInKilogramPerCubicMeterVal = actualInPoundsPerCubicFoot.getInKilogramsPerCubicMeters();

        // Then
        Density expectedInPoundsPerCubicFoot = Density.ofPoundPerCubicFoot(0.0749135526913747);
        assertThat(actualInPoundsPerCubicFoot.getValue()).isEqualTo(actualInPoundsPerCubicFootVal);
        assertThat(actualInKilogramPerCubicMeter.getValue()).isEqualTo(actualInKilogramPerCubicMeterVal);
        assertThat(actualInPoundsPerCubicFoot.getValue()).isEqualTo(expectedInPoundsPerCubicFoot.getValue(), withPrecision(1E-16));
        assertThat(actualInKilogramPerCubicMeter.getValue()).isEqualTo(1.2, withPrecision(1E-16));
    }

    @Test
    @DisplayName("should convert kg/m³ to lb/in³ and vice versa")
    void shouldProperlyConvertKilogramsPerCubicMeterToPoundPerCubicInch() {
        // Given
        Density initialDensity = Density.ofKilogramPerCubicMeter(1.2);

        // When
        Density actualInPoundsPerCubicInch = initialDensity.toUnit(DensityUnits.POUND_PER_CUBIC_INCH);
        double actualInPoundsPerCubicInchVal = initialDensity.getInPoundsPerCubicInches();
        Density actualInKilogramPerCubicMeter = actualInPoundsPerCubicInch.toBaseUnit();

        // Then
        Density expectedInPoundsPerCubicInch = Density.of(0.0000433527506616, DensityUnits.POUND_PER_CUBIC_INCH);
        assertThat(actualInPoundsPerCubicInch.getValue()).isEqualTo(actualInPoundsPerCubicInchVal);
        assertThat(actualInPoundsPerCubicInch.getValue()).isEqualTo(expectedInPoundsPerCubicInch.getValue(), withPrecision(1E-16));
        assertThat(actualInKilogramPerCubicMeter.getValue()).isEqualTo(1.2, withPrecision(1E-16));
    }

    @Test
    @DisplayName("should have kg/m³ as base unit")
    void shouldHaveKilogramPerCubicMeterAsBaseUnit() {
        // Given
        DensityUnit expectedBaseUnit = DensityUnits.KILOGRAM_PER_CUBIC_METER;

        // When
        Density densityInLbPerFt3 = Density.ofPoundPerCubicFoot(10);
        DensityUnit actualBaseUnit = densityInLbPerFt3.getUnitType().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Density expected = Density.ofKilogramPerCubicMeter(10.1);

        // When
        Density actual = expected.toPoundPerCubicFoot()
                .toKilogramPerCubicMeter();

        double actualValue = expected.getInKilogramsPerCubicMeters();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}
