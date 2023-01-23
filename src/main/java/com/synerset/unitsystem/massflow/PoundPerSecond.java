package com.synerset.unitsystem.massflow;

import java.util.Objects;

public class PoundPerSecond implements MassFlow {
    private static final String DEF_SYMBOL = "lb/s";
    private final double value;

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
        return KiloGramPerSecond.of(value / 2.204622622);
    }

    @Override
    public KiloGramPerHour toKiloGramPerHour() {
        double flowInKgs = toKiloGramPerSecond().getValue();
        return KiloGramPerHour.of(flowInKgs * 3600d);
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

    static PoundPerSecond of(double value) {
        return new PoundPerSecond(value);
    }

}
