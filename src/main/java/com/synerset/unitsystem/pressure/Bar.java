package com.synerset.unitsystem.pressure;

public class Bar implements Pressure {

    private static final String DEF_SYMBOL = "bar";
    private final double value;

    private Bar(double value) {
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
        return Pascal.of(value * 10E5);
    }

    @Override
    public HectoPascal toHectoPascal() {
        return HectoPascal.of(value * 10E5 / 10E2);
    }

    @Override
    public MegaPascal toMegaPascal() {
        return MegaPascal.of(value * 10E5 / 10E6);
    }

    @Override
    public Bar toBar() {
        return this;
    }

    static Bar of(double value) {
        return new Bar(value);
    }

}
