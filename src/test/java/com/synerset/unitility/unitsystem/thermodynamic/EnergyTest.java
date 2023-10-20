package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnergyTest {
    @Test
    @DisplayName("should convert from J to kJ and vice versa")
    void shouldProperlyConvertToJoulesFromKiloJoules() {
        // Given
        Energy initialEnergyInJoule = Energy.ofJoules(1000.0);

        // When
        Energy actualInKiloJoule = initialEnergyInJoule.toUnit(EnergyUnits.KILOJOULE);
        double actualInKiloJouleVal = initialEnergyInJoule.getInKiloJoules();
        Energy actualInJoule = actualInKiloJoule.toBaseUnit();
        double actualInJouleVal = actualInKiloJoule.getInJoules();

        // Then
        Energy expectedInKiloJoule = Energy.ofKiloJoules(1.0);
        assertThat(actualInKiloJoule.getValue()).isEqualTo(actualInKiloJouleVal);
        assertThat(actualInJoule.getValue()).isEqualTo(actualInJouleVal);
        assertThat(actualInKiloJoule).isEqualTo(expectedInKiloJoule);
        assertThat(actualInJoule).isEqualTo(initialEnergyInJoule);
    }

    @Test
    @DisplayName("should convert from J to mJ and vice versa")
    void shouldProperlyConvertToJoulesFromMilliJoules() {
        // Given
        Energy initialEnergyInJoule = Energy.ofJoules(1);

        // When
        Energy actualInMilliJoule = initialEnergyInJoule.toUnit(EnergyUnits.MILLIJOULE);
        double actualInMilliJouleVal = initialEnergyInJoule.getInMilliJoules();
        Energy actualInJoule = actualInMilliJoule.toBaseUnit();

        // Then
        Energy expectedInMilliJoule = Energy.ofMilliJoules(1000.0);
        assertThat(actualInMilliJoule.getValue()).isEqualTo(actualInMilliJouleVal);
        assertThat(actualInMilliJoule).isEqualTo(expectedInMilliJoule);
        assertThat(actualInJoule).isEqualTo(initialEnergyInJoule);
    }

    @Test
    @DisplayName("should convert from J to MJ and vice versa")
    void shouldProperlyConvertToJoulesFromMegaJoules() {
        // Given
        Energy initialEnergyInJoule = Energy.ofJoules(1000_000.0);

        // When
        Energy actualInMegaJoules = initialEnergyInJoule.toUnit(EnergyUnits.MEGAJOULE);
        double actualInMegaJoulesVal = initialEnergyInJoule.getInMegaJoules();
        Energy actualInJoules = actualInMegaJoules.toBaseUnit();

        // Then
        Energy expectedInMegaJoules = Energy.ofMegaJoules(1.0);
        assertThat(actualInMegaJoules.getValue()).isEqualTo(actualInMegaJoulesVal);
        assertThat(actualInMegaJoules).isEqualTo(expectedInMegaJoules);
        assertThat(actualInJoules).isEqualTo(initialEnergyInJoule);
    }

    @Test
    @DisplayName("should convert from J to BTU and vice versa")
    void shouldProperlyConvertToJoulesFromBTU() {
        // Given
        Energy initialEnergyInJoule = Energy.ofJoules(1000.0);

        // When
        Energy actualInBTU = initialEnergyInJoule.toUnit(EnergyUnits.BTU);
        double actualInBTUVal = initialEnergyInJoule.getInBTUs();
        Energy actualInJoules = actualInBTU.toBaseUnit();

        // Then
        Energy expectedInBTU = Energy.ofBTU(0.9478171203133172);
        assertThat(actualInBTU.getValue()).isEqualTo(actualInBTUVal);
        assertThat(actualInBTU).isEqualTo(expectedInBTU);
        assertThat(actualInJoules).isEqualTo(initialEnergyInJoule);
    }

    @Test
    @DisplayName("should convert from J to cal and vice versa")
    void shouldProperlyConvertToJoulesFromCalories() {
        // Given
        Energy initialEnergyInJoule = Energy.ofJoules(4.184);

        // When
        Energy actualInCalories = initialEnergyInJoule.toUnit(EnergyUnits.CALORIE);
        double actualInCaloriesVal = initialEnergyInJoule.getInCalories();
        Energy actualInJoules = actualInCalories.toBaseUnit();

        // Then
        Energy expectedInCalories = Energy.ofCalorie(1.0);
        assertThat(actualInCalories.getValue()).isEqualTo(actualInCaloriesVal);
        assertThat(actualInCalories).isEqualTo(expectedInCalories);
        assertThat(actualInJoules).isEqualTo(initialEnergyInJoule);
    }

    @Test
    @DisplayName("should convert from J to kcal and vice versa")
    void shouldProperlyConvertToJoulesFromKilocalories() {
        // Given
        Energy initialEnergyInJoule = Energy.ofJoules(4184.0);

        // When
        Energy actualInKilocalorie = initialEnergyInJoule.toUnit(EnergyUnits.KILOCALORIE);
        double actualInKilocalorieVal = initialEnergyInJoule.getInKiloCalories();
        Energy actualInJoule = actualInKilocalorie.toBaseUnit();

        // Then
        Energy expectedInKilocalorie = Energy.ofKiloCalorie(1.0);
        assertThat(actualInKilocalorie.getValue()).isEqualTo(actualInKilocalorieVal);
        assertThat(actualInKilocalorie).isEqualTo(expectedInKilocalorie);
        assertThat(actualInJoule).isEqualTo(initialEnergyInJoule);
    }

    @Test
    @DisplayName("should convert from J to Wh and vice versa")
    void shouldProperlyConvertToJoulesFromWattHours() {
        // Given
        Energy initialEnergyInJoule = Energy.ofJoules(3600.0);

        // When
        Energy actualInWattHour = initialEnergyInJoule.toUnit(EnergyUnits.WATT_HOUR);
        double actualInWattHourVal = initialEnergyInJoule.getInWattHours();
        Energy actualInJoule = actualInWattHour.toBaseUnit();

        // Then
        Energy expectedInWattHour = Energy.ofWattHour(1.0);
        assertThat(actualInWattHour.getValue()).isEqualTo(actualInWattHourVal);
        assertThat(actualInWattHour).isEqualTo(expectedInWattHour);
        assertThat(actualInJoule).isEqualTo(initialEnergyInJoule);
    }

    @Test
    @DisplayName("should convert from J to kWh and vice versa")
    void shouldProperlyConvertToJoulesFromKilowattHours() {
        // Given
        Energy initialEnergyInJoule = Energy.ofJoules(3.6e+6);

        // When
        Energy actualInKilowattHour = initialEnergyInJoule.toUnit(EnergyUnits.KILOWATT_HOUR);
        double actualInKiloWattHourVal = initialEnergyInJoule.getInKilowattHours();
        Energy actualInJoule = actualInKilowattHour.toBaseUnit();

        // Then
        Energy expectedInKilowattHour = Energy.ofKilowattHour(1.0);
        assertThat(actualInKilowattHour.getValue()).isEqualTo(actualInKiloWattHourVal);
        assertThat(actualInKilowattHour).isEqualTo(expectedInKilowattHour);
        assertThat(actualInJoule).isEqualTo(initialEnergyInJoule);
    }

    @Test
    @DisplayName("should have J as base unit")
    void shouldHaveJouleAsBaseUnit() {
        // Given
        EnergyUnits expectedBaseUnit = EnergyUnits.JOULE;

        // When
        Energy energyInJoule = Energy.ofJoules(10);
        EnergyUnits actualBaseUnit = energyInJoule.getUnitType().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Energy expected = Energy.ofJoules(10.1);

        // When
        Energy actual = expected.toMilliJoules()
                .toKiloJoules()
                .toMegaJoules()
                .toBTU()
                .toCalories()
                .toKiloCalories()
                .toWattHour()
                .toKilowattHour()
                .toJoules();

        double actualValue = expected.getInJoules();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}