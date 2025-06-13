package com.synerset.unitility.unitsystem.flow;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.CUBIC_FEET_PER_CUBIC_METER;
import static com.synerset.unitility.unitsystem.util.Constants.GALLONS_UK_PER_CUBIC_METER;
import static com.synerset.unitility.unitsystem.util.Constants.GALLONS_US_PER_CUBIC_METER;
import static com.synerset.unitility.unitsystem.util.Constants.LITERS_PER_CUBIC_METER;
import static com.synerset.unitility.unitsystem.util.Constants.SECONDS_PER_HOUR;
import static com.synerset.unitility.unitsystem.util.Constants.SECONDS_PER_MINUTE;

public enum VolumetricFlowUnits implements VolumetricFlowUnit {


    CUBIC_METERS_PER_SECOND("m³/s", val -> val, val -> val),
    CUBIC_METERS_PER_MINUTE("m³/min", val -> val / SECONDS_PER_MINUTE, val -> val * SECONDS_PER_MINUTE),
    CUBIC_METERS_PER_HOUR("m³/h", val -> val / SECONDS_PER_HOUR, val -> val * SECONDS_PER_HOUR),
    CUBIC_FEET_PER_MINUTE("ft³/min", val -> val / (CUBIC_FEET_PER_CUBIC_METER * SECONDS_PER_MINUTE), val -> val * (CUBIC_FEET_PER_CUBIC_METER * SECONDS_PER_MINUTE)),
    LITRE_PER_SECOND("l/s", val -> val / LITERS_PER_CUBIC_METER, val -> val * LITERS_PER_CUBIC_METER),
    LITRE_PER_MINUTE("l/min", val -> val / (LITERS_PER_CUBIC_METER * SECONDS_PER_MINUTE), val -> val * (LITERS_PER_CUBIC_METER * SECONDS_PER_MINUTE)),
    LITRE_PER_HOUR("l/h", val -> val / (LITERS_PER_CUBIC_METER * SECONDS_PER_HOUR), val -> val * (LITERS_PER_CUBIC_METER * SECONDS_PER_HOUR)),
    GALLONS_PER_SECOND_US("gal/s_US", val -> val / GALLONS_US_PER_CUBIC_METER, val -> val * GALLONS_US_PER_CUBIC_METER),
    GALLONS_PER_MINUTE_US("gal/min_US", val -> val / (GALLONS_US_PER_CUBIC_METER * SECONDS_PER_MINUTE), val -> val * (GALLONS_US_PER_CUBIC_METER * SECONDS_PER_MINUTE)),
    GALLONS_PER_HOUR_US("gal/h_US", val -> val / (GALLONS_US_PER_CUBIC_METER * SECONDS_PER_HOUR), val -> val * (GALLONS_US_PER_CUBIC_METER * SECONDS_PER_HOUR)),
    GALLONS_PER_SECOND_UK("gal/s_UK", val -> val / GALLONS_UK_PER_CUBIC_METER, val -> val * GALLONS_UK_PER_CUBIC_METER),
    GALLONS_PER_MINUTE_UK("gal/min_UK", val -> val / (GALLONS_UK_PER_CUBIC_METER * SECONDS_PER_MINUTE), val -> val * (GALLONS_UK_PER_CUBIC_METER * SECONDS_PER_MINUTE)),
    GALLONS_PER_HOUR_UK("gal/h_UK", val -> val / (GALLONS_UK_PER_CUBIC_METER * SECONDS_PER_HOUR), val -> val * (GALLONS_UK_PER_CUBIC_METER * SECONDS_PER_HOUR));

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
