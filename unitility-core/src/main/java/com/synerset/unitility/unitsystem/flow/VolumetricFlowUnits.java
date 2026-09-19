package com.synerset.unitility.unitsystem.flow;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum VolumetricFlowUnits implements VolumetricFlowUnit {

    CUBIC_METERS_PER_SECOND("m³/s", 1.0),
    CUBIC_METERS_PER_MINUTE("m³/min", UnitDefinitions.PER_MINUTE),
    CUBIC_METERS_PER_HOUR("m³/h", UnitDefinitions.PER_HOUR),
    CUBIC_FEET_PER_MINUTE("ft³/min", UnitDefinitions.CUBIC_FOOT_PER_MINUTE),
    LITRE_PER_SECOND("l/s", UnitDefinitions.LITRE),
    LITRE_PER_MINUTE("l/min", UnitDefinitions.LITRE_PER_MINUTE),
    LITRE_PER_HOUR("l/h", UnitDefinitions.LITRE_PER_HOUR),
    GALLONS_PER_SECOND_US("gal/s_US", UnitDefinitions.US_GALLON),
    GALLONS_PER_MINUTE_US("gal/min_US", UnitDefinitions.US_GALLON_PER_MINUTE),
    GALLONS_PER_HOUR_US("gal/h_US", UnitDefinitions.US_GALLON_PER_HOUR),
    GALLONS_PER_SECOND_UK("gal/s_UK", UnitDefinitions.UK_GALLON),
    GALLONS_PER_MINUTE_UK("gal/min_UK", UnitDefinitions.UK_GALLON_PER_MINUTE),
    GALLONS_PER_HOUR_UK("gal/h_UK", UnitDefinitions.UK_GALLON_PER_HOUR);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    VolumetricFlowUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    VolumetricFlowUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public VolumetricFlowUnit getBaseUnit() {
        return CUBIC_METERS_PER_SECOND;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static VolumetricFlowUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return CUBIC_METERS_PER_SECOND;
        }

        String requestedSymbol = unifySymbol(rawSymbol);
        for (VolumetricFlowUnit unit : values()) {
            if (hasMatchBeenFound(unit, requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                                           + VolumetricFlowUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .unifyAerialAndVol()
                .replace("cfm", "ft3pmin")
                .toString();
    }

    private static boolean hasMatchBeenFound(VolumetricFlowUnit currentUnit, String requestedSymbol) {
        String currentSymbol = unifySymbol(currentUnit.getSymbol());
        if (currentUnit == VolumetricFlowUnits.GALLONS_PER_HOUR_UK || currentUnit == VolumetricFlowUnits.GALLONS_PER_MINUTE_UK
            || currentUnit == VolumetricFlowUnits.GALLONS_PER_SECOND_UK) {
            String truncatedSymbol = currentSymbol.replace("uk", "");
            return truncatedSymbol.equalsIgnoreCase(requestedSymbol) || currentSymbol.equalsIgnoreCase(requestedSymbol);
        }
        return currentSymbol.equalsIgnoreCase(requestedSymbol);
    }


}
