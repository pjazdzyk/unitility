package com.synerset.unitsystem.massflow;

import io.vavr.control.Either;

public sealed interface MassFlow permits KiloGramPerHour, KiloGramPerSecond, PoundPerSecond {
    double getValue();

    String getSymbol();

    KiloGramPerSecond toKiloGramPerSecond();

    KiloGramPerHour toKiloGramPerHour();

    PoundPerSecond toPoundPerSecond();

    static Either<InvalidMassFlow, KiloGramPerSecond> kiloGramPerSecond(double value){
        return KiloGramPerSecond.of(value);
    }

    static Either<InvalidMassFlow, KiloGramPerHour> kiloGramPerHour(double value){
        return KiloGramPerHour.of(value);
    }

    static Either<InvalidMassFlow, PoundPerSecond> poundPerSecond(double value){
        return PoundPerSecond.of(value);
    }

}
