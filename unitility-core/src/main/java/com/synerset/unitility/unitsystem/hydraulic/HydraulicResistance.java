package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/** The quadratic loss coefficient K of a flow path, dp = K * mdot^2. SI unit: Pa·s²/kg² (= 1/(kg·m)). */
public class HydraulicResistance implements CalculableQuantity<HydraulicResistanceUnit, HydraulicResistance> {

    private final double value;
    private final double baseValue;
    private final HydraulicResistanceUnit unitType;

    public HydraulicResistance(double value, HydraulicResistanceUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = HydraulicResistanceUnits.PASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static HydraulicResistance of(double value, HydraulicResistanceUnit unit) {
        return new HydraulicResistance(value, unit);
    }

    public static HydraulicResistance of(double value, String unitSymbol) {
        HydraulicResistanceUnit resolvedUnit = HydraulicResistanceUnits.fromSymbol(unitSymbol);
        return new HydraulicResistance(value, resolvedUnit);
    }

    // Factory methods for each unit
    public static HydraulicResistance ofPascalSecondSquaredPerKilogramSquared(double value) {
        return new HydraulicResistance(value, HydraulicResistanceUnits.PASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED);
    }

    public static HydraulicResistance ofKilopascalSecondSquaredPerKilogramSquared(double value) {
        return new HydraulicResistance(value, HydraulicResistanceUnits.KILOPASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED);
    }

    public static HydraulicResistance ofBarHourSquaredPerTonneSquared(double value) {
        return new HydraulicResistance(value, HydraulicResistanceUnits.BAR_HOUR_SQUARED_PER_TONNE_SQUARED);
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
    public HydraulicResistanceUnit getUnit() {
        return unitType;
    }

    @Override
    public HydraulicResistance toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public HydraulicResistance toUnit(HydraulicResistanceUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return HydraulicResistance.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public HydraulicResistance toUnit(String targetUnit) {
        HydraulicResistanceUnit resolvedUnit = HydraulicResistanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public HydraulicResistance withValue(double value) {
        return HydraulicResistance.of(value, unitType);
    }

    // Convert to target unit
    public HydraulicResistance toPascalSecondSquaredPerKilogramSquared() {
        return toUnit(HydraulicResistanceUnits.PASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED);
    }

    public HydraulicResistance toKilopascalSecondSquaredPerKilogramSquared() {
        return toUnit(HydraulicResistanceUnits.KILOPASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED);
    }

    public HydraulicResistance toBarHourSquaredPerTonneSquared() {
        return toUnit(HydraulicResistanceUnits.BAR_HOUR_SQUARED_PER_TONNE_SQUARED);
    }

    // Get value in target unit
    public double getInPascalSecondSquaredPerKilogramSquared() {
        return getInUnit(HydraulicResistanceUnits.PASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED);
    }

    public double getInKilopascalSecondSquaredPerKilogramSquared() {
        return getInUnit(HydraulicResistanceUnits.KILOPASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED);
    }

    public double getInBarHourSquaredPerTonneSquared() {
        return getInUnit(HydraulicResistanceUnits.BAR_HOUR_SQUARED_PER_TONNE_SQUARED);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydraulicResistance that = (HydraulicResistance) o;
        return Double.compare(that.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), that.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "HydraulicResistance{" + value + " " + unitType.getSymbol() + '}';
    }
}
