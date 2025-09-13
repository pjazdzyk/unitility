package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.common.DistanceUnit;
import com.synerset.unitility.unitsystem.common.DistanceUnits;

import java.util.Objects;

public class AbsoluteRoughness implements CalculableQuantity<DistanceUnit, AbsoluteRoughness> {

    public static final AbsoluteRoughness PHYSICAL_MIN_LIMIT = AbsoluteRoughness.ofMeters(0);
    private final double value;
    private final double baseValue;
    private final DistanceUnit unitType;

    public AbsoluteRoughness(double value, DistanceUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = DistanceUnits.METER;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static AbsoluteRoughness of(double value, DistanceUnit unit) {
        return new AbsoluteRoughness(value, unit);
    }

    public static AbsoluteRoughness of(double value, String unitSymbol) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(unitSymbol);
        return new AbsoluteRoughness(value, resolvedUnit);
    }

    public static AbsoluteRoughness ofMeters(double value) {
        return new AbsoluteRoughness(value, DistanceUnits.METER);
    }

    public static AbsoluteRoughness ofCentimeters(double value) {
        return new AbsoluteRoughness(value, DistanceUnits.CENTIMETER);
    }

    public static AbsoluteRoughness ofMillimeters(double value) {
        return new AbsoluteRoughness(value, DistanceUnits.MILLIMETER);
    }

    public static AbsoluteRoughness ofKilometers(double value) {
        return new AbsoluteRoughness(value, DistanceUnits.KILOMETER);
    }

    public static AbsoluteRoughness ofMiles(double value) {
        return new AbsoluteRoughness(value, DistanceUnits.MILE);
    }

    public static AbsoluteRoughness ofNauticalMiles(double value) {
        return new AbsoluteRoughness(value, DistanceUnits.NAUTICAL_MILE);
    }

    public static AbsoluteRoughness ofFeet(double value) {
        return new AbsoluteRoughness(value, DistanceUnits.FEET);
    }

    public static AbsoluteRoughness ofInches(double value) {
        return new AbsoluteRoughness(value, DistanceUnits.INCH);
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
    public DistanceUnit getUnit() {
        return unitType;
    }

    @Override
    public AbsoluteRoughness toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public AbsoluteRoughness toUnit(DistanceUnit targetUnit) {
        double valueInMeters = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInMeters);
        return AbsoluteRoughness.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public AbsoluteRoughness toUnit(String targetUnit) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public AbsoluteRoughness withValue(double value) {
        return AbsoluteRoughness.of(value, unitType);
    }

    // Convert to target unit
    public AbsoluteRoughness toMeter() {
        return toUnit(DistanceUnits.METER);
    }

    public AbsoluteRoughness toCentimeter() {
        return toUnit(DistanceUnits.CENTIMETER);
    }

    public AbsoluteRoughness toMillimeter() {
        return toUnit(DistanceUnits.MILLIMETER);
    }

    public AbsoluteRoughness toKilometer() {
        return toUnit(DistanceUnits.KILOMETER);
    }

    public AbsoluteRoughness toMile() {
        return toUnit(DistanceUnits.MILE);
    }

    public AbsoluteRoughness toNauticalMile() {
        return toUnit(DistanceUnits.NAUTICAL_MILE);
    }

    public AbsoluteRoughness toFeet() {
        return toUnit(DistanceUnits.FEET);
    }

    public AbsoluteRoughness toInch() {
        return toUnit(DistanceUnits.INCH);
    }

    // Get value in target unit
    public double getInMeters() {
        return getInUnit(DistanceUnits.METER);
    }

    public double getInCentimeters() {
        return getInUnit(DistanceUnits.CENTIMETER);
    }

    public double getInMillimeters() {
        return getInUnit(DistanceUnits.MILLIMETER);
    }

    public double getInKilometers() {
        return getInUnit(DistanceUnits.KILOMETER);
    }

    public double getInMiles() {
        return getInUnit(DistanceUnits.MILE);
    }

    public double getInNauticalMiles() {
        return getInUnit(DistanceUnits.NAUTICAL_MILE);
    }

    public double getInFeet() {
        return getInUnit(DistanceUnits.FEET);
    }

    public double getInInches() {
        return getInUnit(DistanceUnits.INCH);
    }

    public static AbsoluteRoughness of(PhysicalQuantity<? extends DistanceUnit> distanceType) {
        return AbsoluteRoughness.of(distanceType.getValue(), distanceType.getUnit());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbsoluteRoughness inputQuantity = (AbsoluteRoughness) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "AbsoluteRoughness{" + value + " " + unitType.getSymbol() + '}';
    }

}