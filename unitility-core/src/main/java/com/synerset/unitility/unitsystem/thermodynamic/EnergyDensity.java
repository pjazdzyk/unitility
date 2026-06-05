package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/**
 * Energy per unit volume (base unit J/m³). Common engineering uses: the volumetric calorific
 * (heating) value and the Wobbe index of fuel gases.
 */
public class EnergyDensity implements CalculableQuantity<EnergyDensityUnit, EnergyDensity> {
    private final double value;
    private final double baseValue;
    private final EnergyDensityUnit unitType;

    public EnergyDensity(double value, EnergyDensityUnit unitType) {
        this.value = value;
        if (unitType == null) { unitType = EnergyDensityUnits.JOULE_PER_CUBIC_METER; }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static EnergyDensity of(double value, EnergyDensityUnit unit) {
        return new EnergyDensity(value, unit);
    }

    public static EnergyDensity of(double value, String unitSymbol) {
        return new EnergyDensity(value, EnergyDensityUnits.fromSymbol(unitSymbol));
    }

    public static EnergyDensity ofJoulePerCubicMeter(double value) {
        return new EnergyDensity(value, EnergyDensityUnits.JOULE_PER_CUBIC_METER);
    }

    public static EnergyDensity ofKilojoulePerCubicMeter(double value) {
        return new EnergyDensity(value, EnergyDensityUnits.KILOJOULE_PER_CUBIC_METER);
    }

    public static EnergyDensity ofMegajoulePerCubicMeter(double value) {
        return new EnergyDensity(value, EnergyDensityUnits.MEGAJOULE_PER_CUBIC_METER);
    }

    public static EnergyDensity ofKilowattHourPerCubicMeter(double value) {
        return new EnergyDensity(value, EnergyDensityUnits.KILOWATT_HOUR_PER_CUBIC_METER);
    }

    public static EnergyDensity ofBTUPerCubicFoot(double value) {
        return new EnergyDensity(value, EnergyDensityUnits.BTU_PER_CUBIC_FOOT);
    }

    // CalculableQuantity interface methods
    @Override public double getValue() { return value; }
    @Override public double getBaseValue() { return baseValue; }
    @Override public EnergyDensityUnit getUnit() { return unitType; }

    @Override public EnergyDensity toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }

    @Override public EnergyDensity toUnit(EnergyDensityUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }

    @Override public EnergyDensity toUnit(String targetUnit) {
        return toUnit(EnergyDensityUnits.fromSymbol(targetUnit));
    }

    @Override public EnergyDensity withValue(double value) {
        return of(value, unitType);
    }

    // Conversion methods
    public EnergyDensity toJoulePerCubicMeter() { return toUnit(EnergyDensityUnits.JOULE_PER_CUBIC_METER); }
    public EnergyDensity toKilojoulePerCubicMeter() { return toUnit(EnergyDensityUnits.KILOJOULE_PER_CUBIC_METER); }
    public EnergyDensity toMegajoulePerCubicMeter() { return toUnit(EnergyDensityUnits.MEGAJOULE_PER_CUBIC_METER); }
    public EnergyDensity toKilowattHourPerCubicMeter() { return toUnit(EnergyDensityUnits.KILOWATT_HOUR_PER_CUBIC_METER); }
    public EnergyDensity toBTUPerCubicFoot() { return toUnit(EnergyDensityUnits.BTU_PER_CUBIC_FOOT); }

    // Value getter methods
    public double getInJoulePerCubicMeter() { return getInUnit(EnergyDensityUnits.JOULE_PER_CUBIC_METER); }
    public double getInKilojoulePerCubicMeter() { return getInUnit(EnergyDensityUnits.KILOJOULE_PER_CUBIC_METER); }
    public double getInMegajoulePerCubicMeter() { return getInUnit(EnergyDensityUnits.MEGAJOULE_PER_CUBIC_METER); }
    public double getInKilowattHourPerCubicMeter() { return getInUnit(EnergyDensityUnits.KILOWATT_HOUR_PER_CUBIC_METER); }
    public double getInBTUPerCubicFoot() { return getInUnit(EnergyDensityUnits.BTU_PER_CUBIC_FOOT); }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnergyDensity)) return false;
        EnergyDensity other = (EnergyDensity) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override public int hashCode() { return Objects.hash(baseValue, unitType.getBaseUnit()); }

    @Override public String toString() {
        return "EnergyDensity{" + value + " " + unitType.getSymbol() + "}";
    }
}
