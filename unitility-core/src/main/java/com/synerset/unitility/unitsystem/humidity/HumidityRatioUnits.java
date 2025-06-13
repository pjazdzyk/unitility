package com.synerset.unitility.unitsystem.humidity;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.KILO;
import static com.synerset.unitility.unitsystem.util.Constants.POUNDS_PER_KILOGRAM;

public enum HumidityRatioUnits implements HumidityRatioUnit {

    KILOGRAM_PER_KILOGRAM("kg/kg", val -> val, val -> val),
    GRAM_PER_KILOGRAM("g/kg", val -> val / KILO, val -> val * KILO),
    POUND_PER_POUND("lb/lb", val -> val / POUNDS_PER_KILOGRAM, val -> val * POUNDS_PER_KILOGRAM),;

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    HumidityRatioUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public HumidityRatioUnit getBaseUnit() {
        return KILOGRAM_PER_KILOGRAM;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static HumidityRatioUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return KILOGRAM_PER_KILOGRAM;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (HumidityRatioUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + HumidityRatioUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .dropWvAndDaSuffixes()
                .unifyMultiAndDiv()
                .toString();
    }

}
