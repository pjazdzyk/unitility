package com.synerset.unitsystem.massflow;

import java.util.Objects;

public final class KiloGramPerSecond implements MassFlow{
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
        return KiloGramPerHour.of(value * 3600);
    }

    @Override
    public PoundPerSecond toPoundPerSecond() {
        return PoundPerSecond.of(value * 2.204622622);
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

    static KiloGramPerSecond of(double value){
        return new KiloGramPerSecond(value);
    }

}
