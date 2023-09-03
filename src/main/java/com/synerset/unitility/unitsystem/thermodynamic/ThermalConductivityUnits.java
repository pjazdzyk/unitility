package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;

import java.util.function.DoubleUnaryOperator;

public enum ThermalConductivityUnits implements Unit {

    WATTS_PER_METER_KELVIN("W/(m·K)", val -> val, val -> val),
    KILOWATTS_PER_METER_KELVIN("kW/(m·K)", val -> val * 1000.0, val -> val / 1000.0),
    BTU_PER_HOUR_FOOT_FAHRENHEIT("BTU/(h·ft·°F)", val -> val * 1.7307352822121, val -> val / 1.7307352822121);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    ThermalConductivityUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public ThermalConductivityUnits getBaseUnit() {
        return WATTS_PER_METER_KELVIN;
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
