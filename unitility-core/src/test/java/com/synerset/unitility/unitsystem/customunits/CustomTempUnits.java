package com.synerset.unitility.unitsystem.customunits;

import com.synerset.unitility.unitsystem.thermodynamic.TemperatureUnit;
import com.synerset.unitility.unitsystem.thermodynamic.TemperatureUnits;

import java.util.function.DoubleUnaryOperator;

public enum CustomTempUnits implements TemperatureUnit {

    RANKINE("R", val -> val * 5 / 9, val -> val * 9 / 5);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    CustomTempUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    @Override
    public TemperatureUnit getBaseUnit() {
        return TemperatureUnits.KELVIN;
    }
}
