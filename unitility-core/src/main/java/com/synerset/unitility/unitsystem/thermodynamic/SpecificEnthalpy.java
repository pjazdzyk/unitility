package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class SpecificEnthalpy implements CalculableQuantity<SpecificEnthalpyUnit, SpecificEnthalpy> {
    private final double value;
    private final double baseValue;
    private final SpecificEnthalpyUnit unitType;

    public SpecificEnthalpy(double value, SpecificEnthalpyUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = SpecificEnthalpyUnits.JOULE_PER_KILOGRAM;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static SpecificEnthalpy of(double value, SpecificEnthalpyUnit unit) {
        return new SpecificEnthalpy(value, unit);
    }

    public static SpecificEnthalpy of(double value, String unitSymbol) {
        SpecificEnthalpyUnit resolvedUnit = SpecificEnthalpyUnits.fromSymbol(unitSymbol);
        return new SpecificEnthalpy(value, resolvedUnit);
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
    public SpecificEnthalpyUnit getUnit() {
        return unitType;
    }

    @Override
    public SpecificEnthalpy toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public SpecificEnthalpy toUnit(SpecificEnthalpyUnit targetUnit) {
        double valueInJoulePerKilogram = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInJoulePerKilogram);
        return SpecificEnthalpy.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public SpecificEnthalpy toUnit(String targetUnit) {
        SpecificEnthalpyUnit resolvedUnit = SpecificEnthalpyUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public SpecificEnthalpy withValue(double value) {
        return SpecificEnthalpy.of(value, unitType);
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
        return getInUnit(SpecificEnthalpyUnits.JOULE_PER_KILOGRAM);
    }

    public double getInKiloJoulesPerKiloGram() {
        return getInUnit(SpecificEnthalpyUnits.KILOJOULE_PER_KILOGRAM);
    }

    public double getInBTUsPerPound() {
        return getInUnit(SpecificEnthalpyUnits.BTU_PER_POUND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificEnthalpy inputQuantity = (SpecificEnthalpy) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "SpecificEnthalpy{" + value + " " + unitType.getSymbol() + '}';
    }
}