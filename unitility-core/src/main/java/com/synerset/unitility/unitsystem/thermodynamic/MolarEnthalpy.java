package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class MolarEnthalpy implements CalculableQuantity<MolarEnthalpyUnit, MolarEnthalpy> {
    private final double value;
    private final double baseValue;
    private final MolarEnthalpyUnit unitType;

    public MolarEnthalpy(double value, MolarEnthalpyUnit unitType) {
        this.value = value;
        if (unitType == null) { unitType = MolarEnthalpyUnits.JOULE_PER_MOLE; }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static MolarEnthalpy of(double value, MolarEnthalpyUnit unit) {
        return new MolarEnthalpy(value, unit);
    }

    public static MolarEnthalpy of(double value, String unitSymbol) {
        return new MolarEnthalpy(value, MolarEnthalpyUnits.fromSymbol(unitSymbol));
    }

    public static MolarEnthalpy ofJoulesPerMole(double value) {
        return new MolarEnthalpy(value, MolarEnthalpyUnits.JOULE_PER_MOLE);
    }

    public static MolarEnthalpy ofKilojoulesPerMole(double value) {
        return new MolarEnthalpy(value, MolarEnthalpyUnits.KILOJOULE_PER_MOLE);
    }

    public static MolarEnthalpy ofBTUPerPoundMole(double value) {
        return new MolarEnthalpy(value, MolarEnthalpyUnits.BTU_PER_POUND_MOLE);
    }

    // Implement CalculableQuantity interface methods
    @Override public double getValue() { return value; }
    @Override public double getBaseValue() { return baseValue; }
    @Override public MolarEnthalpyUnit getUnit() { return unitType; }
    
    @Override public MolarEnthalpy toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }
    
    @Override public MolarEnthalpy toUnit(MolarEnthalpyUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }
    
    @Override public MolarEnthalpy toUnit(String targetUnit) {
        return toUnit(MolarEnthalpyUnits.fromSymbol(targetUnit));
    }
    
    @Override public MolarEnthalpy withValue(double value) {
        return of(value, unitType);
    }

    // Conversion methods
    public MolarEnthalpy toJoulePerMole() { return toUnit(MolarEnthalpyUnits.JOULE_PER_MOLE); }
    public MolarEnthalpy toKilojoulePerMole() { return toUnit(MolarEnthalpyUnits.KILOJOULE_PER_MOLE); }
    public MolarEnthalpy toMegajoulePerKilomole() { return toUnit(MolarEnthalpyUnits.MEGAJOULE_PER_KILOMOLE); }
    public MolarEnthalpy toBTUPerPoundMole() { return toUnit(MolarEnthalpyUnits.BTU_PER_POUND_MOLE); }

    // Value getter methods
    public double getInJoulesPerMole() { return getInUnit(MolarEnthalpyUnits.JOULE_PER_MOLE); }
    public double getInKilojoulesPerMole() { return getInUnit(MolarEnthalpyUnits.KILOJOULE_PER_MOLE); }
    public double getInMegajoulesPerKilomole() { return getInUnit(MolarEnthalpyUnits.MEGAJOULE_PER_KILOMOLE); }
    public double getInBTUsPerPoundMole() { return getInUnit(MolarEnthalpyUnits.BTU_PER_POUND_MOLE); }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MolarEnthalpy)) return false;
        MolarEnthalpy other = (MolarEnthalpy) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override public int hashCode() { return Objects.hash(baseValue, unitType.getBaseUnit()); }

    @Override public String toString() {
        return "MolarEnthalpy{" + value + " " + unitType.getSymbol() + "}";
    }
}