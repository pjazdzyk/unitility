package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.basic.common.Angle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CustomAngleUnitTest {

    @Test
    void shouldConvertUsingCustomUnit() {
        // Given
        Angle angleInCustomUnit = Angle.of(1, CustomAngles.REVOLUTIONS);

        // When
        Angle actualAngleDegrees = angleInCustomUnit.toBaseUnit();
        Angle actualCustomAngle = angleInCustomUnit.plus(Angle.ofDegrees(360));

        // Then
        assertThat(actualAngleDegrees).isEqualTo(Angle.ofDegrees(360));
        assertThat(actualCustomAngle).isEqualTo(Angle.of(2, CustomAngles.REVOLUTIONS));
    }

}