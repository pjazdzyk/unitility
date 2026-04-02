package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class SpecificEntropyTest {

    @Test
    @DisplayName("should have J/(kg·K) as base unit")
    void shouldHaveJoulePerKilogramKelvinAsBaseUnit() {
        // Given
        SpecificEntropyUnit expectedBaseUnit = SpecificEntropyUnits.JOULE_PER_KILOGRAM_KELVIN;

        // When
        SpecificEntropy entropyInBTU = SpecificEntropy.ofBTUPerPoundRankine(100);
        SpecificEntropyUnit actualBaseUnit = entropyInBTU.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should convert to J/(kg·K) from kJ/(kg·K) and vice versa")
    void shouldProperlyConvertToJoulePerKilogramKelvinFromKilojoule() {
        // Given
        SpecificEntropy initialEntropyInKilojoule = SpecificEntropy.ofKiloJoulePerKilogramKelvin(1.0);

        // When
        SpecificEntropy actualInJoule = initialEntropyInKilojoule.toBaseUnit();
        SpecificEntropy actualInKilojoule = actualInJoule.toUnit(SpecificEntropyUnits.KILOJOULE_PER_KILOGRAM_KELVIN);

        // Then
        SpecificEntropy expectedInJoule = SpecificEntropy.ofJoulePerKilogramKelvin(1000.0);
        assertThat(actualInJoule).isEqualTo(expectedInJoule);
        assertThat(actualInKilojoule).isEqualTo(initialEntropyInKilojoule);
    }

    @Test
    @DisplayName("should convert to J/(kg·K) from BTU/(lb·°R) and vice versa")
    void shouldProperlyConvertToJoulePerKilogramKelvinFromBTUPerPoundRankine() {
        // Given
        SpecificEntropy initialEntropyInBTU = SpecificEntropy.ofBTUPerPoundRankine(1.0);

        // When
        SpecificEntropy actualInJoule = initialEntropyInBTU.toBaseUnit();
        SpecificEntropy actualInBTU = actualInJoule.toUnit(SpecificEntropyUnits.BTU_PER_POUND_RANKINE);

        // Then
        SpecificEntropy expectedInJoule = SpecificEntropy.ofJoulePerKilogramKelvin(4186.8);
        assertThat(actualInJoule.getValue()).isEqualTo(expectedInJoule.getValue(), withPrecision(0.01));
        assertThat(actualInBTU).isEqualTo(initialEntropyInBTU);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        SpecificEntropy expected = SpecificEntropy.ofJoulePerKilogramKelvin(100.5);

        // When
        SpecificEntropy actual = expected.toKiloJoulePerKilogramKelvin()
                .toBTUPerPoundRankine()
                .toJoulePerKilogramKelvin();
        double actualValue = expected.getInJoulesPerKilogramKelvin();

        // Then
        assertThat(actual.getValue()).isEqualTo(expected.getValue(), withPrecision(1E-10));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }
}