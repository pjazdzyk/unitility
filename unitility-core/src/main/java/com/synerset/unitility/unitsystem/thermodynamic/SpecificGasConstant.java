package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class SpecificGasConstant implements CalculableQuantity<SpecificGasConstantUnit, SpecificGasConstant> {
    private final double value;
    private final double baseValue;
    private final SpecificGasConstantUnit unitType;

    public SpecificGasConstant(double value, SpecificGasConstantUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = SpecificGasConstantUnits.JOULE_PER_KILOGRAM_KELVIN;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static SpecificGasConstant of(double value, SpecificGasConstantUnit unit) {
        return new SpecificGasConstant(value, unit);
    }

    public static SpecificGasConstant of(double value, String unitSymbol) {
        SpecificGasConstantUnit resolvedUnit = SpecificGasConstantUnits.fromSymbol(unitSymbol);
        return new SpecificGasConstant(value, resolvedUnit);
    }

    public static SpecificGasConstant ofJoulesPerKilogramKelvin(double value) {
        return new SpecificGasConstant(value, SpecificGasConstantUnits.JOULE_PER_KILOGRAM_KELVIN);
    }

    public static SpecificGasConstant ofKilojoulesPerKilogramKelvin(double value) {
        return new SpecificGasConstant(value, SpecificGasConstantUnits.KILOJOULE_PER_KILOGRAM_KELVIN);
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
    public SpecificGasConstantUnit getUnit() {
        return unitType;
    }

    @Override
    public SpecificGasConstant toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public SpecificGasConstant toUnit(SpecificGasConstantUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return SpecificGasConstant.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public SpecificGasConstant toUnit(String targetUnit) {
        SpecificGasConstantUnit resolvedUnit = SpecificGasConstantUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public SpecificGasConstant withValue(double value) {
        return SpecificGasConstant.of(value, unitType);
    }

    // Convert to target unit
    public SpecificGasConstant toJoulesPerKilogramKelvin() {
        return toUnit(SpecificGasConstantUnits.JOULE_PER_KILOGRAM_KELVIN);
    }

    public SpecificGasConstant toKilojoulesPerKilogramKelvin() {
        return toUnit(SpecificGasConstantUnits.KILOJOULE_PER_KILOGRAM_KELVIN);
    }

    // Get value in target unit
    public double getInJoulesPerKilogramKelvin() {
        return getInUnit(SpecificGasConstantUnits.JOULE_PER_KILOGRAM_KELVIN);
    }

    public double getInKilojoulesPerKilogramKelvin() {
        return getInUnit(SpecificGasConstantUnits.KILOJOULE_PER_KILOGRAM_KELVIN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificGasConstant other = (SpecificGasConstant) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "SpecificGasConstant{" + value + " " + unitType.getSymbol() + '}';
    }
}
