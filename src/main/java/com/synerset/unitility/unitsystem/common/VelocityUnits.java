package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum VelocityUnits implements Unit<Velocity> {

    METER_PER_SECOND("m/s", val -> val, val -> val),
    CENTIMETER_PER_SECOND("cm/s", val -> val / 100.0, val -> val * 100.0),
    KILOMETER_PER_HOUR("km/h", val -> val / 3.6, val -> val * 3.6),
    INCH_PER_SECOND("in/s", val -> val * 0.0254, val -> val / 0.0254),
    FEET_PER_SECOND("ft/s", val -> val * 0.3048, val -> val / 0.3048),
    MILES_PER_HOUR("mph", val -> val * 0.44704, val -> val / 0.44704),
    KNOTS("kn", val -> val * 0.514444444444444, val -> val / 0.514444444444444),
    MACH("Mach", val -> val * 340.29, val -> val / 340.29);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    VelocityUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public Unit<Velocity> getBaseUnit() {
        return METER_PER_SECOND;
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
