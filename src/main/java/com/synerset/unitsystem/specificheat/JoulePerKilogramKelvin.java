package com.synerset.unitsystem.specificheat;

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
        return KiloJoulePerKilogramKelvin.of(value / 10E3);
    }

    public static JoulePerKilogramKelvin of(double value) {
        return new JoulePerKilogramKelvin(value);
    }

}
