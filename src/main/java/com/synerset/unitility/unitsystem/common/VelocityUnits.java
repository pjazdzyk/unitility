package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.function.DoubleUnaryOperator;

public enum VelocityUnits implements Unit {

    METER_PER_SECOND("m/s", val -> val, val -> val),
    CENTIMETER_PER_SECOND("cm/s", val -> val / 100.0, val -> val * 100.0),
    KILOMETER_PER_HOUR("km/h", val -> val / 3.6, val -> val * 3.6),
    INCH_PER_SECOND("in/s", val -> val * 0.0254, val -> val / 0.0254),
    FEET_PER_SECOND("ft/s", val -> val * 0.3048, val -> val / 0.3048),
    MILES_PER_HOUR("mph", val -> val * 0.44704, val -> val / 0.44704),
    KNOT("kn", val -> val * 0.514444444444444, val -> val / 0.514444444444444),
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
    public VelocityUnits getBaseUnit() {
        return METER_PER_SECOND;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static VelocityUnits fromSymbol(String rawSymbol) {
        String inputSymbolWithoutDegreesSign = formatSymbolInput(rawSymbol);
        for (VelocityUnits unit : values()) {
            String enumSymbolWithoutDegreesSing = formatSymbolInput(unit.symbol);
            if (enumSymbolWithoutDegreesSing.equalsIgnoreCase(inputSymbolWithoutDegreesSign)) {
                return unit;
            }
        }
        throw new UnitSystemArgumentException("Unsupported symbol: " + rawSymbol + ", class: "
                + VelocityUnits.class.getSimpleName());
    }

    private static String formatSymbolInput(String inputString) {
        return inputString
                .trim()
                .toLowerCase()
                .replace(" ", "")
                .replace("/", "p");
    }

}
