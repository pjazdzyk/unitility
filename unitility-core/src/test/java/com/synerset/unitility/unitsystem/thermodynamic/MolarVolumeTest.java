package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class MolarVolumeTest {

    @Test
    @DisplayName("should have m³/mol as base unit")
    void shouldHaveCubicMeterPerMoleAsBaseUnit() {
        // Given
        MolarVolumeUnit expectedBaseUnit = MolarVolumeUnits.CUBIC_METER_PER_MOLE;

        // When
        MolarVolume molarVolInLiters = MolarVolume.ofLitersPerMole(22.414);
        MolarVolumeUnit actualBaseUnit = molarVolInLiters.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should convert to m³/mol from L/mol and vice versa")
    void shouldProperlyConvertToCubicMeterPerMoleFromLiter() {
        // Given
        MolarVolume initialMolarVolInLiters = MolarVolume.ofLitersPerMole(22.414);

        // When
        MolarVolume actualInCubicMeter = initialMolarVolInLiters.toBaseUnit();
        MolarVolume actualInLiter = actualInCubicMeter.toUnit(MolarVolumeUnits.LITER_PER_MOLE);

        // Then
        MolarVolume expectedInCubicMeter = MolarVolume.ofCubicMetersPerMole(0.022414);
        assertThat(actualInCubicMeter.getValue()).isEqualTo(expectedInCubicMeter.getValue(), withPrecision(1E-10));
        assertThat(actualInLiter.getValue()).isEqualTo(initialMolarVolInLiters.getValue(), withPrecision(1E-10));
    }

    @Test
    @DisplayName("should convert to m³/mol from cm³/mol and vice versa")
    void shouldProperlyConvertToCubicMeterPerMoleFromCubicCentimeter() {
        // Given - Water molar volume ≈ 18 cm³/mol
        MolarVolume initialMolarVolInCM = MolarVolume.ofCubicCentimetersPerMole(18.0);

        // When
        MolarVolume actualInCubicMeter = initialMolarVolInCM.toBaseUnit();
        MolarVolume actualInCM = actualInCubicMeter.toUnit(MolarVolumeUnits.CUBIC_CENTIMETER_PER_MOLE);

        // Then
        MolarVolume expectedInCubicMeter = MolarVolume.ofCubicMetersPerMole(0.000018);
        assertThat(actualInCubicMeter).isEqualTo(expectedInCubicMeter);
        assertThat(actualInCM).isEqualTo(initialMolarVolInCM);
    }

    @Test
    @DisplayName("should provide ideal gas molar volume at STP")
    void shouldProvideIdealGasMolarVolumeAtSTP() {
        // Given/When
        MolarVolume idealGasAtSTP = MolarVolume.ofIdealGasAtSTP();

        // Then - 22.414 L/mol at STP (0°C, 1 atm)
        assertThat(idealGasAtSTP.getInLitersPerMole()).isEqualTo(22.414, withPrecision(1E-10));
        assertThat(idealGasAtSTP.getInCubicMetersPerMole()).isEqualTo(0.022414, withPrecision(1E-10));
    }

    @Test
    @DisplayName("should provide ideal gas molar volume at SATP")
    void shouldProvideIdealGasMolarVolumeAtSATP() {
        // Given/When
        MolarVolume idealGasAtSATP = MolarVolume.ofIdealGasAtSATP();

        // Then - 24.789 L/mol at SATP (25°C, 1 bar)
        assertThat(idealGasAtSATP.getInLitersPerMole()).isEqualTo(24.789);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        MolarVolume expected = MolarVolume.ofLitersPerMole(24.5);

        // When
        MolarVolume actual = expected.toCubicMeterPerMole()
                .toCubicCentimeterPerMole()
                .toLiterPerMole();
        double actualValue = expected.getInLitersPerMole();

        // Then
        assertThat(actual.getValue()).isEqualTo(expected.getValue(), withPrecision(1E-10));
        assertThat(actualValue).isEqualTo(expected.getValue());
    }
}