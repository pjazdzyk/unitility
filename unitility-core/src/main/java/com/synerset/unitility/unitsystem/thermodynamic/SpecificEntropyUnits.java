package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum SpecificEntropyUnits implements SpecificEntropyUnit {
    
    JOULE_PER_KILOGRAM_KELVIN("J/(kg·K)", val -> val, val -> val),
    KILOJOULE_PER_KILOGRAM_KELVIN("kJ/(kg·K)", val -> val * 1000.0, val -> val / 1000.0),
    MILLIJOULE_PER_GRAM_KELVIN("mJ/(g·K)", val -> val, val -> val),
    MEGAJOULE_PER_TONNE_KELVIN("MJ/(t·K)", val -> val * 1000.0, val -> val / 1000.0),
    BTU_PER_POUND_RANKINE("BTU/(lb·°R)", val -> val * 4186.8, val -> val / 4186.8),
    BTU_PER_POUND_FAHRENHEIT("BTU/(lb·°F)", val -> val * 4186.8, val -> val / 4186.8);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    SpecificEntropyUnits(String symbol, DoubleUnaryOperator toBaseConverter, 
                        DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() { return symbol; }

    @Override
    public SpecificEntropyUnit getBaseUnit() { return JOULE_PER_KILOGRAM_KELVIN; }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static SpecificEntropyUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return JOULE_PER_KILOGRAM_KELVIN;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (SpecificEntropyUnit unit : values()) {
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
                .dropParentheses()
                .unifySymbolsOfAngle()
                .toString();
    }
}