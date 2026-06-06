package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/**
 * Gravimetric air-fuel ratio: mass of air per mass of fuel gas (dimensionless). Used for stoichiometric
 * combustion-air requirements. The value is unit-invariant across consistent mass units.
 */
public class AirFuelRatioMass implements CalculableQuantity<AirFuelRatioMassUnit, AirFuelRatioMass> {
    private final double value;
    private final double baseValue;
    private final AirFuelRatioMassUnit unitType;

    public AirFuelRatioMass(double value, AirFuelRatioMassUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = AirFuelRatioMassUnits.KILOGRAM_PER_KILOGRAM;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static AirFuelRatioMass of(double value, AirFuelRatioMassUnit unit) {
        return new AirFuelRatioMass(value, unit);
    }

    public static AirFuelRatioMass of(double value, String unitSymbol) {
        return new AirFuelRatioMass(value, AirFuelRatioMassUnits.fromSymbol(unitSymbol));
    }

    public static AirFuelRatioMass ofKilogramPerKilogram(double value) {
        return new AirFuelRatioMass(value, AirFuelRatioMassUnits.KILOGRAM_PER_KILOGRAM);
    }

    // CalculableQuantity interface
    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public AirFuelRatioMassUnit getUnit() {
        return unitType;
    }

    @Override
    public AirFuelRatioMass toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }

    @Override
    public AirFuelRatioMass toUnit(AirFuelRatioMassUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }

    @Override
    public AirFuelRatioMass toUnit(String targetUnit) {
        return toUnit(AirFuelRatioMassUnits.fromSymbol(targetUnit));
    }

    @Override
    public AirFuelRatioMass withValue(double value) {
        return of(value, unitType);
    }

    public double getInKilogramPerKilogram() {
        return getInUnit(AirFuelRatioMassUnits.KILOGRAM_PER_KILOGRAM);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirFuelRatioMass)) return false;
        AirFuelRatioMass other = (AirFuelRatioMass) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "AirFuelRatioMass{" + value + unitType.getSymbol() + '}';
    }
}
