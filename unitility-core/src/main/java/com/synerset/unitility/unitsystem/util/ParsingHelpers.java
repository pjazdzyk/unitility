package com.synerset.unitility.unitsystem.util;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;

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
     * @param doubleAsString The string representation of the double.
     * @param addedMessage   An additional message to be included in the exception message if parsing fails.
     * @return The parsed double value.
     * @throws UnitSystemParseException If the input string cannot be parsed to a double,
     *                                  an exception is thrown with a descriptive error message.
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
     * Parses the specified string to a double value.
     * Calls the {@code parseToDouble} method with an empty additional message.
     *
     * @param doubleAsString The string representation of the double.
     * @return The parsed double value.
     * @throws UnitSystemParseException If the input string cannot be parsed to a double,
     *                                  an exception is thrown with a descriptive error message.
     *                                  The additional message is empty in this case.
     */
    public static double parseToDouble(String doubleAsString) {
        return parseToDouble(doubleAsString, "");
    }

}