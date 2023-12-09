package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class ThermalConductivity implements PhysicalQuantity<ThermalConductivityUnit> {
    private final double value;
    private final double baseValue;
    private final ThermalConductivityUnit unitType;

    public ThermalConductivity(double value, ThermalConductivityUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static ThermalConductivity of(double value, ThermalConductivityUnit unit) {
        return new ThermalConductivity(value, unit);
    }

    public static ThermalConductivity of(double value, String unitSymbol) {
        ThermalConductivityUnit resolvedUnit = ThermalConductivityUnits.fromSymbol(unitSymbol);
        return new ThermalConductivity(value, resolvedUnit);
    }
    
    public static ThermalConductivity ofWattsPerMeterKelvin(double value) {
        return new ThermalConductivity(value, ThermalConductivityUnits.WATTS_PER_METER_KELVIN);
    }

    public static ThermalConductivity ofKilowattsPerMeterKelvin(double value) {
        return new ThermalConductivity(value, ThermalConductivityUnits.KILOWATTS_PER_METER_KELVIN);
    }

    public static ThermalConductivity ofBTUPerHourFeetFahrenheit(double value) {
        return new ThermalConductivity(value, ThermalConductivityUnits.BTU_PER_HOUR_FOOT_FAHRENHEIT);
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
    public ThermalConductivityUnit getUnitType() {
        return unitType;
    }

    @Override
    public ThermalConductivity toBaseUnit() {
        double valueInWattsPerMeterKelvin = unitType.toValueInBaseUnit(value);
        return ThermalConductivity.of(valueInWattsPerMeterKelvin, ThermalConductivityUnits.WATTS_PER_METER_KELVIN);
    }

    @Override
    public ThermalConductivity toUnit(ThermalConductivityUnit targetUnit) {
        double valueInWattsPerMeterKelvin = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInWattsPerMeterKelvin);
        return ThermalConductivity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ThermalConductivity withValue(double value) {
        return ThermalConductivity.of(value, unitType);
    }

    // Convert to target unit
    public ThermalConductivity toWattsPerMeterKelvin() {
        return toUnit(ThermalConductivityUnits.WATTS_PER_METER_KELVIN);
    }

    public ThermalConductivity toKilowattsPerMeterKelvin() {
        return toUnit(ThermalConductivityUnits.KILOWATTS_PER_METER_KELVIN);
    }

    public ThermalConductivity toBTUPerHourFeetFahrenheit() {
        return toUnit(ThermalConductivityUnits.BTU_PER_HOUR_FOOT_FAHRENHEIT);
    }

    // Get value in target unit
    public double getInWattsPerMeterKelvin() {
        return getIn(ThermalConductivityUnits.WATTS_PER_METER_KELVIN);
    }

    public double getInKilowattsPerMeterKelvin() {
        return getIn(ThermalConductivityUnits.KILOWATTS_PER_METER_KELVIN);
    }

    public double getInBTUsPerHourFeetFahrenheit() {
        return getIn(ThermalConductivityUnits.BTU_PER_HOUR_FOOT_FAHRENHEIT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThermalConductivity inputQuantity = (ThermalConductivity) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "ThermalConductivity{" + value + " " + unitType.getSymbol() + '}';
    }

}