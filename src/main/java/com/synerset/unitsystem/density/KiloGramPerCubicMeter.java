package com.synerset.unitsystem.density;

import java.util.Objects;

public class KiloGramPerCubicMeter implements Density {

    private static final String DEF_SYMBOL = "kg/m³";
    private final double value;

    private KiloGramPerCubicMeter(double value) {
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
    public KiloGramPerCubicMeter toKiloGramPerCubicMeter() {
        return this;
    }

    @Override
    public PoundPerCubicFoot toPoundPerCubicFoot() {
        return PoundPerCubicFoot.of(value * 0.06243);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KiloGramPerCubicMeter that)) return false;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    static KiloGramPerCubicMeter of(double value){
        return new KiloGramPerCubicMeter(value);
    }

}
