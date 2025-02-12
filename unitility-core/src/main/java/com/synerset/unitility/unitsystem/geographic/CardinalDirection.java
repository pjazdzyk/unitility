package com.synerset.unitility.unitsystem.geographic;

public enum CardinalDirection {
    NORTH('n', Bearing.of(0)),
    SOUTH('s', Bearing.of(180)),
    EAST('e', Bearing.of(90)),
    WEST('w', Bearing.of(270));

    private final char directionChar;
    private final Bearing trueBearing;

    CardinalDirection(char directionChar, Bearing trueBearing) {
        this.directionChar = directionChar;
        this.trueBearing = trueBearing;
    }

    public char getDirectionChar() {
        return directionChar;
    }

    public Bearing getTrueBearing() {
        return trueBearing;
    }
}
