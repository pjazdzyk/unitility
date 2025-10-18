package com.synerset.unitility.unitsystem.geographic;

/**
 * Utility class for formatting latitude and longitude values
 * into ICAO-compliant DMS (Degrees–Minutes–Seconds) strings.
 *
 * <p>
 * According to ICAO Doc 9674, Chapter 2.5.1:
 * <blockquote>
 * “Resolution of positional data is the smallest separation that can be represented
 * by the method employed to make the positional statement.
 * Care must be taken that the resolution does not affect accuracy;
 * the resolution is always a <b>rounded value</b> as opposed to a truncated value.”
 * </blockquote>
 * </p>
 */
class DMSCoordinateFormatter {

    /**
     * Default ICAO Annex 15 resolution for seconds (0.01).
     */
    public static final double DEFAULT_ICAO_SECONDS_RESOLUTION = 0.01;

    private DMSCoordinateFormatter() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Formats latitude to ICAO DMS string (DD°MM'SS.ss"N/S) using default 0.01 resolution.
     *
     * @param latitude the latitude object
     * @return ICAO-compliant DMS string
     */
    static String latitudeToDMSSFormat(Latitude latitude) {
        return latitudeToDMSSFormat(latitude, DEFAULT_ICAO_SECONDS_RESOLUTION);
    }

    /**
     * Formats latitude to ICAO DMS string (DD°MM'SS.s..."N/S) using custom seconds resolution.
     *
     * @param latitude          the latitude object
     * @param secondsResolution the desired seconds resolution (e.g., 0.01, 0.1, 1.0, or 0 for full seconds)
     * @return ICAO-compliant DMS string
     */
    static String latitudeToDMSSFormat(Latitude latitude, double secondsResolution) {
        double latitudeInDegrees = latitude.getInDegrees();
        char directionSymbol = (latitudeInDegrees < 0) ? 'S' : 'N';
        return createDMSSNotation(latitudeInDegrees, directionSymbol, secondsResolution, 2);
    }

    /**
     * Formats longitude to ICAO DMS string (DDD°MM'SS.ss"E/W) using default 0.01 resolution.
     *
     * @param longitude the longitude object
     * @return ICAO-compliant DMS string
     */
    static String longitudeToDMSSFormat(Longitude longitude) {
        return longitudeToDMSSFormat(longitude, DEFAULT_ICAO_SECONDS_RESOLUTION);
    }

    /**
     * Formats longitude to ICAO DMS string (DDD°MM'SS.s..."E/W) using custom seconds resolution.
     *
     * @param longitude         the longitude object
     * @param secondsResolution the desired seconds resolution (e.g., 0.01, 0.1, 1.0, or 0 for full seconds)
     * @return ICAO-compliant DMS string
     */
    static String longitudeToDMSSFormat(Longitude longitude, double secondsResolution) {
        double longitudeInDegrees = longitude.getInDegrees();
        char directionSymbol = (longitudeInDegrees < 0) ? 'W' : 'E';
        return createDMSSNotation(longitudeInDegrees, directionSymbol, secondsResolution, 3);
    }

    /**
     * ICAO-compliant DMS notation builder.
     *
     * <p>This method rounds seconds to the specified resolution,
     * in accordance with ICAO Doc 9674 §2.5.1.
     * </p>
     *
     * @param coordinateInDegrees the coordinate value in decimal degrees
     * @param directionSymbol     direction letter (N/S/E/W)
     * @param secondsResolution   the desired seconds resolution
     * @param degreePadding       number of digits for degrees (2 for latitude, 3 for longitude)
     * @return formatted DMS string (e.g., "35°05'35.13\"N")
     */
    private static String createDMSSNotation(double coordinateInDegrees, char directionSymbol, double secondsResolution, int degreePadding) {

        // Validate resolution input
        if (secondsResolution < 0) {
            secondsResolution = DEFAULT_ICAO_SECONDS_RESOLUTION;
        }

        double absCoordinate = Math.abs(coordinateInDegrees);
        int degrees = (int) absCoordinate;
        double minutesAndSeconds = (absCoordinate - degrees) * 60;
        int minutes = (int) minutesAndSeconds;
        double seconds = (minutesAndSeconds - minutes) * 60;

        // Determine multiplier and decimal places
        double multiplier;
        int decimalPlaces;

        if (secondsResolution == 0) {
            multiplier = 1.0;
            decimalPlaces = 0;
        } else {
            multiplier = 1.0 / secondsResolution;
            decimalPlaces = Math.max(0, (int) Math.round(Math.log10(multiplier)));
        }

        // Round seconds to the specified resolution (ICAO-compliant)
        double roundedSeconds = Math.round(seconds * multiplier) / multiplier;

        // Handle rounding overflow (e.g. 59.9999 -> 60.00)
        if (roundedSeconds >= 60.0) {
            roundedSeconds = 0.0;
            minutes++;
            if (minutes >= 60) {
                minutes = 0;
                degrees++;
            }
        }

        // Format seconds
        int integerPartWidth = 2;
        int decimalPointWidth = (decimalPlaces > 0) ? 1 : 0;
        int totalWidth = integerPartWidth + decimalPointWidth + decimalPlaces;
        String secondsFormatString = String.format("%%0%d.%df", totalWidth, decimalPlaces);

        // Format all parts
        String degreesFormatted = String.format("%0" + degreePadding + "d", degrees);
        String minutesFormatted = String.format("%02d", minutes);
        String secondsFormatted = String.format(secondsFormatString, roundedSeconds);

        // ICAO DMS output (no spaces)
        return String.format("%s°%s'%s\"%c", degreesFormatted, minutesFormatted, secondsFormatted, directionSymbol);
    }

}
