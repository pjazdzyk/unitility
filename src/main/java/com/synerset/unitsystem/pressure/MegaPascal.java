package com.synerset.unitsystem.pressure;

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
        return Pascal.of(value * 10E6);
    }

    @Override
    public HectoPascal toHectoPascal() {
        return HectoPascal.of(value * 10E6 / 10E2);
    }

    @Override
    public MegaPascal toMegaPascal() {
        return this;
    }

    @Override
    public Bar toBar() {
        return Bar.of(value * 10E6 / 10E5);
    }

    public static MegaPascal of(double value) {
        return new MegaPascal(value);
    }

}
