package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Temperature implements CalculableQuantity<TemperatureUnit, Temperature> {

    public static final Temperature PHYSICAL_MIN_LIMIT = Temperature.ofKelvins(0);
    private final double value;
    private final double baseValue;
    private final TemperatureUnit unitType;

    public Temperature(double value, TemperatureUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = TemperatureUnits.getDefaultUnit();
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Temperature of(double value, TemperatureUnit unit) {
        return new Temperature(value, unit);
    }

    public static Temperature of(double value, String unitSymbol) {
        TemperatureUnit resolvedUnit = TemperatureUnits.fromSymbol(unitSymbol);
        return new Temperature(value, resolvedUnit);
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

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public TemperatureUnit getUnitType() {
        return unitType;
    }

    @Override
    public Temperature toBaseUnit() {
        double valueInKelvin = unitType.toValueInBaseUnit(value);
        return of(valueInKelvin, TemperatureUnits.KELVIN);
    }

    @Override
    public Temperature toUnit(TemperatureUnit targetUnit) {
        double valueInKelvin = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKelvin);
        return Temperature.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Temperature toUnit(String targetUnit) {
        TemperatureUnit resolvedUnit = TemperatureUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Temperature withValue(double value) {
        return Temperature.of(value, unitType);
    }

    // Convert to target unit
    public Temperature toKelvin() {
        return toUnit(TemperatureUnits.KELVIN);
    }

    public Temperature toCelsius() {
        return toUnit(TemperatureUnits.CELSIUS);
    }

    public Temperature toFahrenheit() {
        return toUnit(TemperatureUnits.FAHRENHEIT);
    }

    // Get value in target unit
    public double getInKelvins() {
        return getInUnit(TemperatureUnits.KELVIN);
    }

    public double getInCelsius() {
        return getInUnit(TemperatureUnits.CELSIUS);
    }

    public double getInFahrenheits() {
        return getInUnit(TemperatureUnits.FAHRENHEIT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature inputQuantity = (Temperature) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(),
                inputQuantity.getUnitType().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        String separator = getUnitType().getSymbol().contains("°") ? "" : " ";
        return "Temperature{" + value + separator + unitType.getSymbol() + '}';
    }

}