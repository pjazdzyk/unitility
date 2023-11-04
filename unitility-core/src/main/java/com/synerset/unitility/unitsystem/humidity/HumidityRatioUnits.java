package com.synerset.unitility.unitsystem.humidity;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.function.DoubleUnaryOperator;

public enum HumidityRatioUnits implements HumidityRatioUnit {

    KILOGRAM_PER_KILOGRAM("kg/kg", val -> val, val -> val),
    POUND_PER_POUND("lb/lb", val -> val / 2.20462262184878, val -> val * 2.20462262184878);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    HumidityRatioUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public HumidityRatioUnit getBaseUnit() {
        return KILOGRAM_PER_KILOGRAM;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static HumidityRatioUnit fromSymbol(String rawSymbol) {
        String requestedSymbol = formatSymbol(rawSymbol);
        for (HumidityRatioUnits unit : values()) {
            String currentSymbol = formatSymbol(unit.symbol);
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemArgumentException("Unsupported symbol: " + rawSymbol + ", class: "
                + HumidityRatioUnits.class.getSimpleName());
    }

    private static String formatSymbol(String inputString) {
        return inputString
                .trim()
                .toLowerCase()
                .replace(" ", "")
                .replace("/", "p");
    }

}
