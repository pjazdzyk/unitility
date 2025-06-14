package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

public enum CurvatureUnits implements CurvatureUnit {

    RADIANS_PER_METER("rad/m", val -> val, val -> val),
    RADIANS_PER_FOOT("rad/ft", val -> val / 0.3048, val -> val * 0.3048),
    DEGREES_PER_METER("°/m", Math::toRadians, Math::toDegrees),
    DEGREES_PER_FOOT("°/ft", val -> toRadians(val) / 0.3048, val -> toDegrees(val) * 0.3048),
    DEGREES_PER_HUNDRED_FEET("°/100ft", val -> toRadians(val) / 0.3048 * 100.0, val -> toDegrees(val) * 0.3048 /100.0);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    CurvatureUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public CurvatureUnits getBaseUnit() {
        return RADIANS_PER_METER;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static CurvatureUnits fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return RADIANS_PER_METER;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (CurvatureUnits unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + CurvatureUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifySymbolsOfAngle()
                .toString();
    }
}