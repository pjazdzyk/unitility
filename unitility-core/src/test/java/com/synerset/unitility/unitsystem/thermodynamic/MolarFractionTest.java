package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.util.ValueFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MolarFractionTest {

    @Test
    @DisplayName("Should create molar fraction with dimensionless unit")
    void shouldCreateMolarFractionWithDimensionlessUnit() {
        double value = 0.5;
        MolarFraction molarFraction = MolarFraction.ofDimensionless(value);

        assertThat(molarFraction.getValue()).isEqualTo(value);
        assertThat(molarFraction.getUnit()).isEqualTo(MolarFractionUnits.DIMENSIONLESS);
        assertThat(molarFraction.getBaseValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("Should create molar fraction from string symbol")
    void shouldCreateMolarFractionFromStringSymbol() {
        double value = 0.25;
        MolarFraction molarFraction = MolarFraction.of(value, "dimensionless");

        assertThat(molarFraction.getValue()).isEqualTo(value);
        assertThat(molarFraction.getUnit()).isEqualTo(MolarFractionUnits.DIMENSIONLESS);
        assertThat(molarFraction.getBaseValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("Should convert to base unit correctly")
    void shouldConvertToBaseUnitCorrectly() {
        double value = 0.75;
        MolarFraction molarFraction = MolarFraction.of(value, MolarFractionUnits.DIMENSIONLESS);

        MolarFraction baseUnit = molarFraction.toBaseUnit();

        assertThat(baseUnit.getValue()).isEqualTo(value);
        assertThat(baseUnit.getUnit()).isEqualTo(MolarFractionUnits.DIMENSIONLESS);
    }

    @Test
    @DisplayName("Should convert between units correctly")
    void shouldConvertBetweenUnitsCorrectly() {
        double value = 0.3;
        MolarFraction molarFraction = MolarFraction.of(value, MolarFractionUnits.DIMENSIONLESS);

        MolarFraction converted = molarFraction.toUnit(MolarFractionUnits.DIMENSIONLESS);

        assertThat(converted.getValue()).isEqualTo(value);
        assertThat(converted.getUnit()).isEqualTo(MolarFractionUnits.DIMENSIONLESS);
    }

    @Test
    @DisplayName("Should return correct value in dimensionless")
    void shouldReturnCorrectValueInDimensionless() {
        double value = 0.8;
        MolarFraction molarFraction = MolarFraction.of(value, MolarFractionUnits.DIMENSIONLESS);

        double inDimensionless = molarFraction.getInDimensionless();

        assertThat(inDimensionless).isEqualTo(value);
    }

    @Test
    @DisplayName("Should handle equality comparison correctly")
    void shouldHandleEqualityComparisonCorrectly() {
        MolarFraction fraction1 = MolarFraction.of(0.5, MolarFractionUnits.DIMENSIONLESS);
        MolarFraction fraction2 = MolarFraction.of(0.5, MolarFractionUnits.DIMENSIONLESS);
        MolarFraction fraction3 = MolarFraction.of(0.5, MolarFractionUnits.DIMENSIONLESS);

        assertThat(fraction1).isEqualTo(fraction2);
        assertThat(fraction2).isEqualTo(fraction3);
        assertThat(fraction1).isEqualTo(fraction3);
    }

    @Test
    @DisplayName("Should format value correctly")
    void shouldFormatValueCorrectly() {
        double value = 0.123456789;
        MolarFraction molarFraction = MolarFraction.of(value, MolarFractionUnits.DIMENSIONLESS);

        String formatted = ValueFormatter.toStringWithRelevantDigits(molarFraction.getValue(), 9);

        assertThat(formatted).isEqualTo("0.123456789");
    }
}