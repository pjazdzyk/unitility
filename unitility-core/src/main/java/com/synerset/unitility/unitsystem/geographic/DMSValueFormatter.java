package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.util.ValueFormatter;

class DMSValueFormatter {

    private DMSValueFormatter() {
        throw new IllegalStateException("Utility class");
    }

    // DMS format helpers
    static String latitudeToDmsFormat(Latitude latitude, int relevantDigits) {
        double latitudeInDegrees = latitude.getInDegrees();
        char directionSymbol = (latitudeInDegrees < 0) ? 'S' : 'N';
        return createDMSNotation(latitudeInDegrees, directionSymbol, relevantDigits, 2);
    }

    static String longitudeToDmsFormat(Longitude longitude, int relevantDigits) {
        double longitudeInDegrees = longitude.getInDegrees();
        char directionSymbol = (longitudeInDegrees < 0) ? 'W' : 'E';
        return createDMSNotation(longitudeInDegrees, directionSymbol, relevantDigits, 3);
    }

    private static String createDMSNotation(double coordinateInDegrees, char directionSymbol, int relevantDigits, int degreePadding) {
        coordinateInDegrees = Math.abs(coordinateInDegrees);

        int degrees = (int) coordinateInDegrees;
        double minutesAndSeconds = (coordinateInDegrees - degrees) * 60;
        int minutes = (int) minutesAndSeconds;
        double seconds = (minutesAndSeconds - minutes) * 60;

        String secondsWithRelDigits = relevantDigits > 0
                ? ValueFormatter.toStringWithRelevantDigits(seconds, relevantDigits)
                : String.format("%.2f", seconds);

        String degreesFormatted = String.format("%0" + degreePadding + "d", degrees);

        return String.format("%s°%d'%s\"%c", degreesFormatted, minutes, secondsWithRelDigits, directionSymbol);
    }
}