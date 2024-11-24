package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum PressureUnits implements PressureUnit {

    PASCAL("Pa", val -> val, val -> val),
    HECTOPASCAL("hPa", val -> val * 1.0E2, val -> val / 1.0E2),
    KILOPASCAL("kPa", val -> val * 1.0E3, val -> val / 1.0E3),
    MEGAPASCAL("MPa", val -> val * 1.0E6, val -> val / 1.0E6),
    BAR("bar", val -> val * 1.0E5, val -> val / 1.0E5),
    MILLIBAR("mbar", val -> val * 100, val -> val / 100),
    TORR("Torr", val -> val * 133.322368421053, val -> val / 133.322368421053),
    PSI("psi", val -> val * 6894.757293168, val -> val / 6894.757293168);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    PressureUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public PressureUnit getBaseUnit() {
        return PASCAL;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static PressureUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return getDefaultUnit();
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (PressureUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + PressureUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .toString();
    }

    public static PressureUnit getDefaultUnit() {
        return PASCAL;
    }

}