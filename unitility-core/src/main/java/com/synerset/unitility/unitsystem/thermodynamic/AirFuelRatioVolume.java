package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/**
 * Volumetric air-fuel ratio: volume of air per volume of fuel gas (dimensionless). Used for stoichiometric
 * combustion-air requirements. The value is unit-invariant across consistent volume units.
 */
public class AirFuelRatioVolume implements CalculableQuantity<AirFuelRatioVolumeUnit, AirFuelRatioVolume> {
    private final double value;
    private final double baseValue;
    private final AirFuelRatioVolumeUnit unitType;

    public AirFuelRatioVolume(double value, AirFuelRatioVolumeUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = AirFuelRatioVolumeUnits.CUBIC_METER_PER_CUBIC_METER;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static AirFuelRatioVolume of(double value, AirFuelRatioVolumeUnit unit) {
        return new AirFuelRatioVolume(value, unit);
    }

    public static AirFuelRatioVolume of(double value, String unitSymbol) {
        return new AirFuelRatioVolume(value, AirFuelRatioVolumeUnits.fromSymbol(unitSymbol));
    }

    public static AirFuelRatioVolume ofCubicMeterPerCubicMeter(double value) {
        return new AirFuelRatioVolume(value, AirFuelRatioVolumeUnits.CUBIC_METER_PER_CUBIC_METER);
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
    public AirFuelRatioVolumeUnit getUnit() {
        return unitType;
    }

    @Override
    public AirFuelRatioVolume toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }

    @Override
    public AirFuelRatioVolume toUnit(AirFuelRatioVolumeUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }

    @Override
    public AirFuelRatioVolume toUnit(String targetUnit) {
        return toUnit(AirFuelRatioVolumeUnits.fromSymbol(targetUnit));
    }

    @Override
    public AirFuelRatioVolume withValue(double value) {
        return of(value, unitType);
    }

    public double getInCubicMeterPerCubicMeter() {
        return getInUnit(AirFuelRatioVolumeUnits.CUBIC_METER_PER_CUBIC_METER);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirFuelRatioVolume)) return false;
        AirFuelRatioVolume other = (AirFuelRatioVolume) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "AirFuelRatioVolume{" + value + unitType.getSymbol() + '}';
    }
}
