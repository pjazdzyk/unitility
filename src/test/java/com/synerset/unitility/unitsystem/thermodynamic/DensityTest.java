package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;
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
        Density actualInPoundsPerCubicFoot = initialDensity.toPoundPerCubicFoot();
        Density actualInKilogramPerCubicMeter = actualInPoundsPerCubicFoot.toKilogramPerCubicMeter();

        // Then
        Density expectedInPoundsPerCubicFoot = Density.ofPoundPerCubicFoot(0.0749135526913747);
        assertThat(actualInPoundsPerCubicFoot.getValue()).isEqualTo(expectedInPoundsPerCubicFoot.getValue(), withPrecision(1E-16));
        assertThat(actualInKilogramPerCubicMeter.getValue()).isEqualTo(1.2, withPrecision(1E-16));
    }

    @Test
    @DisplayName("should convert kg/m³ to lb/in³ and vice versa")
    void shouldProperlyConvertKilogramsPerCubicMeterToPoundPerCubicInch() {
        // Given
        Density initialDensity = Density.ofKilogramPerCubicMeter(1.2);

        // When
        Density actualInPoundsPerCubicInch = initialDensity.toUnit(DensityUnits.POUNDS_PER_CUBIC_INCH);
        Density actualInKilogramPerCubicMeter = actualInPoundsPerCubicInch.toBaseUnit();

        // Then
        Density expectedInPoundsPerCubicInch = Density.of(0.0000433527506616, DensityUnits.POUNDS_PER_CUBIC_INCH);
        assertThat(actualInPoundsPerCubicInch.getValue()).isEqualTo(expectedInPoundsPerCubicInch.getValue(), withPrecision(1E-16));
        assertThat(actualInKilogramPerCubicMeter.getValue()).isEqualTo(1.2, withPrecision(1E-16));
    }

    @Test
    @DisplayName("should have kg/m³ as base unit")
    void shouldHaveKilogramPerCubicMeterAsBaseUnit() {
        // Given
        DensityUnits expectedBaseUnit = DensityUnits.KILOGRAM_PER_CUBIC_METER;

        // When
        Density densityInLbPerFt3 = Density.ofPoundPerCubicFoot(10);
        Unit<Density> actualBaseUnit = densityInLbPerFt3.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

}
