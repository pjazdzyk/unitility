package com.synerset.unitsystem.specificheat;

import io.vavr.control.Either;

import java.util.Objects;

public final class JoulePerKilogramKelvin implements SpecificHeat {

    private static final String DEF_SYMBOL = "J/kgK";
    private final double value;

    private JoulePerKilogramKelvin(double value) {
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
    public JoulePerKilogramKelvin toJoulePerKilogramKelvin() {
        return this;
    }

    @Override
    public KiloJoulePerKilogramKelvin toKiloJoulePerKilogramKelvin() {
        return KiloJoulePerKilogramKelvin.of(value / 1E3)
                .getOrElseThrow(() -> new IllegalStateException());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JoulePerKilogramKelvin that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static Either<InvalidSpecificHeat, JoulePerKilogramKelvin> of(double value) {
        return value < 0.0
                ? Either.left(new InvalidSpecificHeat(value, JoulePerKilogramKelvin.class))
                : Either.right(new JoulePerKilogramKelvin(value));
    }

    @Override
    public String toString() {
        return value + ", " + DEF_SYMBOL;
    }

}
