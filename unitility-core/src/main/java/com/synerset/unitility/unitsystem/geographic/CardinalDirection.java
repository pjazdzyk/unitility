package com.synerset.unitility.unitsystem.geographic;

public enum CardinalDirection implements GeoDirection {
    NORTH("N", Bearing.of(0)),
    NORTH_NORTHEAST("NNE", Bearing.of(22.5)),
    NORTHEAST("NE", Bearing.of(45)),
    EAST_NORTHEAST("ENE", Bearing.of(67.5)),
    EAST("E", Bearing.of(90)),
    EAST_SOUTHEAST("ESE", Bearing.of(112.5)),
    SOUTHEAST("SE", Bearing.of(135)),
    SOUTH_SOUTHEAST("SSE", Bearing.of(157.5)),
    SOUTH("S", Bearing.of(180)),
    SOUTH_SOUTHWEST("SSW", Bearing.of(202.5)),
    SOUTHWEST("SW", Bearing.of(225)),
    WEST_SOUTHWEST("WSW", Bearing.of(247.5)),
    WEST("W", Bearing.of(270)),
    WEST_NORTHWEST("WNW", Bearing.of(292.5)),
    NORTHWEST("NW", Bearing.of(315)),
    NORTH_NORTHWEST("NNW", Bearing.of(337.5));

    private final String directionSymbol;
    private final Bearing trueBearing;

    CardinalDirection(String directionSymbol, Bearing trueBearing) {
        this.directionSymbol = directionSymbol;
        this.trueBearing = trueBearing;
    }

    public String getDirectionSymbol() {
        return directionSymbol;
    }

    public Bearing getTrueBearing() {
        return trueBearing;
    }
}
