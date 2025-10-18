package com.synerset.unitility.unitsystem.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * The ValueFormatter class provides utility methods for formatting double values with a specified number of
 * relevant digits and decimal places.
 * Set decimal '.' and grouping ',' separators of 'format()' for float numbers.(','  is used for decimal locale separators in USA/TR )
 */
public class ValueFormatter {

    private ValueFormatter() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Formats a double value to a string using a cutoff precision (epsilon).
     * The value is rounded to the nearest multiple of the given precision using HALF_EVEN rounding mode.
     * <p>
     * Example:
     * <ul>
     *   <li>value = 0.01567, precision = 0.01 → "0.02"</li>
     *   <li>value = 0.014, precision = 0.01 → "0.01"</li>
     *   <li>value = 123.456, precision = 0.1 → "123.5"</li>
     * </ul>
     *
     * @param value     The double value to be formatted.
     * @param precision The cutoff precision (epsilon), e.g. 0.01 for rounding to hundredths.
     * @return A formatted string representation of the rounded value.
     */
    public static String toStringWithPrecision(double value, double precision) {
        if (precision <= 0) {
            throw new IllegalArgumentException("Precision must be positive");
        }

        // Determine number of decimal places from precision (e.g. 0.001 -> 3)
        int decimalPlaces = 0;
        double tmp = precision;
        while (tmp < 1) {
            tmp *= 10;
            decimalPlaces++;
        }

        // Round using HALF_EVEN
        double rounded = Math.round(value / precision) * precision;

        StringBuilder pattern = new StringBuilder("#");
        if (decimalPlaces > 0) {
            pattern.append(".").append("#".repeat(decimalPlaces));
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');

        DecimalFormat df = new DecimalFormat(pattern.toString(), symbols);
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(rounded);
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
        if(Math.abs(value) < 1 && relevantDigits <= 0) {
            return "0";
        }

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
        // Set locale decimal and grouping separators.
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        
        DecimalFormat decimalFormat = new DecimalFormat(formatString, symbols);
        decimalFormat.setRoundingMode(RoundingMode.HALF_EVEN);
        return decimalFormat.format(value);
    }

}