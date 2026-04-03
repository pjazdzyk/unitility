package com.synerset.unitility.unitsystem.thermodynamic;

import java.util.function.DoubleUnaryOperator;

public enum CompressibilityFactorUnits implements CompressibilityFactorUnit {
    
    DIMENSIONLESS("", val -> val, val -> val);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    CompressibilityFactorUnits(String symbol, DoubleUnaryOperator toBaseConverter, 
                               DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() { return symbol; }

    @Override
    public CompressibilityFactorUnit getBaseUnit() { return DIMENSIONLESS; }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }
}