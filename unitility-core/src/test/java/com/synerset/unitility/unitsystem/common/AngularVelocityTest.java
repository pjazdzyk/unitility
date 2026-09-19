package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class AngularVelocityTest {

    @Test
    @DisplayName("should convert rad/s to rpm and vice versa")
    void shouldProperlyConvertRadiansPerSecondToRevolutionsPerMinute() {
        // Given
        AngularVelocity initialAngularVelocity = AngularVelocity.ofRadiansPerSecond(Math.PI*2.0/60.0);

        // When
        AngularVelocity actualInRevolutionsPerMinute = initialAngularVelocity.toUnit(AngularVelocityUnits.REVOLUTIONS_PER_MINUTE);
        double actualInRevolutionsPerMinuteVal = initialAngularVelocity.getInRevolutionsPerMinute();
        AngularVelocity actualInRadiansPerSecond = actualInRevolutionsPerMinute.toBaseUnit();
        double actualInRadiansPerSecondVal = actualInRevolutionsPerMinute.getInRadiansPerSecond();
        AngularVelocity expectedInRevolutionsPerMinute = AngularVelocity.ofRevolutionsPerMinute(1.0);

        // Then
        assertThat(actualInRevolutionsPerMinute.getValue()).isEqualTo(expectedInRevolutionsPerMinute.getValue());
        assertThat(actualInRevolutionsPerMinute.getValue()).isEqualTo(actualInRevolutionsPerMinuteVal);
        assertThat(actualInRadiansPerSecond.getValue()).isEqualTo(actualInRadiansPerSecondVal);
        assertThat(actualInRadiansPerSecond).isEqualTo(initialAngularVelocity);
    }

    @Test
    @DisplayName("should convert rad/s to rpm and vice versa")
    void shouldProperlyConvertRadiansPerSecondToRevolutionsPerSecond() {
        // Given
        AngularVelocity initialAngularVelocity = AngularVelocity.ofRadiansPerSecond(Math.PI*2.0/60.0);

        // When
        AngularVelocity actualInRevolutionsPerSecond = initialAngularVelocity.toUnit(AngularVelocityUnits.REVOLUTIONS_PER_SECOND);
        double actualInRevolutionsPerSecondVal = initialAngularVelocity.getInRevolutionsPerSecond();
        AngularVelocity actualInRadiansPerSecond = actualInRevolutionsPerSecond.toBaseUnit();
        double actualInRadiansPerSecondVal = actualInRevolutionsPerSecond.getInRadiansPerSecond();
        AngularVelocity expectedInRevolutionsPerSecond = AngularVelocity.ofRevolutionsPerSecond(1.0/60.0);

        // Then
        assertThat(actualInRevolutionsPerSecond.getValue()).isEqualTo(expectedInRevolutionsPerSecond.getValue());
        assertThat(actualInRevolutionsPerSecond.getValue()).isEqualTo(actualInRevolutionsPerSecondVal);
        assertThat(actualInRadiansPerSecond.getValue()).isEqualTo(actualInRadiansPerSecondVal);
        assertThat(actualInRadiansPerSecond).isEqualTo(initialAngularVelocity);
    }

    @Test
    @DisplayName("should convert rad/s to °/s and vice versa")
    void shouldProperlyConvertRadiansPerSecondToDegreesPerSecond() {
        // Given
        AngularVelocity initialAngularVelocity = AngularVelocity.ofRadiansPerSecond(Math.PI*2.0/60.0);

        // When
        AngularVelocity actualInDegreesPerSecond = initialAngularVelocity.toUnit(AngularVelocityUnits.DEGREES_PER_SECOND);
        double actualInDegreesPerSecondVal = initialAngularVelocity.getInDegreesPerSecond();
        AngularVelocity actualInRadiansPerSecond = actualInDegreesPerSecond.toBaseUnit();
        double actualInRadiansPerSecondVal = actualInDegreesPerSecond.getInRadiansPerSecond();
        AngularVelocity expectedInDegreesPerSecond = AngularVelocity.ofDegreesPerSecond(360.0 / 60.0);

        // Then
        assertThat(actualInDegreesPerSecond.getValue()).isEqualTo(expectedInDegreesPerSecond.getValue());
        assertThat(actualInDegreesPerSecond.getValue()).isEqualTo(actualInDegreesPerSecondVal);
        assertThat(actualInRadiansPerSecond.getValue()).isEqualTo(actualInRadiansPerSecondVal);
        // 4.2.0: °/s scales by the correctly rounded π/180, so 6 °/s comes back as 6 × round(π/180) =
        // 0.10471975511965978, one ulp above the correctly rounded 2π/60 (0.10471975511965977) this test starts
        // from. Two roundings, one ulp: noise, not a factor change. The factor is pinned by UnitGoldenTableTest.
        assertThat(actualInRadiansPerSecond.getValue())
                .isCloseTo(initialAngularVelocity.getValue(), within(Math.ulp(initialAngularVelocity.getValue())));
    }

    @Test
    @DisplayName("should have rad/s as base unit")
    void shouldHaveRadiansPerSecondAsBaseUnit() {
        // Given
        AngularVelocityUnit expectedBaseUnit = AngularVelocityUnits.RADIANS_PER_SECOND;

        // When
        AngularVelocity velocityInMilesPerHour = AngularVelocity.ofRevolutionsPerMinute(60.0);
        AngularVelocityUnit actualBaseUnit = velocityInMilesPerHour.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        AngularVelocity expected = AngularVelocity.ofRadiansPerSecond(Math.PI*2.0/60.0);

        // When
        AngularVelocity actual = expected.toRevolutionsPerMinute()
                .toRevolutionsPerSecond()
                .toDegreesPerSecond()
                .toRadiansPerSecond();

        double actualValue = expected.getInRadiansPerSecond();

        // Then
        // 4.2.0: a chain of conversions may now end one ulp from where it started (double arithmetic); the factors
        // themselves are pinned by UnitGoldenTableTest.
        assertThat(actual.getValue()).isCloseTo(expected.getValue(), within(Math.ulp(expected.getValue())));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}