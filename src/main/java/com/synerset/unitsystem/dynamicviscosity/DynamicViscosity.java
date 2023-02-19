package com.synerset.unitsystem.dynamicviscosity;

import io.vavr.control.Either;

public sealed interface DynamicViscosity permits KiloGramPerMeterSecond, PascalSecond, Poise {

    double getValue();

    String getSymbol();

    PascalSecond toPascalSecond();

    KiloGramPerMeterSecond toKiloGramPerMeterSecond();

    Poise toPoise();

    static Either<InvalidDynamicViscosity, PascalSecond> pascalSecond(double value){
        return PascalSecond.of(value);
    }

    static Either<InvalidDynamicViscosity, KiloGramPerMeterSecond> kiloGramPerMeterSecond(double value){
        return KiloGramPerMeterSecond.of(value);
    }

    static Either<InvalidDynamicViscosity, Poise> poise(double value){
        return Poise.of(value);
    }

}
