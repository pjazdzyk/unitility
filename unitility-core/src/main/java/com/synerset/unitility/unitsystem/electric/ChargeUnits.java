package com.synerset.unitility.unitsystem.electric;

import com.synerset.unitility.unitsystem.Constants;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum ChargeUnits implements ChargeUnit {

    COULOMB("C", val -> val, val -> val),
    PICOCOULOMB("pC", val -> val * Constants.PICO, val -> val / Constants.PICO),
    NANOCOULOMB("nC", val -> val * Constants.NANO, val -> val / Constants.NANO),
    MICROCOULOMB("µC", val -> val * Constants.MICRO, val -> val / Constants.MICRO),
    MILLICOULOMB("mC", val -> val * Constants.MILLI, val -> val / Constants.MILLI),
    KILOCOULOMB("kC", val -> val * Constants.KILO, val -> val / Constants.KILO),
    MEGACOULOMB("MC", val -> val * Constants.MEGA, val -> val / Constants.MEGA);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    ChargeUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public ChargeUnits getBaseUnit() {
        return COULOMB;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static ChargeUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return COULOMB;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (ChargeUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equals(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + ChargeUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimAndClean()
                .replace("c", "C")
                .replace("u", "µ")
                .toString();
    }
}