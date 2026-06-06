package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

/**
 * Gravimetric air-fuel ratio (mass of air per mass of fuel gas). A mass ratio is unit-invariant, the numeric
 * value is identical in any consistent pair of mass units, so all unit converters are identity.
 */
public enum AirFuelRatioMassUnits implements AirFuelRatioMassUnit {

    KILOGRAM_PER_KILOGRAM("kg/kg", val -> val, val -> val),   // Base SI unit
    GRAM_PER_GRAM("g/g", val -> val, val -> val),
    POUND_PER_POUND("lb/lb", val -> val, val -> val),
    OUNCE_PER_OUNCE("oz/oz", val -> val, val -> val);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    AirFuelRatioMassUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public AirFuelRatioMassUnit getBaseUnit() {
        return KILOGRAM_PER_KILOGRAM;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static AirFuelRatioMassUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return KILOGRAM_PER_KILOGRAM;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (AirFuelRatioMassUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equals(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + rawSymbol);
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimAndClean()
                .unifyMultiAndDiv()
                .toString();
    }
}
