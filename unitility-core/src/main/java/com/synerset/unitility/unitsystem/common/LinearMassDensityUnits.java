package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum LinearMassDensityUnits implements LinearMassDensityUnit {

    KILOGRAM_PER_METER("kg/m", val -> val, val -> val),
    TONNE_PER_METER("t/m", val -> val * 1000.0, val -> val / 1000.0),
    OUNCE_PER_FOOT("oz/ft", val -> val * (0.028349523125 / 0.3048), val -> val / (0.028349523125 / 0.3048)),
    POUND_PER_FOOT("lb/ft", val -> val * (0.45359237 / 0.3048), val -> val / (0.45359237 / 0.3048));

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    LinearMassDensityUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public LinearMassDensityUnit getBaseUnit() {
        return KILOGRAM_PER_METER;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static LinearMassDensityUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return KILOGRAM_PER_METER;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (LinearMassDensityUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                                           + LinearMassDensityUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .toString();
    }


}
