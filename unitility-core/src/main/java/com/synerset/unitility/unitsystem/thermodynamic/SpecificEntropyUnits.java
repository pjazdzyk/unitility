package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum SpecificEntropyUnits implements SpecificEntropyUnit {
    
    JOULE_PER_KILOGRAM_KELVIN("J/(kg·K)", 1.0),
    KILOJOULE_PER_KILOGRAM_KELVIN("kJ/(kg·K)", 1.0E3),
    MILLIJOULE_PER_GRAM_KELVIN("mJ/(g·K)", 1.0),
    MEGAJOULE_PER_TONNE_KELVIN("MJ/(t·K)", 1.0E3),
    BTU_PER_POUND_RANKINE("BTU/(lb·°R)", UnitDefinitions.BTU_PER_POUND_FAHRENHEIT_DEGREE),
    BTU_PER_POUND_FAHRENHEIT("BTU/(lb·°F)", UnitDefinitions.BTU_PER_POUND_FAHRENHEIT_DEGREE);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    SpecificEntropyUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    SpecificEntropyUnits(String symbol, DoubleUnaryOperator toBaseConverter, 
                        DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() { return symbol; }

    @Override
    public SpecificEntropyUnit getBaseUnit() { return JOULE_PER_KILOGRAM_KELVIN; }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static SpecificEntropyUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return JOULE_PER_KILOGRAM_KELVIN;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (SpecificEntropyUnit unit : values()) {
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
                .dropParentheses()
                .unifySymbolsOfAngle()
                .toString();
    }
}