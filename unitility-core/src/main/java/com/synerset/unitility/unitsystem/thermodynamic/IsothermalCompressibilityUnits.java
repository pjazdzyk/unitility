package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum IsothermalCompressibilityUnits implements IsothermalCompressibilityUnit {

    INVERSE_PASCAL("1/Pa", val -> val, val -> val),
    INVERSE_KILOPASCAL("1/kPa", val -> val / 1000.0, val -> val * 1000.0),
    INVERSE_MEGAPASCAL("1/MPa", val -> val / 1_000_000.0, val -> val * 1_000_000.0),
    INVERSE_BAR("1/bar", val -> val / 100_000.0, val -> val * 100_000.0),
    // 1/psi to 1/Pa: divide by the number of Pascals in one PSI
    INVERSE_PSI("1/psi",
            val -> val / 6894.757293168,
            val -> val * 6894.757293168),
    // 1/atm to 1/Pa: divide by the number of Pascals in one ATM
    INVERSE_ATMOSPHERE("1/atm",
            val -> val / 101325.0,
            val -> val * 101325.0);


    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    IsothermalCompressibilityUnits(String symbol, DoubleUnaryOperator toBaseConverter, 
                                   DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() { return symbol; }

    @Override
    public IsothermalCompressibilityUnit getBaseUnit() { return INVERSE_PASCAL; }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static IsothermalCompressibilityUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return INVERSE_PASCAL;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (IsothermalCompressibilityUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + rawSymbol);
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .toString();
    }
}