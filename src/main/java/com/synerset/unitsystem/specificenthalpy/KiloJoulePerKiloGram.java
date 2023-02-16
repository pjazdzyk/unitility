package com.synerset.unitsystem.specificenthalpy;

import java.util.Objects;

public final class KiloJoulePerKiloGram implements SpecificEnthalpy{

    private static final String DEF_SYMBOL = "kJ/kg";
    private final double value;

    private KiloJoulePerKiloGram(double value) {
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
    public JoulePerKiloGram toJoulePerKiloGram(double value) {
        return JoulePerKiloGram.of(value * 1E3);
    }

    @Override
    public KiloJoulePerKiloGram toKiloJoulePerKiloGram(double value) {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KiloJoulePerKiloGram that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static KiloJoulePerKiloGram of(double value){
        return new KiloJoulePerKiloGram(value);
    }

    @Override
    public String toString() {
        return value + DEF_SYMBOL;
    }
}
