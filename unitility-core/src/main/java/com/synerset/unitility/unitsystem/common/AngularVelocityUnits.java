package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum AngularVelocityUnits implements AngularVelocityUnit {

    RADIAN_PER_SECOND("rad/s", val -> val, val -> val),
    REVOLUTIONS_PER_SECOND("rps", val -> val / (1.0 / 2 / Math.PI), val -> val * (1.0 / 2 / Math.PI)),
    REVOLUTIONS_PER_MINUTE("rpm", val -> val / (60.0 / 2.0 / Math.PI), val -> val * (60.0 / 2.0 / Math.PI)),
    DEGREES_PER_SECOND("°/s", val -> val / (180.0 / Math.PI), val -> val * (180.0 / Math.PI));
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
        return RADIAN_PER_SECOND;
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
        return RADIAN_PER_SECOND;
    }

}
