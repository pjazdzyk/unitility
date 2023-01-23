package com.synerset.unitsystem.temperature;

import io.vavr.control.Either;

import java.util.Objects;

public class Kelvin implements Temperature {

    private static final String DEF_SYMBOL = "K";
    private final double value;

    private Kelvin(double value) {
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
        return this;
    }

    @Override
    public Celsius toCelsius() {
        return Celsius.of(value - 273.15);
    }

    @Override
    public Fahrenheit toFahrenheit() {
        return Fahrenheit.of((9.0 / 5.0) * value - 459.67);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kelvin kelvin)) return false;
        return Double.compare(kelvin.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static Either<InvalidTemperature, Kelvin> of(double value) {
        return value < 0.0
                ? Either.left(new InvalidTemperature())
                : Either.right(new Kelvin(value));
    }

}
