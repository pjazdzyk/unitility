package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum VolumeUnits implements Unit<Volume> {

    CUBIC_METER("m³", val -> val, val -> val),
    CUBIC_CENTIMETER("L", val -> val * 0.001, val -> val * 1000),
    LITER("L", val -> val * 0.001, val -> val * 1000),
    HECTOLITRE("hL", val -> val * 0.1, val -> val * 10),
    MILLILITRE("mL", val -> val * 0.0001, val -> val * 10000),
    OUNCE("fl.oz", val -> val * 0.0000295735295625, val -> val / 0.0000295735295625),
    PINT("pt", val -> val * 0.000473176473, val -> val / 0.000473176473),
    GALLON("gal", val -> val * 0.003785411784, val -> val / 0.003785411784);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    VolumeUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public Unit<Volume> getBaseUnit() {
        return CUBIC_METER;
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
