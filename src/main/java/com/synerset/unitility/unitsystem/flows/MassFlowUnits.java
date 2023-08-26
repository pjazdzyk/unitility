package com.synerset.unitility.unitsystem.flows;

import com.synerset.unitility.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum MassFlowUnits implements Unit<MassFlow> {

    KILOGRAM_PER_SECOND("kg/s", val -> val, val -> val),
    KILOGRAM_PER_HOUR("kg/h", val -> val / 3600.0, val -> val * 3600.0),
    TONNE_PER_HOUR("t/h", val -> val * (10.0 / 36.0), val -> val / (10.0 / 36.0)),
    POUND_PER_SECOND("lb/s", val -> val * 0.45359237, val -> val / 0.45359237);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    MassFlowUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public Unit<MassFlow> getBaseUnit() {
        return KILOGRAM_PER_SECOND;
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
