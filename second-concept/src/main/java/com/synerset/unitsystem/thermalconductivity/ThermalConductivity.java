package com.synerset.unitsystem.thermalconductivity;

import com.synerset.unitsystem.PhysicalQuantity;
import com.synerset.unitsystem.Unit;

import java.util.Objects;

public final class ThermalConductivity implements PhysicalQuantity<ThermalConductivity> {

    public static final byte TO_STRING_PRECISION = 3;
    private final double value;
    private final Unit<ThermalConductivity> unit;

    private ThermalConductivity(double value, Unit<ThermalConductivity> unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit<ThermalConductivity> getUnit() {
        return unit;
    }

    @Override
    public ThermalConductivity toBaseUnit() {
        double valueInWattsPerMeterKelvin = unit.toBaseUnit(value);
        return ThermalConductivity.of(valueInWattsPerMeterKelvin, ThermalConductivityUnits.WATTS_PER_METER_KELVIN);
    }

    @Override
    public ThermalConductivity toUnit(Unit<ThermalConductivity> targetUnit) {
        double valueInWattsPerMeterKelvin = unit.toBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromBaseToThisUnit(valueInWattsPerMeterKelvin);
        return ThermalConductivity.of(valueInTargetUnit, targetUnit);
    }

    // Custom converter methods, for most popular units
    public ThermalConductivity toWattsPerMeterKelvin() {
        return toUnit(ThermalConductivityUnits.WATTS_PER_METER_KELVIN);
    }

    public ThermalConductivity toKilowattsPerMeterKelvin() {
        return toUnit(ThermalConductivityUnits.KILOWATTS_PER_METER_KELVIN);
    }

    public ThermalConductivity toBTUPerHourFeetFahrenheit() {
        return toUnit(ThermalConductivityUnits.BTU_PER_HOUR_FOOT_FAHRENHEIT);
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
        return String.format("%." + TO_STRING_PRECISION + "f %s", value, unit.getSymbol());
    }

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