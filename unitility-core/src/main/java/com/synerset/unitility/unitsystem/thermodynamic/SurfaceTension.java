package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/**
 * Surface tension {@code σ} in N/m: the force per unit length acting along a liquid-vapour (or liquid-liquid)
 * interface, equivalently the interfacial free energy per unit area (J/m²).
 */
public class SurfaceTension implements CalculableQuantity<SurfaceTensionUnit, SurfaceTension> {

    public static final SurfaceTension PHYSICAL_MIN_LIMIT = SurfaceTension.ofNewtonsPerMeter(0);

    private final double value;
    private final double baseValue;
    private final SurfaceTensionUnit unitType;

    public SurfaceTension(double value, SurfaceTensionUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = SurfaceTensionUnits.NEWTON_PER_METER;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static SurfaceTension of(double value, SurfaceTensionUnit unit) {
        return new SurfaceTension(value, unit);
    }

    public static SurfaceTension of(double value, String unitSymbol) {
        SurfaceTensionUnit resolvedUnit = SurfaceTensionUnits.fromSymbol(unitSymbol);
        return new SurfaceTension(value, resolvedUnit);
    }

    public static SurfaceTension ofNewtonsPerMeter(double value) {
        return new SurfaceTension(value, SurfaceTensionUnits.NEWTON_PER_METER);
    }

    public static SurfaceTension ofMillinewtonsPerMeter(double value) {
        return new SurfaceTension(value, SurfaceTensionUnits.MILLINEWTON_PER_METER);
    }

    public static SurfaceTension ofDynesPerCentimeter(double value) {
        return new SurfaceTension(value, SurfaceTensionUnits.DYNE_PER_CENTIMETER);
    }

    public static SurfaceTension ofPoundsForcePerFoot(double value) {
        return new SurfaceTension(value, SurfaceTensionUnits.POUND_FORCE_PER_FOOT);
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
    public SurfaceTensionUnit getUnit() {
        return unitType;
    }

    @Override
    public SurfaceTension toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public SurfaceTension toUnit(SurfaceTensionUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return SurfaceTension.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public SurfaceTension toUnit(String targetUnit) {
        SurfaceTensionUnit resolvedUnit = SurfaceTensionUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public SurfaceTension withValue(double value) {
        return SurfaceTension.of(value, unitType);
    }

    // Convert to target unit
    public SurfaceTension toNewtonsPerMeter() {
        return toUnit(SurfaceTensionUnits.NEWTON_PER_METER);
    }

    public SurfaceTension toMillinewtonsPerMeter() {
        return toUnit(SurfaceTensionUnits.MILLINEWTON_PER_METER);
    }

    public SurfaceTension toDynesPerCentimeter() {
        return toUnit(SurfaceTensionUnits.DYNE_PER_CENTIMETER);
    }

    public SurfaceTension toPoundsForcePerFoot() {
        return toUnit(SurfaceTensionUnits.POUND_FORCE_PER_FOOT);
    }

    // Get value in target unit
    public double getInNewtonsPerMeter() {
        return getInUnit(SurfaceTensionUnits.NEWTON_PER_METER);
    }

    public double getInMillinewtonsPerMeter() {
        return getInUnit(SurfaceTensionUnits.MILLINEWTON_PER_METER);
    }

    public double getInDynesPerCentimeter() {
        return getInUnit(SurfaceTensionUnits.DYNE_PER_CENTIMETER);
    }

    public double getInPoundsForcePerFoot() {
        return getInUnit(SurfaceTensionUnits.POUND_FORCE_PER_FOOT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurfaceTension other = (SurfaceTension) o;
        return Double.compare(other.toBaseUnit().getValue(), baseValue) == 0
                && Objects.equals(unitType.getBaseUnit(), other.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "SurfaceTension{" + value + " " + unitType.getSymbol() + '}';
    }
}
