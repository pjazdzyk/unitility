package com.synerset.unitsystem.massflow;

import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.DoubleFunction;

public final class KiloGramPerHour implements MassFlow {

    private static final String DEF_SYMBOL = "kg/h";
    private final double value;
    private static final DoubleFunction<Double> VALUE_TO_KGS = val -> val / 3600d;

    private KiloGramPerHour(double value) {
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
        if (!(o instanceof KiloGramPerHour that)) return false;
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
    public KiloGramPerSecond toKiloGramPerSecond() {
        return KiloGramPerSecond.of(VALUE_TO_KGS.apply(value))
                .getOrElseThrow(() -> new IllegalStateException());
    }

    @Override
    public KiloGramPerHour toKiloGramPerHour() {
        return this;
    }

    @Override
    public PoundPerSecond toPoundPerSecond() {
        KiloGramPerSecond flowInKgs = toKiloGramPerSecond();
        return flowInKgs.toPoundPerSecond();
    }

    static Either<InvalidMassFlow, KiloGramPerHour> of(double value) {
        return KiloGramPerSecond.of(VALUE_TO_KGS.apply(value))
                .mapLeft(l -> new InvalidMassFlow())
                .map(r -> new KiloGramPerHour(value));
    }

}
