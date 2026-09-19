package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum SpecificVolumeUnits implements SpecificVolumeUnit {

    CUBIC_METER_PER_KILOGRAM("m³/kg", 1.0),
    CUBIC_CENTIMETER_PER_KILOGRAM("cm³/kg", 1.0E-6),
    CUBIC_DECIMETER_PER_KILOGRAM("dm³/kg", 1.0E-3),
    LITER_PER_KILOGRAM("L/kg", UnitDefinitions.LITRE),
    HECTOLITER_PER_KILOGRAM("hL/kg", 0.1),
    MILLILITER_PER_KILOGRAM("mL/kg", 1.0E-6),

    CUBIC_FOOT_PER_POUND("ft³/lb", UnitDefinitions.CUBIC_FOOT_PER_POUND),
    GALLON_US_PER_POUND("gal_US/lb", UnitDefinitions.US_GALLON_PER_POUND),
    GALLON_UK_PER_POUND("gal_UK/lb", UnitDefinitions.UK_GALLON_PER_POUND),
    OUNCE_PER_POUND("fl.oz/lb", UnitDefinitions.US_FLUID_OUNCE_PER_POUND);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    SpecificVolumeUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

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
        if (currentUnit == SpecificVolumeUnits.GALLON_UK_PER_POUND) {
            String truncatedSymbol = currentSymbol.replace("uk", "");
            return truncatedSymbol.equalsIgnoreCase(requestedSymbol) || currentSymbol.equalsIgnoreCase(requestedSymbol);
        }
        return currentSymbol.equalsIgnoreCase(requestedSymbol);
    }
}