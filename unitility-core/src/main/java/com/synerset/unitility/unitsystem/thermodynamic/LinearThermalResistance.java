package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/**
 * Thermal resistance per unit length of a pipe, the temperature difference per unit heat flow per metre. Base unit m·K/W.
 */
public class LinearThermalResistance implements CalculableQuantity<LinearThermalResistanceUnit, LinearThermalResistance> {
    private final double value;
    private final double baseValue;
    private final LinearThermalResistanceUnit unitType;

    public LinearThermalResistance(double value, LinearThermalResistanceUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = LinearThermalResistanceUnits.METER_KELVIN_PER_WATT;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static LinearThermalResistance of(double value, LinearThermalResistanceUnit unit) {
        return new LinearThermalResistance(value, unit);
    }

    public static LinearThermalResistance of(double value, String unitSymbol) {
        LinearThermalResistanceUnit resolvedUnit = LinearThermalResistanceUnits.fromSymbol(unitSymbol);
        return new LinearThermalResistance(value, resolvedUnit);
    }

    public static LinearThermalResistance ofMeterKelvinPerWatt(double value) {
        return new LinearThermalResistance(value, LinearThermalResistanceUnits.METER_KELVIN_PER_WATT);
    }

    public static LinearThermalResistance ofMeterCelsiusPerWatt(double value) {
        return new LinearThermalResistance(value, LinearThermalResistanceUnits.METER_CELSIUS_PER_WATT);
    }

    public static LinearThermalResistance ofFootFahrenheitHourPerBTU(double value) {
        return new LinearThermalResistance(value, LinearThermalResistanceUnits.FOOT_FAHRENHEIT_HOUR_PER_BTU);
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
    public LinearThermalResistanceUnit getUnit() {
        return unitType;
    }

    @Override
    public LinearThermalResistance toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public LinearThermalResistance toUnit(LinearThermalResistanceUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return LinearThermalResistance.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public LinearThermalResistance toUnit(String targetUnit) {
        LinearThermalResistanceUnit resolvedUnit = LinearThermalResistanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public LinearThermalResistance withValue(double value) {
        return LinearThermalResistance.of(value, unitType);
    }

    // Convert to target unit
    public LinearThermalResistance toMeterKelvinPerWatt() {
        return toUnit(LinearThermalResistanceUnits.METER_KELVIN_PER_WATT);
    }

    public LinearThermalResistance toMeterCelsiusPerWatt() {
        return toUnit(LinearThermalResistanceUnits.METER_CELSIUS_PER_WATT);
    }

    public LinearThermalResistance toFootFahrenheitHourPerBTU() {
        return toUnit(LinearThermalResistanceUnits.FOOT_FAHRENHEIT_HOUR_PER_BTU);
    }

    // Get value in target unit
    public double getInMeterKelvinPerWatt() {
        return getInUnit(LinearThermalResistanceUnits.METER_KELVIN_PER_WATT);
    }

    public double getInMeterCelsiusPerWatt() {
        return getInUnit(LinearThermalResistanceUnits.METER_CELSIUS_PER_WATT);
    }

    public double getInFootFahrenheitHourPerBTU() {
        return getInUnit(LinearThermalResistanceUnits.FOOT_FAHRENHEIT_HOUR_PER_BTU);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinearThermalResistance other = (LinearThermalResistance) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "LinearThermalResistance{" + value + " " + unitType.getSymbol() + '}';
    }
}
