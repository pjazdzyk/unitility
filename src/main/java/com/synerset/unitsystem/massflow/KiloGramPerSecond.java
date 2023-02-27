package com.synerset.unitsystem.massflow;

import io.vavr.control.Either;

import java.util.Objects;

public final class KiloGramPerSecond implements MassFlow {
    private static final String DEF_SYMBOL = "kg/s";
    private final double value;

    private KiloGramPerSecond(double value) {
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
    public KiloGramPerSecond toKiloGramPerSecond() {
        return this;
    }

    @Override
    public KiloGramPerHour toKiloGramPerHour() {
        return KiloGramPerHour.of(value * 3600)
                .getOrElseThrow(() -> new IllegalStateException());
    }

    @Override
    public PoundPerSecond toPoundPerSecond() {
        return PoundPerSecond.of(value * 2.204622622)
                .getOrElseThrow(() -> new IllegalStateException());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KiloGramPerSecond that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static Either<InvalidMassFlow, KiloGramPerSecond> of(double value) {
        return value < 0.0
                ? Either.left(new InvalidMassFlow())
                : Either.right(new KiloGramPerSecond(value));
    }

    @Override
    public String toString() {
        return value + DEF_SYMBOL;
    }

}
