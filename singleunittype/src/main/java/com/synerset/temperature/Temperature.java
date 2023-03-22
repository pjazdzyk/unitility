package com.synerset.temperature;

import com.synerset.PhysicalQuantity;
import com.synerset.Unit;

import java.util.Objects;

public final class Temperature implements PhysicalQuantity<Temperature> {

    public static final int DECIMAL_PRECISION = 3;
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
        return String.format("%%.%df %s", DECIMAL_PRECISION, unit.getSymbol());
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
