package com.synerset.unitsystem.thermalconductivity;

import io.vavr.control.Either;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WattPerMeterKelvin that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + DEF_SYMBOL;
    }

    @Override
    public WattPerMeterKelvin toWattPerMeterKelvin() {
        return this;
    }

    @Override
    public BtuPerHourFootFahrenheit toBtuPerHourFootFahrenheit() {
        return BtuPerHourFootFahrenheit.of(value * 0.57818)
                .getOrElseThrow(() -> new IllegalStateException());
    }

    static Either<InvalidThermalConductivity, WattPerMeterKelvin> of(double value) {
        return value < 0.0
                ? Either.left(new InvalidThermalConductivity())
                : Either.right(new WattPerMeterKelvin(value));
    }
}
