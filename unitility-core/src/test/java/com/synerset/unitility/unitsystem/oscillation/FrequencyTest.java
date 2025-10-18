package com.synerset.unitility.unitsystem.oscillation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrequencyTest {

    @Test
    @DisplayName("Frequency: should properly convert between various frequency units and back")
    void shouldProperlyConvertBetweenVariousFrequencyUnits() {
        // Given
        double initialValue = 5.0;
        // 5.0 kHz
        Frequency initialFrequency = Frequency.ofKiloHertz(initialValue);

        // When
        // To Base Unit (Hz)
        Frequency actualInHertz = initialFrequency.toHertz();
        double actualHertzVal = initialFrequency.getInHertz();

        // To Gigahertz
        Frequency actualInGigaHertz = initialFrequency.toGigaHertz();
        double actualGigaHertzVal = initialFrequency.getInGigaHertz();

        // To Cycles Per Minute
        Frequency actualInCpm = initialFrequency.toCyclesPerMinute();
        double actualCpmVal = initialFrequency.getInCyclesPerMinute();

        // Round-trip (GHz -> kHz)
        Frequency actualRoundTrip = actualInGigaHertz.toKiloHertz();

        // Then
        // 5.0 kHz = 5000.0 Hz (5.0 * 10^3)
        double expectedHertzValue = initialValue * 1000.0;
        assertThat(actualInHertz.getValue()).isEqualTo(expectedHertzValue);
        assertThat(actualHertzVal).isEqualTo(expectedHertzValue);

        // 5.0 kHz = 0.000005 GHz (5.0 / 10^6)
        double expectedGigaHertzValue = initialValue / 1000000.0;
        assertThat(actualInGigaHertz.getValue()).isEqualTo(expectedGigaHertzValue);
        assertThat(actualGigaHertzVal).isEqualTo(expectedGigaHertzValue);

        // 5.0 kHz = 300000.0 cpm (5000 Hz * 60 s/min)
        double expectedCpmValue = expectedHertzValue * 60.0;
        assertThat(actualInCpm.getValue()).isEqualTo(expectedCpmValue);
        assertThat(actualCpmVal).isEqualTo(expectedCpmValue);

        // Round trip should equal the initial value (based on base value comparison in equals method)
        assertThat(actualRoundTrip).isEqualTo(initialFrequency);
    }

    @Test
    @DisplayName("Frequency: should have HERTZ as base unit")
    void shouldHaveHertzAsBaseUnit() {
        // Given
        FrequencyUnit expectedBaseUnit = FrequencyUnits.HERTZ;

        // When
        // Check a non-base unit (e.g., MEGAHERTZ)
        Frequency frequencyInMegaHertz = Frequency.ofMegaHertz(10);
        FrequencyUnit actualBaseUnit = frequencyInMegaHertz.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("Frequency: should return valid result from to() and getIn() methods and maintain equality after round-trip conversion")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        double testValue = 10.1;
        Frequency expected = Frequency.ofMegaHertz(testValue);

        // When
        // Perform a round-trip conversion: MHz -> GHz -> MHz
        Frequency actual = expected.toGigaHertz().toMegaHertz();
        double actualValue = expected.getInMegaHertz();

        // Then
        // The object should be equal after the round-trip
        assertThat(actual).isEqualTo(expected);
        // The getter for the current unit should return the original value
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

}