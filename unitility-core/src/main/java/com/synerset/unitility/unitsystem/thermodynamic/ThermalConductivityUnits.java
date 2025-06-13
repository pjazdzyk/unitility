package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.IMPERIAL_THERMAL_CONDUCTIVITY_RATIO;
import static com.synerset.unitility.unitsystem.util.Constants.KILO;

public enum ThermalConductivityUnits implements ThermalConductivityUnit {

    WATTS_PER_METER_KELVIN("W/(m·K)", val -> val, val -> val),
    KILOWATTS_PER_METER_KELVIN("kW/(m·K)", val -> val * KILO, val -> val / KILO),
    BTU_PER_HOUR_FOOT_FAHRENHEIT("BTU/(h·ft·°F)", val -> val * IMPERIAL_THERMAL_CONDUCTIVITY_RATIO, val -> val / IMPERIAL_THERMAL_CONDUCTIVITY_RATIO);

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
    public ThermalConductivityUnit getBaseUnit() {
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

    public static ThermalConductivityUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return getDefaultUnit();
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (ThermalConductivityUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + ThermalConductivityUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .dropParentheses()
                .dropDegreeSymbols()
                .toString();
    }

    public static ThermalConductivityUnit getDefaultUnit() {
        return WATTS_PER_METER_KELVIN;
    }

}