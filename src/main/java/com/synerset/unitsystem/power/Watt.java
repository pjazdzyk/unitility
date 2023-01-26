package com.synerset.unitsystem.power;

import java.util.Objects;

public final class Watt implements Power {
    private static final String DEF_SYMBOL = "W";
    private final double value;

    private Watt(double value) {
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
    public Watt toWatt() {
        return this;
    }

    @Override
    public KiloWatt toKiloWatt() {
        return KiloWatt.of(value / 1E3);
    }

    @Override
    public BtuPerHour toBtuPerHour() {
        return BtuPerHour.of(value * 3.412141633);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Watt watt)) return false;
        return Double.compare(watt.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static Watt of(double value) {
        return new Watt(value);
    }

}
