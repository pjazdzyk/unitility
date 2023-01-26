package com.synerset.unitsystem.massflow;

public sealed interface MassFlow permits KiloGramPerHour, KiloGramPerSecond, PoundPerSecond {
    double getValue();

    String getSymbol();

    KiloGramPerSecond toKiloGramPerSecond();

    KiloGramPerHour toKiloGramPerHour();

    PoundPerSecond toPoundPerSecond();

    static KiloGramPerSecond kiloGramPerSecond(double value){
        return KiloGramPerSecond.of(value);
    }

    static KiloGramPerHour kiloGramPerHour(double value){
        return KiloGramPerHour.of(value);
    }

    static PoundPerSecond poundPerSecond(double value){
        return PoundPerSecond.of(value);
    }

}
