package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.function.DoubleUnaryOperator;

public enum AreaUnits implements Unit {

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

    public static AreaUnits fromSymbol(String rawSymbol) {
        String inputSymbolWithoutDegreesSign = formatSymbolInput(rawSymbol);
        for (AreaUnits unit : values()) {
            String enumSymbolWithoutDegreesSing = formatSymbolInput(unit.symbol);
            if (enumSymbolWithoutDegreesSing.equalsIgnoreCase(inputSymbolWithoutDegreesSign)) {
                return unit;
            }
        }
        throw new UnitSystemArgumentException("Unsupported symbol: " + rawSymbol + ", class: "
                + AreaUnits.class.getSimpleName());
    }

    private static String formatSymbolInput(String inputString) {
        return inputString
                .trim()
                .toLowerCase()
                .replace(" ", "")
                .replace("2", "²");
    }

}