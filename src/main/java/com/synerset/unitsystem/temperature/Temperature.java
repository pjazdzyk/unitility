package com.synerset.unitsystem.temperature;

import io.vavr.control.Either;

public sealed interface Temperature permits Celsius, Fahrenheit, Kelvin {

    double getValue();

    Kelvin toKelvin();

    String getSymbol();

    Celsius toCelsius();

    Fahrenheit toFahrenheit();

    static Either<InvalidTemperature, Kelvin> kelvin(double value) {
        return Kelvin.of(value);
    }

    static Celsius celsius(double value) {
        return Celsius.of(value);
    }

    static Fahrenheit fahrenheit(double value){
        return Fahrenheit.of(value);
    }

}