package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.KILO;
import static com.synerset.unitility.unitsystem.util.Constants.KILOGRAMS_PER_OUNCE;
import static com.synerset.unitility.unitsystem.util.Constants.KILOGRAMS_PER_POUND;
import static com.synerset.unitility.unitsystem.util.Constants.MILLI;
import static com.synerset.unitility.unitsystem.util.Constants.OUNCES_PER_KILOGRAM;
import static com.synerset.unitility.unitsystem.util.Constants.POUNDS_PER_KILOGRAM;

public enum MassUnits implements MassUnit {

    KILOGRAM("kg", val -> val, val -> val),
    GRAM("g", val -> val / KILO, val -> val * KILO),
    MILLIGRAM("mg", val -> val / KILO * MILLI, val -> val * KILO / MILLI),
    TONNE_SI("t", val -> val * KILO, val -> val / KILO),
    OUNCE("oz", val -> val * KILOGRAMS_PER_OUNCE, val -> val / KILOGRAMS_PER_OUNCE),
    POUND("lb", val -> val * KILOGRAMS_PER_POUND, val -> val / KILOGRAMS_PER_POUND);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    MassUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public MassUnit getBaseUnit() {
        return KILOGRAM;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static MassUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return getDefaultUnit();
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (MassUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + MassUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .toString();
    }

    public static MassUnit getDefaultUnit() {
        return KILOGRAM;
    }


}
