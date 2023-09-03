package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum ThermalDiffusivityUnits implements Unit {

    SQUARE_METER_PER_SECOND("m²/s", val -> val, val -> val),
    SQUARE_FEET_PER_SECOND("ft²/s", val -> val * 0.09290304, val -> val / 0.09290304);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    ThermalDiffusivityUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public ThermalDiffusivityUnits getBaseUnit() {
        return SQUARE_METER_PER_SECOND;
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
