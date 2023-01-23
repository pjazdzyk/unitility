package com.synerset.unitsystem.power;

import java.util.Objects;

public class BtuPerHour implements Power{
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
    public Watt toWatt() {
        return Watt.of(value / 3.412141633);
    }

    @Override
    public KiloWatt toKiloWatt() {
        double valInWatt = toWatt().getValue();
        return KiloWatt.of(valInWatt / 1E3);
    }

    @Override
    public BtuPerHour toBtuPerHour() {
        return this;
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

    static BtuPerHour of(double value){
        return new BtuPerHour(value);
    }

}
