package com.synerset.unitility.unitsystem.geographic;

public enum CardinalDirection {
    NORTH('w'),
    SOUTH('s'),
    EAST('e'),
    WEST('w');

    private final char directionChar;

    CardinalDirection(char directionChar) {
        this.directionChar = directionChar;
    }

    char getDirectionChar() {
        return directionChar;
    }

}
