package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.common.AngleUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class BearingTest {

    private static final double DELTA = 1e-9; // Tolerance for floating-point comparisons

    @Test
    @DisplayName("should properly create a Bearing from true bearing value")
    void shouldProperlyCreateBearingFromTrueBearing() {
        // Given
        double trueBearingValue = 270;

        // When
        Bearing bearing = Bearing.of(trueBearingValue);

        // Then
        assertThat(bearing.getValue()).isEqualTo(trueBearingValue);
        assertThat(bearing.getBaseValue()).isEqualTo(trueBearingValue);
        assertThat(bearing.getUnit()).isEqualTo(AngleUnits.DEGREES);
        assertThat(bearing.getSignedValue()).isEqualTo(-90.0);
    }

    @Test
    @DisplayName("should properly create a Bearing from signed bearing value")
    void shouldProperlyCreateBearingFromSignedBearing() {
        // Given
        double signedBearingValue = -90;

        // When
        Bearing bearing = Bearing.ofSigned(signedBearingValue);

        // Then
        assertThat(bearing.getValue()).isEqualTo(270); // -90 converted to true bearing
        assertThat(bearing.getBaseValue()).isEqualTo(270);
        assertThat(bearing.getUnit()).isEqualTo(AngleUnits.DEGREES);
    }

    @Test
    @DisplayName("should properly convert Bearing to base unit (degrees)")
    void shouldProperlyConvertBearingToBaseUnit() {
        // Given
        Bearing bearing = new Bearing(45, AngleUnits.DEGREES);

        // When
        Bearing baseBearing = bearing.toBaseUnit();

        // Then
        assertThat(baseBearing).isEqualTo(bearing);
        assertThat(baseBearing.getUnit()).isEqualTo(AngleUnits.DEGREES);
    }

    @Test
    @DisplayName("should properly convert Bearing to another angle unit")
    void shouldProperlyConvertBearingToAnotherUnit() {
        // Given
        Bearing bearing = new Bearing(180, AngleUnits.DEGREES);

        // When
        Bearing bearingInRadians = bearing.toUnit(AngleUnits.RADIANS);

        // Then
        double expectedRadians = Math.PI;
        assertThat(bearingInRadians.getValue()).isCloseTo(expectedRadians, within(DELTA));
        assertThat(bearingInRadians.getUnit()).isEqualTo(AngleUnits.RADIANS);
    }

    @Test
    @DisplayName("should properly convert Bearing to another unit by symbol")
    void shouldProperlyConvertBearingToAnotherUnitBySymbol() {
        // Given
        Bearing bearing = new Bearing(90, AngleUnits.DEGREES);

        // When
        Bearing bearingInRadians = bearing.toUnit("rad");

        // Then
        double expectedRadians = Math.PI / 2;
        assertThat(bearingInRadians.getValue()).isCloseTo(expectedRadians, within(DELTA));
        assertThat(bearingInRadians.getUnit()).isEqualTo(AngleUnits.RADIANS);
    }

    @Test
    @DisplayName("should create a new Bearing with a different value but same unit")
    void shouldCreateBearingWithNewValue() {
        // Given
        Bearing originalBearing = new Bearing(30, AngleUnits.DEGREES);

        // When
        Bearing newBearing = originalBearing.withValue(60);

        // Then
        assertThat(newBearing.getValue()).isEqualTo(60);
        assertThat(newBearing.getUnit()).isEqualTo(originalBearing.getUnit());
    }

    @Test
    @DisplayName("should correctly compare two equal Bearings")
    void shouldCompareEqualBearings() {
        // Given
        Bearing bearing1 = new Bearing(45, AngleUnits.DEGREES);
        Bearing bearing2 = new Bearing(45, AngleUnits.DEGREES);

        // Then
        assertThat(bearing1).isEqualTo(bearing2);
        assertThat(bearing1.hashCode()).hasSameHashCodeAs(bearing2.hashCode());
    }

    @Test
    @DisplayName("should correctly compare two different Bearings")
    void shouldCompareDifferentBearings() {
        // Given
        Bearing bearing1 = new Bearing(45, AngleUnits.DEGREES);
        Bearing bearing2 = new Bearing(90, AngleUnits.DEGREES);

        // Then
        assertThat(bearing1).isNotEqualTo(bearing2);
    }

    @Test
    @DisplayName("should properly return string representation of Bearing")
    void shouldProperlyReturnStringRepresentation() {
        // Given
        Bearing bearing = new Bearing(270, AngleUnits.DEGREES);

        // When
        String bearingString = bearing.toString();

        // Then
        assertThat(bearingString).isEqualTo("Bearing{270.0°}");
    }

    @Test
    @DisplayName("should create new bearing from other bearing")
    void shouldCreateNewBearingFromOtherBearing() {
        // Given
        Bearing bearing = Bearing.of(360);
        Bearing otherBearing = Bearing.ofSigned(10);

        // When
        Bearing resultingBearing = bearing.from(otherBearing);

        // Then
        assertThat(resultingBearing.getInDegrees()).isEqualTo(10);
    }

    @Test
    @DisplayName("should create new bearing from cardinal direction")
    void shouldCreateNewBearingFromCardinalDirection() {
        // Given
        // When
        Bearing bearing = Bearing.of(CardinalDirection.NORTH, Bearing.ofSigned(-10));
        Bearing bearingSouth = Bearing.of(CardinalDirection.SOUTH);

        // Then
        assertThat(bearing.getInDegrees()).isEqualTo(350);
        assertThat(bearingSouth.getInDegrees()).isEqualTo(180);
    }

    @Test
    @DisplayName("Test cardinal bearings: North, East, South, West")
    void testCardinalBearings() {
        assertThat(Bearing.north().getValue()).isEqualTo(0.0);
        assertThat(Bearing.east().getValue()).isEqualTo(90.0);
        assertThat(Bearing.south().getValue()).isEqualTo(180.0);
        assertThat(Bearing.west().getValue()).isEqualTo(270.0);
    }

    @Test
    @DisplayName("Test cardinal bearings: Northeast, Southeast, Southwest, Northwest")
    void testInterCardinalBearings() {
        assertThat(Bearing.northEast().getValue()).isEqualTo(45.0);
        assertThat(Bearing.southEast().getValue()).isEqualTo(135.0);
        assertThat(Bearing.southWest().getValue()).isEqualTo(225.0);
        assertThat(Bearing.northWest().getValue()).isEqualTo(315.0);
    }

}
