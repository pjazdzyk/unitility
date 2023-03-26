package com.synerset.unitsystem.specificheat;

import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.DoubleFunction;

public final class KiloJoulePerKilogramKelvin implements SpecificHeat {

    private static final String DEF_SYMBOL = "kJ/kgK";
    private final double value;

    private static final DoubleFunction<Double> VALUE_TO_J_KGK = val -> val * 1E3;

    private KiloJoulePerKilogramKelvin(double value) {
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
        if (!(o instanceof KiloJoulePerKilogramKelvin that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + ", " + DEF_SYMBOL;
    }

    @Override
    public JoulePerKilogramKelvin toJoulePerKilogramKelvin() {
        return JoulePerKilogramKelvin.of(VALUE_TO_J_KGK.apply(value))
                .getOrElseThrow(() -> new IllegalStateException());
    }

    @Override
    public KiloJoulePerKilogramKelvin toKiloJoulePerKilogramKelvin() {
        return this;
    }

    public static Either<InvalidSpecificHeat, KiloJoulePerKilogramKelvin> of(double value) {
        return JoulePerKilogramKelvin.of(VALUE_TO_J_KGK.apply(value))
                .mapLeft(left -> new InvalidSpecificHeat(value, KiloJoulePerKilogramKelvin.class))
                .map(right -> new KiloJoulePerKilogramKelvin(value));
    }


}
