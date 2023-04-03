package com.synerset.unitility.unitsystem.volumetricflow;

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
        VolumetricFlow actualFlowInM3pMin = initialFlowInM3pSec.toCubicMetersPerMinute();
        VolumetricFlow actualFlowInM3pSec = actualFlowInM3pMin.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInM3pMin = VolumetricFlow.ofCubicMetersPerMinute(60.0);
        assertThat(actualFlowInM3pMin).isEqualTo(expectedFlowInM3pMin);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from m³/hr and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromCubicMetersPerHour() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInM3pHr = initialFlowInM3pSec.toCubicMetersPerHour();
        VolumetricFlow actualFlowInM3pSec = actualFlowInM3pHr.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInM3pMin = VolumetricFlow.ofCubicMetersPerHour(3600.0);
        assertThat(actualFlowInM3pHr).isEqualTo(expectedFlowInM3pMin);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from l/s and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromLitrePerSecond() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInLpSec = initialFlowInM3pSec.toLitresPerSecond();
        VolumetricFlow actualFlowInM3pSec = actualFlowInLpSec.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInLpSec = VolumetricFlow.ofLitresPerSecond(1000.0);
        assertThat(actualFlowInLpSec).isEqualTo(expectedFlowInLpSec);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from l/min and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromLitrePerMinute() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInLpMin = initialFlowInM3pSec.toLitresPerMinute();
        VolumetricFlow actualFlowInM3pSec = actualFlowInLpMin.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInLpMin = VolumetricFlow.ofLitresPerMinute(60000.0);
        assertThat(actualFlowInLpMin).isEqualTo(expectedFlowInLpMin);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from L/h and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromLitrePerHour() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInLpHr = initialFlowInM3pSec.toLitresPerHour();
        VolumetricFlow actualFlowInM3pSec = actualFlowInLpHr.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInLpHr = VolumetricFlow.ofLitresPerHour(3600000.0);
        assertThat(actualFlowInLpHr).isEqualTo(expectedFlowInLpHr);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from gal/s and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromGallonsPerSecond() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInGalpSec = initialFlowInM3pSec.toGallonsPerSecond();
        VolumetricFlow actualFlowInM3pSec = actualFlowInGalpSec.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInGalpSec = VolumetricFlow.ofGallonsPerSecond(0.2641720523581484);
        assertThat(actualFlowInGalpSec).isEqualTo(expectedFlowInGalpSec);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from gal/min and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromGallonsPerMinute() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInGalpMin = initialFlowInM3pSec.toGallonsPerMinute();
        VolumetricFlow actualFlowInM3pSec = actualFlowInGalpMin.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInGalpMin = VolumetricFlow.ofGallonsPerMinute(15.850610141490908);
        assertThat(actualFlowInGalpMin).isEqualTo(expectedFlowInGalpMin);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from gal/h and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromGallonsPerHour() {
        // Given
        VolumetricFlow initialFlowInM3PerSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInGalPerHr = initialFlowInM3PerSec.toGallonsPerHour();
        VolumetricFlow actualFlowInM3PerSec = actualFlowInGalPerHr.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInGalPerHr = VolumetricFlow.ofGallonsPerHour(951.0366084894545);
        assertThat(actualFlowInGalPerHr).isEqualTo(expectedFlowInGalPerHr);
        assertThat(actualFlowInM3PerSec).isEqualTo(initialFlowInM3PerSec);
    }
}
