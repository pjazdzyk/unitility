package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum VolumeUnits implements VolumeUnit {

    CUBIC_METER("m³", 1.0),
    CUBIC_CENTIMETER("cm³", 1.0E-6),
    CUBIC_DECIMETER("dm³", 1.0E-3),
    CUBIC_FOOT("ft³", UnitDefinitions.CUBIC_FOOT),
    LITRE("l", UnitDefinitions.LITRE),
    HECTOLITRE("hl", 0.1),
    MILLILITRE("ml", 1.0E-6),
    OUNCE("fl.oz", UnitDefinitions.US_FLUID_OUNCE),
    PINT("pt", UnitDefinitions.US_PINT),
    GALLON_US("gal_US", UnitDefinitions.US_GALLON),
    GALLON_UK("gal_UK", UnitDefinitions.UK_GALLON);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    VolumeUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

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
            return CUBIC_METER;
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

}
