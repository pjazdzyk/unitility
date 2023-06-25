package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum MassUnits implements Unit<Mass> {

    KILOGRAM("kg", val -> val, val -> val),
    GRAM("g", val -> val * 0.001, val -> val / 0.001),
    MILLIGRAM("mg", val -> val * 0.000001, val -> val / 0.000001),
    TONNE_SI("t", val -> val * 1000.0, val -> val / 1000.0),
    OUNCE("oz", val -> val * 0.028349523125, val -> val / 0.028349523125),
    POUND("lb", val -> val * 0.45359237, val -> val / 0.45359237);

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
    public Unit<Mass> getBaseUnit() {
        return KILOGRAM;
    }

    @Override
    public double toBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromBaseToThisUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }
}
