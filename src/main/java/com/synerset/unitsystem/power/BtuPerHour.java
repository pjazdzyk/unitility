package com.synerset.unitsystem.power;

import java.util.Objects;

public final class BtuPerHour implements Power{
    private static final String DEF_SYMBOL = "BTU/hr";
    private final double value;

    private BtuPerHour(double value) {
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
        if (!(o instanceof BtuPerHour that)) return false;
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
    public Watt toWatt() {
        return Watt.of(value / 3.412141633);
    }

    @Override
    public KiloWatt toKiloWatt() {
        Watt valInWatt = toWatt();
        return valInWatt.toKiloWatt();
    }

    @Override
    public BtuPerHour toBtuPerHour() {
        return this;
    }

    static BtuPerHour of(double value){
        return new BtuPerHour(value);
    }

}
