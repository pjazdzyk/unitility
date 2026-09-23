package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

/**
 * A temperature interval converts by ratio, never by offset.
 *
 * <p>The distinction this quantity exists for, asserted directly against {@link Temperature} so
 * that the two can never quietly converge on one behaviour.</p>
 */
@DisplayName("TemperatureDifference — an interval, not a point on a scale")
class TemperatureDifferenceTest {

    @Test
    @DisplayName("should have kelvin as base unit")
    void shouldHaveKelvinAsBaseUnit() {
        assertThat(TemperatureDifference.ofCelsius(5.0).getUnit().getBaseUnit())
                .isEqualTo(TemperatureDifferenceUnits.KELVIN);
    }

    @Test
    @DisplayName("a degree Celsius interval is exactly a kelvin")
    void celsiusIntervalIsAKelvin() {
        assertThat(TemperatureDifference.ofCelsius(12.5).getInKelvins()).isEqualTo(12.5);
        assertThat(TemperatureDifference.ofKelvins(12.5).getInCelsius()).isEqualTo(12.5);
    }

    /** The bug that motivated the type: a 1.15 K fan rise read as 34.07 °F instead of 2.07 °F. */
    @Test
    @DisplayName("converts to Fahrenheit by ratio, where a Temperature would add the ice-point offset")
    void fahrenheitConversionCarriesNoOffset() {
        TemperatureDifference rise = TemperatureDifference.ofKelvins(1.15);

        assertThat(rise.getInFahrenheits())
                .as("1.15 K of rise is 2.07 degrees Fahrenheit of rise")
                .isCloseTo(2.07, within(1.0e-9));
        assertThat(Temperature.ofCelsius(1.15).getInFahrenheits())
                .as("the same number as an absolute temperature is a completely different figure")
                .isCloseTo(34.07, within(1.0e-9));
    }

    @Test
    @DisplayName("a rankine interval is the same size as a Fahrenheit one")
    void rankineMatchesFahrenheit() {
        assertThat(TemperatureDifference.ofKelvins(10.0).getInRankine())
                .isEqualTo(TemperatureDifference.ofKelvins(10.0).getInFahrenheits());
    }

    @Test
    @DisplayName("round-trips through every unit")
    void roundTrips() {
        TemperatureDifference original = TemperatureDifference.ofKelvins(7.25);

        assertThat(original.toCelsius().toFahrenheit().toRankine().toKelvins().getInKelvins())
                .isCloseTo(7.25, within(1.0e-12));
    }

    @Test
    @DisplayName("between() subtracts in kelvin, so the operands' scales cannot leak an offset")
    void betweenIsScaleIndependent() {
        TemperatureDifference fromCelsius = TemperatureDifference.between(
                Temperature.ofCelsius(20.0), Temperature.ofCelsius(32.0));
        TemperatureDifference fromFahrenheit = TemperatureDifference.between(
                Temperature.ofFahrenheit(68.0), Temperature.ofFahrenheit(89.6));

        assertThat(fromCelsius.getInKelvins()).isCloseTo(12.0, within(1.0e-9));
        assertThat(fromFahrenheit.getInKelvins()).isCloseTo(12.0, within(1.0e-9));
    }

    @Test
    @DisplayName("a negative interval is legal, because a drop is a difference too")
    void negativeIntervalIsLegal() {
        assertThat(TemperatureDifference.between(
                Temperature.ofCelsius(24.0), Temperature.ofCelsius(14.0)).getInKelvins())
                .isCloseTo(-10.0, within(1.0e-9));
    }

    @Test
    @DisplayName("equality holds across units")
    void equalityAcrossUnits() {
        assertThat(TemperatureDifference.ofKelvins(1.0))
                .isEqualTo(TemperatureDifference.ofCelsius(1.0));
        assertThat(TemperatureDifference.ofFahrenheit(3.24).getInKelvins())
                .as("3.24 degF of interval is 1.8 K, to within double rounding")
                .isCloseTo(1.8, within(1.0e-15));
    }

    @Test
    @DisplayName("a blank symbol resolves to kelvin, and the degree symbols parse")
    void symbolParsing() {
        assertThat(TemperatureDifferenceUnits.fromSymbol("")).isEqualTo(TemperatureDifferenceUnits.KELVIN);
        assertThat(TemperatureDifferenceUnits.fromSymbol("K")).isEqualTo(TemperatureDifferenceUnits.KELVIN);
        assertThat(TemperatureDifferenceUnits.fromSymbol("°C")).isEqualTo(TemperatureDifferenceUnits.CELSIUS);
        assertThat(TemperatureDifferenceUnits.fromSymbol("°F")).isEqualTo(TemperatureDifferenceUnits.FAHRENHEIT);
        assertThat(TemperatureDifferenceUnits.fromSymbol("°R")).isEqualTo(TemperatureDifferenceUnits.RANKINE);
    }

    @Test
    @DisplayName("zero is zero in every unit")
    void zeroIsInvariant() {
        assertThat(TemperatureDifference.ofKelvins(0.0).getInFahrenheits()).isZero();
        assertThat(TemperatureDifference.ofFahrenheit(0.0).getInKelvins()).isZero();
    }
}
