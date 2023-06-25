package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum PowerUnits implements Unit<Power> {

    WATT("W", val -> val, val -> val),
    KILOWATT("kW", val -> val * 1000.0, val -> val / 1000.0),
    MEGAWATT("MW", val -> val * 1_000_000, val -> val / 1_000_000),
    BTU_PER_HOUR("BTU/h", val -> val * 0.2930710701722222, val -> val / 0.2930710701722222),
    HORSE_POWER("HP", val -> val * 745.69987158227022, val -> val / 745.69987158227022);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    PowerUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public Unit<Power> getBaseUnit() {
        return WATT;
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
