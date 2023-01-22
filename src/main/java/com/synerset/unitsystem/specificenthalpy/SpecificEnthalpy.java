package com.synerset.unitsystem.specificenthalpy;

public interface SpecificEnthalpy {
    double getValue();

    String getSymbol();

    JoulePerKiloGram toJoulePerKiloGram(double value);

    KiloJoulePerKiloGram toKiloJoulePerKiloGram(double value);

    static JoulePerKiloGram joulePerKiloGram(double value){
        return JoulePerKiloGram.of(value);
    }

    static KiloJoulePerKiloGram kiloJoulePerKiloGram(double value){
        return KiloJoulePerKiloGram.of(value);
    }

}
