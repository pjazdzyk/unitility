package com.synerset.unitsystem.specificheat;

import java.util.Objects;

public final class KiloJoulePerKilogramKelvin implements SpecificHeat{

    private static final String DEF_SYMBOL = "kJ/kgK";
    private final double value;

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
    public JoulePerKilogramKelvin toJoulePerKilogramKelvin() {
        return JoulePerKilogramKelvin.of(value * 1E3);
    }

    @Override
    public KiloJoulePerKilogramKelvin toKiloJoulePerKilogramKelvin() {
        return this;
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

    public static KiloJoulePerKilogramKelvin of(double value) {
        return new KiloJoulePerKilogramKelvin(value);
    }

}
