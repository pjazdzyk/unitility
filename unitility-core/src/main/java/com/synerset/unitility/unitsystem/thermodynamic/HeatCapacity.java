package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/**
 * Heat capacity {@code C} in J/K: the thermal mass of a lumped node, where {@code Q = C·dT/dt}.
 * Distinct from {@link SpecificHeat} (J/(kg·K)).
 */
public class HeatCapacity implements CalculableQuantity<HeatCapacityUnit, HeatCapacity> {

    public static final HeatCapacity PHYSICAL_MIN_LIMIT = HeatCapacity.ofJoulesPerKelvin(0);

    private final double value;
    private final double baseValue;
    private final HeatCapacityUnit unitType;

    public HeatCapacity(double value, HeatCapacityUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = HeatCapacityUnits.JOULES_PER_KELVIN;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static HeatCapacity of(double value, HeatCapacityUnit unit) {
        return new HeatCapacity(value, unit);
    }

    public static HeatCapacity of(double value, String unitSymbol) {
        HeatCapacityUnit resolvedUnit = HeatCapacityUnits.fromSymbol(unitSymbol);
        return new HeatCapacity(value, resolvedUnit);
    }

    public static HeatCapacity ofJoulesPerKelvin(double value) {
        return new HeatCapacity(value, HeatCapacityUnits.JOULES_PER_KELVIN);
    }

    public static HeatCapacity ofKilojoulesPerKelvin(double value) {
        return new HeatCapacity(value, HeatCapacityUnits.KILOJOULES_PER_KELVIN);
    }

    public static HeatCapacity ofBTUPerFahrenheit(double value) {
        return new HeatCapacity(value, HeatCapacityUnits.BTU_PER_FAHRENHEIT);
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
    public HeatCapacityUnit getUnit() {
        return unitType;
    }

    @Override
    public HeatCapacity toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public HeatCapacity toUnit(HeatCapacityUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return HeatCapacity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public HeatCapacity toUnit(String targetUnit) {
        HeatCapacityUnit resolvedUnit = HeatCapacityUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public HeatCapacity withValue(double value) {
        return HeatCapacity.of(value, unitType);
    }

    // Convert to target unit
    public HeatCapacity toJoulesPerKelvin() {
        return toUnit(HeatCapacityUnits.JOULES_PER_KELVIN);
    }

    public HeatCapacity toKilojoulesPerKelvin() {
        return toUnit(HeatCapacityUnits.KILOJOULES_PER_KELVIN);
    }

    public HeatCapacity toBTUPerFahrenheit() {
        return toUnit(HeatCapacityUnits.BTU_PER_FAHRENHEIT);
    }

    // Get value in target unit
    public double getInJoulesPerKelvin() {
        return getInUnit(HeatCapacityUnits.JOULES_PER_KELVIN);
    }

    public double getInKilojoulesPerKelvin() {
        return getInUnit(HeatCapacityUnits.KILOJOULES_PER_KELVIN);
    }

    public double getInBTUPerFahrenheit() {
        return getInUnit(HeatCapacityUnits.BTU_PER_FAHRENHEIT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeatCapacity other = (HeatCapacity) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "HeatCapacity{" + value + " " + unitType.getSymbol() + '}';
    }
}
