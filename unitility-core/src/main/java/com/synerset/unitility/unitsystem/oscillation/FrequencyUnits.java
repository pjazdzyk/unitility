package com.synerset.unitility.unitsystem.oscillation;

import com.synerset.unitility.unitsystem.Constants;

import java.util.function.DoubleUnaryOperator;

public enum FrequencyUnits implements FrequencyUnit {

    HERTZ("Hz", val -> val, val -> val),
    KILOHERTZ("kHz", val -> val * Constants.KILO, val -> val / Constants.KILO),
    MEGAHERTZ("MHz", val -> val * Constants.MEGA, val -> val / Constants.MEGA),
    GIGAHERTZ("GHz", val -> val * Constants.GIGA, val -> val / Constants.GIGA),
    CYCLES_PER_MINUTE("cpm", val -> val / Constants.SECONDS_IN_MINUTE, val -> val * Constants.SECONDS_IN_MINUTE);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    FrequencyUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public FrequencyUnits getBaseUnit() {
        return HERTZ;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static FrequencyUnits fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return HERTZ;
        }
        String s = rawSymbol.trim().toLowerCase();
        for (FrequencyUnits unit : values()) {
            if (unit.getSymbol().toLowerCase().equals(s)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Unsupported unit symbol: " + rawSymbol);
    }
}
