package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.CUBIC_CENTI;
import static com.synerset.unitility.unitsystem.util.Constants.CUBIC_DECI;
import static com.synerset.unitility.unitsystem.util.Constants.CUBIC_FEET_PER_CUBIC_METER;
import static com.synerset.unitility.unitsystem.util.Constants.GALLONS_UK_PER_CUBIC_METER;
import static com.synerset.unitility.unitsystem.util.Constants.GALLONS_US_PER_CUBIC_METER;
import static com.synerset.unitility.unitsystem.util.Constants.HECTOLITRES_PER_CUBIC_METER;
import static com.synerset.unitility.unitsystem.util.Constants.LITERS_PER_CUBIC_METER;
import static com.synerset.unitility.unitsystem.util.Constants.MILLIITRES_PER_CUBIC_METER;
import static com.synerset.unitility.unitsystem.util.Constants.OUNCE_PER_CUBIC_METER;
import static com.synerset.unitility.unitsystem.util.Constants.PINTS_PER_CUBIC_METER;

public enum VolumeUnits implements VolumeUnit {

    CUBIC_METER("m³", val -> val, val -> val),
    CUBIC_CENTIMETER("cm³", val -> val * CUBIC_CENTI, val -> val / CUBIC_CENTI),
    CUBIC_DECIMETER("dm³", val -> val * CUBIC_DECI, val -> val / CUBIC_DECI),
    CUBIC_FEET("ft³", val -> val / CUBIC_FEET_PER_CUBIC_METER, val -> val * CUBIC_FEET_PER_CUBIC_METER),
    LITRE("l", val -> val / LITERS_PER_CUBIC_METER, val -> val * LITERS_PER_CUBIC_METER),
    HECTOLITRE("hl", val -> val / HECTOLITRES_PER_CUBIC_METER, val -> val * HECTOLITRES_PER_CUBIC_METER),
    MILLILITRE("ml", val -> val / MILLIITRES_PER_CUBIC_METER, val -> val * MILLIITRES_PER_CUBIC_METER),
    GALLON_US("gal_US", val -> val / GALLONS_US_PER_CUBIC_METER, val -> val * GALLONS_US_PER_CUBIC_METER),
    OUNCE("fl.oz", val -> val / OUNCE_PER_CUBIC_METER, val -> val * OUNCE_PER_CUBIC_METER),
    PINT("pt", val -> val / PINTS_PER_CUBIC_METER, val -> val * PINTS_PER_CUBIC_METER),
    GALLON_UK("gal_UK", val -> val / GALLONS_UK_PER_CUBIC_METER, val -> val * GALLONS_UK_PER_CUBIC_METER);

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
