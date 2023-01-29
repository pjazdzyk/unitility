package com.synerset.unitsystem.specificheat;

import io.vavr.control.Either;

public sealed interface SpecificHeat permits JoulePerKilogramKelvin, KiloJoulePerKilogramKelvin {

    double getValue();

    String getSymbol();

    JoulePerKilogramKelvin toJoulePerKilogramKelvin();

    KiloJoulePerKilogramKelvin toKiloJoulePerKilogramKelvin();

    static Either<InvalidSpecificHeat, JoulePerKilogramKelvin> joulePerKilogramKelvin(double value){
        return JoulePerKilogramKelvin.of(value);
    }

    static Either<InvalidSpecificHeat, KiloJoulePerKilogramKelvin> kiloJoulePerKilogramKelvin(double value){
        return KiloJoulePerKilogramKelvin.of(value);
    }

}
