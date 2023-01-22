package com.synerset.unitsystem.pressure;

import java.util.Objects;

public class MegaPascal implements Pressure{

    private static final String DEF_SYMBOL = "MPa";
    private final double value;

    private MegaPascal(double value) {
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
    public Pascal toPascal() {
        return Pascal.of(value * 1E6);
    }

    @Override
    public HectoPascal toHectoPascal() {
        return HectoPascal.of(toPascal().getValue() / 1E2);
    }

    @Override
    public MegaPascal toMegaPascal() {
        return this;
    }

    @Override
    public Bar toBar() {
        return Bar.of(toPascal().getValue() / 1E5);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MegaPascal that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static MegaPascal of(double value) {
        return new MegaPascal(value);
    }

}
