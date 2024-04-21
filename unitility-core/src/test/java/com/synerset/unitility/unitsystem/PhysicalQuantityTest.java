package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.common.Distance;
import com.synerset.unitility.unitsystem.common.DistanceUnits;
import com.synerset.unitility.unitsystem.dimensionless.BypassFactor;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.flow.VolumetricFlow;
import com.synerset.unitility.unitsystem.flow.VolumetricFlowUnit;
import com.synerset.unitility.unitsystem.thermodynamic.Pressure;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.thermodynamic.ThermalDiffusivity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PhysicalQuantityTest {

    // Boolean operations
    @Test
    @DisplayName("should be equals if base units and values in base units are the same")
    void equals_shouldBeEquals_whenBaseUnitsAndValuesAreTheSame() {
        // Given
        // When
        Distance distanceInMeters = Distance.ofMeters(100);
        Distance distanceInMiles = distanceInMeters.toUnit(DistanceUnits.MILE);

        // Then
        assertThat(distanceInMeters.isEqualWithPrecision(distanceInMiles, 1E-13)).isTrue();
    }

    @Test
    @DisplayName("should assert that first quantity is greater than second")
    void isGreaterThan_isLowerThan_shouldAssertThatFirstQuantityIsGreaterThanSecond() {
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
    @DisplayName("should correctly indicate if value is positive, negative or zero")
    void IsPositive_isNegative_shouldIndicateIfValueIsPositiveNegativeOrZero() {
        // Given
        Temperature negativeTemp = Temperature.ofCelsius(-20);
        Temperature zeroTemp = Temperature.ofCelsius(0);
        Temperature positiveTemp = Temperature.ofCelsius(20);

        // When

        // Then
        assertThat(negativeTemp.isNegative()).isTrue();
        assertThat(negativeTemp.isNegativeOrZero()).isTrue();
        assertThat(negativeTemp.isEqualZero()).isFalse();
        assertThat(negativeTemp.isPositive()).isFalse();
        assertThat(negativeTemp.isPositiveOrZero()).isFalse();

        assertThat(zeroTemp.isNegative()).isFalse();
        assertThat(zeroTemp.isNegativeOrZero()).isTrue();
        assertThat(zeroTemp.isEqualZero()).isTrue();
        assertThat(zeroTemp.isPositive()).isFalse();
        assertThat(zeroTemp.isPositiveOrZero()).isTrue();

        assertThat(positiveTemp.isNegative()).isFalse();
        assertThat(positiveTemp.isNegativeOrZero()).isFalse();
        assertThat(positiveTemp.isEqualZero()).isFalse();
        assertThat(positiveTemp.isPositive()).isTrue();
        assertThat(positiveTemp.isPositiveOrZero()).isTrue();

    }

    @Test
    @DisplayName("should return true if value is closed to zero and false if it is not")
    void isCloseToZero_shouldEvaluateIfCloseToZero(){
        // Given
        Temperature temperature = Temperature.ofCelsius(1E-13);
        Pressure pressure = Pressure.ofPascal(0.001);

        // When
        boolean actualResult = temperature.isCloseToZero();
        boolean closeToZeroTrue = pressure.isCloseToZero(0.1);
        boolean closeToZeroFalse = pressure.isCloseToZero(0.0001);

        // Then
        assertThat(actualResult).isTrue();
        assertThat(closeToZeroTrue).isTrue();
        assertThat(closeToZeroFalse).isFalse();
    }

    // Transformations

    @Test
    @DisplayName("should correctly add value to quantity")
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
    @DisplayName("should correctly subtract value from quantity")
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
    @DisplayName("should correctly add quantities of the same type, but different units")
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
    @DisplayName("should correctly subtract quantities of the same type, but different units")
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
    @DisplayName("should correctly multiply quantity by value")
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
    @DisplayName("should correctly multiply quantities of the same type, but different units")
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
    @DisplayName("should correctly divide quantity by value")
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
    @DisplayName("should throw an exception if divided by 0")
    void divide_shouldNotDivideByZeroThrowingException() {
        // Given
        Temperature temperature = Temperature.ofCelsius(20);

        // Then
        assertThatThrownBy(() -> temperature.div(0))
                .isInstanceOf(UnitSystemArgumentException.class)
                .hasMessage("Division by zero is not allowed. Please provide a non-zero divider value.");
    }

    @Test
    @DisplayName("should correctly divide quantities of the same type, but different units")
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
    @DisplayName("should subtract value of current unit from number")
    void subtract_shouldSubtractFromValue() {
        // Given
        Temperature sourceTemperature = Temperature.ofCelsius(0.5);

        // When
        Temperature actualTemperature = sourceTemperature.minusFromValue(1);

        // Then
        Temperature expectedTemperature = Temperature.ofCelsius((1 - 0.5));
        assertThat(actualTemperature).isEqualTo(expectedTemperature);
    }

    @Test
    @DisplayName("should change wrapped value to absolute abs(value)")
    void abs_shouldChangeValueToAbsolute(){
        // Given
        Temperature temperature = Temperature.ofCelsius(-20);

        // When
        Temperature acutalTemperature = temperature.abs();

        // Then
        assertThat(acutalTemperature.getValue()).isEqualTo(20);
    }

    // Others

    @Test
    @DisplayName("should return property unit symbol")
    void getUnitSymbol_shouldReturnPropertyUnitSymbol() {
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
    void sort_shouldSortQuantitiesInTheList() {
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
    @DisplayName("should return canonical string")
    void toEngineeringFormat_shouldReturnEngineeringString() {
        // Given
        Temperature temperature = Temperature.ofCelsius(20.1234567);
        BypassFactor bypassFactor = BypassFactor.of(20.1234567);

        // When
        String actualFormattedTemp = temperature.toEngineeringFormat();
        String actualFormattedBf = bypassFactor.toEngineeringFormat();

        String actualFormattedTempTwoDigits = temperature.toEngineeringFormat(2);
        String actualFormattedTempVariable = temperature.toEngineeringFormat("t_a");
        String actualFormattedTempVariableDigits = temperature.toEngineeringFormat("t_a", 2);

        // Then
        assertThat(actualFormattedTemp).isEqualTo("20.1234567 [°C]");
        assertThat(actualFormattedBf).isEqualTo("20.1234567");
        assertThat(actualFormattedTempTwoDigits).isEqualTo("20.12 [°C]");
        assertThat(actualFormattedTempVariable).isEqualTo("t_a = 20.1234567 [°C]");
        assertThat(actualFormattedTempVariableDigits).isEqualTo("t_a = 20.12 [°C]");
    }

    @Test
    @DisplayName("Should change from quantity sent as argument and convert value to that unit")
    void toUnitFrom_shouldUseUnitFromFromOtherQuantity(){
        // Given
        VolumetricFlow volFlowM3h = VolumetricFlow.ofCubicMetersPerHour(3600);
        VolumetricFlow volFlowM3s = VolumetricFlow.ofCubicMetersPerSecond(2);

        // When
        VolumetricFlow actualVolFlow = volFlowM3s.toUnitFrom(volFlowM3h);
        VolumetricFlowUnit actualUnit = actualVolFlow.getUnitType();

        // Then
        assertThat(actualUnit).isEqualTo(volFlowM3h.getUnitType());
        assertThat(actualVolFlow.getValue()).isEqualTo(7200);
    }

}