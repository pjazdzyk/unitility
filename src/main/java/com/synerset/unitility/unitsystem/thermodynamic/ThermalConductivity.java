package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.util.Objects;

public final class ThermalConductivity implements PhysicalQuantity<ThermalConductivity> {
    private final double value;
    private final double baseValue;
    private final Unit<ThermalConductivity> unit;

    private ThermalConductivity(double value, Unit<ThermalConductivity> unit) {
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
    public Unit<ThermalConductivity> getUnit() {
        return unit;
    }

    @Override
    public ThermalConductivity toBaseUnit() {
        double valueInWattsPerMeterKelvin = unit.toValueInBaseUnit(value);
        return ThermalConductivity.of(valueInWattsPerMeterKelvin, ThermalConductivityUnits.WATTS_PER_METER_KELVIN);
    }

    @Override
    public ThermalConductivity toUnit(Unit<ThermalConductivity> targetUnit) {
        double valueInWattsPerMeterKelvin = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInWattsPerMeterKelvin);
        return ThermalConductivity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public ThermalConductivity createNewWithValue(double value) {
        return ThermalConductivity.of(value, unit);
    }

    // Convert to target unit
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
        ThermalConductivity that = (ThermalConductivity) o;
        return Double.compare(that.value, value) == 0 && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "ThermalConductivity{" + value + " " + unit.getSymbol() + '}';
    }

    // Static factory methods
    public static ThermalConductivity of(double value, Unit<ThermalConductivity> unit) {
        return new ThermalConductivity(value, unit);
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
}