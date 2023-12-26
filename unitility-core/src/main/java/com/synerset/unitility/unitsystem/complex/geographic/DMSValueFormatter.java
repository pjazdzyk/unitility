package com.synerset.unitility.unitsystem.complex.geographic;

import com.synerset.unitility.unitsystem.utils.ValueFormatter;

class DMSValueFormatter {

    private DMSValueFormatter() {
        throw new IllegalStateException("Utility class");
    }

    // DMS format helpers
    static String latitudeToDmsFormat(Latitude latitude, int relevantDigits) {
        double latitudeInDegrees = latitude.getInDegrees();
        char directionSymbol = (latitudeInDegrees < 0) ? 'S' : 'N';
        return createDMSNotation(latitudeInDegrees, directionSymbol, relevantDigits);
    }

    static String longitudeToDmsFormat(Longitude longitude, int relevantDigits) {
        double longitudeInDegrees = longitude.getInDegrees();
        char directionSymbol = (longitudeInDegrees < 0) ? 'W' : 'E';
        return createDMSNotation(longitudeInDegrees, directionSymbol, relevantDigits);
    }

    private static String createDMSNotation(double coordinateInDegrees, char directionSymbol, int relevantDigits) {
        coordinateInDegrees = Math.abs(coordinateInDegrees);

        int degrees = (int) coordinateInDegrees;
        double minutesAndSeconds = (coordinateInDegrees - degrees) * 60;
        int minutes = (int) minutesAndSeconds;
        double seconds = (minutesAndSeconds - minutes) * 60;

        String secondsWithRelDigits = relevantDigits > 0
                ? ValueFormatter.toStringWithRelevantDigits(seconds, relevantDigits)
                : String.valueOf(seconds);

        return String.format("%d°%d'%s\"%c", degrees, minutes, secondsWithRelDigits, directionSymbol);
    }

}