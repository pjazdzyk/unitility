package com.synerset.unitsystem.temperature;

import io.vavr.control.Either;

public interface Temperature {

    double getValue();

    String getSymbol();

    Celsius toCelsius();

    Kelvin toKelvin();

    static Either<InvalidTemperature, Kelvin> kelvin(double value) {
        return Kelvin.of(value);
    }

    static Celsius celsius(double value) {
        return Celsius.of(value);
    }

}