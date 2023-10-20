package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Force implements PhysicalQuantity<ForceUnits> {

    private final double value;
    private final double baseValue;
    private final ForceUnits unitType;

    public Force(double value, ForceUnits unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Force of(double value, ForceUnits unit) {
        return new Force(value, unit);
    }

    public static Force ofNewtons(double value) {
        return new Force(value, ForceUnits.NEWTON);
    }

    public static Force ofKiloNewtons(double value) {
        return new Force(value, ForceUnits.KILONEWTON);
    }

    public static Force ofKiloponds(double value) {
        return new Force(value, ForceUnits.KILOPOND);
    }

    public static Force ofDynes(double value) {
        return new Force(value, ForceUnits.DYNE);
    }

    public static Force ofPoundForce(double value) {
        return new Force(value, ForceUnits.POUND_FORCE);
    }

    public static Force ofPoundal(double value) {
        return new Force(value, ForceUnits.POUNDAL);
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
    public ForceUnits getUnitType() {
        return unitType;
    }

    @Override
    public Force toBaseUnit() {
        double valueInNewtons = unitType.toValueInBaseUnit(value);
        return Force.of(valueInNewtons, ForceUnits.NEWTON);
    }

    @Override
    public Force toUnit(ForceUnits targetUnit) {
        double valueInNewtons = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInNewtons);
        return Force.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Force createNewWithValue(double value) {
        return Force.of(value, unitType);
    }

    // Convert to target unit
    public Force toNewtons() {
        return toUnit(ForceUnits.NEWTON);
    }

    public Force toKiloNewtons() {
        return toUnit(ForceUnits.KILONEWTON);
    }

    public Force toKiloponds() {
        return toUnit(ForceUnits.KILOPOND);
    }

    public Force toDynes() {
        return toUnit(ForceUnits.DYNE);
    }

    public Force toPoundForce() {
        return toUnit(ForceUnits.POUND_FORCE);
    }

    public Force toPoundal() {
        return toUnit(ForceUnits.POUNDAL);
    }

    // Get value in target unit
    public double getInNewtons() {
        return getIn(ForceUnits.NEWTON);
    }

    public double getInKiloNewtons() {
        return getIn(ForceUnits.KILONEWTON);
    }

    public double getInKiloponds() {
        return getIn(ForceUnits.KILOPOND);
    }

    public double getInDynes() {
        return getIn(ForceUnits.DYNE);
    }

    public double getInPoundsForce() {
        return getIn(ForceUnits.POUND_FORCE);
    }

    public double getInPoundals() {
        return getIn(ForceUnits.POUNDAL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Force inputQuantity = (Force) o;
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnitType().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Force{" + value + " " + unitType.getSymbol() + '}';
    }

}