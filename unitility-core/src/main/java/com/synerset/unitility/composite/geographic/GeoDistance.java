package com.synerset.unitility.composite.geographic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.Distance;
import com.synerset.unitility.unitsystem.common.DistanceUnit;
import com.synerset.unitility.unitsystem.common.DistanceUnits;

import static com.synerset.unitility.composite.geographic.GeoEquations.*;

public class GeoDistance implements PhysicalQuantity<DistanceUnit> {
    private final DistanceUnit unitType;
    private final GeoCoordinate startCoordinate;
    private final GeoCoordinate targetCoordinate;
    private final Angle bearing;
    private final Distance distance;

    public GeoDistance(GeoCoordinate startCoordinate, GeoCoordinate targetCoordinate, DistanceUnit unitType) {
        this.unitType = unitType;
        this.startCoordinate = startCoordinate;
        this.targetCoordinate = targetCoordinate;
        this.bearing = calcBearing(startCoordinate, targetCoordinate);
        this.distance = calcAngularDistance(startCoordinate, targetCoordinate).toUnit(unitType);
    }

    // Static factory methods
    public static GeoDistance of(GeoCoordinate startCoordinate, GeoCoordinate targetCoordinate, DistanceUnit unitType) {
        return new GeoDistance(startCoordinate, targetCoordinate, unitType);
    }

    public static GeoDistance of(GeoCoordinate startCoordinate, Angle bearing, Distance distance) {
        GeoCoordinate newTargetCoordinate = calcTargetCoordinate(startCoordinate, bearing, distance);
        return GeoDistance.of(startCoordinate, newTargetCoordinate, distance.getUnitType());
    }

    // Progressing from the current TARGET coordinate
    public GeoDistance translate(Angle bearing, Distance byDistance) {
        return GeoDistance.of(this.targetCoordinate, bearing, byDistance);
    }

    public GeoDistance translate(Distance byDistance) {
        return translate(this.bearing, byDistance);
    }

    public GeoDistance translate(GeoCoordinate targetCoordinate) {
        return GeoDistance.of(this.startCoordinate, targetCoordinate, this.unitType);
    }

    // Progressing from the current START coordinate
    public GeoDistance with(Angle bearing, Distance byDistance) {
        return GeoDistance.of(this.startCoordinate, bearing, byDistance);
    }

    public GeoDistance with(Distance byDistance) {
        return GeoDistance.of(this.startCoordinate, this.bearing, byDistance);
    }

    public GeoDistance with(GeoCoordinate targetCoordinate) {
        return GeoDistance.of(this.startCoordinate, targetCoordinate, this.unitType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public GeoDistance withValue(double distanceValue) {
        return with(Distance.of(distanceValue, unitType));
    }

    public Angle getBearing() {
        return this.bearing;
    }

    public GeoCoordinate getStartCoordinate() {
        return this.startCoordinate;
    }

    public GeoCoordinate getTargetCoordinate() {
        return this.targetCoordinate;
    }

    @Override
    public double getValue() {
        return distance.getValue();
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

    public static void main(String[] args) {
        GeoCoordinate from = GeoCoordinate.builder()
                .latitude(Angle.ofDegrees(511.1))
                .longitude(Angle.ofDegrees(17.078318888888887))
                .build();

        GeoCoordinate to = GeoCoordinate.builder()
                .latitude(Angle.ofDegrees(50.2))
                .longitude(Angle.ofDegrees(19.027))
                .build();

        GeoDistance geoDistance = GeoDistance.of(from, to, DistanceUnits.KILOMETER);
        System.out.printf("1. %s, end: %s, bearing: %s, distance: %s", geoDistance.getStartCoordinate().toEngineeringFormat("start", 3), geoDistance.getTargetCoordinate().toEngineeringFormat(), geoDistance.getBearing().toEngineeringFormat(3), geoDistance.distance.toEngineeringFormat(3));
        System.out.println();
        geoDistance = geoDistance.translate(Distance.ofKilometers(2));
        System.out.printf("1. %s, end: %s, bearing: %s, distance: %s", geoDistance.getStartCoordinate().toEngineeringFormat("start", 3), geoDistance.getTargetCoordinate().toEngineeringFormat(), geoDistance.getBearing().toEngineeringFormat(3), geoDistance.distance.toEngineeringFormat(3));

    }

}