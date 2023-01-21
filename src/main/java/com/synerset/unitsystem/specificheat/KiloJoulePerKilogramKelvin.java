package com.synerset.unitsystem.specificheat;

public class KiloJoulePerKilogramKelvin implements SpecificHeat{

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
        return JoulePerKilogramKelvin.of(value * 10E3);
    }

    @Override
    public KiloJoulePerKilogramKelvin toKiloJoulePerKilogramKelvin() {
        return this;
    }

    public static KiloJoulePerKilogramKelvin of(double value) {
        return new KiloJoulePerKilogramKelvin(value);
    }

}
