package com.synerset.unitility.unitsystem.electric;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum VoltageUnits implements VoltageUnit {

    VOLT("V", val -> val, val -> val), // Base Unit
    MICROVOLT("µV", val -> val * 1E-6, val -> val / 1E-6),
    MILLIVOLT("mV", val -> val * 1E-3, val -> val / 1E-3),
    KILOVOLT("kV", val -> val * 1E3, val -> val / 1E3),
    MEGAVOLT("MV", val -> val * 1E6, val -> val / 1E6),
    GIGAVOLT("GV", val -> val * 1E9, val -> val / 1E9);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    VoltageUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public VoltageUnits getBaseUnit() {
        return VOLT;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static VoltageUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return VOLT;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (VoltageUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equals(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + VoltageUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimAndClean()
                .replace("v", "V")
                .replace("u", "µ")
                .toString();
    }
}