package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/**
 * Vapour quality — the mass fraction of vapour in a two-phase (wet) mixture, bounded to [0, 1]
 * (0 = saturated liquid, 1 = saturated vapour). A dedicated bounded type, distinct from a generic
 * {@code Ratio}, so APIs that report or accept a steam/refrigerant quality are self-describing and
 * can be range-validated.
 */
public class VapourQuality implements CalculableQuantity<VapourQualityUnit, VapourQuality> {

    /** Saturated-liquid bound (quality = 0). */
    public static final VapourQuality PHYSICAL_MIN_LIMIT = VapourQuality.ofFraction(0.0);
    /** Saturated-vapour bound (quality = 1). */
    public static final VapourQuality PHYSICAL_MAX_LIMIT = VapourQuality.ofFraction(1.0);

    private final double value;
    private final double baseValue;
    private final VapourQualityUnit unitType;

    public VapourQuality(double value, VapourQualityUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = VapourQualityUnits.FRACTION;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static VapourQuality of(double value, VapourQualityUnit unit) {
        return new VapourQuality(value, unit);
    }

    public static VapourQuality of(double value, String unitSymbol) {
        VapourQualityUnit resolvedUnit = VapourQualityUnits.fromSymbol(unitSymbol);
        return new VapourQuality(value, resolvedUnit);
    }

    public static VapourQuality ofFraction(double value) {
        return new VapourQuality(value, VapourQualityUnits.FRACTION);
    }

    public static VapourQuality ofPercent(double value) {
        return new VapourQuality(value, VapourQualityUnits.PERCENT);
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
    public VapourQualityUnit getUnit() {
        return unitType;
    }

    @Override
    public VapourQuality toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public VapourQuality toUnit(VapourQualityUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return VapourQuality.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public VapourQuality toUnit(String targetUnit) {
        VapourQualityUnit resolvedUnit = VapourQualityUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public VapourQuality withValue(double value) {
        return VapourQuality.of(value, unitType);
    }

    // Convert to target unit
    public VapourQuality toFraction() {
        return toUnit(VapourQualityUnits.FRACTION);
    }

    public VapourQuality toPercent() {
        return toUnit(VapourQualityUnits.PERCENT);
    }

    // Get value in target unit
    public double getInFraction() {
        return getInUnit(VapourQualityUnits.FRACTION);
    }

    public double getInPercent() {
        return getInUnit(VapourQualityUnits.PERCENT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VapourQuality inputQuantity = (VapourQuality) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "VapourQuality{" + value + " " + unitType.getSymbol() + '}';
    }
}
