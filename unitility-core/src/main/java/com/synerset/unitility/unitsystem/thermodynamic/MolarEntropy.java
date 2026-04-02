package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import java.util.Objects;

public class MolarEntropy implements CalculableQuantity<MolarEntropyUnit, MolarEntropy> {
    private final double value;
    private final double baseValue;
    private final MolarEntropyUnit unitType;

    public MolarEntropy(double value, MolarEntropyUnit unitType) {
        this.value = value;
        if (unitType == null) { unitType = MolarEntropyUnits.JOULE_PER_MOLE_KELVIN; }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static MolarEntropy of(double value, MolarEntropyUnit unit) {
        return new MolarEntropy(value, unit);
    }

    public static MolarEntropy of(double value, String unitSymbol) {
        return new MolarEntropy(value, MolarEntropyUnits.fromSymbol(unitSymbol));
    }

    // Unit-specific factory methods (e.g., ofJoulesPerMoleKelvin, ofKilojoulesPerMoleKelvin)
    public static MolarEntropy ofJoulesPerMoleKelvin(double value) {
        return new MolarEntropy(value, MolarEntropyUnits.JOULE_PER_MOLE_KELVIN);
    }

    public static MolarEntropy ofKilojoulesPerMoleKelvin(double value) {
        return new MolarEntropy(value, MolarEntropyUnits.KILOJOULE_PER_MOLE_KELVIN);
    }

    public static MolarEntropy ofCaloriesPerMoleKelvin(double value) {
        return new MolarEntropy(value, MolarEntropyUnits.CALORIE_PER_MOLE_KELVIN);
    }

    // Implement CalculableQuantity interface methods
    @Override public double getValue() { return value; }
    @Override public double getBaseValue() { return baseValue; }
    @Override public MolarEntropyUnit getUnit() { return unitType; }
    @Override public MolarEntropy toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }
    @Override public MolarEntropy toUnit(MolarEntropyUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }
    @Override public MolarEntropy toUnit(String targetUnit) {
        return toUnit(MolarEntropyUnits.fromSymbol(targetUnit));
    }
    @Override public MolarEntropy withValue(double value) {
        return of(value, unitType);
    }

    // Conversion methods (e.g., toJoulesPerMoleKelvin(), toKilojoulesPerMoleKelvin())
    public double getInJoulesPerMoleKelvin() {
        return MolarEntropyUnits.JOULE_PER_MOLE_KELVIN.fromValueInBaseUnit(baseValue);
    }

    public double getInKilojoulesPerMoleKelvin() {
        return MolarEntropyUnits.KILOJOULE_PER_MOLE_KELVIN.fromValueInBaseUnit(baseValue);
    }

    public double getInCaloriesPerMoleKelvin() {
        return MolarEntropyUnits.CALORIE_PER_MOLE_KELVIN.fromValueInBaseUnit(baseValue);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MolarEntropy)) return false;
        MolarEntropy other = (MolarEntropy) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override public int hashCode() { return Objects.hash(baseValue, unitType.getBaseUnit()); }

    @Override public String toString() {
        return "MolarEntropy{" + value + unitType.getSymbol() + '}';
    }
}