package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

/**
 * Represents a geographic Earth coordinate consisting of latitude, longitude, and an optional name.
 * This class provides various methods for formatting and manipulating geographic coordinates on Earth.
 *
 * <p>The latitude and longitude values are strictly enforced within Earth's valid latitude and longitude ranges.
 * Latitude values range from -90.0 degrees (South) to 90.0 degrees (North), and longitude values range from
 * -180.0 degrees (West) to 180.0 degrees (East). Attempting to create a coordinate with latitude or longitude values
 * outside these Earth-specific limits will result in an {@link UnitSystemArgumentException} being thrown.
 *
 * @param latitude  The {@link Latitude} of the geographic coordinate.
 * @param longitude The {@link Longitude} of the geographic coordinate.
 * @param name      An optional name associated with the geographic coordinate.
 */
public record GeoCoordinate(Latitude latitude, Longitude longitude, String name) {

    /**
     * Constructs a GeoCoordinate object with the given latitude, longitude, and name.
     * Validates the latitude and longitude values.
     *
     * @param latitude  The {@link Latitude} of the geographic coordinate.
     * @param longitude The {@link Longitude} of the geographic coordinate.
     * @param name      An optional name associated with the geographic coordinate.
     * @throws UnitSystemArgumentException If the latitude or longitude is outside the valid range.
     */
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

    // Console output in DMS (degrees, minutes, seconds) format

    /**
     * Returns the geographic coordinate in Degrees, Minutes, Seconds (DMS) format.
     * Example: 52°14'5.12345"N, -10°13'2.12345"W
     *
     * @return The geographic coordinate in DMS format (latitude, longitude).
     */
    public String toDMSFormat() {
        return latitude.toDMSFormat() + ", " + longitude.toDMSFormat();
    }

    /**
     * Returns the geographic coordinate in Degrees, Minutes, Seconds (DMS) format with a custom variable name.
     * Example: variable = 52°14'5.12345"N, -10°13'2.12345"W
     *
     * @param variableName The variable name to be used in the output.
     * @return The geographic coordinate in DMS format with the specified variable name.
     */
    public String toDMSFormat(String variableName) {
        return variableName + " = " + toDMSFormat();
    }

    /**
     * Returns the geographic coordinate in Degrees, Minutes, Seconds (DMS) format with a specified number of relevant digits.
     * Example, for relevant digits = 2: 52°14'5.12"N, -10°13'2.12"W.
     *
     * @param relevantDigits The number of relevant digits to include in the output.
     * @return The geographic coordinate in DMS format with the specified number of relevant digits.
     */
    public String toDMSFormat(int relevantDigits) {
        return latitude.toDMSFormat(relevantDigits) + ", " + longitude.toDMSFormat(relevantDigits);
    }

    /**
     * Returns the geographic coordinate in Degrees, Minutes, Seconds (DMS) format with a custom variable name and a specified number of relevant digits.
     * Example, for relevant digits = 2: variable = 52°14'5.12"N, -10°13'2.12"W.
     *
     * @param variableName   The variable name to be used in the output.
     * @param relevantDigits The number of relevant digits to include in the output.
     * @return The geographic coordinate in DMS format with the specified variable name and number of relevant digits.
     */
    public String toDMSFormat(String variableName, int relevantDigits) {
        return variableName + " = " + toDMSFormat(relevantDigits);
    }

    // Console output in decimal degrees format, Google Maps outputs coords this way\

    /**
     * Returns the geographic coordinate in decimal degrees format. This is how Google outputs coordinates,
     * when you click on some location on the map.
     * Example: 52.12345, -10.12345
     *
     * @return The geographic coordinate in decimal degrees format (latitude, longitude).
     */
    public String toDecimalDegrees() {
        return latitude.getInDegrees() + ", " + longitude.getInDegrees();
    }

    /**
     * Returns the geographic coordinate in decimal degrees format with a custom variable name.
     * Example: variable = 52.12345, -10.12345
     *
     * @param variableName The variable name to be used in the output.
     * @return The geographic coordinate in decimal degrees format with the specified variable name.
     */
    public String toDecimalDegrees(String variableName) {
        return variableName + " = " + toDecimalDegrees();
    }

    /**
     * Returns the geographic coordinate in decimal degrees format with a specified number of relevant digits.
     * Example, for two relevant digits: 52.12, -10.12
     *
     * @param relevantDigits The number of relevant digits to include in the output.
     * @return The geographic coordinate in decimal degrees format with the specified number of relevant digits.
     */
    public String toDecimalDegrees(int relevantDigits) {
        return ValueFormatter.toStringWithRelevantDigits(latitude.getInDegrees(), relevantDigits) + ", " +
                ValueFormatter.toStringWithRelevantDigits(longitude.getInDegrees(), relevantDigits);
    }

    /**
     * Returns the geographic coordinate in decimal degrees format with a custom variable name and a specified number of relevant digits.
     * Example, for two relevant digits: variable = 52.12, -10.12
     *
     * @param variableName   The variable name to be used in the output.
     * @param relevantDigits The number of relevant digits to include in the output.
     * @return The geographic coordinate in decimal degrees format with the specified variable name and number of relevant digits.
     */
    public String toDecimalDegrees(String variableName, int relevantDigits) {
        return variableName + " = " + toDecimalDegrees(relevantDigits);
    }

    // Console output in engineering format
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

    private void validateLatitude(Latitude latitude) {
        if (latitude.isGreaterThan(Latitude.MAX_EARTH_LATITUDE) || latitude.isLowerThan(Latitude.MIN_EARTH_LATITUDE)) {
            throw new UnitSystemArgumentException("Invalid latitude value = " + latitude + " Allowed range: "
                    + Latitude.MIN_EARTH_LATITUDE.toEngineeringFormat() + " to " + Latitude.MAX_EARTH_LATITUDE.toEngineeringFormat());
        }
    }

    private void validateLongitude(Longitude longitude) {
        if (longitude.isGreaterThan(Longitude.MAX_EARTH_LONGITUDE) || longitude.isLowerThan(Longitude.MIN_EARTH_LONGITUDE)) {
            throw new UnitSystemArgumentException("Invalid longitude value = " + longitude + ". Allowed range: "
                    + Longitude.MIN_EARTH_LONGITUDE.toEngineeringFormat() + " to " + Longitude.MAX_EARTH_LONGITUDE.toEngineeringFormat());
        }
    }

    public boolean equalsWithPrecision(GeoCoordinate inputGeoCoordinate, double epsilon) {
        if (this == inputGeoCoordinate) {
            return true;
        }
        if (inputGeoCoordinate == null) {
            return false;
        }
        return latitude.equalsWithPrecision(inputGeoCoordinate.latitude, epsilon)
                && longitude.equalsWithPrecision(inputGeoCoordinate.longitude, epsilon);
    }

    /**
     * Converts the GeoCoordinate to its base unit representation (decimal degrees).
     *
     * @return A new GeoCoordinate instance with latitude and longitude converted to base units.
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