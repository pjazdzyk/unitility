package com.synerset.unitsystem.temperature;

import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.DoubleFunction;

public final class Fahrenheit implements Temperature {

    private static final String DEF_SYMBOL = "°F";
    private static final DoubleFunction<Double> VALUE_TO_KELVIN = val -> (5.0 / 9.0) * (val + 459.67);
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
        return Kelvin.of(VALUE_TO_KELVIN.apply(value))
                .getOrElseThrow(() -> new IllegalStateException());
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

    static Either<InvalidTemperature, Fahrenheit> of(double value) {
        return Kelvin.of(VALUE_TO_KELVIN.apply(value))
                .mapLeft(left -> new InvalidTemperature(value, Fahrenheit.class))
                .map(right -> new Fahrenheit(value));
    }

    @Override
    public String toString() {
        return value + ", " + DEF_SYMBOL;
    }

}
