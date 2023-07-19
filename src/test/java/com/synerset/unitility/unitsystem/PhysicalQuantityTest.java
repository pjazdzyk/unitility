package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.Distance;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhysicalQuantityTest {

    @Test
    @DisplayName("should be equals in business requirements if their base units and values in base units are the same")
    void shouldBeEquals_whenBaseUnitsAndValuesAreTheSame() {
        // Given
        // When
        Distance distanceInMeters = Distance.ofMeters(100);
        Distance distanceInMiles = distanceInMeters.toMile();

        // Then

        // This equality is true from the business point of view
        assertThat(distanceInMeters.isEqualsWithPrecision(distanceInMiles, 1E-13)).isTrue();

        // This is not equals, because from programmatic point of view, they are different
        assertThat(distanceInMeters).isNotEqualTo(distanceInMiles);
    }

    @Test
    @DisplayName("should return String for quantity accordingly to given relevant digits")
    void shouldStringForQuantity_whenRelevantDigitsAreGiven() {
        // Given
        Angle angle = Angle.ofDegrees(30.123456789);
        Distance distance = Distance.ofMeters(100.1238);

        // When
        String actualAngleOutput = angle.toStringWithRelevantDigits(3);
        String actualDistanceOutput = distance.toStringWithRelevantDigits(3);

        // Then
        String expectedAngleOutput = "30.123°";
        String expectedDistanceOutput = "100.124 m";
        assertThat(actualAngleOutput).isEqualTo(expectedAngleOutput);
        assertThat(actualDistanceOutput).isEqualTo(expectedDistanceOutput);
    }

    @Test
    @DisplayName("should assert that first quantity is greater than second")
    void shouldAssertThatFirstQuantityIsGreaterThanSecond() {
        // Given
        Temperature smallerTemp = Temperature.ofCelsius(-20.0);
        Temperature greaterTemp = Temperature.ofCelsius(0.0);
        Temperature smallerOrEqualTemp = Temperature.ofCelsius(-20.0);
        Temperature greaterOrEqualTemp = Temperature.ofCelsius(0.0);

        // When
        boolean firstIsSmaller = smallerTemp.isLowerThan(greaterTemp);
        boolean secondIsGreater = greaterTemp.isGreaterThan(smallerTemp);
        boolean firstIsEqualOrLower = smallerTemp.isEqualOrLowerThan(smallerOrEqualTemp);
        boolean secondIsEqualOrGreater = greaterTemp.isEqualOrGreaterThan(greaterOrEqualTemp);

        // Then
        assertThat(firstIsSmaller).isTrue();
        assertThat(secondIsGreater).isTrue();
        assertThat(firstIsEqualOrLower).isTrue();
        assertThat(secondIsEqualOrGreater).isTrue();
    }


    @Test
    @DisplayName("should return value in base unit")
    void shouldReturnValueInBaseUnit() {
        // Given
        Distance distanceInKm = Distance.ofKilometers(1.0);

        // When
        double actualDistanceInBaseUnit = distanceInKm.getBaseValue();

        // Then
        double expectedDistanceInBaseUnit = 1000; // meters
        assertThat(actualDistanceInBaseUnit).isEqualTo(expectedDistanceInBaseUnit);
    }


}
