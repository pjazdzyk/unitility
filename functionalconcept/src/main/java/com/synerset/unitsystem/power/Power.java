package com.synerset.unitsystem.power;

public sealed interface Power permits BtuPerHour, KiloWatt, Watt {

    double getValue();

    String getSymbol();

    Watt toWatt();

    KiloWatt toKiloWatt();

    BtuPerHour toBtuPerHour();

    static Watt watt(double value){
        return Watt.of(value);
    }
    static KiloWatt kiloWatt(double value){
        return KiloWatt.of(value);
    }
    static BtuPerHour btuPerHour(double value){
        return BtuPerHour.of(value);
    }

}
