package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;

class DMSParserHelper {

    private DMSParserHelper() {
        throw new IllegalStateException("Utility class");
    }

    static double extractDegreesFromDMSFormat(String dmsFormat) {
        validateInputString(dmsFormat);
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

        double sign = determineSign(dmsFormat, degrees);

        return sign * (Math.abs(degrees) + minutes / 60.0 + seconds / 3600.0);
    }

    private static double parseToDouble(String doubleAsString) {
        try {
            return Double.parseDouble(doubleAsString.trim().replace(",", "."));
        } catch (NumberFormatException ex) {
            throw new UnitSystemParseException("Geo double parser: Invalid input, could not parse to double, input = "
                    + doubleAsString);
        }
    }

    private static double determineSign(String dmsString, double degrees) {
        char directionChar = dmsString.charAt(dmsString.length() - 1);

        double sign = 1;
        if (directionChar == 'S' || directionChar == 's' || directionChar == 'W' || directionChar == 'w' || degrees < 0) {
            sign = -1;
        }
        return sign;
    }

    private static void validateInputString(String inputString) {
        if (inputString == null || inputString.isBlank()) {
            throw new UnitSystemArgumentException("Geo parser: Invalid input. Argument cannot be null or blank.");
        }
    }

}