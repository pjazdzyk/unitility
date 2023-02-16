package com.synerset.unitsystem.density;

import java.util.Objects;

public final class PoundPerCubicFoot implements Density {

    private static final String DEF_SYMBOL = "lb/ft³";
    private final double value;

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
    public KiloGramPerCubicMeter toKiloGramPerCubicMeter() {
        return KiloGramPerCubicMeter.of(value / 0.06243).getOrElseThrow((() -> new IllegalStateException()));
    }

    @Override
    public PoundPerCubicFoot toPoundPerCubicFoot() {
        return this;
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

    static PoundPerCubicFoot of(double value) {
        return new PoundPerCubicFoot(value);
    }

    @Override
    public String toString() {
        return value + DEF_SYMBOL;
    }
}
