package com.synerset.unitsystem.dynamicviscosity;

import com.synerset.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum DynamicViscosityUnits implements Unit<DynamicViscosity> {

    KILOGRAM_PER_METER_SECOND("kg/(m·s)", val -> val, val -> val),
    PASCAL_SECOND("Pa·s", val -> val, val -> val),
    POISE("P", val -> val * 0.1, val -> val / 0.1);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    DynamicViscosityUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public Unit<DynamicViscosity> getBaseUnit() {
        return KILOGRAM_PER_METER_SECOND;
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
