package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/**
 * Specific fan power: the electrical power a fan draws per unit of volumetric flow.
 *
 * <p>The figure a ventilation schedule carries and a design review argues about. It is its own
 * quantity rather than a {@code Pressure}, although the two share dimensions, because its unit set
 * is different: W per litre per second in Europe, W per cubic foot per minute in the United States,
 * and never an inch of water.</p>
 */
public class SpecificFanPower implements CalculableQuantity<SpecificFanPowerUnit, SpecificFanPower> {

    private final double value;
    private final double baseValue;
    private final SpecificFanPowerUnit unitType;

    public SpecificFanPower(double value, SpecificFanPowerUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = SpecificFanPowerUnits.WATT_PER_CUBIC_METER_PER_SECOND;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static SpecificFanPower of(double value, SpecificFanPowerUnit unit) {
        return new SpecificFanPower(value, unit);
    }

    public static SpecificFanPower of(double value, String unitSymbol) {
        SpecificFanPowerUnit resolvedUnit = SpecificFanPowerUnits.fromSymbol(unitSymbol);
        return new SpecificFanPower(value, resolvedUnit);
    }

    // Factory methods for each unit
    public static SpecificFanPower ofWattPerCubicMeterPerSecond(double value) {
        return new SpecificFanPower(value, SpecificFanPowerUnits.WATT_PER_CUBIC_METER_PER_SECOND);
    }

    public static SpecificFanPower ofWattPerLitrePerSecond(double value) {
        return new SpecificFanPower(value, SpecificFanPowerUnits.WATT_PER_LITRE_PER_SECOND);
    }

    public static SpecificFanPower ofKilowattPerCubicMeterPerSecond(double value) {
        return new SpecificFanPower(value, SpecificFanPowerUnits.KILOWATT_PER_CUBIC_METER_PER_SECOND);
    }

    public static SpecificFanPower ofWattPerCubicFootPerMinute(double value) {
        return new SpecificFanPower(value, SpecificFanPowerUnits.WATT_PER_CUBIC_FOOT_PER_MINUTE);
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
    public SpecificFanPowerUnit getUnit() {
        return unitType;
    }

    @Override
    public SpecificFanPower toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public SpecificFanPower toUnit(SpecificFanPowerUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return SpecificFanPower.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public SpecificFanPower toUnit(String targetUnit) {
        SpecificFanPowerUnit resolvedUnit = SpecificFanPowerUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public SpecificFanPower withValue(double value) {
        return SpecificFanPower.of(value, unitType);
    }

    // Convert to target unit
    public SpecificFanPower toWattPerCubicMeterPerSecond() {
        return toUnit(SpecificFanPowerUnits.WATT_PER_CUBIC_METER_PER_SECOND);
    }

    public SpecificFanPower toWattPerLitrePerSecond() {
        return toUnit(SpecificFanPowerUnits.WATT_PER_LITRE_PER_SECOND);
    }

    public SpecificFanPower toKilowattPerCubicMeterPerSecond() {
        return toUnit(SpecificFanPowerUnits.KILOWATT_PER_CUBIC_METER_PER_SECOND);
    }

    public SpecificFanPower toWattPerCubicFootPerMinute() {
        return toUnit(SpecificFanPowerUnits.WATT_PER_CUBIC_FOOT_PER_MINUTE);
    }

    // Get value in target unit
    public double getInWattPerCubicMeterPerSecond() {
        return getInUnit(SpecificFanPowerUnits.WATT_PER_CUBIC_METER_PER_SECOND);
    }

    public double getInWattPerLitrePerSecond() {
        return getInUnit(SpecificFanPowerUnits.WATT_PER_LITRE_PER_SECOND);
    }

    public double getInKilowattPerCubicMeterPerSecond() {
        return getInUnit(SpecificFanPowerUnits.KILOWATT_PER_CUBIC_METER_PER_SECOND);
    }

    public double getInWattPerCubicFootPerMinute() {
        return getInUnit(SpecificFanPowerUnits.WATT_PER_CUBIC_FOOT_PER_MINUTE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificFanPower that = (SpecificFanPower) o;
        return Double.compare(that.toBaseUnit().getValue(), baseValue) == 0
               && Objects.equals(unitType.getBaseUnit(), that.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "SpecificFanPower{" + value + " " + unitType.getSymbol() + '}';
    }
}
