package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.common.Curvature;
import com.synerset.unitility.unitsystem.common.CurvatureUnits;

import java.util.Objects;

public class RotationSpeedToFlowRateRatio implements CalculableQuantity<RotationSpeedToFlowRateRatioUnit, RotationSpeedToFlowRateRatio> {

    private final double value;
    private final double baseValue;
    private final RotationSpeedToFlowRateRatioUnit unitType;

    public RotationSpeedToFlowRateRatio(double value, RotationSpeedToFlowRateRatioUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = RotationSpeedToFlowRateRatioUnits.RADIAN_PER_SECOND_PER_CUBIC_METER_PER_SECOND;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static RotationSpeedToFlowRateRatio of(double value, RotationSpeedToFlowRateRatioUnit unit) {
        return new RotationSpeedToFlowRateRatio(value, unit);
    }

    public static RotationSpeedToFlowRateRatio of(double value, String unitSymbol) {
        RotationSpeedToFlowRateRatioUnit resolvedUnit = RotationSpeedToFlowRateRatioUnits.fromSymbol(unitSymbol);
        return new RotationSpeedToFlowRateRatio(value, resolvedUnit);
    }

    // Factory methods for each unit
    public static RotationSpeedToFlowRateRatio ofRadianPerSecondPerCubicMeterPerSecond(double value) {
        return new RotationSpeedToFlowRateRatio(value, RotationSpeedToFlowRateRatioUnits.RADIAN_PER_SECOND_PER_CUBIC_METER_PER_SECOND);
    }

    // Factory methods for each unit
    public static RotationSpeedToFlowRateRatio ofRpmPerGpm(double value) {
        return new RotationSpeedToFlowRateRatio(value, RotationSpeedToFlowRateRatioUnits.RPM_PER_GPM);
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
    public RotationSpeedToFlowRateRatioUnit getUnit() {
        return unitType;
    }

    @Override
    public RotationSpeedToFlowRateRatio toBaseUnit() {
        double valueInPascal = unitType.toValueInBaseUnit(value);
        return of(valueInPascal, RotationSpeedToFlowRateRatioUnits.RADIAN_PER_SECOND_PER_CUBIC_METER_PER_SECOND);
    }

    @Override
    public RotationSpeedToFlowRateRatio toUnit(RotationSpeedToFlowRateRatioUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return RotationSpeedToFlowRateRatio.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public RotationSpeedToFlowRateRatio toUnit(String targetUnit) {
        RotationSpeedToFlowRateRatioUnit resolvedUnit = RotationSpeedToFlowRateRatioUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public RotationSpeedToFlowRateRatio withValue(double value) {
        return RotationSpeedToFlowRateRatio.of(value, unitType);
    }

    // Convert to target unit
    public RotationSpeedToFlowRateRatio toRadianPerSecondForCubicMeterPerSecond() {
        return toUnit(RotationSpeedToFlowRateRatioUnits.RADIAN_PER_SECOND_PER_CUBIC_METER_PER_SECOND);
    }

    public RotationSpeedToFlowRateRatio toRpmPerGpm() {
        return toUnit(RotationSpeedToFlowRateRatioUnits.RPM_PER_GPM);
    }

    // Get value in target unit
    public double getInRadianPerSecondForCubicMeterPerSecond() {
        return getInUnit(RotationSpeedToFlowRateRatioUnits.RADIAN_PER_SECOND_PER_CUBIC_METER_PER_SECOND);
    }

    public double getInRpmPerGpm() {
        return getInUnit(RotationSpeedToFlowRateRatioUnits.RPM_PER_GPM);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RotationSpeedToFlowRateRatio that = (RotationSpeedToFlowRateRatio) o;
        return Double.compare(that.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), that.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "RotationSpeedToFlowRatio{" + value + " " + unitType.getSymbol() + '}';
    }
}
