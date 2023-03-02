package com.synerset.unitsystem.power;

import java.util.Objects;

public final class KiloWatt implements Power {
    private static final String DEF_SYMBOL = "kW";
    private final double value;

    private KiloWatt(double value) {
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
        if (!(o instanceof KiloWatt kiloWatt)) return false;
        return Double.compare(kiloWatt.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public BtuPerHour toBtuPerHour() {
        Watt valInWatt = toWatt();
        return valInWatt.toBtuPerHour();
    }

    @Override
    public String toString() {
        return value + DEF_SYMBOL;
    }

    @Override
    public Watt toWatt() {
        return Watt.of(value / 1E3);
    }

    @Override
    public KiloWatt toKiloWatt() {
        return this;
    }

    static KiloWatt of(double value) {
        return new KiloWatt(value);
    }

}
