package com.synerset.unitsystem.kinematicviscosity;

import com.synerset.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum KinematicViscosityUnits implements Unit<KinematicViscosity> {
    SQUARE_METER_PER_SECOND("m²/s", val -> val, val -> val),
    SQUARE_FOOT_PER_SECOND("ft²/s", val -> val * 0.09290304, val -> val / 0.09290304);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    KinematicViscosityUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public Unit<KinematicViscosity> getBaseUnit() {
        return SQUARE_METER_PER_SECOND;
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
