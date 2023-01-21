package com.synerset.unitsystem.specificheat;

public interface SpecificHeat {

    double getValue();

    String getSymbol();

    JoulePerKilogramKelvin toJoulePerKilogramKelvin();

    KiloJoulePerKilogramKelvin toKiloJoulePerKilogramKelvin();

    static JoulePerKilogramKelvin joulePerKilogramKelvin(double value){
        return JoulePerKilogramKelvin.of(value);
    }

    static KiloJoulePerKilogramKelvin kiloJoulePerKilogramKelvin(double value){
        return KiloJoulePerKilogramKelvin.of(value);
    }

}
