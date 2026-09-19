package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
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
        // 2π rad/m = 360 °/m = 360 × 30.48 °/100ft = 10 972.8 °/100ft. 4.1.0 pinned 360 × 0.3048 / 100 = 1.09728, the
        // value of its factor, which multiplied by 100 where it must divide (10 000 times too large, 01_audit.md).
        Curvature expectedCurvatureInDegreesPerHundredFeet = Curvature.ofDegreesPerHundredFeet(10972.8);
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
        // 360 × 0.3048 = 109.728 exactly; 4.2.0 gives the double nearest to it (the double product 360.0 * 0.3048,
        // which this test used before, is one ulp above).
        Curvature expectedCurvatureInDegreesPerFoot = Curvature.ofDegreesPerFoot(109.728);
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
    @DisplayName("should return valid result from to() and getIn() methods for most radii of curvature")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        // Note: Math.PI * 2.0 does not pass an equality test, even by using StrictMath. I believe this is
        // simply a "sweet spot"
        Curvature expected = Curvature.ofRadiansPerMeter(Math.PI * 2.0 * 10.0);

        // When
        Curvature actual = expected.toRadiansPerMeter().toDegreesPerMeter().toRadiansPerFoot().toDegreesPerFoot().toDegreesPeHundredFeet().toRadiansPerMeter();
        double actualValue = expected.getInRadiansPerMeter();

        // Then
        // 4.2.0: each of the five conversions of this chain rounds once, so it may end up to five ulp from where it
        // started (here two). The factors themselves are pinned by UnitGoldenTableTest.
        assertThat(actual.getValue()).isCloseTo(expected.getValue(), within(5 * Math.ulp(expected.getValue())));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

    @Test
    @DisplayName("floating point math quirks introduce a small amount of 'round-trip' error for some radii of curvature")
    void doesNotExactlyEqualDueToFloatingPointMathForSomeCurvature() {
        // Given
        // Note: Math.PI * 2.0 [rad/m] does not pass the strict 'round-trip' equality test even using StrictMath.
        // This is apparently a "sweet spot" for the mantissa, multiplying by 0.001, 0.1, 2.1, 10.0, 1000.0 all
        // result in exact equality when compared before/after the round trip. Possibly something more significant
        // lurks underneath? This may be why some unit libraries made a design choice to use BigDecimal types
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
        // 4.1.0 drifted by 8.881784197001252E-16 here. With correctly rounded factors, 4.2.0 lands exactly.
        assertThat(actual.minus(expected).getValue()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods with some floating point drift for smaller curvature")
    void shouldReturnValidResultFromToAndGetInMethodsWithFloatingPointError() {
        // Given
        Curvature expected = Curvature.ofRadiansPerMeter(Math.PI * 2.0);

        // When
        double actualValue = expected
                .toRadiansPerMeter()
                .toDegreesPerMeter()
                .toRadiansPerFoot()
                .toDegreesPerFoot()
                .toDegreesPeHundredFeet()
                .getInRadiansPerMeter();

        // Then
        assertThat(actualValue).isEqualTo(expected.getValue(),withPrecision(1e-14));
    }

}
