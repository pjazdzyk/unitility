package com.synerset.unitility.unitsystem.flow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

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
        VolumetricFlow actualFlowInGalpSec = initialFlowInM3pSec.toUnit(VolumetricFlowUnits.GALLONS_PER_SECOND_US);
        double actualFlowInGalpSecVal = initialFlowInM3pSec.getInGallonsPerSecondUS();
        VolumetricFlow actualFlowInM3pSec = actualFlowInGalpSec.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInGalpSec = VolumetricFlow.ofGallonsPerSecondUS(264.17205235814845);
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
        VolumetricFlow actualFlowInGalpMin = initialFlowInM3pSec.toUnit(VolumetricFlowUnits.GALLONS_PER_MINUTE_US);
        double actualFlowInGalpMinVal = initialFlowInM3pSec.getInGallonsPerMinuteUS();
        VolumetricFlow actualFlowInM3pSec = actualFlowInGalpMin.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInGalpMin = VolumetricFlow.ofGallonsPerMinuteUS(15850.323141488907);
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
        VolumetricFlow actualFlowInGalPerHr = initialFlowInM3PerSec.toUnit(VolumetricFlowUnits.GALLONS_PER_HOUR_US);
        double actualFlowInGalPerHrVal = initialFlowInM3PerSec.getInGallonsPerHourUS();
        VolumetricFlow actualFlowInM3PerSec = actualFlowInGalPerHr.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInGalPerHr = VolumetricFlow.ofGallonsPerHourUS(951019.3884893344);
        assertThat(actualFlowInGalPerHr.getValue()).isEqualTo(actualFlowInGalPerHrVal);
        assertThat(actualFlowInGalPerHr).isEqualTo(expectedFlowInGalPerHr);
        assertThat(actualFlowInM3PerSec).isEqualTo(initialFlowInM3PerSec);
    }

    @Test
    @DisplayName("should convert to m³/s from ft³/min and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromFeetPerMinute() {
        // Given
        VolumetricFlow initialFlowInM3PerSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInCFM = initialFlowInM3PerSec.toUnit(VolumetricFlowUnits.CUBIC_FEET_PER_MINUTE);
        double actualFlowInCFMVal = initialFlowInM3PerSec.getInCubicFeetPerMinute();
        VolumetricFlow actualFlowInM3PerSec = actualFlowInCFM.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInCFM = VolumetricFlow.ofCubicFeetPerMinute(2118.880003289315415);
        assertThat(actualFlowInCFM.getValue()).isEqualTo(actualFlowInCFMVal);
        assertThat(actualFlowInCFM).isEqualTo(expectedFlowInCFM);
        assertThat(actualFlowInM3PerSec).isEqualTo(initialFlowInM3PerSec);
    }

    @Test
    @DisplayName("should have m³/s as base unit")
    void shouldHaveCubicMetersPerSecondAsBaseUnit() {
        // Given
        VolumetricFlowUnit expectedBaseUnit = VolumetricFlowUnits.CUBIC_METERS_PER_SECOND;

        // When
        VolumetricFlow volumetricFlowInGalPerHr = VolumetricFlow.ofGallonsPerHourUS(10);
        VolumetricFlowUnit actualBaseUnit = volumetricFlowInGalPerHr.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should convert to m³/s from gal/s_UK and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromGallonsPerSecondUK() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInGalpSecUK = initialFlowInM3pSec.toUnit(VolumetricFlowUnits.GALLONS_PER_SECOND_UK);
        double actualFlowInGalpSecUKVal = initialFlowInM3pSec.getInGallonsPerSecondUK();
        VolumetricFlow actualFlowInM3pSec = actualFlowInGalpSecUK.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInGalpSecUK = VolumetricFlow.ofGallonsPerSecondUK(219.96924829908775);
        assertThat(actualFlowInGalpSecUK.getValue()).isEqualTo(actualFlowInGalpSecUKVal);
        assertThat(actualFlowInGalpSecUK).isEqualTo(expectedFlowInGalpSecUK);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from gal/min_UK and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromGallonsPerMinuteUK() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInGalpMinUK = initialFlowInM3pSec.toUnit(VolumetricFlowUnits.GALLONS_PER_MINUTE_UK);
        double actualFlowInGalpMinUKVal = initialFlowInM3pSec.getInGallonsPerMinuteUK();
        VolumetricFlow actualFlowInM3pSec = actualFlowInGalpMinUK.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInGalpMinUK = VolumetricFlow.ofGallonsPerMinuteUK(13198.154897945265);
        assertThat(actualFlowInGalpMinUK.getValue()).isEqualTo(actualFlowInGalpMinUKVal);
        assertThat(actualFlowInGalpMinUK).isEqualTo(expectedFlowInGalpMinUK);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
    }

    @Test
    @DisplayName("should convert to m³/s from gal/h_UK and vice versa")
    void shouldProperlyConvertToMetersCubedPerSecondFromGallonsPerHourUK() {
        // Given
        VolumetricFlow initialFlowInM3pSec = VolumetricFlow.ofCubicMetersPerSecond(1.0);

        // When
        VolumetricFlow actualFlowInGalpHourUK = initialFlowInM3pSec.toUnit(VolumetricFlowUnits.GALLONS_PER_HOUR_UK);
        double actualFlowInGalpHourUKVal = initialFlowInM3pSec.getInGallonsPerHourUK();
        VolumetricFlow actualFlowInM3pSec = actualFlowInGalpHourUK.toBaseUnit();

        // Then
        VolumetricFlow expectedFlowInGalpHourUK = VolumetricFlow.ofGallonsPerHourUK(791889.2938767159);
        assertThat(actualFlowInGalpHourUK.getValue()).isEqualTo(actualFlowInGalpHourUKVal);
        assertThat(actualFlowInGalpHourUK).isEqualTo(expectedFlowInGalpHourUK);
        assertThat(actualFlowInM3pSec).isEqualTo(initialFlowInM3pSec);
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
                .toCubicFeetPerMinute()
                .toGallonsPerSecondUS()
                .toGallonsPerMinuteUS()
                .toGallonsPerHourUS()
                .toGallonsPerSecondUK()
                .toGallonsPerMinuteUK()
                .toGallonsPerHourUK()
                .toCubicMetersPerSecond();

        // Then
        assertThat(actual.getInCubicMetersPerSecond()).isEqualTo(expected.getValue(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("should properly parse to VolumetricFlow from string")
    void shouldProperlyParseToVolumetricFlowFromString() {
        String galEmpty = "gal/min";
        String galUK = "gal/h_Uk";
        String galUs = "gal/s us";

        assertThat(VolumetricFlow.of(1, galEmpty)).isEqualTo(VolumetricFlow.ofGallonsPerMinuteUK(1));
        assertThat(VolumetricFlow.of(1, galUK)).isEqualTo(VolumetricFlow.ofGallonsPerHourUK(1));
        assertThat(VolumetricFlow.of(1, galUs)).isEqualTo(VolumetricFlow.ofGallonsPerSecondUS(1));

    }

}
