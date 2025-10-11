package com.synerset.unitility.unitsystem.electric;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum CapacitanceUnits implements CapacitanceUnit {

    FARAD("F", val -> val, val -> val),
    PICOFARAD("pF", val -> val * 1E-12, val -> val / 1E-12),
    NANOFARAD("nF", val -> val * 1E-9, val -> val / 1E-9),
    MICROFARAD("µF", val -> val * 1E-6, val -> val / 1E-6),
    MILLIFARAD("mF", val -> val * 1E-3, val -> val / 1E-3),
    KILOFARAD("kF", val -> val * 1E3, val -> val / 1E3),
    MEGAFARAD("MF", val -> val * 1E6, val -> val / 1E6);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    CapacitanceUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public CapacitanceUnits getBaseUnit() {
        return FARAD;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static CapacitanceUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return FARAD;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (CapacitanceUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equals(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + CapacitanceUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimAndClean()
                .replace("f", "F")
                .replace("u", "µ")
                .toString();
    }
}