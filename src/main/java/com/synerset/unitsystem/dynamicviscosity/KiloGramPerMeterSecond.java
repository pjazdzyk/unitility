package com.synerset.unitsystem.dynamicviscosity;

import io.vavr.control.Either;

import java.util.Objects;

public final class KiloGramPerMeterSecond implements DynamicViscosity {
    private static final String DEF_SYMBOL = "kg/ms";
    private final double value;

    private KiloGramPerMeterSecond(double value) {
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
        if (!(o instanceof KiloGramPerMeterSecond that)) return false;
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
    public PascalSecond toPascalSecond() {
        return PascalSecond.of(value).getOrElseThrow(() -> new IllegalStateException());
    }

    @Override
    public KiloGramPerMeterSecond toKiloGramPerMeterSecond() {
        return this;
    }

    @Override
    public Poise toPoise() {
        return Poise.of(value * 10).getOrElseThrow(() -> new IllegalStateException());
    }

    static Either<InvalidDynamicViscosity, KiloGramPerMeterSecond> of(double value) {
        return value < 0.0
                ? Either.left(new InvalidDynamicViscosity())
                : Either.right(new KiloGramPerMeterSecond(value));
    }

}
