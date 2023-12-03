package com.synerset.unitility.unitsystem.humidity;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.function.DoubleUnaryOperator;

public enum RelativeHumidityUnits implements RelativeHumidityUnit {

    PERCENT("%", val -> val, val -> val),
    DECIMAL("", val -> val * 100, val -> val / 100);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    RelativeHumidityUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public RelativeHumidityUnit getBaseUnit() {
        return PERCENT;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static RelativeHumidityUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return DECIMAL;
        }
        String requestedSymbol = formatSymbol(rawSymbol);
        for (RelativeHumidityUnit unit : values()) {
            String currentSymbol = formatSymbol(unit.getSymbol());
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
                .replace(" ", "");
    }

}