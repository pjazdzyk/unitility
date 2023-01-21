package com.synerset.unitsystem.pressure;

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
        return HectoPascal.of(value / 10E2);
    }

    @Override
    public MegaPascal toMegaPascal() {
        return MegaPascal.of(value / 10E6);
    }

    @Override
    public Bar toBar() {
        return Bar.of(value / 10E5);
    }

    static Pascal of(double value){
        return new Pascal(value);
    }

}
