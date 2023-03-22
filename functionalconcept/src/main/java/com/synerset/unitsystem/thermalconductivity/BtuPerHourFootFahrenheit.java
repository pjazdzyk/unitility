package com.synerset.unitsystem.thermalconductivity;

import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.DoubleFunction;

public final class BtuPerHourFootFahrenheit implements ThermalConductivity {
    private static final String DEF_SYMBOL = "BTU/hftF";
    private static final DoubleFunction<Double> VALUE_TO_W_MK = val -> val / 0.57818;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BtuPerHourFootFahrenheit that)) return false;
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
        return WattPerMeterKelvin.of(VALUE_TO_W_MK.apply(value))
                .getOrElseThrow(() -> new IllegalStateException());
    }

    @Override
    public BtuPerHourFootFahrenheit toBtuPerHourFootFahrenheit() {
        return this;
    }

    static Either<InvalidThermalConductivity, BtuPerHourFootFahrenheit> of(double value) {
        return WattPerMeterKelvin.of(VALUE_TO_W_MK.apply(value))
                .mapLeft(l -> new InvalidThermalConductivity())
                .map(r -> new BtuPerHourFootFahrenheit(value));
    }

}
