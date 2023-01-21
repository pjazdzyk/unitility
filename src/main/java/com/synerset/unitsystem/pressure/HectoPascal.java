package com.synerset.unitsystem.pressure;

public class HectoPascal implements Pressure {

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
        return Pascal.of(value * 10E2);
    }

    @Override
    public HectoPascal toHectoPascal() {
        return this;
    }

    @Override
    public MegaPascal toMegaPascal() {
        return MegaPascal.of(value * 10E2 / 10E6);
    }

    @Override
    public Bar toBar() {
        return Bar.of(10E2 * value / 10E5);
    }

    static HectoPascal of(double value) {
        return new HectoPascal(value);
    }

}
