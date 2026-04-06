package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class MolarMass implements CalculableQuantity<MolarMassUnit, MolarMass> {
    private final double value;
    private final double baseValue;
    private final MolarMassUnit unitType;

    public MolarMass(double value, MolarMassUnit unitType) {
        this.value = value;
        if (unitType == null) { unitType = MolarMassUnits.KILOGRAM_PER_MOLE; }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static MolarMass of(double value, MolarMassUnit unit) {
        return new MolarMass(value, unit);
    }

    public static MolarMass of(double value, String unitSymbol) {
        return new MolarMass(value, MolarMassUnits.fromSymbol(unitSymbol));
    }

    // Unit-specific factory methods
    public static MolarMass ofGramPerMole(double value) {
        return new MolarMass(value, MolarMassUnits.GRAM_PER_MOLE);
    }

    public static MolarMass ofKilogramPerKilomole(double value) {
        return new MolarMass(value, MolarMassUnits.KILOGRAM_PER_KILOMOLE);
    }

    public static MolarMass ofKilogramPerMole(double value) {
        return new MolarMass(value, MolarMassUnits.KILOGRAM_PER_MOLE);
    }

    public static MolarMass ofMilligramPerMillimole(double value) {
        return new MolarMass(value, MolarMassUnits.MILLIGRAM_PER_MILLIMOLE);
    }

    public static MolarMass ofPoundPerPoundMole(double value) {
        return new MolarMass(value, MolarMassUnits.POUND_PER_POUND_MOLE);
    }

    public static MolarMass ofOuncePerMole(double value) {
        return new MolarMass(value, MolarMassUnits.OUNCE_PER_MOLE);
    }

    // Implement CalculableQuantity interface methods
    @Override
    public double getValue() { return value; }

    @Override
    public double getBaseValue() { return baseValue; }

    @Override
    public MolarMassUnit getUnit() { return unitType; }

    @Override
    public MolarMass toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }

    @Override
    public MolarMass toUnit(MolarMassUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }

    @Override
    public MolarMass toUnit(String targetUnit) {
        return toUnit(MolarMassUnits.fromSymbol(targetUnit));
    }

    @Override
    public MolarMass withValue(double value) {
        return of(value, unitType);
    }

    // Conversion methods
    public MolarMass toGramPerMole() {
        return toUnit(MolarMassUnits.GRAM_PER_MOLE);
    }

    public MolarMass toKilogramPerKilomole() {
        return toUnit(MolarMassUnits.KILOGRAM_PER_KILOMOLE);
    }

    public MolarMass toKilogramPerMole() {
        return toUnit(MolarMassUnits.KILOGRAM_PER_MOLE);
    }

    public MolarMass toMilligramPerMillimole() {
        return toUnit(MolarMassUnits.MILLIGRAM_PER_MILLIMOLE);
    }

    public MolarMass toPoundPerPoundMole() {
        return toUnit(MolarMassUnits.POUND_PER_POUND_MOLE);
    }

    public MolarMass toOuncePerMole() {
        return toUnit(MolarMassUnits.OUNCE_PER_MOLE);
    }

    // Get value in target unit
    public double getInGramPerMole() {
        return getInUnit(MolarMassUnits.GRAM_PER_MOLE);
    }

    public double getInKilogramPerKilomole() {
        return getInUnit(MolarMassUnits.KILOGRAM_PER_KILOMOLE);
    }

    public double getInKilogramPerMole() {
        return getInUnit(MolarMassUnits.KILOGRAM_PER_MOLE);
    }

    public double getInMilligramPerMillimole() {
        return getInUnit(MolarMassUnits.MILLIGRAM_PER_MILLIMOLE);
    }

    public double getInPoundPerPoundMole() {
        return getInUnit(MolarMassUnits.POUND_PER_POUND_MOLE);
    }

    public double getInOuncePerMole() {
        return getInUnit(MolarMassUnits.OUNCE_PER_MOLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MolarMass)) return false;
        MolarMass other = (MolarMass) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override
    public int hashCode() { return Objects.hash(baseValue, unitType.getBaseUnit()); }

    @Override
    public String toString() {
        return "MolarMass{" + value + unitType.getSymbol() + '}';
    }
}