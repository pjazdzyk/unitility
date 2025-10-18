package com.synerset.unitility.unitsystem.geographic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DMSCoordinateFormatterTest {

    // Define the default precision for clarity, though it's passed explicitly
    private static final double DEFAULT_ICAO_RESOLUTION = 0.01;

    @Test
    void testEdgeBoundaries() {
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(90.0), DEFAULT_ICAO_RESOLUTION)).isEqualTo("90°00'00.00\"N");
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(-90.0), DEFAULT_ICAO_RESOLUTION)).isEqualTo("90°00'00.00\"S");
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(0.0), DEFAULT_ICAO_RESOLUTION)).isEqualTo("00°00'00.00\"N");
        assertThat(DMSCoordinateFormatter.longitudeToDMSSFormat(Longitude.ofDegrees(0.0), DEFAULT_ICAO_RESOLUTION)).isEqualTo("000°00'00.00\"E");
        assertThat(DMSCoordinateFormatter.longitudeToDMSSFormat(Longitude.ofDegrees(180.0), DEFAULT_ICAO_RESOLUTION)).isEqualTo("180°00'00.00\"E");
        assertThat(DMSCoordinateFormatter.longitudeToDMSSFormat(Longitude.ofDegrees(-180.0), DEFAULT_ICAO_RESOLUTION)).isEqualTo("180°00'00.00\"W");
    }

    @Test
    void testIntegerDegrees() {
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(35.0), DEFAULT_ICAO_RESOLUTION)).isEqualTo("35°00'00.00\"N");
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(7.0), DEFAULT_ICAO_RESOLUTION)).isEqualTo("07°00'00.00\"N");
        assertThat(DMSCoordinateFormatter.longitudeToDMSSFormat(Longitude.ofDegrees(5.0), DEFAULT_ICAO_RESOLUTION)).isEqualTo("005°00'00.00\"E");
    }

    @Test
    void testMinutesNonZeroSecondsZero() {
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(35.083333333333336), DEFAULT_ICAO_RESOLUTION)).isEqualTo("35°05'00.00\"N");
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(0.125), DEFAULT_ICAO_RESOLUTION)).isEqualTo("00°07'30.00\"N");
    }

    @Test
    void testSecondsWhole() {
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(35.09305555555555), DEFAULT_ICAO_RESOLUTION)).isEqualTo("35°05'35.00\"N");
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(-12.505277777777778), DEFAULT_ICAO_RESOLUTION)).isEqualTo("12°30'19.00\"S");
    }

    @Test
    void testSecondsWithFractional() {
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(35.093125), DEFAULT_ICAO_RESOLUTION)).isEqualTo("35°05'35.25\"N");
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(52.234567), DEFAULT_ICAO_RESOLUTION)).isEqualTo("52°14'04.44\"N");
    }

    @Test
    void testVerySmallFractions() {
        // 35° 5' 34.8036"
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(35.093001), DEFAULT_ICAO_RESOLUTION)).isEqualTo("35°05'34.80\"N");
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(35.00027777777778), DEFAULT_ICAO_RESOLUTION)).isEqualTo("35°00'01.00\"N");
    }

    @Test
    void testZeroSecondsButFractionalMinutes() {
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(35.00041666666667), DEFAULT_ICAO_RESOLUTION)).isEqualTo("35°00'01.50\"N");
    }

    @Test
    void testMinutesOrSecondsLessThan10() {
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(7.051144444444444), DEFAULT_ICAO_RESOLUTION)).isEqualTo("07°03'04.12\"N");
    }

    @Test
    void testLongitudeSpecifics() {
        assertThat(DMSCoordinateFormatter.longitudeToDMSSFormat(Longitude.ofDegrees(5.0), DEFAULT_ICAO_RESOLUTION)).isEqualTo("005°00'00.00\"E");
        assertThat(DMSCoordinateFormatter.longitudeToDMSSFormat(Longitude.ofDegrees(32.85352777777778), DEFAULT_ICAO_RESOLUTION)).isEqualTo("032°51'12.70\"E");
        assertThat(DMSCoordinateFormatter.longitudeToDMSSFormat(Longitude.ofDegrees(179.999999), DEFAULT_ICAO_RESOLUTION)).isEqualTo("180°00'00.00\"E");
    }

    @Test
    void testExamplesNegatives() {
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(-0.0002777777777777778), DEFAULT_ICAO_RESOLUTION)).isEqualTo("00°00'01.00\"S");
        assertThat(DMSCoordinateFormatter.longitudeToDMSSFormat(Longitude.ofDegrees(-121.90277777777777), DEFAULT_ICAO_RESOLUTION)).isEqualTo("121°54'10.00\"W");
    }

    @Test
    void testRandomFuzz() {
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(0.0002777777777777778), DEFAULT_ICAO_RESOLUTION)).isEqualTo("00°00'01.00\"N");
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(45.5), DEFAULT_ICAO_RESOLUTION)).isEqualTo("45°30'00.00\"N");
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(-45.5), DEFAULT_ICAO_RESOLUTION)).isEqualTo("45°30'00.00\"S");
        // This value is 89° 59' 59.9964", but should be rounded up to "90°00'00.00"N" (including rounding overflow)
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(89.999999), DEFAULT_ICAO_RESOLUTION)).isEqualTo("90°00'00.00\"N");
    }

    @Test
    void testDefaultPrecisionMethods() {
        // Also test the overloaded methods that use the default precision
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(35.093125))).isEqualTo("35°05'35.25\"N");
        assertThat(DMSCoordinateFormatter.longitudeToDMSSFormat(Longitude.ofDegrees(-121.90277777))).isEqualTo("121°54'10.00\"W");
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(0.0))).isEqualTo("00°00'00.00\"N");
    }

    @Test
    void testOtherPrecisions() {
        // Test with 1 decimal place (0.1 precision)
        // 35.093125 -> 35°05'35.25" -> Rounds to 35.3
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(35.093125), 0.1)).isEqualTo("35°05'35.3\"N");

        // Test with 0 decimal places (1.0 precision)
        // 35.093125 -> 35°05'35.25" -> Rounds to 35
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(35.093125), 1.0)).isEqualTo("35°05'35\"N");

        // Test with 3 decimal places (0.001 precision)
        // 35.09309163888889 -> 35° 5' 35.1299 -> Rounds to .130
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(35.09309163888889), 0.001)).isEqualTo("35°05'35.130\"N");

        // Test with 0 decimal places (0 precision)
        // 35.09309163888889 -> 35° 5' 35.1299 -> Rounds to .130
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(35.09309163888889), 0)).isEqualTo("35°05'35\"N");

        // Test with -1, should result to default 0.01 resolution
        assertThat(DMSCoordinateFormatter.latitudeToDMSSFormat(Latitude.ofDegrees(35.09309163888889), -1)).isEqualTo("35°05'35.13\"N");
    }

}