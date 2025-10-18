package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.Constants;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum DistanceUnits implements DistanceUnit {

    METER("m", val -> val, val -> val),
    CENTIMETER("cm", val -> val * Constants.CENTI, val -> val / Constants.CENTI),
    MILLIMETER("mm", val -> val * Constants.MILLI, val -> val / Constants.MILLI),
    KILOMETER("km", val -> val * Constants.KILO, val -> val / Constants.KILO),
    MILE("mi", val -> val * 1609.344, val -> val / 1609.344),
    NAUTICAL_MILE("nmi", val -> val * 1852, val -> val / 1852),
    FEET("ft", val -> val * 0.3048, val -> val / 0.3048),
    INCH("in", val -> val * 0.0254, val -> val / 0.0254),
    YARD("yd", val -> val * 0.9144, val -> val / 0.9144),
    DECAMETER("dam", val -> val * Constants.DECA, val -> val / Constants.DECA),
    HECTOMETER("hm", val -> val * Constants.HECTO, val -> val / Constants.HECTO),
    DATAMILE("datmi", val -> val * 1828.8, val -> val / 1828.8);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    DistanceUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public DistanceUnit getBaseUnit() {
        return METER;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static DistanceUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return METER;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (DistanceUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + DistanceUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .replace("da", "dam")
                .replace("dm", "datmi")
                .replace("datami", "datmi")
                .toString();
    }


}
