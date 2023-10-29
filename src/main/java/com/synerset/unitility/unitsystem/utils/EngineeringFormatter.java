package com.synerset.unitility.unitsystem.utils;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;

public class EngineeringFormatter {

    private EngineeringFormatter() {
        throw new IllegalStateException("Utility class");
    }

    public static <U extends Unit, Q extends PhysicalQuantity<U>> Q fromEngineeringFormat(Class<Q> targetClass, String source) {
        String preparedSource = source.trim()
                .replace(" ", "")
                .replace(",", ".");
        String unitSymbol = null;
        if (preparedSource.contains("[")) {
            unitSymbol = extractInsideParentheses(targetClass, preparedSource);
        }
        double value = extractNumber(targetClass, preparedSource);
        return PhysicalQuantity.createFromSymbol(targetClass, value, unitSymbol);
    }

    public static <U extends Unit, Q extends PhysicalQuantity<U>> String toEngineeringFormat(Q physicalQuantity) {
        if (physicalQuantity.getUnitSymbol().isBlank()) {
            return String.valueOf(physicalQuantity.getValue());
        }
        return String.format("%s[%s]", physicalQuantity.getValue(), physicalQuantity.getUnitSymbol());
    }

    private static double extractNumber(Class<?> targetClass, String input) {
        String extractedNumber;

        if (input.contains("[")) {
            int endIndex = input.indexOf('[');
            extractedNumber = input.substring(0, endIndex);
        } else {
            extractedNumber = input;
        }

        try {
            return Double.parseDouble(extractedNumber);
        } catch (Exception e) {
            throw new UnitSystemParseException("Could not extract number from input: " + input
                    + ", target class: " + targetClass.getSimpleName());
        }
    }

    private static String extractInsideParentheses(Class<?> targetClass, String input) {
        int startIndex = input.indexOf('[');
        int endIndex = input.indexOf(']', startIndex);

        if (startIndex != -1 && endIndex != -1) {
            return input.substring(startIndex + 1, endIndex);
        } else {
            throw new UnitSystemParseException("Could not extract unit symbol from input: " + input
                    + ", target class: " + targetClass.getSimpleName());
        }
    }

}