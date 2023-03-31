package com.synerset.unitsystem.humidityratio;

import com.synerset.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum HumidityRatioUnits implements Unit<HumidityRatio> {

    KILOGRAM_PER_KILOGRAM("kg/kg", val -> val, val -> val),
    POUND_PER_POUND("lb/lb", val -> val / 2.20462262184878 , val -> val * 2.20462262184878);

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
    public Unit<HumidityRatio> getBaseUnit() {
        return KILOGRAM_PER_KILOGRAM;
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
