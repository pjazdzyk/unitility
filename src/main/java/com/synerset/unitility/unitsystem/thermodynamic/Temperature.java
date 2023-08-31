package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public class Temperature implements PhysicalQuantity<Temperature> {

    public static final Temperature PHYSICAL_MIN_LIMIT = Temperature.ofKelvins(0);
    private final double value;
    private final double baseValue;
    private final Unit<Temperature> unit;

    public Temperature(double value, Unit<Temperature> unit) {
        this.value = value;
        this.unit = unit;
        this.baseValue = unit.toValueInBaseUnit(value);
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public Unit<Temperature> getUnit() {
        return unit;
    }

    @Override
    public Temperature toBaseUnit() {
        double valueInKelvin = unit.toValueInBaseUnit(value);
        return Temperature.of(valueInKelvin, TemperatureUnits.KELVIN);
    }

    @Override
    public Temperature toUnit(Unit<Temperature> targetUnit) {
        double valueInKelvin = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKelvin);
        return Temperature.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Temperature createNewWithValue(double value) {
        return Temperature.of(value, unit);
    }

    // Convert to target unit
    public Temperature toKelvin(){
        return toUnit(TemperatureUnits.KELVIN);
    }

    public Temperature toCelsius(){
        return toUnit(TemperatureUnits.CELSIUS);
    }

    public Temperature toFahrenheit(){
        return toUnit(TemperatureUnits.FAHRENHEIT);
    }

    // Get value in target unit
    public double getInKelvins() {
        return getIn(TemperatureUnits.KELVIN);
    }

    public double getInCelsius() {
        return getIn(TemperatureUnits.CELSIUS);
    }

    public double getInFahrenheits() {
        return getIn(TemperatureUnits.FAHRENHEIT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "Temperature{" + value + " " + unit.getSymbol() + '}';
    }

    @Override
    public String toStringWithRelevantDigits(int relevantDigits) {
        String separator = unit == TemperatureUnits.CELSIUS ? "" : " ";
        return ValueFormatter.formatDoubleToRelevantDigits(value, relevantDigits) + separator + unit.getSymbol();
    }

    // Static factory methods
    public static Temperature of(double value, Unit<Temperature> unit) {
        return new Temperature(value, unit);
    }

    public static Temperature ofKelvins(double value) {
        return new Temperature(value, TemperatureUnits.KELVIN);
    }

    public static Temperature ofCelsius(double value) {
        return new Temperature(value, TemperatureUnits.CELSIUS);
    }

    public static Temperature ofFahrenheit(double value) {
        return new Temperature(value, TemperatureUnits.FAHRENHEIT);
    }
}
