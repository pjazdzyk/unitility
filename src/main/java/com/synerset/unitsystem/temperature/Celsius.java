package com.synerset.unitsystem.temperature;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Celsius celsius)) return false;
        return Double.compare(celsius.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static Celsius of(double value){
        return new Celsius(value);
    }

}
