package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.DEGREES_PER_RADIAN;
import static com.synerset.unitility.unitsystem.util.Constants.RADIANS_PER_REVOLUTION;
import static com.synerset.unitility.unitsystem.util.Constants.SECONDS_PER_MINUTE;

public enum AngularVelocityUnits implements AngularVelocityUnit {

    RADIANS_PER_SECOND("rad/s", val -> val, val -> val),
    REVOLUTIONS_PER_SECOND("rps", val -> val * RADIANS_PER_REVOLUTION, val -> val / RADIANS_PER_REVOLUTION),
    REVOLUTIONS_PER_MINUTE("rpm", val -> val * (RADIANS_PER_REVOLUTION / SECONDS_PER_MINUTE), val -> val / (RADIANS_PER_REVOLUTION / SECONDS_PER_MINUTE)),
    DEGREES_PER_SECOND("°/s", val -> val / DEGREES_PER_RADIAN, val -> val * DEGREES_PER_RADIAN),;
    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    AngularVelocityUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public AngularVelocityUnit getBaseUnit() {
        return RADIANS_PER_SECOND;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static AngularVelocityUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return getDefaultUnit();
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (AngularVelocityUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + AngularVelocityUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .toString();
    }

    public static AngularVelocityUnits getDefaultUnit() {
        return RADIANS_PER_SECOND;
    }

}
