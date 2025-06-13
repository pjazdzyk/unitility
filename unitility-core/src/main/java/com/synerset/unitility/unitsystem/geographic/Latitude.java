package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;

import java.util.Objects;

/**
 * Represents a latitude coordinate on any celestial spherical body, measured in degrees, with optional output
 * to DMS format (degrees minutes and seconds).
 * Latitude max/min values are not enforced here, for flexibility.
 */
public class Latitude implements CalculableQuantity<AngleUnit, Latitude> {

    public static final Latitude MIN_EARTH_LATITUDE = Latitude.ofDegrees(-90);
    public static final Latitude MAX_EARTH_LATITUDE = Latitude.ofDegrees(90);
    private final double value;
    private final double baseValue;
    private final AngleUnit unitType;

    public Latitude(double value, AngleUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = AngleUnits.DEGREES;
        }
        this.unitType = unitType;
        this.baseValue = Angle.of(value, unitType).getInDegrees();
    }

    // Static factory methods
    public static Latitude of(double value, AngleUnit unit) {
        return new Latitude(value, unit);
    }

    public static Latitude of(double value, String unitSymbol) {
        AngleUnit resolvedUnit = AngleUnits.fromSymbol(unitSymbol);
        return new Latitude(value, resolvedUnit);
    }

    public static Latitude ofRadians(double value) {
        return new Latitude(value, AngleUnits.RADIANS);
    }

    public static Latitude ofDegrees(double value) {
        return new Latitude(value, AngleUnits.DEGREES);
    }

    public static Latitude ofDegMinSec(int degrees, int minutes, double seconds) {
        double decimalDegrees = HaversineEquations.dmsToDegrees(degrees, minutes, seconds);
        double decimalDegreesWithSign = degrees > 0 ? decimalDegrees : decimalDegrees * -1;
        return ofDegrees(decimalDegreesWithSign);
    }

    public static Latitude ofDegMinSec(int degrees, int minutes, double seconds, PrimaryDirection direction) {
        double sign = HaversineEquations.determineSign(direction.getDirectionSymbol(), degrees);
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
    public Latitude toBaseUnit() {
        double degrees = unitType.toValueInBaseUnit(value);
        return Latitude.of(degrees, AngleUnits.DEGREES);
    }

    @Override
    public Latitude toUnit(AngleUnit targetUnit) {
        double valueInTargetUnit = Angle.of(value, unitType).toUnit(targetUnit).getValue();
        return Latitude.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Latitude toUnit(String targetUnit) {
        AngleUnit resolvedUnit = AngleUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Latitude withValue(double value) {
        return Latitude.of(value, unitType);
    }

    // Convert to target unit
    public Latitude toRadians() {
        return toUnit(AngleUnits.RADIANS);
    }

    public Latitude toDegrees() {
        return toUnit(AngleUnits.DEGREES);
    }

    // Get value in target unit
    public double getInRadians() {
        return getInUnit(AngleUnits.RADIANS);
    }

    public double getInDegrees() {
        return getInUnit(AngleUnits.DEGREES);
    }

    // Console output in DMS (degrees, minutes, seconds) format
    public String toDMSFormat() {
        return DMSValueFormatter.latitudeToDmsFormat(this, -1);
    }

    public String toDMSFormat(String variableName) {
        return variableName + " = " + toDMSFormat();
    }

    public String toDMSFormat(int relevantDigits) {
        return DMSValueFormatter.latitudeToDmsFormat(this, relevantDigits);
    }

    public String toDMSFormat(String variableName, int relevantDigits) {
        return variableName + " = " + toDMSFormat(relevantDigits);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Latitude inputQuantity = (Latitude) o;
        return Double.compare(inputQuantity.getInDegrees(), this.getInDegrees()) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        String separator = getUnit().getSymbol().contains("°") ? "" : " ";
        return "Latitude{" + value + separator + unitType.getSymbol() + '}';
    }

}