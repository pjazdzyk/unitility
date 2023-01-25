package com.synerset.unitsystem.pressure;

public interface Pressure {

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

}