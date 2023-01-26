package com.synerset.unitsystem.dynamicviscosity;

public sealed interface DynamicViscosity permits KiloGramPerMeterSecond, PascalSecond, Poise {

    double getValue();

    String getSymbol();

    PascalSecond toPascalSecond();

    KiloGramPerMeterSecond toKiloGramPerMeterSecond();

    Poise toPoise();

    static PascalSecond pascalSecond(double value){
        return PascalSecond.of(value);
    }

    static KiloGramPerMeterSecond kiloGramPerMeterSecond(double value){
        return KiloGramPerMeterSecond.of(value);
    }

    static Poise poise(double value){
        return Poise.of(value);
    }

}
