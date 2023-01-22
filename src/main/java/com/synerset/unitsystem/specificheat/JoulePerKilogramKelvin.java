package com.synerset.unitsystem.specificheat;

import java.util.Objects;

public class JoulePerKilogramKelvin implements SpecificHeat {

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
        return KiloJoulePerKilogramKelvin.of(value / 1E3);
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

    public static JoulePerKilogramKelvin of(double value) {
        return new JoulePerKilogramKelvin(value);
    }

}
