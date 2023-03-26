package com.synerset.unitsystem.specificheat;

import com.synerset.unitsystem.Unit;
import java.util.function.DoubleUnaryOperator;

public enum SpecificHeatUnits implements Unit<SpecificHeat> {

    JOULES_PER_KILOGRAM_KELVIN("J/(kg·K)", val -> val, val -> val),
    KILOJOULES_PER_KILOGRAM_KELVIN("kJ/(kg·K)", val -> val * 1000, val -> val / 1000),
    BTU_PER_POUND_FAHRENHEIT("BTU/(lb·°F)", val -> val * 4186.7999934703, val -> val / 4186.7999934703);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    SpecificHeatUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public Unit<SpecificHeat> getBaseUnit() {
        return JOULES_PER_KILOGRAM_KELVIN;
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
