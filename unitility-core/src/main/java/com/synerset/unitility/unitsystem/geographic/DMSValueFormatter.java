package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.util.ValueFormatter;

class DMSValueFormatter {

    public static final double DEFAULT_ICAO_SECONDS_PRECISION = 0.01;

    private DMSValueFormatter() {
        throw new IllegalStateException("Utility class");
    }

    static String latitudeToDmsFormat(Latitude latitude, double secondsPrecision) {
        double latitudeInDegrees = latitude.getInDegrees();
        char directionSymbol = (latitudeInDegrees < 0) ? 'S' : 'N';
        return createDMSNotation(latitudeInDegrees, directionSymbol, secondsPrecision, 2);
    }

    static String longitudeToDmsFormat(Longitude longitude, double secondsPrecision) {
        double longitudeInDegrees = longitude.getInDegrees();
        char directionSymbol = (longitudeInDegrees < 0) ? 'W' : 'E';
        return createDMSNotation(longitudeInDegrees, directionSymbol, secondsPrecision, 3);
    }

    /**
     * ICAO-compliant DMS notation builder.
     * Latitude:  DD°MM'SS.S"N
     * Longitude: DDD°MM'SS.S"E
     */
    private static String createDMSNotation(double coordinateInDegrees, char directionSymbol, double secondsPrecision, int degreePadding) {
        coordinateInDegrees = Math.abs(coordinateInDegrees);

        int degrees = (int) coordinateInDegrees;
        double minutesAndSeconds = (coordinateInDegrees - degrees) * 60;
        int minutes = (int) minutesAndSeconds;
        double seconds = (minutesAndSeconds - minutes) * 60;

        // Defaulting negative relevantDigits to default ICAO precision
        if (secondsPrecision < 0) {
            secondsPrecision = DEFAULT_ICAO_SECONDS_PRECISION;
        }
        // Format seconds using relevant digits — ICAO needs at least 1 digit, but we allow flexible precision
        String secondsWithRelDigits = ValueFormatter.toStringWithPrecision(seconds, secondsPrecision);

        // Zero-pad degrees and minutes
        String degreesFormatted = String.format("%0" + degreePadding + "d", degrees);
        String minutesFormatted = String.format("%02d", minutes);

        // ICAO format: DD°MM'SS.S"N (no spaces)
        return String.format("%s°%s'%s\"%c", degreesFormatted, minutesFormatted, secondsWithRelDigits, directionSymbol);
    }
}
