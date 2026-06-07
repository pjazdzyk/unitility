package com.synerset.unitility.unitsystem.flow;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/**
 * Normal/standard volumetric gas flow — a volumetric flow referenced to a fixed thermodynamic state
 * (normal or standard conditions), the way compressed-air and gas systems are specified. Distinct from
 * actual-state {@link VolumetricFlow}: the base unit is the normal cubic metre per second (Nm³/s, at
 * 0 °C / 101.325 kPa), and standard-referenced units (Sm³, slpm, scfm) inter-convert via the
 * ideal-gas reference-state ratio documented on {@link NormalVolumetricFlowUnits}.
 */
public class NormalVolumetricFlow implements CalculableQuantity<NormalVolumetricFlowUnit, NormalVolumetricFlow> {

    public static final NormalVolumetricFlow PHYSICAL_MIN_LIMIT =
            NormalVolumetricFlow.ofNormalCubicMetersPerSecond(0.0);

    private final double value;
    private final double baseValue;
    private final NormalVolumetricFlowUnit unitType;

    public NormalVolumetricFlow(double value, NormalVolumetricFlowUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = NormalVolumetricFlowUnits.NORMAL_CUBIC_METERS_PER_SECOND;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static NormalVolumetricFlow of(double value, NormalVolumetricFlowUnit unit) {
        return new NormalVolumetricFlow(value, unit);
    }

    public static NormalVolumetricFlow of(double value, String unitSymbol) {
        NormalVolumetricFlowUnit resolvedUnit = NormalVolumetricFlowUnits.fromSymbol(unitSymbol);
        return new NormalVolumetricFlow(value, resolvedUnit);
    }

    public static NormalVolumetricFlow ofNormalCubicMetersPerSecond(double value) {
        return new NormalVolumetricFlow(value, NormalVolumetricFlowUnits.NORMAL_CUBIC_METERS_PER_SECOND);
    }

    public static NormalVolumetricFlow ofNormalCubicMetersPerMinute(double value) {
        return new NormalVolumetricFlow(value, NormalVolumetricFlowUnits.NORMAL_CUBIC_METERS_PER_MINUTE);
    }

    public static NormalVolumetricFlow ofNormalCubicMetersPerHour(double value) {
        return new NormalVolumetricFlow(value, NormalVolumetricFlowUnits.NORMAL_CUBIC_METERS_PER_HOUR);
    }

    public static NormalVolumetricFlow ofNormalLitersPerSecond(double value) {
        return new NormalVolumetricFlow(value, NormalVolumetricFlowUnits.NORMAL_LITERS_PER_SECOND);
    }

    public static NormalVolumetricFlow ofNormalLitersPerMinute(double value) {
        return new NormalVolumetricFlow(value, NormalVolumetricFlowUnits.NORMAL_LITERS_PER_MINUTE);
    }

    public static NormalVolumetricFlow ofStandardCubicMetersPerHour(double value) {
        return new NormalVolumetricFlow(value, NormalVolumetricFlowUnits.STANDARD_CUBIC_METERS_PER_HOUR);
    }

    public static NormalVolumetricFlow ofStandardLitersPerMinute(double value) {
        return new NormalVolumetricFlow(value, NormalVolumetricFlowUnits.STANDARD_LITERS_PER_MINUTE);
    }

    public static NormalVolumetricFlow ofStandardCubicFeetPerMinute(double value) {
        return new NormalVolumetricFlow(value, NormalVolumetricFlowUnits.STANDARD_CUBIC_FEET_PER_MINUTE);
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
    public NormalVolumetricFlowUnit getUnit() {
        return unitType;
    }

    @Override
    public NormalVolumetricFlow toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public NormalVolumetricFlow toUnit(NormalVolumetricFlowUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return NormalVolumetricFlow.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public NormalVolumetricFlow toUnit(String targetUnit) {
        NormalVolumetricFlowUnit resolvedUnit = NormalVolumetricFlowUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public NormalVolumetricFlow withValue(double value) {
        return NormalVolumetricFlow.of(value, unitType);
    }

    // Get value in target unit
    public double getInNormalCubicMetersPerSecond() {
        return getInUnit(NormalVolumetricFlowUnits.NORMAL_CUBIC_METERS_PER_SECOND);
    }

    public double getInNormalCubicMetersPerMinute() {
        return getInUnit(NormalVolumetricFlowUnits.NORMAL_CUBIC_METERS_PER_MINUTE);
    }

    public double getInNormalCubicMetersPerHour() {
        return getInUnit(NormalVolumetricFlowUnits.NORMAL_CUBIC_METERS_PER_HOUR);
    }

    public double getInNormalLitersPerSecond() {
        return getInUnit(NormalVolumetricFlowUnits.NORMAL_LITERS_PER_SECOND);
    }

    public double getInNormalLitersPerMinute() {
        return getInUnit(NormalVolumetricFlowUnits.NORMAL_LITERS_PER_MINUTE);
    }

    public double getInStandardCubicMetersPerHour() {
        return getInUnit(NormalVolumetricFlowUnits.STANDARD_CUBIC_METERS_PER_HOUR);
    }

    public double getInStandardLitersPerMinute() {
        return getInUnit(NormalVolumetricFlowUnits.STANDARD_LITERS_PER_MINUTE);
    }

    public double getInStandardCubicFeetPerMinute() {
        return getInUnit(NormalVolumetricFlowUnits.STANDARD_CUBIC_FEET_PER_MINUTE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalVolumetricFlow inputQuantity = (NormalVolumetricFlow) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "NormalVolumetricFlow{" + value + " " + unitType.getSymbol() + '}';
    }
}
