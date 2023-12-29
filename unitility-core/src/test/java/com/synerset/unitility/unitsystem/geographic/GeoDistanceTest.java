package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.Distance;
import com.synerset.unitility.unitsystem.common.DistanceUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class GeoDistanceTest {

    @Test
    @DisplayName("should create geo distance instance")
    void geoDistance_shouldCreateInstance() {
        // Given
        GeoCoordinate start = GeoCoordinate.of(Latitude.ofDegrees(20), Longitude.ofDegrees(-20));
        GeoCoordinate destination = GeoCoordinate.of(Latitude.ofDegrees(-20), Longitude.ofDegrees(20));

        // When
        GeoDistance geoDistanceMeters = GeoDistance.ofMeters(start, destination);
        GeoDistance geoDistanceKiloMeters = GeoDistance.ofKilometers(start, destination);
        GeoDistance geoDistanceMiles = GeoDistance.ofMiles(start, destination);
        GeoDistance geoDistanceNauticalMiles = GeoDistance.ofNauticalMiles(start, destination);

        // Then
        assertThat(geoDistanceMeters.getDistance()).isEqualTo(Distance.ofMeters(6224890.080159444));
        assertThat(geoDistanceKiloMeters.getDistance()).isEqualTo(Distance.ofKilometers(6224.890080159445));
        assertThat(geoDistanceMiles.getDistance()).isEqualTo(Distance.ofMiles(3867.9673706550271414));
        assertThat(geoDistanceNauticalMiles.getDistance()).isEqualTo(Distance.ofNauticalMiles(3361.171749546136));
        assertThat(geoDistanceMeters.toNauticalMile().getValue()).isEqualTo(3361.171749546136);

    }

    @Test
    @DisplayName("should properly convert between supported units")
    void geoDistance_ShouldSuccessfullyConvertBetweenSupportedUnit() {
        // Given
        GeoCoordinate wroclaw = GeoCoordinate.of(Latitude.ofDegrees(51.102772), Longitude.ofDegrees(16.885802));
        GeoCoordinate newYork = GeoCoordinate.of(Latitude.ofDegrees(40.712671), Longitude.ofDegrees(-74.004655));

        // When
        GeoDistance actualGeoDistance = GeoDistance.of(wroclaw, newYork, DistanceUnits.METER);
        GeoDistance actualInMeters = actualGeoDistance
                .toKilometer()
                .toMile()
                .toMeter();

        // Then
        assertThat(actualGeoDistance).isEqualTo(actualInMeters);
        assertThat(actualInMeters.getInKilometers()).isEqualTo(6669.896095258197);
        assertThat(actualInMeters.getInMiles()).isEqualTo(4144.481288809725);
        assertThat(actualInMeters.getInNauticalMiles()).isEqualTo(3601.4557749774285);

    }

    @Test
    @DisplayName("should create GeoDistance instance and calculate distance")
    void geoDistance_shouldCreateInstanceAndProperlyRepresentDistance() {
        // Given
        GeoCoordinate wroclaw = GeoCoordinate.of(Latitude.ofDegrees(51.102772), Longitude.ofDegrees(16.885802));
        GeoCoordinate newYork = GeoCoordinate.of(Latitude.ofDegrees(40.712671), Longitude.ofDegrees(-74.004655));

        // When
        GeoDistance curvedRouteDistance = GeoDistance.of(wroclaw, newYork, DistanceUnits.KILOMETER);

        // Then
        assertThat(curvedRouteDistance.getInMeters()).isEqualTo(6669896.095258198);
        assertThat(curvedRouteDistance.getInKilometers()).isEqualTo(6669.896095258197);
    }

    @Test
    @DisplayName("should create new GeoDistance from the same staring point and new target")
    void geoDistance_shouldCreateInstanceAFromTheSameStartingPoint() {
        // Given
        GeoCoordinate wroclaw = GeoCoordinate.of(Latitude.ofDegrees(51.102772), Longitude.ofDegrees(16.885802));
        GeoCoordinate newYork = GeoCoordinate.of(Latitude.ofDegrees(40.712671), Longitude.ofDegrees(-74.004655));
        GeoCoordinate wellington = GeoCoordinate.of(Latitude.ofDegrees(-41.289463), Longitude.ofDegrees(174.774913));

        // When
        GeoDistance travelDistance = GeoDistance.of(wroclaw, newYork, DistanceUnits.KILOMETER);
        System.out.println(travelDistance.getTrueBearing());
        GeoDistance changedRoute = travelDistance.with(wellington);
        System.out.println(changedRoute.getTrueBearing());

        // Then
        assertThat(changedRoute.getInKilometers()).isEqualTo(18005.619822652574);
    }

    @Test
    @DisplayName("should create new GeoDistance from the same staring point in bearing by distance")
    void geoDistance_shouldCreateInstanceAFromTheSameStartingPointInBearingByDistance() {
        // Given
        GeoCoordinate wroclaw = GeoCoordinate.of(Latitude.ofDegrees(51.102772), Longitude.ofDegrees(16.885802));
        GeoCoordinate newYork = GeoCoordinate.of(Latitude.ofDegrees(40.712671), Longitude.ofDegrees(-74.004655));
        GeoCoordinate wellington = GeoCoordinate.of(Latitude.ofDegrees(-41.289463), Longitude.ofDegrees(174.774913));

        // When
        GeoDistance travelDistance = GeoDistance.of(wroclaw, newYork, DistanceUnits.KILOMETER);
        GeoDistance changedRoute = travelDistance.with(Angle.ofDegrees(65.74497889266877), Distance.ofKilometers(18005.619822652574));
        GeoCoordinate actualStartCoordinate = changedRoute.getStartCoordinate();
        GeoCoordinate actualTargetCoordinate = changedRoute.getTargetCoordinate();

        // Then
        assertThat(actualStartCoordinate).isEqualTo(wroclaw);
        assertThat(actualTargetCoordinate.equalsWithPrecision(wellington, 1E-13)).isTrue();
        assertThat(changedRoute.getTrueBearing()).isEqualTo(Angle.ofDegrees(65.74497889266877));
        assertThat(changedRoute.getDistance()).isEqualTo(Distance.ofKilometers(18005.619822652574));
        assertThat(changedRoute.toMile().getDistance()).isEqualTo(Distance.ofMiles(11188.173456173803));
    }

    @Test
    @DisplayName("should translate from current target to new destination coordinates")
    void geoDistance_shouldTranslateFromCurrentTargetToNewDestinationCoordinates() {
        // Given
        GeoCoordinate wroclaw = GeoCoordinate.of(Latitude.ofDegrees(51.102772), Longitude.ofDegrees(16.885802), "wrocław");
        GeoCoordinate newYork = GeoCoordinate.of(Latitude.ofDegrees(40.712671), Longitude.ofDegrees(-74.004655), "new york");
        GeoCoordinate wellington = GeoCoordinate.of(Latitude.ofDegrees(-41.289463), Longitude.ofDegrees(174.774913), "wellington");

        // When
        GeoDistance travelDistance = GeoDistance.of(wroclaw, newYork, DistanceUnits.KILOMETER);
        GeoDistance changedRoute = travelDistance.translate(wellington);
        Angle trueBearing = changedRoute.getTrueBearing();
        System.out.println(trueBearing);
        System.out.println(changedRoute.getDistance());
        System.out.println(changedRoute.getStartCoordinate());
        System.out.println(changedRoute.getTargetCoordinate());

        // Then
        assertThat(changedRoute.getInKilometers()).isEqualTo(14403.693472915033);
    }

    @Test
    @DisplayName("should translate from current target to new destination in bearing by distance")
    void geoDistance_shouldTranslateFromCurrentTargetInBearingByDistance() {
        // Given
        GeoCoordinate wroclaw = GeoCoordinate.of(Latitude.ofDegrees(51.102772), Longitude.ofDegrees(16.885802));
        GeoCoordinate newYork = GeoCoordinate.of(Latitude.ofDegrees(40.712671), Longitude.ofDegrees(-74.004655));
        GeoCoordinate wellington = GeoCoordinate.of(Latitude.ofDegrees(-41.289463), Longitude.ofDegrees(174.774913));
        GeoCoordinate prague = GeoCoordinate.of(Latitude.ofDegrees(50.052494), Longitude.ofDegrees(14.486350), "prague");

        // When
        GeoDistance travelDistance = GeoDistance.of(wroclaw, newYork, DistanceUnits.KILOMETER);
        GeoDistance changedRoute = travelDistance.translate(Angle.ofDegrees(-114.74106142958847),
                Distance.ofKilometers(14403.693472915034));
        GeoCoordinate actualStartCoordinate = changedRoute.getStartCoordinate();
        GeoCoordinate actualTargetCoordinate = changedRoute.getTargetCoordinate();

        // Then
        assertThat(actualStartCoordinate).isEqualTo(newYork);
        System.out.println(actualTargetCoordinate.toDecimalDegrees());
        assertThat(actualTargetCoordinate.equalsWithPrecision(wellington, 1E-13)).isTrue();
        assertThat(changedRoute.getTrueBearing()).isEqualTo(Angle.ofDegrees(-114.74106142958847));
        assertThat(changedRoute.getDistance()).isEqualTo(Distance.ofKilometers(14403.693472915034));
    }

    @Test
    @DisplayName("should cross prime meridian and equator and back to the same place with minimal accuracy loss")
    void geoDistance_shouldCrossPrimeMedianAndEquatorAndBackToTheSamePoint() {
        // Given
        GeoCoordinate wroclaw = GeoCoordinate.of(
                Latitude.ofDegrees(51.102772),
                Longitude.ofDegrees(16.885802),
                "Wroclaw");
        GeoCoordinate newYork = GeoCoordinate.of(
                Latitude.ofDegrees(40.712671),
                Longitude.ofDegrees(-74.004655),
                "NewYork");
        GeoCoordinate santiago = GeoCoordinate.of(
                Latitude.ofDegrees(-33.47732),
                Longitude.ofDegrees(-70.6893480),
                "Santiago");
        GeoCoordinate wellington = GeoCoordinate.of(
                Latitude.ofDegrees(-41.289463),
                Longitude.ofDegrees(174.774913),
                "Wellington");

        // When // Then
        // First route, from Wroclaw to NewYork
        GeoDistance routeWroToNY = GeoDistance.of(wroclaw, newYork, DistanceUnits.KILOMETER);
        assertThat(routeWroToNY.getTargetCoordinate()).isEqualTo(newYork);

        // Route from NewYork to Santiago
        Angle bearingToSantiago = HaversineEquations.calcBearing(newYork, santiago);
        Distance distanceToSantiago = HaversineEquations.calcSphericalDistance(newYork, santiago);
        GeoDistance routeNySantg = routeWroToNY.translate(bearingToSantiago, distanceToSantiago);
        assertThat(routeNySantg.getTargetCoordinate().equalsWithPrecision(santiago, 1E-13)).isTrue();

        // Route back from Sanitago to New York
        Angle bearingBackToNyFromSant = HaversineEquations.calcBearing(santiago, newYork);
        Distance distanceBackToNYFromSant = HaversineEquations.calcSphericalDistance(santiago, newYork);
        GeoDistance routeToNyFromSant = routeNySantg.translate(bearingBackToNyFromSant, distanceBackToNYFromSant);
        assertThat(routeToNyFromSant.getTargetCoordinate().equalsWithPrecision(newYork, 1E-13)).isTrue();

        // Route from Santiago to Wellington
        Angle bearingToWellington = HaversineEquations.calcBearing(santiago, wellington);
        Distance distanceWellington = HaversineEquations.calcSphericalDistance(santiago, wellington);
        GeoDistance routeSantWell = routeNySantg.translate(bearingToWellington, distanceWellington);
        assertThat(routeSantWell.getTargetCoordinate().equalsWithPrecision(wellington, 1E-13)).isTrue();

        // Route back from Wellington to Santiago
        Angle bearingToBackToSantiago = HaversineEquations.calcBearing(wellington, santiago);
        Distance distanceBackToSantiago = HaversineEquations.calcSphericalDistance(wellington, santiago);
        GeoDistance routeBackToStg = routeSantWell.translate(bearingToBackToSantiago, distanceBackToSantiago);
        assertThat(routeBackToStg.getTargetCoordinate().equalsWithPrecision(santiago, 1E-13)).isTrue();

        // Route from Wellington to Wroclaw
        Angle bearingToWroclaw = HaversineEquations.calcBearing(wellington, wroclaw);
        Distance distanceWroclaw = HaversineEquations.calcSphericalDistance(wellington, wroclaw);
        GeoDistance routeWellWro = routeSantWell.translate(bearingToWroclaw, distanceWroclaw);
        assertThat(routeWellWro.getTargetCoordinate().equalsWithPrecision(wroclaw, 1E-13)).isTrue();

        // Route from wellington to NewYork
        Angle bearingToNewYork = HaversineEquations.calcBearing(wellington, newYork);
        Distance distanceToNewYork = HaversineEquations.calcSphericalDistance(wellington, newYork);
        GeoDistance routeWellNy = routeSantWell.translate(bearingToNewYork, distanceToNewYork);
        assertThat(routeWellNy.getTargetCoordinate().equalsWithPrecision(newYork, 1E-13)).isTrue();
    }

    @Test
    @DisplayName("should properly translate from 0,90,-90,180,-180")
    void geoDistance_shouldTranslateCorrectlyFromCoordinateLimits() {
        // Given
        GeoCoordinate start = GeoCoordinate.of(Latitude.ofDegrees(0), Longitude.ofDegrees(0));
        GeoCoordinate destination = GeoCoordinate.of(Latitude.ofDegrees(0), Longitude.ofDegrees(180));
        GeoDistance firstDistance = GeoDistance.ofKilometers(start, destination);
        assertThat(firstDistance.getDistance()).isEqualTo(Distance.ofKilometers(20015.086796020572));

        // Traveling along the equator by half of the estimated earth perimeter
        GeoDistance circleHalfEarth = firstDistance.translate(Distance.ofKilometers(40075.017 / 2.0));
        assertThat(circleHalfEarth.getTargetCoordinate().latitude().getInDegrees())
                .isEqualTo(0, withPrecision(1E-13));
        assertThat(circleHalfEarth.getTargetCoordinate().longitude().getInDegrees())
                .isEqualTo(0, withPrecision(0.22));

        // Traveling along the equator by full of the estimated earth perimeter
        GeoDistance fullEarthCircle = firstDistance.translate(Distance.ofKilometers(40075.017));
        assertThat(fullEarthCircle.getTargetCoordinate().latitude().getInDegrees())
                .isEqualTo(0, withPrecision(1E-13));
        assertThat(fullEarthCircle.getTargetCoordinate().longitude().getInDegrees())
                .isEqualTo(-180, withPrecision(0.59));

        // Setting new starting distance
        GeoCoordinate newStart = GeoCoordinate.of(Latitude.ofDegrees(0), Longitude.ofDegrees(0));
        GeoCoordinate newDestination = GeoCoordinate.of(Latitude.ofDegrees(0), Longitude.ofDegrees(0));
        GeoDistance newDistance = GeoDistance.ofKilometers(newStart, newDestination);
        assertThat(newDistance.getDistance()).isEqualTo(Distance.ofKilometers(0));

        // Traveling along the prime meridian by half of the estimated earth perimeter
        GeoDistance halfEarthCircleMeridian = newDistance.translate(Distance.ofKilometers(40075.017 / 2.0));
        assertThat(halfEarthCircleMeridian.getDistance().getInKilometers()).isEqualTo(40075.017 / 2.0);
        assertThat(halfEarthCircleMeridian.getTargetCoordinate().latitude().getInDegrees())
                .isEqualTo(0, withPrecision(0.21));
        assertThat(halfEarthCircleMeridian.getTargetCoordinate().longitude().getInDegrees())
                .isEqualTo(0, withPrecision(1E-13));

        // Traveling along the prime meridian by quarter of the estimated earth perimeter
        GeoDistance quarterEarthCircleMeridian = newDistance.translate(Distance.ofKilometers(40075.017 / 4.0));
        assertThat(quarterEarthCircleMeridian.getDistance().getInKilometers()).isEqualTo(40075.017 / 4.0);
        assertThat(quarterEarthCircleMeridian.getTargetCoordinate().latitude().getInDegrees())
                .isEqualTo(90, withPrecision(0.21));
        assertThat(quarterEarthCircleMeridian.getTargetCoordinate().longitude().getInDegrees())
                .isEqualTo(0, withPrecision(1E-13));

        // Traveling along the prime meridian by full of the estimated earth perimeter
        GeoDistance fullEarthCircleMeridian = newDistance.translate(Distance.ofKilometers(40075.017));
        assertThat(fullEarthCircleMeridian.getDistance().getInKilometers()).isEqualTo(40075.017);
        assertThat(fullEarthCircleMeridian.getTargetCoordinate().latitude().getInDegrees())
                .isEqualTo(0, withPrecision(0.41));
        assertThat(fullEarthCircleMeridian.getTargetCoordinate().longitude().getInDegrees())
                .isEqualTo(0, withPrecision(1E-13));


    }

    @Test
    @DisplayName("should correctly handle arithmetics and adjust target coordinate")
    void geoDistance_shouldCorrectlyHandleArithmeticAndAdjustTargetCoordinate() {
        // Given
        GeoCoordinate start = GeoCoordinate.of(Latitude.ofDegrees(0), Longitude.ofDegrees(0));
        GeoCoordinate destination = GeoCoordinate.of(Latitude.ofDegrees(0), Longitude.ofDegrees(180));
        GeoDistance geoDistance = GeoDistance.ofKilometers(start, destination);
        assertThat(geoDistance.getDistance()).isEqualTo(Distance.ofKilometers(20015.086796020572));

        // When
        // Adding a value
        GeoDistance increasedDistance = geoDistance.plus(1000);
        assertThat(increasedDistance.getDistance()).isEqualTo(Distance.ofKilometers(21015.086796020572));
        assertThat(increasedDistance.getTargetCoordinate().longitude().getInDegrees())
                .isEqualTo(-171, withPrecision(0.01));
    }

}