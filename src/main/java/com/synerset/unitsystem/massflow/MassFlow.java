package com.synerset.unitsystem.massflow;

public interface MassFlow {
    double getValue();

    String getSymbol();

    KiloGramPerSecond toKiloGramPerSecond();

    KiloGramPerHour toKiloGramPerHour();

    PoundPerSecond toPoundPerSecond();

}
