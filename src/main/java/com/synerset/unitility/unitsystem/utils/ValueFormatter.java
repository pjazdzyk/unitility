package com.synerset.unitility.unitsystem.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ValueFormatter {

    private ValueFormatter() {
        throw new IllegalStateException("Utility class");
    }

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
