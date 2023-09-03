package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class SpecificEnthalpy implements PhysicalQuantity<SpecificEnthalpyUnits> {
    private final double value;
    private final double baseValue;
    private final SpecificEnthalpyUnits unit;

    public SpecificEnthalpy(double value, SpecificEnthalpyUnits unit) {
        this.value = value;
        this.unit = unit;
        this.baseValue = unit.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static SpecificEnthalpy of(double value, SpecificEnthalpyUnits unit) {
        return new SpecificEnthalpy(value, unit);
    }

    public static SpecificEnthalpy ofJoulePerKiloGram(double value) {
        return new SpecificEnthalpy(value, SpecificEnthalpyUnits.JOULE_PER_KILOGRAM);
    }

    public static SpecificEnthalpy ofKiloJoulePerKiloGram(double value) {
        return new SpecificEnthalpy(value, SpecificEnthalpyUnits.KILOJOULE_PER_KILOGRAM);
    }

    public static SpecificEnthalpy ofBTUPerPound(double value) {
        return new SpecificEnthalpy(value, SpecificEnthalpyUnits.BTU_PER_POUND);
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
    public SpecificEnthalpyUnits getUnit() {
        return unit;
    }

    @Override
    public SpecificEnthalpy toBaseUnit() {
        double valueInJoulePerKilogram = unit.toValueInBaseUnit(value);
        return SpecificEnthalpy.of(valueInJoulePerKilogram, SpecificEnthalpyUnits.JOULE_PER_KILOGRAM);
    }

    @Override
    public SpecificEnthalpy toUnit(SpecificEnthalpyUnits targetUnit) {
        double valueInJoulePerKilogram = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInJoulePerKilogram);
        return SpecificEnthalpy.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SpecificEnthalpy createNewWithValue(double value) {
        return SpecificEnthalpy.of(value, unit);
    }

    // Convert to target unit
    public SpecificEnthalpy toJoulePerKiloGram() {
        return toUnit(SpecificEnthalpyUnits.JOULE_PER_KILOGRAM);
    }

    public SpecificEnthalpy toKiloJoulePerKiloGram() {
        return toUnit(SpecificEnthalpyUnits.KILOJOULE_PER_KILOGRAM);
    }

    public SpecificEnthalpy toBTUPerPound() {
        return toUnit(SpecificEnthalpyUnits.BTU_PER_POUND);
    }

    // Get value in target unit
    public double getInJoulesPerKiloGram() {
        return getIn(SpecificEnthalpyUnits.JOULE_PER_KILOGRAM);
    }

    public double getInKiloJoulesPerKiloGram() {
        return getIn(SpecificEnthalpyUnits.KILOJOULE_PER_KILOGRAM);
    }

    public double getInBTUsPerPound() {
        return getIn(SpecificEnthalpyUnits.BTU_PER_POUND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificEnthalpy inputQuantity = (SpecificEnthalpy) o;
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unit.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unit.getBaseUnit());
    }

    @Override
    public String toString() {
        return "SpecificEnthalpy{" + value + " " + unit.getSymbol() + '}';
    }
}