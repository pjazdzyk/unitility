package com.synerset.unitility.unitsystem.flow;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum VolumetricFlowUnits implements VolumetricFlowUnit {

    CUBIC_METERS_PER_SECOND("m³/s", val -> val, val -> val),
    CUBIC_METERS_PER_MINUTE("m³/min", val -> val / 60.0, val -> val * 60.0),
    CUBIC_METERS_PER_HOUR("m³/h", val -> val / 3600.0, val -> val * 3600.0),
    CUBIC_FEET_PER_MINUTE("ft³/min", val -> val / 2118.880003289315415, val -> val * 2118.880003289315415),
    LITRE_PER_SECOND("l/s", val -> val / 1000.0, val -> val * 1000.0),
    LITRE_PER_MINUTE("l/min", val -> val / 60000.0, val -> val * 60000.0),
    LITRE_PER_HOUR("l/h", val -> val / 3600000.0, val -> val * 3600000.0),
    GALLONS_PER_SECOND_US("gal/s_US", val -> val / 264.17205236, val -> val * 264.17205236),
    GALLONS_PER_MINUTE_US("gal/min_US", val -> val / 15850.323141, val -> val * 15850.323141),
    GALLONS_PER_HOUR_US("gal/h_US", val -> val / 951019.38849, val -> val * 951019.38849),
    GALLONS_PER_SECOND_UK("gal/s_UK", val -> val / 219.9692483, val -> val * 219.9692483),
    GALLONS_PER_MINUTE_UK("gal/min_UK", val -> val / 13198.154898, val -> val * 13198.154898),
    GALLONS_PER_HOUR_UK("gal/h_UK", val -> val / 791889.29388, val -> val * 791889.29388);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

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
            return getDefaultUnit();
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

    public static VolumetricFlowUnit getDefaultUnit() {
        return CUBIC_METERS_PER_HOUR;
    }

}