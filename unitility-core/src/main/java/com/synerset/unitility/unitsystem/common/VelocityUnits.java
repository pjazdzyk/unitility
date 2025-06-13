package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.CENTI;
import static com.synerset.unitility.unitsystem.util.Constants.FEET_PER_METER;
import static com.synerset.unitility.unitsystem.util.Constants.INCHES_PER_METER;
import static com.synerset.unitility.unitsystem.util.Constants.KILO;
import static com.synerset.unitility.unitsystem.util.Constants.KNOT_FOR_METER_PER_SECOND;
import static com.synerset.unitility.unitsystem.util.Constants.MACH_METERS_PER_SECOND;
import static com.synerset.unitility.unitsystem.util.Constants.METERS_PER_MILE;
import static com.synerset.unitility.unitsystem.util.Constants.MILES_PER_METER;
import static com.synerset.unitility.unitsystem.util.Constants.SECONDS_PER_HOUR;

public enum VelocityUnits implements VelocityUnit {

    METER_PER_SECOND("m/s", val -> val, val -> val),
    CENTIMETER_PER_SECOND("cm/s", val -> val * CENTI, val -> val / CENTI),
    KILOMETER_PER_HOUR("km/h", val -> val * (KILO / SECONDS_PER_HOUR), val -> val / (KILO / SECONDS_PER_HOUR)),
    INCH_PER_SECOND("in/s", val -> val / INCHES_PER_METER, val -> val * INCHES_PER_METER),
    FEET_PER_SECOND("ft/s", val -> val / FEET_PER_METER, val -> val * FEET_PER_METER),
    MILES_PER_HOUR("mph", val -> val / (SECONDS_PER_HOUR / METERS_PER_MILE), val -> val * (SECONDS_PER_HOUR / METERS_PER_MILE)),
    KNOT("kn", val -> val * KNOT_FOR_METER_PER_SECOND, val -> val / KNOT_FOR_METER_PER_SECOND),
    MACH("Mach", val -> val * MACH_METERS_PER_SECOND, val -> val / MACH_METERS_PER_SECOND);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

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
