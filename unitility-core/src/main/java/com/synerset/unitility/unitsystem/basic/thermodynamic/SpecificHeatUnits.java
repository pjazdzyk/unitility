package com.synerset.unitility.unitsystem.basic.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.utils.SymbolCleaner;

import java.util.function.DoubleUnaryOperator;

public enum SpecificHeatUnits implements SpecificHeatUnit {

    JOULES_PER_KILOGRAM_KELVIN("J/(kg·K)", val -> val, val -> val),
    KILOJOULES_PER_KILOGRAM_KELVIN("kJ/(kg·K)", val -> val * 1000, val -> val / 1000),
    BTU_PER_POUND_FAHRENHEIT("BTU/(lb·°F)", val -> val * 4186.7999934703, val -> val / 4186.7999934703);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    SpecificHeatUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public SpecificHeatUnit getBaseUnit() {
        return JOULES_PER_KILOGRAM_KELVIN;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static SpecificHeatUnit fromSymbol(String rawSymbol) {
        String requestedSymbol = unifySymbol(rawSymbol);
        for (SpecificHeatUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemArgumentException("Unsupported symbol: " + rawSymbol + ", class: "
                + SpecificHeatUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return SymbolCleaner.of(inputString)
                .trimAndClean()
                .unifyMultiAndDiv()
                .removeParentheses()
                .unifySymbolsOfAngle()
                .toString();
    }

}