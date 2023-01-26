package com.synerset.unitsystem.density;

public sealed interface Density permits KiloGramPerCubicMeter, PoundPerCubicFoot {
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


