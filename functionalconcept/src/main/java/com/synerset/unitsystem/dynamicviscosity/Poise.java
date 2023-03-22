package com.synerset.unitsystem.dynamicviscosity;

import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.DoubleFunction;

public final class Poise implements DynamicViscosity {
    private static final String DEF_SYMBOL = "P";
    private static final DoubleFunction<Double> VALUE_TO_KG_P_MS = val -> val / 10d;
    private final double value;

    private Poise(double value) {
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
        if (!(o instanceof Poise poise)) return false;
        return Double.compare(poise.value, value) == 0;
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
        return PascalSecond.of(VALUE_TO_KG_P_MS.apply(value))
                .getOrElseThrow(() -> new IllegalStateException());
    }

    @Override
    public KiloGramPerMeterSecond toKiloGramPerMeterSecond() {
        PascalSecond pascalSecond = toPascalSecond();
        return pascalSecond.toKiloGramPerMeterSecond();
    }

    @Override
    public Poise toPoise() {
        return this;
    }

    static Either<InvalidDynamicViscosity, Poise> of(double value) {
        return KiloGramPerMeterSecond.of(value)
                .mapLeft(l -> new InvalidDynamicViscosity())
                .map(r -> new Poise(value));
    }

}
