package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.Distance;
import com.synerset.unitility.unitsystem.common.DistanceUnit;
import com.synerset.unitility.unitsystem.common.DistanceUnits;

import java.util.Objects;

/**
 * Represents the geographic, spherical distance between two coordinates on Earth, taking into account the curvature of
 * the Earth. The calculation includes the start coordinate, target coordinate, true bearing, and distance in specified
 * units, utilizing the Haversine equations as a calculation basis.
 */
public class GeoDistance implements CalculableQuantity<DistanceUnit, GeoDistance> {
    private final DistanceUnit unitType;
    private final GeoCoordinate startCoordinate;
    private final GeoCoordinate targetCoordinate;
    private final Angle trueBearing;
    private final Distance distance;

    /**
     * Constructs a GeoDistance object between the given start and target coordinates in the specified unit type.
     *
     * @param startCoordinate  The starting {@link GeoCoordinate}.
     * @param targetCoordinate The target {@link GeoCoordinate}.
     * @param unitType         The {@link DistanceUnit} type for representing the distance.
     */
    public GeoDistance(GeoCoordinate startCoordinate, GeoCoordinate targetCoordinate, DistanceUnit unitType) {
        this.unitType = unitType;
        this.startCoordinate = startCoordinate;
        this.targetCoordinate = targetCoordinate;
        this.trueBearing = HaversineEquations.calcBearing(startCoordinate, targetCoordinate);
        this.distance = HaversineEquations.calcSphericalDistance(startCoordinate, targetCoordinate).toUnit(unitType);
    }

    public GeoDistance(GeoCoordinate startCoordinate, Angle trueBearing, Distance distance) {
        this.distance = distance;
        this.unitType = distance.getUnitType();
        this.startCoordinate = startCoordinate;
        this.trueBearing = trueBearing;
        this.targetCoordinate = HaversineEquations.calcTargetCoordinate(startCoordinate, this.trueBearing, this.distance);
    }

    // Static factory methods

    /**
     * Creates a new GeoDistance instance with the specified start and target coordinates and unit type.
     *
     * @param startCoordinate  The starting {@link GeoCoordinate}.
     * @param targetCoordinate The target {@link GeoCoordinate}.
     * @param unitType         The {@link DistanceUnit} type for representing the distance.
     * @return A new {@link GeoDistance} instance.
     */
    public static GeoDistance of(GeoCoordinate startCoordinate, GeoCoordinate targetCoordinate, DistanceUnit unitType) {
        return new GeoDistance(startCoordinate, targetCoordinate, unitType);
    }

    /**
     * Creates a new GeoDistance instance with the specified start coordinate, true bearing, and distance.
     *
     * @param startCoordinate The starting {@link GeoCoordinate}.
     * @param trueBearing     The true bearing {@link Angle} in range (-180, 180)
     * @param distance        The {@link Distance} between start and target coordinates.
     * @return A new {@link GeoDistance} instance.
     */
    public static GeoDistance of(GeoCoordinate startCoordinate, Angle trueBearing, Distance distance) {
        return new GeoDistance(startCoordinate, trueBearing, distance);
    }

    /**
     * Creates a new GeoDistance instance with the specified start and target coordinates and in Kilometers unit type.
     *
     * @param startCoordinate  The starting {@link GeoCoordinate}.
     * @param targetCoordinate The target {@link GeoCoordinate}.
     * @return A new {@link GeoDistance} instance.
     */

    public static GeoDistance ofMeters(GeoCoordinate startCoordinate, GeoCoordinate targetCoordinate) {
        return new GeoDistance(startCoordinate, targetCoordinate, DistanceUnits.METER);
    }

    public static GeoDistance ofKilometers(GeoCoordinate startCoordinate, GeoCoordinate targetCoordinate) {
        return new GeoDistance(startCoordinate, targetCoordinate, DistanceUnits.KILOMETER);
    }

    public static GeoDistance ofMiles(GeoCoordinate startCoordinate, GeoCoordinate targetCoordinate) {
        return new GeoDistance(startCoordinate, targetCoordinate, DistanceUnits.MILE);
    }

    public static GeoDistance ofNauticalMiles(GeoCoordinate startCoordinate, GeoCoordinate targetCoordinate) {
        return new GeoDistance(startCoordinate, targetCoordinate, DistanceUnits.NAUTICAL_MILE);
    }

    // Progressing from the current START coordinate to a new TARGET coordinate default behavior for arithmetic operations

    /**
     * Progresses from the current START {@link GeoCoordinate} to a new TARGET {@link GeoCoordinate} by a specified
     * true bearing as {@link Angle} {-180deg,+180deg} and {@link Distance}.
     *
     * @param trueBearing The true bearing {@link Angle}.
     * @param byDistance  The {@link Distance} to progress by.
     * @return A new {@link GeoDistance} instance representing the progressed distance.
     */
    public GeoDistance with(Angle trueBearing, Distance byDistance) {
        if (byDistance.isEqualZero()) {
            return this;
        }
        return GeoDistance.of(this.startCoordinate, trueBearing, byDistance);
    }

    /**
     * Progresses from the current START {@link GeoCoordinate} to a new TARGET {@link GeoCoordinate} by a specified
     * distance.
     *
     * @param byDistance The {@link Distance} to progress by.
     * @return A new {@link GeoDistance} instance representing the progressed distance.
     */
    public GeoDistance with(Distance byDistance) {
        if (byDistance.isEqualZero()) {
            return this;
        }
        return GeoDistance.of(this.startCoordinate, this.trueBearing, byDistance);
    }

    /**
     * Progresses from the current START {@link GeoCoordinate} to a new TARGET {@link GeoCoordinate}.
     *
     * @param targetCoordinate The new target {@link GeoCoordinate}.
     * @return A new {@link GeoDistance} instance representing the progressed distance.
     */
    public GeoDistance with(GeoCoordinate targetCoordinate) {
        if (this.targetCoordinate.equals(targetCoordinate)) {
            return this;
        }
        return GeoDistance.of(this.startCoordinate, targetCoordinate, this.unitType);
    }

    /**
     * Progresses from the current START {@link GeoCoordinate} to a new TARGET {@link GeoCoordinate} by setting
     * the distance value to the provided value in the current unit type.
     *
     * @param distanceValue The new value for the {@link Distance}.
     * @return A new {@link GeoDistance} instance representing the progressed distance.
     */
    @Override
    public GeoDistance withValue(double distanceValue) {
        return with(Distance.of(distanceValue, unitType));
    }

    // Progressing from the current TARGET coordinate to a NEW TARGET coordinate

    /**
     * Progresses from the current TARGET {@link GeoCoordinate} to a new TARGET {@link GeoCoordinate} by a specified
     * true bearing as {@link Angle} {-180deg,+180deg} and {@link Distance}.
     *
     * @param trueBearing The true bearing {@link Angle}.
     * @param byDistance  The {@link Distance} to progress by.
     * @return A new {@link GeoDistance} instance representing the progressed distance.
     */
    public GeoDistance translate(Angle trueBearing, Distance byDistance) {
        if (byDistance.isEqualZero()) {
            return this;
        }
        return GeoDistance.of(this.targetCoordinate, trueBearing, byDistance);
    }

    /**
     * Progresses from the current TARGET {@link GeoCoordinate} to a new TARGET {@link GeoCoordinate} by a specified
     * distance.
     *
     * @param byDistance The {@link Distance} to progress by.
     * @return A new {@link GeoDistance} instance representing the progressed distance.
     */
    public GeoDistance translate(Distance byDistance) {
        if (byDistance.isEqualZero()) {
            return this;
        }
        return translate(this.trueBearing, byDistance);
    }

    /**
     * Progresses from the current TARGET {@link GeoCoordinate} to a new TARGET {@link GeoCoordinate}.
     *
     * @param targetCoordinate The new target {@link GeoCoordinate}.
     * @return A new {@link GeoDistance} instance representing the progressed distance.
     */
    public GeoDistance translate(GeoCoordinate targetCoordinate) {
        if (this.targetCoordinate.equals(targetCoordinate)) {
            return this;
        }
        return GeoDistance.of(this.targetCoordinate, targetCoordinate, this.unitType);
    }

    public Angle getTrueBearing() {
        return this.trueBearing;
    }

    public GeoCoordinate getStartCoordinate() {
        return this.startCoordinate;
    }

    public GeoCoordinate getTargetCoordinate() {
        return this.targetCoordinate;
    }

    public Distance getDistance() {
        return distance;
    }

    @Override
    public double getValue() {
        return distance.getValue();
    }

    // Convert to target unit
    public GeoDistance toMeter() {
        return toUnit(DistanceUnits.METER);
    }

    public GeoDistance toKilometer() {
        return toUnit(DistanceUnits.KILOMETER);
    }

    public GeoDistance toMile() {
        return toUnit(DistanceUnits.MILE);
    }

    public GeoDistance toNauticalMile() {
        return toUnit(DistanceUnits.NAUTICAL_MILE);
    }

    // Get value in target unit
    public double getInMeters() {
        return getInUnit(DistanceUnits.METER);
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

    @Override
    public double getBaseValue() {
        return distance.getBaseValue();
    }

    @Override
    public DistanceUnit getUnitType() {
        return unitType;
    }

    @Override
    public GeoDistance toBaseUnit() {
        return GeoDistance.of(startCoordinate, targetCoordinate, unitType.getBaseUnit());
    }

    @Override
    public GeoDistance toUnit(DistanceUnit targetUnit) {
        return GeoDistance.of(startCoordinate, targetCoordinate, targetUnit);
    }

    @Override
    public GeoDistance toUnit(String targetUnit) {
        DistanceUnit resolvedUnit = DistanceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoDistance that = (GeoDistance) o;
        return Objects.equals(unitType.getBaseUnit(), that.unitType.getBaseUnit())
                && startCoordinate.equals(that.startCoordinate)
                && targetCoordinate.equals(that.targetCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitType.getBaseUnit(), startCoordinate.toBaseUnit(), targetCoordinate.toBaseUnit());
    }

    @Override
    public String toString() {
        return "GeoDistance{" +
                "unitType=" + unitType +
                ", startCoordinate=" + startCoordinate +
                ", targetCoordinate=" + targetCoordinate +
                ", distance=" + distance +
                '}';
    }
}