package com.synerset.unitility.unitsystem.utils;

import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ValueFormatter {

    private ValueFormatter() {
        throw new IllegalStateException("Utility class");
    }

    public static String formatDoubleToRelevantPrecision(BigDecimal value, int relevantDigits) {

        int relevantScale = calculateRelevantScale(value);

        int numDecimalPlaces = value.compareTo(BigDecimal.ONE) >= 0 ? 6 : Math.max(relevantDigits, relevantScale + relevantDigits);
        String formatString = "#." + "#".repeat(numDecimalPlaces);
        DecimalFormat decimalFormat = new DecimalFormat(formatString);
        decimalFormat.setRoundingMode(RoundingMode.HALF_EVEN);
        return decimalFormat.format(value);
    }

    private static int calculateRelevantScale(BigDecimal value) {

        BigDecimal abs = value.abs(MathContext.DECIMAL64);
        if (abs.compareTo(BigDecimal.ZERO) == 0) {
            return  0;
        }
        BigDecimal log10 = BigDecimalMath.log10(abs, MathContext.DECIMAL64);
        BigDecimal log10abs = log10.abs(MathContext.DECIMAL64);

        return log10abs.intValue();

    }

    public static String formatDoubleToRelevantPrecisionOld(double value, int relevantDigits) {
        int doubleScale = (int) Math.abs(Math.log10(Math.abs(value)));
        int numDecimalPlaces = value >= 1.0 ? 3 : Math.max(relevantDigits, doubleScale + relevantDigits);
        String formatString = "#." + "#".repeat(numDecimalPlaces);
        DecimalFormat decimalFormat = new DecimalFormat(formatString);
        decimalFormat.setRoundingMode(RoundingMode.HALF_EVEN);
        return decimalFormat.format(value);
    }

}
