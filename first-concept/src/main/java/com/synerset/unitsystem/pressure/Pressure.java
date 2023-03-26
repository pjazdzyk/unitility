package com.synerset.unitsystem.pressure;

public sealed interface Pressure permits Bar, HectoPascal, MegaPascal, Pascal, Psi {

    double getValue();

    String getSymbol();

    Pascal toPascal();

    HectoPascal toHectoPascal();

    MegaPascal toMegaPascal();

    Bar toBar();

    Psi toPsi();

    static Pascal pascal(double value) {
        return Pascal.of(value);
    }

    static HectoPascal hectoPascal(double value) {
        return HectoPascal.of(value);
    }

    static MegaPascal megaPascal(double value){
        return MegaPascal.of(value);
    }

    static Bar bar(double value) {
        return Bar.of(value);
    }

    static Psi psi(double value) {
        return Psi.of(value);
    }

}