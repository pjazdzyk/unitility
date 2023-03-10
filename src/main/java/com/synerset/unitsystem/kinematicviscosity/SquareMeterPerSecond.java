package com.synerset.unitsystem.kinematicviscosity;

import io.vavr.control.Either;

import java.util.Objects;

public final class SquareMeterPerSecond implements KinematicViscosity {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SquareMeterPerSecond that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + DEF_SYMBOL;
    }

    @Override
    public SquareMeterPerSecond toSquareMeterPerSecond() {
        return this;
    }

    @Override
    public SquareInchPerSecond toSquareInchPerSecond() {
        return SquareInchPerSecond.of(value * 1550.0031).getOrElseThrow(() -> new IllegalStateException());
    }

    static Either<InvalidKinematicViscosity, SquareMeterPerSecond> of(double value) {
        return value < 0.0
                ? Either.left(new InvalidKinematicViscosity())
                : Either.right(new SquareMeterPerSecond(value));
    }
}
