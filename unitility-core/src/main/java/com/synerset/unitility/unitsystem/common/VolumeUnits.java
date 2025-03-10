package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum VolumeUnits implements VolumeUnit {

    CUBIC_METER("m³", val -> val, val -> val),
    CUBIC_CENTIMETER("cm³", val -> val * 0.000001, val -> val * 1000000.0),
    CUBIC_DECIMETER("dm³", val -> val * 0.001, val -> val * 1000.0),
    CUBIC_FEET("ft³", val -> val * 0.0283168466, val -> val / 0.0283168466),
    LITRE("l", val -> val * 0.001, val -> val * 1000.0),
    HECTOLITRE("hl", val -> val * 0.1, val -> val * 10.0),
    MILLILITRE("ml", val -> val * 0.000001, val -> val * 1000000.0),
    OUNCE("fl.oz", val -> val * 0.0000295735295625, val -> val / 0.0000295735295625),
    PINT("pt", val -> val * 0.000473176473, val -> val / 0.000473176473),
    GALLON_US("gal_US", val -> val * 0.003785411784, val -> val / 0.003785411784),
    GALLON_UK("gal_UK", val -> val * 0.00454608999999, val -> val / 0.00454608999999);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    VolumeUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public VolumeUnit getBaseUnit() {
        return CUBIC_METER;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static VolumeUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return getDefaultUnit();
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (VolumeUnit unit : values()) {
            if (hasMatchBeenFound(unit, requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                                           + VolumeUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .unifyAerialAndVol()
                .toString();
    }

    private static boolean hasMatchBeenFound(VolumeUnit currentUnit, String requestedSymbol) {
        String currentSymbol = unifySymbol(currentUnit.getSymbol());
        if (currentUnit == VolumeUnits.GALLON_UK) {
            String truncatedSymbol = currentSymbol.replace("uk", "");
            return truncatedSymbol.equalsIgnoreCase(requestedSymbol) || currentSymbol.equalsIgnoreCase(requestedSymbol);
        }
        return currentSymbol.equalsIgnoreCase(requestedSymbol);
    }

    public static VolumeUnit getDefaultUnit() {
        return CUBIC_METER;
    }
}
