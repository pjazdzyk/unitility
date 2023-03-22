package com.synerset.temperature;

import com.synerset.Unit;

import java.util.function.DoubleUnaryOperator;

public enum TemperatureUnits implements Unit<Temperature> {

    KELVIN("K", val -> val, val -> val),
    CELSIUS("°C", val -> val + 273.15, val -> val - 273.15);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    TemperatureUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public Unit<Temperature> getBaseUnit() {
        return KELVIN;
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

