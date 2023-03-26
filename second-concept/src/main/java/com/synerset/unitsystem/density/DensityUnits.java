package com.synerset.unitsystem.density;

import com.synerset.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum DensityUnits implements Unit<Density> {

    KILOGRAM_PER_CUBIC_METER("kg/m³", val -> val, val -> val),
    POUND_PER_CUBIC_FOOT("lb/ft³", val -> val * 16.0184633739599, val -> val / 16.0184633739599),
    POUNDS_PER_CUBIC_INCH("lb/in³", val -> val / 0.000036127292218, val -> val * 0.000036127292218);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    DensityUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public Unit<Density> getBaseUnit() {
        return KILOGRAM_PER_CUBIC_METER;
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
