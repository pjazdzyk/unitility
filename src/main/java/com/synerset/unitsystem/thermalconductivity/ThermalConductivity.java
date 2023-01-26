package com.synerset.unitsystem.thermalconductivity;

public sealed interface ThermalConductivity permits BtuPerHourFootFahrenheit, WattPerMeterKelvin {

    double getValue();

    String getSymbol();

    WattPerMeterKelvin toWattPerMeterKelvin();

    BtuPerHourFootFahrenheit toBtuPerHourFootFahrenheit();

    static WattPerMeterKelvin wattPerMeterKelvin(double value) {
        return WattPerMeterKelvin.of(value);
    }

    static BtuPerHourFootFahrenheit btuPerHourFootFahrenheit(double value) {
        return BtuPerHourFootFahrenheit.of(value);
    }

}
