package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.util.ValueFormatter;

import java.util.Objects;

/**
 * Represents a geographic Earth coordinate consisting of latitude, longitude, and an optional name.
 * Provides methods for formatting and manipulating geographic coordinates in both
 * decimal and ICAO-compliant DMS<sub>S</sub> (Degrees–Minutes–Seconds with rounded seconds) formats.
 *
 * <p>Latitude and longitude are validated against the Earth’s valid ranges:
 * latitude ∈ [-90°, +90°], longitude ∈ [-180°, +180°].
 * Values outside these limits throw a {@link UnitSystemArgumentException}.
 *
 * <p><b>Note:</b> The DMS<sub>S</sub> format implemented here follows
 * <em>ICAO Doc 9674 (WGS-84 Manual)</em>, § 2.5.1 —
 * <q>the resolution is always a <b>rounded value</b> as opposed to a truncated value.</q>
 *
 * @param latitude  The {@link Latitude} of this coordinate.
 * @param longitude The {@link Longitude} of this coordinate.
 * @param name      Optional descriptive name for this coordinate.
 */
public record GeoCoordinate(Latitude latitude, Longitude longitude, String name) {

    public GeoCoordinate {
        validateLatitude(latitude);
        validateLongitude(longitude);
    }

    public static GeoCoordinate of(Latitude latitude, Longitude longitude, String name) {
        return new GeoCoordinate(latitude, longitude, name);
    }

    public static GeoCoordinate of(Latitude latitude, Longitude longitude) {
        return new GeoCoordinate(latitude, longitude, null);
    }

    public static GeoCoordinate ofDMSFormat(String latitudeDMS, String longitudeDMS, String name) {
        Latitude latitude = Latitude.ofDMSFormat(latitudeDMS);
        Longitude longitude = Longitude.ofDMSFormat(longitudeDMS);
        return new GeoCoordinate(latitude, longitude, name);
    }

    public static GeoCoordinate ofDMSFormat(String latitudeDMS, String longitudeDMS) {
        Latitude latitude = Latitude.ofDMSFormat(latitudeDMS);
        Longitude longitude = Longitude.ofDMSFormat(longitudeDMS);
        return new GeoCoordinate(latitude, longitude, null);
    }

    // ICAO DMS_S FORMAT  (Degrees–Minutes–Seconds with defined seconds resolution)
    /**
     * Returns the coordinate in ICAO-compliant DMS<sub>S</sub> format
     * (Degrees–Minutes–Seconds with rounded seconds), using the default ICAO seconds resolution 0.01″.
     *
     * <p>Example:
     * <pre>
     * 52°14'05.12"N, 010°13'02.12"W
     * </pre>
     *
     * @return Coordinate in ICAO DMS<sub>S</sub> format (lat, lon).
     */
    public String toDMSsFormat() {
        return latitude.toDMSsFormat() + ", " + longitude.toDMSsFormat();
    }

    /**
     * Returns the coordinate in ICAO DMS<sub>S</sub> format with a variable label.
     * <p>Example:
     * <pre>
     * pointA = 52°14'05.12"N, 010°13'02.12"W
     * </pre>
     *
     * @param variableName Label to prepend.
     * @return Labeled coordinate in ICAO DMS<sub>S</sub> format.
     */
    public String toDMSsFormat(String variableName) {
        return variableName + " = " + toDMSsFormat();
    }

    /**
     * Returns the coordinate in ICAO DMS<sub>S</sub> format with a custom seconds resolution.
     *
     * <p>Resolution defines the smallest step between consecutive second values (e.g. 0.01, 0.1, 1.0).
     * Seconds are <b>rounded</b>, not truncated, per ICAO Doc 9674 § 2.5.1.
     *
     * <p>Examples:</p>
     * <ul>
     *   <li><code>secondsResolution = 0.01</code> → 52°14'05.12"N, 010°13'02.12"W</li>
     *   <li><code>secondsResolution = 1.0</code>  → 52°14'05"N, 010°13'02"W</li>
     * </ul>
     *
     * @param secondsResolution Seconds resolution (> 0 → custom; ≤ 0 → defaults to 0.01).
     * @return Coordinate in ICAO DMS<sub>S</sub> format with specified resolution.
     */
    public String toDMSsFormat(double secondsResolution) {
        return latitude.toDMSsFormat(secondsResolution) + ", " + longitude.toDMSsFormat(secondsResolution);
    }

    /**
     * Returns the coordinate in ICAO DMS<sub>S</sub> format with both label and seconds resolution.
     * <p>Example:
     * <pre>
     * pointA = 52°14'05.12"N, 010°13'02.12"W
     * </pre>
     *
     * @param variableName      Label to prepend.
     * @param secondsResolution Seconds resolution (e.g. 0.01, 0.1, 1.0).
     * @return Labeled coordinate in ICAO DMS<sub>S</sub> format.
     */
    public String toDMSsFormat(String variableName, double secondsResolution) {
        return variableName + " = " + toDMSsFormat(secondsResolution);
    }

    // DECIMAL DEGREES FORMAT (e.g. Google Maps)
    /**
     * Returns the coordinate in decimal-degrees format.
     * Example: {@code 52.12345, -10.12345}
     */
    public String toDecimalDegrees() {
        return latitude.getInDegrees() + ", " + longitude.getInDegrees();
    }

    public String toDecimalDegrees(String variableName) {
        return variableName + " = " + toDecimalDegrees();
    }

    public String toDecimalDegrees(int relevantDigits) {
        return ValueFormatter.toStringWithRelevantDigits(latitude.getInDegrees(), relevantDigits) + ", " +
                ValueFormatter.toStringWithRelevantDigits(longitude.getInDegrees(), relevantDigits);
    }

    public String toDecimalDegrees(String variableName, int relevantDigits) {
        return variableName + " = " + toDecimalDegrees(relevantDigits);
    }

    // ENGINEERING FORMAT
    public String toEngineeringFormat() {
        return latitude.toEngineeringFormat() + ", " + longitude.toEngineeringFormat();
    }

    public String toEngineeringFormat(String variableName) {
        return variableName + " = " + toEngineeringFormat();
    }

    public String toEngineeringFormat(int relevantDigits) {
        return latitude.toEngineeringFormat(relevantDigits) + ", " + longitude.toEngineeringFormat(relevantDigits);
    }

    public String toEngineeringFormat(String variableName, int relevantDigits) {
        return variableName + " = " + toEngineeringFormat(relevantDigits);
    }

    // VALIDATION / EQUALITY
    private void validateLatitude(Latitude latitude) {
        if (latitude.isGreaterThan(Latitude.MAX_EARTH_LATITUDE) ||
                latitude.isLowerThan(Latitude.MIN_EARTH_LATITUDE)) {
            throw new UnitSystemArgumentException("Invalid latitude value = " + latitude +
                    ". Allowed range: " + Latitude.MIN_EARTH_LATITUDE.toEngineeringFormat() +
                    " to " + Latitude.MAX_EARTH_LATITUDE.toEngineeringFormat());
        }
    }

    private void validateLongitude(Longitude longitude) {
        if (longitude.isGreaterThan(Longitude.MAX_EARTH_LONGITUDE) ||
                longitude.isLowerThan(Longitude.MIN_EARTH_LONGITUDE)) {
            throw new UnitSystemArgumentException("Invalid longitude value = " + longitude +
                    ". Allowed range: " + Longitude.MIN_EARTH_LONGITUDE.toEngineeringFormat() +
                    " to " + Longitude.MAX_EARTH_LONGITUDE.toEngineeringFormat());
        }
    }

    public boolean equalsWithPrecision(GeoCoordinate inputGeoCoordinate, double epsilon) {
        if (this == inputGeoCoordinate) return true;
        if (inputGeoCoordinate == null) return false;
        return latitude.isEqualWithPrecision(inputGeoCoordinate.latitude, epsilon)
                && longitude.isEqualWithPrecision(inputGeoCoordinate.longitude, epsilon);
    }

    /**
     * Converts this GeoCoordinate to its base-unit representation (decimal degrees).
     *
     * @return New {@link GeoCoordinate} in decimal degrees.
     */
    public GeoCoordinate toBaseUnit() {
        return GeoCoordinate.of(latitude.toBaseUnit(), longitude.toBaseUnit());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoCoordinate that = (GeoCoordinate) o;
        return Objects.equals(latitude.toBaseUnit(), that.latitude.toBaseUnit())
                && Objects.equals(longitude.toBaseUnit(), that.longitude.toBaseUnit())
                && Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude.toBaseUnit(), longitude.toBaseUnit(), name);
    }
}
