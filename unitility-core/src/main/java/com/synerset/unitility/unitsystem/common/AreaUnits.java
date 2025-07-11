package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum AreaUnits implements AreaUnit {

    SQUARE_METER("m²", val -> val, val -> val),
    SQUARE_KILOMETER("km²", val -> val * 1_000_000, val -> val / 1_000_000),
    SQUARE_CENTIMETER("cm²", val -> val / 10_000, val -> val * 10_000),
    SQUARE_MILLIMETER("mm²", val -> val / 1_000_000, val -> val * 1_000_000),
    ARE("a", val -> val * 100, val -> val / 100),
    HECTARE("ha", val -> val * 10_000, val -> val / 10_000),
    SQUARE_INCH("in²", val -> val * 0.00064516, val -> val / 0.00064516),
    SQUARE_FOOT("ft²", val -> val * 0.09290304, val -> val / 0.09290304),
    SQUARE_YARD("yd²", val -> val * 0.83612736, val -> val / 0.83612736),
    ACRE("ac", val -> val * 4046.8564224, val -> val / 4046.8564224),
    SQUARE_MILE("mi²", val -> val * 2589988.110336, val -> val / 2589988.110336);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    AreaUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public AreaUnits getBaseUnit() {
        return SQUARE_METER;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static AreaUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return SQUARE_METER;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (AreaUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + AreaUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyAerialAndVol()
                .toString();
    }


}
