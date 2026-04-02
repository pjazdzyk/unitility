package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class MolarEntropyTest {

    @Test
    @DisplayName("Should create MolarEntropy with JoulesPerMoleKelvin unit")
    void shouldCreateMolarEntropyWithJoulesPerMoleKelvinUnit() {
        double value = 1000.0;
        MolarEntropy entropy = MolarEntropy.ofJoulesPerMoleKelvin(value);

        assertThat(entropy.getValue()).isEqualTo(value);
        assertThat(entropy.getUnit()).isEqualTo(MolarEntropyUnits.JOULE_PER_MOLE_KELVIN);
        assertThat(entropy.getInJoulesPerMoleKelvin()).isEqualTo(value);
    }

    @Test
    @DisplayName("Should create MolarEntropy with KilojoulesPerMoleKelvin unit")
    void shouldCreateMolarEntropyWithKilojoulesPerMoleKelvinUnit() {
        double value = 1.0;
        MolarEntropy entropy = MolarEntropy.ofKilojoulesPerMoleKelvin(value);

        assertThat(entropy.getValue()).isEqualTo(value);
        assertThat(entropy.getUnit()).isEqualTo(MolarEntropyUnits.KILOJOULE_PER_MOLE_KELVIN);
        assertThat(entropy.getInKilojoulesPerMoleKelvin()).isEqualTo(value);
        assertThat(entropy.getInJoulesPerMoleKelvin()).isEqualTo(value * 1E3);
    }

    @Test
    @DisplayName("Should create MolarEntropy with CaloriesPerMoleKelvin unit")
    void shouldCreateMolarEntropyWithCaloriesPerMoleKelvinUnit() {
        double value = 240.0;
        MolarEntropy entropy = MolarEntropy.ofCaloriesPerMoleKelvin(value);

        assertThat(entropy.getValue()).isEqualTo(value);
        assertThat(entropy.getUnit()).isEqualTo(MolarEntropyUnits.CALORIE_PER_MOLE_KELVIN);
        assertThat(entropy.getInCaloriesPerMoleKelvin()).isEqualTo(value);
        assertThat(entropy.getInJoulesPerMoleKelvin()).isCloseTo(value * 4.1868, withPrecision(1E-9));
    }

    @ParameterizedTest
    @DisplayName("Should convert between different units correctly")
    @CsvSource({
            "1000, 1.0, 238.84589662749596",
            "500, 0.5, 119.42294831374798",
            "100, 0.1, 23.8845896624"
    })
    void shouldConvertBetweenDifferentUnitsCorrectly(double joulesPerMoleKelvin, double kilojoulesPerMoleKelvin, double caloriesPerMoleKelvin) {
        MolarEntropy entropy = MolarEntropy.ofJoulesPerMoleKelvin(joulesPerMoleKelvin);

        assertThat(entropy.getInJoulesPerMoleKelvin()).isEqualTo(joulesPerMoleKelvin);
        assertThat(entropy.getInKilojoulesPerMoleKelvin()).isEqualTo(kilojoulesPerMoleKelvin);
        assertThat(entropy.getInCaloriesPerMoleKelvin())
                .isCloseTo(caloriesPerMoleKelvin, withPrecision(1E-9));
    }

    @Test
    @DisplayName("Should convert to base unit correctly")
    void shouldConvertToBaseUnitCorrectly() {
        double value = 1000.0;
        MolarEntropy entropy = MolarEntropy.ofJoulesPerMoleKelvin(value);

        MolarEntropy baseUnit = entropy.toBaseUnit();
        assertThat(baseUnit.getValue()).isEqualTo(value);
        assertThat(baseUnit.getUnit()).isEqualTo(MolarEntropyUnits.JOULE_PER_MOLE_KELVIN);
    }

    @Test
    @DisplayName("Should convert to specific unit correctly")
    void shouldConvertToSpecificUnitCorrectly() {
        double value = 1000.0;
        MolarEntropy entropy = MolarEntropy.ofJoulesPerMoleKelvin(value);

        MolarEntropy converted = entropy.toUnit(MolarEntropyUnits.KILOJOULE_PER_MOLE_KELVIN);
        assertThat(converted.getValue()).isEqualTo(1.0);
        assertThat(converted.getUnit()).isEqualTo(MolarEntropyUnits.KILOJOULE_PER_MOLE_KELVIN);
    }

    @Test
    @DisplayName("Should handle equality comparison correctly")
    void shouldHandleEqualityComparisonCorrectly() {
        MolarEntropy entropy1 = MolarEntropy.ofJoulesPerMoleKelvin(1000.0);
        MolarEntropy entropy2 = MolarEntropy.ofKilojoulesPerMoleKelvin(1.0);

        // Wykorzystuje poprawioną metodę equals opartą na baseValue
        assertThat(entropy1).isEqualTo(entropy2);
    }

    @Test
    @DisplayName("Should return correct string representation")
    void shouldReturnCorrectStringRepresentation() {
        MolarEntropy entropy = MolarEntropy.ofJoulesPerMoleKelvin(1000.0);
        // Upewnij się, że toString() w klasie MolarEntropy faktycznie produkuje taką formę
        String expected = "MolarEntropy{1000.0J/mol·K}";

        assertThat(entropy.toString()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should handle string symbol parsing correctly")
    void shouldHandleStringSymbolParsingCorrectly() {
        MolarEntropy entropy = MolarEntropy.of(1000.0, "J/mol·K");
        assertThat(entropy.getValue()).isEqualTo(1000.0);
        assertThat(entropy.getUnit()).isEqualTo(MolarEntropyUnits.JOULE_PER_MOLE_KELVIN);

        entropy = MolarEntropy.of(1.0, "kJ/mol·K");
        assertThat(entropy.getValue()).isEqualTo(1.0);
        assertThat(entropy.getUnit()).isEqualTo(MolarEntropyUnits.KILOJOULE_PER_MOLE_KELVIN);

        double calValue = 23.8845896624;
        entropy = MolarEntropy.of(calValue, "cal/mol·K");
        assertThat(entropy.getValue()).isEqualTo(calValue);
        assertThat(entropy.getUnit()).isEqualTo(MolarEntropyUnits.CALORIE_PER_MOLE_KELVIN);
    }

    @Test
    @DisplayName("Should handle null unit correctly")
    void shouldHandleNullUnitCorrectly() {
        MolarEntropy entropy = new MolarEntropy(1000.0, null);
        assertThat(entropy.getUnit()).isEqualTo(MolarEntropyUnits.JOULE_PER_MOLE_KELVIN);
    }
}