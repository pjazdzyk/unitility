package com.synerset.unitility.unitsystem.geographic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DMSValidatorTest {

    @Test
    @DisplayName("should return true when given string is valid DMS format")
    void isValidDMSFormat_shouldReturnTrueIfStringIsValidDmsFormat() {
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("52°14'5.123\"N")).isTrue();
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("52°14'5.123\"")).isTrue();
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("52°14'")).isTrue();
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("52°")).isTrue();
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("52°")).isTrue();

        // Invalid examples
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("52")).isFalse();
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("52o14'5.123\"X")).isFalse();
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("52o14'5.123\"NS")).isFalse();
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("abc")).isFalse();
    }

    @Test
    void testValidDMSFormats() {
        // Basic valid latitude and longitude
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("45°30'N")).isTrue();             // minimal valid DMS, no seconds
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("123°15'45\"E")).isTrue();        // full DMS
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("0°0'0\"N")).isTrue();            // edge case: 0 degrees
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("90°0'0\"S")).isTrue();           // edge case: max latitude
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("180°0'0\"W")).isTrue();          // edge case: max longitude

        // With trailing zeros
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("45°30'00\"N")).isTrue();         // trailing zeros in seconds
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("45°30'00.0000\"N")).isTrue();    // trailing zeros with decimals

        // High precision seconds
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("45°30'15.1234567N")).isTrue();   // arbitrary decimal places

        // Different unit suffixes
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("45o30'15.5N")).isTrue();
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("45deg30min15.5secN")).isTrue();
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("45°30m15.5sN")).isTrue();

        // Lowercase direction
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("45°30'15.5n")).isTrue();
    }

    @Test
    void testInvalidDMSFormats() {
        // Invalid direction
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("45°30'15.5X")).isFalse();      // invalid letter

        // Invalid numbers
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("1234°0'0\"N")).isFalse();       // too many degrees
        // Non-numeric input
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("abc°def'ghi\"N")).isFalse();
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("45°xx'yy\"N")).isFalse();

        // Completely malformed
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("°'\"N")).isFalse();             // only symbols
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("")).isFalse();                  // empty string
    }

    @Test
    void testEdgeCases() {
        // Negative degrees (valid for longitude west or latitude south)
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("-45°30'15.5S")).isTrue();
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("-123°45'0\"W")).isTrue();

        // Degrees with only decimal part for seconds
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("45°30'0.5N")).isTrue();        // half a second

        // Only degrees and direction
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("45°N")).isTrue();
        assertThat(GeoParsingHelpers.isDMSFormatOrSimilar("123°E")).isTrue();
    }

}