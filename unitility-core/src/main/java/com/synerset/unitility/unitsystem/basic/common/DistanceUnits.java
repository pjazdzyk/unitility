package com.synerset.unitility.unitsystem.basic.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.utils.StringCleaner;

import java.util.function.DoubleUnaryOperator;

public enum DistanceUnits implements DistanceUnit {

    METER("m", val -> val, val -> val),
    CENTIMETER("cm", val -> val / 100, val -> val * 100),
    MILLIMETER("mm", val -> val / 1000, val -> val * 1000),
    KILOMETER("km", val -> val * 1000, val -> val / 1000),
    MILE("mi", val -> val * 1609.344, val -> val / 1609.344),
    FEET("ft", val -> val * 0.3048, val -> val / 0.3048),
    INCH("in", val -> val * 0.0254, val -> val / 0.0254);

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
        String requestedSymbol = unifySymbol(rawSymbol);
        for (DistanceUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemArgumentException("Unsupported symbol: " + rawSymbol + ", class: "
                + DistanceUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringCleaner.of(inputString)
                .trimAndClean()
                .toString();
    }

}
