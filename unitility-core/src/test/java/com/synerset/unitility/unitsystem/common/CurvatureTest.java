package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class CurvatureTest {

    @Test
    @DisplayName("should properly convert radians per meter to degrees per meter and vice versa")
    void shouldProperlyConvertFromRadiansPerMeterToDegreesPerMeter() {
        // Given
        Curvature initialCurvatureInRadiansPerMeter = Curvature.ofRadiansPerMeter(Math.PI * 2.0);

        // When
        Curvature actualInDegreesPerMeter = initialCurvatureInRadiansPerMeter.toUnit(CurvatureUnits.DEGREES_PER_METER);
        double actualInDegreesPerMeterVal = initialCurvatureInRadiansPerMeter.getInDegreesPerMeter();
        Curvature actualInRadiansPerMeter = actualInDegreesPerMeter.toUnit(CurvatureUnits.RADIANS_PER_METER);
        double actualInRadiansPerMeterVal = actualInRadiansPerMeter.getInRadiansPerMeter();

        // Then
        Curvature expectedCurvatureInDegreesPerMeter = Curvature.ofDegreesPerMeter(360.0);
        assertThat(actualInDegreesPerMeter).isEqualTo(expectedCurvatureInDegreesPerMeter);
        assertThat(actualInDegreesPerMeterVal).isEqualTo(actualInDegreesPerMeter.getValue());
        assertThat(actualInRadiansPerMeter.getValue()).isEqualTo(actualInRadiansPerMeterVal);
    }

    @Test
    @DisplayName("should properly convert radians per meter to degrees per hundred feet and vice versa")
    void shouldProperlyConvertFromRadiansPerMeterToDegreesPerHundredFeet() {
        // Given
        Curvature initialCurvatureInRadiansPerMeter = Curvature.ofRadiansPerMeter(Math.PI * 2.0);

        // When
        Curvature actualInDegreesPerHundredFeet = initialCurvatureInRadiansPerMeter.toUnit(CurvatureUnits.DEGREES_PER_HUNDRED_FEET);
        double actualInDegreesPerHundredFeetVal = initialCurvatureInRadiansPerMeter.getInDegreesPerHundredFeet();
        Curvature actualInRadiansPerMeter = actualInDegreesPerHundredFeet.toUnit(CurvatureUnits.RADIANS_PER_METER);
        double actualInRadiansPerMeterVal = actualInRadiansPerMeter.getInRadiansPerMeter();

        // Then
        Curvature expectedCurvatureInDegreesPerHundredFeet = Curvature.ofDegreesPerHundredFeet(360.0 * 0.3048 / 100.0);
        assertThat(actualInDegreesPerHundredFeet).isEqualTo(expectedCurvatureInDegreesPerHundredFeet);
        assertThat(actualInDegreesPerHundredFeetVal).isEqualTo(actualInDegreesPerHundredFeet.getValue());
        assertThat(actualInRadiansPerMeter.getValue()).isEqualTo(actualInRadiansPerMeterVal);
    }

    @Test
    @DisplayName("should properly convert radians per meter to radians per foot and vice versa")
    void shouldProperlyConvertFromRadiansPerMeterToRadiansPerFoot() {
        // Given
        Curvature initialCurvatureInRadiansPerMeter = Curvature.ofRadiansPerMeter(Math.PI * 2.0);

        // When
        Curvature actualInRadiansPerFoot = initialCurvatureInRadiansPerMeter.toUnit(CurvatureUnits.RADIANS_PER_FOOT);
        double actualInRadiansPerFootVal = initialCurvatureInRadiansPerMeter.getInRadiansPerFoot();
        Curvature actualInRadiansPerMeter = actualInRadiansPerFoot.toUnit(CurvatureUnits.RADIANS_PER_METER);
        double actualInRadiansPerMeterVal = actualInRadiansPerMeter.getInRadiansPerMeter();

        // Then
        Curvature expectedCurvatureInRadiansPerFoot = Curvature.ofRadiansPerFoot(Math.PI * 2.0 * 0.3048);
        assertThat(actualInRadiansPerFoot).isEqualTo(expectedCurvatureInRadiansPerFoot);
        assertThat(actualInRadiansPerFootVal).isEqualTo(actualInRadiansPerFoot.getValue());
        assertThat(actualInRadiansPerMeter.getValue()).isEqualTo(actualInRadiansPerMeterVal);
    }

    @Test
    @DisplayName("should properly convert radians per meter to degrees per foot and vice versa")
    void shouldProperlyConvertFromRadiansPerMeterToDegreesPerFoot() {
        // Given
        Curvature initialCurvatureInRadiansPerMeter = Curvature.ofRadiansPerMeter(Math.PI * 2.0);

        // When
        Curvature actualInDegreesPerFoot = initialCurvatureInRadiansPerMeter.toUnit(CurvatureUnits.DEGREES_PER_FOOT);
        double actualInDegreesPerFootVal = initialCurvatureInRadiansPerMeter.getInDegreesPerFoot();
        Curvature actualInRadiansPerMeter = actualInDegreesPerFoot.toUnit(CurvatureUnits.RADIANS_PER_METER);
        double actualInRadiansPerMeterVal = actualInRadiansPerMeter.getInRadiansPerMeter();

        // Then
        Curvature expectedCurvatureInDegreesPerFoot = Curvature.ofDegreesPerFoot(360.0 * 0.3048);
        assertThat(actualInDegreesPerFoot).isEqualTo(expectedCurvatureInDegreesPerFoot);
        assertThat(actualInDegreesPerFootVal).isEqualTo(actualInDegreesPerFoot.getValue());
        assertThat(actualInRadiansPerMeter.getValue()).isEqualTo(actualInRadiansPerMeterVal);
    }


    @Test
    @DisplayName("should have RADIANS_PER_METER as base unit")
    void shouldHaveRadiansPerMeterAsBaseUnit() {
        // Given
        CurvatureUnit expectedBaseUnit = CurvatureUnits.RADIANS_PER_METER;

        // When
        Curvature actualInDegreesPerHundredFeet = Curvature.ofDegreesPerHundredFeet(10);
        CurvatureUnit actualBaseUnit = actualInDegreesPerHundredFeet.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        // Note: Math.PI * 2.0 does not pass an equality test, even by using StrictMath. I believe this is
        // simply a "sweet spot"
        Curvature expected = Curvature.ofRadiansPerMeter(Math.PI * 2.0);

        // When
        Curvature actual = expected
                .toRadiansPerMeter()
                .toDegreesPeHundredFeet()
                .toDegreesPerMeter()
                .toRadiansPerFoot()
                .toDegreesPerFoot()
                .toRadiansPerMeter();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actual.getValue()).isEqualTo(expected.getValue());
    }

}
