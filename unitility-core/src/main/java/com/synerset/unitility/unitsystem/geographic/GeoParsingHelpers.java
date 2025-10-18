package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.ParsingHelpers;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.util.ValueSymbolPair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeoParsingHelpers {

    private static final String DMS_FLEXIBLE_PATTERN =
            "^" +
                    "-?\\d{1,3}(?:°|o|deg)" +             // degrees
                    "(?:" +
                    "\\d{1,2}(?:'|min|m)" +               // optional minutes
                    "(?:" +
                    "\\d{1,2}(?:\\.\\d+)?(?:\"|sec|s)?" + // optional seconds with any decimal precision
                    ")?" +
                    ")?" +
                    "[NSEWnsew]?$";                       // optional direction

    public static boolean isDMSFormatOrSimilar(String stringCoordinate) {
        Pattern regex = Pattern.compile(DMS_FLEXIBLE_PATTERN);
        Matcher matcher = regex.matcher(stringCoordinate);
        return matcher.matches();
    }

    public static ValueSymbolPair extractValueAndSymbolFromDMSFormat(Class<?> targetClass, String partiallyPreparedInput) {
        String preparedInput = StringTransformer.of(partiallyPreparedInput)
                .unifyDMSNotationSymbols()
                .toString();

        if (Latitude.class.isAssignableFrom(targetClass) && (preparedInput.contains("e") || preparedInput.contains("w"))) {
            throw new UnitSystemParseException("Invalid latitude direction. Expected: N or S. Input: " + preparedInput);
        } else if (Longitude.class.isAssignableFrom(targetClass) && (preparedInput.contains("n") || preparedInput.contains("s"))) {
            throw new UnitSystemParseException("Invalid longitude direction. Expected: W or E. Input: " + preparedInput);
        }

        double valueInDegrees = convertDMSExpressionToDecimalDegrees(preparedInput);
        return new ValueSymbolPair(valueInDegrees, AngleUnits.DEGREES.getSymbol());
    }

    /**
     * Extracts the degrees value from a string in degrees-minutes-seconds format.
     *
     * @param dmsFormat the string to parse in degrees-minutes-seconds format
     * @return the degrees value as a double
     * @throws UnitSystemArgumentException if dmsFormat is invalid
     */
    public static double convertDMSExpressionToDecimalDegrees(String dmsFormat) {
        if (dmsFormat == null || dmsFormat.isBlank()) {
            throw new UnitSystemArgumentException("Geo parser: Invalid input. Argument cannot be null or blank.");
        }

        String[] parts = dmsFormat.split("[o°'\"nsew]");

        if (parts.length == 0) {
            throw new UnitSystemArgumentException("Geo DMS parser: Input string could not be parsed: input = "
                    + dmsFormat);
        }

        double degrees = ParsingHelpers.parseToDouble(parts[0]);

        double minutes = 0;
        if (parts.length > 1) {
            minutes = ParsingHelpers.parseToDouble(parts[1]);
        }

        double seconds = 0;
        if (parts.length > 2) {
            seconds = ParsingHelpers.parseToDouble(parts[2]);
        }

        char directionChar = dmsFormat.charAt(dmsFormat.length() - 1);
        double sign = HaversineEquations.determineSign(String.valueOf(directionChar), degrees);

        return sign * HaversineEquations.dmsToDegrees(degrees, minutes, seconds);
    }

}
