package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.thermodynamic.Pressure;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculableQuantityTest {

    // Basic math operations

    @Test
    @DisplayName("Add value: should correctly add value to quantity")
    void add_shouldAddValueToQuantity() {
        // Given
        Temperature temperature = Temperature.ofCelsius(20);

        // When
        Temperature actualTemperature = temperature.plus(20);

        // Then
        Temperature exptectedTemperature = Temperature.ofCelsius(40);
        assertThat(actualTemperature).isEqualTo(exptectedTemperature);
    }

    @Test
    @DisplayName("Add quantity: should correctly subtract value from quantity")
    void add_shouldSubtractValueToQuantity() {
        // Given
        Temperature temperature = Temperature.ofCelsius(20);

        // When
        Temperature actualTemperature = temperature.minus(20);

        // Then
        Temperature exptectedTemperature = Temperature.ofCelsius(0);
        assertThat(actualTemperature).isEqualTo(exptectedTemperature);
    }

    @Test
    @DisplayName("Subtract value: should correctly add quantities of the same type, but different units")
    void subtract_shouldAddQuantityToSourceQuantity() {
        // Given
        Temperature sourceTemperature = Temperature.ofCelsius(20);
        Temperature temperatureToAdd = Temperature.ofKelvins(20 + 273.15);

        // When
        Temperature actualTemperature = sourceTemperature.plus(temperatureToAdd);

        // Then
        Temperature exptectedTemperature = Temperature.ofCelsius(40);
        assertThat(actualTemperature).isEqualTo(exptectedTemperature);
    }

    @Test
    @DisplayName("Subtract quantity: should correctly subtract quantities of the same type, but different units")
    void subtract_shouldSubtractQuantityToSourceQuantity() {
        // Given
        Temperature sourceTemperature = Temperature.ofCelsius(20);
        Temperature temperatureToAdd = Temperature.ofKelvins(20 + 273.15);

        // When
        Temperature actualTemperature = sourceTemperature.minus(temperatureToAdd);

        // Then
        Temperature exptectedTemperature = Temperature.ofCelsius(0);
        assertThat(actualTemperature).isEqualTo(exptectedTemperature);
    }

    @Test
    @DisplayName("Multiply by value: should correctly multiply quantity by value")
    void multiply_shouldMultiplyValueToQuantity() {
        // Given
        Temperature temperature = Temperature.ofCelsius(20);

        // When
        Temperature actualTemperature = temperature.multiply(2);
        Temperature actualTemperatureTimes = temperature.times(2);

        // Then
        Temperature exptectedTemperature = Temperature.ofCelsius(40);
        assertThat(actualTemperature).isEqualTo(exptectedTemperature).isEqualTo(actualTemperatureTimes);
    }

    @Test
    @DisplayName("Multiply by quantity: should correctly multiply quantities of the same type, but different units")
    void multiply_shouldMultiplyQuantityToSourceQuantity() {
        // Given
        Temperature sourceTemperature = Temperature.ofCelsius(20);
        Pressure pressure = Pressure.ofPascal(2);

        // When
        double actualMultiplyResult = sourceTemperature.multiply(pressure);
        double actualMultiplyResultTimes = sourceTemperature.times(pressure);

        // Then
        double expectedMultiplyResult = 40;
        assertThat(actualMultiplyResult).isEqualTo(expectedMultiplyResult).isEqualTo(actualMultiplyResultTimes);
    }

    @Test
    @DisplayName("Divide by value: should correctly divide quantity by value")
    void divide_shouldDivideValueToQuantity() {
        // Given
        Temperature temperature = Temperature.ofCelsius(20);

        // When
        Temperature actualTemperature = temperature.div(2);

        // Then
        Temperature exptectedTemperature = Temperature.ofCelsius(10);
        assertThat(actualTemperature).isEqualTo(exptectedTemperature);
    }

    @Test
    @DisplayName("Divide by 0: should throw an exception if divided by 0")
    void divide_shouldNotDivideByZeroThrowingException() {
        // Given
        Temperature temperature = Temperature.ofCelsius(20);

        // Then
        assertThatThrownBy(() -> temperature.div(0))
                .isInstanceOf(UnitSystemArgumentException.class)
                .hasMessage("Division by zero is not allowed. Please provide a non-zero divider value.");
    }

    @Test
    @DisplayName("Divide by quantity: should correctly divide quantities of the same type, but different units")
    void divide_shouldDivideQuantityToSourceQuantity() {
        // Given
        Temperature sourceTemperature = Temperature.ofCelsius(20);
        Pressure pressure = Pressure.ofPascal(2);

        // When
        double actualDivideResult = sourceTemperature.div(pressure);

        // Then
        double expectedDivideResult = 10;
        assertThat(actualDivideResult).isEqualTo(expectedDivideResult);
    }

    @Test
    @DisplayName("Absolute value: should change wrapped value to absolute Math.abs(value)")
    void abs_shouldChangeValueToAbsolute() {
        // Given
        Temperature temperature = Temperature.ofCelsius(-20);

        // When
        Temperature acutalTemperature = temperature.abs();

        // Then
        assertThat(acutalTemperature.getValue()).isEqualTo(20);
    }

    @Test
    @DisplayName("Power to exponent: should correctly raise quantity to the power of the given exponent")
    void power_shouldRaiseQuantityToExponent() {
        // Given
        Temperature temperature = Temperature.ofCelsius(2);

        // When
        Temperature poweredTemperature = temperature.power(3);

        // Then
        Temperature expectedTemperature = Temperature.ofCelsius(8);
        assertThat(poweredTemperature).isEqualTo(expectedTemperature);
    }

    // Square Root

    @Test
    @DisplayName("Square root: should correctly calculate square root of the quantity's value")
    void sqrt_shouldCalculateSquareRoot() {
        // Given
        Temperature temperature = Temperature.ofCelsius(25);

        // When
        Temperature sqrtTemperature = temperature.sqrt();

        // Then
        Temperature expectedTemperature = Temperature.ofCelsius(5);
        assertThat(sqrtTemperature).isEqualTo(expectedTemperature);
    }

    // Logarithms

    @Test
    @DisplayName("Natural logarithm: should correctly calculate natural logarithm of the quantity's value")
    void log_shouldCalculateNaturalLogarithm() {
        // Given
        Temperature temperature = Temperature.ofCelsius(10);

        // When
        Temperature logTemperature = temperature.log();

        // Then
        Temperature expectedTemperature = Temperature.ofCelsius(Math.log(10));
        assertThat(logTemperature).isEqualTo(expectedTemperature);
    }

    @Test
    @DisplayName("Natural logarithm of negative value: should throw exception when calculating natural logarithm of non-positive value")
    void log_shouldThrowExceptionForNonPositiveValue() {
        // Given
        Temperature temperature = Temperature.ofCelsius(0);

        // Then
        assertThatThrownBy(temperature::log)
                .isInstanceOf(UnitSystemArgumentException.class)
                .hasMessageContaining("Cannot calculate logarithm for non-positive value");
    }

    @Test
    @DisplayName("Logarithm of 10 base: should correctly calculate base-10 logarithm of the quantity's value")
    void log10_shouldCalculateBase10Logarithm() {
        // Given
        Temperature temperature = Temperature.ofCelsius(1000);

        // When
        Temperature log10Temperature = temperature.log10();

        // Then
        Temperature expectedTemperature = Temperature.ofCelsius(3);
        assertThat(log10Temperature).isEqualTo(expectedTemperature);
    }

    @Test
    @DisplayName("Logarithm of 10 base of negative value: should throw exception when calculating base-10 logarithm of non-positive value")
    void log10_shouldThrowExceptionForNonPositiveValue() {
        // Given
        Temperature temperature = Temperature.ofCelsius(-10);

        // Then
        assertThatThrownBy(temperature::log10)
                .isInstanceOf(UnitSystemArgumentException.class);


    }

    // Ceiling, and rounding up

    @Test
    @DisplayName("Ceil: should correctly ceil up to the nearest integer")
    void ceil_shouldCeilToNearestInteger() {
        // Given
        Temperature temperature1 = Temperature.ofCelsius(10.123456);
        Temperature temperature2 = Temperature.ofCelsius(0.123456);
        Temperature temperature3 = Temperature.ofCelsius(-10.123456);

        // When
        Temperature ceilTemp1 = temperature1.ceil();
        Temperature ceilTemp2 = temperature2.ceil();
        Temperature ceilTemp3 = temperature3.ceil();

        // Then
        assertThat(ceilTemp1).isEqualTo(Temperature.ofCelsius(11));
        assertThat(ceilTemp2).isEqualTo(Temperature.ofCelsius(1));
        assertThat(ceilTemp3).isEqualTo(Temperature.ofCelsius(-10));
    }

    @Test
    @DisplayName("Floor: should correctly floor down to the nearest integer")
    void floor_shouldFloorToNearestInteger() {
        // Given
        Temperature temperature1 = Temperature.ofCelsius(10.123456);
        Temperature temperature2 = Temperature.ofCelsius(0.123456);
        Temperature temperature3 = Temperature.ofCelsius(-10.123456);

        // When
        Temperature floorTemp1 = temperature1.floor();
        Temperature floorTemp2 = temperature2.floor();
        Temperature floorTemp3 = temperature3.floor();

        // Then
        assertThat(floorTemp1).isEqualTo(Temperature.ofCelsius(10));
        assertThat(floorTemp2).isEqualTo(Temperature.ofCelsius(0));
        assertThat(floorTemp3).isEqualTo(Temperature.ofCelsius(-11));
    }

    @Test
    @DisplayName("Round half even: should correctly round up to half even for specified relevant digits")
    void roundHalfEven_shouldFloorToNearestInteger() {
        // Given
        Temperature temperature1 = Temperature.ofCelsius(10.123456);
        Temperature temperature2 = Temperature.ofCelsius(0.153456);
        Temperature temperature3 = Temperature.ofCelsius(-10.123456);

        // When
        Temperature roundTemp1 = temperature1.roundHalfEven(0);
        Temperature roundTemp2 = temperature2.roundHalfEven(1);
        Temperature roundTemp3 = temperature3.roundHalfEven(2);

        // Then
        assertThat(roundTemp1).isEqualTo(Temperature.ofCelsius(10.0));
        assertThat(roundTemp2).isEqualTo(Temperature.ofCelsius(0.2));
        assertThat(roundTemp3).isEqualTo(Temperature.ofCelsius(-10.12));
    }

}