package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum CurvatureUnits implements CurvatureUnit {

    RADIANS_PER_METER("rad/m", 1.0),
    RADIANS_PER_FOOT("rad/ft", UnitDefinitions.RADIAN_PER_FOOT),
    // Kept on Math.toRadians / Math.toDegrees, as AngleUnits.DEGREES is. Linear in effect.
    DEGREES_PER_METER("°/m", Math::toRadians, Math::toDegrees),
    DEGREES_PER_FOOT("°/ft", UnitDefinitions.DEGREE_PER_FOOT),
    DEGREES_PER_HUNDRED_FEET("°/100ft", UnitDefinitions.DEGREE_PER_HUNDRED_FEET);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    CurvatureUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

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
                .unifyMultiAndDiv()
                .toString();
    }
}