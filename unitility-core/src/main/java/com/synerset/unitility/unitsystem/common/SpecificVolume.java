package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import java.util.Objects;

public class SpecificVolume implements CalculableQuantity<SpecificVolumeUnit, SpecificVolume> {
    private final double value;
    private final double baseValue;
    private final SpecificVolumeUnit unitType;

    public SpecificVolume(double value, SpecificVolumeUnit unitType) {
        this.value = value;
        if (unitType == null) { unitType = SpecificVolumeUnits.CUBIC_METER_PER_KILOGRAM; }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static SpecificVolume of(double value, SpecificVolumeUnit unit) {
        return new SpecificVolume(value, unit);
    }

    public static SpecificVolume of(double value, String unitSymbol) {
        return new SpecificVolume(value, SpecificVolumeUnits.fromSymbol(unitSymbol));
    }

    // Unit-specific factory methods
    public static SpecificVolume ofCubicMeterPerKilogram(double value) {
        return new SpecificVolume(value, SpecificVolumeUnits.CUBIC_METER_PER_KILOGRAM);
    }

    public static SpecificVolume ofCubicCentimeterPerKilogram(double value) {
        return new SpecificVolume(value, SpecificVolumeUnits.CUBIC_CENTIMETER_PER_KILOGRAM);
    }

    public static SpecificVolume ofCubicDecimeterPerKilogram(double value) {
        return new SpecificVolume(value, SpecificVolumeUnits.CUBIC_DECIMETER_PER_KILOGRAM);
    }

    public static SpecificVolume ofCubicFootPerKilogram(double value) {
        return new SpecificVolume(value, SpecificVolumeUnits.CUBIC_FOOT_PER_KILOGRAM);
    }

    public static SpecificVolume ofLiterPerKilogram(double value) {
        return new SpecificVolume(value, SpecificVolumeUnits.LITER_PER_KILOGRAM);
    }

    public static SpecificVolume ofHectoliterPerKilogram(double value) {
        return new SpecificVolume(value, SpecificVolumeUnits.HECTOLITER_PER_KILOGRAM);
    }

    public static SpecificVolume ofMilliliterPerKilogram(double value) {
        return new SpecificVolume(value, SpecificVolumeUnits.MILLILITER_PER_KILOGRAM);
    }

    public static SpecificVolume ofOuncePerKilogram(double value) {
        return new SpecificVolume(value, SpecificVolumeUnits.OUNCE_PER_KILOGRAM);
    }

    public static SpecificVolume ofPintPerKilogram(double value) {
        return new SpecificVolume(value, SpecificVolumeUnits.PINT_PER_KILOGRAM);
    }

    public static SpecificVolume ofGallonUSPerKilogram(double value) {
        return new SpecificVolume(value, SpecificVolumeUnits.GALLON_US_PER_KILOGRAM);
    }

    public static SpecificVolume ofGallonUKPerKilogram(double value) {
        return new SpecificVolume(value, SpecificVolumeUnits.GALLON_UK_PER_KILOGRAM);
    }

    // Implement CalculableQuantity interface methods
    @Override
    public double getValue() { return value; }

    @Override
    public double getBaseValue() { return baseValue; }

    @Override
    public SpecificVolumeUnit getUnit() { return unitType; }

    @Override
    public SpecificVolume toBaseUnit() {
        return of(unitType.toValueInBaseUnit(value), unitType.getBaseUnit());
    }

    @Override
    public SpecificVolume toUnit(SpecificVolumeUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(targetUnit.fromValueInBaseUnit(valueInBaseUnit), targetUnit);
    }

    @Override
    public SpecificVolume toUnit(String targetUnit) {
        return toUnit(SpecificVolumeUnits.fromSymbol(targetUnit));
    }

    @Override
    public SpecificVolume withValue(double value) {
        return of(value, unitType);
    }

    // Conversion methods
    public SpecificVolume toCubicMeterPerKilogram() { return toUnit(SpecificVolumeUnits.CUBIC_METER_PER_KILOGRAM); }
    public SpecificVolume toCubicCentimeterPerKilogram() { return toUnit(SpecificVolumeUnits.CUBIC_CENTIMETER_PER_KILOGRAM); }
    public SpecificVolume toCubicDecimeterPerKilogram() { return toUnit(SpecificVolumeUnits.CUBIC_DECIMETER_PER_KILOGRAM); }
    public SpecificVolume toCubicFootPerKilogram() { return toUnit(SpecificVolumeUnits.CUBIC_FOOT_PER_KILOGRAM); }
    public SpecificVolume toLiterPerKilogram() { return toUnit(SpecificVolumeUnits.LITER_PER_KILOGRAM); }
    public SpecificVolume toHectoliterPerKilogram() { return toUnit(SpecificVolumeUnits.HECTOLITER_PER_KILOGRAM); }
    public SpecificVolume toMilliliterPerKilogram() { return toUnit(SpecificVolumeUnits.MILLILITER_PER_KILOGRAM); }
    public SpecificVolume toOuncePerKilogram() { return toUnit(SpecificVolumeUnits.OUNCE_PER_KILOGRAM); }
    public SpecificVolume toPintPerKilogram() { return toUnit(SpecificVolumeUnits.PINT_PER_KILOGRAM); }
    public SpecificVolume toGallonUSPerKilogram() { return toUnit(SpecificVolumeUnits.GALLON_US_PER_KILOGRAM); }
    public SpecificVolume toGallonUKPerKilogram() { return toUnit(SpecificVolumeUnits.GALLON_UK_PER_KILOGRAM); }

    // Value getter methods
    public double getInCubicMeterPerKilogram() { return getInUnit(SpecificVolumeUnits.CUBIC_METER_PER_KILOGRAM); }
    public double getInCubicCentimeterPerKilogram() { return getInUnit(SpecificVolumeUnits.CUBIC_CENTIMETER_PER_KILOGRAM); }
    public double getInCubicDecimeterPerKilogram() { return getInUnit(SpecificVolumeUnits.CUBIC_DECIMETER_PER_KILOGRAM); }
    public double getInCubicFootPerKilogram() { return getInUnit(SpecificVolumeUnits.CUBIC_FOOT_PER_KILOGRAM); }
    public double getInLiterPerKilogram() { return getInUnit(SpecificVolumeUnits.LITER_PER_KILOGRAM); }
    public double getInHectoliterPerKilogram() { return getInUnit(SpecificVolumeUnits.HECTOLITER_PER_KILOGRAM); }
    public double getInMilliliterPerKilogram() { return getInUnit(SpecificVolumeUnits.MILLILITER_PER_KILOGRAM); }
    public double getInOuncePerKilogram() { return getInUnit(SpecificVolumeUnits.OUNCE_PER_KILOGRAM); }
    public double getInPintPerKilogram() { return getInUnit(SpecificVolumeUnits.PINT_PER_KILOGRAM); }
    public double getInGallonUSPerKilogram() { return getInUnit(SpecificVolumeUnits.GALLON_US_PER_KILOGRAM); }
    public double getInGallonUKPerKilogram() { return getInUnit(SpecificVolumeUnits.GALLON_UK_PER_KILOGRAM); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecificVolume)) return false;
        SpecificVolume other = (SpecificVolume) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0;
    }

    @Override
    public int hashCode() { return Objects.hash(baseValue, unitType.getBaseUnit()); }

    @Override
    public String toString() {
        return "SpecificVolume{" + value + " " + unitType.getSymbol() + "}";
    }
}