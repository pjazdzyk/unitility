package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum AreaUnits implements AreaUnit {

    SQUARE_METER("m²", 1.0),
    SQUARE_KILOMETER("km²", 1.0E6),
    SQUARE_CENTIMETER("cm²", 1.0E-4),
    SQUARE_MILLIMETER("mm²", 1.0E-6),
    ARE("a", 1.0E2),
    HECTARE("ha", 1.0E4),
    SQUARE_INCH("in²", UnitDefinitions.SQUARE_INCH),
    SQUARE_FOOT("ft²", UnitDefinitions.SQUARE_FOOT),
    SQUARE_YARD("yd²", UnitDefinitions.SQUARE_YARD),
    ACRE("ac", UnitDefinitions.ACRE),
    SQUARE_MILE("mi²", UnitDefinitions.SQUARE_MILE);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    AreaUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

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
