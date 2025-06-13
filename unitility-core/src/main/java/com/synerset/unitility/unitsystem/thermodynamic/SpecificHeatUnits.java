package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.IMPERIAL_SPECIFIC_HEAT_RATIO;
import static com.synerset.unitility.unitsystem.util.Constants.KILO;

public enum SpecificHeatUnits implements SpecificHeatUnit {

    JOULES_PER_KILOGRAM_KELVIN("J/(kg·K)", val -> val, val -> val),
    KILOJOULES_PER_KILOGRAM_KELVIN("kJ/(kg·K)", val -> val * KILO, val -> val / KILO),
    BTU_PER_POUND_FAHRENHEIT("BTU/(lb·°F)", val -> val * IMPERIAL_SPECIFIC_HEAT_RATIO, val -> val / IMPERIAL_SPECIFIC_HEAT_RATIO);

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
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return JOULES_PER_KILOGRAM_KELVIN;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (SpecificHeatUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + SpecificHeatUnits.class.getSimpleName());
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