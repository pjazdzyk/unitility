package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum VelocityUnits implements VelocityUnit {

    METER_PER_SECOND("m/s", 1.0),
    CENTIMETER_PER_SECOND("cm/s", 1.0E-2),
    KILOMETER_PER_HOUR("km/h", UnitDefinitions.KILOMETER_PER_HOUR),
    INCH_PER_SECOND("in/s", UnitDefinitions.INCH),
    FEET_PER_SECOND("ft/s", UnitDefinitions.FOOT),
    FEET_PER_MINUTE("ft/min", UnitDefinitions.FOOT_PER_MINUTE),
    MILES_PER_HOUR("mph", UnitDefinitions.MILE_PER_HOUR),
    KNOT("kn", UnitDefinitions.KNOT),
    /**
     * Converts at a fixed 340.29 m/s.
     *
     * @deprecated A Mach number is a ratio of a speed to the local speed of sound, which depends on the temperature,
     * so it is not a unit of velocity and no fixed factor is right. Compute the ratio against the speed of sound at
     * the actual state instead. Kept, unchanged, for API compatibility, and to be removed in the next major version.
     */
    @Deprecated(since = "4.2.0")
    MACH("Mach", UnitDefinitions.MACH_LEGACY_SPEED_OF_SOUND);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    VelocityUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

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
    public VelocityUnit getBaseUnit() {
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

    public static VelocityUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return METER_PER_SECOND;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (VelocityUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + VelocityUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .toString();
    }


}
