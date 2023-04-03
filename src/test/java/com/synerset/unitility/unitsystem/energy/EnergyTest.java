package com.synerset.unitility.unitsystem.energy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnergyTest {
    @Test
    @DisplayName("should convert from J to kJ and vice versa")
    public void shouldProperlyConvertToJoulesFromKiloJoules() {
        // Given
        Energy initialEnergyInJoule = Energy.ofJoules(1000.0);

        // When
        Energy actualInKiloJoule = initialEnergyInJoule.toKiloJoules();
        Energy actualInJoule = actualInKiloJoule.toBaseUnit();

        // Then
        Energy expectedInKiloJoule = Energy.ofKiloJoules(1.0);
        assertThat(actualInKiloJoule).isEqualTo(expectedInKiloJoule);
        assertThat(actualInJoule).isEqualTo(initialEnergyInJoule);
    }

    @Test
    @DisplayName("should convert from J to mJ and vice versa")
    public void shouldProperlyConvertToJoulesFromMilliJoules() {
        // Given
        Energy initialEnergyInJoule = Energy.ofJoules(1);

        // When
        Energy actualInMilliJoule = initialEnergyInJoule.toMilliJoules();
        Energy actualInJoule = actualInMilliJoule.toBaseUnit();

        // Then
        Energy expectedInMilliJoule = Energy.ofMilliJoules(1000.0);
        assertThat(actualInMilliJoule).isEqualTo(expectedInMilliJoule);
        assertThat(actualInJoule).isEqualTo(initialEnergyInJoule);
    }

    @Test
    @DisplayName("should convert from J to MJ and vice versa")
    public void shouldProperlyConvertToJoulesFromMegaJoules() {
        // Given
        Energy initialEnergyInJoules = Energy.ofJoules(1000_000.0);

        // When
        Energy actualInMegaJoules = initialEnergyInJoules.toMegaJoules();
        Energy actualInJoules = actualInMegaJoules.toBaseUnit();

        // Then
        Energy expectedInMegaJoules = Energy.ofMegaJoules(1.0);
        assertThat(actualInMegaJoules).isEqualTo(expectedInMegaJoules);
        assertThat(actualInJoules).isEqualTo(initialEnergyInJoules);
    }

    @Test
    @DisplayName("should convert from J to BTU and vice versa")
    public void shouldProperlyConvertToJoulesFromBTU() {
        // Given
        Energy initialEnergyInJoules = Energy.ofJoules(1000.0);

        // When
        Energy actualInBTU = initialEnergyInJoules.toBTU();
        Energy actualInJoules = actualInBTU.toBaseUnit();

        // Then
        Energy expectedInBTU = Energy.ofBTU(0.9478171203133172);
        assertThat(actualInBTU).isEqualTo(expectedInBTU);
        assertThat(actualInJoules).isEqualTo(initialEnergyInJoules);
    }

    @Test
    @DisplayName("should convert from J to cal and vice versa")
    public void shouldProperlyConvertToJoulesFromCalories() {
        // Given
        Energy initialEnergyInJoules = Energy.ofJoules(4.184);

        // When
        Energy actualInCalories = initialEnergyInJoules.toCalories();
        Energy actualInJoules = actualInCalories.toBaseUnit();

        // Then
        Energy expectedInCalories = Energy.ofCalorie(1.0);
        assertThat(actualInCalories).isEqualTo(expectedInCalories);
        assertThat(actualInJoules).isEqualTo(initialEnergyInJoules);
    }

    @Test
    @DisplayName("should convert from J to kcal and vice versa")
    public void shouldProperlyConvertToJoulesFromKilocalories() {
        // Given
        Energy initialEnergyInJoule = Energy.ofJoules(4184.0);

        // When
        Energy actualInKilocalorie = initialEnergyInJoule.toKiloCalories();
        Energy actualInJoule = actualInKilocalorie.toBaseUnit();

        // Then
        Energy expectedInKilocalorie = Energy.ofKiloCalorie(1.0);
        assertThat(actualInKilocalorie).isEqualTo(expectedInKilocalorie);
        assertThat(actualInJoule).isEqualTo(initialEnergyInJoule);
    }

    @Test
    @DisplayName("should convert from J to Wh and vice versa")
    public void shouldProperlyConvertToJoulesFromWattHours() {
        // Given
        Energy initialEnergyInJoule = Energy.ofJoules(3600.0);

        // When
        Energy actualInWattHour = initialEnergyInJoule.toWattHour();
        Energy actualInJoule = actualInWattHour.toBaseUnit();

        // Then
        Energy expectedInWattHour = Energy.ofWattHour(1.0);
        assertThat(actualInWattHour).isEqualTo(expectedInWattHour);
        assertThat(actualInJoule).isEqualTo(initialEnergyInJoule);
    }

    @Test
    @DisplayName("should convert from J to kWh and vice versa")
    public void shouldProperlyConvertToJoulesFromKilowattHours() {
        // Given
        Energy initialEnergyInJoule = Energy.ofJoules(3.6e+6);

        // When
        Energy actualInKilowattHour = initialEnergyInJoule.toKilowattHour();
        Energy actualInJoule = actualInKilowattHour.toBaseUnit();

        // Then
        Energy expectedInKilowattHour = Energy.ofKilowattHour(1.0);
        assertThat(actualInKilowattHour).isEqualTo(expectedInKilowattHour);
        assertThat(actualInJoule).isEqualTo(initialEnergyInJoule);
    }

}