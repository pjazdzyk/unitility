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

    static Either<InvalidTemperature, Celsius> celsius(double value) {
        return Celsius.of(value);
    }

    static Either<InvalidTemperature, Fahrenheit> fahrenheit(double value){
        return Fahrenheit.of(value);
    }

}