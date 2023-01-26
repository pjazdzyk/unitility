package com.synerset.unitsystem.thermalconductivity;

import java.util.Objects;

public final class WattPerMeterKelvin implements ThermalConductivity {

    private static final String DEF_SYMBOL = "W/mK";

    private final double value;

    private WattPerMeterKelvin(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public String getSymbol() {
        return DEF_SYMBOL;
    }

    @Override
    public WattPerMeterKelvin toWattPerMeterKelvin() {
        return this;
    }

    @Override
    public BtuPerHourFootFahrenheit toBtuPerHourFootFahrenheit() {
        return BtuPerHourFootFahrenheit.of(value * 0.57818);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WattPerMeterKelvin that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static WattPerMeterKelvin of(double value) {
        return new WattPerMeterKelvin(value);
    }

}
