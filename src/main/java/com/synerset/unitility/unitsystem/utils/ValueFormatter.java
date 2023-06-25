package com.synerset.unitility.unitsystem.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ValueFormatter {

    private ValueFormatter() {
        throw new IllegalStateException("Utility class");
    }

    public static String formatDoubleToRelevantDigits(double value, int relevantDigits) {
        int doubleScale = (int) Math.abs(Math.log10(Math.abs(value)));
        int numDecimalPlaces = value >= 1.0 ? 3 : Math.max(relevantDigits, doubleScale + relevantDigits);
        String formatString = "#." + "#".repeat(numDecimalPlaces);
        DecimalFormat decimalFormat = new DecimalFormat(formatString);
        decimalFormat.setRoundingMode(RoundingMode.HALF_EVEN);
        return decimalFormat.format(value);
    }

}
