package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.util.ValueSymbolPair;

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

    /**
     * Creates a {@link Latitude} instance by parsing a coordinate string expressed
     * in <b>ICAO-compliant DMS_S</b> (Degrees–Minutes–Seconds with Symbol) format.
     * <p>
     * The input must follow the conventions defined in
     * <b>ICAO Annex 15 — Aeronautical Information Services</b>, where latitude
     * is expressed in degrees (°), minutes (′), and seconds (″) of arc,
     * followed by a <b>North (N)</b> or <b>South (S)</b> direction indicator.
     * <p>
     * This method automatically validates and parses DMS-formatted strings such as:
     * <ul>
     *   <li>{@code 52°14'05.12"N}</li>
     *   <li>{@code 52°14'5.1"S}</li>
     *   <li>{@code 52deg14min5.123secN}</li>
     * </ul>
     * Optional symbols for degrees, minutes, or seconds are supported
     * (°, o, deg, ', min, ″, sec, etc.), and the seconds component
     * may include any decimal precision (e.g., 0.01″ or higher).
     * <p>
     * If the input string is {@code null}, missing the N/S direction,
     * or does not conform to a valid DMS structure, a
     * {@link UnitSystemArgumentException} is thrown.
     *
     * <h4>Examples:</h4>
     * <pre>{@code
     * Latitude lat1 = Latitude.ofDMSFormat("52°14'05.12\"N");    // Valid, ICAO precision
     * Latitude lat2 = Latitude.ofDMSFormat("52°14'5.1\"S");      // Valid, lower precision
     * Latitude lat3 = Latitude.ofDMSFormat("52deg14min5.123secN"); // Valid, alternate format
     * Latitude.ofDMSFormat("52°14'5.1");  // X Invalid — missing direction (N/S)
     * }</pre>
     *
     * @param dmsFormat the DMS_S-formatted latitude string to parse (e.g. {@code "52°14'05.12\"N"})
     * @return a {@link Latitude} instance representing the parsed coordinate
     * @throws UnitSystemArgumentException if {@code dmsFormat} is {@code null} or malformed
     * @see GeoParsingHelpers#isDMSFormatOrSimilar(String)
     * @see GeoParsingHelpers#extractValueAndSymbolFromDMSFormat(Class, String)
     * @see DMSCoordinateFormatter
     */
    public static Latitude ofDMSFormat(String dmsFormat) {
        if (dmsFormat == null || !GeoParsingHelpers.isDMSFormatOrSimilar(dmsFormat)) {
            throw new UnitSystemArgumentException("Latitude input DMS format is invalid: " + dmsFormat);
        }
        ValueSymbolPair valueSymbolPair = GeoParsingHelpers.extractValueAndSymbolFromDMSFormat(Latitude.class, dmsFormat);
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

    // Formatted in DMS_s (degrees, minutes, seconds) format
    /**
     * Returns the latitude formatted in ICAO-compliant <b>DMS_S</b> (Degrees–Minutes–Seconds with Symbol) format.
     * <p>
     * This format follows the conventions defined in <b>ICAO Annex 15 — Aeronautical Information Services</b>, where
     * coordinates are expressed in degrees, minutes, and seconds of arc, and the seconds value is typically given
     * to a resolution of <b>0.01″ (hundredth of a second)</b>.
     * <p>
     * Example output: {@code 52°14'05.12"N}
     * <ul>
     *   <li>Degrees (°) and minutes (′) are integer values.</li>
     *   <li>Seconds (″) may include decimals depending on the chosen resolution.</li>
     *   <li>Direction (N/S for latitude, E/W for longitude) is mandatory.</li>
     * </ul>
     *
     * @return DMS_S-formatted latitude string using the default ICAO seconds resolution (0.01″)
     */
    public String toDMSsFormat() {
        return DMSCoordinateFormatter.latitudeToDMSSFormat(this, DMSCoordinateFormatter.DEFAULT_ICAO_SECONDS_RESOLUTION);
    }

    /**
     * Returns the latitude formatted in ICAO-compliant <b>DMS_S</b> format,
     * prefixed with the provided variable name.
     * <p>
     * Example output: {@code LAT = 52°14'05.12"N}
     *
     * @param variableName a label to prepend before the coordinate, typically a variable name such as "LAT" or "LONG"
     * @return formatted DMS_S coordinate string prefixed with the given variable name
     */
    public String toDMSsFormat(String variableName) {
        return variableName + " = " + toDMSsFormat();
    }

    /**
     * Returns the latitude formatted in ICAO-compliant <b>DMS_S</b> format using a custom
     * seconds resolution.
     * <p>
     * The resolution defines the rounding precision of the seconds value. For example:
     * <ul>
     *   <li>{@code secondsResolution = 0.1} → seconds rounded to 1 decimal place</li>
     *   <li>{@code secondsResolution = 0.01} → seconds rounded to 2 decimals (default ICAO precision)</li>
     *   <li>{@code secondsResolution = 1} → seconds shown as integers only</li>
     * </ul>
     * Example output with {@code secondsResolution = 0.1}: {@code 52°14'05.1"N}
     *
     * @param secondsResolutions the rounding resolution for seconds, typically {@code 0.01}
     * @return formatted DMS_S coordinate string using the given seconds resolution
     */
    public String toDMSsFormat(double secondsResolutions) {
        return DMSCoordinateFormatter.latitudeToDMSSFormat(this, secondsResolutions);
    }

    /**
     * Returns the latitude formatted in ICAO-compliant <b>DMS_S</b> format using a custom
     * seconds resolution, and prefixed with the given variable name.
     * <p>
     * Example output: {@code LAT = 52°14'05.12"N}
     *
     * @param variableName a label to prepend before the coordinate, e.g. {@code "LAT"}
     * @param secondsResolutions the rounding resolution for seconds, typically {@code 0.01}
     * @return formatted DMS_S coordinate string with variable name and custom seconds resolution
     */
    public String toDMSsFormat(String variableName, double secondsResolutions) {
        return variableName + " = " + toDMSsFormat(secondsResolutions);
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