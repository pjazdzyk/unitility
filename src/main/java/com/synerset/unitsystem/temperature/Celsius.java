package com.synerset.unitsystem.temperature;

import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.DoubleFunction;

public final class Celsius implements Temperature {

    private static final String DEF_SYMBOL = "°C";
    private static final DoubleFunction<Double> VALUE_TO_KELVIN = val -> val + 273.15;
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
    public Kelvin toKelvin() {
        return Kelvin.of(VALUE_TO_KELVIN.apply(value))
                .getOrElseThrow(() -> new IllegalStateException());
    }

    @Override
    public Celsius toCelsius() {
        return this;
    }

    @Override
    public Fahrenheit toFahrenheit() {
        Kelvin valInKelvin = toKelvin();
        return valInKelvin.toFahrenheit();
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

    static Either<InvalidTemperature, Celsius> of(double value) {
        return Kelvin.of(VALUE_TO_KELVIN.apply(value))
                .mapLeft(left -> new InvalidTemperature(value, Celsius.class))
                .map(right -> new Celsius(value));
    }

    @Override
    public String toString() {
        return value + ", " + DEF_SYMBOL;
    }
}