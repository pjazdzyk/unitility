package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.TrigonometricQuantity;
import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.Objects;

/**
 * Represents a bearing angle, which can be either a true bearing (0 to 360 degrees)
 * or a signed bearing (-180 to +180 degrees).
 * <p>
 * By default, the constructor assumes a true bearing angle in a clockwise direction from 0 to 360 degrees.
 * Signed bearings are represented as values from -180 to +180 degrees.
 * </p>
 */
public class Bearing implements TrigonometricQuantity<Bearing> {

    private final double value;
    private final double baseValue;
    private final double signedValue;
    private final AngleUnit unitType;

    /**
     * Constructs a Bearing instance using a true bearing value and an optional unit type.
     * <p>
     * The angle is treated as a true bearing (0 to 360 degrees by default), and the corresponding signed bearing
     * (-180 to +180 degrees) is calculated internally.
     * If the provided unit type is null, the default angle unit is used (usually degrees).
     * </p>
     *
     * @param trueBearingValueDeg the true bearing value in degrees (0 to 360 degrees)
     * @param unitType            the unit type for the bearing value (e.g., degrees, radians)
     */
    public Bearing(double trueBearingValueDeg, AngleUnit unitType) {
        this.value = trueBearingValueDeg;
        if (unitType == null) {
            unitType = AngleUnits.getDefaultUnit();
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(trueBearingValueDeg);
        this.signedValue = HaversineEquations.toSignedBearing(baseValue);
    }

    // Static factory methods

    /**
     * Creates a Bearing instance from a true bearing value in degrees.
     * <p>
     * This method assumes the angle is a true bearing (0 to 360 degrees).
     * </p>
     *
     * @param trueBearingValueDeg the true bearing value in degrees (0 to 360 degrees)
     * @return a new Bearing instance with the specified true bearing value in degrees
     */
    public static Bearing of(double trueBearingValueDeg) {
        return new Bearing(trueBearingValueDeg, AngleUnits.DEGREES);
    }

    /**
     * Creates a new {@link Bearing} instance based on a numeric value and a unit symbol.
     * <p>
     * This method resolves the unit of measurement from the provided symbol and
     * constructs a {@link Bearing} using the given value in the corresponding unit.
     * </p>
     *
     * @param value      the numeric bearing value
     * @param unitSymbol the symbol representing the angle unit (e.g., "°" for degrees, "rad" for radians)
     * @return a new {@link Bearing} instance using the specified value and unit
     * @throws IllegalArgumentException if the unit symbol is invalid or unrecognized
     */
    public static Bearing of(double value, String unitSymbol) {
        AngleUnit resolvedUnit = AngleUnits.fromSymbol(unitSymbol);
        return new Bearing(value, resolvedUnit);
    }

    /**
     * Creates a Bearing instance from a true bearing value.
     * <p>
     * This method assumes the angle is a true bearing (0 to 360 degrees) and the provided
     * Angle object represents the true bearing in degrees.
     * </p>
     *
     * @param trueBearing the Angle object representing the true bearing value in degrees
     * @return a new Bearing instance with the specified true bearing value in degrees
     */
    public static Bearing of(Angle trueBearing) {
        return new Bearing(trueBearing.getInDegrees(), AngleUnits.DEGREES);
    }

    /**
     * Creates a new {@link Bearing} based on a cardinal direction and a relative bearing.
     * <p>
     * This method takes the true bearing of the given cardinal direction and adds the signed relative bearing
     * (which is an offset) to calculate the new true bearing. The result is then normalized to be within the
     * range of 0 to 360 degrees.
     * </p>
     *
     * @param cardinalDirection the cardinal direction (e.g., NORTH, EAST, SOUTH, WEST)
     * @param relativeBearing   the relative bearing to adjust the cardinal direction's bearing by
     *                          (can be positive or negative)
     * @return a new {@link Bearing} instance representing the calculated bearing
     */
    public static Bearing of(CardinalDirection cardinalDirection, Bearing relativeBearing) {
        double trueBearing = cardinalDirection.getTrueBearing().getValue();
        double signedRelativeBearing = relativeBearing.getSignedValueInDegrees();
        double newTrueBearing = trueBearing + signedRelativeBearing;
        newTrueBearing = (newTrueBearing + 360) % 360;
        return Bearing.of(newTrueBearing);
    }

    /**
     * Returns the true bearing corresponding to the specified cardinal direction.
     * <p>
     * This method retrieves the predefined true bearing value associated with the
     * given cardinal direction. The returned {@link Bearing} represents the exact
     * direction without any modifications.
     * </p>
     *
     * @param cardinalDirection the cardinal direction (e.g., NORTH, EAST, SOUTH, WEST)
     * @return the corresponding {@link Bearing} instance for the given cardinal direction
     */
    public static Bearing of(CardinalDirection cardinalDirection) {
        return cardinalDirection.getTrueBearing();
    }

    /**
     * Returns the Bearing instance for true north (0 degrees).
     *
     * @return Bearing instance representing north
     */
    public static Bearing north() {
        return Bearing.of(0.0);
    }

    /**
     * Returns the Bearing instance for due east (90 degrees).
     *
     * @return Bearing instance representing east
     */
    public static Bearing east() {
        return Bearing.of(90.0);
    }

    /**
     * Returns the Bearing instance for due south (180 degrees).
     *
     * @return Bearing instance representing south
     */
    public static Bearing south() {
        return Bearing.of(180.0);
    }

    /**
     * Returns the Bearing instance for due west (270 degrees).
     *
     * @return Bearing instance representing west
     */
    public static Bearing west() {
        return Bearing.of(270.0);
    }

    /**
     * Returns the Bearing instance for northeast (45 degrees).
     *
     * @return Bearing instance representing northeast
     */
    public static Bearing northEast() {
        return Bearing.of(45.0);
    }

    /**
     * Returns the Bearing instance for southeast (135 degrees).
     *
     * @return Bearing instance representing southeast
     */
    public static Bearing southEast() {
        return Bearing.of(135.0);
    }

    /**
     * Returns the Bearing instance for southwest (225 degrees).
     *
     * @return Bearing instance representing southwest
     */
    public static Bearing southWest() {
        return Bearing.of(225.0);
    }

    /**
     * Returns the Bearing instance for northwest (315 degrees).
     *
     * @return Bearing instance representing northwest
     */
    public static Bearing northWest() {
        return Bearing.of(315.0);
    }

    /**
     * Creates a Bearing instance from a signed bearing value in degrees.
     * <p>
     * This method converts the signed bearing (-180 to +180 degrees) to the corresponding true bearing
     * (0 to 360 degrees) before creating the Bearing instance.
     * </p>
     *
     * @param signedBearingDeg the signed bearing value in degrees (-180 to +180 degrees)
     * @return a new Bearing instance with the corresponding true bearing value in degrees
     */
    public static Bearing ofSigned(double signedBearingDeg) {
        double trueBearingValue = HaversineEquations.toTrueBearing(signedBearingDeg);
        return new Bearing(trueBearingValue, AngleUnits.DEGREES);
    }

    /**
     * Creates a Bearing instance from a signed bearing value.
     * <p>
     * This method converts the signed bearing (represented by the provided Angle object, in the range of -180 to +180 degrees)
     * to the corresponding true bearing (0 to 360 degrees) before creating the Bearing instance.
     * </p>
     *
     * @param signedBearing the Angle object representing the signed bearing value in degrees (-180 to +180 degrees)
     * @return a new Bearing instance with the corresponding true bearing value in degrees
     */
    public static Bearing ofSigned(Angle signedBearing) {
        if (signedBearing == null) {
            throw new UnitSystemArgumentException("Bearing: Argument of signedBearing cannot be null.");
        }
        double trueBearingValue = HaversineEquations.toTrueBearing(signedBearing.getInDegrees());
        return new Bearing(trueBearingValue, AngleUnits.DEGREES);
    }

    @Override
    public double getValue() {
        return value;
    }

    public double getInDegrees() {
        return this.toUnit(AngleUnits.DEGREES).getValue();
    }

    public double getSignedValueInDegrees() {
        return signedValue;
    }

    public Angle getSigned() {
        return Angle.ofDegrees(signedValue);
    }

    public Bearing from(Bearing relativeBearing) {
        double currentTrueBearing = this.getValue();
        double signedRelativeBearing = relativeBearing.getSignedValueInDegrees();
        double newTrueBearing = currentTrueBearing + signedRelativeBearing;
        newTrueBearing = (newTrueBearing + 360) % 360;
        return Bearing.of(newTrueBearing);
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
    public Bearing toBaseUnit() {
        double degrees = unitType.toValueInBaseUnit(value);
        return new Bearing(degrees, AngleUnits.DEGREES);
    }

    @Override
    public Bearing toUnit(AngleUnit targetUnit) {
        double valueInDegrees = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInDegrees);
        return new Bearing(valueInTargetUnit, targetUnit);
    }

    @Override
    public Bearing toUnit(String targetUnit) {
        AngleUnit resolvedUnit = AngleUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Bearing withValue(double value) {
        return new Bearing(value, unitType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bearing inputQuantity = (Bearing) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        String separator = getUnit().getSymbol().contains("°") ? "" : " ";
        return "Bearing{" + value + separator + unitType.getSymbol() + '}';
    }

}