package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class PowerTest {

    @Test
    @DisplayName("should convert W from BTU/hour and vice versa")
    void shouldProperlyConvertWattsFromBTUPerHour() {
        // Given
        Power initialPowerInWatts = Power.ofWatts(1000);

        // When
        Power actualInBTUPerHour = initialPowerInWatts.toUnit(PowerUnits.BTU_PER_HOUR);
        double actualInBTUPerHourVal = initialPowerInWatts.getInBTUPerHour();
        Power actualInWatts = actualInBTUPerHour.toBaseUnit();
        double actualInWattsVal = actualInBTUPerHour.getInWatts();

        // Then
        Power expectedInBTUPerHour = Power.ofBTUPerHour(3412.141633127942);
        assertThat(actualInBTUPerHour.getValue()).isEqualTo(actualInBTUPerHourVal);
        assertThat(actualInWatts.getValue()).isEqualTo(actualInWattsVal);
        assertThat(actualInBTUPerHour.getValue()).isEqualTo(expectedInBTUPerHour.getValue(), withPrecision(1E-14));
        assertThat(actualInWatts.getValue()).isEqualTo(1000, withPrecision(1E-9));
    }

    @Test
    @DisplayName("should convert W from kW and vice versa")
    void shouldProperlyConvertWattsFromKiloWatts() {
        // Given
        Power initialPowerInWatts = Power.ofWatts(10_000);

        // When
        Power actualInKilowatts = initialPowerInWatts.toUnit(PowerUnits.KILOWATT);
        double actualInKilowattsVal = initialPowerInWatts.getInKiloWatts();
        Power actualInWatts = actualInKilowatts.toBaseUnit();

        // Then
        Power expectedInKiloWatts = Power.ofKiloWatts(10);
        assertThat(actualInKilowatts.getValue()).isEqualTo(actualInKilowattsVal);
        assertThat(actualInKilowatts).isEqualTo(expectedInKiloWatts);
        assertThat(actualInWatts).isEqualTo(initialPowerInWatts);
    }

    @Test
    @DisplayName("should convert W from MW and vice versa")
    void shouldProperlyConvertWattsFromMegaWatts() {
        // Given
        Power initialPowerInWatts = Power.ofWatts(2_000_000);

        // When
        Power actualInMegaWatts = initialPowerInWatts.toUnit(PowerUnits.MEGAWATT);
        double actualInMegaWattsVal = initialPowerInWatts.getInMegaWatts();
        Power actualInWatts = actualInMegaWatts.toBaseUnit();

        // Then
        Power expectedInMegaWatts = Power.ofMegaWatts(2);
        assertThat(actualInMegaWatts.getValue()).isEqualTo(actualInMegaWattsVal);
        assertThat(actualInMegaWatts).isEqualTo(expectedInMegaWatts);
        assertThat(actualInWatts).isEqualTo(initialPowerInWatts);
    }

    @Test
    @DisplayName("should convert W from HP and vice versa")
    void shouldProperlyConvertWattsFromHorsePower() {
        // Given
        Power initialPowerInWatts = Power.ofWatts(2000);

        // When
        Power actualInHorsePower = initialPowerInWatts.toUnit(PowerUnits.HORSE_POWER);
        double actualInHorsePowerVal = initialPowerInWatts.getInHorsePower();
        Power actualInWatts = actualInHorsePower.toBaseUnit();

        // Then
        Power expectedInHorsePower = Power.ofHorsePower(2.68204417919006);
        assertThat(actualInHorsePower.getValue()).isEqualTo(actualInHorsePowerVal);
        assertThat(actualInHorsePower.getValue()).isEqualTo(expectedInHorsePower.getValue(), withPrecision(1E-13));
        assertThat(actualInWatts.getValue()).isEqualTo(initialPowerInWatts.getValue(), withPrecision(1E-12));
    }

    @Test
    @DisplayName("should have W as base unit")
    void shouldHaveWattAsBaseUnit() {
        // Given
        PowerUnit expectedBaseUnit = PowerUnits.WATT;

        // When
        Power powerWatt = Power.ofWatts(10);
        PowerUnit actualBaseUnit = powerWatt.getUnitType().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Power expected = Power.ofWatts(10.1);

        // When
        Power actual = expected.toKiloWatts()
                .toBTUPerHour()
                .toMegaWatts()
                .toHorsePower()
                .toWatts();

        double actualValue = expected.getInWatts();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}