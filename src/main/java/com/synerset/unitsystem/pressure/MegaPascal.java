package com.synerset.unitsystem.pressure;

import java.util.Objects;

public final class MegaPascal implements Pressure{

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
        Pascal pascal = toPascal();
        return pascal.toHectoPascal();
    }

    @Override
    public MegaPascal toMegaPascal() {
        return this;
    }

    @Override
    public Bar toBar() {
        Pascal pascal = toPascal();
        return pascal.toBar();
    }

    @Override
    public Psi toPsi() {
        Pascal pascal = toPascal();
        return pascal.toPsi();
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

    @Override
    public String toString() {
        return value + DEF_SYMBOL;
    }
}
