package com.synerset.unitsystem.density;

public interface Density {
    double getValue();

    String getSymbol();

    KiloGramPerCubicMeter toKiloGramPerCubicMeter();

    PoundPerCubicFoot toPoundPerCubicFoot();

    static KiloGramPerCubicMeter kiloGramPerCubicMeter(double value) {
        return KiloGramPerCubicMeter.of(value);
    }

    static PoundPerCubicFoot poundPerCubicFoot(double value) {
        return PoundPerCubicFoot.of(value);
    }

}


