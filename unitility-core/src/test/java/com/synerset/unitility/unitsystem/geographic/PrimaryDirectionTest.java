package com.synerset.unitility.unitsystem.geographic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PrimaryDirectionTest {
    @Test
    @DisplayName("Should return correct direction symbols for primary directions")
    void testDirectionSymbols() {
        assertThat(PrimaryDirection.NORTH.getDirectionSymbol()).isEqualTo("N");
        assertThat(PrimaryDirection.EAST.getDirectionSymbol()).isEqualTo("E");
        assertThat(PrimaryDirection.SOUTH.getDirectionSymbol()).isEqualTo("S");
        assertThat(PrimaryDirection.WEST.getDirectionSymbol()).isEqualTo("W");
    }

    @Test
    @DisplayName("Should return correct true bearing values for primary directions")
    void testTrueBearings() {
        assertThat(PrimaryDirection.NORTH.getTrueBearing()).isEqualTo(Bearing.of(0));
        assertThat(PrimaryDirection.EAST.getTrueBearing()).isEqualTo(Bearing.of(90));
        assertThat(PrimaryDirection.SOUTH.getTrueBearing()).isEqualTo(Bearing.of(180));
        assertThat(PrimaryDirection.WEST.getTrueBearing()).isEqualTo(Bearing.of(270));
    }

    @Test
    @DisplayName("Should contain all expected enum values in correct order")
    void testEnumValues() {
        assertThat(PrimaryDirection.values()).containsExactly(
                PrimaryDirection.NORTH, PrimaryDirection.EAST, PrimaryDirection.SOUTH, PrimaryDirection.WEST
        );
    }
}