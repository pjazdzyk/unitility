package com.synerset.unitsystem.temperature;

import java.util.Objects;

public final class Fahrenheit implements Temperature {

    private static final String DEF_SYMBOL = "F";

    private final double value;

    private Fahrenheit(double value) {
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
    public Kelvin toKelvin() {
        return Kelvin.of((5.0 / 9.0) * (value + 459.67)).getOrElseThrow(() -> new IllegalStateException("Invalid Temperature"));
    }

    @Override
    public Celsius toCelsius() {
        Kelvin valInKelvin = toKelvin();
        return valInKelvin.toCelsius();
    }

    @Override
    public Fahrenheit toFahrenheit() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fahrenheit that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static Fahrenheit of(double value) {
        return new Fahrenheit(value);
    }

}
