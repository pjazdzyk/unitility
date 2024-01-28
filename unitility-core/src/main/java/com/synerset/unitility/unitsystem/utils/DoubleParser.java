package com.synerset.unitility.unitsystem.utils;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;

/**
 * The {@link DoubleParser} class provides utility methods for parsing strings to double values.
 * It includes methods for parsing a string to a double with or without an additional error message.
 * The class is designed with static methods and a private constructor to prevent instantiation,
 * as it serves as a utility class.
 */
public class DoubleParser {

    /**
     * Private constructor to prevent instantiation of the utility class.
     * Throws an {@code IllegalStateException} with the message "Utility class" if called.
     */
    private DoubleParser() {
        throw new IllegalStateException("Utility class");
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