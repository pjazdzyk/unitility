package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.utils.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum MassUnits implements MassUnit {

    KILOGRAM("kg", val -> val, val -> val),
    GRAM("g", val -> val * 0.001, val -> val / 0.001),
    MILLIGRAM("mg", val -> val * 0.000001, val -> val / 0.000001),
    TONNE_SI("t", val -> val * 1000.0, val -> val / 1000.0),
    OUNCE("oz", val -> val * 0.028349523125, val -> val / 0.028349523125),
    POUND("lb", val -> val * 0.45359237, val -> val / 0.45359237);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    MassUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public MassUnit getBaseUnit() {
        return KILOGRAM;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static MassUnit fromSymbol(String rawSymbol) {
        String requestedSymbol = unifySymbol(rawSymbol);
        for (MassUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemArgumentException("Unsupported symbol: " + rawSymbol + ", class: "
                + MassUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .toString();
    }

}
