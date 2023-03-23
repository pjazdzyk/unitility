package com.synerset.unitsystem.temperature;

import com.synerset.unitsystem.PhysicalQuantity;
import com.synerset.unitsystem.Unit;

import java.util.Objects;

public final class Temperature implements PhysicalQuantity<Temperature> {

    public static final byte TO_STRING_PRECISION = 3;
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
        return String.format("%." + TO_STRING_PRECISION + "f %s", value, unit.getSymbol());
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

}