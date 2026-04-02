package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum SpecificVolumeUnits implements SpecificVolumeUnit {

    CUBIC_METER_PER_KILOGRAM("m³/kg", val -> val, val -> val),
    CUBIC_CENTIMETER_PER_KILOGRAM("cm³/kg", val -> val * 1e-6, val -> val / 1e-6),
    CUBIC_DECIMETER_PER_KILOGRAM("dm³/kg", val -> val * 0.001, val -> val / 0.001),
    CUBIC_FOOT_PER_KILOGRAM("ft³/kg", val -> val * 0.028316846592, val -> val / 0.028316846592),
    LITER_PER_KILOGRAM("L/kg", val -> val * 0.001, val -> val / 0.001),
    HECTOLITER_PER_KILOGRAM("hL/kg", val -> val * 0.1, val -> val / 0.1),
    MILLILITER_PER_KILOGRAM("mL/kg", val -> val * 1e-6, val -> val / 1e-6),
    OUNCE_PER_KILOGRAM("fl.oz/kg", val -> val * 2.95735295625e-5, val -> val / 2.95735295625e-5),
    PINT_PER_KILOGRAM("pt/kg", val -> val * 0.000473176473, val -> val / 0.000473176473),
    GALLON_US_PER_KILOGRAM("gal_US/kg", val -> val * 0.003785411784, val -> val / 0.003785411784),
    GALLON_UK_PER_KILOGRAM("gal_UK/kg", val -> val * 0.00454609, val -> val / 0.00454609);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    SpecificVolumeUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public SpecificVolumeUnits getBaseUnit() {
        return CUBIC_METER_PER_KILOGRAM;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static SpecificVolumeUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return CUBIC_METER_PER_KILOGRAM;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (SpecificVolumeUnit unit : values()) {
            if (hasMatchBeenFound(unit, requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                                           + SpecificVolumeUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .unifyAerialAndVol()
                .toString();
    }

    private static boolean hasMatchBeenFound(SpecificVolumeUnit currentUnit, String requestedSymbol) {
        String currentSymbol = unifySymbol(currentUnit.getSymbol());
        if (currentUnit == SpecificVolumeUnits.GALLON_UK_PER_KILOGRAM) {
            String truncatedSymbol = currentSymbol.replace("uk", "");
            return truncatedSymbol.equalsIgnoreCase(requestedSymbol) || currentSymbol.equalsIgnoreCase(requestedSymbol);
        }
        return currentSymbol.equalsIgnoreCase(requestedSymbol);
    }
}