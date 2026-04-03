package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.util.ValueFormatter;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MolarMassTest {

    @Test
    @DisplayName("Should create molar mass with kilogram per mole unit")
    void shouldCreateMolarMassWithKilogramPerMoleUnit() {
        double value = 18.015;
        MolarMass molarMass = MolarMass.ofKilogramPerMole(value);

        assertThat(molarMass.getValue()).isEqualTo(value);
        assertThat(molarMass.getUnit()).isEqualTo(MolarMassUnits.KILOGRAM_PER_MOLE);
        assertThat(molarMass.getBaseValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("Should create molar mass from string symbol")
    void shouldCreateMolarMassFromStringSymbol() {
        double value = 18.015;
        MolarMass molarMass = MolarMass.of(value, "kg/mol");

        assertThat(molarMass.getValue()).isEqualTo(value);
        assertThat(molarMass.getUnit()).isEqualTo(MolarMassUnits.KILOGRAM_PER_MOLE);
        assertThat(molarMass.getBaseValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("Should convert to base unit correctly")
    void shouldConvertToBaseUnitCorrectly() {
        double value = 18.015;
        MolarMass molarMass = MolarMass.of(value, MolarMassUnits.KILOGRAM_PER_MOLE);

        MolarMass baseUnit = molarMass.toBaseUnit();

        assertThat(baseUnit.getValue()).isEqualTo(value);
        assertThat(baseUnit.getUnit()).isEqualTo(MolarMassUnits.KILOGRAM_PER_MOLE);
    }

    @Test
    @DisplayName("Should convert between units correctly")
    void shouldConvertBetweenUnitsCorrectly() {
        double value = 18.015;
        MolarMass molarMass = MolarMass.of(value, MolarMassUnits.KILOGRAM_PER_MOLE);

        MolarMass converted = molarMass.toUnit(MolarMassUnits.KILOGRAM_PER_MOLE);

        assertThat(converted.getValue()).isEqualTo(value);
        assertThat(converted.getUnit()).isEqualTo(MolarMassUnits.KILOGRAM_PER_MOLE);
    }

    @Test
    @DisplayName("Should return correct value in different units")
    void shouldReturnCorrectValueInDifferentUnits() {
        double value = 18.015;
        MolarMass molarMass = MolarMass.of(value, MolarMassUnits.KILOGRAM_PER_MOLE);

        double inGramPerMole = molarMass.getInGramPerMole();
        double inKilogramPerKilomole = molarMass.getInKilogramPerKilomole();
        double inMilligramPerMillimole = molarMass.getInMilligramPerMillimole();

        assertThat(inGramPerMole).isEqualTo(value * 1000.0, Offset.offset(1E-13));
        assertThat(inKilogramPerKilomole).isEqualTo(value * 1000.0, Offset.offset(1E-13));
        assertThat(inMilligramPerMillimole).isEqualTo(value * 1000.0, Offset.offset(1E-13));
    }

    @Test
    @DisplayName("Should handle equality comparison correctly")
    void shouldHandleEqualityComparisonCorrectly() {
        MolarMass mass1 = MolarMass.of(18.015, MolarMassUnits.KILOGRAM_PER_MOLE);
        MolarMass mass2 = MolarMass.of(18.015, MolarMassUnits.KILOGRAM_PER_MOLE);
        MolarMass mass3 = MolarMass.of(18.015, MolarMassUnits.KILOGRAM_PER_MOLE);

        assertThat(mass1).isEqualTo(mass2);
        assertThat(mass2).isEqualTo(mass3);
        assertThat(mass1).isEqualTo(mass3);
    }

    @Test
    @DisplayName("Should format value correctly")
    void shouldFormatValueCorrectly() {
        double value = 18.015;
        MolarMass molarMass = MolarMass.of(value, MolarMassUnits.KILOGRAM_PER_MOLE);

        String formatted = ValueFormatter.toStringWithRelevantDigits(molarMass.getValue(), 5);

        assertThat(formatted).isEqualTo("18.015");
    }
}