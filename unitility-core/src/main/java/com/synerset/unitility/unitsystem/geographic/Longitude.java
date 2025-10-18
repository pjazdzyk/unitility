package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.util.ValueSymbolPair;

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
        if (unitType == null) {
            unitType = AngleUnits.DEGREES;
        }
        this.unitType = unitType;
        this.baseValue = Angle.of(value, unitType).getInDegrees();
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

    public static Longitude ofDegMinSec(int degrees, int minutes, double seconds, PrimaryDirection direction) {
        double sign = HaversineEquations.determineSign(direction.getDirectionSymbol(), degrees);
        double decimalDegrees = HaversineEquations.dmsToDegrees(degrees, minutes, seconds);
        return ofDegrees(sign * decimalDegrees);
    }

    /**
     * Creates a {@link Longitude} instance by parsing a coordinate string expressed
     * in <b>ICAO-compliant DMS_S</b> (Degrees–Minutes–Seconds with Symbol) format.
     * <p>
     * The input must follow the conventions defined in
     * <b>ICAO Annex 15 — Aeronautical Information Services</b>, where longitude
     * is expressed in degrees (°), minutes (′), and seconds (″) of arc,
     * followed by an <b>East (E)</b> or <b>West (W)</b> direction indicator.
     * <p>
     * This method automatically validates and parses DMS-formatted strings such as:
     * <ul>
     *   <li>{@code 021°04'03.98"E}</li>
     *   <li>{@code 21°4'3.9"E}</li>
     *   <li>{@code 21deg4min3.98secW}</li>
     * </ul>
     * Optional degrees, minutes, or seconds symbols are supported (°, o, deg, ', min, ″, sec, etc.).
     * The seconds component may include decimal precision (e.g., 0.01″).
     * <p>
     * If the input does not conform to a valid DMS structure or is {@code null},
     * a {@link UnitSystemArgumentException} is thrown.
     *
     * <h4>Examples:</h4>
     * <pre>{@code
     * Longitude lon1 = Longitude.ofDMSFormat("021°04'03.98\"E");  // Valid, ICAO precision
     * Longitude lon2 = Longitude.ofDMSFormat("21°4'3.9\"W");      // Valid, lower precision
     * Longitude lon3 = Longitude.ofDMSFormat("21deg4min3.98secE"); // Valid, alternate format
     * Longitude.ofDMSFormat("21°4'3.9");  // X Invalid — missing direction (E/W)
     * }</pre>
     *
     * @param dmsFormat the DMS_S-formatted longitude string to parse (e.g. {@code "021°04'03.98\"E"})
     * @return a {@link Longitude} instance representing the parsed coordinate
     * @throws UnitSystemArgumentException if {@code dmsFormat} is {@code null} or malformed
     * @see GeoParsingHelpers#isDMSFormatOrSimilar(String)
     * @see GeoParsingHelpers#extractValueAndSymbolFromDMSFormat(Class, String)
     * @see DMSCoordinateFormatter
     */
    public static Longitude ofDMSFormat(String dmsFormat) {
        if (dmsFormat == null || !GeoParsingHelpers.isDMSFormatOrSimilar(dmsFormat)) {
            throw new UnitSystemArgumentException("Longitude input DMS format is invalid: " + dmsFormat);
        }
        ValueSymbolPair valueSymbolPair = GeoParsingHelpers.extractValueAndSymbolFromDMSFormat(Longitude.class, dmsFormat);
        return of(valueSymbolPair.value(), valueSymbolPair.symbol());
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
        double valueInTargetUnit = Angle.of(value, unitType).toUnit(targetUnit).getValue();
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

    // Formatted in DMS_s (degrees, minutes, seconds) format
    /**
     * Returns the longitude formatted in ICAO-compliant <b>DMS_S</b> (Degrees–Minutes–Seconds with Symbol) format.
     * <p>
     * This format follows the conventions defined in <b>ICAO Annex 15 — Aeronautical Information Services</b>, where
     * geographical coordinates are expressed in degrees, minutes, and seconds of arc. The seconds component is
     * typically represented to a precision of <b>0.01″ (hundredth of a second)</b> by default.
     * <p>
     * Example output: {@code 021°04'03.98"E}
     * <ul>
     *   <li>Degrees (°) and minutes (′) are integer values.</li>
     *   <li>Seconds (″) may contain decimals depending on the specified resolution.</li>
     *   <li>Direction (E/W) is mandatory for longitude.</li>
     * </ul>
     *
     * @return DMS_S-formatted longitude string using the default ICAO seconds resolution (0.01″)
     */
    public String toDMSsFormat() {
        return DMSCoordinateFormatter.longitudeToDMSSFormat(this, DMSCoordinateFormatter.DEFAULT_ICAO_SECONDS_RESOLUTION);
    }

    /**
     * Returns the longitude formatted in ICAO-compliant <b>DMS_S</b> format,
     * prefixed with the provided variable name.
     * <p>
     * Example output: {@code LON = 021°04'03.98"E}
     *
     * @param variableName a label to prepend before the coordinate, typically a variable name such as "LON"
     * @return formatted DMS_S longitude string prefixed with the given variable name
     */
    public String toDMSsFormat(String variableName) {
        return variableName + " = " + toDMSsFormat();
    }

    /**
     * Returns the longitude formatted in ICAO-compliant <b>DMS_S</b> format using a custom
     * seconds resolution.
     * <p>
     * The seconds resolution defines how precisely the seconds component is rounded or displayed. For example:
     * <ul>
     *   <li>{@code secondsResolution = 0.1} → seconds rounded to one decimal place</li>
     *   <li>{@code secondsResolution = 0.01} → seconds rounded to two decimals (default ICAO precision)</li>
     *   <li>{@code secondsResolution = 1} → seconds displayed as whole numbers</li>
     * </ul>
     * Example output with {@code secondsResolution = 0.1}: {@code 021°04'03.9"E}
     *
     * @param secondsResolutions the rounding resolution for seconds, typically {@code 0.01}
     * @return formatted DMS_S longitude string using the given seconds resolution
     */
    public String toDMSsFormat(double secondsResolutions) {
        return DMSCoordinateFormatter.longitudeToDMSSFormat(this, secondsResolutions);
    }

    /**
     * Returns the longitude formatted in ICAO-compliant <b>DMS_S</b> format using a custom
     * seconds resolution, and prefixed with the provided variable name.
     * <p>
     * Example output: {@code LON = 021°04'03.98"E}
     *
     * @param variableName a label to prepend before the coordinate, e.g. {@code "LON"}
     * @param secondsResolutions the rounding resolution for seconds, typically {@code 0.01}
     * @return formatted DMS_S longitude string with variable name and custom seconds resolution
     */
    public String toDMSsFormat(String variableName, double secondsResolutions) {
        return variableName + " = " + toDMSsFormat(secondsResolutions);
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
        return Double.compare(inputQuantity.getInDegrees(), this.getInDegrees()) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
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