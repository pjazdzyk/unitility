package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.CENTI;
import static com.synerset.unitility.unitsystem.util.Constants.KILO;
import static com.synerset.unitility.unitsystem.util.Constants.METERS_PER_FOOT;
import static com.synerset.unitility.unitsystem.util.Constants.METERS_PER_INCH;
import static com.synerset.unitility.unitsystem.util.Constants.METERS_PER_MILE;
import static com.synerset.unitility.unitsystem.util.Constants.METERS_PER_NAUTICAL_MILE;
import static com.synerset.unitility.unitsystem.util.Constants.MILLI;

public enum DistanceUnits implements DistanceUnit {

    METER("m", val -> val, val -> val),
    CENTIMETER("cm", val -> val * CENTI, val -> val / CENTI),
    MILLIMETER("mm", val -> val * MILLI, val -> val / MILLI),
    KILOMETER("km", val -> val * KILO, val -> val / KILO),
    MILE("mi", val -> val * METERS_PER_MILE, val -> val / METERS_PER_MILE),
    NAUTICAL_MILE("nmi", val -> val * METERS_PER_NAUTICAL_MILE, val -> val / METERS_PER_NAUTICAL_MILE),
    FEET("ft", val -> val * METERS_PER_FOOT, val -> val / METERS_PER_FOOT),
    INCH("in", val -> val * METERS_PER_INCH, val -> val / METERS_PER_INCH);

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
                .toString();
    }


}
