package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.common.Angle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class BearingMapperTest {

    private static final double DELTA = 1e-9; // Tolerance for floating-point comparisons

    @Test
    void testToTrueBearing() {
        // Cover every 15-degree increment and edge cases
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(-180)).getInDegrees()).isCloseTo(180, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(-165)).getInDegrees()).isCloseTo(195, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(-150)).getInDegrees()).isCloseTo(210, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(-135)).getInDegrees()).isCloseTo(225, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(-120)).getInDegrees()).isCloseTo(240, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(-105)).getInDegrees()).isCloseTo(255, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(-90)).getInDegrees()).isCloseTo(270, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(-75)).getInDegrees()).isCloseTo(285, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(-60)).getInDegrees()).isCloseTo(300, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(-45)).getInDegrees()).isCloseTo(315, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(-30)).getInDegrees()).isCloseTo(330, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(-15)).getInDegrees()).isCloseTo(345, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(0)).getInDegrees()).isCloseTo(0, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(15)).getInDegrees()).isCloseTo(15, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(30)).getInDegrees()).isCloseTo(30, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(45)).getInDegrees()).isCloseTo(45, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(60)).getInDegrees()).isCloseTo(60, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(75)).getInDegrees()).isCloseTo(75, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(90)).getInDegrees()).isCloseTo(90, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(105)).getInDegrees()).isCloseTo(105, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(120)).getInDegrees()).isCloseTo(120, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(135)).getInDegrees()).isCloseTo(135, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(150)).getInDegrees()).isCloseTo(150, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(165)).getInDegrees()).isCloseTo(165, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(180)).getInDegrees()).isCloseTo(180, within(DELTA));

        // Edge cases
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(-1)).getInDegrees()).isCloseTo(359, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(359)).getInDegrees()).isCloseTo(359, within(DELTA));
        assertThat(BearingMapper.toTrueBearing(Angle.ofDegrees(360)).getInDegrees()).isCloseTo(0, within(DELTA));
    }

    @Test
    void testToSignedBearing() {
        // Cover every 15-degree increment and edge cases
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(0)).getInDegrees()).isCloseTo(0, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(15)).getInDegrees()).isCloseTo(15, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(30)).getInDegrees()).isCloseTo(30, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(45)).getInDegrees()).isCloseTo(45, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(60)).getInDegrees()).isCloseTo(60, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(75)).getInDegrees()).isCloseTo(75, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(90)).getInDegrees()).isCloseTo(90, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(105)).getInDegrees()).isCloseTo(105, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(120)).getInDegrees()).isCloseTo(120, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(135)).getInDegrees()).isCloseTo(135, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(150)).getInDegrees()).isCloseTo(150, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(165)).getInDegrees()).isCloseTo(165, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(180)).getInDegrees()).isCloseTo(180, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(195)).getInDegrees()).isCloseTo(-165, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(210)).getInDegrees()).isCloseTo(-150, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(225)).getInDegrees()).isCloseTo(-135, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(240)).getInDegrees()).isCloseTo(-120, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(255)).getInDegrees()).isCloseTo(-105, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(270)).getInDegrees()).isCloseTo(-90, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(285)).getInDegrees()).isCloseTo(-75, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(300)).getInDegrees()).isCloseTo(-60, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(315)).getInDegrees()).isCloseTo(-45, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(330)).getInDegrees()).isCloseTo(-30, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(345)).getInDegrees()).isCloseTo(-15, within(DELTA));

        // Edge cases
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(359)).getInDegrees()).isCloseTo(-1, within(DELTA));
        assertThat(BearingMapper.toSignedBearing(Angle.ofDegrees(360)).getInDegrees()).isCloseTo(0, within(DELTA));
    }
}