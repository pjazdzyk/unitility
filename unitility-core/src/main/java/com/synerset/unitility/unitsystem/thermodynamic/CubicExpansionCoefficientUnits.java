package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum CubicExpansionCoefficientUnits implements CubicExpansionCoefficientUnit {

    INVERSE_KELVIN("1/K", val -> val, val -> val),
    INVERSE_CELSIUS("1/°C", val -> val, val -> val),
    INVERSE_MILLIKELVIN("1/mK", val -> val * 1000.0, val -> val / 1000.0),
    INVERSE_RANKINE("1/°R", val -> val * 1.8, val -> val / 1.8),
    INVERSE_FAHRENHEIT("1/°F", val -> val * 1.8, val -> val / 1.8);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    CubicExpansionCoefficientUnits(String symbol, DoubleUnaryOperator toBaseConverter,
                                   DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public CubicExpansionCoefficientUnit getBaseUnit() {
        return INVERSE_KELVIN;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static CubicExpansionCoefficientUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return INVERSE_KELVIN;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (CubicExpansionCoefficientUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + rawSymbol);
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .dropDegreeSymbols()
                .toString();
    }
}
