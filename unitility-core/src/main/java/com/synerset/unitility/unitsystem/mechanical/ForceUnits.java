package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.CENTI;
import static com.synerset.unitility.unitsystem.util.Constants.GRAVITY_SI;
import static com.synerset.unitility.unitsystem.util.Constants.KILO;
import static com.synerset.unitility.unitsystem.util.Constants.POUNDAL_PER_NEWTON_HISTORICAL;
import static com.synerset.unitility.unitsystem.util.Constants.POUND_FORCE_PER_NEWTON;

public enum ForceUnits implements ForceUnit {

    NEWTON("N", val -> val, val -> val),
    KILONEWTON("kN", val -> val * KILO, val -> val / KILO),
    KILOPOND("kp", val -> val * GRAVITY_SI, val -> val / GRAVITY_SI),
    DYNE("dyn", val -> val * (CENTI / KILO), val -> val / (CENTI / KILO)),
    POUND_FORCE("lbf", val -> val * POUND_FORCE_PER_NEWTON, val -> val / POUND_FORCE_PER_NEWTON),
    POUNDAL("pdl", val -> val * POUNDAL_PER_NEWTON_HISTORICAL, val -> val / POUNDAL_PER_NEWTON_HISTORICAL);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    ForceUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public ForceUnit getBaseUnit() {
        return NEWTON;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static ForceUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return NEWTON;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (ForceUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + ForceUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .toString();
    }

}
