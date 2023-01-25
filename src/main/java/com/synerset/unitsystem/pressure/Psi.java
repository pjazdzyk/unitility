package com.synerset.unitsystem.pressure;

import java.util.Objects;

public class Psi implements Pressure{
    private static final String DEF_SYMBOL = "Psi";
    private final double value;

    private Psi(double value) {
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
        return Pascal.of(value / 0.0001450377);
    }

    @Override
    public HectoPascal toHectoPascal() {
        Pascal pascal = toPascal();
        return pascal.toHectoPascal();
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
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Psi psi)) return false;
        return Double.compare(psi.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static Psi of(double value){
        return new Psi(value);
    }

}
