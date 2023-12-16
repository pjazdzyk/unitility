package com.synerset.unitility.composite.geographic;

import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.Distance;

public class GeoEquations {

    private GeoEquations() {
        throw new IllegalStateException("Utility class");
    }

    public static final Distance EARTH_RADIUS = Distance.ofMeters(6_371_000);

    public static Angle calcBearing(GeoCoordinate startCoordinate, GeoCoordinate targetCoordinate) {
        double lat1 = startCoordinate.latitude().getInRadians();
        double lon1 = startCoordinate.longitude().getInRadians();
        double lat2 = targetCoordinate.latitude().getInRadians();
        double lon2 = targetCoordinate.longitude().getInRadians();

        double deltaLon = lon2 - lon1;

        double y = Math.sin(deltaLon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(deltaLon);

        double bearing = Math.atan2(y, x);
        bearing = Math.toDegrees(bearing);

        // Adjust the bearing to be in the range [0, 360)
        bearing = (bearing + 360) % 360;

        return Angle.ofDegrees(bearing);
    }

    public static Distance calcAngularDistance(GeoCoordinate startCoordinate, GeoCoordinate targetCoordinate) {
        double dLat = targetCoordinate.latitude()
                .toDegrees()
                .minus(startCoordinate.latitude())
                .getInRadians();

        double dLon = targetCoordinate.longitude()
                .toDegrees()
                .minus(startCoordinate.longitude())
                .getInRadians();

        double havLat = Math.sin(dLat / 2) * Math.sin(dLat / 2);
        double havLon = Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double lat1 = startCoordinate.latitude().getInRadians();
        double lat2 = targetCoordinate.latitude().getInRadians();

        double a = havLat + Math.cos(lat1) * Math.cos(lat2) * havLon;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS.multiply(c);
    }

    public static GeoCoordinate calcTargetCoordinate(GeoCoordinate fromCoordinate, Angle bearing, Distance distanceChange) {
        double angularDistance = distanceChange.toMeter().div(EARTH_RADIUS);
        double initialLat = fromCoordinate.latitude().getInRadians();
        double initialLon = fromCoordinate.longitude().getInRadians();

        double newLat = Math.asin(Math.sin(initialLat) * Math.cos(angularDistance) +
                Math.cos(initialLat) * Math.sin(angularDistance) * Math.cos(bearing.getInRadians()));

        double newLon = initialLon + Math.atan2(Math.sin(bearing.getInRadians()) * Math.sin(angularDistance)
                * Math.cos(initialLat), Math.cos(angularDistance) - Math.sin(initialLat) * Math.sin(newLat));

        return GeoCoordinate.builder()
                .latitude(Angle.ofRadians(newLat).toDegrees())
                .longitude(Angle.ofRadians(newLon).toDegrees())
                .build();
    }

}
