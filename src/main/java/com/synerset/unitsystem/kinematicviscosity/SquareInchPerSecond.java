package com.synerset.unitsystem.kinematicviscosity;

import java.util.Objects;

public final class SquareInchPerSecond implements KinematicViscosity{

    private static final String DEF_SYMBOL = "in²/s";
    private final double value;

    private SquareInchPerSecond(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public String getSymbol() {
        return DEF_SYMBOL;
    }

    @Override
    public SquareMeterPerSecond toSquareMeterPerSecond() {
        return SquareMeterPerSecond.of(value / 1550.0031);
    }

    @Override
    public SquareInchPerSecond toSquareInchPerSecond() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SquareInchPerSecond that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static SquareInchPerSecond of(double value){
        return new SquareInchPerSecond(value);
    }

}
