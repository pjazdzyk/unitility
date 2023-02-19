package com.synerset.unitsystem.dynamicviscosity;

import io.vavr.control.Either;

import java.util.Objects;

public final class PascalSecond implements DynamicViscosity{

    private static final String DEF_SYMBOL = "Pas";
    private final double value;

    private PascalSecond(double value) {
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
        if (!(o instanceof PascalSecond that)) return false;
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
        return this;
    }

    @Override
    public KiloGramPerMeterSecond toKiloGramPerMeterSecond() {
        return KiloGramPerMeterSecond.of(value).getOrElseThrow(()-> new IllegalStateException());
    }

    @Override
    public Poise toPoise() {
        return toKiloGramPerMeterSecond().toPoise();
    }

    static Either<InvalidDynamicViscosity, PascalSecond> of(double value){
        return value < 0
                ? Either.left(new InvalidDynamicViscosity())
                : Either.right(new PascalSecond(value));
    }

}
