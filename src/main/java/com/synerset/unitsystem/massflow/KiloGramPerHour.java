package com.synerset.unitsystem.massflow;

import java.util.Objects;

public final class KiloGramPerHour implements MassFlow{

    private static final String DEF_SYMBOL = "kg/h";
    private final double value;

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
    public KiloGramPerSecond toKiloGramPerSecond() {
        return KiloGramPerSecond.of(value / 3600d);
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

    static KiloGramPerHour of(double value){
        return new KiloGramPerHour(value);
    }

    @Override
    public String toString() {
        return value + DEF_SYMBOL;
    }
}
