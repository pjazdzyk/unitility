package com.synerset.unitility.unitsystem.definitions;

import com.synerset.unitility.unitsystem.flow.VolumetricFlowUnits;
import com.synerset.unitility.unitsystem.thermodynamic.TemperatureUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.DoubleUnaryOperator;

import static org.assertj.core.api.Assertions.assertThat;

class LinearScaleTest {

    @Test
    @DisplayName("a scale that is the reciprocal of a whole number converts through that whole number, exactly")
    void reciprocalOfWholeNumberConvertsExactly() {
        // Given
        double perHour = 1.0 / 3600.0;
        DoubleUnaryOperator toBase = LinearScale.toBase(perHour);
        DoubleUnaryOperator fromBase = LinearScale.fromBase(perHour);

        // Then
        assertThat(LinearScale.exactReciprocal(perHour)).isEqualTo(3600.0);
        assertThat(fromBase.applyAsDouble(1.0)).isEqualTo(3600.0);
        assertThat(toBase.applyAsDouble(3600.0)).isEqualTo(1.0);
        // 1 m³/s is exactly 3 600 000 L/h, not 3 600 000.000 000 000 5.
        assertThat(VolumetricFlowUnits.LITRE_PER_HOUR.fromValueInBaseUnit(1.0)).isEqualTo(3600000.0);
    }

    @Test
    @DisplayName("any other scale multiplies to the base unit and divides back")
    void otherScalesMultiplyAndDivide() {
        // Given
        double foot = 0.3048;
        DoubleUnaryOperator toBase = LinearScale.toBase(foot);
        DoubleUnaryOperator fromBase = LinearScale.fromBase(foot);

        // Then
        assertThat(LinearScale.exactReciprocal(foot)).isEqualTo(0.0);
        assertThat(LinearScale.exactReciprocal(1.0)).isEqualTo(0.0);
        assertThat(LinearScale.exactReciprocal(1000.0)).isEqualTo(0.0);
        assertThat(toBase.applyAsDouble(10.0)).isEqualTo(10.0 * foot);
        assertThat(fromBase.applyAsDouble(3.048)).isEqualTo(3.048 / foot);
    }

    @Test
    @DisplayName("the affine temperatures are anchored at the ice point: 0 °C and 32 °F are exactly 273.15 K and back")
    void affineTemperaturesAreAnchoredAtTheIcePoint() {
        assertThat(TemperatureUnits.CELSIUS.toValueInBaseUnit(0.0)).isEqualTo(273.15);
        assertThat(TemperatureUnits.FAHRENHEIT.toValueInBaseUnit(32.0)).isEqualTo(273.15);
        assertThat(TemperatureUnits.FAHRENHEIT.fromValueInBaseUnit(273.15)).isEqualTo(32.0);
        assertThat(TemperatureUnits.CELSIUS.fromValueInBaseUnit(273.15)).isEqualTo(0.0);
        // 212 °F = 100 °C = 373.15 K; NIST SP 811 App. B.8: T/K = (t/°F + 459.67)/1.8.
        assertThat(TemperatureUnits.FAHRENHEIT.toValueInBaseUnit(212.0)).isEqualTo(373.15);
    }

}
