package com.synerset.unitsystem.power;

public interface Power {

    double getValue();

    String getSymbol();

    Watt toWatt();

    KiloWatt toKiloWatt();

    BtuPerHour toBtuPerHour();

}
