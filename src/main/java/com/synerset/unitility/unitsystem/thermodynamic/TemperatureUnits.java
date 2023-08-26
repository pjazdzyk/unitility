package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum TemperatureUnits implements Unit<Temperature> {

    KELVIN("K", val -> val, val -> val),
    CELSIUS("°C", val -> val + 273.15, val -> val - 273.15),
    FAHRENHEIT("°F", val -> (val - 32) * 5 / 9 + 273.15, val -> (val - 273.15) * 9 / 5 + 32);

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
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

}

