package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum SpecificInternalEnergyUnits implements SpecificInternalEnergyUnit {

    JOULE_PER_KILOGRAM("J/kg", 1.0),
    KILOJOULE_PER_KILOGRAM("kJ/kg", 1.0E3),
    MEGAJOULE_PER_KILOGRAM("MJ/kg", 1.0E6),
    BTU_PER_POUND("BTU/lb", UnitDefinitions.BTU_PER_POUND),
    CALORIE_PER_GRAM("cal/g", UnitDefinitions.CALORIE_PER_GRAM);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    SpecificInternalEnergyUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    SpecificInternalEnergyUnits(String symbol,
                                DoubleUnaryOperator toBaseConverter,
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
    public SpecificInternalEnergyUnit getBaseUnit() {
        return JOULE_PER_KILOGRAM;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static SpecificInternalEnergyUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return JOULE_PER_KILOGRAM;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (SpecificInternalEnergyUnits unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: {" + rawSymbol + "}. Target class: "
                + SpecificInternalEnergyUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .toString();
    }
}