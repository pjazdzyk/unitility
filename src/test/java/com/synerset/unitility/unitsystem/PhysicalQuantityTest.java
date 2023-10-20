package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.Distance;
import com.synerset.unitility.unitsystem.common.DistanceUnits;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.thermodynamic.Pressure;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.thermodynamic.ThermalDiffusivity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PhysicalQuantityTest {

    // Equality0
    @Test
    @DisplayName("should be equals if base units and values in base units are the same")
    void shouldBeEquals_whenBaseUnitsAndValuesAreTheSame() {
        // Given
        // When
        Distance distanceInMeters = Distance.ofMeters(100);
        Distance distanceInMiles = distanceInMeters.toUnit(DistanceUnits.MILE);

        // Then
        assertThat(distanceInMeters.isEqualsWithPrecision(distanceInMiles, 1E-13)).isTrue();
    }

    @Test
    @DisplayName("should return String for quantity accordingly to given relevant digits")
    void shouldReturnStringWithRelevantDigitsForQuantity_whenRelevantDigitsAreGiven() {
        // Given
        Angle angle = Angle.ofDegrees(30.123456789);
        Distance distance = Distance.ofMeters(100.1238);

        // When
        String actualAngleOutput = angle.toFormattedString(3);
        String actualDistanceOutput = distance.toFormattedString(3);

        // Then
        String expectedAngleOutput = "30.123°";
        String expectedDistanceOutput = "100.124 m";
        assertThat(actualAngleOutput).isEqualTo(expectedAngleOutput);
        assertThat(actualDistanceOutput).isEqualTo(expectedDistanceOutput);
    }

    @Test
    @DisplayName("should assert that first quantity is greater than second")
    void shouldAssertThatFirstQuantityIsGreaterThanSecond() {
        // Given
        Temperature smallerTemp = Temperature.ofCelsius(-20.0);
        Temperature greaterTemp = Temperature.ofCelsius(0.0);
        Temperature smallerOrEqualTemp = Temperature.ofCelsius(-20.0);
        Temperature greaterOrEqualTemp = Temperature.ofCelsius(0.0);

        // When
        boolean firstIsSmaller = smallerTemp.isLowerThan(greaterTemp);
        boolean secondIsGreater = greaterTemp.isGreaterThan(smallerTemp);
        boolean firstIsEqualOrLower = smallerTemp.isEqualOrLowerThan(smallerOrEqualTemp);
        boolean secondIsEqualOrGreater = greaterTemp.isEqualOrGreaterThan(greaterOrEqualTemp);
        boolean bothAreTheSame = greaterTemp.isGreaterThan(greaterTemp);

        // Then
        assertThat(firstIsSmaller).isTrue();
        assertThat(secondIsGreater).isTrue();
        assertThat(firstIsEqualOrLower).isTrue();
        assertThat(secondIsEqualOrGreater).isTrue();
        assertThat(bothAreTheSame).isFalse();
    }

    @Test
    @DisplayName("should correctly add value to quantity")
    void shouldAddValueToQuantity() {
        // Given
        Temperature temperature = Temperature.ofCelsius(20);

        // When
        Temperature actualTemperature = temperature.add(20);

        // Then
        Temperature exptectedTemperature = Temperature.ofCelsius(40);
        assertThat(actualTemperature).isEqualTo(exptectedTemperature);
    }

    @Test
    @DisplayName("should correctly subtract value from quantity")
    void shouldSubtractValueToQuantity() {
        // Given
        Temperature temperature = Temperature.ofCelsius(20);

        // When
        Temperature actualTemperature = temperature.subtract(20);

        // Then
        Temperature exptectedTemperature = Temperature.ofCelsius(0);
        assertThat(actualTemperature).isEqualTo(exptectedTemperature);
    }

    @Test
    @DisplayName("should correctly add quantities of the same type, but different units")
    void shouldAddQuantityToSourceQuantity() {
        // Given
        Temperature sourceTemperature = Temperature.ofCelsius(20);
        Temperature temperatureToAdd = Temperature.ofKelvins(20 + 273.15);

        // When
        Temperature actualTemperature = sourceTemperature.add(temperatureToAdd);

        // Then
        Temperature exptectedTemperature = Temperature.ofCelsius(40);
        assertThat(actualTemperature).isEqualTo(exptectedTemperature);
    }

    @Test
    @DisplayName("should correctly subtract quantities of the same type, but different units")
    void shouldSubtractQuantityToSourceQuantity() {
        // Given
        Temperature sourceTemperature = Temperature.ofCelsius(20);
        Temperature temperatureToAdd = Temperature.ofKelvins(20 + 273.15);

        // When
        Temperature actualTemperature = sourceTemperature.subtract(temperatureToAdd);

        // Then
        Temperature exptectedTemperature = Temperature.ofCelsius(0);
        assertThat(actualTemperature).isEqualTo(exptectedTemperature);
    }

    @Test
    @DisplayName("should correctly multiply quantity by value")
    void shouldMultiplyValueToQuantity() {
        // Given
        Temperature temperature = Temperature.ofCelsius(20);

        // When
        Temperature actualTemperature = temperature.multiply(2);

        // Then
        Temperature exptectedTemperature = Temperature.ofCelsius(40);
        assertThat(actualTemperature).isEqualTo(exptectedTemperature);
    }

    @Test
    @DisplayName("should correctly multiply quantities of the same type, but different units")
    void shouldMultiplyQuantityToSourceQuantity() {
        // Given
        Temperature sourceTemperature = Temperature.ofCelsius(20);
        Pressure pressure = Pressure.ofPascal(2);

        // When
        double actualMultiplyResult = sourceTemperature.multiply(pressure);

        // Then
        double expectedMultiplyResult = 40;
        assertThat(actualMultiplyResult).isEqualTo(expectedMultiplyResult);
    }

    @Test
    @DisplayName("should correctly divide quantity by value")
    void shouldDivideValueToQuantity() {
        // Given
        Temperature temperature = Temperature.ofCelsius(20);

        // When
        Temperature actualTemperature = temperature.divide(2);

        // Then
        Temperature exptectedTemperature = Temperature.ofCelsius(10);
        assertThat(actualTemperature).isEqualTo(exptectedTemperature);
    }

    @Test
    @DisplayName("should throw an exception if divided by 0")
    void shouldNotDivideByZeroThrowingException() {
        // Given
        Temperature temperature = Temperature.ofCelsius(20);

        // Then
        assertThatThrownBy(() -> temperature.divide(0))
                .isInstanceOf(UnitSystemArgumentException.class)
                .hasMessage("Division by zero is not allowed. Please provide a non-zero divider value.");
    }

    @Test
    @DisplayName("should correctly divide quantities of the same type, but different units")
    void shouldDivideQuantityToSourceQuantity() {
        // Given
        Temperature sourceTemperature = Temperature.ofCelsius(20);
        Pressure pressure = Pressure.ofPascal(2);

        // When
        double actualDivideResult = sourceTemperature.divide(pressure);

        // Then
        double expectedDivideResult = 10;
        assertThat(actualDivideResult).isEqualTo(expectedDivideResult);
    }

    @Test
    @DisplayName("should subtract value of current unit from number")
    void shouldSubtractFromValue() {
        // Given
        Temperature sourceTemperature = Temperature.ofCelsius(0.5);

        // When
        Temperature actualTemperature = sourceTemperature.subtractFromValue(1);

        // Then
        Temperature expectedTemperature = Temperature.ofCelsius((1 - 0.5));
        assertThat(actualTemperature).isEqualTo(expectedTemperature);
    }

    @Test
    @DisplayName("should correctly indicate if value is positive, negative or zero")
    void shouldIndicateIfValueIsPositiveNegativeOrZero() {
        // Given
        Temperature negativeTemp = Temperature.ofCelsius(-20);
        Temperature zeroTemp = Temperature.ofCelsius(0);
        Temperature positiveTemp = Temperature.ofCelsius(20);

        // When

        // Then
        assertThat(negativeTemp.isNegative()).isTrue();
        assertThat(negativeTemp.isNegativeOrZero()).isTrue();
        assertThat(negativeTemp.isZero()).isFalse();
        assertThat(negativeTemp.isPositive()).isFalse();
        assertThat(negativeTemp.isPositiveOrZero()).isFalse();

        assertThat(zeroTemp.isNegative()).isFalse();
        assertThat(zeroTemp.isNegativeOrZero()).isTrue();
        assertThat(zeroTemp.isZero()).isTrue();
        assertThat(zeroTemp.isPositive()).isFalse();
        assertThat(zeroTemp.isPositiveOrZero()).isTrue();

        assertThat(positiveTemp.isNegative()).isFalse();
        assertThat(positiveTemp.isNegativeOrZero()).isFalse();
        assertThat(positiveTemp.isZero()).isFalse();
        assertThat(positiveTemp.isPositive()).isTrue();
        assertThat(positiveTemp.isPositiveOrZero()).isTrue();

    }

    @Test
    @DisplayName("should return property unit symbol")
    void shouldReturnPropertyUnitSymbol() {
        // Given
        ThermalDiffusivity thermalDiffusivity = ThermalDiffusivity.ofSquareFeetPerSecond(0.5);

        // When
        String actualUnitSymbol = thermalDiffusivity.getUnitSymbol();

        // Then
        String expectedUnitSymbol = "ft²/s";
        assertThat(actualUnitSymbol).isEqualTo(expectedUnitSymbol);
    }

    @Test
    @DisplayName("should properly sort all quantities in the list")
    void shouldSortQuantitiesInTheList() {
        // Given
        Temperature[] temperatures = {
                Temperature.ofCelsius(54),
                Temperature.ofKelvins(10),
                Temperature.ofCelsius(-20),
                Temperature.ofCelsius(100)
        };

        // When
        Arrays.sort(temperatures);

        // Then
        Temperature[] correctOrderTemperatures = {
                Temperature.ofKelvins(10),
                Temperature.ofCelsius(-20),
                Temperature.ofCelsius(54),
                Temperature.ofCelsius(100)
        };

        assertThat(temperatures).isEqualTo(correctOrderTemperatures);

    }

    @Test
    @DisplayName("should output formatted string")
    void shouldOutputFormattedString() {
        // Given
        Temperature temperature = Temperature.ofCelsius(25.1);

        // When
        String simpleFormattedString = temperature.toFormattedString("t");
        String formattedString = temperature.toFormattedString("t", "da", "| ");

        // Then
        assertThat(simpleFormattedString).isEqualTo("t = 25.1°C");
        assertThat(formattedString).isEqualTo("t_da = 25.1°C | ");
    }

    @Test
    @DisplayName("create instance of class from symbol")
    void shouldCreateInstanceOfClassFromSymbol() {
        // Given
        double value = 20.1;
        // When
        Temperature actualTemperature = PhysicalQuantity.createParsingFromSymbol(Temperature.class, value, "f");

        // Then
        assertThat(actualTemperature).isEqualTo(Temperature.ofFahrenheit(value));
    }

    @Test
    @DisplayName("create instance of class from unit")
    void shouldCreateInstanceOfClassFromUnit() {
        // Given
        double value = 20.1;
        // When
        Temperature actualTemperature = PhysicalQuantity.createParsingFromUnit(Temperature.class, value, "fahrenheit");

        // Then
        assertThat(actualTemperature).isEqualTo(Temperature.ofFahrenheit(value));
    }

}