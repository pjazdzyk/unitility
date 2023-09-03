package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum ForceUnits implements Unit {

    NEWTON("N", val -> val, val -> val),
    KILONEWTON("kN", val -> val * 1000.0, val -> val / 1000.0),
    KILOPOND("kp", val -> val * 9.80665, val -> val / 9.80665),
    DYNE("dyn", val -> val * 0.00001, val -> val / 0.00001),
    POUND_FORCE("lbf", val -> val * 4.4482216152605, val -> val / 4.4482216152605),
    POUNDAL("pdl", val -> val * 0.138254954376, val -> val / 0.138254954376);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    ForceUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public ForceUnits getBaseUnit() {
        return NEWTON;
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
