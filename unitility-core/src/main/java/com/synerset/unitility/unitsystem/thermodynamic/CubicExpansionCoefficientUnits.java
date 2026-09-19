package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum CubicExpansionCoefficientUnits implements CubicExpansionCoefficientUnit {

    INVERSE_KELVIN("1/K", 1.0),
    INVERSE_CELSIUS("1/°C", 1.0),
    INVERSE_MILLIKELVIN("1/mK", 1.0E3),
    INVERSE_RANKINE("1/°R", UnitDefinitions.PER_FAHRENHEIT_DEGREE),
    INVERSE_FAHRENHEIT("1/°F", UnitDefinitions.PER_FAHRENHEIT_DEGREE);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    CubicExpansionCoefficientUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    CubicExpansionCoefficientUnits(String symbol, DoubleUnaryOperator toBaseConverter,
                                   DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public CubicExpansionCoefficientUnit getBaseUnit() {
        return INVERSE_KELVIN;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static CubicExpansionCoefficientUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return INVERSE_KELVIN;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (CubicExpansionCoefficientUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + rawSymbol);
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .dropDegreeSymbols()
                .toString();
    }
}
