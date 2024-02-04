package com.synerset.unitility.unitsystem.util;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.geographic.HaversineEquations;

public class ParsingHelpers {

    private ParsingHelpers() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Checks if the given input string contains any non-digit characters.
     *
     * @param inputString The string to check
     * @return True if the string contains any non-digit characters, false otherwise
     */
    public static boolean containsNonDigitChars(String inputString) {
        for (char nextChar : inputString.toCharArray()) {
            if (Character.isAlphabetic(nextChar)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parses the specified string to a double value.
     *
     * @param doubleAsString The string representation of the double
     * @param addedMessage   An additional message to be included in the exception
     *                       message if parsing fails
     * @return The parsed double value
     * @throws UnitSystemParseException If the input string cannot be parsed to a
     *                                  double, an exception is thrown with a descriptive error message
     */
    public static double parseToDouble(String doubleAsString, String addedMessage) {
        try {
            return Double.parseDouble(doubleAsString);
        } catch (NumberFormatException ex) {
            throw new UnitSystemParseException(addedMessage + " Invalid input, could not parse to double, input = "
                    + doubleAsString);
        }
    }

    /**
     * Parses the specified string to a double value. Calls the {@code
     * parseToDouble} method with an empty additional message.
     *
     * @param doubleAsString The string representation of the double
     * @return The parsed double value
     * @throws UnitSystemParseException If the input string cannot be parsed to a
     *                                  double, an exception is thrown with a descriptive error message. The
     *                                  additional message is empty in this case.
     */
    public static double parseToDouble(String doubleAsString) {
        return parseToDouble(doubleAsString, "");
    }

    /**
     * Extracts the degrees value from a string in degrees-minutes-seconds format.
     *
     * @param dmsFormat the string to parse in degrees-minutes-seconds format
     * @return the degrees value as a double
     * @throws UnitSystemArgumentException if dmsFormat is invalid
     */
    public static double extractDegreesFromDMSFormat(String dmsFormat) {
        if (dmsFormat == null || dmsFormat.isBlank()) {
            throw new UnitSystemArgumentException("Geo parser: Invalid input. Argument cannot be null or blank.");
        }

        String[] parts = dmsFormat.split("[o'\"nsew]");

        if (parts.length == 0) {
            throw new UnitSystemArgumentException("Geo DMS parser: Input string could not be parsed: input = "
                    + dmsFormat);
        }

        double degrees = parseToDouble(parts[0]);

        double minutes = 0;
        if (parts.length > 1) {
            minutes = parseToDouble(parts[1]);
        }

        double seconds = 0;
        if (parts.length > 2) {
            seconds = parseToDouble(parts[2]);
        }

        char directionChar = dmsFormat.charAt(dmsFormat.length() - 1);
        double sign = HaversineEquations.determineSign(directionChar, degrees);

        return sign * HaversineEquations.dmsToDegrees(degrees, minutes, seconds);
    }
}