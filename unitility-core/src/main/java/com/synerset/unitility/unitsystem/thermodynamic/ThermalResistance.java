package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/**
 * Area-specific thermal resistance (R-value), the temperature difference per unit heat flux. Base unit m²·K/W.
 */
public class ThermalResistance implements CalculableQuantity<ThermalResistanceUnit, ThermalResistance> {
    private final double value;
    private final double baseValue;
    private final ThermalResistanceUnit unitType;

    public ThermalResistance(double value, ThermalResistanceUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = ThermalResistanceUnits.SQUARE_METER_KELVIN_PER_WATT;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static ThermalResistance of(double value, ThermalResistanceUnit unit) {
        return new ThermalResistance(value, unit);
    }

    public static ThermalResistance of(double value, String unitSymbol) {
        ThermalResistanceUnit resolvedUnit = ThermalResistanceUnits.fromSymbol(unitSymbol);
        return new ThermalResistance(value, resolvedUnit);
    }

    public static ThermalResistance ofSquareMeterKelvinPerWatt(double value) {
        return new ThermalResistance(value, ThermalResistanceUnits.SQUARE_METER_KELVIN_PER_WATT);
    }

    public static ThermalResistance ofSquareMeterCelsiusPerWatt(double value) {
        return new ThermalResistance(value, ThermalResistanceUnits.SQUARE_METER_CELSIUS_PER_WATT);
    }

    public static ThermalResistance ofSquareMeterKelvinPerKilowatt(double value) {
        return new ThermalResistance(value, ThermalResistanceUnits.SQUARE_METER_KELVIN_PER_KILOWATT);
    }

    public static ThermalResistance ofSquareFootFahrenheitHourPerBTU(double value) {
        return new ThermalResistance(value, ThermalResistanceUnits.SQUARE_FOOT_FAHRENHEIT_HOUR_PER_BTU);
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
    public ThermalResistanceUnit getUnit() {
        return unitType;
    }

    @Override
    public ThermalResistance toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public ThermalResistance toUnit(ThermalResistanceUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return ThermalResistance.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public ThermalResistance toUnit(String targetUnit) {
        ThermalResistanceUnit resolvedUnit = ThermalResistanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public ThermalResistance withValue(double value) {
        return ThermalResistance.of(value, unitType);
    }

    // Convert to target unit
    public ThermalResistance toSquareMeterKelvinPerWatt() {
        return toUnit(ThermalResistanceUnits.SQUARE_METER_KELVIN_PER_WATT);
    }

    public ThermalResistance toSquareMeterCelsiusPerWatt() {
        return toUnit(ThermalResistanceUnits.SQUARE_METER_CELSIUS_PER_WATT);
    }

    public ThermalResistance toSquareMeterKelvinPerKilowatt() {
        return toUnit(ThermalResistanceUnits.SQUARE_METER_KELVIN_PER_KILOWATT);
    }

    public ThermalResistance toSquareFootFahrenheitHourPerBTU() {
        return toUnit(ThermalResistanceUnits.SQUARE_FOOT_FAHRENHEIT_HOUR_PER_BTU);
    }

    // Get value in target unit
    public double getInSquareMeterKelvinPerWatt() {
        return getInUnit(ThermalResistanceUnits.SQUARE_METER_KELVIN_PER_WATT);
    }

    public double getInSquareMeterCelsiusPerWatt() {
        return getInUnit(ThermalResistanceUnits.SQUARE_METER_CELSIUS_PER_WATT);
    }

    public double getInSquareMeterKelvinPerKilowatt() {
        return getInUnit(ThermalResistanceUnits.SQUARE_METER_KELVIN_PER_KILOWATT);
    }

    public double getInSquareFootFahrenheitHourPerBTU() {
        return getInUnit(ThermalResistanceUnits.SQUARE_FOOT_FAHRENHEIT_HOUR_PER_BTU);
    }

    /**
     * The reciprocal of this resistance: the heat transfer coefficient (U-value) in W/(m²·K).
     *
     * @return heat transfer coefficient, zero resistance gives infinity
     */
    public HeatTransferCoefficient toHeatTransferCoefficient() {
        return HeatTransferCoefficient.ofWattsPerSquareMeterKelvin(1.0 / baseValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThermalResistance other = (ThermalResistance) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "ThermalResistance{" + value + " " + unitType.getSymbol() + '}';
    }
}
