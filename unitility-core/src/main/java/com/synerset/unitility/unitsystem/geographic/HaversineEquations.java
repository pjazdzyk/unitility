package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.Distance;

public class HaversineEquations {

    private HaversineEquations() {
        throw new IllegalStateException("Utility class");
    }

    static final Distance MEAN_EARTH_RADIUS = Distance.ofMeters(6_371_000);

    /**
     * Calculates the bearing {@link Angle} between two geographic coordinates in range: [-180, 180]
     *
     * @param startCoordinate The starting {@link GeoCoordinate}.
     * @param targetCoordinate The target {@link GeoCoordinate}.
     * @return The bearing angle as an {@link Angle}.
     */
    static Angle calcBearing(GeoCoordinate startCoordinate, GeoCoordinate targetCoordinate) {
        double lat1 = startCoordinate.latitude().getInRadians();
        double lat2 = targetCoordinate.latitude().getInRadians();

        double lon1 = startCoordinate.longitude().getInDegrees();
        double lon2 = targetCoordinate.longitude().getInDegrees();
        double deltaLon = Math.toRadians(lon2 - lon1);

        double y = Math.sin(deltaLon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(deltaLon);

        double bearing = Math.atan2(y, x);
        bearing = Math.toDegrees(bearing);

        // Adjust the bearing to be in the range [0, 180)
        bearing = bearing % 180;

        return Angle.ofDegrees(bearing);
    }

    /**
     * Calculates the spherical {@link Distance} between two geographic coordinates using the Haversine formula.
     *
     * @param startCoordinate The starting {@link GeoCoordinate}.
     * @param targetCoordinate The target {@link GeoCoordinate}.
     * @return The spherical distance as a {@link Distance}.
     */
    static Distance calcSphericalDistance(GeoCoordinate startCoordinate, GeoCoordinate targetCoordinate) {
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

        return MEAN_EARTH_RADIUS.multiply(c);
    }

    /**
     * Calculates the target {@link GeoCoordinate} based on the initial coordinate, bearing, and distance change.
     *
     * @param fromCoordinate   The initial {@link GeoCoordinate}.
     * @param bearing          The bearing angle as an {@link Angle}.
     * @param distanceChange   The change in distance as a {@link Distance}.
     * @return The target {@link GeoCoordinate}.
     */
    static GeoCoordinate calcTargetCoordinate(GeoCoordinate fromCoordinate, Angle bearing, Distance distanceChange) {
        double distanceByEarthRadius = distanceChange.toMeter().div(MEAN_EARTH_RADIUS);
        double lat1 = fromCoordinate.latitude().getInRadians();
        double lon1 = fromCoordinate.longitude().getInRadians();
        double trueBearing = bearing.getInRadians();

        double newLat = Math.asin(Math.sin(lat1) * Math.cos(distanceByEarthRadius) +
                Math.cos(lat1) * Math.sin(distanceByEarthRadius) * Math.cos(trueBearing));

        double newLon = lon1 + Math.atan2(
                Math.sin(trueBearing) * Math.sin(distanceByEarthRadius) * Math.cos(lat1),
                Math.cos(distanceByEarthRadius) - Math.sin(lat1) * Math.sin(newLat)
        );

        double latInDegrees = Math.toDegrees(newLat);
        double lonInDegrees = Math.toDegrees(newLon);

        // Corrector algorithm to prevent invalid coordinates when crossing 180 degrees longitude
        if (lonInDegrees < -180) {
            lonInDegrees = 180 - (Math.abs(lonInDegrees)) % 180;
        } else if (lonInDegrees > 180) {
            lonInDegrees = (180 - (Math.abs(lonInDegrees)) % 180) * -1;
        } else{
            lonInDegrees = (lonInDegrees) % 180;
        }

        latInDegrees = (latInDegrees) % 90;

        return GeoCoordinate.of(Latitude.ofDegrees(latInDegrees), Longitude.ofDegrees(lonInDegrees));
    }

    /**
     * Converts degrees, minutes, seconds (DMS format) to decimal degrees.
     *
     * @param degrees the degrees component
     * @param minutes the minutes component
     * @param seconds the seconds component
     * @return the total degrees as a double
     */
    public static double dmsToDegrees(double degrees, double minutes, double seconds) {
        return Math.abs(degrees) + Math.abs(minutes) / 60.0 + Math.abs(seconds) / 3600.0;
    }

    /**
     * Determines the sign for the degrees based on the direction character.
     *
     * @param directionChar the direction character (S, W, etc.)
     * @param degrees the degrees value
     * @return the sign (-1 if S/W, 1 otherwise)
     */
    public static double determineSign(char directionChar, double degrees) {
        double sign = 1;
        if (directionChar == 'S' || directionChar == 's' || directionChar == 'W' || directionChar == 'w' || degrees < 0) {
            sign = -1;
        }
        return sign;
    }
}