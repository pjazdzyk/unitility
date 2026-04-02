package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class MolarVolume implements CalculableQuantity<MolarVolumeUnit, MolarVolume> {
    private final double value;
    private final double baseValue;
    private final MolarVolumeUnit unitType;

    public MolarVolume(double value, MolarVolumeUnit unitType) {
        this.value = value;
        if (unitType == null) { unitType = MolarVolumeUnits.CUBIC_METER_PER_MOLE; }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static MolarVolume of(double value, MolarVolumeUnit unit) {
        return new MolarVolume(value, unit);
    }

    public static MolarVolume of(double value, String unitSymbol) {
        return new MolarVolume(value, MolarVolumeUnits.fromSymbol(unitSymbol));
    }

    public static MolarVolume ofCubicMetersPerMole(double value) {
        return new MolarVolume(value, MolarVolumeUnits.CUBIC_METER_PER_MOLE);
    }

    public static MolarVolume ofLitersPerMole(double value) {
        return new MolarVolume(value, MolarVolumeUnits.LITER_PER_MOLE);
    }

    public static MolarVolume ofCubicCentimetersPerMole(double value) {
        return new MolarVolume(value, MolarVolumeUnits.CUBIC_CENTIMETER_PER_MOLE);
    }

    public static MolarVolume ofIdealGasAtSTP() {
        // Ideal gas molar volume at STP (0°C, 1 atm) = 22.414 L/mol
        return new MolarVolume(22.414, MolarVolumeUnits.LITER_PER_MOLE);
    }

    public static MolarVolume ofIdealGasAtSATP() {
        // Ideal gas molar volume at SATP (25°C, 1 bar) = 24.789 L/mol
        return new MolarVolume(24.789, MolarVolumeUnits.LITER_PER_MOLE);
    }

    // Implement CalculableQuantity interface methods
    @Override public double getValue() { return value; }
    @Override public double getBaseValue() { return baseValue; }
    @Override public MolarVolumeUnit getUnit() { return unitType; }
    
    @Override public MolarVolume toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }
    
    @Override public MolarVolume toUnit(MolarVolumeUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }
    
    @Override public MolarVolume toUnit(String targetUnit) {
        return toUnit(MolarVolumeUnits.fromSymbol(targetUnit));
    }
    
    @Override public MolarVolume withValue(double value) {
        return of(value, unitType);
    }

    // Conversion methods
    public MolarVolume toCubicMeterPerMole() { return toUnit(MolarVolumeUnits.CUBIC_METER_PER_MOLE); }
    public MolarVolume toLiterPerMole() { return toUnit(MolarVolumeUnits.LITER_PER_MOLE); }
    public MolarVolume toCubicCentimeterPerMole() { return toUnit(MolarVolumeUnits.CUBIC_CENTIMETER_PER_MOLE); }
    public MolarVolume toCubicFootPerPoundMole() { return toUnit(MolarVolumeUnits.CUBIC_FOOT_PER_POUND_MOLE); }

    // Value getter methods
    public double getInCubicMetersPerMole() { return getInUnit(MolarVolumeUnits.CUBIC_METER_PER_MOLE); }
    public double getInLitersPerMole() { return getInUnit(MolarVolumeUnits.LITER_PER_MOLE); }
    public double getInCubicCentimetersPerMole() { return getInUnit(MolarVolumeUnits.CUBIC_CENTIMETER_PER_MOLE); }
    public double getInCubicFeetPerPoundMole() { return getInUnit(MolarVolumeUnits.CUBIC_FOOT_PER_POUND_MOLE); }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MolarVolume)) return false;
        MolarVolume other = (MolarVolume) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override public int hashCode() { return Objects.hash(baseValue, unitType.getBaseUnit()); }

    @Override public String toString() {
        return "MolarVolume{" + value + " " + unitType.getSymbol() + "}";
    }
}