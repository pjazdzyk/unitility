package com.synerset.unitility.unitsystem.flows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VolumetricFlowTest {

    @Test
    @DisplayName("should convert to m³/s from m³/min and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromCubicMetersPerMinute() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInM3pMin = initialFlowInM3pSec.toUnit(VolumetricFlowUnits.CUBIC_METERS_PER_MINUTE);
        double actualFlowInM3pMinVal = initialFlowInM3pSec.getInCubicMetersPerMinute();
        VolumetricFlow actualFlowInM3pSec = actualFlowInM3pMin.toBaseUnit();
        double actualFlowInM3pSecVal = actualFlowInM3pMin.getInCubicMetersPerSecond();

        // Then
        VolumetricFlow expectedFlowInM3pMin = VolumetricFlow.ofCubicMetersPerMinute(60.0);
        assertThat(actualFlowInM3pMin.getValue()).isEqualTo(actualFlowInM3pMinVal);
        assertThat(actualFlowInM3pSec.getValue()).isEqualTo(actualFlowInM3pSecVal);
        assertThat(actualFlowInM3pMin).isEqualTo(expectedFlowInM3pMin);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from m³/hr and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromCubicMetersPerHour() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInM3pHr = initialFlowInM3pSec.toUnit(VolumetricFlowUnits.CUBIC_METERS_PER_HOUR);
        double actualFlowInM3pHrVal = initialFlowInM3pSec.getInCubicMetersPerHour();
        VolumetricFlow actualFlowInM3pSec = actualFlowInM3pHr.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInM3pMin = VolumetricFlow.ofCubicMetersPerHour(3600.0);
        assertThat(actualFlowInM3pHr.getValue()).isEqualTo(actualFlowInM3pHrVal);
        assertThat(actualFlowInM3pHr).isEqualTo(expectedFlowInM3pMin);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from l/s and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromLitrePerSecond() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInLpSec = initialFlowInM3pSec.toUnit(VolumetricFlowUnits.LITRE_PER_SECOND);
        double actualFlowInLpSecVal = initialFlowInM3pSec.getInLitresPerSecond();
        VolumetricFlow actualFlowInM3pSec = actualFlowInLpSec.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInLpSec = VolumetricFlow.ofLitresPerSecond(1000.0);
        assertThat(actualFlowInLpSec.getValue()).isEqualTo(actualFlowInLpSecVal);
        assertThat(actualFlowInLpSec).isEqualTo(expectedFlowInLpSec);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from l/min and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromLitrePerMinute() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInLpMin = initialFlowInM3pSec.toUnit(VolumetricFlowUnits.LITRE_PER_MINUTE);
        double actualFlowInLpMinVal = initialFlowInM3pSec.getInLitresPerMinute();
        VolumetricFlow actualFlowInM3pSec = actualFlowInLpMin.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInLpMin = VolumetricFlow.ofLitresPerMinute(60000.0);
        assertThat(actualFlowInLpMin.getValue()).isEqualTo(actualFlowInLpMinVal);
        assertThat(actualFlowInLpMin).isEqualTo(expectedFlowInLpMin);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from L/h and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromLitrePerHour() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInLpHr = initialFlowInM3pSec.toUnit(VolumetricFlowUnits.LITRE_PER_HOUR);
        double actualFlowInLpHrVal = initialFlowInM3pSec.getInLitresPerHour();
        VolumetricFlow actualFlowInM3pSec = actualFlowInLpHr.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInLpHr = VolumetricFlow.ofLitresPerHour(3600000.0);
        assertThat(actualFlowInLpHr.getValue()).isEqualTo(actualFlowInLpHrVal);
        assertThat(actualFlowInLpHr).isEqualTo(expectedFlowInLpHr);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from gal/s and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromGallonsPerSecond() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInGalpSec = initialFlowInM3pSec.toUnit(VolumetricFlowUnits.GALLONS_PER_SECOND);
        double actualFlowInGalpSecVal = initialFlowInM3pSec.getInGallonsPerSecond();
        VolumetricFlow actualFlowInM3pSec = actualFlowInGalpSec.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInGalpSec = VolumetricFlow.ofGallonsPerSecond(0.2641720523581484);
        assertThat(actualFlowInGalpSec.getValue()).isEqualTo(actualFlowInGalpSecVal);
        assertThat(actualFlowInGalpSec).isEqualTo(expectedFlowInGalpSec);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from gal/min and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromGallonsPerMinute() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInGalpMin = initialFlowInM3pSec.toUnit(VolumetricFlowUnits.GALLONS_PER_MINUTE);
        double actualFlowInGalpMinVal = initialFlowInM3pSec.getInGallonsPerMinute();
        VolumetricFlow actualFlowInM3pSec = actualFlowInGalpMin.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInGalpMin = VolumetricFlow.ofGallonsPerMinute(15.850610141490908);
        assertThat(actualFlowInGalpMin.getValue()).isEqualTo(actualFlowInGalpMinVal);
        assertThat(actualFlowInGalpMin).isEqualTo(expectedFlowInGalpMin);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from gal/h and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromGallonsPerHour() {
        // Given
        VolumetricFlow initialFlowInM3PerSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInGalPerHr = initialFlowInM3PerSec.toUnit(VolumetricFlowUnits.GALLONS_PER_HOUR);
        double actualFlowInGalPerHrVal = initialFlowInM3PerSec.getInGallonsPerHour();
        VolumetricFlow actualFlowInM3PerSec = actualFlowInGalPerHr.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInGalPerHr = VolumetricFlow.ofGallonsPerHour(951.0366084894545);
        assertThat(actualFlowInGalPerHr.getValue()).isEqualTo(actualFlowInGalPerHrVal);
        assertThat(actualFlowInGalPerHr).isEqualTo(expectedFlowInGalPerHr);
        assertThat(actualFlowInM3PerSec).isEqualTo(initialFlowInM3PerSec);
    }

    @Test
    @DisplayName("should have m³/s as base unit")
    void shouldHaveCubicMetersPerSecondAsBaseUnit() {
        // Given
        VolumetricFlowUnits expectedBaseUnit = VolumetricFlowUnits.CUBIC_METERS_PER_SECOND;

        // When
        VolumetricFlow volumetricFlowInGalPerHr = VolumetricFlow.ofGallonsPerHour(10);
        VolumetricFlowUnits actualBaseUnit = volumetricFlowInGalPerHr.getUnitType().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        VolumetricFlow expected = VolumetricFlow.ofCubicMetersPerSecond(10.1);

        // When
        VolumetricFlow actual = expected.toCubicMetersPerMinute()
                .toCubicMetersPerHour()
                .toLitresPerSecond()
                .toLitresPerMinute()
                .toLitresPerHour()
                .toGallonsPerSecond()
                .toGallonsPerMinute()
                .toGallonsPerHour()
                .toCubicMetersPerSecond();

        double actualValue = expected.getInCubicMetersPerSecond();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}
