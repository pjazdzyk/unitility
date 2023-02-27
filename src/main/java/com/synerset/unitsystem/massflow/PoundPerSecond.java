package com.synerset.unitsystem.massflow;

import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.DoubleFunction;

public final class PoundPerSecond implements MassFlow {
    private static final String DEF_SYMBOL = "lb/s";
    private final double value;
    private static final DoubleFunction<Double> VALUE_TO_KGS = val -> val / 2.204622622;

    private PoundPerSecond(double value) {
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
        return KiloGramPerSecond.of(VALUE_TO_KGS.apply(value))
                .getOrElseThrow(() -> new IllegalStateException());
    }

    @Override
    public KiloGramPerHour toKiloGramPerHour() {
        double flowInKgs = toKiloGramPerSecond().getValue();
        return KiloGramPerHour.of(flowInKgs * 3600d)
                .getOrElseThrow(() -> new IllegalStateException());
    }
    @Override
    public PoundPerSecond toPoundPerSecond() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PoundPerSecond that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static Either<InvalidMassFlow, PoundPerSecond> of(double value) {
        return KiloGramPerSecond.of(VALUE_TO_KGS.apply(value))
                .mapLeft(l -> new InvalidMassFlow())
                .map(r -> new PoundPerSecond(value));
    }

    @Override
    public String toString() {
        return value + DEF_SYMBOL;
    }
}
