package com.synerset.unitsystem.thermalconductivity;

import java.util.Objects;

public final class BtuPerHourFootFahrenheit implements ThermalConductivity {
    private static final String DEF_SYMBOL = "BTU/hftF";

    private final double value;

    private BtuPerHourFootFahrenheit(double value) {
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
        return WattPerMeterKelvin.of(value / 0.57818);
    }

    @Override
    public BtuPerHourFootFahrenheit toBtuPerHourFootFahrenheit() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BtuPerHourFootFahrenheit that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static BtuPerHourFootFahrenheit of(double value) {
        return new BtuPerHourFootFahrenheit(value);
    }

}
