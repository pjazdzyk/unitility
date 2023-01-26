package com.synerset.unitsystem.specificenthalpy;

import java.util.Objects;

public final class JoulePerKiloGram implements SpecificEnthalpy {

    private static final String DEF_SYMBOL = "J/kg";
    private final double value;

    private JoulePerKiloGram(double value) {
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
        return this;
    }

    @Override
    public KiloJoulePerKiloGram toKiloJoulePerKiloGram(double value) {
        return KiloJoulePerKiloGram.of(value / 1E3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JoulePerKiloGram that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static JoulePerKiloGram of(double value){
        return new JoulePerKiloGram(value);
    }

}
