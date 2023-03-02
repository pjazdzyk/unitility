package com.synerset.unitsystem.thermalconductivity;

import io.vavr.control.Either;

public sealed interface ThermalConductivity permits BtuPerHourFootFahrenheit, WattPerMeterKelvin {

    double getValue();

    String getSymbol();

    WattPerMeterKelvin toWattPerMeterKelvin();

    BtuPerHourFootFahrenheit toBtuPerHourFootFahrenheit();

    static Either<InvalidThermalConductivity, WattPerMeterKelvin> wattPerMeterKelvin(double value) {
        return WattPerMeterKelvin.of(value);
    }

    static Either<InvalidThermalConductivity, BtuPerHourFootFahrenheit> btuPerHourFootFahrenheit(double value) {
        return BtuPerHourFootFahrenheit.of(value);
    }

}
