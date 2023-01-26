package com.synerset.unitsystem.pressure;

import java.util.Objects;

public final class HectoPascal implements Pressure {

    private static final String DEF_SYMBOL = "hPa";
    private final double value;

    private HectoPascal(double value) {
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
        return Pascal.of(value * 1E2);
    }

    @Override
    public HectoPascal toHectoPascal() {
        return this;
    }

    @Override
    public MegaPascal toMegaPascal() {
        Pascal pascal = toPascal();
        return pascal.toMegaPascal();
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
        if (!(o instanceof HectoPascal that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static HectoPascal of(double value) {
        return new HectoPascal(value);
    }

}
