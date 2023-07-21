package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public final class Temperature implements PhysicalQuantity<Temperature> {

    public static final Temperature PHYSICAL_MIN_LIMIT = Temperature.ofKelvins(0);
    private final double value;
    private final Unit<Temperature> unit;

    private Temperature(double value, Unit<Temperature> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<Temperature> getUnit() {
        return unit;
    }

    @Override
    public Temperature toBaseUnit() {
        double valueInKelvin = unit.toBaseUnit(value);
        return Temperature.of(valueInKelvin, TemperatureUnits.KELVIN);
    }

    @Override
    public Temperature toUnit(Unit<Temperature> targetUnit) {
        double valueInKelvin = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInKelvin);
        return Temperature.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public PhysicalQuantity<Temperature> createNewWithValue(double value) {
        return Temperature.of(value, unit);
    }

    // Custom value getters
    public double getValueOfCelsius() {
        if (unit == TemperatureUnits.CELSIUS) {
            return value;
        }
        return toUnit(TemperatureUnits.CELSIUS).getValue();
    }

    public double getValueOfKelvin() {
        if (unit == TemperatureUnits.KELVIN) {
            return value;
        }
        return toUnit(TemperatureUnits.KELVIN).getValue();
    }

    // Custom converter methods, for most popular units
    public Temperature toKelvin(){
        return toUnit(TemperatureUnits.KELVIN);
    }

    public Temperature toCelsius(){
        return toUnit(TemperatureUnits.CELSIUS);
    }

    public Temperature toFahrenheit(){
        return toUnit(TemperatureUnits.FAHRENHEIT);
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
        return "Temperature{" +
                "value=" + value +
                ", unit=" + unit.getSymbol() +
                '}';
    }

    @Override
    public String toStringWithRelevantDigits(int relevantDigits) {
        String separator = unit == TemperatureUnits.CELSIUS ? "" : " ";
        return ValueFormatter.formatDoubleToRelevantDigits(value, relevantDigits) + separator + unit.getSymbol();
    }

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
