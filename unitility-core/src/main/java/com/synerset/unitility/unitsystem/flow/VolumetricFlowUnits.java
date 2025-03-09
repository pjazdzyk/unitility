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
    GALLONS_PER_SECOND("gal/s", val -> val / 264.17205236, val -> val * 264.17205236),
    GALLONS_PER_MINUTE("gal/min", val -> val / 15850.323141, val -> val * 15850.323141),
    GALLONS_PER_HOUR("gal/h", val -> val / 951019.38849, val -> val * 951019.38849);

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
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
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

    public static VolumetricFlowUnit getDefaultUnit() {
        return CUBIC_METERS_PER_HOUR;
    }

}