package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;

import java.util.Objects;

/**
 * Represents a longitude coordinate on any celestial spherical body, measured in degrees, with optional output
 * to DMS format (degrees minutes and seconds).
 * Longitude max/min values are not enforced here, for flexibility.
 */
public class Longitude implements CalculableQuantity<AngleUnit, Longitude> {
    public static final Longitude MIN_EARTH_LONGITUDE = Longitude.ofDegrees(-180);
    public static final Longitude MAX_EARTH_LONGITUDE = Longitude.ofDegrees(180);
    private final double value;
    private final double baseValue;
    private final AngleUnit unitType;

    public Longitude(double value, AngleUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = AngleUnits.getDefaultUnit();
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Longitude of(double value, AngleUnit unit) {
        return new Longitude(value, unit);
    }

    public static Longitude of(double value, String unitSymbol) {
        AngleUnit resolvedUnit = AngleUnits.fromSymbol(unitSymbol);
        return new Longitude(value, resolvedUnit);
    }

    public static Longitude ofRadians(double value) {
        return new Longitude(value, AngleUnits.RADIANS);
    }

    public static Longitude ofDegrees(double value) {
        return new Longitude(value, AngleUnits.DEGREES);
    }

    public static Longitude ofDegMinSec(int degrees, int minutes, double seconds) {
        double decimalDegrees = HaversineEquations.dmsToDegrees(degrees, minutes, seconds);
        double decimalDegreesWithSign = degrees > 0 ? decimalDegrees : decimalDegrees * -1;
        return ofDegrees(decimalDegreesWithSign);
    }

    public static Longitude ofDegMinSec(int degrees, int minutes, double seconds, CardinalDirection direction) {
        double sign = HaversineEquations.determineSign(direction.getDirectionChar(), degrees);
        double decimalDegrees = HaversineEquations.dmsToDegrees(degrees, minutes, seconds);
        return ofDegrees(sign * decimalDegrees);
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
    public AngleUnit getUnit() {
        return unitType;
    }

    @Override
    public Longitude toBaseUnit() {
        double degrees = unitType.toValueInBaseUnit(value);
        return Longitude.of(degrees, AngleUnits.DEGREES);
    }

    @Override
    public Longitude toUnit(AngleUnit targetUnit) {
        double valueInDegrees = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInDegrees);
        return Longitude.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Longitude toUnit(String targetUnit) {
        AngleUnit resolvedUnit = AngleUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Longitude withValue(double value) {
        return Longitude.of(value, unitType);
    }

    // Console output in DMS (degrees, minutes, seconds) format
    public String toDMSFormat() {
        return DMSValueFormatter.longitudeToDmsFormat(this, -1);
    }

    public String toDMSFormat(String variableName) {
        return variableName + " = " + toDMSFormat();
    }

    public String toDMSFormat(int relevantDigits) {
        return DMSValueFormatter.longitudeToDmsFormat(this, relevantDigits);
    }

    public String toDMSFormat(String variableName, int relevantDigits) {
        return variableName + " = " + toDMSFormat(relevantDigits);
    }

    // Convert to target unit
    public Longitude toRadians() {
        return toUnit(AngleUnits.RADIANS);
    }

    public Longitude toDegrees() {
        return toUnit(AngleUnits.DEGREES);
    }

    // Get value in target unit
    public double getInRadians() {
        return getInUnit(AngleUnits.RADIANS);
    }

    public double getInDegrees() {
        return getInUnit(AngleUnits.DEGREES);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Longitude inputQuantity = (Longitude) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        String separator = getUnit().getSymbol().contains("°") ? "" : " ";
        return "Longitude{" + value + separator + unitType.getSymbol() + '}';
    }

}