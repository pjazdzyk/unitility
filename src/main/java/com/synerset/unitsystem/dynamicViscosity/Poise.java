package com.synerset.unitsystem.dynamicViscosity;

import java.util.Objects;

public class Poise implements DynamicViscosity {
    private static final String DEF_SYMBOL = "P";
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
    public PascalSecond toPascalSecond() {
        return PascalSecond.of(value / 10d);
    }

    @Override
    public KiloGramPerMeterSecond toKiloGramPerMeterSecond() {
        return KiloGramPerMeterSecond.of(value / 10d);
    }

    @Override
    public Poise toPoise() {
        return this;
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

    static Poise of(double value){
        return new Poise(value);
    }

}
