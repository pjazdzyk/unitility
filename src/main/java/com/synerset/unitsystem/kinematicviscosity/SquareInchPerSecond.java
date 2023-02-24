package com.synerset.unitsystem.kinematicviscosity;

import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.DoubleFunction;

public final class SquareInchPerSecond implements KinematicViscosity {

    private static final String DEF_SYMBOL = "in²/s";
    private final double value;

    private static final DoubleFunction<Double> VALUE_TO_M2_S = val -> val / 1550.0031;

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
        return SquareMeterPerSecond.of(VALUE_TO_M2_S.apply(value)).getOrElseThrow(() -> new IllegalStateException());
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

    static Either<InvalidKinematicViscosity, SquareInchPerSecond> of(double value) {
        return SquareMeterPerSecond.of(VALUE_TO_M2_S.apply(value))
                .mapLeft(l->new InvalidKinematicViscosity())
                .map(r -> new SquareInchPerSecond(value));
    }

    @Override
    public String toString() {
        return value + DEF_SYMBOL;
    }
}
