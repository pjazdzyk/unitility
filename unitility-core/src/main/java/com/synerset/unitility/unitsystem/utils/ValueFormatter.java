package com.synerset.unitility.unitsystem.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * The ValueFormatter class provides utility methods for formatting double values with a specified number of
 * relevant digits and decimal places.
 */
public class ValueFormatter {

    private ValueFormatter() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Formats a double value to a string with the specified number of relevant digits and decimal places.
     * The method calculates the appropriate number of decimal places based on the given relevant digits and
     * ensures proper rounding using HALF_EVEN approach.
     * This allows for automatic determining of number of digits to be output during formatting.
     *
     * @param value          The double value to be formatted.
     * @param relevantDigits The number of relevant digits to consider in the formatting.
     * @return A formatted string representation of the double value.
     */
    public static String toStringWithRelevantDigits(double value, int relevantDigits) {
        relevantDigits = Math.abs(relevantDigits);
        int doubleScale = (int) Math.log10(Math.abs(value));
        if (doubleScale >= 0) {
            return formatValueToDecimalPlaces(value, relevantDigits);
        }
        doubleScale = Math.abs(doubleScale);
        int numDecimalPlaces = Math.max(relevantDigits, doubleScale + relevantDigits);
        return formatValueToDecimalPlaces(value, numDecimalPlaces);
    }

    /**
     * Formats a double value to a string with a specified number of decimal places.
     *
     * @param value            The double value to be formatted.
     * @param numDecimalPlaces The number of decimal places to use in the formatting.
     * @return A formatted string representation of the double value.
     */
    private static String formatValueToDecimalPlaces(double value, int numDecimalPlaces) {
        String formatString = "#";
        if (numDecimalPlaces > 0) {
            formatString = "#." + "#".repeat(numDecimalPlaces);
        }
        DecimalFormat decimalFormat = new DecimalFormat(formatString);
        decimalFormat.setRoundingMode(RoundingMode.HALF_EVEN);
        return decimalFormat.format(value);
    }

}
