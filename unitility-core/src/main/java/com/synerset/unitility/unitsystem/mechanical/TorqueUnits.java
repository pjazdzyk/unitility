package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.FOOT_POUND_FORCE_PER_NEWTON_METER;
import static com.synerset.unitility.unitsystem.util.Constants.GRAVITY_SI;
import static com.synerset.unitility.unitsystem.util.Constants.INCH_POUND_FORCE_PER_NEWTON_METER;
import static com.synerset.unitility.unitsystem.util.Constants.MILLI;

public enum TorqueUnits implements TorqueUnit {

    NEWTON_METER("N·m", val -> val, val -> val),
    MILLINEWTON_METER("mN·m", val -> val * MILLI, val -> val / MILLI),
    KILOPOND_METER("kp·m", val -> val * GRAVITY_SI, val -> val / GRAVITY_SI),
    FOOT_POUND("ft·lb", val -> val * FOOT_POUND_FORCE_PER_NEWTON_METER, val -> val / FOOT_POUND_FORCE_PER_NEWTON_METER),
    INCH_POUND("in·lb", val -> val * INCH_POUND_FORCE_PER_NEWTON_METER, val -> val / INCH_POUND_FORCE_PER_NEWTON_METER);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    TorqueUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public TorqueUnits getBaseUnit() {
        return NEWTON_METER;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static TorqueUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return getDefaultUnit();
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (TorqueUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + TorqueUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .toString();
    }

    public static TorqueUnit getDefaultUnit() {
        return NEWTON_METER;
    }

}