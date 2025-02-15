package com.synerset.unitility.unitsystem.geographic;

public enum PrimaryDirection implements GeoDirection{
    NORTH("N", Bearing.of(0)),
    EAST("E", Bearing.of(90)),
    SOUTH("S", Bearing.of(180)),
    WEST("W", Bearing.of(270));

    private final String directionSymbol;
    private final Bearing trueBearing;

    PrimaryDirection(String directionSymbol, Bearing trueBearing) {
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
