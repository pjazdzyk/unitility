package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum LinearResistanceUnits implements LinearResistanceUnit {

    PASCAL_PER_METER("Pa/m", val -> val, val -> val),
    INCH_OF_WATER_PER_100_FEET("inH₂O/100ft", val -> val * 8.16722, val -> val / 8.16722),
    INCH_OF_MERCURY_PER_100_FEET("inHg/100ft", val -> val * 111.10166332193331, val -> val / 111.10166332193331);

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
            return PASCAL_PER_METER;
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

}
