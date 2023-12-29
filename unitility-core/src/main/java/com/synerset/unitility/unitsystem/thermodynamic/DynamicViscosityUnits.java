package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.utils.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum DynamicViscosityUnits implements DynamicViscosityUnit {

    KILOGRAM_PER_METER_SECOND("kg/(m·s)", val -> val, val -> val),
    PASCAL_SECOND("Pa·s", val -> val, val -> val),
    POISE("P", val -> val * 0.1, val -> val / 0.1);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    DynamicViscosityUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public DynamicViscosityUnit getBaseUnit() {
        return KILOGRAM_PER_METER_SECOND;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static DynamicViscosityUnit fromSymbol(String rawSymbol) {
        String requestedSymbol = unifySymbol(rawSymbol);
        for (DynamicViscosityUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemArgumentException("Unsupported symbol: " + rawSymbol + ", class: "
                + DynamicViscosityUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .removeParentheses()
                .toString();
    }

}