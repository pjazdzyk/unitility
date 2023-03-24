package com.synerset.unitsystem.specificenthalpy;

import com.synerset.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum SpecificEnthalpyUnits implements Unit<SpecificEnthalpy> {

    JOULE_PER_KILOGRAM("J/kg", val -> val, val -> val),
    KILO_JOULE_PER_KILOGRAM("kJ/kg", val -> val * 1000, val -> val / 1000),
    BTU_PER_POUND("BTU/lb", val -> val * 2326, val -> val / 2326.0);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    SpecificEnthalpyUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public Unit<SpecificEnthalpy> getBaseUnit() {
        return JOULE_PER_KILOGRAM;
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
