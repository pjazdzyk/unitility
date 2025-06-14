package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.common.Curvature;
import com.synerset.unitility.unitsystem.common.CurvatureUnit;
import com.synerset.unitility.unitsystem.common.CurvatureUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class RotationSpeedToFlowRateRateRatioTest {

    @Test
    @DisplayName("should convert (rad/s)/(m3/s) to (rpm)/(gal_US/min) and vice versa")
    void shouldProperlyConvertRadiansPerSecondPerCubicMeterToRpmPerGpm() {
        // Given
        RotationSpeedToFlowRateRatio initialRatio = RotationSpeedToFlowRateRatio.ofRadianPerSecondPerCubicMeterPerSecond(414.960489486047);

        // When
        RotationSpeedToFlowRateRatio inRpmPerGpm = initialRatio.toRpmPerGpm();
        double inRpmPerGpmValue = initialRatio.getInRpmPerGpm();
        RotationSpeedToFlowRateRatio backToRadiansPerSecondPerCubicMeterPerSecond = inRpmPerGpm.toRadianPerSecondForCubicMeterPerSecond();

        // Then
        double expectedInRpmPerGpm = 0.25;
        assertThat(inRpmPerGpmValue).isEqualTo(expectedInRpmPerGpm, withPrecision(1e-14));
        // Converting back to Pa/m should yield the original value
        assertThat(backToRadiansPerSecondPerCubicMeterPerSecond.getValue()).isEqualTo(initialRatio.getInRadianPerSecondForCubicMeterPerSecond());
    }

    @Test
    @DisplayName("should have RADIANS_PER_METER as base unit")
    void shouldHaveRadiansPerMeterAsBaseUnit() {
        // Given
        RotationSpeedToFlowRateRatioUnit expectedBaseUnit = RotationSpeedToFlowRateRatioUnits.RADIAN_PER_SECOND_PER_CUBIC_METER_PER_SECOND;

        // When
        RotationSpeedToFlowRateRatio actualRotationSpeedToFlowRateRatio = RotationSpeedToFlowRateRatio.ofRpmPerGpm(0.25);
        RotationSpeedToFlowRateRatioUnit actualBaseUnit = actualRotationSpeedToFlowRateRatio.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }


    @Test
    @DisplayName("should return valid result from chained to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        RotationSpeedToFlowRateRatio initial = RotationSpeedToFlowRateRatio.ofRadianPerSecondPerCubicMeterPerSecond(414.960489486047);

        // When
        // Chain conversions: Pa/m -> inH₂O/100ft -> inHg/100ft -> Pa/m
        RotationSpeedToFlowRateRatio converted = initial
                .toRpmPerGpm()
                .toRadianPerSecondForCubicMeterPerSecond();
        double actualValue = converted.getValue();

        // Then
        assertThat(actualValue).isEqualTo(initial.getInRadianPerSecondForCubicMeterPerSecond());
    }
}
