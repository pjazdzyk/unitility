package com.synerset.unitility.unitsystem.customunit;

import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;

import java.util.function.DoubleUnaryOperator;

public enum CustomAngleUnits implements AngleUnit {
    REVOLUTIONS("rev", val -> val * 360, val -> val / 360);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    CustomAngleUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public AngleUnit getBaseUnit() {
        return AngleUnits.DEGREES;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static AngleUnit fromSymbol(String rawSymbol) {
        String requestedSymbol = formatSymbol(rawSymbol);
        for (AngleUnit unit : values()) {
            String currentSymbol = formatSymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + AngleUnits.class.getSimpleName());
    }

    private static String formatSymbol(String inputString) {
        return inputString
                .trim()
                .toLowerCase()
                .replace(" ", "")
                .replace("o", "°")
                .replace("deg", "°")
                .replace("radian", "rad");
    }

}