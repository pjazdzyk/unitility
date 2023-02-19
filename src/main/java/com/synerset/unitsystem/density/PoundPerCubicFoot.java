package com.synerset.unitsystem.density;

import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.DoubleFunction;

public final class PoundPerCubicFoot implements Density {

    private static final String DEF_SYMBOL = "lb/ft³";
    private final double value;

    private static final DoubleFunction<Double> VALUE_TO_KG_P_M3 = val -> val / 0.06243;

    private PoundPerCubicFoot(double value) {
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
        if (!(o instanceof PoundPerCubicFoot that)) return false;
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
    public KiloGramPerCubicMeter toKiloGramPerCubicMeter() {
        return KiloGramPerCubicMeter.of(VALUE_TO_KG_P_M3.apply(value)).getOrElseThrow((() -> new IllegalStateException()));
    }

    @Override
    public PoundPerCubicFoot toPoundPerCubicFoot() {
        return this;
    }

    static Either<InvalidDensity, PoundPerCubicFoot> of(double value) {
        return KiloGramPerCubicMeter.of(VALUE_TO_KG_P_M3.apply(value))
                .mapLeft(l -> new InvalidDensity())
                .map(r -> new PoundPerCubicFoot(value));
    }

}
