package com.synerset.unitility.unitsystem.flow;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.KILO;
import static com.synerset.unitility.unitsystem.util.Constants.KILOGRAMS_PER_POUND;
import static com.synerset.unitility.unitsystem.util.Constants.SECONDS_PER_HOUR;
import static com.synerset.unitility.unitsystem.util.Constants.SECONDS_PER_MINUTE;

public enum MassFlowUnits implements MassFlowUnit {

    KILOGRAM_PER_SECOND("kg/s", val -> val, val -> val),
    KILOGRAM_PER_HOUR("kg/h", val -> val / SECONDS_PER_HOUR, val -> val * SECONDS_PER_HOUR),
    TONNE_PER_HOUR("t/h", val -> val * (KILO / SECONDS_PER_HOUR), val -> val / (KILO / SECONDS_PER_HOUR)),
    POUND_PER_SECOND("lb/s", val -> val * KILOGRAMS_PER_POUND, val -> val / KILOGRAMS_PER_POUND);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    MassFlowUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public MassFlowUnit getBaseUnit() {
        return KILOGRAM_PER_SECOND;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static MassFlowUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return getDefaultUnit();
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (MassFlowUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + MassFlowUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .toString();
    }

    public static MassFlowUnit getDefaultUnit() {
        return KILOGRAM_PER_SECOND;
    }

}
