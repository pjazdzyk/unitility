package com.synerset.unitility.unitsystem.flows;

import com.synerset.unitility.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum VolumetricFlowUnits implements Unit<VolumetricFlow> {

    CUBIC_METERS_PER_SECOND("m³/s", val -> val, val -> val),
    CUBIC_METERS_PER_MINUTE("m³/min", val -> val / 60.0, val -> val * 60.0),
    CUBIC_METERS_PER_HOUR("m³/h", val -> val / 3600.0, val -> val * 3600.0),
    LITRE_PER_SECOND("L/s", val -> val / 1000.0, val -> val * 1000.0),
    LITRE_PER_MINUTE("L/min", val -> val / 60000.0, val -> val * 60000.0),
    LITRE_PER_HOUR("L/h", val -> val / 3600000.0, val -> val * 3600000.0),
    GALLONS_PER_SECOND("gal/s", val -> val / 0.2641720523581484, val -> val * 0.2641720523581484),
    GALLONS_PER_MINUTE("gal/min", val -> val / 15.850610141490908, val -> val * 15.850610141490908),
    GALLONS_PER_HOUR("gal/h", val -> val / 951.0366084894545, val -> val * 951.0366084894545);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    VolumetricFlowUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public Unit<VolumetricFlow> getBaseUnit() {
        return CUBIC_METERS_PER_SECOND;
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
