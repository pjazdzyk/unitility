package com.synerset.unitsystem.kinematicviscosity;

import java.util.Objects;

public final class SquareMeterPerSecond implements KinematicViscosity{
    private static final String DEF_SYMBOL = "m²/s";
    private final double value;

    private SquareMeterPerSecond(double value) {
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
        return this;
    }

    @Override
    public SquareInchPerSecond toSquareInchPerSecond() {
        return SquareInchPerSecond.of(value * 1550.0031);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SquareMeterPerSecond that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static SquareMeterPerSecond of(double value){
        return new SquareMeterPerSecond(value);
    }

}
