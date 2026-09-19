package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;

import java.util.function.DoubleUnaryOperator;

/**
 * Gravimetric air-fuel ratio (mass of air per mass of fuel gas). A mass ratio is unit-invariant, the numeric
 * value is identical in any consistent pair of mass units, so all unit converters are identity.
 */
public enum AirFuelRatioMassUnits implements AirFuelRatioMassUnit {

    KILOGRAM_PER_KILOGRAM("kg/kg", 1.0),   // Base SI unit
    GRAM_PER_GRAM("g/g", 1.0),
    POUND_PER_POUND("lb/lb", 1.0),
    OUNCE_PER_OUNCE("oz/oz", 1.0);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    AirFuelRatioMassUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

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
