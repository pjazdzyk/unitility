package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.PASCAL_PER_INCH_OF_MERCURY_PER_100_FEET;
import static com.synerset.unitility.unitsystem.util.Constants.PASCAL_PER_INCH_OF_WATER_PER_100_FEET;

public enum LinearResistanceUnits implements LinearResistanceUnit {

    PASCAL_PER_METER("Pa/m", val -> val, val -> val),
    INCH_OF_WATER_PER_100_FEET("inH₂O/100ft", val -> val * PASCAL_PER_INCH_OF_WATER_PER_100_FEET, val -> val / PASCAL_PER_INCH_OF_WATER_PER_100_FEET),
    INCH_OF_MERCURY_PER_100_FEET("inHg/100ft", val -> val * PASCAL_PER_INCH_OF_MERCURY_PER_100_FEET, val -> val / PASCAL_PER_INCH_OF_MERCURY_PER_100_FEET);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    LinearResistanceUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public LinearResistanceUnit getBaseUnit() {
        return PASCAL_PER_METER;  // Define Pa/m as the base unit
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static LinearResistanceUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return getDefaultUnit();
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (LinearResistanceUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                                           + LinearResistanceUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyAerialAndVol()
                .unifyMultiAndDiv()
                .toString();
    }

    public static LinearResistanceUnit getDefaultUnit() {
        return PASCAL_PER_METER;  // Default to Pa/m
    }
}