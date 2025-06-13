package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.SQUARE_ACRES_PER_SQUARE_METER;
import static com.synerset.unitility.unitsystem.util.Constants.SQUARE_CENTI;
import static com.synerset.unitility.unitsystem.util.Constants.SQUARE_DECA;
import static com.synerset.unitility.unitsystem.util.Constants.SQUARE_FEET_PER_SQUARE_METER;
import static com.synerset.unitility.unitsystem.util.Constants.SQUARE_HECTO;
import static com.synerset.unitility.unitsystem.util.Constants.SQUARE_INCHES_PER_SQUARE_METER;
import static com.synerset.unitility.unitsystem.util.Constants.SQUARE_KILO;
import static com.synerset.unitility.unitsystem.util.Constants.SQUARE_MILES_PER_SQUARE_METER;
import static com.synerset.unitility.unitsystem.util.Constants.SQUARE_MILLI;
import static com.synerset.unitility.unitsystem.util.Constants.SQUARE_YARD_PER_SQUARE_METER;

public enum AreaUnits implements AreaUnit {

    SQUARE_METER("m²", val -> val, val -> val),
    SQUARE_KILOMETER("km²", val -> val * SQUARE_KILO, val -> val / SQUARE_KILO),
    SQUARE_CENTIMETER("cm²", val -> val * SQUARE_CENTI, val -> val / SQUARE_CENTI),
    SQUARE_MILLIMETER("mm²", val -> val * SQUARE_MILLI, val -> val / SQUARE_MILLI),
    ARE("a", val -> val * SQUARE_DECA, val -> val / SQUARE_DECA),
    HECTARE("ha", val -> val * SQUARE_HECTO, val -> val / SQUARE_HECTO),
    SQUARE_INCH("in²", val -> val / SQUARE_INCHES_PER_SQUARE_METER, val -> val * SQUARE_INCHES_PER_SQUARE_METER),
    SQUARE_FOOT("ft²", val -> val / SQUARE_FEET_PER_SQUARE_METER, val -> val * SQUARE_FEET_PER_SQUARE_METER ),
    SQUARE_YARD("yd²", val -> val / SQUARE_YARD_PER_SQUARE_METER, val -> val * SQUARE_YARD_PER_SQUARE_METER),
    ACRE("ac", val -> val / SQUARE_ACRES_PER_SQUARE_METER, val -> val * SQUARE_ACRES_PER_SQUARE_METER),
    SQUARE_MILE("mi²", val -> val / SQUARE_MILES_PER_SQUARE_METER, val -> val * SQUARE_MILES_PER_SQUARE_METER);

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
