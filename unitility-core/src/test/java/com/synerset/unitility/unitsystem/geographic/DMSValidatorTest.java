package com.synerset.unitility.unitsystem.geographic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DMSValidatorTest {

    @Test
    @DisplayName("should return true when given string is valid DMS format")
    void isValidDMSFormat_shouldReturnTrueIfStringIsValidDmsFormat() {
        assertThat(DMSValidator.isValidDMSFormat("52°14'5.123\"N")).isTrue();
        assertThat(DMSValidator.isValidDMSFormat("52°14'5.123\"")).isTrue();
        assertThat(DMSValidator.isValidDMSFormat("52°14'")).isTrue();
        assertThat(DMSValidator.isValidDMSFormat("52°")).isTrue();
        assertThat(DMSValidator.isValidDMSFormat("52°")).isTrue();

        // Invalid examples
        assertThat(DMSValidator.isValidDMSFormat("52")).isFalse();
        assertThat(DMSValidator.isValidDMSFormat("52o14'5.123\"X")).isFalse();
        assertThat(DMSValidator.isValidDMSFormat("52o14'5.123\"NS")).isFalse();
        assertThat(DMSValidator.isValidDMSFormat("abc")).isFalse();
    }

}