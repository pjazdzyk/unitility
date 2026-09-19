package com.synerset.unitility.unitsystem.oscillation;

import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum FrequencyUnits implements FrequencyUnit {

    HERTZ("Hz", 1.0),
    KILOHERTZ("kHz", 1.0E3),
    MEGAHERTZ("MHz", 1.0E6),
    GIGAHERTZ("GHz", 1.0E9),
    CYCLES_PER_MINUTE("cpm", UnitDefinitions.PER_MINUTE);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    FrequencyUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

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
