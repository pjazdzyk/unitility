package com.synerset.unitility.unitsystem.electric;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum ConductanceUnits implements ConductanceUnit {

    SIEMENS("S", val -> val, val -> val),
    PICOSIEMENS("pS", val -> val * 1E-12, val -> val / 1E-12),
    NANOSIEMENS("nS", val -> val * 1E-9, val -> val / 1E-9),
    MICROSIEMENS("µS", val -> val * 1E-6, val -> val / 1E-6),
    MILLISIEMENS("mS", val -> val * 1E-3, val -> val / 1E-3),
    KILOSIEMENS("kS", val -> val * 1E3, val -> val / 1E3);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    ConductanceUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public ConductanceUnits getBaseUnit() {
        return SIEMENS;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static ConductanceUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return SIEMENS;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (ConductanceUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + ConductanceUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .replace("u", "µ")
                .toString();
    }
}