package com.synerset.unitsystem.temperature;

public class Celsius implements Temperature{

    private static final String DEF_SYMBOL = "C";

    private final double value;

    private Celsius(double value) {
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
    public Celsius toCelsius() {
        return this;
    }

    @Override
    public Kelvin toKelvin() {
        return Kelvin.of(value + 273.15).getOrElseThrow(() -> new IllegalStateException("Invalid Temperature"));
    }

    static Celsius of(double value){
        return new Celsius(value);
    }

}
