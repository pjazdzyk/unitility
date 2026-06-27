package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/**
 * Thermal conductance {@code G} in W/K: the strength of a lumped conductor in a thermal network,
 * where heat flow {@code Q = G·ΔT}. Distinct from {@link ThermalConductivity} (W/(m·K)).
 */
public class ThermalConductance implements CalculableQuantity<ThermalConductanceUnit, ThermalConductance> {

    public static final ThermalConductance PHYSICAL_MIN_LIMIT = ThermalConductance.ofWattsPerKelvin(0);

    private final double value;
    private final double baseValue;
    private final ThermalConductanceUnit unitType;

    public ThermalConductance(double value, ThermalConductanceUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = ThermalConductanceUnits.WATTS_PER_KELVIN;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static ThermalConductance of(double value, ThermalConductanceUnit unit) {
        return new ThermalConductance(value, unit);
    }

    public static ThermalConductance of(double value, String unitSymbol) {
        ThermalConductanceUnit resolvedUnit = ThermalConductanceUnits.fromSymbol(unitSymbol);
        return new ThermalConductance(value, resolvedUnit);
    }

    public static ThermalConductance ofWattsPerKelvin(double value) {
        return new ThermalConductance(value, ThermalConductanceUnits.WATTS_PER_KELVIN);
    }

    public static ThermalConductance ofKilowattsPerKelvin(double value) {
        return new ThermalConductance(value, ThermalConductanceUnits.KILOWATTS_PER_KELVIN);
    }

    public static ThermalConductance ofBTUPerHourFahrenheit(double value) {
        return new ThermalConductance(value, ThermalConductanceUnits.BTU_PER_HOUR_FAHRENHEIT);
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
    public ThermalConductanceUnit getUnit() {
        return unitType;
    }

    @Override
    public ThermalConductance toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public ThermalConductance toUnit(ThermalConductanceUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return ThermalConductance.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public ThermalConductance toUnit(String targetUnit) {
        ThermalConductanceUnit resolvedUnit = ThermalConductanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public ThermalConductance withValue(double value) {
        return ThermalConductance.of(value, unitType);
    }

    // Convert to target unit
    public ThermalConductance toWattsPerKelvin() {
        return toUnit(ThermalConductanceUnits.WATTS_PER_KELVIN);
    }

    public ThermalConductance toKilowattsPerKelvin() {
        return toUnit(ThermalConductanceUnits.KILOWATTS_PER_KELVIN);
    }

    public ThermalConductance toBTUPerHourFahrenheit() {
        return toUnit(ThermalConductanceUnits.BTU_PER_HOUR_FAHRENHEIT);
    }

    // Get value in target unit
    public double getInWattsPerKelvin() {
        return getInUnit(ThermalConductanceUnits.WATTS_PER_KELVIN);
    }

    public double getInKilowattsPerKelvin() {
        return getInUnit(ThermalConductanceUnits.KILOWATTS_PER_KELVIN);
    }

    public double getInBTUPerHourFahrenheit() {
        return getInUnit(ThermalConductanceUnits.BTU_PER_HOUR_FAHRENHEIT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThermalConductance other = (ThermalConductance) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "ThermalConductance{" + value + " " + unitType.getSymbol() + '}';
    }
}
