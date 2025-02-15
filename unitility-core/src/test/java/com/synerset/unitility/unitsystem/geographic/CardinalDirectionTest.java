package com.synerset.unitility.unitsystem.geographic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardinalDirectionTest {
    @Test
    @DisplayName("Should return correct direction symbols for each cardinal direction")
    void testDirectionSymbols() {
        assertThat(CardinalDirection.NORTH.getDirectionSymbol()).isEqualTo("N");
        assertThat(CardinalDirection.NORTH_NORTHEAST.getDirectionSymbol()).isEqualTo("NNE");
        assertThat(CardinalDirection.NORTHEAST.getDirectionSymbol()).isEqualTo("NE");
        assertThat(CardinalDirection.EAST_NORTHEAST.getDirectionSymbol()).isEqualTo("ENE");
        assertThat(CardinalDirection.EAST.getDirectionSymbol()).isEqualTo("E");
        assertThat(CardinalDirection.EAST_SOUTHEAST.getDirectionSymbol()).isEqualTo("ESE");
        assertThat(CardinalDirection.SOUTHEAST.getDirectionSymbol()).isEqualTo("SE");
        assertThat(CardinalDirection.SOUTH_SOUTHEAST.getDirectionSymbol()).isEqualTo("SSE");
        assertThat(CardinalDirection.SOUTH.getDirectionSymbol()).isEqualTo("S");
        assertThat(CardinalDirection.SOUTH_SOUTHWEST.getDirectionSymbol()).isEqualTo("SSW");
        assertThat(CardinalDirection.SOUTHWEST.getDirectionSymbol()).isEqualTo("SW");
        assertThat(CardinalDirection.WEST_SOUTHWEST.getDirectionSymbol()).isEqualTo("WSW");
        assertThat(CardinalDirection.WEST.getDirectionSymbol()).isEqualTo("W");
        assertThat(CardinalDirection.WEST_NORTHWEST.getDirectionSymbol()).isEqualTo("WNW");
        assertThat(CardinalDirection.NORTHWEST.getDirectionSymbol()).isEqualTo("NW");
        assertThat(CardinalDirection.NORTH_NORTHWEST.getDirectionSymbol()).isEqualTo("NNW");
    }

    @Test
    @DisplayName("Should return correct true bearing values for each cardinal direction")
    void testTrueBearings() {
        assertThat(CardinalDirection.NORTH.getTrueBearing()).isEqualTo(Bearing.of(0));
        assertThat(CardinalDirection.NORTH_NORTHEAST.getTrueBearing()).isEqualTo(Bearing.of(22.5));
        assertThat(CardinalDirection.NORTHEAST.getTrueBearing()).isEqualTo(Bearing.of(45));
        assertThat(CardinalDirection.EAST_NORTHEAST.getTrueBearing()).isEqualTo(Bearing.of(67.5));
        assertThat(CardinalDirection.EAST.getTrueBearing()).isEqualTo(Bearing.of(90));
        assertThat(CardinalDirection.EAST_SOUTHEAST.getTrueBearing()).isEqualTo(Bearing.of(112.5));
        assertThat(CardinalDirection.SOUTHEAST.getTrueBearing()).isEqualTo(Bearing.of(135));
        assertThat(CardinalDirection.SOUTH_SOUTHEAST.getTrueBearing()).isEqualTo(Bearing.of(157.5));
        assertThat(CardinalDirection.SOUTH.getTrueBearing()).isEqualTo(Bearing.of(180));
        assertThat(CardinalDirection.SOUTH_SOUTHWEST.getTrueBearing()).isEqualTo(Bearing.of(202.5));
        assertThat(CardinalDirection.SOUTHWEST.getTrueBearing()).isEqualTo(Bearing.of(225));
        assertThat(CardinalDirection.WEST_SOUTHWEST.getTrueBearing()).isEqualTo(Bearing.of(247.5));
        assertThat(CardinalDirection.WEST.getTrueBearing()).isEqualTo(Bearing.of(270));
        assertThat(CardinalDirection.WEST_NORTHWEST.getTrueBearing()).isEqualTo(Bearing.of(292.5));
        assertThat(CardinalDirection.NORTHWEST.getTrueBearing()).isEqualTo(Bearing.of(315));
        assertThat(CardinalDirection.NORTH_NORTHWEST.getTrueBearing()).isEqualTo(Bearing.of(337.5));
    }

    @Test
    @DisplayName("Should contain all expected enum values in correct order")
    void testEnumValues() {
        assertThat(CardinalDirection.values()).containsExactly(
                CardinalDirection.NORTH, CardinalDirection.NORTH_NORTHEAST, CardinalDirection.NORTHEAST,
                CardinalDirection.EAST_NORTHEAST, CardinalDirection.EAST, CardinalDirection.EAST_SOUTHEAST,
                CardinalDirection.SOUTHEAST, CardinalDirection.SOUTH_SOUTHEAST, CardinalDirection.SOUTH,
                CardinalDirection.SOUTH_SOUTHWEST, CardinalDirection.SOUTHWEST, CardinalDirection.WEST_SOUTHWEST,
                CardinalDirection.WEST, CardinalDirection.WEST_NORTHWEST, CardinalDirection.NORTHWEST,
                CardinalDirection.NORTH_NORTHWEST
        );
    }
}