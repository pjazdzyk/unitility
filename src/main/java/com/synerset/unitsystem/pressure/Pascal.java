package com.synerset.unitsystem.pressure;

import java.util.Objects;

public class Pascal implements Pressure{

    private static final String DEF_SYMBOL = "Pa";
    private final double value;

    private Pascal(double value) {
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
        return this;
    }

    @Override
    public HectoPascal toHectoPascal() {
        return HectoPascal.of(value / 1E2);
    }

    @Override
    public MegaPascal toMegaPascal() {
        return MegaPascal.of(value / 1E6);
    }

    @Override
    public Bar toBar() {
        return Bar.of(value / 1E5);
    }

    @Override
    public Psi toPsi() {
        return Psi.of(value * 0.0001450377);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pascal pascal)) return false;
        return Double.compare(pascal.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static Pascal of(double value){
        return new Pascal(value);
    }

  }
